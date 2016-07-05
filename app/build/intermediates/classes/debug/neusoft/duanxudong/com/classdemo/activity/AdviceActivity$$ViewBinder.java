// Generated code from Butter Knife. Do not modify!
package neusoft.duanxudong.com.classdemo.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class AdviceActivity$$ViewBinder<T extends neusoft.duanxudong.com.classdemo.activity.AdviceActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131624139, "field 'input'");
    target.input = finder.castView(view, 2131624139, "field 'input'");
    view = finder.findRequiredView(source, 2131624157, "field 'toolbar' and method 'ask_UI'");
    target.toolbar = finder.castView(view, 2131624157, "field 'toolbar'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.ask_UI(p0);
        }
      });
    view = finder.findRequiredView(source, 2131624158, "field 'center_tv'");
    target.center_tv = finder.castView(view, 2131624158, "field 'center_tv'");
    view = finder.findRequiredView(source, 2131624159, "field 'ask_bar_tv'");
    target.ask_bar_tv = finder.castView(view, 2131624159, "field 'ask_bar_tv'");
    view = finder.findRequiredView(source, 2131624140, "field 'show'");
    target.show = finder.castView(view, 2131624140, "field 'show'");
  }

  @Override public void unbind(T target) {
    target.input = null;
    target.toolbar = null;
    target.center_tv = null;
    target.ask_bar_tv = null;
    target.show = null;
  }
}
