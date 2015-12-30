package com.scorpio.tools.activity;

import com.scorpio.tools.receiver.NetworkConnectChangedReceiver;
import com.scorpio.tools.systemdevice.NetworkUtil;
import com.scorpio.tools.systemdevice.WifiUtil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class NetworkControllActivity extends Activity {
	
	private static TextView WifiState;
	private static TextView WifiEnable;
	private static TextView WifiName;
	private static ToggleButton wifiToggleButton;
	
	private static TextView NetworkEnable;
	private static ToggleButton networkToggleButton;
	
	private static WifiUtil wifiUtil;
	private static NetworkUtil networkUtil;
	
	private static Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.network_controll_main);
		wifiUtil = new WifiUtil(this);
		networkUtil = new NetworkUtil(this);
		context = this;
		regist();
		commonOperation();
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		commonOperation();
	}
	
	public void initCurWifiState() {
		WifiState = (TextView) findViewById(R.id.WifiState);
		WifiEnable = (TextView) findViewById(R.id.WifiEnable);
		WifiName = (TextView) findViewById(R.id.WifiName);
		wifiToggleButton = (ToggleButton) findViewById(R.id.WifiOnOffToggleBtn);
		refreshWifiTextView();
	}
	
	public static void refreshWifiTextView() {
		/**
		 * 常用WIFI状态
		 * WIFI_STATE_DISABLED     //WIFI网卡不可用   
		 * WIFI_STATE_DISABLING    //WIFI网卡正在关闭   
		 * WIFI_STATE_ENABLED  //WIFI网卡可用   
		 * WIFI_STATE_ENABLING     //WIFI网卡正在打开   
		 * WIFI_STATE_UNKNOWN  //WIFI网卡状态不可知
		 */
		String curWifiState = wifiUtil.getCurWifiState();
		WifiState.setText(curWifiState);
		// 判断WIFI设备是否打开
		if (WifiUtil.isWifiOpen()) {
			wifiToggleButton.setChecked(true);
			WifiEnable.setText(R.string.wifi_is_open);
		} else {
			wifiToggleButton.setChecked(false);
			WifiEnable.setText(R.string.wifi_is_close);
		}
		
		// 获取WIFI连接信息
		WifiName.setText(WifiUtil.getCurConnWifiName());
	}
	
	public void initCurNetworkState() {
		NetworkEnable = (TextView) findViewById(R.id.NetworkEnable);
		networkToggleButton = (ToggleButton) findViewById(R.id.NetworkOnOffToggleBtn);
		refreshNetworkTextView();
	}
	
	public static void refreshNetworkTextView() {
		if (NetworkUtil.checkNetworkIsOpen()) {
			networkToggleButton.setText(R.string.network_is_open);
			NetworkEnable.setText(R.string.network_is_open);
		} else {
			networkToggleButton.setText(R.string.network_is_close);
			NetworkEnable.setText(R.string.network_is_close);
		}
	}
	
	public void initWifiStateChangeBtn() {
		wifiToggleButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				WifiUtil.changeWifiState();
				refresh();
			}
		});
	}
	
	public void initNetworkStateChangeBtn() {
		networkToggleButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				NetworkUtil.changeNetworkState();
				refresh();
			}
		});
	}
	
	private void regist() {
		IntentFilter filter = new IntentFilter();
		filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
		filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
		filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
		registerReceiver(new NetworkConnectChangedReceiver(), filter);
	}
	
	private void commonOperation() {
		initCurWifiState();
		initWifiStateChangeBtn();
		initCurNetworkState();
	}
	
	public static void refresh() {
		wifiRefresh();
		networkRefresh();
	}
	
	public static void wifiRefresh() {
		boolean isRefresh = WifiUtil.refreshWifiManager();
		if (isRefresh) {
			refreshWifiTextView();
		}
	}
	
	public static void networkRefresh() {
		boolean isRefresh = NetworkUtil.refreshNetworkManager();
		if (isRefresh) {
			refreshNetworkTextView();
		}
	}
	
	public static void Toast(CharSequence text) {
		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();;
	}
	
	public static void Toast(int textId) {
		String text = context.getString(textId);
		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();;
	}
	
	private void backToMain() {
		Intent intent = new Intent();
		intent.setClass(NetworkControllActivity.this, MainActivity.class);
		startActivity(intent);
	}
}
