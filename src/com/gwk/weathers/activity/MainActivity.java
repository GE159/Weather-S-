package com.gwk.weathers.activity;

import com.gwk.weathers.app.R;
import com.gwk.weathers.fragment.MainIndexFragment;
import com.gwk.weathers.fragment.MainUserFragment;
import com.gwk.weathers.fragment.MainliveFragment;
import com.gwk.weathers.fragment.MyFragmentPagerAdapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends FragmentActivity implements
		OnCheckedChangeListener
{
	// UI Objects
	private RadioGroup rg_tab_bar;
	private RadioButton rb_weather,rb_live,rb_me;
	private ViewPager vpager;

	// Fragment Object
	private MyFragmentPagerAdapter mAdapter;
	private FragmentManager fm;
	private FragmentTransaction ft;
	private MainIndexFragment indexFragment;
	private MainliveFragment liveFragment;
	private MainUserFragment userFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		initViews();
		// 获取第一个单选按钮，并设置其为选中状态
		rb_weather.setChecked(true);
	}

	private void initViews()
	{
		rg_tab_bar = (RadioGroup) findViewById(R.id.rg_tab_bar);
		rb_weather = (RadioButton) findViewById(R.id.rb_weather);
		rb_live = (RadioButton) findViewById(R.id.rb_live);
		rb_me = (RadioButton) findViewById(R.id.rb_me);
		rg_tab_bar.setOnCheckedChangeListener(this);

		vpager = (ViewPager) findViewById(R.id.mainviewPager);
		mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
		vpager.setAdapter(mAdapter);
		vpager.setCurrentItem(0);
		
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId)
	{

		switch (checkedId)
		{
		case R.id.rb_weather:
			vpager.setCurrentItem(0);
			break;
		case R.id.rb_live:
			vpager.setCurrentItem(1);
			break;
		case R.id.rb_me:
			vpager.setCurrentItem(2);
			break;

		}

	}

	private void hideAllFragment(FragmentTransaction ft)
	{
		if (indexFragment != null)
			ft.hide(indexFragment);
		if (liveFragment != null)
			ft.hide(liveFragment);
		if (userFragment != null)
			ft.hide(userFragment);
	}
	
	/**
	 * 传入Countcode,启动WeatherActivity界面
	 * @param countyCode
	 */
	public static void StartWeatherActivity(Context context,String countyCode)
	{
		Intent intent=new Intent(context,MainActivity.class);
		intent.putExtra(Myconfig.COUNTYCODE, countyCode);
		context.startActivity(intent);
	}
}
