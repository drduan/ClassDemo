package neusoft.duanxudong.com.classdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import neusoft.duanxudong.com.classdemo.R;

/**
 * Created by duanxudong on 15/11/30.
 */
public class DoAnswer extends BaseActivity {


    @Bind(R.id.ans_submit)
    Button do_answer_submit;
    @Bind(R.id.ans_cancle)
    Button do_answer_cancel;


    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("回答");

        Intent intent = this.getIntent();
        String ansname = intent.getStringExtra("ansname") + System.nanoTime();
        Toast.makeText(this, ansname, Toast.LENGTH_LONG).show();


        do_answer_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                JSONObject ans = new JSONObject();
                try {
                    ans.put("id", "id");
                    ans.put("answer", "answer");
                } catch (JSONException e) {
                    e.printStackTrace();


                }
            }

        });


    }
}
