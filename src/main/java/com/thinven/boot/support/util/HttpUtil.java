package com.thinven.boot.support.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Map;

public abstract class HttpUtil {

	public static String get(String urlString, Map<String, String> params) {
		InputStream is = null;
		urlString = urlString + "?" + getURLQuery(params);
		try {
			URLConnection conn = new URL(urlString).openConnection();
			conn.setRequestProperty("User-Agent", "Mozilla/5.0");
			is = conn.getInputStream();

			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			return jsonText;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "";
	}

	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
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
