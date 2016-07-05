package neusoft.duanxudong.com.classdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;
import neusoft.duanxudong.com.classdemo.R;
import neusoft.duanxudong.com.classdemo.model.Answer;
import neusoft.duanxudong.com.classdemo.util.LoggingUtils;

/**
 * Created by duanxudong on 15/11/27.
 */
public class AskItemMore extends BaseActivity {


//     Set<Answer> A_set;

    @Bind(R.id.item_more_askname)
    TextView asktitle;
    @Bind(R.id.ianswer)
    Button IAnswer;

    ListView ansList;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.likeLayout)
    LinearLayout likelayout;

    @Bind(R.id.likePic)
    ImageView likepic;
    long ask_id;
    boolean a ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ask_item_more);


        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent intent = getIntent();
        String ask_name = intent.getStringExtra("ask_content");

        ask_id = intent.getLongExtra("ask_id", 0);


        asktitle.setText(ask_name);


        a = false;
        likelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (a) {
                    LoggingUtils.debug("@@",a+"true");
                    likepic.setImageResource(R.drawable.module_treehole_like_true);
                    a = false;



                } else {
                    LoggingUtils.debug("@@",a+"false");
                    likepic.setImageResource(R.drawable.module_treehole_like_normal);
                    a = true;
                }

            }
        });



        IAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent1 = new Intent();
                intent1.setClass(AskItemMore.this,DoAnswer.class);
                startActivity(intent1);
            }
        });
    }



}
