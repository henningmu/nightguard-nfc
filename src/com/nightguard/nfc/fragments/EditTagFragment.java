package com.nightguard.nfc.fragments;

import java.io.IOException;

import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.nightguard.nfc.R;
import com.nightguard.nfc.constants.MimeType;

public class EditTagFragment extends Fragment {

	public static final String TAG = "com.nightguard.nfc.fragments.EditTagFragment";

	View mRootView;
	NfcAdapter mAdapter;
	boolean writeRequested;

	@InjectView(R.id.fragmentEditSerialNumber)
	TextView mTvSerialNumber;

	@InjectView(R.id.fragmentEditTagName)
	EditText mEtTagName;

	@InjectView(R.id.fragmentEditTagPlace)
	EditText mEtTagPlace;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mRootView = inflater.inflate(R.layout.fragment_edit_tag, container,
				false);

		ButterKnife.inject(this, mRootView);

		mAdapter = NfcAdapter.getDefaultAdapter(getActivity());
		writeRequested = false;

		return mRootView;
	}

	@OnClick(R.id.fragmentEditButtonGenerateSerialNumber)
	public void onGenerateNewSerialNumber(View v) {

	}

	@OnClick(R.id.fragmentEditButtonSaveToTag)
	public void saveDataOnNfcTag(View v) {
		writeRequested = true;
	}

	public void handleTag(Tag tag) {
		if (writeRequested) {
			byte[] payload = "This is my Test message".getBytes();
			byte[] mimeBytes = MimeType.MIME_TYPE.getBytes();
			NdefRecord cardRecord = new NdefRecord(NdefRecord.TNF_MIME_MEDIA,
					mimeBytes, new byte[0], payload);
			NdefMessage message = new NdefMessage(
					new NdefRecord[] { cardRecord });

			try {
				// see if tag is already NDEF formatted
				Ndef ndef = Ndef.get(tag);
				if (ndef != null) {
					ndef.connect();

					if (!ndef.isWritable()) {
						Log.d(TAG, "Read-only Tag :(");
						return;
					}

					// work out how much space we need for the data
					int size = message.toByteArray().length;
					if (ndef.getMaxSize() < size) {
						Log.d(TAG, "Tag doesn't have enough free space.");
						return;
					}

					ndef.writeNdefMessage(message);
					Log.d(TAG, "Tag written successfully.");
					return;
				} else {
					// attempt to format tag
					NdefFormatable format = NdefFormatable.get(tag);
					if (format != null) {
						try {
							format.connect();
							format.format(message);
							Log.d(TAG,
									"Tag written successfully!\nClose this app and scan tag.");
							return;
						} catch (IOException e) {
							Log.d(TAG, "Unable to format tag to NDEF.");
							return;
						}
					} else {
						Log.d(TAG, "Tag doesn't appear to support NDEF format.");
						return;
					}
				}
			} catch (Exception e) {
				Log.d(TAG, "Failed to write tag");
			}
		}
	}
}
/**
 * Foreground dispat
 * 
 * @Override public void onNewIntent(Intent intent) { Tag tag =
 *           intent.getParcelableExtra(NfcAdapter.EXTRA_TAG); writeData(tag); }
 * 
 * 
 * 
 *           private boolean writeData(Tag tag) { // record that contains our
 *           custom "retro console" game data, using // custom MIME_TYPE byte[]
 *           payload = "Hallo NFC Tag".getBytes(); byte[] mimeBytes =
 *           MIME_TYPE.getBytes(); NdefRecord cardRecord = new
 *           NdefRecord(NdefRecord.TNF_MIME_MEDIA, mimeBytes, new byte[0],
 *           payload); NdefMessage message = new NdefMessage(new NdefRecord[] {
 *           cardRecord });
 * 
 *           try { // see if tag is already NDEF formatted Ndef ndef =
 *           Ndef.get(tag); if (ndef != null) { ndef.connect();
 * 
 *           if (!ndef.isWritable()) { Log.d(TAG, "Read-only Tag :("); return
 *           false; }
 * 
 *           // work out how much space we need for the data int size =
 *           message.toByteArray().length; if (ndef.getMaxSize() < size) {
 *           Log.d(TAG, "Tag doesn't have enough free space."); return false; }
 * 
 *           ndef.writeNdefMessage(message); Log.d(TAG,
 *           "Tag written successfully."); return true; } else { // attempt to
 *           format tag NdefFormatable format = NdefFormatable.get(tag); if
 *           (format != null) { try { format.connect(); format.format(message);
 *           Log.d(TAG,
 *           "Tag written successfully!\nClose this app and scan tag."); return
 *           true; } catch (IOException e) { Log.d(TAG,
 *           "Unable to format tag to NDEF."); return false; } } else {
 *           Log.d(TAG, "Tag doesn't appear to support NDEF format."); return
 *           false; } } } catch (Exception e) { Log.d(TAG,
 *           "Failed to write tag"); }
 * 
 *           return false; }
 * **/
