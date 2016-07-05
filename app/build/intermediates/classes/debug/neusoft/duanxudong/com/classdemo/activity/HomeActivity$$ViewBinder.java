// Generated code from Butter Knife. Do not modify!
package neusoft.duanxudong.com.classdemo.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class HomeActivity$$ViewBinder<T extends neusoft.duanxudong.com.classdemo.activity.HomeActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131624097, "field 'mdrawerLayout'");
    target.mdrawerLayout = finder.castView(view, 2131624097, "field 'mdrawerLayout'");
    view = finder.findRequiredView(source, 2131624341, "field 'mToolbar'");
    target.mToolbar = finder.castView(view, 2131624341, "field 'mToolbar'");
    view = finder.findRequiredView(source, 2131624099, "field 'flashView'");
    target.flashView = finder.castView(view, 2131624099, "field 'flashView'");
    view = finder.findRequiredView(source, 2131624109, "field 'navigationView'");
    target.navigationView = finder.castView(view, 2131624109, "field 'navigationView'");
  }

  @Override public void unbind(T target) {
    target.mdrawerLayout = null;
    target.mToolbar = null;
    target.flashView = null;
    target.navigationView = null;
  }
}
