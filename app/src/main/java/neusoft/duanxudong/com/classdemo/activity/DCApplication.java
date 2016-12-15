package neusoft.duanxudong.com.classdemo.activity;

import android.app.Application;
import android.content.Context;

import com.liulishuo.filedownloader.FileDownloader;

import neusoft.duanxudong.com.classdemo.R;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


/**
 * Created by duanxudong on 16/3/30.
 */
public class DCApplication extends Application {


    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/NotoSans-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        FileDownloader.init(getApplicationContext());


    }


}

