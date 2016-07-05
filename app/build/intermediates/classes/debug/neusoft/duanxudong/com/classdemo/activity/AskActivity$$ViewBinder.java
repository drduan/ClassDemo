// Generated code from Butter Knife. Do not modify!
package neusoft.duanxudong.com.classdemo.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class AskActivity$$ViewBinder<T extends neusoft.duanxudong.com.classdemo.activity.AskActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131624081, "field 'mListView'");
    target.mListView = finder.castView(view, 2131624081, "field 'mListView'");
    view = finder.findRequiredView(source, 2131624080, "field 'swiperefresh'");
    target.swiperefresh = finder.castView(view, 2131624080, "field 'swiperefresh'");
    view = finder.findRequiredView(source, 2131624157, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131624157, "field 'toolbar'");
    view = finder.findRequiredView(source, 2131624158, "field 'center_tv'");
    target.center_tv = finder.castView(view, 2131624158, "field 'center_tv'");
    view = finder.findRequiredView(source, 2131624159, "method 'ask_UI'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.ask_UI(p0);
        }
      });
  }

  @Override public void unbind(T target) {
    target.mListView = null;
    target.swiperefresh = null;
    target.toolbar = null;
    target.center_tv = null;
  }
}
