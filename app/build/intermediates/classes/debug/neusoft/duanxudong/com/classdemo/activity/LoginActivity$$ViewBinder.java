// Generated code from Butter Knife. Do not modify!
package neusoft.duanxudong.com.classdemo.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class LoginActivity$$ViewBinder<T extends neusoft.duanxudong.com.classdemo.activity.LoginActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131624114, "field 'email'");
    target.email = finder.castView(view, 2131624114, "field 'email'");
    view = finder.findRequiredView(source, 2131624116, "field 'passwd'");
    target.passwd = finder.castView(view, 2131624116, "field 'passwd'");
    view = finder.findRequiredView(source, 2131624341, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131624341, "field 'toolbar'");
  }

  @Override public void unbind(T target) {
    target.email = null;
    target.passwd = null;
    target.toolbar = null;
  }
}
