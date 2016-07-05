// Generated code from Butter Knife. Do not modify!
package neusoft.duanxudong.com.classdemo.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ProjectActivity$$ViewBinder<T extends neusoft.duanxudong.com.classdemo.activity.ProjectActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131624085, "field 'ultimateRecyclerView'");
    target.ultimateRecyclerView = finder.castView(view, 2131624085, "field 'ultimateRecyclerView'");
    view = finder.findRequiredView(source, 2131624341, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131624341, "field 'toolbar'");
    view = finder.findRequiredView(source, 2131624176, "field 'fabbtn'");
    target.fabbtn = finder.castView(view, 2131624176, "field 'fabbtn'");
  }

  @Override public void unbind(T target) {
    target.ultimateRecyclerView = null;
    target.toolbar = null;
    target.fabbtn = null;
  }
}
