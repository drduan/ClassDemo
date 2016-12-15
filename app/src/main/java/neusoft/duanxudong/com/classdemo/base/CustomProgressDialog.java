package neusoft.duanxudong.com.classdemo.base;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import neusoft.duanxudong.com.classdemo.R;


public class CustomProgressDialog extends Dialog {

    private CustomProgressBar mCustomProgressBar = null;

    private boolean isFirstShow = true;

    private CustomProgressDialog(Context context, int theme) {

        super(context, theme);
    }

    public static CustomProgressDialog buildDialog(Context context) {

        CustomProgressDialog dialog = new CustomProgressDialog(
                context,
                R.style.CustomProgressDialog);
        dialog.setContentView(dialog.getContentView(context));
        dialog.setCanceledOnTouchOutside(false);
        // Let the progress bar in the center
        dialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        return dialog;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

        if (hasFocus && isFirstShow) {
            mCustomProgressBar.execute();
            isFirstShow = false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return super.onTouchEvent(event);
    }

    // Draw a default progress bar
    private View getContentView(Context c) {

        LinearLayout linear = new LinearLayout(c);
        linear.setLayoutParams(new LinearLayout.LayoutParams(
                android.view.ViewGroup.LayoutParams.MATCH_PARENT,
                android.view.ViewGroup.LayoutParams.MATCH_PARENT));
        mCustomProgressBar = new CustomProgressBar(c);
        linear.addView(mCustomProgressBar);

        return linear;
    }

    public int getBarLengthRatio() {

        return mCustomProgressBar.getBarLengthRatio();
    }

    public void setBarLengthRatio(int barLengthRatio) {

        mCustomProgressBar.setBarLengthRatio(barLengthRatio);
    }

    public float getSpinSpeed() {

        return mCustomProgressBar.getSpinSpeed();
    }

    public void setSpinSpeed(int spinSpeed) {

        mCustomProgressBar.setSpinSpeed(spinSpeed);
    }

    public float getPadding() {

        return mCustomProgressBar.getPadding();
    }

    public void setPadding(float padding) {

        mCustomProgressBar.setPadding(padding);
    }

    public void setCircleColor(int circleColor) {

        mCustomProgressBar.setCircleColor(circleColor);
    }

    public void setBarColor(int barColor) {

        mCustomProgressBar.setBarColor(barColor);
    }

    public void setProgressMode(int progressMode) {

        mCustomProgressBar.setProgressMode(progressMode);
    }

    public int getProgress() {

        return mCustomProgressBar.getProgress();
    }

    public void setProgress(int progress) {

        mCustomProgressBar.setProgress(progress);
    }

}
