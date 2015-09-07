package com.training.highschool.sportsapp;

import android.content.Context;
import android.content.IntentSender;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.List;


/**
 * A fragment that launches other parts of the demo application.
 */
public class UserMapFragment extends Fragment implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener {

    private SupportMapFragment fragment;
    private GoogleMap map;
    private LocationRequest mLocationRequest;

    private GoogleApiClient mGoogleApiClient;
    public static final String TAG = UserMapFragment.class.getSimpleName();
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    public float viteza;
    private static View view;
    public String provider;

    @Override
    public void onConnected(Bundle connectionHint) {
        Log.i(TAG, "Location services connected.");
        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (location == null) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        } else {
            handleNewLocation(location);
        }
    }

    double currentLatitude, currentLongitude;

    private void handleNewLocation(Location location) {
        Log.d(TAG, location.toString());
        //pentru afisare
        currentLatitude = location.getLatitude();
        currentLongitude = location.getLongitude();
        LatLng latLng = new LatLng(currentLatitude, currentLongitude);

        /*

        MarkerOptions options = new MarkerOptions()
                .position(latLng)
                .title("Checkpoint");
        //map.addMarker(options);
        PolylineOptions linie = new PolylineOptions()
                .add(latLng);
        Polyline linie2 = map.addPolyline(linie);

        PolygonOptions forma = new PolygonOptions()
                .add(latLng);
        Polygon forma2 = map.addPolygon(forma);

        */

        map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        //map.animateCamera(CameraUpdateFactory.zoomTo(4));


        map.setMyLocationEnabled(true);

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(currentLatitude, currentLongitude), 18));

        GroundOverlayOptions overlay = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.drawable.punct))
                .position(latLng, 8f, 8f);
        map.addGroundOverlay(overlay);


   /*     PolylineOptions linie = new PolylineOptions().width(5).color(Color.RED);
        Polyline linie2 = map.addPolyline(linie);

       List<LatLng> points = linie2.getPoints();
        points.add(latLng);
        linie2.setPoints(points);

        List<LatLng> points = decodePoly(_path); // list of latlng
        for (int i = 0; i < points.size() - 1; i++) {
            LatLng src = points.get(i);
            LatLng dest = points.get(i + 1);

            // mMap is the Map Object
            Polyline line = map.addPolyline(
                    new PolylineOptions().add(
                            new LatLng(src.latitude, src.longitude),
                            new LatLng(dest.latitude,dest.longitude)
                    ).width(2).color(Color.BLUE).geodesic(true)
            );
        }
*/


    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "Location services suspended. Please reconnect.");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
        }
        try {
            view = inflater.inflate(R.layout.fragment_map, container, false);
        } catch (InflateException e) {

        }

        buildGoogleApiClient();

        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(1 * 1000)        // 10 seconds, in milliseconds
                .setFastestInterval(1 * 1000); // 1 second, in milliseconds

        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);




        return view;
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity().getApplication())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentManager fm = getChildFragmentManager();
        fragment = (SupportMapFragment) fm.findFragmentById(R.id.map);
        if (fragment == null) {
            fragment = SupportMapFragment.newInstance();
            fm.beginTransaction().replace(R.id.map, fragment).commit();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        mGoogleApiClient.connect();

        if (map == null) {
            LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 30, 30000, new MyLocationListener());

            map = fragment.getMap();


        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (connectionResult.hasResolution()) try {
            // Incepe o activitate ce incearca sa rezolve eroarea
            connectionResult.startResolutionForResult(getActivity(), CONNECTION_FAILURE_RESOLUTION_REQUEST);
        } catch (IntentSender.SendIntentException e) {
            e.printStackTrace();
        }
        else {
            Log.i(TAG, "Location services connection failed with code " + connectionResult.getErrorCode());
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            mGoogleApiClient.disconnect();
        }
    }

   /* LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,
                    (android.location.LocationListener) locationListener);
            handleNewLocation(location);
            float viteza = location.getSpeed();
            Log.i(TAG, "Viteza = " + String.valueOf(viteza));

        }
    };*/

    @Override
    public void onLocationChanged(Location location) {
        handleNewLocation(location);
        float viteza = location.getSpeed();
        Log.i(TAG, "Viteza = " + String.valueOf(viteza));
    }
}
