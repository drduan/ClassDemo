package neusoft.duanxudong.com.classdemo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import neusoft.duanxudong.com.classdemo.R;
import neusoft.duanxudong.com.classdemo.model.Video;


/**
 * Created by duanxudong on 16/4/3.
 */
public class Video_List extends Fragment {

    public ArrayList<Video> list;
    @Bind(R.id.video_list)
    ListView liview;
    SimpleAdapter mListAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.video_list, container, false);

        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    public void doSomething(ArrayList<Video> list1) {
        list = list1;
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ArrayList<Video> list1) {
        doSomething(list1);
        initview();


    }


    @Override

    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        EventBus.getDefault().register(this);

    }

    public void initview() {


        List<HashMap<String, Object>> llist = new ArrayList<HashMap<String, Object>>();


        for (Video video : list) {


            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("video_title", video.getVname());
            map.put("video_introduce", video.getIntroduce());
            llist.add(map);
        }

        String[] from = {"video_title", "video_introduce"};
        int[] to = {R.id.class_title, R.id.class_introduce};


        mListAdapter = new SimpleAdapter(getActivity(), llist, R.layout.video_list_item, from, to);

        liview.setAdapter(mListAdapter);
        mListAdapter.notifyDataSetChanged();
        liview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();


    }


    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }


}


/**
 * Retrofit retrofit = new Retrofit.Builder()
 * .baseUrl(getString(R.string.comment_Get_api))
 * .addConverterFactory(new GsonConverterFactory(new Gson()).create())
 * .build();
 * TaskService service = retrofit.create(TaskService.class);
 * try {
 * Log.d("@@", service.createTask(1).execute().toString());
 * } catch (IOException e) {
 * e.printStackTrace();
 * }
 */