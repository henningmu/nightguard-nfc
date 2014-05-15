// Generated code from Butter Knife. Do not modify!
package com.nightguard.nfc.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class MainFragment$$ViewInjector {
  public static void inject(Finder finder, final com.nightguard.nfc.fragments.MainFragment target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131165206, "field 'mTvTimeSinceLastScan'");
    target.mTvTimeSinceLastScan = (android.widget.TextView) view;
    view = finder.findRequiredView(source, 2131165205, "field 'mTvLastScan'");
    target.mTvLastScan = (android.widget.TextView) view;
  }

  public static void reset(com.nightguard.nfc.fragments.MainFragment target) {
    target.mTvTimeSinceLastScan = null;
    target.mTvLastScan = null;
  }
}
