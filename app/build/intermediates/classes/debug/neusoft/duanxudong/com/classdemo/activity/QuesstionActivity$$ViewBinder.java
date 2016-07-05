// Generated code from Butter Knife. Do not modify!
package neusoft.duanxudong.com.classdemo.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class QuesstionActivity$$ViewBinder<T extends neusoft.duanxudong.com.classdemo.activity.QuesstionActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131624132, "field 'monikaoshi'");
    target.monikaoshi = finder.castView(view, 2131624132, "field 'monikaoshi'");
    view = finder.findRequiredView(source, 2131624131, "field 'cuotilianxi'");
    target.cuotilianxi = finder.castView(view, 2131624131, "field 'cuotilianxi'");
    view = finder.findRequiredView(source, 2131624130, "field 'simu_kaoshi'");
    target.simu_kaoshi = finder.castView(view, 2131624130, "field 'simu_kaoshi'");
    view = finder.findRequiredView(source, 2131624341, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131624341, "field 'toolbar'");
  }

  @Override public void unbind(T target) {
    target.monikaoshi = null;
    target.cuotilianxi = null;
    target.simu_kaoshi = null;
    target.toolbar = null;
  }
}
