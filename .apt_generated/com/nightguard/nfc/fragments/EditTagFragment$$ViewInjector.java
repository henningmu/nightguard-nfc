// Generated code from Butter Knife. Do not modify!
package com.nightguard.nfc.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class EditTagFragment$$ViewInjector {
  public static void inject(Finder finder, final com.nightguard.nfc.fragments.EditTagFragment target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131165194, "field 'mEtTagPlace'");
    target.mEtTagPlace = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131165196, "field 'mTvSerialNumber'");
    target.mTvSerialNumber = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131165192, "field 'mEtTagName'");
    target.mEtTagName = (android.widget.EditText) view;
    view = finder.findRequiredView(source, 2131165197, "method 'onGenerateNewSerialNumber'");
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.onGenerateNewSerialNumber(p0);
        }
      });
    view = finder.findRequiredView(source, 2131165199, "method 'saveDataOnNfcTag'");
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.saveDataOnNfcTag(p0);
        }
      });
  }

  public static void reset(com.nightguard.nfc.fragments.EditTagFragment target) {
    target.mEtTagPlace = null;
    target.mTvSerialNumber = null;
    target.mEtTagName = null;
  }
}
