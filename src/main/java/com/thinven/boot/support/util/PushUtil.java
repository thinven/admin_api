package com.thinven.boot.support.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.thinven.boot.support.log.Log;

public abstract class PushUtil {

	public static String send(Map<String, String> params, Map<String, String> data) {
		StringBuilder responseStringBuilder = new StringBuilder();
		try {
			URL url = new URL("https://fcm.googleapis.com/fcm/send");
			trustAllHosts();
			HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
			httpsURLConnection.setHostnameVerifier(new HostnameVerifier() {
				@Override
				public boolean verify(String s, SSLSession sslSession) {
					return true;
				}
			});
			HttpURLConnection connection = httpsURLConnection;

			String p = "{\"to\": \"" + params.get("token") + "\", " + "\"notification\" : {\"title\" : \"" + params.get("title") + "\", \"body\" : \""
					+ params.get("body") + "\", " + "\"sound\" : \"push_sound\"}," + "\"data\" : {" + getMakeData(data) + "}" + "}";
			Log.info(PushUtil.class, "parameter : " + p);

			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Authorization",
					"key=AAAAs2RO8HY:APA91bGDlweXdVopB7RHPpBvZJDvM4Hd8aV4Cx8t-93gEyQi6MGhev0wzmCgdP0bcKAFgEi_vK6Rj5QlXLGSMgL4meLipBavc60toUs0urvqaiMhHu3ZkC_u_dLt6MSBghSgjTBpGbHS");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Content-Length", "" + Integer.toString(p.getBytes().length));

			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			wr.write(p.getBytes("UTF-8"));
			wr.flush();
			wr.close();

			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				for (;;) {
					String stringLine = bufferedReader.readLine();
					if (stringLine == null)
						break;
					responseStringBuilder.append(stringLine + '\n');
				}
				bufferedReader.close();
			} else {
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
				for (;;) {
					String stringLine = bufferedReader.readLine();
					if (stringLine == null)
						break;
					responseStringBuilder.append(stringLine + '\n');
				}
				bufferedReader.close();
			}
			connection.disconnect();
			Log.info(PushUtil.class, responseStringBuilder.toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return responseStringBuilder.toString();
	}

	private static void trustAllHosts() {
		// Create a trust manager that does not validate certificate chains
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return new java.security.cert.X509Certificate[] {};
			}

			@Override
			public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws java.security.cert.CertificateException {
				// TODO Auto-generated method stub

			}

			@Override
			public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws java.security.cert.CertificateException {
				// TODO Auto-generated method stub

			}
		} };

		// Install the all-trusting trust manager
		try {
			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getMakeData(Map<String, String> data) {
		StringBuilder stringBuilder = new StringBuilder();
		boolean first = true;

		for (String key : data.keySet()) {
			if (first)
				first = false;
			else
				stringBuilder.append(",");

			stringBuilder.append("\"" + key + "\" : \"" + data.get(key) + "\"");
		}

		return stringBuilder.toString();
	}

}
