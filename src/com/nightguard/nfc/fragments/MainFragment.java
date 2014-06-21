package com.nightguard.nfc.fragments;

import java.sql.Date.*;
import java.util.Date;
import java.util.List;

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

import com.nightguard.nfc.R;
import com.nightguard.nfc.dao.NFCTagLocation;
import com.nightguard.nfc.dao.NFCTagLocationDAO;
import com.nightguard.nfc.dao.NFCTagReadTime;
import com.nightguard.nfc.dao.NFCTagReadTimeDAO;

public class MainFragment extends Fragment {

	public static String nfcText;

	public static final String TAG = "com.nightguard.nfc.fragments.MainFragment";

	View mRootView;
	TextView mTvLastScan;
	TextView mTvTimeSinceLastScan;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mRootView = inflater.inflate(R.layout.fragment_main, container, false);
		mTvLastScan = (TextView) mRootView
				.findViewById(R.id.fragmentMainLastScan);
		mTvTimeSinceLastScan = (TextView) mRootView
				.findViewById(R.id.fragmentMainTimeSinceLastScan);

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
			Date timestamp = new Date();
			TagTimBean.setReadTime(timestamp);
			writeTimData.openConnection();
			writeTimData.create(TagTimBean);
			writeTimData.closeConnection();

			List<NFCTagLocation> getLoc;
			writeLocData.openConnection();
			getLoc = writeLocData.readAll();
			writeLocData.closeConnection();
			for (NFCTagLocation tagloc : getLoc) {
				String ara=new String(tagloc.getNfcTagId());
					if (ara.compareTo(nfcSplit[0])==0){
//				mTvLastScan.setText("Letztes Tag: "+ara+", "+tagloc.getLocation());
				
			}
			}
		}
	}
}
