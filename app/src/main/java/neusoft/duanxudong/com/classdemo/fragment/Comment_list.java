package neusoft.duanxudong.com.classdemo.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import neusoft.duanxudong.com.classdemo.R;
import neusoft.duanxudong.com.classdemo.model.Comment;
import neusoft.duanxudong.com.classdemo.model.User;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by duanxudong on 16/4/4.
 * fragment  oncreate   ontsart 之后接收到消息
 */
public class Comment_list extends Fragment {


    @Bind(R.id.comment_list)
    ListView comment_list;
    ArrayList<Map<String, Object>> datalist;
    @Bind(R.id.comment_edit)
    EditText comment_edit;
    String comment_content;
    ProgressDialog progressDialog;
    private User user;
    private long subid;
    private String a;
    private List<Comment> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.comment_list_fragment, container, false);
        ButterKnife.bind(this, view);


        return view;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        Toast.makeText(getActivity(),"oncreate",Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.send)
    void send(View view) {


        if (user != null) {


//            jpanel.setVisibility(View.VISIBLE);
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setMessage("loading +++");
            progressDialog.show();

            comment_content = comment_edit.getText().toString();
            comment_edit.setText("");
//            Toast.makeText(getActivity(), "正在上传", Toast.LENGTH_LONG).show();
            new Thread(new Runnable() {
                @Override
                public void run() {

                    OkHttpClient client = new OkHttpClient();

                    FormBody body = new FormBody.Builder().addEncoded("id", user.getId() + "")
                            .addEncoded("comment", comment_content)
                            .addEncoded("sub_id", subid + "")

                            .build();
//                    comment_edit.setText("");


                    Request request = new Request.Builder().post(body).url(getString(R.string.comment_api_action)).build();
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    progressDialog.dismiss();

                                }
                            }, 3000);

                        }
                    });


                }
            }


            ).start();
        } else {
            Toast.makeText(getActivity(), "请登录之后发表评论", Toast.LENGTH_LONG).show();


        }

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(User user) {

        this.user = user;

//        Toast.makeText(getActivity(),"接收到消息",Toast.LENGTH_SHORT).show();


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Long subid) {

        this.subid = subid;
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String comm) {

        this.a = comm;

        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return new Date(json.getAsJsonPrimitive().getAsLong());
            }
        });

        list = builder.create().fromJson(a, new TypeToken<List<Comment>>() {
        }.getType());


        datalist = new ArrayList<Map<String, Object>>();

        for (Comment comment : list) {

            ArrayMap<String, Object> map = new ArrayMap<String, Object>();
            map.put("comment", comment.getComment());
            map.put("name", comment.getUser().getName());
            datalist.add(map);

        }


        initlistview(datalist);
    }


    public void initlistview(List<Map<String, Object>> datalist) {


        String[] from = {"comment", "name"};
        int[] to = {R.id.commenter, R.id.commenter_name};
        SimpleAdapter simpleAdapter = new SimpleAdapter(this.getContext(), datalist, R.layout.comment_item, from, to);


        comment_list.setAdapter(simpleAdapter);


    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        Toast.makeText(getActivity(), "onstart", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }
}
