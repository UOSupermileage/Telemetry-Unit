package com.uoeracing.telemetrysystem;

import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class RunConfirmation extends AppCompatActivity {

    ImageButton cancel, confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_confirmation);

        //getActionBar().setTitle("Run Confirmation");
        cancel = (ImageButton) findViewById(R.id.cancelButton);
        confirm = (ImageButton) findViewById(R.id.confirmButton);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialogBox dialog = new AlertDialogBox();

                //dialog.show(getSupportFragmentManager(), "DiscardRun");
                startActivity(new Intent(RunConfirmation.this, MenuActivity.class));
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //MainActivity.addRun();
            }
        });

    }



}
