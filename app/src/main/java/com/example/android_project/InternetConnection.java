package com.example.android_project;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class InternetConnection {
    public static boolean isConnectedInternet(Activity activity){
        ConnectivityManager cm = (ConnectivityManager)activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if( networkInfo != null){
            NetworkInfo.State ns = networkInfo.getState();
            if(ns.compareTo(NetworkInfo.State.CONNECTED) == 0){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
}