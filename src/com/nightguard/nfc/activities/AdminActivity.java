package com.nightguard.nfc.activities;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentFilter.MalformedMimeTypeException;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.nightguard.nfc.R;
import com.nightguard.nfc.constants.MimeType;
import com.nightguard.nfc.fragments.AdminFragment;
import com.nightguard.nfc.fragments.AdminFragment.AdminListener;
import com.nightguard.nfc.fragments.EditTagFragment;

public class AdminActivity extends FragmentActivity implements AdminListener {

	public static final String TAG = "com.nightguard.nfc.activities.AdminActivity";
	
	NfcAdapter mNfcAdapter;
	AdminFragment mAdminFragment;
	EditTagFragment mEditTagFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin);

		mAdminFragment = new AdminFragment(this);
		mEditTagFragment = new EditTagFragment();
		mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
		
		getSupportFragmentManager()
				.beginTransaction()
				.add(R.id.activityAdminContentFrame, mAdminFragment,
						AdminFragment.TAG).commit();
		
		handleIntent(getIntent());
	}

	@Override
	public void onResume() {
		super.onResume();
		setupForegroundDispatch();
	}

	@Override
	protected void onPause() {
		super.onPause();
		stopForegroundDispatch();
	}

	@Override
	protected void onNewIntent(Intent intent) {
		handleIntent(intent);
	}
	
	@Override
	public void onEditTags() {
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.activityAdminContentFrame, mEditTagFragment)
				.addToBackStack(EditTagFragment.TAG).commit();
	}
	
	private void handleIntent(Intent intent) {
		String action = intent.getAction();
		if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {
			String type = intent.getType();
			if (MimeType.MIME_TYPE.equals(type)) {
				Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
				
				mEditTagFragment.handleTag(tag);
			} else {
				Log.d(TAG, "Wrong mime type: " + type);
			}
		}
	}
	
	private void stopForegroundDispatch() {
		mNfcAdapter.disableForegroundDispatch(this);
	}

	private void setupForegroundDispatch() {
		Intent intent = new Intent(this.getApplicationContext(),
				this.getClass());
		intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

		PendingIntent pendingIntent = PendingIntent.getActivity(
				this.getApplicationContext(), 0, intent, 0);

		IntentFilter[] filters = new IntentFilter[1];
		String[][] techList = new String[][] {};

		// Notice that this is the same filter as in our manifest.
		filters[0] = new IntentFilter();
		filters[0].addAction(NfcAdapter.ACTION_NDEF_DISCOVERED);
		filters[0].addCategory(Intent.CATEGORY_DEFAULT);
		try {
			filters[0].addDataType(MimeType.MIME_TYPE);
		} catch (MalformedMimeTypeException e) {
			throw new RuntimeException("Check your mime type.");
		}

		mNfcAdapter.enableForegroundDispatch(this, pendingIntent, filters,
				techList);
	}
}
