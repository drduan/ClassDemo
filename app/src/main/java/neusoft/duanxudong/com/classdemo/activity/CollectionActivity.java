package neusoft.duanxudong.com.classdemo.activity;//


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import neusoft.duanxudong.com.classdemo.R;
import neusoft.duanxudong.com.classdemo.model.subject;
import neusoft.duanxudong.com.classdemo.util.MySingleton;

/**
 * Created by duanxudong on 16/2/26 11:01 ${PACKAGENAME}.
 */
public class CollectionActivity extends BaseActivity {

    List<subject> datas;
    @Bind(R.id.colist)
    ListView CoList;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    RequestQueue mQueue;
    SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setTitle("收藏");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(CollectionActivity.this);


    }


    public void readBitmapByVolleyForListView(String imgUrl, final Map map, final SimpleAdapter mAdapter) {


        mQueue = MySingleton.getInstance(this.getApplicationContext()).getRequestQueue();
        mQueue.start();
        ImageRequest imgRequest = new ImageRequest(imgUrl,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        map.put("img_url", bitmap);
                    }
                },
                300,
                400,
                ImageView.ScaleType.CENTER,
                Bitmap.Config.ARGB_8888,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError arg0) {

                        Log.v("Tag", arg0.getMessage());
                    }
                });
        imgRequest.setTag("Colle");
        mQueue.add(imgRequest);

    }


    public void mei() {

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.id.head_image, options);
        int imageHeight = options.outHeight;
        int imageWidth = options.outWidth;
        String imageType = options.outMimeType;
    }

}
