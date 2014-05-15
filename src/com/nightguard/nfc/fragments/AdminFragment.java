package com.nightguard.nfc.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.nightguard.nfc.R;

@SuppressLint("ValidFragment")
public class AdminFragment extends Fragment {

	public static final String TAG = "com.nightguard.nfc.fragments.AdminFragment";

	View mRootView;

	AdminListener mAdminListener;

	@InjectView(R.id.fragmentAdminWays)
	ListView mLvWays;

	public AdminFragment() {
	}

	public AdminFragment(AdminListener adminListener) {
		mAdminListener = adminListener;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mRootView = inflater.inflate(R.layout.fragment_admin, container, false);

		ButterKnife.inject(this, mRootView);

		return mRootView;
	}

	@OnClick(R.id.fragmentAdminButtonEditTags)
	public void onEditTagsClicked(View v) {
		mAdminListener.onEditTags();
	}

	public interface AdminListener {
		void onEditTags();
	}
}
