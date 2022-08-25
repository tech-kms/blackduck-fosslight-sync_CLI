package integration.values;

import java.util.ArrayList;

public class IdentifiedFilesValues {
	
	private static IdentifiedFilesValues values = new IdentifiedFilesValues();

	private IdentifiedFilesValues() {
	}

	public static IdentifiedFilesValues getInstance() {
		return values;
	}
	
	private ArrayList<String> filepath = new ArrayList<String>();
	private ArrayList<String> componenetName = new ArrayList<String>();
	private ArrayList<String> componenetId = new ArrayList<String>();
	private ArrayList<String> componentVersion = new ArrayList<String>();
	private ArrayList<String> componentLicenseId = new ArrayList<String>();
	private ArrayList<String> componenetHomepage = new ArrayList<String>();
	private ArrayList<String> componenetDownload = new ArrayList<String>();
	private ArrayList<String> copyright = new ArrayList<String>();
	private ArrayList<String> copyright2 = new ArrayList<String>();
	private ArrayList<String> comment = new ArrayList<String>();
	
	private int totalIdcount;
	

	public ArrayList<String> getfilepath() {
		return filepath;
	}
	
	public void setfilepath(String filepath) {
		this.filepath.add(filepath);
	}

	public ArrayList<String> getcomponenetName() {
		return componenetName;
	}
	public void setcomponenetName(String componenetName) {
		this.componenetName.add(componenetName);
	}
	
	public ArrayList<String> getcomponentId() {
		return componenetId;
	}
	public void setcomponentId(String componenetId) {
		this.componenetId.add(componenetId);
	}

	public ArrayList<String> getcomponentVersion() {
		return componentVersion;
	}
	public void setcomponentVersion(String componentVersion) {
		this.componentVersion.add(componentVersion);
	}

	public ArrayList<String> getcomponentLicenseId() {
		return componentLicenseId;
	}
	public void setcomponentLicenseId(String componentLicenseId) {
		this.componentLicenseId.add(componentLicenseId);
	}
	
	public ArrayList<String> getcomponentHompage() {
		return componenetHomepage;
	}
	public void setcomponentHompage(String componenetHomepage) {
		this.componenetHomepage.add(componenetHomepage);
	}
	
	public ArrayList<String> getcomponentDownload() {
		return componenetDownload;
	}
	public void setcomponentDownload(String componenetDownload) {
		this.componenetDownload.add(componenetDownload);
	}
	
	public ArrayList<String> getCopyright() {
		return copyright;
	}
	
	public void setCopyright(String copyright) {
		this.copyright.add(copyright); 
	}	
	
	public ArrayList<String> getCopyright2() {
		return copyright2;
	}
	
	public void setCopyright2(String copyright2) {
		this.copyright2.add(copyright2);
	}	
	
	public ArrayList<String> getcommnet() {
		return comment;
	}
	public void setcommnet(String comment) {
		this.comment.add(comment);
	}
	
	public int gettotalIdcount() {
		return totalIdcount;
	}
	public void settotalIdcount(int totalIdcount) {
		this.totalIdcount = totalIdcount;
	}
	
	
}

