package com.nightguard.nfc.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nightguard.nfc.R;

@SuppressLint("ValidFragment")
public class LoginFragment extends Fragment {

	public static final String TAG = "com.nightguard.nfc.fragments.LoginFragment";

	private static final String PASSWORD = "bosshaft";
	
	View mRootView;
	LoginListener mLoginListener;

	EditText mEtPassword;
	Button mBtnLogin;
	
	public LoginFragment() {
	}

	public LoginFragment(LoginListener loginListener) {
		mLoginListener = loginListener;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mRootView = inflater.inflate(R.layout.fragment_login, container, false);
		mEtPassword=(EditText) mRootView.findViewById(R.id.fragmentLoginPasswordField);
		mBtnLogin = (Button) mRootView.findViewById(R.id.fragmentLoginButton);
		mBtnLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String password = mEtPassword.getText().toString();
				if (PASSWORD.equals(password)) {
					mLoginListener.onSuccessfulLogin();
				}
				else {
					Toast.makeText(getActivity().getApplicationContext(), "Wrong password!", Toast.LENGTH_SHORT).show();
				}
			}
		});
		return mRootView;
	}

	public interface LoginListener {
		public void onSuccessfulLogin();
	}

	public void onLoginClicked(View p0) {
		String password = mEtPassword.getText().toString();
		if (PASSWORD.equals(password)) {
			mLoginListener.onSuccessfulLogin();
		}		
	}
}
