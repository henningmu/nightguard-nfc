package com.nightguard.nfc.fragments;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

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
import android.widget.Toast;

import com.nightguard.nfc.R;
import com.nightguard.nfc.dao.NFCTagLocationDAO;
import com.nightguard.nfc.dao.NFCTagReadTime;
import com.nightguard.nfc.dao.NFCTagReadTimeDAO;

public class MainFragment extends Fragment {

	public static String nfcText;

	public static final String TAG = "com.nightguard.nfc.fragments.MainFragment";

	View mRootView;
	TextView mTvLastScan;
	TextView mTvTimeSinceLastScan;
	Date mTimestamp;
	
	String data;
	boolean newDataAvailable = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mRootView = inflater.inflate(R.layout.fragment_main, container, false);
		mTvLastScan = (TextView) mRootView
				.findViewById(R.id.fragmentMainLastScan);
		mTvTimeSinceLastScan = (TextView) mRootView
				.findViewById(R.id.fragmentMainTimeSinceLastScan);

		if(newDataAvailable) {
			mTvLastScan.setText(data);
			startCounter();
			newDataAvailable = false;
		}
		
		return mRootView;
	}

	public void onNewTag(Tag tag, NFCTagReadTimeDAO writeTimData,
			NFCTagLocationDAO writeLocData) {
		Ndef ndef = Ndef.get(tag);
		if (ndef == null) {
			Log.d(TAG, "NDEF is not supported by this Tag.");
			return;
		}

		NdefMessage ndefMessage = ndef.getCachedNdefMessage();

		NdefRecord[] records = ndefMessage.getRecords();
		for (NdefRecord ndefRecord : records) {

			// Read text
			byte[] payload = ndefRecord.getPayload();
			// Get the Text
			nfcText = new String(payload);
			Log.d(TAG, nfcText);
			String[] nfcSplit = nfcText.split("-");
			NFCTagReadTime TagTimBean = new NFCTagReadTime();
			TagTimBean.setNfcTagId(nfcSplit[0].getBytes());
			mTimestamp = new Date();
			TagTimBean.setReadTime(mTimestamp);
			writeTimData.openConnection();
			writeTimData.create(TagTimBean);
			writeTimData.closeConnection();

			if (nfcSplit.length == 2) {
				String tagId = nfcSplit[0];
				String tagPlace = nfcSplit[1];
				String formatTime = new SimpleDateFormat("dd. MMM. yyyy hh:mm")
						.format(mTimestamp);
				if(mTvLastScan == null) {
					newDataAvailable = true;
					data = tagPlace + " @ " + formatTime;
				} else {
					mTvLastScan.setText(tagPlace + " @ " + formatTime);
				}

				startCounter();
			} else {
				Toast.makeText(getActivity().getApplicationContext(),
						"Invalid data on NFC tag", Toast.LENGTH_SHORT).show();
			}
		}
	}

	private void startCounter() {
		Timer T=new Timer();
		T.scheduleAtFixedRate(new TimerTask() {         
		        @Override
		        public void run() {
		            getActivity().runOnUiThread(new Runnable()
		            {
		                @Override
		                public void run()
		                {
		                	Date now = new Date();
							long difMillis = now.getTime() - mTimestamp.getTime();
		
							long diffSeconds = difMillis / 1000;
		
							String difference = "" + (diffSeconds / 60) + ":"
									+ (diffSeconds % 60);
							Log.d(TAG, "dif: " + difference);
							mTvTimeSinceLastScan.setText(difference);               
		                }
		            });
		        }
		    }, 1000, 1000);
		
	}
}
