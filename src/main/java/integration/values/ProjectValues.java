package integration.values;

import java.util.ArrayList;

public class ProjectValues {
	
	private static ProjectValues values = new ProjectValues();

	public ProjectValues() {
	}

	public static ProjectValues getInstance() {
		return values;
	}
	private static String bd_bearerkokenkey;	
	private static String bd_projectid;	
	private static String bd_projectname;
	private static String bd_projectversionname;	
	private static String bd_projectversionid;
	
	private static String bd_scanid;
	private static String bd_scanname;
	
	private static String flt_projectName;
	private static String flt_projectVersion;
	private static ArrayList<String> flitflt_projectidlist = new ArrayList<String>();
	private static String flt_projectid;
	private static String flt_projectidcheck = "false";
	private static String flt_codeosType;
	
	public String getBDbearerkokenKey() {
		return bd_bearerkokenkey;
	}
	public void setBDbearerkokenKey(String bd_bearerkokenkey) {
		this.bd_bearerkokenkey = bd_bearerkokenkey;
	}
	
	public String getBDprojectId() {
		return bd_projectid;
	}

	public void setBDprojectId(String bd_projectid) {
		this.bd_projectid = bd_projectid;
	}

	public String getBDprojectName() {
		return bd_projectname;
	}

	public void setBDprojectName(String bd_projectname) {
		this.bd_projectname = bd_projectname;
	}

	public String getBDprojectVersionName() {
		return bd_projectversionname;
	}

	public void setBDprojectVersionName(String bd_projectversionname) {
		this.bd_projectversionname = bd_projectversionname;
	}

	public String getBD_projectVersionId() {
		return bd_projectversionid;
	}

	public void setBD_projectVersionId(String bd_projectversionid) {
		this.bd_projectversionid = bd_projectversionid;
	}
	
	public String getBDscanId() {
		return bd_scanid;
	}

	public void setBDscanId(String bd_scanid) {
		this.bd_scanid = bd_scanid;
	}

	public void setBDscanName(String bd_scanname) {
		this.bd_scanname = bd_scanname;
	}
	
	public String getBDscanName() {
		return bd_scanname;
	}		

	public void setFLTprojectName(String flt_projectName) {
		this.flt_projectName = flt_projectName;
	}
	
	public String getFLTprojectName() {
		return flt_projectName;
	}
	
	public void setFLTprojectVersion(String flt_projectVerion) {
		this.flt_projectVersion = flt_projectVerion;
	}
			
	public String getFLTprojectVersion() {
		return flt_projectVersion;
	}

	public void setFLTprojectIdList(String projectVersionlist) {
		this.flitflt_projectidlist.add(projectVersionlist);
	}
	
	public ArrayList<String> getFLTprojectIdList() {
		return flitflt_projectidlist;
	}
	
	public String getFLTprojectId() {
		return flt_projectid;
	}

	public void setFLTprojectId(String flt_projectid) {
		this.flt_projectid = flt_projectid;
	}
	
	public String getFLTprojectIdCheck() {
		return flt_projectidcheck;
	}

	public void setFLTprojectIdCheck(String flt_projectidcheck) {
		this.flt_projectidcheck = flt_projectidcheck;
	}
	
	public String getFLTcodeosType() {
		return flt_codeosType;
	}

	public void setFLTcodeosType(String flt_codeosType) {
		this.flt_codeosType = flt_codeosType;
	}

}
