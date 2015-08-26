package com.training.highschool.sportsapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by ovi on 26/08/15.
 */


/*
public class recordActivity extends FragmentActivity {


    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    public Marker whereAmI;

    Polyline lineRoute;
    PolylineOptions lineOptions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        setUpMapIfNeeded();


        LocationManager locationManager;
        String svcName= Context.LOCATION_SERVICE;
        locationManager = (LocationManager)getSystemService(svcName);

        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setSpeedRequired(false);
        criteria.setCostAllowed(true);
        String provider = locationManager.getBestProvider(criteria, true);

        Location l = locationManager.getLastKnownLocation(provider);

        LatLng latlng=fromLocationToLatLng(l);


        whereAmI=mMap.addMarker(new MarkerOptions().position(latlng).icon(BitmapDescriptorFactory.defaultMarker(
                BitmapDescriptorFactory.HUE_BLUE)));
        // Zoom in
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng,
                17));

        updateWithNewLocation(l);

    //get current location, set marker at that location and zoom in
        locationManager.requestLocationUpdates(provider, 2000, 10,
                locationListener);


    }


    private void initializeDraw() {
        lineOptions = new PolylineOptions().width(5).color(Color.RED);
        lineRoute = mMap.addPolyline(lineOptions);
    }


    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
        displayCurrentTime();
        displayStartTime();
    }

    public static LatLng fromLocationToLatLng(Location location){
        return new LatLng(location.getLatitude(), location.getLongitude());

    }

    private void updateWithNewLocation(Location location) {
        TextView myLocationText;
        myLocationText = (TextView)findViewById(R.id.myLocationText);

        String latLongString = "No location found";
        String addressString = "No address found";

        if (location != null) {
            // Update the map location.

            LatLng latlng=fromLocationToLatLng(location);

            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng,
                    17));


            if(whereAmI!=null)
                whereAmI.remove();

            whereAmI=mMap.addMarker(new MarkerOptions().position(latlng).icon(BitmapDescriptorFactory.defaultMarker(
                    BitmapDescriptorFactory.HUE_BLUE)).title("Here I Am."));

            double lat = location.getLatitude();
            double lng = location.getLongitude();
            latLongString = "Lat:" + lat + "\nLong:" + lng;

            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            Geocoder gc = new Geocoder(this, Locale.getDefault());

            if (!Geocoder.isPresent())
                addressString = "No geocoder available";
            else {
                try {
                    List<Address> addresses = gc.getFromLocation(latitude, longitude, 1);
                    StringBuilder sb = new StringBuilder();
                    if (addresses.size() > 0) {
                        Address address = addresses.get(0);

                        for (int i = 0; i < address.getMaxAddressLineIndex(); i++)
                            sb.append(address.getAddressLine(i)).append("\n");

                        sb.append(address.getLocality()).append("\n");
                        sb.append(address.getPostalCode()).append("\n");
                        sb.append(address.getCountryName());
                    }
                    addressString = sb.toString();
                } catch (IOException e) {
                    Log.d("WHEREAMI", "IO Exception", e);
                }
            }
        }

        myLocationText.setText("Your Current Position is:\n" +
                latLongString + "\n\n" + addressString);
    }



    private final LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            initializeDraw();
            LatLng newPoint = new LatLng(location.getLatitude(), location.getLongitude());
            List<LatLng> points = lineRoute.getPoints();
            points.add(newPoint);
            lineRoute.setPoints(points);

            updateWithNewLocation(location);
        }

        public void onProviderDisabled(String provider) {}
        public void onProviderEnabled(String provider) {}
        public void onStatusChanged(String provider, int status,
                                    Bundle extras) {}
    };


    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
                // Configure the map display options

            }
        }
    }

    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    //set map type as normal
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }

    public void onCancelClicked(View v) {

        Toast.makeText(getApplicationContext(),
                getString(R.string.cancel_message), Toast.LENGTH_SHORT).show();

        Intent mIntent = new Intent(recordActivity.this,
                Home.class);
        startActivity(mIntent);
    }

*/