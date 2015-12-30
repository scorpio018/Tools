package com.scorpio.tools.receiver;

import com.scorpio.tools.activity.NetworkControllActivity;
import com.scorpio.tools.activity.R;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiManager;
import android.os.Parcelable;

public class NetworkConnectChangedReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(intent.getAction())) {// 这个监听wifi的打开与关闭，与wifi的连接无关
//			WifiUtil.refreshWifiManager();
			NetworkControllActivity.wifiRefresh();
			
			/*int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, 0);
			switch (wifiState) {
			case WifiManager.WIFI_STATE_DISABLED:
				NetworkControllActivity.Toast(R.string.wifi_state_disabled);
			case WifiManager.WIFI_STATE_DISABLING:
				NetworkControllActivity.Toast(R.string.wifi_state_disabling);
			case WifiManager.WIFI_STATE_ENABLED:
				NetworkControllActivity.Toast(R.string.wifi_state_enabled);
			case WifiManager.WIFI_STATE_ENABLING:
				NetworkControllActivity.Toast(R.string.wifi_state_enabling);
			default:
				NetworkControllActivity.Toast(R.string.wifi_state_unknown);
			}*/
		}
		// 这个监听wifi的连接状态即是否连上了一个有效无线路由，当上边广播的状态是WifiManager.WIFI_STATE_DISABLING，和WIFI_STATE_DISABLED的时候，根本不会接到这个广播。
		// 在上边广播接到广播是WifiManager.WIFI_STATE_ENABLED状态的同时也会接到这个广播，当然刚打开wifi肯定还没有连接到有效的无线
		if (WifiManager.NETWORK_STATE_CHANGED_ACTION.equals(intent.getAction())) {
			NetworkControllActivity.wifiRefresh();
			Parcelable parcelableExtra = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
			if (null != parcelableExtra) {
				NetworkInfo networkInfo = (NetworkInfo) parcelableExtra;
				State state = networkInfo.getState();
				boolean isConnected = state == State.CONNECTED;// 当然，这边可以更精确的确定状态
				if (isConnected) {
					NetworkControllActivity.Toast(R.string.wifi_is_connect);
				} else {
					NetworkControllActivity.Toast(R.string.wifi_is_disconnect);
				}
			}
		}
		
		// 这个监听网络连接的设置，包括wifi和移动数据的打开和关闭。.
		// 最好用的还是这个监听。wifi如果打开，关闭，以及连接上可用的连接都会接到监听。见log
		// 这个广播的最大弊端是比上边两个广播的反应要慢，如果只是要监听wifi，我觉得还是用上边两个配合比较合适
		if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
//			NetworkUtil.refreshNetworkManager();
			NetworkControllActivity.networkRefresh();
			ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
			if (activeNetworkInfo != null) {
				int type = activeNetworkInfo.getType();
				if (type == ConnectivityManager.TYPE_WIFI) {
					NetworkControllActivity.wifiRefresh();
//					NetworkControllActivity.Toast(R.string.wifi_is_connect);
				} else if (type == ConnectivityManager.TYPE_MOBILE) {
					NetworkControllActivity.networkRefresh();
//					NetworkControllActivity.Toast(R.string.network_is_open);
				}
			} else {
//				NetworkControllActivity.Toast(R.string.network_is_connect_no);
				NetworkControllActivity.refresh();
			}
//			NetworkInfo gprs = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
//			NetworkInfo wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
//			Log.i(NetworkControllActivity.ACTIVITY_SERVICE, "网络状态改变:" + wifi.isConnected() + " 3g:" + gprs.isConnected());
//			NetworkInfo info = intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
//			if (info != null) {
//				Log.e("H3c", "info.getTypeName()" + info.getTypeName());
//				Log.e("H3c", "getSubtypeName()" + info.getSubtypeName());
//				Log.e("H3c", "getState()" + info.getState());
//				Log.e("H3c", "getDetailedState()" + info.getDetailedState().name());
//				Log.e("H3c", "getDetailedState()" + info.getExtraInfo());
//				Log.e("H3c", "getType()" + info.getType());
//
//				if (NetworkInfo.State.CONNECTED == info.getState()) {
//					Toast.makeText(context, "网络已经连接", Toast.LENGTH_SHORT);
//					Log.i(NetworkControllActivity.ACTIVITY_SERVICE, "连接状态为：已连接");
//				} else if (info.getType() == 1) {
//					if (NetworkInfo.State.DISCONNECTING == info.getState()) {
//						Toast.makeText(context, "网络已经断开连接", Toast.LENGTH_SHORT);
//						Log.i(NetworkControllActivity.ACTIVITY_SERVICE, "连接状态为：未连接");
//					}
//				}
//			}
		}
	}
}