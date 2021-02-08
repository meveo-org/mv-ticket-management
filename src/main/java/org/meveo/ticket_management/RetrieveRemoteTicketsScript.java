package org.meveo.ticket_management;

import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import org.meveo.service.script.Script;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.meveo.model.customEntities.CustomEntityInstance;
import org.meveo.model.persistence.CEIUtils;

import org.meveo.model.customEntities.MV_TCKTMNG_TICKET;
import org.meveo.model.customEntities.MV_TCKTMNG_COMMENT;
import org.meveo.model.customEntities.MV_TCKTMNG_MILESTONE;
import org.meveo.model.customEntities.MV_TCKTMNG_PROJECT;

import org.meveo.model.customEntities.CREDENTIAL;
import org.meveo.admin.exception.BusinessException;
import org.meveo.service.storage.RepositoryService;
import org.meveo.api.persistence.CrossStorageApi;

import com.assembla.State;
import com.assembla.Ticket;
import com.assembla.TicketComment;
import com.assembla.Ticket.Priority;
import com.assembla.client.AssemblaAPI;
import com.assembla.client.AssemblaResource;
import com.assembla.client.PagedIterator;
import com.assembla.service.TicketRequest;
import com.assembla.service.TicketRequest.Builder;

public class RetrieveRemoteTicketsScript extends Script {

  private static final Logger log = LoggerFactory.getLogger(RetrieveRemoteTicketsScript.class);

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

  public void retrieveAssemblaTicketsForMilestone(AssemblaResource api, MV_TCKTMNG_MILESTONE milestone,
      MV_TCKTMNG_PROJECT project,TicketRequest tr) throws BusinessException {
     PagedIterator<Ticket> pgIt = api.tickets(project.getRemoteSpaces().get("assembla.com"))
        .getByMilestone(milestone.getRemoteId(), tr);
    pgIt.forEach(tickets->{;
    for (Ticket assemblaTicket : tickets) {
      log.debug("tickets  {}", assemblaTicket);
      MV_TCKTMNG_TICKET ticket =new MV_TCKTMNG_TICKET();
      ticket.setMilestone(milestone);
      ticket.setProject(project);
      ticket.setRemoteId(assemblaTicket.getNumber()+" - "+assemblaTicket.getId());
      ticket.setTitle(assemblaTicket.getSummary());
      ticket.setDescription(assemblaTicket.getDescription());
      ticket.setCreator(assemblaTicket.getReporterId());
      if(assemblaTicket.getCreatedOn()!=null){
        ticket.setCreatedAt(assemblaTicket.getCreatedOn().toInstant());
      }
      if(assemblaTicket.getCompletedDate()!=null){
        ticket.setClosedAt(assemblaTicket.getCompletedDate().toInstant());
      }
      if(assemblaTicket.getUpdatedAt()!=null){
        ticket.setUpdatedAt(assemblaTicket.getUpdatedAt().toInstant());
      }
      List<String> assignees = new ArrayList<String>();
      assignees.add(assemblaTicket.getAssignedToId());
      ticket.setAssignees(assignees);
      List<String> tags = new ArrayList<String>();
      Priority p= assemblaTicket.getPriority();
      if(p!=null){
        tags.add(p.name());
      }
      List<String> atags=assemblaTicket.getTags();
      if(atags!=null){
        tags.addAll(atags);
      }
      State s = assemblaTicket.getState();
      if(s!=null){
        tags.add(s.name());
      }
      String status = assemblaTicket.getStatus();
      if(status!=null){
        tags.add(status);
      }
      ticket.setTags(tags);
      List<MV_TCKTMNG_COMMENT> comments = new ArrayList<>();
      PagedIterator<TicketComment> pigtcomments = api.ticketComments(project.getRemoteSpaces().get("assembla.com")).getAll(assemblaTicket.getNumber());
      pigtcomments.forEach(assemblaComments -> {
        for(TicketComment assemblaComment:assemblaComments){
          log.debug("comment  {}", assemblaComments);
          MV_TCKTMNG_COMMENT comment = new MV_TCKTMNG_COMMENT();
          if(assemblaComment.getCreatedOn()!=null){
            comment.setCreatedAt(assemblaComment.getCreatedOn().toInstant());
          }
          if(assemblaComment.getComment()!=null && assemblaComment.getComment().length()>0){
            comment.setDescription(assemblaComment.getComment());
            if(assemblaComment.getUpdatedAt()!=null){
              comment.setUpdatedAt(assemblaComment.getUpdatedAt().toInstant());
            }
            comment.setCreator(assemblaComment.getUserId()+"");
            comments.add(comment);
          } 
        }
      });
      ticket.setComments(comments);
      try {
        crossStorageApi.createOrUpdate(repositoryService.findDefaultRepository(), CEIUtils.pojoToCei(ticket));
      } catch (Exception e) {
        throw new RuntimeException("Exception while persisting ticket", e);
      }
    }
  });
  }

  public void retrieveAssemblaTickets(MV_TCKTMNG_PROJECT project) throws BusinessException {
    log.debug("retrieveAssemblaTickets for project {}", project.getName());
    CREDENTIAL assemblaCredential = getCredential("assembla.com");
    if (assemblaCredential == null) {
      throw new BusinessException(
          "cannot find any Credential for assembla.com, please create one with username= api-key and header-value=api-secret.");
    }
    try {
      AssemblaResource api = AssemblaAPI.create(assemblaCredential.getUSERNAME(), assemblaCredential.getHEADER_VALUE());
      Builder builder= new Builder();
      TicketRequest tr = new TicketRequest(builder.all());
        List<MV_TCKTMNG_MILESTONE> milestones = getMilestones(project.getUuid());
        for(MV_TCKTMNG_MILESTONE milestone:milestones){
          retrieveAssemblaTicketsForMilestone(api,milestone,project,tr);
        }
      } catch (Exception e){
        throw new BusinessException("Exception while retrieving assembla tickets",e);
      }
    }

    @Override
    public void execute(Map<String,Object> params)  throws BusinessException {
        CustomEntityInstance cei = (CustomEntityInstance)params.get("CONTEXT_ENTITY");
        MV_TCKTMNG_PROJECT project = CEIUtils.ceiToPojo(cei, MV_TCKTMNG_PROJECT.class);
        for(String domain:project.getRemoteSpaces().keySet()){
          if("assembla.com".equals(domain)){
            retrieveAssemblaTickets(project);
          }
        }
      	log.info("project:{}",project);
    }


}