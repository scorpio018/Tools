package com.scorpio.tools.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	
	private Button btnToNetworkControll;
	private Button btnToTextViewDemo;
	private Button btnToViewPagerActivity;
	
	private Intent intent = new Intent();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initBtn();
	}
	
	private void initBtn() {
		initNetworkControllBtn();
		initTextViewDemoBtn();
		initViewPagerActivityBtn();
	}
	
	private void initTextViewDemoBtn() {
		btnToTextViewDemo = (Button) findViewById(R.id.btnToTextViewDemo);
		btnToTextViewDemo.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				intent.setClass(MainActivity.this, TextViewDemoActivity.class);
				startActivity(intent);
			}
		});
	}
	
	private void initViewPagerActivityBtn() {
		btnToViewPagerActivity = (Button) findViewById(R.id.btnToViewPagerActivity);
		btnToViewPagerActivity.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				intent.setClass(MainActivity.this, ViewPagerActivity.class);
				startActivity(intent);
			}
		});
	}
	
	private void initNetworkControllBtn() {
		btnToNetworkControll = (Button) findViewById(R.id.btnToNetworkControll);
		btnToNetworkControll.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				intent.setClass(MainActivity.this, NetworkControllActivity.class);
				startActivity(intent);
			}
		});
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		System.out.println("opPause");
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		System.out.println("onStop");
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		System.out.println("onResume");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
