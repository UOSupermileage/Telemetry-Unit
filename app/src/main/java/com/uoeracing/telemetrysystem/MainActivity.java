package com.uoeracing.telemetrysystem;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements LocationListener, OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    GoogleMap mGoogleMap;
    MapFragment mapFragment;
    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;


    static RunData run;

    TextView speed, latitude, longitude, altitude, angle;
    TextView lapText;
    int lap = 0;
    Button lapButton;

    Chronometer timer;

    private boolean readyToCancel = false;
    private int cancelTimeout = 0;

    static int runNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Map View init
        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.fragment_map);
        mapFragment.getMapAsync(this);


        // Starting the timer
        timer = (Chronometer) findViewById(R.id.timer);
        timer.start();

        // Increasing the Lap Time
        lapText = (TextView)findViewById(R.id.lapText);
        lapButton = (Button)findViewById(R.id.lapButton);
        angle = (TextView) findViewById(R.id.bearing);
        lapButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                lapText.setText("Lap: " + lap++);
            }
        });

        // Stopping the current lap
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(readyToCancel){
                    startActivity(new Intent(MainActivity.this, RunConfirmation.class));

                }
                else{
                    Snackbar.make(view, "Click again to end run.", Snackbar.LENGTH_SHORT).setAction("End Run", null).show();
                    readyToCancel = true;
                }
            }
        });

        // Asking for USER PERMISSIONS
        LocationManager lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION}, 1);

        }
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);


    }

    @Override
    public void onLocationChanged(Location location) {
        speed = (TextView)this.findViewById(R.id.speed);
        longitude = (TextView)this.findViewById(R.id.longitude);
        latitude = (TextView)this.findViewById(R.id.latitude);
        altitude = (TextView)this.findViewById(R.id.altitude);

        if(location == null) {
            speed.setText("Speed: 0 m/s");
            longitude.setText("Longitude: Out of Service");
            latitude.setText("Latitude: Out of Service");
            altitude.setText("Altitude: Out of Service");
            angle.setText("Bearing: Out of Service");
        }
        else {
            speed.setText(location.getSpeed() + " m/s");
            longitude.setText("Longitude: " + location.getLongitude());
            latitude.setText("Latitude: " + location.getLatitude());
            altitude.setText("Altitude: " + location.getAltitude());
            angle.setText("Bearing: " + location.getBearing());
        }

        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,11));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }





    public static void addRun() {
        String id = ResultsActivity.runsDatabase.push().getKey();
        runNumber++;
        SimpleDateFormat formattedDate = new SimpleDateFormat("MMM DD, YYYY");

        //run.setRunName("Run " + runNumber);
        //run.setStartDate(formattedDate.format(Calendar.getInstance().getTime()));

        ResultsActivity.runsDatabase.child(id).setValue(run);

        //Toast.makeText(this, "Run Recorded", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        buildGoogleApiClient();
        mGoogleMap.setMyLocationEnabled(true);
        mGoogleApiClient.connect();
    }

    protected void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
