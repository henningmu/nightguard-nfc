package com.nightguard.nfc.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nightguard.nfc.R;

public class DetailsFragment extends Fragment {
	
	public static final String TAG = "com.nightguard.nfc.fragments.DetailsFragment";
	
	View mRootView;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_details, container, false);
        
        return mRootView;
    }
}
