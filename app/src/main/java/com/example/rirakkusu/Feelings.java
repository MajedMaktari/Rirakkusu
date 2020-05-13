package com.example.rirakkusu;

import com.google.android.material.internal.NavigationMenu;

import java.security.PrivateKey;
import java.util.Date;

public class Feelings {
    private String mName;
    private Date mDate = new Date();
    private String mFeeling;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public String getFeeling() {
        return mFeeling;
    }

    public void setFeeling(String feeling) {
        mFeeling = feeling;
    }


}
