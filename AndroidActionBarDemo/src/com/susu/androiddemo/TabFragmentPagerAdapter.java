package com.susu.androiddemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabFragmentPagerAdapter extends FragmentPagerAdapter {

	public static final int MAX_TAB_SIZE = 2;

	public TabFragmentPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int arg0) {
		Fragment ft = null;
		switch (arg0) {
		case 0:
			ft = new ListFragment();
			break;
		case 1:
			ft = new HistoryFragment();
			break;
		}
		return ft;
	}

	@Override
	public int getCount() {
		return MAX_TAB_SIZE;
	}

	public CharSequence getPageTitle(int position) {
		String tabLable = null;
		switch (position) {
		case 0:
			tabLable = "任务清单";
			break;
		case 1:
			tabLable = "历史回顾";
			break;
		}
		return tabLable;
	}
}
