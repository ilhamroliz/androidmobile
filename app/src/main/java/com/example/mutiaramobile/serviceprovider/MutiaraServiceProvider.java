package com.example.mutiaramobile.serviceprovider;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class MutiaraServiceProvider {

    public String globalurl, globalsecret, globalclient, globalgrant;

    public MutiaraServiceProvider(){
        this.globalurl = "http://192.168.100.19/mutiara/";
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

}
