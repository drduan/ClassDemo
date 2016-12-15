package neusoft.duanxudong.com.classdemo.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import neusoft.duanxudong.com.classdemo.R;
import neusoft.duanxudong.com.classdemo.model.User;
import neusoft.duanxudong.com.classdemo.util.GalleryUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by duanxudong on 16/3/26.
 */


public class MeActivity extends BaseActivity implements View.OnClickListener {


    public static final int REQUEST_CHOOSE_FROM_GALLERY = 0x300;
    // PhotoData photoData;
    public static final int REQUEST_TAKE_PHOTO = 0x301;
    public static final int REQUEST_CROP = 0x303;
    Uri mCapturedImageUri;
    String userData;
    User user;
    @Bind(R.id.settings_avatar)
    ImageView head_image;
    @Bind(R.id.nicheng_settings)
    EditText nicheng;
    @Bind(R.id.passwd_settings)
    EditText passwd;
    @Bind(R.id.phon8e_settings)
    EditText phone;
    @Bind(R.id.grade_settings)
    EditText grade;
    @Bind(R.id.ask_add)
    Toolbar toolbar;
    @Bind(R.id.center_tv)
    TextView center_tv;
    @Bind(R.id.ask_bar_tv)

    TextView bar_add;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_center);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        center_tv.setText("个人设置");
        bar_add.setText("提交");


        userData = getPreferenceManager().read(User.class.getName(), "");
        user = new Gson().fromJson(userData, User.class);

        long id = 0;
        id = user.getId();
        initView();
        phone.setText(user.getPhone());
        nicheng.setText(user.getName());
        passwd.setText(user.getPasswd());
        grade.setText(user.getGrade());
        head_image.setOnClickListener(this);


    }


    public void initView() {

        head_image.setOnClickListener(this);
        bar_add.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {


        switch (v.getId()) {

            case R.id.settings_avatar:

                showOptionsDialog(null,
                        new String[]{"拍照", "从相册获取"},
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (i == 0) {
                                    shootPhoto();
                                }

                                if (i == 1) {
                                    pickPhoto();
                                }
                            }
                        });


                break;
            case R.id.ask_bar_tv:


                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Do something here
                        upload();

                    }
                }, 1000);


                break;

        }


    }


    /**
     * Pick from Gallery
     */
    void pickPhoto() {

        Intent toGallery = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        toGallery.setAction(Intent.ACTION_GET_CONTENT);
        toGallery.setType("image/*");
        startActivityForResult(toGallery, REQUEST_CHOOSE_FROM_GALLERY);
    }


    void shootPhoto() {

        String fileName = GalleryUtils.generateImageName();
        ContentValues values = new ContentValues();
        values.put(MediaStore.MediaColumns.TITLE, fileName);
        this.mCapturedImageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                values);

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);


        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, mCapturedImageUri);

        startActivityForResult(cameraIntent, REQUEST_TAKE_PHOTO);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (Activity.RESULT_OK != resultCode) {
            return;
        }

        if (REQUEST_CHOOSE_FROM_GALLERY == requestCode) {

            this.mCapturedImageUri = data.getData();


        }

        if (REQUEST_CHOOSE_FROM_GALLERY == requestCode || REQUEST_TAKE_PHOTO == requestCode) {


            onCrop(mCapturedImageUri);


        }

        if (requestCode == REQUEST_CROP) {

            Bundle extras = data.getExtras();
            Bitmap selectedBitmap = extras.getParcelable("data");
            head_image.setImageBitmap(selectedBitmap);


        }


        log(GalleryUtils.getImagePathByUri(getApplicationContext(), mCapturedImageUri));


    }


    public void upload() {


        dialog = new ProgressDialog(this);

        dialog.show();


        OkHttpClient client = new OkHttpClient();

        final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/jpg");


        if (mCapturedImageUri == null) {
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), "succeed", Toast.LENGTH_LONG).show();

                    finish();

                }
            }, 100);

            Toast.makeText(getApplicationContext(), "无照片", Toast.LENGTH_LONG).show();

        } else {

            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("userID", "" + user.getId())
                    .addFormDataPart("nickname", nicheng.getText().toString())
                    .addFormDataPart("passwd", passwd.getText().toString())
                    .addFormDataPart("phone", phone.getText().toString())
                    .addFormDataPart("grade", grade.getText().toString())
                    .addFormDataPart("file", "logo-square.png",
                            RequestBody.create(MEDIA_TYPE_PNG, new File(GalleryUtils.getFilePath(this, mCapturedImageUri))))
                    .build();

            final Request request = new Request.Builder()
                    .url(getString(R.string.user_info_change))
                    .addHeader("Content-Type", "multipart-form-data")
                    .post(requestBody)
                    .build();

            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                    Log.e("@@", e.getMessage());
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            dialog.dismiss();
                            Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_LONG).show();

                            finish();

                        }
                    }, 3000);

                }

                @Override
                public void onResponse(Call call, Response r) throws IOException {


                    String result = r.body().string();
                    Log.e("@@", result);

                    String head_img = new Gson().fromJson(result, RegLog.class).getUser().getHead_url();
                    Log.e("@@", head_img);

                    User user1 = new User();
                    user1.setHead_url(head_img);
                    user1.setEmail(user1.getEmail());
                    user1.setPasswd(passwd.getText().toString());
                    user1.setName(nicheng.getText().toString());
                    user1.setGrade(grade.getText().toString());
                    user1.setPhone(phone.getText().toString());
//                    String user2 = new Gson().toJson(user1,User.class);
                    getPreferenceManager().removeAll();
//                    userData = getPreferenceManager().read(User.class.getName(), "");
                    getPreferenceManager().save(User.class.getName(), new Gson().toJson(user1));

                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            dialog.dismiss();
                            Toast.makeText(getApplicationContext(), "succeed", Toast.LENGTH_LONG).show();

                            finish();

                        }
                    }, 3000);


                }
            });
        }
    }

    public void onCrop(Uri url) {


        Intent crop_intent = new Intent("com.android.camera.action.CROP");
        crop_intent.setData(url);
        crop_intent.putExtra("crop", "true");
        crop_intent.putExtra("aspectX", 1);
        crop_intent.putExtra("aspectY", 1);
        crop_intent.putExtra("outputX", 96);
        crop_intent.putExtra("outputY", 96);
        crop_intent.putExtra("return-data", true);
        startActivityForResult(crop_intent, REQUEST_CROP);
    }


}
