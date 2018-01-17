package com.uoeracing.telemetrysystem;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ResultsActivity extends AppCompatActivity {

    ListView listOfRuns;
    public static DatabaseReference runsDatabase;
    List<RunData> runs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        listOfRuns = (ListView) findViewById(R.id.resultsList);
        runsDatabase = FirebaseDatabase.getInstance().getReference("runs");
        runs = new ArrayList<>();



    }

    public void setRunsDatabase(DatabaseReference runsDatabase) {
        this.runsDatabase = runsDatabase;
    }

    public DatabaseReference getRunsDatabase() {
        return runsDatabase;
    }

    private void addRun() {

    }

}
