// Generated code from Butter Knife. Do not modify!
package neusoft.duanxudong.com.classdemo.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class AskItemMore$$ViewBinder<T extends neusoft.duanxudong.com.classdemo.activity.AskItemMore> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131624141, "field 'asktitle'");
    target.asktitle = finder.castView(view, 2131624141, "field 'asktitle'");
    view = finder.findRequiredView(source, 2131624150, "field 'IAnswer'");
    target.IAnswer = finder.castView(view, 2131624150, "field 'IAnswer'");
    view = finder.findRequiredView(source, 2131624341, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131624341, "field 'toolbar'");
    view = finder.findRequiredView(source, 2131624143, "field 'likelayout'");
    target.likelayout = finder.castView(view, 2131624143, "field 'likelayout'");
    view = finder.findRequiredView(source, 2131624144, "field 'likepic'");
    target.likepic = finder.castView(view, 2131624144, "field 'likepic'");
  }

  @Override public void unbind(T target) {
    target.asktitle = null;
    target.IAnswer = null;
    target.toolbar = null;
    target.likelayout = null;
    target.likepic = null;
  }
}
