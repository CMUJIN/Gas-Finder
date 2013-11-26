package com.jinhs.gasfinder.rest.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.jinhs.gasfinder.rest.data.PlaceResponse;

public class PlaceApiClient {
	private static final String endPoint = "https://maps.googleapis.com/maps/api/place/nearbysearch/json";
	private static final String parameters = "?sensor=true&rankby=distance&keyword=gas+station&key=AIzaSyCGFpJ2FHG8Jj1k92YvtWbx5i7x3kBqsSw";
	
	public static PlaceResponse getNearByGasStation(double latitude, double longtitude) throws JsonSyntaxException, IOException{
		URL url = generateUrl(latitude, longtitude);
		PlaceResponse response = new Gson().fromJson(getStringFromInputStream(url.openStream()), PlaceResponse.class);
		return response;
	}

	private static URL generateUrl(double latitude, double longtitude) throws MalformedURLException {
		return new URL(endPoint+parameters+"&location="+latitude+","+longtitude);
	}
	
	// convert InputStream to String
		private static String getStringFromInputStream(InputStream is) {

			BufferedReader br = null;
			StringBuilder sb = new StringBuilder();

			String line;
			try {
				br = new BufferedReader(new InputStreamReader(is));
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return sb.toString();
		}
}
