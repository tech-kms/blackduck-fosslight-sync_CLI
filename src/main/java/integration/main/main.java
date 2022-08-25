package integration.main;

import blackduck_getdata.BearerTokenkey;
import blackduck_getdata.Components;
import blackduck_getdata.ProjectInformation;
import blackduck_getdata.SSLTrustAllCerts;
import integration.main.LoginInfo;
import integration.values.ProjectValues;
import fosslight.project.*;
import blackduck_report.CreateReport;
import blackduck_report.DeleteReportfile;

public class main {
	static ProjectValues pvalues = ProjectValues.getInstance();

	public static void main(String[] args) {

		try {
			runwithArgu(args);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void runwithArgu(String[] args) {

		String bd_url = "";
		String bd_token = "";
		String bd_projectname = "";
		String bd_projectversion = "";

		String flt_protocol = "";
		String flt_address = "";
		String flt_token = "";
		String flt_projectname = "";
		String flt_projectversion = "";
		String flt_ostype = "";
		String flt_projectid = "";
	
		System.out.println("===================================================================");
		for (int i = 0; i < args.length; i++) {
			
			if (args[i].equals("--bdurl")) {
				bd_url = args[i + 1];
				System.out.println("bdurl             : " + bd_url);
			}
			if (args[i].equals("--bdtoken")) {
				bd_token = args[i + 1];
				System.out.println("bdtoken           : " + bd_token);
			}

			if (args[i].equals("--bdprojectname")) {
				bd_projectname = args[i + 1];
				System.out.println("bdprojectname     : " + bd_projectname);
			}

			if (args[i].equals("--bdprojectversion")) {
				bd_projectversion = args[i + 1];
				System.out.println("bdprojectversion  : " + bd_projectversion);
			}
			if (args[i].equals("--fltprotocol")) {
				flt_protocol = args[i + 1];
				System.out.println("fltprotocol       : " + flt_protocol);
			}

			if (args[i].equals("--fltaddress")) {
				flt_address = args[i + 1];
				System.out.println("fltaddress        : " + flt_address);
			}

			if (args[i].equals("--flttoken")) {
				flt_token = args[i + 1];
				System.out.println("flttoken          : " + flt_token);
			}

			if (args[i].equals("--fltprojectname")) {
				flt_projectname = args[i + 1];
				System.out.println("fltprojectname    : " + flt_projectname);
			}

			if (args[i].equals("--fltprojectversion")) {
				flt_projectversion = args[i + 1];
				System.out.println("fltprojectversion : " + flt_projectversion);
			}

			if (args[i].equals("--fltostype")) {
				flt_ostype = args[i + 1];
				System.out.println("fltostype         : " + flt_ostype);
			}
			if (args[i].equals("--fltprojectid")) {
				flt_projectid = args[i + 1];
				System.out.println("fltprojectid      : " + flt_projectid);
			}
			
			i++;
		}
		System.out.println("===================================================================");

		// SSL인증 처리
		SSLTrustAllCerts sslTrustAllCerts = new SSLTrustAllCerts();
		sslTrustAllCerts.sslTrustAllCerts();
		
		PrintInfo printInfo = new PrintInfo(); 
		printInfo.startBlackDuck();; //log
		
		// 입력_초기값셋팅
		LoginInfo logininfo = new LoginInfo();
		logininfo.setInfo(bd_url, bd_token, bd_projectname, bd_projectversion, flt_protocol, flt_address, flt_token);

		// BearerToken 조회
		BearerTokenkey bearertokenkey = new BearerTokenkey();
		bearertokenkey.getBearerTokenkey();
		
		// Project정보조회 (List, ID, Version)
		ProjectInformation projectinformation = new ProjectInformation();
		projectinformation.getProjectInformation();
		
		printInfo.printBlackDuckInfo(); //log
		
		// Components
		Components components = new Components();
		components.getComponentsstart();

		// Report.xlsx 파일을 생성
		CreateReport createReport = new CreateReport();
		createReport.writeReport(null);

		// FOSSLight
		printInfo.startFosslight(); //log
		
		CheckProjectID check_projectVersion = new CheckProjectID();
		check_projectVersion.check_projectversion(flt_projectname, flt_projectversion, flt_projectid);
		
		printInfo.printFLTInfo(); //log
		

		if (pvalues.getFLTprojectIdCheck().equals("true")) {
			ImportData importData = new ImportData();
			importData.importData();

		} else if (pvalues.getFLTprojectIdCheck().equals("false")) {
			CodeSearch codesearch = new CodeSearch();
			if (!(flt_ostype.equals(""))) {
				codesearch.codesearch(flt_ostype);
			}

			CreateProjectVersion createprojectVersion = new CreateProjectVersion();
			createprojectVersion.createProjectversion();

			ImportData importData = new ImportData();
			importData.importData();

		}

		DeleteReportfile deleteReportFile = new DeleteReportfile();
		deleteReportFile.deleteReportfile();
	}
}
