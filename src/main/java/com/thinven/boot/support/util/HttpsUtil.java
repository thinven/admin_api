package com.thinven.boot.support.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.thinven.boot.support.log.Log;

public abstract class HttpsUtil {

	public static String post(String urlString, Map<String, String> params) {
		StringBuilder responseStringBuilder = new StringBuilder();
		try {
			URL url = new URL(urlString);
			trustAllHosts();
			HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
			httpsURLConnection.setHostnameVerifier(new HostnameVerifier() {
				@Override
				public boolean verify(String s, SSLSession sslSession) {
					return true;
				}
			});
			HttpURLConnection connection = httpsURLConnection;

			String p = getURLQueryWithoutEncoding(params);
			Log.info(HttpsUtil.class, "parameter : " + p);

			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Accept-Charset", "UTF-8");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			connection.setRequestProperty("Content-Length", "" + Integer.toString(p.getBytes().length));

			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			wr.writeBytes(p);
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
			Log.info(HttpsUtil.class, responseStringBuilder.toString());
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

	public static String getURLQueryWithoutEncoding(Map<String, String> params) {
		StringBuilder stringBuilder = new StringBuilder();
		boolean first = true;

		for (String key : params.keySet()) {
			if (first)
				first = false;
			else
				stringBuilder.append("&");

			stringBuilder.append(key);
			stringBuilder.append("=");
			stringBuilder.append(params.get(key));
		}

		return stringBuilder.toString();
	}

	public static String getURLQuery(Map<String, String> params) {
		StringBuilder stringBuilder = new StringBuilder();
		boolean first = true;

		for (String key : params.keySet()) {
			if (first)
				first = false;
			else
				stringBuilder.append("&");

			try {
				stringBuilder.append(URLEncoder.encode(key, "UTF-8"));
				stringBuilder.append("=");
				stringBuilder.append(URLEncoder.encode(params.get(key), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

		return stringBuilder.toString();
	}

}
