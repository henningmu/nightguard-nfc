package com.nightguard.nfc.activities;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentFilter.MalformedMimeTypeException;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.nightguard.nfc.R;
import com.nightguard.nfc.constants.MimeType;
import com.nightguard.nfc.dao.NFCTagLocationDAO;
import com.nightguard.nfc.dao.NFCTagReadTimeDAO;
import com.nightguard.nfc.fragments.DetailsFragment;
import com.nightguard.nfc.fragments.LoginFragment;
import com.nightguard.nfc.fragments.LoginFragment.LoginListener;
import com.nightguard.nfc.fragments.MainFragment;

public class MainActivity extends FragmentActivity implements LoginListener {

	public static final String TAG = "com.nightguard.nfc.activities.MainActivity";

	NfcAdapter mNfcAdapter;
	SectionsPagerAdapter mSectionsPagerAdapter;
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mNfcAdapter = NfcAdapter.getDefaultAdapter(this);

		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

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

	private void handleIntent(Intent intent) {
		String action = intent.getAction();
		if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {
			String type = intent.getType();
			if (MimeType.MIME_TYPE.equals(type)) {
				Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
				
				NFCTagLocationDAO writeLocData=new NFCTagLocationDAO(this);
				NFCTagReadTimeDAO writeTimData=new NFCTagReadTimeDAO(this);
				((MainFragment) mSectionsPagerAdapter.getItem(0)).onNewTag(tag, writeTimData, writeLocData);
			} else {
				Log.d(TAG, "Wrong mime type: " + type);
			}
		}
	}

//	private void readDataFromTag(Tag tag) {
//		Ndef ndef = Ndef.get(tag);
//		if (ndef == null) {
//			Log.d(TAG, "NDEF is not supported by this Tag.");
//			return;
//		}
//
//		NdefMessage ndefMessage = ndef.getCachedNdefMessage();
//
//		NdefRecord[] records = ndefMessage.getRecords();
//		for (NdefRecord ndefRecord : records) {
//			try {
//				// Read text
//				byte[] payload = ndefRecord.getPayload();
//				// Get the Text Encoding
//				String textEncoding = ((payload[0] & 128) == 0) ? "UTF-8"
//						: "UTF-16";
//				// Get the Language Code
//				int languageCodeLength = payload[0] & 0063;
//				// Get the Text
//				String text = new String(payload, languageCodeLength + 1,
//						payload.length - languageCodeLength - 1, textEncoding);
//				Log.d(TAG, text);
//			} catch (UnsupportedEncodingException e) {
//				Log.e(TAG, "Unsupported Encoding", e);
//			}
//		}
//	}

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

	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			switch (position) {
			case 0:
				MainFragment mainFragment = new MainFragment();
				return mainFragment;
			case 1:
				DetailsFragment detailsFragment = new DetailsFragment();
				return detailsFragment;
			case 2:
				LoginFragment loginFragment = new LoginFragment(MainActivity.this);
				return loginFragment;
			default:
				return null;
			}
		}

		@Override
		public int getCount() {
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			switch (position) {
			case 0:
				return getString(R.string.titleMainSection);
			case 1:
				return getString(R.string.titleDetailSection);
			case 2:
				return getString(R.string.titleAdminSection);
			}
			return null;
		}
	}

	@Override
	public void onSuccessfulLogin() {
		Intent intent = new Intent(this, AdminActivity.class);
		startActivity(intent);
	}
}
