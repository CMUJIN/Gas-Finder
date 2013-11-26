package com.jinhs.gasfinder;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.google.android.glass.app.Card;
import com.google.gson.JsonSyntaxException;
import com.jinhs.gasfinder.rest.client.PlaceApiClient;
import com.jinhs.gasfinder.rest.data.GeoLocation;
import com.jinhs.gasfinder.rest.data.PlaceResponse;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Location location = getUserLocation();
		
		boolean apiNotAvailable = false;
		GeoLocation stationLocation = null;
		if (location != null) {
			try {
				stationLocation = new PlaceApiAsyncTask().execute(location)
						.get();
			} catch (InterruptedException e) {
				apiNotAvailable = true;
				Log.e("InterruptedException", e.getMessage());
			} catch (ExecutionException e) {
				apiNotAvailable = true;
				Log.e("ExecutionException", e.getMessage());
			}
		}
		if (stationLocation == null || stationLocation.getLat() == 0
				|| stationLocation.getLng() == 0)
			apiNotAvailable = true;

		if (!apiNotAvailable) 
			callNavigator(stationLocation);
		else 
			setCardView(location);

	}

	private void setCardView(Location location) {
		Card card1 = new Card(this);
		card1.setText("this is the nearby gas station. latitude:"
				+ location.getLatitude() + " longtitude:"
				+ location.getLongitude());
		card1.setInfo("I'm the footer!");
		View card1View = card1.toView();
		setContentView(card1View);
	}

	private void callNavigator(GeoLocation stationLocation) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setComponent(new ComponentName("com.google.glass.maps",
				"com.google.glass.maps.NavigationActivity"));
		intent.setData(Uri.parse("google.navigation:q="
				+ stationLocation.getLat() + "," + stationLocation.getLng()));
		startActivity(intent);
	}

	private Location getUserLocation() {
		Location location;
		// initialized elsewhere
		LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		// In this example we ask for fine accuracy and require altitude, but
		// these criteria could be whatever you want.
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		criteria.setAltitudeRequired(true);

		String provider = locationManager.getBestProvider(criteria, true);
		Log.d("provider", provider);
		locationManager.requestSingleUpdate(provider,
				new LocationUpdateListener(), null);
		location = locationManager.getLastKnownLocation(provider);
		return location;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private class PlaceApiAsyncTask extends
			AsyncTask<Location, Integer, GeoLocation> {
		@Override
		protected GeoLocation doInBackground(Location... locations) {

			if (locations == null || locations.length == 0)
				return null;
			Location location = locations[0];
			PlaceResponse response = null;
			try {
				response = PlaceApiClient.getNearByGasStation(
						location.getLatitude(), location.getLongitude());
			} catch (JsonSyntaxException e) {
				Log.e("JsonSyntaxException", e.getMessage());
			} catch (IOException e) {
				Log.e("IOException", e.getMessage());
			}
			if (response == null || response.getResults() == null
					|| response.getResults().size() == 0
					|| response.getResults().get(0).getGeometry() == null)
				return null;
			return response.getResults().get(0).getGeometry().getLocation();
		}
	}

	class LocationUpdateListener implements LocationListener {

		@Override
		public void onLocationChanged(Location arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderDisabled(String arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderEnabled(String arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
			// TODO Auto-generated method stub

		}
	}
}
