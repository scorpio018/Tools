package com.scorpio.tools.activity;

import java.util.ArrayList;

import com.scorpio.tools.adapter.ViewPagerAdapter;
import com.scorpio.tools.listener.PagerListener;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

public class ViewPagerActivity extends Activity {
	public static TextView tipsTv;

    ViewPager viewPager;
    ArrayList<View> viewList = new ArrayList<View>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager_main);

        tipsTv = (TextView) findViewById(R.id.tips_textview);
        tipsTv.setText("1");

//        viewPager = (ViewPager) findViewById(R.id.viewpager);
        // 给viewList装入数组
        for (int i = 0; i < 5; i++) {
            viewList.add(newView(i + 1));
        }
        // 设置适配器，将数组设置进去
        viewPager.setAdapter(new ViewPagerAdapter(viewList));
        viewPager.addOnPageChangeListener(new PagerListener());

    }

    public View newView(int text) {
        TextView tv = new TextView(this);
        tv.setText(String.valueOf(text));
        tv.setTextSize(100);
        tv.setTextColor(0xff000000);
        tv.setGravity(Gravity.CENTER);
        return tv;
    }
}
