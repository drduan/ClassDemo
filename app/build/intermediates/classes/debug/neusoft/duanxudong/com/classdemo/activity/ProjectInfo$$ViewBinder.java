// Generated code from Butter Knife. Do not modify!
package neusoft.duanxudong.com.classdemo.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ProjectInfo$$ViewBinder<T extends neusoft.duanxudong.com.classdemo.activity.ProjectInfo> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131624341, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131624341, "field 'toolbar'");
    view = finder.findRequiredView(source, 2131624128, "field 'webView'");
    target.webView = finder.castView(view, 2131624128, "field 'webView'");
  }

  @Override public void unbind(T target) {
    target.toolbar = null;
    target.webView = null;
  }
}
