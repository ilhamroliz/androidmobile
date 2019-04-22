package com.example.mutiaramobile.serviceprovider;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class MutiaraServiceProvider {

    public String globalurl, globalsecret, globalclient, globalgrant;

    public MutiaraServiceProvider(){
        this.globalurl = "http://alamraya.site/sub_mutiaraberlian/";
        this.globalclient = "2";
        this.globalsecret = "0MJgnmsIWxy8M695N30imMQGmsQ7ZveeGhLcuijV";
        this.globalgrant = "password";
    }
    public String getGlobalGrant() {
        return this.globalgrant;
    }

    public String getGlobalUrl(){
        return this.globalurl;
    }

    public String getGlobalSecret(){
        return this.globalsecret;
    }

    public String getGlobalClient(){
        return this.globalclient;
    }

    public String getAuthorization(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("Mutiara Berlian", Context.MODE_PRIVATE);
        String user = sharedPreferences.getString("token_type", null);
        String token = sharedPreferences.getString("access_token", null);
        String auth = user + " " + token;
        return auth;
    }
}
