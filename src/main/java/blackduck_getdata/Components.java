package blackduck_getdata;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import integration.values.IdentifiedFilesValues;
import integration.values.ProjectValues;

public class Components {
	ProjectValues projectValues = ProjectValues.getInstance();
	IdentifiedFilesValues identifiedFilesValues = IdentifiedFilesValues.getInstance();

	JSONObject jsonObjComponentsList = new JSONObject();
	JSONArray jsonObjComponentsListItems = new JSONArray();
	long Listsize = 0;
	long Listsizesey = 0;
	long offset = 0;

	public void getComponentsstart() {

		getComponentsListsize();

		Listsizesey = Listsize / 100;
		if (Listsize < 100) {
			System.out.println("-----------  "+ Listsizesey +"  ------------");
			getComponentsList();
			System.out.println("Processing : [1/5]");
			getComponentsdata();
			System.out.println("Processing : [2/5]");
			getHomepage();
			System.out.println("Processing : [3/5]");
			getorigins();
			System.out.println("Processing : [4/5]");
			getcomments();
			System.out.println("Processing : [5/5]");
		} else {
			for (long e = 0; e < Listsizesey + 1; e++) {
				System.out.println("++++++++++  "+offset+"  ++++++++");
				getComponentsList();
				System.out.println("Processing : [1/5]");
				getComponentsdata();
				System.out.println("Processing : [2/5]");
				getHomepage();
				System.out.println("Processing : [3/5]");
				getorigins();
				System.out.println("Processing : [4/5]");
				getcomments();
				System.out.println("Processing : [5/5]");
				offset = offset + 100;
			}

		}
	}

	public void getComponentsListsize() {

		String address = projectValues.getBD_projectVersionId() + "/components";
		String output = null;
		try {
			URL url = new URL(address);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Authorization", "Bearer " + projectValues.getBDbearerkokenKey());

			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			StringBuffer result = new StringBuffer();
			while ((output = in.readLine()) != null) {
				result.append(output);
			}
			in.close();

			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObjListsize = (JSONObject) jsonParser.parse(result.toString());
			Listsize = (long) jsonObjListsize.get("totalCount");
			System.out.println("Components Listsize : " + Listsize);

		} catch (Exception e) {
			System.out.println("Exception : getComponentsListsize() class");
			e.printStackTrace();
			System.exit(1);
		}

	}

	public void getComponentsList() {
		String address = "";
		String output = null;

		address = projectValues.getBD_projectVersionId() + "/components?limit=100&offset=" + offset;

		try {
			URL url = new URL(address);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Authorization", "Bearer " + projectValues.getBDbearerkokenKey());

			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			StringBuffer result = new StringBuffer();
			while ((output = in.readLine()) != null) {
				result.append(output);
			}
			in.close();

			JSONParser jsonParser = new JSONParser();
			jsonObjComponentsList = (JSONObject) jsonParser.parse(result.toString());
			jsonObjComponentsListItems = (JSONArray) jsonObjComponentsList.get("items");

		} catch (Exception e) {
			System.out.println("Exception : getComponentsList() class");
			e.printStackTrace();
			System.exit(1);
		}
	}

	public void getComponentsdata() {
		// ** START)Source Name or Path, componentName , componentVersionName **
		for (int i = 0; i < jsonObjComponentsListItems.size(); i++) {
			JSONObject jsonObjComponentName = (JSONObject) jsonObjComponentsListItems.get(i);
			
			String componentName = jsonObjComponentName.get("componentName").toString().replace(",", "_");
			identifiedFilesValues.setcomponenetName(componentName);
			
			if(jsonObjComponentName.containsKey("componentVersionName")) {
				identifiedFilesValues.setcomponentVersion(jsonObjComponentName.get("componentVersionName").toString());
			}else {
				identifiedFilesValues.setcomponentVersion("");
			}
			String str = jsonObjComponentName.get("matchTypes").toString().replaceAll("\"", "");
			str = str.replaceAll("\\[|\\]", "");

			if (str.equals("FILE_DEPENDENCY_TRANSITIVE")) {
				identifiedFilesValues.setfilepath("Transitive Dependency");
			} else if (str.equals("FILE_DEPENDENCY_DIRECT")) {
				identifiedFilesValues.setfilepath("Direct Dependency");
			} else if (str.equals("FILE_DEPENDENCY_DIRECT,FILE_DEPENDENCY_TRANSITIVE")) {
				identifiedFilesValues.setfilepath("Direct Dependency, Transitive Dependency");
			}
			else if (str.contains("FILE_EXACT") || str.contains("FILE_SOME_FILES_MODIFIED")) {
				JSONObject jsonObjComponentMeta = (JSONObject) jsonObjComponentName.get("_meta");
				JSONArray jsonObjComponentMetaLinks = (JSONArray) jsonObjComponentMeta.get("links");
				for (int k = 0; k < jsonObjComponentMetaLinks.size(); k++) {
					JSONObject jsonObjComponentFiles = (JSONObject) jsonObjComponentMetaLinks.get(k);
					if (jsonObjComponentFiles.get("rel").equals("matched-files")) {
						String originslinks = (String) jsonObjComponentFiles.get("href");
						String address2 = originslinks;
						String output2 = null;
						try {
							URL url2 = new URL(address2);
							HttpURLConnection conn2 = (HttpURLConnection) url2.openConnection();
							conn2.setRequestMethod("GET");
							conn2.setRequestProperty("Accept", "application/json");
							conn2.setRequestProperty("Authorization", "Bearer " + projectValues.getBDbearerkokenKey());

							BufferedReader in2 = new BufferedReader(new InputStreamReader(conn2.getInputStream()));

							StringBuffer result2 = new StringBuffer();
							while ((output2 = in2.readLine()) != null) {
								result2.append(output2);
							}
							in2.close();
							
							JSONParser jsonParser = new JSONParser();
							JSONObject jsonComponentsdata = (JSONObject) jsonParser.parse(result2.toString());
							JSONArray jsonComponentsdataId = (JSONArray) jsonComponentsdata.get("items");
							ArrayList<String> filePath = new ArrayList<String>();
							String path = "";
							for (int b = 0; b < jsonComponentsdataId.size(); b++) {
								JSONObject jsonoriginsFile = (JSONObject) jsonComponentsdataId.get(b);
								JSONObject jsonoriginsFilePath = (JSONObject) jsonoriginsFile.get("filePath");
								path = jsonoriginsFilePath.get("path").toString();
								filePath.add(path);
							}
							identifiedFilesValues.setfilepath(filePath.toString().replaceAll("\\[|\\]", ""));
						} catch (Exception e) {
							e.printStackTrace();
							System.out.print("Exception : getProjectId()");
						}
					}
				}
			} else {
				identifiedFilesValues.setfilepath(str);
			}
			// ** END)componentName , componentVersionName **
			
			// ** START) setcomponentLicenseId **
			JSONArray jsonObjComponentLicenses = (JSONArray) jsonObjComponentName.get("licenses");
			for (int j = 0; j < jsonObjComponentLicenses.size(); j++) {
				JSONObject jsonObjComponentLicenseId = (JSONObject) jsonObjComponentLicenses.get(j);

				String licenseDisplay = jsonObjComponentLicenseId.get("licenseDisplay").toString().replaceAll("\\(|\\)|\\,", "");
				licenseDisplay = licenseDisplay.replace(" OR ", ",");
				licenseDisplay = licenseDisplay.replace(" AND ", ",");
				identifiedFilesValues.setcomponentLicenseId(licenseDisplay);
			}
			// ** END)componentLicenseId**
		}
	}

	public void getHomepage() {
		for (int i = 0; i < jsonObjComponentsListItems.size(); i++) {
			JSONObject jsonObjlinks = (JSONObject) jsonObjComponentsListItems.get(i);
			// ** START)componentDownload, componentHompage**

			// identifiedFilesValues.setcomponentDownload("");

			JSONObject jsonObjComponent = (JSONObject) jsonObjlinks.get("_meta");
			JSONArray jsonObjComponentHompageLinks = (JSONArray) jsonObjComponent.get("links");
			JSONObject jsonObjComponentHompage = null;
			String componentHompage = "";
			for (int n = 0; n < jsonObjComponentHompageLinks.size(); n++) {
				jsonObjComponentHompage = (JSONObject) jsonObjComponentHompageLinks.get(n);

				if (jsonObjComponentHompage.get("rel").equals("component-home")) {
					componentHompage = jsonObjComponentHompage.get("href").toString();
				}
			}
			identifiedFilesValues.setcomponentHompage(componentHompage);
		}
	}

	public void getorigins() {
		// ** START)Copyright( origins URL )**
		for (int i = 0; i < jsonObjComponentsListItems.size(); i++) {
			JSONObject jsonObjOrigins = (JSONObject) jsonObjComponentsListItems.get(i);
			JSONArray jsonObjOriginsId = (JSONArray) jsonObjOrigins.get("origins");
			JSONObject jsonObjOriginsdata = null;
			Object chkMeta =  null;
			for (int k = 0; k < jsonObjOriginsId.size(); k++) {
				jsonObjOriginsdata = (JSONObject) jsonObjOriginsId.get(k);
			}
			if (jsonObjOriginsdata != null ) {
				chkMeta = jsonObjOriginsdata.get("_meta");
			}
			if (chkMeta != null ) {
				JSONObject jsonObjOriginsMeta = (JSONObject) jsonObjOriginsdata.get("_meta");
				JSONArray jsonObjOriginsLinks = (JSONArray) jsonObjOriginsMeta.get("links");
				
				for (int q = 0; q < jsonObjOriginsLinks.size(); q++) {
					JSONObject jsonObjOriginsLinksUrl = (JSONObject) jsonObjOriginsLinks.get(q);

					if (jsonObjOriginsLinksUrl.get("rel").equals("component-origin-copyrights")) {
						String originslinks = (String) jsonObjOriginsLinksUrl.get("href");
						String address2 = originslinks;
						String output2 = null;
						try {
							URL url2 = new URL(address2);
							HttpURLConnection conn2 = (HttpURLConnection) url2.openConnection();
							conn2.setRequestMethod("GET");
							conn2.setRequestProperty("Accept", "application/json");
							conn2.setRequestProperty("Authorization", "Bearer " + projectValues.getBDbearerkokenKey());

							BufferedReader in2 = new BufferedReader(new InputStreamReader(conn2.getInputStream()));

							StringBuffer result2 = new StringBuffer();
							while ((output2 = in2.readLine()) != null) {
								result2.append(output2);
							}
							in2.close();

							JSONParser jsonParser = new JSONParser();
							JSONObject jsonOrigins = (JSONObject) jsonParser.parse(result2.toString());
							JSONArray jsonOriginsId = (JSONArray) jsonOrigins.get("items");

							JSONObject jsonorigins3 = null;
							ArrayList<String> copyright = new ArrayList<String>();

							for (int b = 0; b < jsonOriginsId.size(); b++) {
								jsonorigins3 = (JSONObject) jsonOriginsId.get(b);
								if (jsonorigins3.get("active").toString().equals("true")) {
									String active = jsonorigins3.get("updatedCopyright").toString();
									active = active.replace("�", "");
									copyright.add(active);
								}
							}
							identifiedFilesValues.setCopyright(copyright.toString().replaceAll("\\[|\\]", ""));
						} catch (Exception e) {
							e.printStackTrace();
							System.out.print("Exception : getProjectId() class");
						}
					}

				}
			}else {
				identifiedFilesValues.setCopyright("");
			}
		}
	
		// ** END)Copyright( origins URL )**
	}
		
	public void getcomments() {
		for (int i = 0; i < jsonObjComponentsListItems.size(); i++) {
			JSONObject jsonObjComment = (JSONObject) jsonObjComponentsListItems.get(i);

			JSONObject jsonObjCommentMeta = (JSONObject) jsonObjComment.get("_meta");
			JSONArray jsonObjCommentLinks = (JSONArray) jsonObjCommentMeta.get("links");
			JSONObject jsonlinks3 = null;
			String commentsurl = "";
			for (int n = 0; n < jsonObjCommentLinks.size(); n++) {
				jsonlinks3 = (JSONObject) jsonObjCommentLinks.get(n);
				
				if (jsonlinks3.get("rel").equals("comments")) {
					commentsurl = jsonlinks3.get("href").toString();
					
					String address2 = commentsurl;
					String output2 = null;
					try {
						URL url2 = new URL(address2);
						HttpURLConnection conn2 = (HttpURLConnection) url2.openConnection();
						conn2.setRequestMethod("GET");
						conn2.setRequestProperty("Accept", "application/json");
						conn2.setRequestProperty("Authorization", "Bearer " + projectValues.getBDbearerkokenKey());

						BufferedReader in2 = new BufferedReader(new InputStreamReader(conn2.getInputStream()));

						StringBuffer result2 = new StringBuffer();
						while ((output2 = in2.readLine()) != null) {
							result2.append(output2);
						}
						in2.close();

						JSONParser jsonParser = new JSONParser();
						JSONObject jsonComments = (JSONObject) jsonParser.parse(result2.toString());
						JSONArray jsonCommentsId = (JSONArray) jsonComments.get("items");

						JSONObject jsonorigins3 = null;
						ArrayList<String> comment = new ArrayList<String>();

						for (int b = 0; b < jsonCommentsId.size(); b++) {
							jsonorigins3 = (JSONObject) jsonCommentsId.get(b);
							comment.add(jsonorigins3.get("comment").toString());
						}

						 identifiedFilesValues.setcommnet(comment.toString().replaceAll("\\[|\\]", ""));

					} catch (Exception e) {
						e.printStackTrace();
						System.out.print("Exception : getProjectId() class");
					}
					
				}
				else if (jsonlinks3.get("rel").equals("")) {
					identifiedFilesValues.setcommnet("");
				}
			}

		}

	}


}
