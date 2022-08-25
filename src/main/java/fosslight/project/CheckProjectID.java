package fosslight.project;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import integration.values.LoginValues;
import integration.values.ProjectValues;


public class CheckProjectID {	
	
	static LoginValues loginValues = LoginValues.getInstance();		
	static ProjectValues projectValues = ProjectValues.getInstance();
	
	
	public void check_projectversion(String projectName, String projectVersion, String projectid) {
			
		flt_projectList();
		setflt_projectVersion(projectName,  projectVersion);

		if(projectValues.getFLTprojectIdList().size() > 0) {
			for(int i = 0; i < projectValues.getFLTprojectIdList().size(); i++) {			
				if(projectid.equals(projectValues.getFLTprojectIdList().get(i))){
					projectValues.setFLTprojectIdCheck("true");
					projectValues.setFLTprojectId(projectid);
				}
			}	
		}		
	}

	private void flt_projectList() {
		
		String address = loginValues.getFLTserverUri() + "/api/v1/prj_search";
	    String output;
       
		try {
			URL url = new URL(address);
			System.err.println("");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();			
			conn.setRequestMethod("GET");
			conn.setRequestProperty("_token", loginValues.getFLTtoken());
			conn.setRequestProperty("Content-Type","application/json");
			
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
                JSONArray dataArray = (JSONArray) jsonObj2.get("content");
                
                if(jsonObj2.get("content") != null || !(jsonObj2.get("content").equals(""))) {
                	
                	for(int i=0; i < dataArray.size(); i++) {
                        JSONObject tempObj = (JSONObject) dataArray.get(i);            
                        projectValues.setFLTprojectIdList(tempObj.get("prjId").toString());
                    }
                
                }
            } else {            	
            	System.err.println("ERROR: check_projectVersion class" + jsonObj1.get("msg").toString());
            	System.exit(1);
            }
            
		    
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	
	private void setflt_projectVersion(String projectName, String projectVersion) {
		
		try {
			
			Properties props = new Properties();
			InputStream is = getClass().getResourceAsStream("/config.properties");
			props.load(is);
			if(!(projectName.equals(""))) {	
				projectValues.setFLTprojectName(projectName);
			} else {
				projectValues.setFLTprojectName(projectValues.getBDprojectName());
			}
			
			if(!(projectVersion.equals(""))) {
				projectValues.setFLTprojectVersion(projectVersion);
			} else {
				projectValues.setFLTprojectVersion(projectValues.getBDprojectVersionName());
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
