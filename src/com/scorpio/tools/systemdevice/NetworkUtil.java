package com.scorpio.tools.systemdevice;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

public class NetworkUtil {
	
	protected static ConnectivityManager connectivityManager;
	protected static Context context;
	
	// 获取流量连接对象
	public NetworkUtil(Context context) {
		NetworkUtil.context = context;
	}
	
	public static boolean refreshNetworkManager() {
		if (context == null) {
			return false;
		}
		connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		return true;
	}
	
	public static void changeNetworkState() {
//		connectivityManager.0
	}
	
	// 查看流量是否打开
	public static boolean checkNetworkIsOpen() {
		if (connectivityManager == null) {
			return false;
		}
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		if (networkInfo == null) {
			return false;
		}
		return networkInfo.isAvailable();
	}
}
