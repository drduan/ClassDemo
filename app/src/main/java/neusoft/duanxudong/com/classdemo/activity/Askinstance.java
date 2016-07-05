package neusoft.duanxudong.com.classdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import neusoft.duanxudong.com.classdemo.R;
import neusoft.duanxudong.com.classdemo.model.User;

/**
 * Created by duanxudong on 15/11/28.
 */

public class Askinstance extends BaseActivity implements View.OnClickListener {


    @Bind(R.id.edit_ask_title)
    EditText edit_ask_title;

    @Bind(R.id.edit_ask_content)
    EditText edit_ask_content;

    @Bind(R.id.ask_submit)

    Button submit;

    @Bind(R.id.ask_cancle)
    Button cancle;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    String date = null;

    String asktitle;
    String askcontent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Date dt = new Date();
        date = dt.toString();

        asktitle = edit_ask_title.getText().toString();
        askcontent = edit_ask_content.getText().toString();

        submit.setOnClickListener(this);
        cancle.setOnClickListener(this);

    }


    @OnClick(value = {R.id.ask_submit, R.id.ask_cancle})
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.ask_submit:


                String userData = getPreferenceManager().read(User.class.getName(), "");

                User userName = new Gson().fromJson(userData, User.class);


                if (userName == null) {

                    Toast.makeText(getApplicationContext(), "请登录后操作", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(Askinstance.this, LoginActivity.class);
                    startActivity(intent);
                    break;

                }


                if (edit_ask_title.getText().toString().length() == 0 && edit_ask_content.getText().toString().length() == 0) {
                    Toast.makeText(this, "请输入问题", Toast.LENGTH_LONG).show();
                } else {
                    String askInstence = "";
                    final JSONObject jsonObject = new JSONObject();

                    try {


                        jsonObject.put("asktitle", edit_ask_title.getText().toString());
                        jsonObject.put("askcontent", edit_ask_content.getText().toString());
                        jsonObject.put("askman", userName.getName());

                    } catch (JSONException j) {

                        // TODO: 16/4/7

                        Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
                    }


                    Toast.makeText(this, "" + jsonObject.toString(), Toast.LENGTH_LONG).show();

                    RequestQueue queue = Volley.newRequestQueue(this);

                    JsonObjectRequest jsonreq = new JsonObjectRequest(Request.Method.POST, getString(R.string.ask_api
                    ), jsonObject, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Toast.makeText(getApplicationContext(), jsonObject.toString(), Toast.LENGTH_LONG).show();
                            finish();

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();

// TODO: 16/4/7
                        }


                    });
                    jsonreq.setRetryPolicy(new DefaultRetryPolicy(2000,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

                    queue.add(jsonreq);

                }
                break;
            case R.id.ask_cancle:
                this.finish();
                break;
            default:

                break;
        }

    }


}
