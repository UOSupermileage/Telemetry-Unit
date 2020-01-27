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
        db.execSQL("CREATE TABLE RUN(ID INTEGER PRIMARY KEY AUTOINCREMENT)");
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

    public boolean createNewRun() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.insert("RUN", null, null) != -1;
    }

    public boolean logSpeed(int run, int lap, String point, double speed) {

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM SPEED WHERE Run=?", new String[]{String.valueOf(run)});
        cursor.moveToFirst();

        ContentValues cv = new ContentValues();
        cv.put("Run", run);
        cv.put("Lap", lap);
        cv.put("Point", point);
        cv.put("Speed", speed);

        return db.update("SPEED", cv, "run=? AND lap=?", new String[]{String.valueOf(run), String.valueOf(lap)}) != -1;

    }

    public boolean logElevation(int run, int lap, String point, double elevation) {

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM ELEVATION WHERE Run=?", new String[]{String.valueOf(run)});
        cursor.moveToFirst();

        ContentValues cv = new ContentValues();
        cv.put("Run", run);
        cv.put("Lap", lap);
        cv.put("Point", point);
        cv.put("Elevation", elevation);

        return db.update("ELEVATION", cv, "run=? AND lap=?", new String[]{String.valueOf(run), String.valueOf(lap)}) != -1;

    }
}
