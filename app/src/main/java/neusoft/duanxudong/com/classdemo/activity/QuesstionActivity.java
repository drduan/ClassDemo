package neusoft.duanxudong.com.classdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;


import butterknife.Bind;
import butterknife.ButterKnife;
import neusoft.duanxudong.com.classdemo.R;


public class QuesstionActivity extends  BaseActivity implements View.OnClickListener {


    @Bind(R.id.monikaoshi)
     LinearLayout monikaoshi;

    @Bind(R.id.cuotilianxi)
     LinearLayout cuotilianxi;

    @Bind(R.id.zhangjielianxi)
     LinearLayout  simu_kaoshi;

    @Bind(R.id.toolbar)
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quesstion);


        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("资源");

        simu_kaoshi.setOnClickListener(this);
        monikaoshi.setOnClickListener(this);
        cuotilianxi.setOnClickListener(this);
    }






    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,Simu_kaoshi.class);
        startActivity(intent);

    }
}
