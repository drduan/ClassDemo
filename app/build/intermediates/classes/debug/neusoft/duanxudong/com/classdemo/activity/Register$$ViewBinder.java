// Generated code from Butter Knife. Do not modify!
package neusoft.duanxudong.com.classdemo.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class Register$$ViewBinder<T extends neusoft.duanxudong.com.classdemo.activity.Register> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131624133, "field '_nameText'");
    target._nameText = finder.castView(view, 2131624133, "field '_nameText'");
    view = finder.findRequiredView(source, 2131624134, "field '_emailText'");
    target._emailText = finder.castView(view, 2131624134, "field '_emailText'");
    view = finder.findRequiredView(source, 2131624135, "field '_passwordText'");
    target._passwordText = finder.castView(view, 2131624135, "field '_passwordText'");
    view = finder.findRequiredView(source, 2131624136, "field '_passwordText2'");
    target._passwordText2 = finder.castView(view, 2131624136, "field '_passwordText2'");
    view = finder.findRequiredView(source, 2131624137, "field '_signupButton'");
    target._signupButton = finder.castView(view, 2131624137, "field '_signupButton'");
    view = finder.findRequiredView(source, 2131624138, "field '_loginLink'");
    target._loginLink = finder.castView(view, 2131624138, "field '_loginLink'");
    view = finder.findRequiredView(source, 2131624341, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131624341, "field 'toolbar'");
  }

  @Override public void unbind(T target) {
    target._nameText = null;
    target._emailText = null;
    target._passwordText = null;
    target._passwordText2 = null;
    target._signupButton = null;
    target._loginLink = null;
    target.toolbar = null;
  }
}
