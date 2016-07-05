package neusoft.duanxudong.com.classdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;
import com.igexin.sdk.PushManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.sharesdk.framework.ShareSDK;
import cn.smssdk.SMSSDK;
import neusoft.duanxudong.com.classdemo.R;
import neusoft.duanxudong.com.classdemo.effect.EffectConstants;
import neusoft.duanxudong.com.classdemo.model.User;
import neusoft.duanxudong.com.classdemo.widget.FlashView;


public class HomeActivity extends BaseActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {


    LinearLayout btn_classes, btn_resource, btn_quesstions, btn_exam, btn_ask, btn_co, btn_info, btn_project, btn_comment;

    @Bind(R.id.drawer)
    DrawerLayout mdrawerLayout;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.flash_view)
    FlashView flashView;


    TextView login_label;
    TextView signup_label;

    List<String> imageUrls;


    ImageView head_image;


    String userData;
    User user;
    long firsttime;


    @Bind((R.id.nav_view))
    NavigationView navigationView;

    StringBuilder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);


        userData = getPreferenceManager().read(User.class.getName(), "");
        user = new Gson().fromJson(userData, User.class);

        initUI();


        imageUrls = new ArrayList<String>();
        imageUrls.add("http://www.bz55.com/uploads1/allimg/120312/1_120312100435_8.jpg");
        imageUrls.add("http://img3.iqilu.com/data/attachment/forum/201308/21/192654ai88zf6zaa60zddo.jpg");
        imageUrls.add("http://img2.pconline.com.cn/pconline/0706/19/1038447_34.jpg");
        flashView.setImageUris(imageUrls);
        flashView.setEffect(EffectConstants.DEFAULT_EFFECT);//更改图片切换的动画效果


        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mdrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mdrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getDelegate().setLocalNightMode(AppCompatDelegate.getDefaultNightMode());


        if (!TextUtils.isEmpty(userData)) {
            signup_label.setVisibility(View.INVISIBLE);
            login_label.setText("用户名:" + user.getName());
            //loadRoundImage(getApplicationContext(), "http://www.bz55.com/uploads1/allimg/120312/1_120312100435_8.jpg", head_image);

            loadRoundImage(getApplicationContext(), user.getHead_url(), head_image);

            head_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent();
                    intent.setClass(HomeActivity.this, MeActivity.class);
                    startActivity(intent);
                }
            });
            return;
        }


        PushManager.getInstance().initialize(this.getApplicationContext());
        SMSSDK.initSDK(this, "ed68736f30f3", "3a2e2b7392b2f6838f07540b38533e90");


    }


    void initUI() {

        btn_classes = (LinearLayout) findViewById(R.id.home_btn_classes);
        btn_classes.setOnClickListener(this);
        btn_quesstions = (LinearLayout) findViewById(R.id.home_btn_quesstions);
        btn_quesstions.setOnClickListener(this);
        btn_resource = (LinearLayout) findViewById(R.id.home_btn_resource);
        btn_resource.setOnClickListener(this);
        btn_exam = (LinearLayout) findViewById(R.id.home_btn_exam);
        btn_exam.setOnClickListener(this);
        btn_ask = (LinearLayout) findViewById(R.id.home_btn_ask);
        btn_ask.setOnClickListener(this);
        btn_co = (LinearLayout) findViewById(R.id.home_btn_co);
        btn_co.setOnClickListener(this);
        btn_comment = (LinearLayout) findViewById(R.id.comment);
        btn_comment.setOnClickListener(this);

        navigationView.setNavigationItemSelectedListener(this);

        login_label = (TextView) navigationView.inflateHeaderView(R.layout.nav_header_main).findViewById(R.id.login_label);

        head_image = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.head_image);


        if (user != null) {

            loadRoundImage(HomeActivity.this, "" + user.getHead_url(), head_image);

            head_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    login_label.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if (!TextUtils.isEmpty(userData)) {

                                new MaterialDialog.Builder(HomeActivity.this)
                                        .title("是否注销")
                                        .positiveText("是")
                                        .negativeText("否")
                                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                                            @Override
                                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {


                                                getPreferenceManager().remove(User.class.getName());

                                                HomeActivity.this.onRestart();

                                            }
                                        })
                                        .show();


                            } else {


                                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                                startActivity(intent);


                            }

                        }
                    });
                }
            });

        }

        //user null

        signup_label = (TextView) navigationView.getHeaderView(0).findViewById(R.id.textView_signup);
        signup_label.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(HomeActivity.this, Register.class);
                startActivity(intent);
                finish();

            }
        });

        login_label.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!TextUtils.isEmpty(userData)) {

                    new MaterialDialog.Builder(HomeActivity.this)
                            .title("是否注销")
                            .positiveText("是")
                            .negativeText("否")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {


                                    getPreferenceManager().remove(User.class.getName());


                                    HomeActivity.this.recreate();


                                }
                            })
                            .show();


                } else {


                    Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                    startActivity(intent);


                }

            }
        });

        btn_info = (LinearLayout) findViewById(R.id.home_btn_info);
        btn_info.setOnClickListener(this);
        btn_info = (LinearLayout) findViewById(R.id.home_btn_info);
        btn_info.setOnClickListener(this);
        btn_project = (LinearLayout) findViewById(R.id.home_btn_project);
        btn_project.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {


            case R.id.home_btn_info:
                intent = new Intent(HomeActivity.this, InfoActivity.class);
                break;
            case R.id.home_btn_project:
                intent = new Intent(HomeActivity.this, ProjectActivity.class);
                break;

            case R.id.home_btn_classes:

                intent = new Intent(HomeActivity.this, ClassesActivity.class);
                break;
            //change
            case R.id.home_btn_quesstions:
                intent = new Intent(HomeActivity.this, QuesstionActivity.class);
                //试题库
                break;
            case R.id.home_btn_resource:
                intent = new Intent(HomeActivity.this, MaterialActivity.class);
                break;
            case R.id.home_btn_exam:
                intent = new Intent(HomeActivity.this, ExamActivity.class);
                break;
            case R.id.home_btn_ask:
                intent = new Intent(HomeActivity.this, AskActivity.class);
                break;
            case R.id.home_btn_co:
                intent = new Intent(HomeActivity.this, CollectionActivity.class);
                break;


            case R.id.comment:
                intent = new Intent(HomeActivity.this, AdviceActivity.class);

                break;
            default:
                break;
        }


        flashView.clearFocus();
        flashView.destroyDrawingCache();
        flashView.cancelLongPress();
        flashView.stopFlash();
        startActivity(intent);
    }


    void showShare() {
        ShareSDK.initSDK(this);


        cn.sharesdk.onekeyshare.OnekeyShare oks = new cn.sharesdk.onekeyshare.OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();


        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("邀请");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("https://www.pgyer.com/1pFJ");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("邀请您一起使用﹣https://www.pgyer.com/1pFJ");

        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
//        oks.setImagePath("/sdcard/temp_head_image.jpg");
        oks.setUrl("http://www.pgyer.com/1pFJ");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("https://www.pgyer.com/1pFJ");
//        oks
// 启动分享GUI
        oks.show(this);


    }


    @Override
    public void onBackPressed() {


        if (mdrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mdrawerLayout.closeDrawer(GravityCompat.START);
        }


        long secondtime = System.currentTimeMillis();


        if (secondtime - firsttime > 3000) {


            firsttime = secondtime;
//            secondtime = System.currentTimeMillis();
            Snackbar snackbar = Snackbar.make(getWindow().getDecorView().getRootView(), "再按一次退出", Snackbar.LENGTH_LONG);

            snackbar.show();


        } else {
//            HomeActivity.this.finish();

            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.nav_share:
                showShare();
                break;
            case R.id.nav_manage:

                Intent intent = new Intent();
                intent.setClass(HomeActivity.this, LookHis.class);
                startActivity(intent);

                break;

            case R.id.nav_slideshow:
                Intent intent3 = new Intent();
                intent3.setClass(HomeActivity.this, LookHis.class);
                startActivity(intent3);
                break;

            case R.id.nav_gallery:


                if (userData == null) {

                    Toast.makeText(getContext(), "请登录后查看", Toast.LENGTH_LONG).show();

                } else {

                    Intent intent2 = new Intent();
                    intent2.setClass(HomeActivity.this, MeJoinedProject.class);
                    startActivity(intent2);

                }
                break;
            case R.id.nav_camera:
                Intent intent1 = new Intent(HomeActivity.this, AdviceActivity.class);
                startActivity(intent1);
                break;


            default:

                break;


        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        drawer.closeDrawer(GravityCompat.START);
        return true;


    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


}