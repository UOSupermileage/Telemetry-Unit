package com.uoeracing.telemetrysystem;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;


public class AlertDialogBox extends DialogFragment {


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the Builder class for convenient dialog construction
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getActivity());
        builder.setTitle("Discard Run?");
        builder.setMessage("Are you sure you want to delete run data?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // You don't have to do anything here if you just
                // want it dismissed when clicked
                //startActivity(new Intent(RunConfirmation.class, MenuActivity.class));
            }
        });

        // Create the AlertDialog object and return it
        return builder.create();
    }
}
