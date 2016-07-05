package neusoft.duanxudong.com.classdemo.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import neusoft.duanxudong.com.classdemo.R;

public class MaterialActivity extends BaseActivity {
    @Bind(R.id.material_list)
     ListView mListView;
     SimpleAdapter mListAdapter;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initListDatas();
    }

     void initListDatas() {
        String[] from = {"material_title", "material_image"};
        int[] to = {R.id.material_title, R.id.material_image};
        final List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("material_title", "理论文章");
        map.put("material_image", getResources().getDrawable(R.drawable.material_book));
        list.add(map);
        map = new HashMap<String, Object>();
        map.put("material_title", "PPT资源下载");
        map.put("material_image", getResources().getDrawable(R.drawable.ic_get_app_black_24dp));
        list.add(map);
        mListAdapter = new SimpleAdapter(this, list, R.layout.material_list_item, from, to);
        mListAdapter.setViewBinder(new SimpleAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Object data, String textRepresentation) {
                if (view instanceof ImageView && data instanceof Drawable) {
                    ImageView iv = (ImageView) view;
                    iv.setImageDrawable((Drawable) data);
                    return true;
                } else {
                    return false;
                }
            }
        });
        mListView.setAdapter(mListAdapter);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent();

                switch (position) {

                    case 0:
                        break;
                    case 1:
                        intent.setClass(MaterialActivity.this, DownloadActivity.class);
                        startActivity(intent);
                        break;

                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_material, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
