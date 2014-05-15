package com.nightguard.nfc.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

import com.nightguard.nfc.R;

public class MainFragment extends Fragment {
	
	public static final String TAG = "com.nightguard.nfc.fragments.MainFragment";
	
	View mRootView;
		
	@InjectView(R.id.fragmentMainLastScan)
	TextView mTvLastScan;
	
	@InjectView(R.id.fragmentMainTimeSinceLastScan)
	TextView mTvTimeSinceLastScan;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_main, container, false);
        
        ButterKnife.inject(this, mRootView);
        
        return mRootView;
    }
}
