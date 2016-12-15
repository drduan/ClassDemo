package neusoft.duanxudong.com.classdemo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import neusoft.duanxudong.com.classdemo.R;
import neusoft.duanxudong.com.classdemo.model.User;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by duanxudong on 15/11/30.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {


    TextView loginbtn;
    TextView registerbtn;
    Context context;

    @Bind(R.id.editText3)
    EditText email;
    @Bind(R.id.editText4)
    EditText passwd;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    String response;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        context = getApplicationContext();
        initUI();
    }


    public void initUI() {

        loginbtn = (TextView) findViewById(R.id.login);
        registerbtn = (TextView) findViewById(R.id.register);
        registerbtn.setOnClickListener(this);
        loginbtn.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {


        //Mob.com  短信验证码  分享
        switch (v.getId()) {
            case R.id.register:

//                RegisterPage registerPage = new RegisterPage();
//                registerPage.setRegisterCallback(new EventHandler() {
//                    public void afterEvent(int event, int result, Object data) {
//// 解析注册结果
//                        if (result == SMSSDK.RESULT_COMPLETE) {
////                            @SuppressWarnings("unchecked")
//                            HashMap<String, Object> phoneMap = (HashMap<String, Object>) data;
//                            String country = (String) phoneMap.get("country");
//                            String phone = (String) phoneMap.get("phone");
//
//                            //15566411770
//                            Log.d("@@Result",country + phone);
//                            //event 3 result 1
//                            Log.d("result","@@"+ event+result+data.toString());
//
//

                Intent intent = new Intent(LoginActivity.this, Register.class);
//                            intent.putExtra("phone",phone);
                startActivity(intent);
// 提交用户信息
//                            registerUser(country, phone);
//                        }
//                                            }
//                });
//                registerPage.show(context);
                break;

            case R.id.login:


                if (check()) {

                    doLogin();

                } else {
                    Toast.makeText(getApplicationContext(), "输入不合法", Toast.LENGTH_LONG).show();
                }

                break;
            default:
                break;
        }
    }

    public boolean check() {

        if (!email.getText().toString().equals(null) && !email.getText().toString().equals("") && !passwd.getText().toString().equals(null) && !passwd.getText().toString().equals(""))

        {
            return true;
        } else {

            email.setError("请输入正确的邮箱号密码格式");


            return false;
        }
    }

    boolean doLogin() {


        final User user = new User();
        user.setEmail(email.getText().toString());
        user.setPasswd(passwd.getText().toString());
        String user_string = new Gson().toJson(user);

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, user_string);

        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(getString(R.string.user_login_api))

                .post(body)
                .build();

        Call call = okHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {


            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {


                String r = response.body().string();

                Log.i("@@", "" + r);


                if (null == response || r.isEmpty()) {

                    runOnUiThread(
                            new Runnable() {
                                @Override
                                public void run() {

                                    loginfailed("信息是否正确");
                                }
                            }
                    );
                } else if (new Gson().fromJson(r, RegLog.class).getResult().equals("1")) {


                    Log.d("response", "@@" + response.body().toString());

                    User user = new Gson().fromJson(r, RegLog.class).getUser();


                    getPreferenceManager().save(User.class.getName(), new Gson().toJson(user));


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            loginSucceed("登录成功");


                            Intent intent = new Intent();
                            intent.setClass(LoginActivity.this, HomeActivity.class);
                            startActivity(intent);

                        }

                    });


                } else {


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            loginfailed("登录失败");
                        }
                    });

                }


            }
        });

        return false
                ;
    }


    public void loginSucceed(String msg) {

        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();

    }

    public void loginfailed(String msg) {

        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();

    }
}






