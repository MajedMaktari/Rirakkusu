package com.example.rirakkusu.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class UserFeelingHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "userFeeling.db";

    public UserFeelingHelper( Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + FeelingDbSchema.FeelingTable.NAME + " (" +
                "_id integer primary key autoincrement," +
                FeelingDbSchema.FeelingTable.Cols.NAME + "," +
                FeelingDbSchema.FeelingTable.Cols.DATE + "," +
                FeelingDbSchema.FeelingTable.Cols.FEELING + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
