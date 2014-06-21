package com.nightguard.nfc.fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.nightguard.nfc.R;

@SuppressLint("ValidFragment")
public class AdminFragment extends Fragment {

	public static final String TAG = "com.nightguard.nfc.fragments.AdminFragment";

	View mRootView;
	AdminListener mAdminListener;


	public AdminFragment() {
	}

	public AdminFragment(AdminListener adminListener) {
		mAdminListener = adminListener;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mRootView = inflater.inflate(R.layout.fragment_admin, container, false);
		Button mEditTag; 
		mEditTag = (Button) mRootView.findViewById(R.id.fragmentAdminButtonEditTags);
		mEditTag.setOnClickListener(new OnClickListener(){
			
			public void onClick(View v) {
			mAdminListener.onEditTags();
			}
		});
		
		

		return mRootView;
	}

	public interface AdminListener {
		void onEditTags();
	}
}
