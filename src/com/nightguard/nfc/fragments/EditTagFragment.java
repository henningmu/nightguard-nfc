package com.nightguard.nfc.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.nightguard.nfc.R;

public class EditTagFragment extends Fragment {
	
	public static final String TAG = "com.nightguard.nfc.fragments.EditTagFragment";
	
	View mRootView;
	
	@InjectView(R.id.fragmentEditSerialNumber)
	TextView mTvSerialNumber;
	
	@InjectView(R.id.fragmentEditTagName)
	EditText mEtTagName;
	
	@InjectView(R.id.fragmentEditTagPlace)
	EditText mEtTagPlace;	
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_edit_tag, container, false);
        
        ButterKnife.inject(this, mRootView);
        
        return mRootView;
    }
    
    @OnClick(R.id.fragmentEditButtonGenerateSerialNumber)
    public void onGenerateNewSerialNumber(View v) {
    	
    }
    
    @OnClick(R.id.fragmentEditButtonSaveToTag)
    public void saveDataOnNfcTag(View v) {
    	
    }
}
