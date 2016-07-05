package neusoft.duanxudong.com.classdemo.model;

import android.util.Log;

import java.util.List;

import neusoft.duanxudong.com.classdemo.fragment.Comment_list;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by duanxudong on 16/4/13.
 */
public interface TaskService {


    /**
     *   comment service
     * @param task
     * @return
     */









//    @GET("/repos/{owner}/{repo}/contributors")

    @GET("?id={id}")

    Call< List<Comment>>  createTask(@Field("id") int id  );


}
