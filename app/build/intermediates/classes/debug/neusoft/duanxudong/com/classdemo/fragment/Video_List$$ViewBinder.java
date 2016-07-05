// Generated code from Butter Knife. Do not modify!
package neusoft.duanxudong.com.classdemo.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class Video_List$$ViewBinder<T extends neusoft.duanxudong.com.classdemo.fragment.Video_List> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131624405, "field 'liview'");
    target.liview = finder.castView(view, 2131624405, "field 'liview'");
  }

  @Override public void unbind(T target) {
    target.liview = null;
  }
}
