package neusoft.duanxudong.com.classdemo.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;

/**
 * Created by duanxudong on 16/4/13.
 */
public interface TaskService {


    /**
     * comment service
     *
     * @param task
     * @return
     */


//    @GET("/repos/{owner}/{repo}/contributors")
    @GET("?id={id}")
    Call<List<Comment>> createTask(@Field("id") int id);


}
