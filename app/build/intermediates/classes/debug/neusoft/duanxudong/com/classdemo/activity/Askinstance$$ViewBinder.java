// Generated code from Butter Knife. Do not modify!
package neusoft.duanxudong.com.classdemo.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class Askinstance$$ViewBinder<T extends neusoft.duanxudong.com.classdemo.activity.Askinstance> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131624076, "field 'edit_ask_title'");
    target.edit_ask_title = finder.castView(view, 2131624076, "field 'edit_ask_title'");
    view = finder.findRequiredView(source, 2131624077, "field 'edit_ask_content'");
    target.edit_ask_content = finder.castView(view, 2131624077, "field 'edit_ask_content'");
    view = finder.findRequiredView(source, 2131624078, "field 'submit' and method 'onClick'");
    target.submit = finder.castView(view, 2131624078, "field 'submit'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131624079, "field 'cancle' and method 'onClick'");
    target.cancle = finder.castView(view, 2131624079, "field 'cancle'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(p0);
        }
      });
    view = finder.findRequiredView(source, 2131624341, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131624341, "field 'toolbar'");
  }

  @Override public void unbind(T target) {
    target.edit_ask_title = null;
    target.edit_ask_content = null;
    target.submit = null;
    target.cancle = null;
    target.toolbar = null;
  }
}
