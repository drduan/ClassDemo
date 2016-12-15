package neusoft.duanxudong.com.classdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import neusoft.duanxudong.com.classdemo.R;
import neusoft.duanxudong.com.classdemo.adapter.ViewPagerAdapter;
import neusoft.duanxudong.com.classdemo.fragment.Comment_list;
import neusoft.duanxudong.com.classdemo.fragment.Video_List;
import neusoft.duanxudong.com.classdemo.model.User;
import neusoft.duanxudong.com.classdemo.model.Video;
import neusoft.duanxudong.com.classdemo.util.LoggingUtils;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by duanxudong on 15/11/21.
 */
public class VideoExampleActivity extends BaseActivity

{

    public static final String TAG = "MyTag";
    @Bind(R.id.video_vp)
    ViewPager viewPager;
    @Bind(R.id.video_tab)
    TabLayout video_tab;
    ArrayList<Video> list;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.custom_videoplayer_standard)
    JCVideoPlayerStandard jcVideoPlayerStandard;
    String userData;
    private long subid;
    private String Comment;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_example);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        if (list.get())


        userData = getPreferenceManager().read(User.class.getName(), "");

        final User user = new Gson().fromJson(userData, User.class);


        Intent intent = getIntent();
        list = (ArrayList<Video>) intent.getSerializableExtra("listobj");

        subid = intent.getLongExtra("subject", 0L);


//        HtmlEscapers
//        Html
        if (list.isEmpty()) {
            jcVideoPlayerStandard.setUp("", "", "");

        } else {

            jcVideoPlayerStandard.setUp("http://www.saesea.cn:8080/ClassOnlineService/video/8852.mp4", "", "软件测试第一讲");
        }

        ViewPagerAdapter vpa = new ViewPagerAdapter(getSupportFragmentManager());
        Video_List fragment1 = new Video_List();
        Comment_list fragment2 = new Comment_list();

        vpa.addFrag(fragment1, "视频");

        vpa.addFrag(fragment2, "讨论");
        viewPager.setAdapter(vpa);


        video_tab.setupWithViewPager(viewPager);


        new Thread(new Runnable() {
            @Override
            public void run() {
                LoadComment();
            }
        }).start();


        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {

                        LoggingUtils.error("@@", "@@" + subid);
                        EventBus.getDefault().post(subid);

                        if (Comment != null) {


                            EventBus.getDefault().post(Comment);
                        }
                        EventBus.getDefault().post(list);
                        if (user != null) {


                            EventBus.getDefault().post(user);

                        }
                    }
                }, 500);
        video_tab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {


            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {


            }


            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(video_tab));


    }


    @Override
    protected void onPause() {

        JCVideoPlayer.releaseAllVideos();
        super.onPause();
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    public void LoadComment() {

        OkHttpClient client = new OkHttpClient();

        FormBody body1 = new FormBody.Builder().addEncoded("id", "" + subid).build();


        Request request = new Request.Builder()
                .url(getString(R.string.comment_Get_api))
                .post(body1)
                .build();


        try {

            Response response = client.newCall(request).execute();

            Comment = response.body().string();


        } catch (IOException e) {

            e.printStackTrace();
        }
    }


}

