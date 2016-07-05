// Generated code from Butter Knife. Do not modify!
package neusoft.duanxudong.com.classdemo.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class Simu_kaoshi$$ViewBinder<T extends neusoft.duanxudong.com.classdemo.activity.Simu_kaoshi> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131624341, "field 'toolbar'");
    target.toolbar = finder.castView(view, 2131624341, "field 'toolbar'");
    view = finder.findRequiredView(source, 2131624094, "field 'timmer'");
    target.timmer = finder.castView(view, 2131624094, "field 'timmer'");
    view = finder.findRequiredView(source, 2131624096, "field 'btn_next'");
    target.btn_next = finder.castView(view, 2131624096, "field 'btn_next'");
    view = finder.findRequiredView(source, 2131624088, "field 'radioGroup'");
    target.radioGroup = finder.castView(view, 2131624088, "field 'radioGroup'");
    view = finder.findRequiredView(source, 2131624087, "field 'tv_question'");
    target.tv_question = finder.castView(view, 2131624087, "field 'tv_question'");
    view = finder.findRequiredView(source, 2131624093, "field 'tv_explaination'");
    target.tv_explaination = finder.castView(view, 2131624093, "field 'tv_explaination'");
    view = finder.findRequiredView(source, 2131624095, "field 'btn_previous'");
    target.btn_previous = finder.castView(view, 2131624095, "field 'btn_previous'");
    target.radioButtons = Finder.arrayOf(
        finder.<android.widget.RadioButton>findRequiredView(source, 2131624089, "field 'radioButtons'"),
        finder.<android.widget.RadioButton>findRequiredView(source, 2131624090, "field 'radioButtons'"),
        finder.<android.widget.RadioButton>findRequiredView(source, 2131624091, "field 'radioButtons'"),
        finder.<android.widget.RadioButton>findRequiredView(source, 2131624092, "field 'radioButtons'")
    );
  }

  @Override public void unbind(T target) {
    target.toolbar = null;
    target.timmer = null;
    target.btn_next = null;
    target.radioGroup = null;
    target.tv_question = null;
    target.tv_explaination = null;
    target.btn_previous = null;
    target.radioButtons = null;
  }
}
