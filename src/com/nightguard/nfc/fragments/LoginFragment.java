package com.nightguard.nfc.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import com.nightguard.nfc.R;

@SuppressLint("ValidFragment")
public class LoginFragment extends Fragment {

	public static final String TAG = "com.nightguard.nfc.fragments.LoginFragment";

	private static final String PASSWORD = "bosshaft";
	
	View mRootView;
	LoginListener mLoginListener;

	@InjectView(R.id.fragmentLoginPasswordField)
	EditText mEtPassword;

	public LoginFragment() {
	}

	public LoginFragment(LoginListener loginListener) {
		mLoginListener = loginListener;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mRootView = inflater.inflate(R.layout.fragment_login, container, false);

		ButterKnife.inject(this, mRootView);

		return mRootView;
	}

	@OnClick(R.id.fragmentLoginButton)
	public void onLoginClicked(View v) {
		String password = mEtPassword.getText().toString();
		if (PASSWORD.equals(password)) {
			mLoginListener.onSuccessfulLogin();
		}
	}

	public interface LoginListener {
		public void onSuccessfulLogin();
	}
}
