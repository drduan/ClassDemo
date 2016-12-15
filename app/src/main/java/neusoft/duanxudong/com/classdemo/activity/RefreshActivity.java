package neusoft.duanxudong.com.classdemo.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

/**
 * Created by lisheng on 2016/3/28.
 */
public abstract class RefreshActivity extends BaseActivity {
    public String[] titles;
    public Bitmap[] bitmaps;
    public Handler handler;

    public abstract void initViews();

    public abstract void initData();

    public abstract void volleyRequest();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        volleyRequest();
        initData();
        initViews();

    }
}
