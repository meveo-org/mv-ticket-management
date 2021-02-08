package org.meveo.ticket_management;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHIssueBuilder;
import org.kohsuke.github.GHIssueState;
import org.kohsuke.github.GHMilestone;
import org.kohsuke.github.GHProject;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.kohsuke.github.PagedIterable;
import org.meveo.admin.exception.BusinessException;
import org.meveo.api.persistence.CrossStorageApi;
import org.meveo.model.customEntities.CREDENTIAL;
import org.meveo.model.customEntities.CustomEntityInstance;
import org.meveo.model.customEntities.MV_TCKTMNG_COMMENT;
import org.meveo.model.customEntities.MV_TCKTMNG_MILESTONE;
import org.meveo.model.customEntities.MV_TCKTMNG_PROJECT;
import org.meveo.model.customEntities.MV_TCKTMNG_TICKET;
import org.meveo.model.persistence.CEIUtils;
import org.meveo.service.script.Script;
import org.meveo.service.storage.RepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WriteOpenTicketsToGithub extends Script {
    String oauthToken = "acccd14e50cf230f8d58311dabc25815a73028e8";
    private static final Logger log = LoggerFactory.getLogger(WriteOpenTicketsToGithub.class);

    private CrossStorageApi crossStorageApi = getCDIBean(CrossStorageApi.class);

    private RepositoryService repositoryService = getCDIBean(RepositoryService.class);

    private CREDENTIAL getCredential(String domain) {
        List<CREDENTIAL> matchingCredentials = crossStorageApi
                .find(repositoryService.findDefaultRepository(), CREDENTIAL.class).by("DOMAIN", domain).getResults();
        if (matchingCredentials.size() > 0) {
            return matchingCredentials.get(0);
        } else {
            return null;
        }
    }

    private List<MV_TCKTMNG_MILESTONE> getMilestones(String projectUuid) {
        List<MV_TCKTMNG_MILESTONE> matchingMilestones = crossStorageApi
                .find(repositoryService.findDefaultRepository(), MV_TCKTMNG_MILESTONE.class).by("project", projectUuid)
                .getResults();
        return matchingMilestones;
    }


    private List<MV_TCKTMNG_TICKET> getTickets(String milestoneUuid) {
        List<MV_TCKTMNG_TICKET> matchingTickets = crossStorageApi
                .find(repositoryService.findDefaultRepository(), MV_TCKTMNG_TICKET.class).by("milestone", milestoneUuid)
                .getResults();
        return matchingTickets;
    }


    private void createGithubTicketsForMilestone(GitHub github, MV_TCKTMNG_MILESTONE milestone,
            MV_TCKTMNG_PROJECT project)  throws BusinessException {
        try {
            //GHProject ghProject = github.getProject(Long.parseLong(project.getRemoteSpaces().get("github.com")));
            GHRepository ghRepository = github.getRepository(project.getRemoteSpaces().get("github.com"));
            GHMilestone ghMilestone = null;
            List<GHMilestone> ghMilestones = ghRepository.listMilestones(GHIssueState.ALL).asList();
            for(GHMilestone gh_milestone:ghMilestones) {
                if(milestone.getTitle().equals(gh_milestone.getTitle())){
                    ghMilestone=gh_milestone;
                    break;
                }
            };
            if(ghMilestone==null){
                ghMilestone = ghRepository.createMilestone(milestone.getTitle(), milestone.getDescription());
            }
            List<MV_TCKTMNG_TICKET> tickets = getTickets(milestone.getUuid());
            Map<String,MV_TCKTMNG_TICKET> openTickets= tickets.stream().filter(ticket-> (ticket.getClosedAt()==null)).collect(Collectors.toMap(MV_TCKTMNG_TICKET::getTitle, ticket -> ticket));
            PagedIterable<GHIssue> issues = ghRepository.listIssues(GHIssueState.ALL);
            issues.forEach(issue ->{
                if(openTickets.containsKey(issue.getTitle())){
                    openTickets.remove(issue.getTitle());
                    log.debug("ticket ["+issue.getTitle()+"] already exist in github, we ignore it");
                }
            });
            for(MV_TCKTMNG_TICKET ticket:openTickets.values()){
                GHIssueBuilder createIssue = ghRepository.createIssue(ticket.getTitle());
                //TODO: implement assignee mapping
                createIssue.body(ticket.getDescription());
                createIssue.milestone(ghMilestone);
                for(String tag:ticket.getTags()){
                    if((!"open".equalsIgnoreCase(tag))&&(!"closed".equalsIgnoreCase(tag))){
                        createIssue.label(tag);
                    }
                }
                GHIssue issue = createIssue.create();
                /*if(ticket.getComments()!=null){
                    for(MV_TCKTMNG_COMMENT comment:ticket.getComments()){
                        issue.comment(comment.getDescription());
                    }
                } */  
            }
        } catch (NumberFormatException e) {
            throw new BusinessException("project should contain a \"github.com\" remote space containing the repository name");
        } catch (IOException e) {
            throw new BusinessException("Cannot find project with id :"+project.getRemoteSpaces().get("github.com"));
        }
  }

  public void createGithubTickets(MV_TCKTMNG_PROJECT project) throws BusinessException {
    log.debug("createGithubTickets for project {}", project.getName());
    CREDENTIAL assemblaCredential = getCredential("github.com");
    if (assemblaCredential == null) {
      throw new BusinessException(
          "cannot find any Credential for github.com, please create one with token set to your github personal token.");
    }
    try {
        GitHub github = new GitHubBuilder().withOAuthToken(assemblaCredential.getTOKEN()).build();
        List<MV_TCKTMNG_MILESTONE> milestones = getMilestones(project.getUuid());
        for(MV_TCKTMNG_MILESTONE milestone:milestones){
          createGithubTicketsForMilestone(github,milestone,project);
        }
      } catch (Exception e){
        throw new BusinessException("Exception while creating github tickets",e);
      }
    }


    @Override
  public void execute(Map<String,Object> params)  throws BusinessException {
      CustomEntityInstance cei = (CustomEntityInstance)params.get("CONTEXT_ENTITY");
      MV_TCKTMNG_PROJECT project = CEIUtils.ceiToPojo(cei, MV_TCKTMNG_PROJECT.class);
      for(String domain:project.getRemoteSpaces().keySet()){
        if("github.com".equals(domain)){
          createGithubTickets(project);
        }
      }
        log.info("project:{}",project);
  }
}
