package com.example.rirakkusu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.rirakkusu.database.FeelingCursorWrapper;
import com.example.rirakkusu.database.FeelingDbSchema;
import com.example.rirakkusu.database.UserFeelingHelper;

import java.util.ArrayList;
import java.util.List;

public class FeelingsLab {
    private static FeelingsLab sfeelingsLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static FeelingsLab get(Context context){
        if(sfeelingsLab == null){
            sfeelingsLab = new FeelingsLab(context);
        }
        return sfeelingsLab;
    }

    private FeelingsLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new UserFeelingHelper(mContext).getWritableDatabase();
    }

    public List<Feelings> getFeelings() {
        List<Feelings> feelings = new ArrayList<>();
        FeelingCursorWrapper cursor = queryFeelings(null, null);

        try{
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                feelings.add(cursor.getFeelings());
                cursor.moveToNext();
            }

        } finally {
            cursor.close();
        }
        return feelings;
    }
    public void addFeelings(Feelings feelings) {
        ContentValues values = getContentValues(feelings);

        mDatabase.insert(FeelingDbSchema.FeelingTable.NAME, null, values);
    }

    private ContentValues getContentValues(Feelings feelings) {
        ContentValues values = new ContentValues();
        values.put(FeelingDbSchema.FeelingTable.Cols.NAME, feelings.getName());
        values.put(FeelingDbSchema.FeelingTable.Cols.DATE, feelings.getDate().getTime());
        values.put(FeelingDbSchema.FeelingTable.Cols.FEELING, feelings.getFeeling());

        return values;
    }

    private FeelingCursorWrapper queryFeelings(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(FeelingDbSchema.FeelingTable.NAME,null,whereClause,whereArgs, null, null, null);
        return new FeelingCursorWrapper(cursor);
    }

}
