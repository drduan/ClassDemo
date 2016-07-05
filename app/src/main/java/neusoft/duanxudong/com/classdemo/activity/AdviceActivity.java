package neusoft.duanxudong.com.classdemo.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import neusoft.duanxudong.com.classdemo.R;
import neusoft.duanxudong.com.classdemo.model.User;
import neusoft.duanxudong.com.classdemo.util.LoggingUtils;


/**
 * Created by duanxudong on 16/3/5 17:52
 */
public class AdviceActivity extends BaseActivity {

    @Bind(R.id.advice_edit)
    EditText input;

    @Bind(R.id.ask_add)
    Toolbar toolbar;


    @Bind(R.id.center_tv)
    TextView center_tv;
    @Bind(R.id.ask_bar_tv)
    TextView ask_bar_tv;

    @Bind(R.id.textView_show)
    TextView show;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activuty_advice);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);


        center_tv.setOnClickListener(null);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        center_tv.setText("评价反馈");
        ask_bar_tv.setText("提交");


        input.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {


                if (s.length() == 0) {
                    show.setText("意见反馈");
                    return;
                }

                show.setText(String.format("意见反馈 (%s/200)",s.length()));

            }
        });
    }


    @OnClick(R.id.ask_add)
    void ask_UI(View v) {


        asyncFeedback();


    }

    void asyncFeedback() {

        if (TextUtils.isEmpty(input.getText().toString())) {
            Toast.makeText(this, "请输入文字", Toast.LENGTH_SHORT).show();
            return;
        }

        String userData = getPreferenceManager().read(User.class.getName(), "");
        if (!TextUtils.isEmpty(userData)) {
            User userResource = new Gson().fromJson(userData, User.class);


            Map<String, Object> params = new HashMap<>();
            EditText input = (EditText) findViewById(R.id.advice_edit);
            params.put("content", input.getText().toString());
            params.put("id", userResource.getId());


        }
    }
}
