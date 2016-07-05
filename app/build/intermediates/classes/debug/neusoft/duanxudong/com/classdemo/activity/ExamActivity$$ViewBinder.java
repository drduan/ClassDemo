// Generated code from Butter Knife. Do not modify!
package neusoft.duanxudong.com.classdemo.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ExamActivity$$ViewBinder<T extends neusoft.duanxudong.com.classdemo.activity.ExamActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131624341, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131624341, "field 'toolbar'");
    view = finder.findRequiredView(source, 2131624086, "field 'mListView'");
    target.mListView = finder.castView(view, 2131624086, "field 'mListView'");
  }

  @Override public void unbind(T target) {
    target.toolbar = null;
    target.mListView = null;
  }
}
