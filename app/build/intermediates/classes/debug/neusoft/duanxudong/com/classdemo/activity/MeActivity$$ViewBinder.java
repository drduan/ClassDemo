// Generated code from Butter Knife. Do not modify!
package neusoft.duanxudong.com.classdemo.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MeActivity$$ViewBinder<T extends neusoft.duanxudong.com.classdemo.activity.MeActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131624330, "field 'head_image'");
    target.head_image = finder.castView(view, 2131624330, "field 'head_image'");
    view = finder.findRequiredView(source, 2131624332, "field 'nicheng'");
    target.nicheng = finder.castView(view, 2131624332, "field 'nicheng'");
    view = finder.findRequiredView(source, 2131624334, "field 'passwd'");
    target.passwd = finder.castView(view, 2131624334, "field 'passwd'");
    view = finder.findRequiredView(source, 2131624335, "field 'phone'");
    target.phone = finder.castView(view, 2131624335, "field 'phone'");
    view = finder.findRequiredView(source, 2131624336, "field 'grade'");
    target.grade = finder.castView(view, 2131624336, "field 'grade'");
    view = finder.findRequiredView(source, 2131624157, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131624157, "field 'toolbar'");
    view = finder.findRequiredView(source, 2131624158, "field 'center_tv'");
    target.center_tv = finder.castView(view, 2131624158, "field 'center_tv'");
    view = finder.findRequiredView(source, 2131624159, "field 'bar_add'");
    target.bar_add = finder.castView(view, 2131624159, "field 'bar_add'");
  }

  @Override public void unbind(T target) {
    target.head_image = null;
    target.nicheng = null;
    target.passwd = null;
    target.phone = null;
    target.grade = null;
    target.toolbar = null;
    target.center_tv = null;
    target.bar_add = null;
  }
}
