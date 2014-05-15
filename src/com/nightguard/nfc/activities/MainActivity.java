package com.nightguard.nfc.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.nightguard.nfc.R;
import com.nightguard.nfc.R.id;
import com.nightguard.nfc.R.layout;
import com.nightguard.nfc.R.string;
import com.nightguard.nfc.fragments.DetailsFragment;
import com.nightguard.nfc.fragments.LoginFragment;
import com.nightguard.nfc.fragments.LoginFragment.LoginListener;
import com.nightguard.nfc.fragments.MainFragment;

public class MainActivity extends FragmentActivity implements LoginListener {

	SectionsPagerAdapter mSectionsPagerAdapter;
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());


		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
	}

	
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			switch (position) {
				case 0:
					Fragment mainFragment = new MainFragment();
					return mainFragment;
				case 1:
					Fragment detailsFragment = new DetailsFragment();
					return detailsFragment;
				case 2:
					Fragment loginFragment = new LoginFragment(MainActivity.this);
					return loginFragment;
				default:
					return null;
			}
		}

		@Override
		public int getCount() {
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			switch (position) {
			case 0:
				return getString(R.string.titleMainSection);
			case 1:
				return getString(R.string.titleDetailSection);
			case 2:
				return getString(R.string.titleAdminSection);
			}
			return null;
		}
	}

	@Override
	public void onSuccessfulLogin() {
		Intent intent = new Intent(this, AdminActivity.class);
		startActivity(intent);
	}
}
