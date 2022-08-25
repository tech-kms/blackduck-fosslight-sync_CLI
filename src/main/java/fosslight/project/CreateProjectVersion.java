package fosslight.project;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import integration.values.LoginValues;
import integration.values.ProjectValues;


public class CreateProjectVersion {
	
	public void createProjectversion() {
		
		LoginValues loginValues = LoginValues.getInstance();		
		ProjectValues projectValues = ProjectValues.getInstance();
		
		String address = loginValues.getFLTserverUri() + "/api/v1/create_project?";		
		String output;
        
		try {
			if(projectValues.getFLTcodeosType() == null) {
				address = address + "prjName=" + projectValues.getFLTprojectName() + "&" + "prjVersion=" + projectValues.getFLTprojectVersion() + "&" + "osType=" + "100";
			} else {
				address = address + "prjName=" + projectValues.getFLTprojectName() + "&" + "prjVersion=" + projectValues.getFLTprojectVersion() + "&" + "osType=" + projectValues.getFLTcodeosType().toString();
			}
			System.out.println(address);			
			 
			URL url = new URL(address);
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();			
			conn.setRequestMethod("GET");
			conn.setRequestProperty("_token", loginValues.getFLTtoken());
			
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		    StringBuffer result = new StringBuffer();
		    
		    while ((output = in.readLine()) != null) {
		          result.append(output);
		    }
		    
		    in.close();
		    
		    JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj1 = (JSONObject) jsonParser.parse(result.toString());
            
            if(jsonObj1.get("code").toString().equals("100")) {
            	JSONObject jsonObj2 = (JSONObject) jsonObj1.get("data");    
    		    projectValues.setFLTprojectId(jsonObj2.get("prjId").toString());
            } else {
            	System.err.println("ERROR: create_projectVersion class" + jsonObj1.get("msg").toString());
            	System.err.println("ERROR: Project Name + ProjectVersion Name :: repeat ");
            	System.exit(1);
            }

            System.out.println();
            System.out.println("Project" + projectValues.getFLTprojectName() + " / Version: " + projectValues.getFLTprojectName() + " / Id: " + projectValues.getFLTprojectId() + " is created");            
            
		} catch (Exception e) {
			e.printStackTrace();

		}		
	}
	
}

