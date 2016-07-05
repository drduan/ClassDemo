// Generated code from Butter Knife. Do not modify!
package neusoft.duanxudong.com.classdemo.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class CollectionActivity$$ViewBinder<T extends neusoft.duanxudong.com.classdemo.activity.CollectionActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131624083, "field 'CoList'");
    target.CoList = finder.castView(view, 2131624083, "field 'CoList'");
    view = finder.findRequiredView(source, 2131624341, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131624341, "field 'toolbar'");
  }

  @Override public void unbind(T target) {
    target.CoList = null;
    target.toolbar = null;
  }
}
