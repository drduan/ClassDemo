package neusoft.duanxudong.com.classdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import neusoft.duanxudong.com.classdemo.R;
import neusoft.duanxudong.com.classdemo.util.AnimationUtil;

/**
 * Created by duanxudong on 15/11/24.
 */


public class LeadActivity extends AppCompatActivity {

    private static final long DELAY_TIME = 500L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_lead);


        redirectByTime();

    }


    private void redirectByTime() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(LeadActivity.this, HomeActivity.class));
                AnimationUtil.finishActivityAnimation(LeadActivity.this);

            }
        }, DELAY_TIME);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
