package neusoft.duanxudong.com.classdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.ui.DividerItemDecoration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import neusoft.duanxudong.com.classdemo.R;
import neusoft.duanxudong.com.classdemo.adapter.ProjectRecycleViewAdapter;

/**
 * Created by lisheng on 2016/3/28.
 */
public class ProjectActivity extends BaseActivity {


    @Bind(R.id.recycleview)
    UltimateRecyclerView ultimateRecyclerView;

    Handler handler;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.fabBtn)
    FloatingActionButton fabbtn;
    private List<Date> dateList;
    private List<String> nameList;
    private List<String> statusList;
    private List<String> p_url;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        handler = new Handler();
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        initData();
        initRecycleView();

    }

    public void initRecycleView() {
        ultimateRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        ultimateRecyclerView.setAdapter(new ProjectRecycleViewAdapter(this, nameList, dateList, statusList, p_url));
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
                                ultimateRecyclerView.setRefreshing(false);
                            }
                        }, 2000);
                    }
                }
        );

        ultimateRecyclerView.setHasFixedSize(false);


        fabbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(ProjectActivity.this, Activity_Post.class);
                startActivity(intent);
            }
        });
    }


    private void initData() {

        statusList = new ArrayList<>();
        statusList.add("1");
        statusList.add("2");
        statusList.add("1");
        statusList.add("1");
        statusList.add("1");

        nameList = new ArrayList<>();
        nameList.add("2016年“深圳杯”数学建模挑战赛");
        nameList.add("翼梦助学招募2016年暑期支教志愿者（50人）");
        nameList.add("2016第六届“太阳神鸟杯”天府·宝岛工业设计大赛");
        nameList.add("第四届“泰迪杯”全国数据挖掘挑战赛");
        nameList.add("2016英国切尔滕纳姆插画奖征集作品");
        dateList = new ArrayList<Date>();
        dateList.add(new Date());
        dateList.add(new Date());
        dateList.add(new Date());
        dateList.add(new Date());
        dateList.add(new Date());
        dateList.add(new Date());

        p_url = new ArrayList<>();
        p_url.add("http://www.shumo.com/home/html/3288.html");
        p_url.add("http://www.ourfreesky.org/ofs/recruit/teacher.html");
        p_url.add("http://sc.people.com.cn/n2/2016/0329/c345167-28036935.html");
        p_url.add("http://www.tipdm.org");
        p_url.add("http://www.visionunion.com/article.jsp?code=201604050007");

    }


}
