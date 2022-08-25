package integration.main;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import integration.values.LoginValues;

public class LoginInfo {
	
	public void setInfo(String bd_url, String bd_token, String bd_projectName, String bd_projectVersion,
			String flt_protocol, String flt_address, String flt_token) {
		
		setBDLogininfo(bd_url, bd_token, bd_projectName, bd_projectVersion);
		
		setFLTLogininfo(flt_protocol, flt_address, flt_token);
	}
		
	void setBDLogininfo(String bd_url, String bd_token, String bd_projectname, String bd_projectversion) {
		LoginValues bd_lvalues = LoginValues.getInstance();

		try {
			Properties props = new Properties();
			InputStream is = getClass().getResourceAsStream("/config.properties");
			props.load(is);

			String bdurl = "";
			String bdtoken = "";
			String bdprojectname = "";
			String bdprojectversion = "";
			if(bd_url.equals("")) {
				bdurl = props.getProperty("bd_url");
			} else {
				bdurl = bd_url;
			}
				
			if(bd_token.equals("")) {
				bdtoken = props.getProperty("bd_token");
			} else {
				bdtoken = bd_token;
			}
			if(bd_projectname.equals("")) {
				bdprojectname = props.getProperty("bd_project_name");
			} else {
				bdprojectname = bd_projectname;
			}
				
			if(bd_projectversion.equals("")) {
				bdprojectversion = props.getProperty("bd_project_version");
			} else {
				bdprojectversion = bd_projectversion;
			}
			
			bd_lvalues.setBDserverUri(bdurl);
			bd_lvalues.setBDtoken("token "+bdtoken);
			bd_lvalues.setBDprojectName(bdprojectname);
			bd_lvalues.setBDprojectVersion(bdprojectversion);

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		
	}
	
	private void setFLTLogininfo(String flt_protocol, String flt_address, String flt_token) {
		
		LoginValues flt_lvalues = LoginValues.getInstance();		
		
		try {
			
			Properties props = new Properties();
			InputStream is = getClass().getResourceAsStream("/config.properties");
			props.load(is);
			
			String flt_schema = "";
			String flt_url = "";

			if(flt_protocol.equals("")) {
				flt_schema = props.getProperty("fosslight.schema");
			} else {
				flt_schema = flt_protocol;
			}
			
			if(flt_address.equals("")) {
				flt_url = props.getProperty("fosslight.domain");
			} else {
				flt_url = flt_address;
			}
			
			if(flt_schema.equals("http")) {
				flt_lvalues.setFLTserverUri("http://" + flt_url);
			} else if(flt_schema.equals("https")) {				
			    flt_lvalues.setFLTserverUri("https://" + flt_url);
			}

			String fltToken = "";			
			
			if(flt_token.equals("")) {
				fltToken = props.getProperty("fosslight.token");
			} else {
				fltToken = flt_token;
			}
			
			flt_lvalues.setFLTtoken(fltToken);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
	
	}

}