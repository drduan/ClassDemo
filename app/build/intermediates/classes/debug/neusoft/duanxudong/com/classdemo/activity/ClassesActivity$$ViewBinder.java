// Generated code from Butter Knife. Do not modify!
package neusoft.duanxudong.com.classdemo.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ClassesActivity$$ViewBinder<T extends neusoft.duanxudong.com.classdemo.activity.ClassesActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131624395, "field 'searchView'");
    target.searchView = finder.castView(view, 2131624395, "field 'searchView'");
  }

  @Override public void unbind(T target) {
    target.searchView = null;
  }
}
