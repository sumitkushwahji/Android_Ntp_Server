package com.example.ntp;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class CheckInternetConnection {
    public static String getConnectivityStatusString(MainActivity mainActivity) {
        String status=null;
        ConnectivityManager connectivityManager= (ConnectivityManager) mainActivity.getSystemService(mainActivity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        if (networkInfo!=null){
            if (networkInfo.getType()==connectivityManager.TYPE_WIFI) {
                status = "wifi enabled";
                return status;
            }else if (networkInfo.getType()==connectivityManager.TYPE_MOBILE){
                status = "mobile data enabled";
                return status;
            }


        }else {
            status="internet is not available";
            return status;

        }
        return status;
    }
}
