package com.xcc.advancedday13.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

/**
 * Created by bukeyishidecheng on 16/9/22.
 */
public class NetUtils  {

    private NetUtils(){
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static boolean isConnected(Context context){

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager!=null) {
            NetworkInfo info = connectivityManager.getActiveNetworkInfo();
            if (info!=null&&info.isConnected()) {
                if (info.getState()== NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }

        return false;

    }
}
