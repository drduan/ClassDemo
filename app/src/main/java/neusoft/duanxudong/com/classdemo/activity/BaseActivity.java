package neusoft.duanxudong.com.classdemo.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.lang.reflect.Method;
import java.util.Stack;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import neusoft.duanxudong.com.classdemo.R;
import neusoft.duanxudong.com.classdemo.util.LoggingUtils;
import neusoft.duanxudong.com.classdemo.util.PreferenceManager;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


/**
 * Created by duanxudong on 16/3/26.
 */
public class BaseActivity extends AppCompatActivity {

    private PreferenceManager preferenceManager;

    private Stack<Object> mDialogs = new java.util.Stack<Object>();

    private Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    private View mActionBarView;


    public static void hideSoftInput(Activity activity) {
        View view = activity.getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) activity
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    public PreferenceManager getPreferenceManager() {
        if (preferenceManager != null) {
            return preferenceManager;
        }

        return preferenceManager = PreferenceManager.getInstance(getApplicationContext());
    }


    public static void loadRoundImage(Context context, String url, ImageView imageView) {

        if (imageView == null || TextUtils.isEmpty(url)) {
            return;
        }


        Glide.with(context).load(url)

                .bitmapTransform(new CropCircleTransformation(context))
                .placeholder(R.drawable.iconfont_login)
                .crossFade()
                .error(R.drawable.iconfont_login)
                .into(imageView);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (this.getThemeId() == R.style.AppTheme) {
//
//            this.setTheme(R.style.AppTheme_DayNight);
//
//        }


        preferenceManager = PreferenceManager.getInstance(getApplicationContext());
        initSystemBarTint(true);


    }


    int getThemeId() {
        try {
            Class<?> wrapper = Context.class;
            Method method = wrapper.getMethod("getThemeResId");
            method.setAccessible(true);
            return (Integer) method.invoke(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;

    }


    public void log(Object obj) {

        // You can use filter *** to filter out message
        LoggingUtils.error(getClass().getName(),
                String.format("*** %s ***",
                        obj == null ? "--!--"
                                : obj.toString()));
    }


    public View getActionBarView() {
        return mActionBarView;
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


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void showOptionsDialog(String title, String[] options,
                                  DialogInterface.OnClickListener listener) {


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


//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//
//    }
}
