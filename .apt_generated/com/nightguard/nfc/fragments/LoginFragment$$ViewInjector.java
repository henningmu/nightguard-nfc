// Generated code from Butter Knife. Do not modify!
package com.nightguard.nfc.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class LoginFragment$$ViewInjector {
  public static void inject(Finder finder, final com.nightguard.nfc.fragments.LoginFragment target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131165202, "field 'mEtPassword'");
    target.mEtPassword = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131165203, "method 'onLoginClicked'");
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.onLoginClicked(p0);
        }
      });
  }

  public static void reset(com.nightguard.nfc.fragments.LoginFragment target) {
    target.mEtPassword = null;
  }
}
