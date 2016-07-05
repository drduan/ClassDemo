package neusoft.duanxudong.com.classdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import neusoft.duanxudong.com.classdemo.R;
import neusoft.duanxudong.com.classdemo.util.LoggingUtils;

public class ExamActivity extends BaseActivity {

    private SimpleAdapter mAdapter;

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.listview)
    ListView   mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("考试中心");

        initDatas();
    }



    private void initDatas() {
        String[] from = {"exam_name", "exam_type", "exam_time"};
        int[] to = {R.id.exam_name, R.id.exam_type, R.id.exam_time};
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("exam_name", "CET-4 模拟考试");
        map.put("exam_type", "考试类型：练习");
        map.put("exam_time", "考试时间：1小时");
        list.add(map);
        map = new HashMap<>();
        map.put("exam_name", "Java 模拟考试");
        map.put("exam_type", "考试类型：练习");
        map.put("exam_time", "考试时间：1小时");
        list.add(map);
        mAdapter = new SimpleAdapter(this, list, R.layout.exam_list_item, from, to);
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                        LoggingUtils.error("@@","as");
                        Intent intent = new Intent().setClass(getApplicationContext(),Simu_kaoshi.class);
                        startActivity(intent);
                    }
                }
        );

    }


}
