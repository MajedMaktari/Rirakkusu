package com.example.rirakkusu.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.rirakkusu.Feelings;

import java.util.Date;

public class FeelingCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public FeelingCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Feelings getFeelings(){
        String name = getString(getColumnIndex(FeelingDbSchema.FeelingTable.Cols.NAME));
        long date = getLong(getColumnIndex(FeelingDbSchema.FeelingTable.Cols.DATE));
        String feeling = getString(getColumnIndex(FeelingDbSchema.FeelingTable.Cols.FEELING));

        Feelings feelingLab = new Feelings();
        feelingLab.setName(name);
        feelingLab.setDate(new Date(date));
        feelingLab.setFeeling(feeling);

    return feelingLab;
    }
}
