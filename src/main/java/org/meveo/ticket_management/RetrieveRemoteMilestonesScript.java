package org.meveo.ticket_management;

import java.util.Map;
import java.time.ZoneOffset;
import java.util.List;
import org.meveo.service.script.Script;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.meveo.model.customEntities.CustomEntityInstance;
import org.meveo.model.persistence.CEIUtils;

import org.meveo.model.customEntities.MV_TCKTMNG_MILESTONE;
import org.meveo.model.customEntities.MV_TCKTMNG_PROJECT;

import org.meveo.model.customEntities.CREDENTIAL;
import org.meveo.admin.exception.BusinessException;
import org.meveo.service.storage.RepositoryService;
import org.meveo.api.persistence.CrossStorageApi;

import com.assembla.Milestone;
import com.assembla.client.AssemblaAPI;
import com.assembla.client.AssemblaResource;
import com.assembla.client.PagedIterator;


public class RetrieveRemoteMilestonesScript extends Script {

  private static final Logger log = LoggerFactory.getLogger(RetrieveRemoteMilestonesScript.class);
    

    private CrossStorageApi crossStorageApi = getCDIBean(CrossStorageApi.class);
	

  	private RepositoryService repositoryService = getCDIBean(RepositoryService.class);
  
    
    private CREDENTIAL getCredential(String domain){
      List<CREDENTIAL> matchingCredentials = crossStorageApi.find(repositoryService.findDefaultRepository(), CREDENTIAL.class)
                .by("DOMAIN", domain)
				.getResults();
      if(matchingCredentials.size()>0){
        return matchingCredentials.get(0);
      } else {
        return null;
      }
    }

    private MV_TCKTMNG_MILESTONE getMilestone(String remoteId){
      MV_TCKTMNG_MILESTONE result=null;
      List<MV_TCKTMNG_MILESTONE> matchingMilestones = crossStorageApi.find(repositoryService.findDefaultRepository(), MV_TCKTMNG_MILESTONE.class)
                .by("remoteId", remoteId)
				.getResults();
      if(matchingMilestones.size()>0){
        result = matchingMilestones.get(0);
      } else {
        result =  new MV_TCKTMNG_MILESTONE();
        result.setRemoteId(remoteId);
      }
      return result;
    }

    public void retrieveAssemblaMilsteones(MV_TCKTMNG_PROJECT project)  throws BusinessException {
      log.debug("retrieveAssemblaTickets for project {}",project.getName());
      CREDENTIAL assemblaCredential = getCredential("assembla.com");
      if(assemblaCredential==null){
        throw new BusinessException("cannot find any Credential for assembla.com, please create one with username= api-key and header-value=api-secret.");
      }
      try {
        AssemblaResource api  = AssemblaAPI.create(assemblaCredential.getUSERNAME(), assemblaCredential.getHEADER_VALUE());
        PagedIterator<Milestone> pgIt=api.milestones(project.getRemoteSpaces().get("assembla.com")).getAll();
        
        pgIt.forEach(milestones->{
          for(Milestone assemblaMilestone:milestones){
            log.debug("milestone  {}",assemblaMilestone);
            MV_TCKTMNG_MILESTONE milestone = getMilestone(assemblaMilestone.getId());
            
            log.debug("milestone title: {}",assemblaMilestone.getTitle());
            milestone.setTitle(assemblaMilestone.getTitle());
            milestone.setDescription(assemblaMilestone.getDescription());
            if(assemblaMilestone.getCreatedAt()!=null){
              milestone.setStartDate(assemblaMilestone.getCreatedAt().toInstant());
            }
            if(assemblaMilestone.getDueDate()!=null){
              milestone.setDueDate(assemblaMilestone.getDueDate().atStartOfDay().toInstant(ZoneOffset.UTC));
            }
            if(assemblaMilestone.getCompletedDate()!=null){
              milestone.setEndDate(assemblaMilestone.getCompletedDate().atStartOfDay().toInstant(ZoneOffset.UTC));
            }
            try{
            crossStorageApi.createOrUpdate(repositoryService.findDefaultRepository(), CEIUtils.pojoToCei(milestone));
            } catch (Exception e){
              throw new RuntimeException("Exception while persisting milestone",e);
            }
          };
        });
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
            retrieveAssemblaMilsteones(project);
          }
        }
      	log.info("project:{}",project);
    }


}