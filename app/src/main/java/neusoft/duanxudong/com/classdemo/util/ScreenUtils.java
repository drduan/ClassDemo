package neusoft.duanxudong.com.classdemo.util;


import neusoft.duanxudong.com.classdemo.activity.DCApplication;

/**
 * Created by Leelit on 2016/3/17.
 */
public class ScreenUtils {
    public static int dp2px(float dp) {
        float scale = DCApplication.context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
