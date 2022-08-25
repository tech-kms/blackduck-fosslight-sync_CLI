package integration.values;

public class LoginValues {

	private static LoginValues values = new LoginValues();

	public LoginValues() {
	}

	public static LoginValues getInstance() {
		return values;
	}

	private String bd_serveruri;	
	private static String bd_projectname;
	private static String bd_projectversion;
	private static String bd_token;
	
	public String getBDserverUri() {
		return bd_serveruri;
	}

	public void setBDserverUri(String db_serveruri) {
		this.bd_serveruri = db_serveruri;
	}
	
	public String getBDprojectName() {
		return bd_projectname;
	}

	public void setBDprojectName(String bd_projectname) {
		this.bd_projectname = bd_projectname;
	}
	
	public String getBDprojectVersion() {
		return bd_projectversion;
	}

	public void setBDprojectVersion(String bd_projectversion) {
		this.bd_projectversion = bd_projectversion;
	}
	
	public String getBDtoken() {
		return bd_token;
	}

	public void setBDtoken(String bd_token) {
		this.bd_token = bd_token;
	}
	
	
	// Fosslight Login Values	
	private String flt_serveruri;		
	private static String flt_token;
	
	public String getFLTserverUri() {
		return flt_serveruri;
	}
	
	public void setFLTserverUri(String flt_serveruri) {
		this.flt_serveruri = flt_serveruri;
	}
	
	public String getFLTtoken() {
		return flt_token;
	}

	public void setFLTtoken(String flt_token) {
		this.flt_token = flt_token;
	}

}
