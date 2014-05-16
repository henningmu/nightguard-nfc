package com.nightguard.nfc.fragments;

import java.io.UnsupportedEncodingException;

import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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
    
    public void onNewTag(Tag tag) {
		Ndef ndef = Ndef.get(tag);
		if (ndef == null) {
			Log.d(TAG, "NDEF is not supported by this Tag.");
			return;
		}

		NdefMessage ndefMessage = ndef.getCachedNdefMessage();

		NdefRecord[] records = ndefMessage.getRecords();
		for (NdefRecord ndefRecord : records) {
			try {
				// Read text
				byte[] payload = ndefRecord.getPayload();
				// Get the Text Encoding
				String textEncoding = ((payload[0] & 128) == 0) ? "UTF-8"
						: "UTF-16";
				// Get the Language Code
				int languageCodeLength = payload[0] & 0063;
				// Get the Text
				String text = new String(payload, languageCodeLength + 1,
						payload.length - languageCodeLength - 1, textEncoding);
				
				
				Log.d(TAG, text);
			} catch (UnsupportedEncodingException e) {
				Log.e(TAG, "Unsupported Encoding", e);
			}
		}
    }
}
