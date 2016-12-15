package neusoft.duanxudong.com.classdemo.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

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
 * Created by duanxudong on 16/1/16.
 */
public class Register extends BaseActivity {


    private static final ScheduledExecutorService worker =
            Executors.newSingleThreadScheduledExecutor();
    @Bind(R.id.input_name)
    EditText _nameText;
    @Bind(R.id.input_email)
    EditText _emailText;
    @Bind(R.id.input_password)
    EditText _passwordText;
    @Bind(R.id.input_password2)
    EditText _passwordText2;
    @Bind(R.id.btn_signup)
    AppCompatButton _signupButton;
    @Bind(R.id.link_login)
    TextView _loginLink;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    ProgressDialog dialog;
    String response;
    String regMsg;
    User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        String phone = getIntent().getStringExtra("phone");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });


    }


    public void signup() {


        if (validate()) {


//            _signupButton.setEnabled(false);


//            mView = new CatLoadingView();
            dialog = new ProgressDialog(this);

            dialog = ProgressDialog.show(Register.this, "", "loading please wait ");

//            mView.show(getSupportFragmentManager(), "");


            // TODO: 16/4/8
            String name = _nameText.getText().toString();
            String email = _emailText.getText().toString();
            String password = _passwordText.getText().toString();


            user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setPasswd(password);


            String aString = new Gson().toJson(user);
            MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            RequestBody body = RequestBody.create(JSON, aString);
            OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

            final Request request = new Request.Builder()
                    .url(getString(R.string.user_register_api))
                    .post(body)
                    .build();

            Call call = okHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {


                }

                @Override
                public void onResponse(Call call, Response r) throws IOException {


                    response = r.body().string();


                    String result = new Gson().fromJson(response, RegLog.class).result;
                    User user = new Gson().fromJson(response, RegLog.class).user;

                    if (result.equals("2")) {

                        Toast.makeText(getContext(), "邮箱或者用户名已經存在,请重新输入", Toast.LENGTH_LONG).show();

                        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //Do something here
                                dialog.dismiss();
//                                onSignupSuccess(regMsg);
//                                finish();

                            }
                        }, 3000);

                    } else if (result.equals("success")) {
                        regMsg = "注册成功";

//                        LoggingUtils.debug("@@",user.toString());

                        getPreferenceManager().save(User.class.getName(), new Gson().toJson(user));

                        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //Do something here
                                dialog.dismiss();
                                onSignupSuccess(regMsg);
                                finish();

                            }
                        }, 3000);

                    } else {


                        regMsg = "注册失敗";

                        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dialog.dismiss();
                                onSignupfailed(regMsg);

                            }
                        }, 3000);


                    }
                }
            });
        }


    }


    public void onSignupSuccess(String regMsg) {
        Toast.makeText(getBaseContext(), "注册成功", Toast.LENGTH_LONG).show();


        Intent intent = new Intent();
        intent.setClass(Register.this, HomeActivity.class);
        startActivity(intent);


    }

    public void onSignupfailed(String regMsg) {


        Toast.makeText(getBaseContext(), regMsg, Toast.LENGTH_LONG).show();

    }


    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("at least 3 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else if (CheckEmail(email)) {


        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }
        if (!_passwordText2.getText().toString().equals(_passwordText.getText().toString())) {
//            Log.i("@@@", "@@" + _passwordText.getText().toString() + _passwordText2.getText().toString());
            _passwordText2.setError("两次密码不同");
            valid = false;
        } else {
            _passwordText2.setError(null);
        }


        return valid;
    }


    public boolean CheckEmail(String email) {


        return true;

    }


}

class RegLog {

    String result;
    @SerializedName(value = "user", alternate = {"info"})
    User user;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
