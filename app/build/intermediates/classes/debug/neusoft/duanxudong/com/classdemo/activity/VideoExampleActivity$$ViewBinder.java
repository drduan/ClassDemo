// Generated code from Butter Knife. Do not modify!
package neusoft.duanxudong.com.classdemo.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class VideoExampleActivity$$ViewBinder<T extends neusoft.duanxudong.com.classdemo.activity.VideoExampleActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131624403, "field 'viewPager'");
    target.viewPager = finder.castView(view, 2131624403, "field 'viewPager'");
    view = finder.findRequiredView(source, 2131624402, "field 'video_tab'");
    target.video_tab = finder.castView(view, 2131624402, "field 'video_tab'");
    view = finder.findRequiredView(source, 2131624341, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131624341, "field 'toolbar'");
    view = finder.findRequiredView(source, 2131624401, "field 'jcVideoPlayerStandard'");
    target.jcVideoPlayerStandard = finder.castView(view, 2131624401, "field 'jcVideoPlayerStandard'");
  }

  @Override public void unbind(T target) {
    target.viewPager = null;
    target.video_tab = null;
    target.toolbar = null;
    target.jcVideoPlayerStandard = null;
  }
}
