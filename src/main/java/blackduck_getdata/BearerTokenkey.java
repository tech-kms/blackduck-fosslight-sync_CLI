package blackduck_getdata;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import integration.values.LoginValues;
import integration.values.ProjectValues;

public class BearerTokenkey {
	ProjectValues projectValues = ProjectValues.getInstance();
	LoginValues loginValues = LoginValues.getInstance();

	public void getBearerTokenkey() {
		String address = loginValues.getBDserverUri() + "/api/tokens/authenticate";
		String output;
		try {
			URL url = new URL(address);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Authorization", loginValues.getBDtoken());

			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			int code = conn.getResponseCode();

			if (code != 200) {
				System.out.println("Exception : BearerToken REST API");
				System.exit(1);
			}
			StringBuffer result = new StringBuffer();
			while ((output = in.readLine()) != null) {
				result.append(output);
			}

			in.close();

			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObjbearerToken = (JSONObject) jsonParser.parse(result.toString());
			String bearerToken = (String) jsonObjbearerToken.get("bearerToken");
			projectValues.setBDbearerkokenKey(bearerToken);

			//System.out.println("bearerToken : " + projectValues.getBDbearerkokenKey());

		} catch (Exception e) {
			System.out.println("Exception : getBearerTokenkey() class");
			e.printStackTrace();
			System.exit(1);
		}
	}
}