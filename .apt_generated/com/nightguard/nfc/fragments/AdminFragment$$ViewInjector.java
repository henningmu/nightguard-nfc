// Generated code from Butter Knife. Do not modify!
package com.nightguard.nfc.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class AdminFragment$$ViewInjector {
  public static void inject(Finder finder, final com.nightguard.nfc.fragments.AdminFragment target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131165187, "field 'mLvWays'");
    target.mLvWays = (android.widget.ListView) view;
    view = finder.findRequiredView(source, 2131165189, "method 'onEditTagsClicked'");
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.onEditTagsClicked(p0);
        }
      });
  }

  public static void reset(com.nightguard.nfc.fragments.AdminFragment target) {
    target.mLvWays = null;
  }
}
