package blackduck_getdata;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class SSLTrustAllCerts {
	
	public void sslTrustAllCerts(){ 
		TrustManager[] trustAllCerts = new TrustManager[] { 
			new X509TrustManager() { 
				public X509Certificate[] getAcceptedIssuers() { 
					return null; 
				} 
				public void checkClientTrusted(X509Certificate[] certs, String authType) { } 
				public void checkServerTrusted(X509Certificate[] certs, String authType) { }
			} 
		}; 

		SSLContext sc;

		try { 
			sc = SSLContext.getInstance("SSL"); 
			sc.init(null, trustAllCerts, new SecureRandom()); 
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory()); 
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
