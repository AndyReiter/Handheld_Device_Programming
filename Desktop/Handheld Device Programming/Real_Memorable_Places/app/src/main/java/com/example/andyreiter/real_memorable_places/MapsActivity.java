package com.example.andyreiter.real_memorable_places;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements
        OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private GoogleMap mMap;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private Location lastLocation;
    private Marker currentUserLocationMarker;
    private double latitide, longitude;
    private static final int Request_User_Location_Code = 99;
    double latPassedIn;
    double longPassedIn;
    boolean passedInCoords = false;
    String location = "";
    ArrayList<String> listOfAddresses = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkUserLocationPermission();
        }
        Intent intent = getIntent();
        if (intent.hasExtra("Lat")) {
            latPassedIn = intent.getExtras().getDouble("Lat");
            longPassedIn = intent.getExtras().getDouble("Long");
        }
        if (latPassedIn != 0.0 && longPassedIn != 0.0){
            passedInCoords = true;
        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    } // END OF ON CREATE METHOD

    public boolean checkUserLocationPermission()
    {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_User_Location_Code);
            }
            else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_User_Location_Code);
            }
            return false;
        }
        else {
            return true;
        }
    } // END OF CHECK USER LOCATION PERMISSION

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (passedInCoords){
            System.out.println("HERE ANDY HEREEEEEE ----> " + latPassedIn + " ------- " + longPassedIn);
            latitide = latPassedIn;
            longitude = longPassedIn;
            LatLng userLatLng = new LatLng(latitide, longitude);

            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(userLatLng);
            markerOptions.title("User Selected Location");
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

            currentUserLocationMarker = mMap.addMarker(markerOptions);

            mMap.addMarker(markerOptions.position(userLatLng));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(userLatLng));
            mMap.animateCamera(CameraUpdateFactory.zoomBy(12));
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && passedInCoords != true) {
            buildGoogleApiClient();

            mMap.setMyLocationEnabled(true);
        }


        onMapClick(mMap);
        // calls the functions from here

    } // END OF ON MAP READY METHOD


    protected synchronized void buildGoogleApiClient() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        googleApiClient.connect();
    } // END OF BUILD GOOGLE API CLIENT MATHOD


    public void onMapClick(final GoogleMap mMap){
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                String theLocation = String.format(Locale.getDefault(), "Lat: %2$.5f", latLng.latitude, latLng.longitude);
                mMap.clear();
                latitide = latLng.latitude;
                longitude = latLng.longitude;
                Marker marker = mMap.addMarker(new MarkerOptions().position(latLng).title(theLocation).snippet(theLocation));
                location = theLocation;

            }
        });
    } // END OF ON MAP CLICK METHOD!

    @Override
    public void onLocationChanged(Location location) {
        if (passedInCoords){
            latitide = latPassedIn;
            longitude = longPassedIn;
            LatLng newLatLng = new LatLng(latitide, longitude);

            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(newLatLng);
            markerOptions.title("user Current Location");
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));

            currentUserLocationMarker = mMap.addMarker(markerOptions);

            mMap.moveCamera(CameraUpdateFactory.newLatLng(newLatLng));
            mMap.animateCamera(CameraUpdateFactory.zoomBy(12));
        }
        else {
            latitide = location.getLatitude();
            longitude = location.getLongitude();

            lastLocation = location;

            if (currentUserLocationMarker != null) {
                currentUserLocationMarker.remove();
            }
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.title("user Current Location");
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));

            currentUserLocationMarker = mMap.addMarker(markerOptions);

            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.animateCamera(CameraUpdateFactory.zoomBy(12));

            if (googleApiClient != null) {
                LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
            }
        }

    } // END OF ON LOCATION CHANGED METHOD!

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(1100);
        locationRequest.setFastestInterval(1100);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
        }
    } // END OF ON CONNECTED METHOD!

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onBackPressed() {
        boolean fromNewActivity=true;

        Intent mainIntent = new Intent(this, MainActivity.class);
        Bundle bundleObj = new Bundle();
        bundleObj.putString("fromNewActivity", Boolean.toString(fromNewActivity));
        System.out.println("LOCATION ----------> " + location);
        bundleObj.putString("address", location);
        bundleObj.putDouble("lat", latitide);
        bundleObj.putDouble("long", longitude);
        mainIntent.putExtras(bundleObj);
        setResult(RESULT_OK, mainIntent);
        finish();
    } // END OF ON BACK PRESSED METHOD!
}
