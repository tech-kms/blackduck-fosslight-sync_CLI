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


public class CodeSearch {

	LoginValues loginValues = LoginValues.getInstance();		
	ProjectValues projectValues = ProjectValues.getInstance();
	
	public void codesearch(String osType) {
		
		String address = loginValues.getFLTserverUri() + "/api/v1/code_search?codeType=OS";
	    String output;
	    
		try {
			
			Properties props = new Properties();
			InputStream is = getClass().getResourceAsStream("/config.properties");
			props.load(is);
			
			// To change encoding to UTF-8
			String encoding = "";

			if(osType.equals("")) {
				
				encoding = new String(props.getProperty("fosslight.project").getBytes("iso-8859-1"), "UTF-8");	
				
				if(encoding.equals("")) {
					osType = "Linux";
				} else {
				    osType = encoding;
				}
				
			} 
			
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
	            JSONArray dataArray = (JSONArray) jsonObj2.get("content");
                
	            for(int i=0; i < dataArray.size(); i++) {
                    JSONObject tempObj = (JSONObject) dataArray.get(i);
                    if(tempObj.get("cdDtlNm").toString().equals(osType)) {
                    	projectValues.setFLTcodeosType(tempObj.get("cdDtlNo").toString());
                    }
                }
	      	            
            } else {
            	System.err.println("ERROR: code_search class" + jsonObj1.get("msg").toString());
            	System.exit(1);
            }
		    
		    System.out.println();
		    System.out.println("OS Type: " + osType + "  OS Code: " + projectValues.getFLTcodeosType());		    
		    
		} catch (Exception e) {
			e.printStackTrace();

		}		
	
	}
	
}
