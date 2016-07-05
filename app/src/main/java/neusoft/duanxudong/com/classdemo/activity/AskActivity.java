package neusoft.duanxudong.com.classdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import neusoft.duanxudong.com.classdemo.R;
import neusoft.duanxudong.com.classdemo.model.Question;
import neusoft.duanxudong.com.classdemo.model.QuestionListModel;
import neusoft.duanxudong.com.classdemo.util.CacheRequest;
import neusoft.duanxudong.com.classdemo.util.MySingleton;

public class AskActivity extends BaseActivity {

    @Bind(R.id.ask_listview)
    ListView mListView;

    List list;
    @Bind(R.id.swiperefresh)
    SwipeRefreshLayout swiperefresh;
    QuestionListModel datas;
    @Bind(R.id.ask_add)
    Toolbar toolbar;
    SimpleAdapter mAdapter;
    RequestQueue mQueue = null;
    @Bind(R.id.center_tv)
    TextView center_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ask_list);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("问题列表");
        center_tv.setText("互动问答");


        loadDatas();


        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        mAdapter.notifyDataSetChanged();
                        mQueue.getCache().clear();
                        loadDatas();


                        swiperefresh.setRefreshing(false);

                    }
                }, 2000);


            }
        });
    }


    public void loadDatas() {

        mQueue = MySingleton.getInstance(this.getApplicationContext()).getRequestQueue();


        mQueue.start();
        CacheRequest stringRequest = new CacheRequest(Request.Method.GET, getString(R.string.question_api), new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {
                try {
                    final String jsonString = new String(response.data,
                            HttpHeaderParser.parseCharset(response.headers));


                    GsonBuilder builder = new GsonBuilder();

                    builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                        public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                            return new Date(json.getAsJsonPrimitive().getAsLong());
                        }
                    });


                    datas = builder.create().fromJson(jsonString, QuestionListModel.class);
//                    datas = JSON.parseObject(jsonString, QuestionListModel.class);

                    // TODO: 16/4/8
                    initView();

                } catch (UnsupportedEncodingException | JsonSyntaxException e) {
                    e.printStackTrace();
                }
            }

        }
                , new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {


            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(2000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


        MySingleton.getInstance(this).addToRequestQueue(stringRequest);


    }


    void initView() {


        list = new ArrayList<>();

        for (Question question : datas.getList()) {

            Map<String, Object> mapa = new HashMap<String, Object>();
            mapa.put("ask_title", question.getAsktitle());
            mapa.put("ask_add_date", question.getAdd_ask_date());


            list.add(mapa);
        }


        String[] from = {"ask_title", "ask_add_date"};
        int[] to = {R.id.ask_name, R.id.ask_time};
        mAdapter = new SimpleAdapter(this, list, R.layout.ask_list_item, from, to);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("ask_name", datas.getList().get(position).getAsktitle());
                intent.putExtra("ask_id", datas.getList().get(position).getQid());
                intent.putExtra("ask_content", datas.getList().get(position).getAskContent());
                intent.setClass(AskActivity.this, AskItemMore.class);
                startActivity(intent);


            }
        });


    }


    @OnClick(R.id.ask_bar_tv)
    public void ask_UI(View v) {

        Intent intent = new Intent();
        intent.setClass(this, Askinstance.class);
        startActivity(intent);

    }


}