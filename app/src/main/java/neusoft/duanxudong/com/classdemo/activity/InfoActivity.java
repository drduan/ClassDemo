package neusoft.duanxudong.com.classdemo.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.ui.DividerItemDecoration;

import org.json.JSONObject;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import neusoft.duanxudong.com.classdemo.R;
import neusoft.duanxudong.com.classdemo.adapter.InfoRecycleViewAdapter;
import neusoft.duanxudong.com.classdemo.model.Message;
import neusoft.duanxudong.com.classdemo.model.MessageModel;

/**
 * Created by lisheng on 2016/3/28.
 */
public class InfoActivity extends BaseActivity {
    UltimateRecyclerView ultimateRecyclerView;

    String[] titles;
    String[] imgUrls;
    Handler handler;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        handler = new Handler();

        initView();
        volleyRequest(getString(R.string.project_api));
    }

    public void initRecycleView() {
        ultimateRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ultimateRecyclerView.setAdapter(new InfoRecycleViewAdapter(this, titles, imgUrls));
        ultimateRecyclerView.enableDefaultSwipeRefresh(true);
        ultimateRecyclerView.addItemDecoration(new DividerItemDecoration(

                this, DividerItemDecoration.HORIZONTAL_LIST));


        ultimateRecyclerView.setDefaultOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                ultimateRecyclerView.setRefreshing(true);
                            }
                        }, 500);
                        Log.i("下拉刷新", "下拉刷新");
                    }
                }
        );
    }


    private void initData(List<Message> messages) {
        int len = messages.size();
        titles = new String[len];
        imgUrls = new String[len];
        for (int i = 0; i < len; i++) {
            titles[i] = messages.get(i).getMsg_title();
            imgUrls[i] = messages.get(i).getMsg_img();
        }
        initRecycleView();
    }

    private void initView() {
        ultimateRecyclerView = (UltimateRecyclerView) findViewById(R.id.recycleview);
    }


    public void volleyRequest(String url) {
        RequestQueue mQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                MessageModel messageModel = new Gson().fromJson(response.toString(), MessageModel.class);
                Log.i("+++", "+++" + messageModel.toString());
                initData(messageModel.getMessages());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("+++", "+++" + "获取JSON数据失败");
            }
        });
        mQueue.add(jsonObjectRequest);
    }

}
