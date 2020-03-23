package com.uoeracing.telemetrysystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "database.db";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE RUN(ID INTEGER PRIMARY KEY NOT NULL)");
        db.execSQL("CREATE TABLE SPEED(Run INTEGER, Lap INTEGER, Point TEXT, Speed REAL)");
        db.execSQL("CREATE TABLE ELEVATION(Run INTEGER, Lap INTEGER, Point TEXT, Elevation REAL)");
        db.execSQL("CREATE TABLE TIME(Run INTEGER, Lap INTEGER, Time INTEGER)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS RUN");
        db.execSQL("DROP TABLE IF EXISTS SPEED");
        db.execSQL("DROP TABLE IF EXISTS ELEVATION");
        db.execSQL("DROP TABLE IF EXISTS TIME");
    }

    public int createNewRun() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT ID FROM RUN", null);

        ContentValues cv = new ContentValues();
        if (cursor.getCount() > 0) {
            cursor.moveToLast();
            cv.put("ID", cursor.getInt(0) + 1);
        } else {
            cv.put("ID", 0);
        }
        db.insert("RUN", null, cv);

        return cv.getAsInteger("ID");
    }

    public boolean logPositionData(int run, PositionData pd) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues speedValues = new ContentValues();
        speedValues.put("Run", run);
        speedValues.put("Lap", pd.getLap());
        speedValues.put("Point", "A");
        speedValues.put("Speed", pd.getSpeed());

        long speedRes = db.update("SPEED", speedValues, "Run=? AND Lap=?",
                new String[]{String.valueOf(run), String.valueOf(pd.getLap())});

        ContentValues elevationValues = new ContentValues();
        elevationValues.put("Run", run);
        elevationValues.put("Lap",pd.getLap());
        elevationValues.put("Point", "A");
        elevationValues.put("Elevation", pd.getAltitude());

        long elevationRes = db.update("ELEVATION", elevationValues, "Run=? AND Lap=?",
                new String[]{String.valueOf(run), String.valueOf(pd.getLap())});

        ContentValues timeValues = new ContentValues();
        elevationValues.put("Run", run);
        elevationValues.put("Lap",pd.getLap());
        elevationValues.put("Time", pd.getTime());

        long timeRes = db.update("TIME", timeValues, "Run=? AND Lap=?",
                new String[]{String.valueOf(run), String.valueOf(pd.getLap())});

        return (speedRes != -1) && (elevationRes != -1) && (timeRes != -1);

    }

    public boolean getPositionData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM RUN", new String[]{});
        cursor.moveToLast();
        return true;
    }

    //CURSORS TO BE USED FOR READING DATABASE
    /*
    Cursor speedCursor = db.rawQuery("SELECT * FROM SPEED WHERE Run=? AND Lap=?",
                new String[]{String.valueOf(run), String.valueOf(pd.getLap())});

        Cursor elevationCursor = db.rawQuery("SELECT * FROM ELEVATION WHERE Run=? AND Lap=?",
                new String[]{String.valueOf(run), String.valueOf(pd.getLap())});

        Cursor lapTimeCursor = db.rawQuery("SELECT * FROM TIME WHERE Run=? AND Lap=?",
                new String[]{String.valueOf(run), String.valueOf(pd.getLap())});
     */
}
