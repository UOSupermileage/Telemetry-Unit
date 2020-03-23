package com.uoeracing.telemetrysystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton start, results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        start = (ImageButton) findViewById(R.id.startButton);
        results = (ImageButton) findViewById(R.id.resultsButton);
        start.setOnClickListener(this);
        results.setOnClickListener(this);

    }

    public void onClick(View v) {

        switch(v.getId()){
            case R.id.startButton:
                startActivity(new Intent(MenuActivity.this, MainActivity.class));
                break;
            case R.id.resultsButton:
                startActivity(new Intent(MenuActivity.this, ResultsActivity.class));
                break;
        }
    }
}
