package com.uoeracing.telemetrysystem;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class TestsList extends ArrayAdapter<RunData> {
    private Activity context;
    List<RunData> tests;

    public TestsList(Activity context, List<RunData> tests) {
        super(context, R.layout.layout_tests, tests);
        this.context = context;
        this.tests = tests;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_tests, null, true);

        TextView textTotalTime = (TextView) listViewItem.findViewById(R.id.totalTime);
        TextView textTotalLaps = (TextView) listViewItem.findViewById(R.id.totalLaps);

        RunData run = tests.get(position);
        textTotalTime.setText(String.valueOf(run.getTotalTime()));
        textTotalLaps.setText(String.valueOf(run.getTotalLaps()));
        return listViewItem;
    }
}
