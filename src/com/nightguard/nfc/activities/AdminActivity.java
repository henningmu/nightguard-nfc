package com.nightguard.nfc.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.nightguard.nfc.R;
import com.nightguard.nfc.R.id;
import com.nightguard.nfc.R.layout;
import com.nightguard.nfc.fragments.AdminFragment;
import com.nightguard.nfc.fragments.AdminFragment.AdminListener;
import com.nightguard.nfc.fragments.EditTagFragment;

public class AdminActivity extends FragmentActivity implements AdminListener {

	AdminFragment mAdminFragment;
	EditTagFragment mEditTagFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin);

		mAdminFragment = new AdminFragment(this);
		mEditTagFragment = new EditTagFragment();

		getSupportFragmentManager()
				.beginTransaction()
				.add(R.id.activityAdminContentFrame, mAdminFragment,
						AdminFragment.TAG).commit();
	}

	@Override
	public void onEditTags() {
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.activityAdminContentFrame, mEditTagFragment)
				.addToBackStack(EditTagFragment.TAG).commit();
	}
}
