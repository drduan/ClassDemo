package neusoft.duanxudong.com.classdemo.util.theme;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import neusoft.duanxudong.com.classdemo.R;


/**
 * Created by sunfusheng on 15/8/10.
 */
public class ThemeUtil {

    public static void changeTheme(Activity activity, ColorEnum theme) {
        if (activity == null)
//            Log.i("","");
            return;
        int style;
        switch (theme) {

        }

//        activity.setTheme(style);
        Log.i("@@Theme","成功测试");
    }

    public static void changeTheme(Activity activity) {
        changeTheme(activity, getCurrentColorEnum(activity));
    }

    public static ColorEnum getCurrentColorEnum(Context context){

        return ColorEnum.BLUE;
    }

}
