package com.example.mutiaramobile.serviceprovider;

import android.content.Context;
import android.content.SharedPreferences;

public class StoreSession {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;
    private static final String PREF_NAME = "Mutiara Berlian";

    public StoreSession(Context context){
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public void setDataString(String data, String value){
        editor.putString(data, value);
        editor.commit();
    }

    public String getDataString(String data){
        return pref.getString(data, null);
    }

    public void setDataInteger(String data, int value){
        editor.putInt(data, value);
        editor.commit();
    }

    public Integer getDataInteger(String data){
        return pref.getInt(data, 0);
    }

    public void RemoveSession(){
        editor.clear();
        editor.commit();
    }
}
