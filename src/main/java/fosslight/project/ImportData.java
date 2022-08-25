package fosslight.project;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import integration.values.ExcelValues;
import integration.values.LoginValues;
import integration.values.ProjectValues;


public class ImportData {
	
	public static void importData() {
		
		LoginValues loginValues = LoginValues.getInstance();
		ExcelValues excelValues = ExcelValues.getInstance();
		ProjectValues projectValues = ProjectValues.getInstance();

        
		String address = loginValues.getFLTserverUri() + "/api/v1/oss_report_src?prjId=";
		String reportPath = excelValues.getfilepath();
		String token = loginValues.getFLTtoken();
        		
		try {			
			
			HttpClient httpClient = HttpClientBuilder.create().build();
			
			HttpPost httpPost = new HttpPost (address + projectValues.getFLTprojectId());
			System.out.println("Start httpPost " +  httpPost);
			//httpPost.addHeader("content-type", "application/json");			
			httpPost.addHeader("_token", token);						
			
			File file = new File(reportPath);			
			FileBody fileBody = new FileBody(file, ContentType.DEFAULT_BINARY);
			// 
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
			builder.addPart("ossReport", fileBody);			
			HttpEntity entity = builder.build();
		
			httpPost.setEntity(entity);
			HttpResponse httpClientResponse = httpClient.execute(httpPost);
			
			if (httpClientResponse.getStatusLine().getStatusCode() != 200) {
				System.out.println("Failed : HTTP Error code : " + httpClientResponse.getStatusLine().getStatusCode());
				System.exit(1);
			}			
			
			BufferedReader br = new BufferedReader(
					new InputStreamReader(httpClientResponse.getEntity().getContent(), "utf-8"));
			String result = br.readLine();
			
			JSONParser jsonParser = new JSONParser();
            JSONObject jsonObj1 = (JSONObject) jsonParser.parse(result.toString());
			
			if(jsonObj1.get("code").toString().equals("100")) {				
				System.out.println("Finish Importing " +  excelValues.getfilepath() +" into FOSSLight");				
            } else {
            	System.err.println("ERROR: import_data class" + jsonObj1.get("msg").toString());
            	System.exit(1);
            }
						
			
		} catch (Exception e) {			
			e.printStackTrace();
			System.exit(1);
		}
        
		
	
	}

}
