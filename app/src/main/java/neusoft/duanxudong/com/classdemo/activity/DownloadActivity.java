package neusoft.duanxudong.com.classdemo.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloader;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import neusoft.duanxudong.com.classdemo.R;
import neusoft.duanxudong.com.classdemo.adapter.DownloadRecycleViewAdapter;
import neusoft.duanxudong.com.classdemo.model.FileResource;
import neusoft.duanxudong.com.classdemo.util.LoggingUtils;

/**
 * Created by lisheng on 2016/3/21.
 */
public class DownloadActivity extends BaseActivity {

    RecyclerView recycleview;
    static ProgressBar progressBar;
    List<FileResource> list;
    String[] names;
    String[] uploaders;
    int[] sizes;
    String[] uris;
    Bitmap[] btm;


    @Bind(R.id.toolbar)
    Toolbar toolbar;

    public static void download(String uri, String name) {
        progressBar.setVisibility(View.VISIBLE);
        new DownloadFile().doDownload(uri, name);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("下载");

        String url = "http://192.168.2.88:8080/ClassOnlineService/File.json";
        list = new ArrayList<>();
        volleyRequext(url);
    }

    public void volleyRequext(String url) {
        RequestQueue mQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<org.json.JSONObject>() {
            @Override
            public void onResponse(org.json.JSONObject response) {
                String mJson = response.toString();


                JsonObject jsonObject = new JsonParser().parse(mJson).getAsJsonObject();
                JsonArray jsonArray = jsonObject.getAsJsonArray("list");


                GsonBuilder builder = new GsonBuilder().registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        return new Date(json.getAsJsonPrimitive().getAsLong());
                    }
                });


                list = builder.create().fromJson(jsonArray.toString(), new TypeToken<List<FileResource>>() {
                }.getType());

                // TODO: 16/4/8


                initData(list);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        mQueue.add(jsonObjectRequest);
    }

    public void initData(List<FileResource> list) {
        btm = new Bitmap[3];

        names = new String[list.size()];
        uploaders = new String[list.size()];
        sizes = new int[list.size()];
        uris = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {

            names[i] = list.get(i).getFile_name();
            uploaders[i] = list.get(i).getPush_name();
            sizes[i] = list.get(i).getFile_size();
            uris[i] = list.get(i).getFile_path();

            if (list.get(i).getFile_format().equals("ppt")) {
                btm[i] = android.graphics.BitmapFactory.decodeResource(getResources(), R.drawable.iconfont_ppt);
            } else if (list.get(i).getFile_format().equals("excel")) {
                btm[i] = android.graphics.BitmapFactory.decodeResource(getResources(), R.drawable.iconfont_excel);
            } else if (list.get(i).getFile_format().equals("word")) {
                btm[i] = android.graphics.BitmapFactory.decodeResource(getResources(), R.drawable.iconfont_doc);
            }


        }
        System.out.println("+++" + names.length);
        initView(names, uploaders, sizes, uris, btm);
    }

    public void initView(String[] names, String[] uploaders, int[] sizes, String[] uris, Bitmap[] btm) {
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        recycleview = (RecyclerView) findViewById(R.id.recycleview);
        recycleview.setLayoutManager(new LinearLayoutManager(this));
        recycleview.setAdapter(new DownloadRecycleViewAdapter(this, names, uploaders, sizes, uris, btm));
    }

    static class DownloadFile {

        public void doDownload(String uri, String finame) {


            System.out.println("开始下载ppt");

            LoggingUtils.debug("@@", android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + finame);

            FileDownloader.getImpl().create(uri).setPath(android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + finame+".ppt")
                    .addFinishListener(new BaseDownloadTask.FinishListener() {
                        @Override
                        public void over(BaseDownloadTask task) {

//                            task.start();
                            LoggingUtils.debug("@@", "下載完成");
//                            progressBar.setVisibility(View.GONE);
                        }
                    }).start();


        }
    }
}