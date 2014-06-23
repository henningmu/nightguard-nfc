package com.nightguard.nfc.fragments;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.nightguard.nfc.R;
import com.nightguard.nfc.dao.NFCTagLocation;
import com.nightguard.nfc.dao.NFCTagLocationDAO;
import com.nightguard.nfc.dao.NFCTagReadTime;
import com.nightguard.nfc.dao.NFCTagReadTimeDAO;

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

		NFCTagLocationDAO readLocData = new NFCTagLocationDAO(this
				.getActivity().getApplicationContext());
		NFCTagReadTimeDAO readTimeData = new NFCTagReadTimeDAO(getActivity());
		readTimeData.openConnection();
		readLocData.openConnection();
		List<NFCTagReadTime> getTime = readTimeData.readAll();
		List<NFCTagLocation> getLoc = readLocData.readAll();
		readTimeData.closeConnection();
		readLocData.closeConnection();
		List<String> loc_Id = new ArrayList<String>();
		
		ArrayList<String> listData = new ArrayList<String>();
		for (NFCTagReadTime tagread : getTime) {
			String readNFCTagId = new String(tagread.getNfcTagId());
			for (NFCTagLocation tagloc : getLoc) {
				String locNFCTagId = new String(tagloc.getNfcTagId());
				if (readNFCTagId.equals(locNFCTagId)){
					String formatTime = new SimpleDateFormat("dd. MMM. yyyy hh:mm").format(tagread.getReadTime());
					listData.add("ID: " + locNFCTagId + " Location: "
					+ tagloc.getLocation() + "\n" + "Readtime: " + formatTime);
				}
			}
		}
		
		Collections.reverse(listData);
		
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this
				.getActivity().getApplicationContext(), R.layout.listview_tags,
                R.id.tvListViewItem, listData);
		
		
		ListView tags = (ListView) mRootView
				.findViewById(R.id.fragmentAdminWays);
		tags.setAdapter(arrayAdapter);
		
		Button mEditTag;
		mEditTag = (Button) mRootView
				.findViewById(R.id.fragmentAdminButtonEditTags);
		mEditTag.setOnClickListener(new OnClickListener() {

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
