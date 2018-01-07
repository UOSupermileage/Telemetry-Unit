package com.uoeracing.telemetrysystem;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LocationListener {

    TextView speed, timer, latitude, longitude, lapText;
    int lap = 0;
    Button lapButton;


    private boolean readyToCancel = false;
    private int cancelTimeout = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //lap.setBackgroundColor(Color.rgb(49,203,0));
        lapText = (TextView)findViewById(R.id.lapText);
        lapButton = (Button)findViewById(R.id.lapButton);
        lapButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                lapText.setText("Lap: " + lap++);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(readyToCancel){
                    startActivity(new Intent(MainActivity.this, MenuActivity.class));
                }else{
                    Snackbar.make(view, "Click again to end run.", Snackbar.LENGTH_SHORT).setAction("End Run", null).show();
                    readyToCancel = true;
                }
            }
        });

        LocationManager lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            return;
        }
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

        startTimer();
        this.onLocationChanged(null);
    }

    private int seconds = 0;
    private boolean running;
    private boolean wasRunning;

    public void startTimer(){
        timer = (TextView) findViewById(R.id.timerText);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                running = true;
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                String time = String.format("%d:%02d:%02d", hours, minutes, secs);
                timer.setText(time);
                seconds++;

                if(readyToCancel){
                    cancelTimeout ++;
                }
                if(cancelTimeout>2){
                    readyToCancel = false;
                    cancelTimeout = 0;
                }

                handler.postDelayed(this,1000);
            }
        });
    }


    @Override
    public void onLocationChanged(Location location) {
        speed = (TextView)this.findViewById(R.id.speed);
        longitude = (TextView)this.findViewById(R.id.longitude);
        latitude = (TextView)this.findViewById(R.id.latitude);

        if(location == null) {
            speed.setText("0 m/s");
            longitude.setText("Longitude: Out of Service");
            latitude.setText("Latitude: Out of Service");
        }
        else {
            speed.setText(location.getSpeed() + " m/s");
            longitude.setText("Longitude: " + location.getLongitude());
            latitude.setText("Latitude: " + location.getLatitude());
        }
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
}
