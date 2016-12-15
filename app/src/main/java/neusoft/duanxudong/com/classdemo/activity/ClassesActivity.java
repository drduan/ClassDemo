package neusoft.duanxudong.com.classdemo.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.SearchView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;
import neusoft.duanxudong.com.classdemo.R;
import neusoft.duanxudong.com.classdemo.model.SubjectListModel;
import neusoft.duanxudong.com.classdemo.model.Video;
import neusoft.duanxudong.com.classdemo.model.subject;
import neusoft.duanxudong.com.classdemo.util.CacheRequest;
import neusoft.duanxudong.com.classdemo.util.MySingleton;


public class ClassesActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    public static final String TAG = "MyTag";
    @Bind(R.id.sv)
    SearchView searchView;
    private ListView mListView;
    private SimpleAdapter mListAdapter;
    private SubjectListModel datas;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_classes);

        ButterKnife.bind(this);
        initUI();
        loadDatas();


    }

    private void initUI() {

        mListView = (ListView) findViewById(R.id.class_list);
    }

    public void loadDatas() {


        mQueue = MySingleton.getInstance(this.getApplicationContext()).getRequestQueue();

        mQueue.start();
        CacheRequest stringRequest = new CacheRequest(Request.Method.GET, getString(R.string.subject_api), new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {
                try {
                    final String jsonString = new String(response.data,
                            HttpHeaderParser.parseCharset(response.headers));


                    // TODO: 16/4/8

                    GsonBuilder builder = new GsonBuilder();
//
                    builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                        public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                            return new Date(json.getAsJsonPrimitive().getAsLong());
                        }
                    });
                    datas = builder.create().fromJson(jsonString, SubjectListModel.class);
                    initListDatas();


                } catch (UnsupportedEncodingException | JsonSyntaxException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        stringRequest.setRetryPolicy(new DefaultRetryPolicy(40000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        stringRequest.setTag(TAG);
        MySingleton.getInstance(this).addToRequestQueue(stringRequest);


    }
//
//    public void readBitmapByVolleyForListView(String imgUrl, final Map map, final SimpleAdapter mAdapter) {
//
//        ImageRequest imgRequest = new ImageRequest(imgUrl,
//                new Response.Listener<Bitmap>() {
//                    @Override
//                    public void onResponse(Bitmap bitmap) {
//                        map.put("class_images", bitmap);
//
//                        mAdapter.notifyDataSetChanged();
//                    }
//                },
//                300,
//                400,
//
//                ImageView.ScaleType.FIT_XY,
//                Bitmap.Config.ARGB_8888,
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError arg0) {
//
//                    }
//                });
//        imgRequest.setTag(TAG);
//        mQueue.add(imgRequest);
//
//    }
//


    private void initListDatas() {
        String[] from = {"class_title", "class_images", "class_introduce"};
        int[] to = {R.id.class_title, R.id.class_image, R.id.class_introduce};
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();


        mListAdapter = new SimpleAdapter(this, list, R.layout.class_list_item, from, to);
//        mListView.measure(99,11100);

        mListView.setDividerHeight(2);


        mListAdapter.setViewBinder(new SimpleAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Object data, String textRepresentation) {
                if (view instanceof ImageView && data instanceof Bitmap) {
                    //把图片和ImageView绑定到一起
                    ImageView iv = (ImageView) view;
                    iv.setImageBitmap((Bitmap) data);
                    return true;
                } else {
                    return false;
                }
            }
        });
        mListView.setAdapter(mListAdapter);

        mListView.setOnItemClickListener(this);


        for (subject subject : datas.getList()) {


            ArrayMap<String, Object> map = new ArrayMap<String, Object>();
            map.put("class_title", subject.getSubject());
            get(getApplicationContext(), subject.getSub_img(), map, mListAdapter);
            map.put("class_introduce", subject.getSub_teacher());

            list.add(map);
        }


        mListAdapter.notifyDataSetChanged();

        mListView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {


                menu.add(0, 0, 0, "添加收藏");

            }


        });


    }

    public void get(final Context context, final String url, final ArrayMap<String, Object> map, final SimpleAdapter mListAdapter) {

        new Runnable() {
            @Override
            public void run() {
                Glide.with(context).load(url).asBitmap().into(new SimpleTarget<Bitmap>(300, 400) {
                    @Override
                    public void onResourceReady(Bitmap bitmapp, GlideAnimation anim) {
                        map.put("class_images", bitmapp);
                    }
                });


                mListAdapter.notifyDataSetChanged();

            }
        }.run();


    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int id = (int) info.id;
        switch (item.getItemId()) {
            case 0:


                // TODO: 16/4/7


                break;
        }

        return super.onContextItemSelected(item);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        Set<Video> v_set = datas.getList().get(position).getSub_v();
        ArrayList<Video> info = new ArrayList<Video>();
        info.addAll(v_set);
        Intent intent = new Intent(ClassesActivity.this, VideoExampleActivity.class);
        intent.putExtra("listobj", info);
        intent.putExtra("subject", datas.getList().get(position).getId());

        startActivity(intent);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;


    }


    @Override
    protected void onStop() {

        super.onStop();
    }


}