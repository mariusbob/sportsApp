package com.training.highschool.sportsapp;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by ovi on 04/09/15.
 */


class MyLocationListener implements LocationListener {


    public void onLocationChanged(Location location) {



        //Toast.makeText(AndroidGPSSampleActivity.this, message, Toast.LENGTH_LONG).show();
    }

    public void onStatusChanged(String s, int i, Bundle b) {

        //Toast.makeText(AndroidGPSSampleActivity.this, "Provider status changed",Toast.LENGTH_LONG).show();
    }

    public void onProviderDisabled(String s) {

        //Toast.makeText(AndroidGPSSampleActivity.this,"Provider disabled by the user. GPS turned off",Toast.LENGTH_LONG).show();
    }

    public void onProviderEnabled(String s) {
        System.out.println("==onProviderEnabled=" + s);
        //Toast.makeText(AndroidGPSSampleActivity.this, "Provider enabled by the user. GPS turned on",Toast.LENGTH_LONG).show();
    }


}