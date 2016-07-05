package neusoft.duanxudong.com.classdemo.util;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by harry on 1/14/16.
 */
public class OkHttpHelper {


    public static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
    public static final MediaType MEDIA_TYPE_JPG = MediaType.parse("image/jpg");
    public static final MediaType MEDIA_TYPE_JSON
            = MediaType.parse("application/json; charset=utf-8");


    public static final Map<String, String> EMPTY_PARAMS = new HashMap<String, String>();
    public static final Map<String, String> HEADERS = new HashMap<String, String>();
    private static final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(180, TimeUnit.SECONDS)
            .writeTimeout(180, TimeUnit.SECONDS)
            .readTimeout(180, TimeUnit.SECONDS)
            .build();
    /**
     * 所有请求都需要带的参数
     */
    private static final Map<String, String> basicParams = new HashMap<String, String>();
    /**
     * 用户认证信息
     */
    private static final Map<String, String> auth = new HashMap<String, String>();

    private OkHttpHelper() {

    }

    /**
     * MultipartParms helper method
     *
     * @param builder
     * @param params
     */
    public static void buildMultipartParms(MultipartBody.Builder builder, Map<String, String> params) {
        for (Map.Entry<String, String> entry : params.entrySet()) {

            if (!TextUtils.isEmpty(entry.getKey()) && !TextUtils.isEmpty(entry.getValue())) {

                builder.addFormDataPart(entry.getKey(), entry.getValue());
            }
        }
    }
}

