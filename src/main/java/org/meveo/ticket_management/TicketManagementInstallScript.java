package org.meveo.ticket_management;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.meveo.admin.exception.BusinessException;
import org.meveo.service.config.impl.MavenConfigurationService;
import org.meveo.service.script.module.ModuleScript;

public class TicketManagementInstallScript extends ModuleScript {
    
    static final String REPO_NAME="smichea";
    static final String GROUP_ID="com.assembla";
    static final String ARTIFACT_ID="assembla-api";
    static final String VERSION="0.2";
    static final String CLASSIFIER="";
    static final String FILENAME = "assembla-api-0.2.jar";

    MavenConfigurationService mavenConfigurationService = getCDIBean(MavenConfigurationService.class);

    protected InputStream  downloadJar(){
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target("https://github.com/"+REPO_NAME+"/"+ARTIFACT_ID+"/releases/download/v"+VERSION+"/"+FILENAME);
        Response response = webTarget.request().get();
        if(response.getStatus()==302){
            String location = response.getHeaderString("Location");
            response = client.target(location).request().get();
        }
        if(response.getStatus()==200){
            InputStream in = response.readEntity(InputStream.class);
            return in;
        }
        return null;
    }

    protected void uploadMavenArtifact(InputStream inputStream, File file) throws BusinessException{
		try {
			OutputStream outputStream = new FileOutputStream(file);
			int read = 0;
			byte[] data = new byte[1024];

			while ((read = inputStream.read(data)) != -1) {
				outputStream.write(data, 0, read);
			}

			inputStream.close();
			outputStream.flush();
			outputStream.close();

		} catch (Exception e) {
			throw new BusinessException("Error uploading file: " + FILENAME + ". " + e.getMessage());
		}
	}
    
    protected boolean artifactExist(){
        boolean result=false;
        return result;
    }

    protected void updloadArtifact() throws BusinessException{
        String filePath = mavenConfigurationService.createDirectory(GROUP_ID, ARTIFACT_ID, VERSION, CLASSIFIER);
		filePath = filePath + File.separator + mavenConfigurationService.buildArtifactName(ARTIFACT_ID, VERSION, CLASSIFIER);
        File file =new File(filePath);
        if(!file.exists()){
            InputStream in = downloadJar();
            uploadMavenArtifact(in,file);
        }
    }

    public void preInstallModule(Map<String, Object> methodContext) throws BusinessException {
        updloadArtifact();
    }

    public void execute(Map<String, Object> methodContext) throws BusinessException {
        updloadArtifact();
    }
}
