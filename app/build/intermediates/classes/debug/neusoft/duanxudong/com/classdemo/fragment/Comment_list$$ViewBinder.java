// Generated code from Butter Knife. Do not modify!
package neusoft.duanxudong.com.classdemo.fragment;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class Comment_list$$ViewBinder<T extends neusoft.duanxudong.com.classdemo.fragment.Comment_list> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131624173, "field 'comment_list'");
    target.comment_list = finder.castView(view, 2131624173, "field 'comment_list'");
    view = finder.findRequiredView(source, 2131624174, "field 'comment_edit'");
    target.comment_edit = finder.castView(view, 2131624174, "field 'comment_edit'");
    view = finder.findRequiredView(source, 2131624175, "method 'send'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.send(p0);
        }
      });
  }

  @Override public void unbind(T target) {
    target.comment_list = null;
    target.comment_edit = null;
  }
}
