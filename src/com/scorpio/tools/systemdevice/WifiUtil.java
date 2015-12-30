package com.scorpio.tools.systemdevice;

import com.scorpio.tools.activity.R;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

public class WifiUtil {
	
	private static WifiManager wifiManager;
	
	private static Context context;
	
	public WifiUtil(Context context) {
		WifiUtil.context = context;
		refreshWifiManager();
	}
	
	public static boolean refreshWifiManager() {
		if (context == null) {
			return false;
		}
		// 获取WIFI设备（注：如果想进行WIFI的操作，需要在AndroidManifest.xml中进行权限配置）
		wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		return true;
	}
	
	// 判断WIFI设备是否打开
	public static boolean isWifiOpen() {
		return wifiManager.isWifiEnabled();
	}
	
	public static void changeWifiState() {
		wifiManager.setWifiEnabled(!isWifiOpen());
	}
	
	// 判断WIFI是否已经连接上
	public static boolean checkWifiIsConnect() {
		WifiInfo wifiInfo = wifiManager.getConnectionInfo();
		int ipAddress = wifiInfo == null ? -1 : wifiInfo.getIpAddress();
		if (isWifiOpen() && ipAddress != -1) {
			return true;
		} else {
			return false;
		}
	}
	
	public String getCurWifiState() {
		// 获取当前WIFI设备的状态
		int wifiState = wifiManager.getWifiState();
		/**
		 * 常用WIFI状态
		 * WIFI_STATE_DISABLED     //WIFI网卡不可用   
		 * WIFI_STATE_DISABLING    //WIFI网卡正在关闭   
		 * WIFI_STATE_ENABLED  //WIFI网卡可用   
		 * WIFI_STATE_ENABLING     //WIFI网卡正在打开   
		 * WIFI_STATE_UNKNOWN  //WIFI网卡状态不可知
		 */
		switch (wifiState) {
		case WifiManager.WIFI_STATE_DISABLED:
			return context.getString(R.string.wifi_state_disabled);
		case WifiManager.WIFI_STATE_DISABLING:
			return context.getString(R.string.wifi_state_disabling);
		case WifiManager.WIFI_STATE_ENABLED:
			return context.getString(R.string.wifi_state_enabled);
		case WifiManager.WIFI_STATE_ENABLING:
			return context.getString(R.string.wifi_state_enabling);
		default:
			return context.getString(R.string.wifi_state_unknown);
		}
		/*if (wifiState == WifiManager.WIFI_STATE_DISABLED) {
			return context.getString(R.string.wifi_state_disabled);
		} else if (wifiState == WifiManager.WIFI_STATE_DISABLING) {
			return context.getString(R.string.wifi_state_disabling);
		} else if (wifiState == WifiManager.WIFI_STATE_ENABLED) {
			return context.getString(R.string.wifi_state_enabled);
		} else if (wifiState == WifiManager.WIFI_STATE_ENABLING) {
			return context.getString(R.string.wifi_state_enabling);
		} else {
			return context.getString(R.string.wifi_state_unknown);
		}*/
	}
	
	public static String getCurConnWifiName() {
		WifiInfo wifiInfo = getCurConnWifiInfo();
		return wifiInfo.getSSID();
	}
	
	/*public static String getCurConnWifiIp() {
		WifiInfo wifiInfo = getCurConnWifiInfo();
		return wifiInfo.getIpAddress();
	}*/
	
	public static WifiInfo getCurConnWifiInfo() {
		// 获取WIFI连接信息
		return wifiManager.getConnectionInfo();
	}
}
