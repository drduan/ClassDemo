// Generated code from Butter Knife. Do not modify!
package neusoft.duanxudong.com.classdemo.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class Activity_Post$$ViewBinder<T extends neusoft.duanxudong.com.classdemo.activity.Activity_Post> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131624123, "field 'mAddImageButton'");
    target.mAddImageButton = finder.castView(view, 2131624123, "field 'mAddImageButton'");
    view = finder.findRequiredView(source, 2131624124, "field 'mImageLayout'");
    target.mImageLayout = finder.castView(view, 2131624124, "field 'mImageLayout'");
    view = finder.findRequiredView(source, 2131624126, "field 'mDeleteImageButton'");
    target.mDeleteImageButton = finder.castView(view, 2131624126, "field 'mDeleteImageButton'");
    view = finder.findRequiredView(source, 2131624120, "field 'time_ac'");
    target.time_ac = finder.castView(view, 2131624120, "field 'time_ac'");
    view = finder.findRequiredView(source, 2131624122, "field 'mState'");
    target.mState = finder.castView(view, 2131624122, "field 'mState'");
    view = finder.findRequiredView(source, 2131624341, "field 'mToolbar'");
    target.mToolbar = finder.castView(view, 2131624341, "field 'mToolbar'");
    view = finder.findRequiredView(source, 2131624125, "field 'mImageView'");
    target.mImageView = finder.castView(view, 2131624125, "field 'mImageView'");
  }

  @Override public void unbind(T target) {
    target.mAddImageButton = null;
    target.mImageLayout = null;
    target.mDeleteImageButton = null;
    target.time_ac = null;
    target.mState = null;
    target.mToolbar = null;
    target.mImageView = null;
  }
}
