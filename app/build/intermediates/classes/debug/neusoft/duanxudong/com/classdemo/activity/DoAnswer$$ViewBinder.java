// Generated code from Butter Knife. Do not modify!
package neusoft.duanxudong.com.classdemo.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class DoAnswer$$ViewBinder<T extends neusoft.duanxudong.com.classdemo.activity.DoAnswer> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131624074, "field 'do_answer_submit'");
    target.do_answer_submit = finder.castView(view, 2131624074, "field 'do_answer_submit'");
    view = finder.findRequiredView(source, 2131624075, "field 'do_answer_cancel'");
    target.do_answer_cancel = finder.castView(view, 2131624075, "field 'do_answer_cancel'");
    view = finder.findRequiredView(source, 2131624341, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131624341, "field 'toolbar'");
  }

  @Override public void unbind(T target) {
    target.do_answer_submit = null;
    target.do_answer_cancel = null;
    target.toolbar = null;
  }
}
