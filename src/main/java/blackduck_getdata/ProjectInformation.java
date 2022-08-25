package blackduck_getdata;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import integration.values.LoginValues;
import integration.values.ProjectValues;

public class ProjectInformation {
	ProjectValues projectValues = ProjectValues.getInstance();
	LoginValues loginValues = LoginValues.getInstance();

	public void getProjectInformation() {

		getProject();
		getProjectVersionList();

	}

	public void getProject() {
		String address = loginValues.getBDserverUri() + "/api/projects?q=name:" + loginValues.getBDprojectName();
		String output = null;
		try {
			URL url = new URL(address);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/vnd.blackducksoftware.project-detail-4+json");
			conn.setRequestProperty("Content-Type", "application/vnd.blackducksoftware.project-detail-4+json");
			conn.setRequestProperty("Authorization", "Bearer " + projectValues.getBDbearerkokenKey());

			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			StringBuffer result = new StringBuffer();
			while ((output = in.readLine()) != null) {
				result.append(output);
			}
			in.close();

			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObjProject = (JSONObject) jsonParser.parse(result.toString());
			JSONArray jsonObjProjectItems = (JSONArray) jsonObjProject.get("items");

			String projectid = "";
			String projectname = "";
			String projectversion = "";

			for (int i = 0; i < jsonObjProjectItems.size(); i++) {
				JSONObject jsonObjProjectName = (JSONObject) jsonObjProjectItems.get(i);
				JSONObject jsonObjProjectId = (JSONObject) jsonObjProjectName.get("_meta");

				projectname = jsonObjProjectName.get("name").toString();
				projectid = (String) jsonObjProjectId.get("href");
				projectValues.setBDprojectName(projectname);
				projectValues.setBDprojectId(projectid);

				JSONArray jsonObjProjectIdUrl = (JSONArray) jsonObjProjectId.get("links");
				for (int j = 0; j < jsonObjProjectIdUrl.size(); j++) {
					JSONObject jsonObj6 = (JSONObject) jsonObjProjectIdUrl.get(j);
					if (jsonObj6.get("rel").equals("versions")) {
						projectversion = (String) jsonObj6.get("href");

					}
				}
//				System.out.println("*************************************************");
//				System.out.println("ProjectList_NAME : " + projectname);
//				System.out.println("*************************************************");
//				System.out.println("ProjectList_ID : " + projectid);
//				System.out.println("*************************************************");
//				System.out.println("ProjectVersionID_URL : " + projectversion);

			}

		} catch (Exception e) {
			System.out.println("Exception : getProjectId() class");
			System.out.println("Error : projectVersionID");
			e.printStackTrace();
			System.exit(1);
		}
	}

	public void getProjectVersionList() {
		String address = projectValues.getBDprojectId() + "/versions";
		String output = null;
		try {
			URL url = new URL(address);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/vnd.blackducksoftware.project-detail-4+json");
			conn.setRequestProperty("Content-Type", "application/vnd.blackducksoftware.project-detail-4+json");
			conn.setRequestProperty("Authorization", "Bearer " + projectValues.getBDbearerkokenKey());

			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			StringBuffer result = new StringBuffer();
			while ((output = in.readLine()) != null) {
				result.append(output);
			}
			in.close();

			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObjprojectValues = (JSONObject) jsonParser.parse(result.toString());
			JSONArray jsonObjprojectValuesItems = (JSONArray) jsonObjprojectValues.get("items");

			String projectversionid = "";
			String projectversionname = "";

			for (int i = 0; i < jsonObjprojectValuesItems.size(); i++) {
				JSONObject jsonObjprojectValuesName = (JSONObject) jsonObjprojectValuesItems.get(i);
				JSONObject jsonObjprojectValuesNameId = (JSONObject) jsonObjprojectValuesName.get("_meta");

				projectversionname = jsonObjprojectValuesName.get("versionName").toString();
				if (projectversionname.equals(loginValues.getBDprojectVersion())) {
					projectversionid = (String) jsonObjprojectValuesNameId.get("href");
					projectValues.setBDprojectVersionName(projectversionname);
					projectValues.setBD_projectVersionId(projectversionid);
				}
			}

//			System.out.println("*************************************************");
//			System.out.println("ProjectVersionName : " + projectValues.getBD_projectVersionName());
//			System.out.println("*************************************************");
//			System.out.println("ProjectVersionID_URL : " + projectValues.getBD_projectVersionId());
//			System.out.println("*************************************************");
		} catch (Exception e) {
			System.out.println("Exception : getProjectVersionList() class");
			e.printStackTrace();
			System.exit(1);
		}
	}
}
