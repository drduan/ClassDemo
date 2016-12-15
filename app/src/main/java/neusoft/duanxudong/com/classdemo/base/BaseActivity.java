package neusoft.duanxudong.com.classdemo.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.Stack;

import neusoft.duanxudong.com.classdemo.R;


/**
 * Provide basic func for all activities used in App
 * <p/>
 * Created by on 9/12/15.
 */
public class BaseActivity extends AppCompatActivity {

    private CustomProgressDialog mProgress;
    private PreferenceManager preferenceManager;

    private View mActionBarView;
    private Stack<Object> mDialogs = new Stack<Object>();
    // Global view click listener
//    private View.OnClickListener onClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            onUserInteraction(view);
//        }
//    };
//
//    public static void loadRoundImage(Context context, String url, ImageView imageView) {
//
//        if (imageView == null || TextUtils.isEmpty(url)) {
//            return;
//        }
//
//        Transformation transformation = new RoundedTransformationBuilder().borderColor(Color.WHITE)
//                .borderWidth(2)
//                .cornerRadiusDp(40)
//                .oval(false)
//                .build();
//        int profileAvatarSize =
//                Math.round(
//                        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50,
//                                context.getResources().getDisplayMetrics()));
//
//        Picasso.with(context)
//                .load(url)
//                .resize(profileAvatarSize, profileAvatarSize)
//                .centerCrop()
//                .placeholder(R.drawable.ic_tab_button_me)
//                .error(R.drawable.ic_tab_button_me)
//                .transform(transformation)
//                .into(imageView);
//    }
//
//    public View getActionBarView() {
//        return mActionBarView;
//    }
//
//    public PreferenceManager getPreferenceManager() {
//        if (preferenceManager != null) {
//            return preferenceManager;
//        }
//
//        return preferenceManager = PreferenceManager.getInstance(getApplicationContext());
//    }


//    public void setCustomActionBar(View actionBarView) {
//
//        Toolbar toolbar = (Toolbar) findViewById(R.id.tl_custom);
//
//
//        if (toolbar != null) {
//
//            toolbar.setContentInsetsAbsolute(0, 0);
//            setSupportActionBar(toolbar);
//            toolbar.setBackgroundResource(R.drawable.shape_theme);
//
//            {
//                ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
//                        ActionBar.LayoutParams.MATCH_PARENT);
//                layoutParams.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER_HORIZONTAL;
//                this.mActionBarView = actionBarView;
//
//                actionBarView.findViewById(R.id.action_bar_back).setOnClickListener(getViewClickListener());
//                actionBarView.findViewById(R.id.action_bar_confirm).setOnClickListener(getViewClickListener());
//
//
//                getSupportActionBar().setCustomView(actionBarView, layoutParams);
//            }
//
//            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//            getSupportActionBar().setDisplayShowCustomEnabled(true);
//
//        }
//
//    }


//    public void showActionBarBackButton(boolean show) {
//
//        if (mActionBarView != null) {
//
//            View backButton = mActionBarView.findViewById(R.id.action_bar_back);
//            if (backButton != null) {
//                backButton.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
//            }
//
//        }
//    }

//    public void setupActionBar() {
//
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        if (toolbar != null) {
//
//            setSupportActionBar(toolbar);
//            toolbar.setBackgroundResource(R.drawable.shape_theme);
//            toolbar.setContentInsetsAbsolute(0, 0);
//
//            {
//                ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
//                        ActionBar.LayoutParams.MATCH_PARENT);
//                layoutParams.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER_HORIZONTAL;
//                View actionBarView = getLayoutInflater().inflate(R.layout.action_bar_title_action, null);
//                this.mActionBarView = actionBarView;
//
//                actionBarView.findViewById(R.id.action_bar_back).setOnClickListener(getViewClickListener());
//                actionBarView.findViewById(R.id.action_bar_confirm).setOnClickListener(getViewClickListener());
//
//
//                getSupportActionBar().setCustomView(actionBarView, layoutParams);
//            }
//
//            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//            getSupportActionBar().setDisplayShowCustomEnabled(true);
//
//        }
//
//    }

//    public void setActionBarConfirmText(CharSequence confirmText) {
//
//        if (mActionBarView != null) {
//            TextView actionBarTitle = (TextView) mActionBarView.findViewById(R.id.action_bar_confirm);
//            actionBarTitle.setText(confirmText);
//            return;
//        }
//    }

    public static void hideSoftInput(Activity activity) {
        View view = activity.getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) activity
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * 设置 Title;
     *
     * @param titleId
     */
    @Override
    public void setTitle(int titleId) {
        this.setTitle(getString(titleId));
    }

    //    /**
//     * 设置 Title；
//     *
//     * @param title
//     */
//    @Override
//    public void setTitle(CharSequence title) {
//        if (mActionBarView != null) {
//            TextView actionBarTitle = (TextView) mActionBarView.findViewById(R.id.action_bar_custom_title);
//            actionBarTitle.setText(title);
//            return;
//        }
//
//        super.setTitle(title);
//    }
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//
//        Notify.Action.register(this);
//        Notify.Data.register(this);
//        Notify.Exception.register(this);
//        Notify.UserSetting.register(this);


//        preferenceManager = PreferenceManager.getInstance(getApplicationContext());

//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

//    @Override
//    protected void onDestroy() {
//
//        super.onDestroy();
//
//        // Unregister event bus
//        Notify.Action.unregister(this);
//        Notify.Data.unregister(this);
//        Notify.Exception.unregister(this);
//        Notify.UserSetting.unregister(this);
//
//    }

    protected void onPause() {
        super.onPause();
    }

    protected void onResume() {
        super.onResume();
    }

//    public View.OnClickListener getViewClickListener() {
//        return onClickListener;
//    }

    /**
     * Central point of handling all view click events
     *
     * @param view
     */
//    public void onUserInteraction(View view) {
//        switch (view.getId()) {
//            default:
//                break;
//
//            case R.id.action_bar_back:
//                onBackPressed();
//                break;
//
//            case R.id.action_bar_confirm:
//                Notify.Action.post(Action.ACTION_BAR_CONFIRM);
//                break;
//        }
//    }

//    public void log(Object obj) {
//
//        // You can use filter *** to filter out message
//        LoggingUtils.error(getClass().getName(),
//                String.format("*** %s ***",
//                        obj == null ? "--!--"
//                                : obj.toString()));
//    }
//
//    /**
//     * Show a inform {@link Toast} message for a short period of time.
//     */
//    public void showDeveloperInformToast(String msg) {
//
//        if (BuildConfig.DEBUG) {
//            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
//        }
//    }

    /**
     * Show a loading bar
     */
//    public synchronized void showLoading() {
//
//        // The dialog is already showing
//        if (this.mProgress != null && this.mProgress.isShowing()) {
//            return;
//        }
//        /*-
//         * If the callback is in the process of finishing, we should not
//         * show the loading dialog.
//         *
//         * see http://developer.android.com/reference
//         * /android/app/Activity.html#isFinishing().
//         */
//        if (mProgress != null && isFinishing()) {
//            return;
//        }
//
//        try {
//            this.mProgress = CustomProgressDialog.buildDialog(this);
//            this.mProgress.setCircleColor(getResources().getColor(R.color.theme_color));
//            this.mProgress.show();
//        } catch (Resources.NotFoundException e) {
//            e.printStackTrace();
//        } catch (Exception allOtherException) {
//            allOtherException.printStackTrace();
//        }
//    }

    /**
     * Dismiss a loading bar
     */
    public synchronized void dismissLoading() {

        if (this.mProgress != null && this.mProgress.isShowing()) {
            this.mProgress.dismiss();
        }
    }

    // ------------------------------------
    // EventBus methods
//    // ------------------------------------
//    public synchronized void onEventMainThread(Action action) {
//
//        switch (action) {
//
//            default:
//                break;
//            case ShowLoading: {
//                showLoading();
//                break;
//            }
//            case DismissLoading: {
//                dismissLoading();
//                break;
//            }

//            case NetWorkDisconnected: {
//
//                Intent toAlert = new Intent(this, NetworkUnavailableActivity.class);
//                toAlert.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                startActivity(toAlert);
//
//                break;
//            }
//        }
//    }


    public void showAlertConfirmDialog(String message,
                                       DialogInterface.OnClickListener listener, String positiveText, String negativeText) {

        // No need to show
        if (isFinishing()) {
            return;
        }

        // Already has one shown, no need to show
        if (!mDialogs.isEmpty()) {
            return;
        }

        AlertDialog.Builder alert = new AlertDialog.Builder(
                this,
                AlertDialog.THEME_HOLO_LIGHT);
        alert.setMessage("\n" + message + "\n");
        alert.setPositiveButton(positiveText, listener);
        alert.setNegativeButton(negativeText, listener);

        mDialogs.add(new Object());
        AlertDialog dialog = alert.create();

        // Reset the dialog counts.
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {

            @Override
            public void onDismiss(DialogInterface dialog) {

                mDialogs.clear();
            }
        });

        // Reset the dialog counts.
        alert.setOnCancelListener(new DialogInterface.OnCancelListener() {

            @Override
            public void onCancel(DialogInterface dialog) {

                mDialogs.clear();
            }
        });

        dialog.show();
    }

    /**
     * Show a list of action options, with title enabled.
     *
     * @param title
     * @param options
     * @param listener
     */
    public void showOptionsDialog(String title, String[] options,
                                  DialogInterface.OnClickListener listener) {

        // No need to show
        if (isFinishing()) {
            return;
        }

        // Already has one shown, no need to show
        if (!mDialogs.isEmpty()) {
            return;
        }

        AlertDialog.Builder alert = new AlertDialog.Builder(
                this
                ,
                AlertDialog.THEME_DEVICE_DEFAULT_LIGHT
        );
        alert.setItems(options, listener);
        alert.setTitle(title);

        mDialogs.add(new Object());
        AlertDialog dialog = alert.create();

        // Reset the dialog counts.
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {

            @Override
            public void onDismiss(DialogInterface dialog) {

                mDialogs.clear();
            }
        });

        dialog.show();

    }

    /**
     * Showing a list of action options, without title enabled.
     *
     * @param options
     * @param listener
     */
    public void showOptionsDialog(String[] options,
                                  DialogInterface.OnClickListener listener) {

        this.showOptionsDialog(null, options, listener);
    }


    public void initSystemBarTint(boolean on) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(on);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(on);
            tintManager.setStatusBarTintColor(getColorPrimary());
        }
    }

    public int getColorPrimary() {
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        return typedValue.data;
    }


    public int getDarkColorPrimary() {
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.colorPrimaryDark, typedValue, true);
        return typedValue.data;
    }


    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

}
