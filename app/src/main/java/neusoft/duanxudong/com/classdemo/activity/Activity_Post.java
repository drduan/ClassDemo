package neusoft.duanxudong.com.classdemo.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import butterknife.Bind;
import butterknife.ButterKnife;
import neusoft.duanxudong.com.classdemo.R;
import neusoft.duanxudong.com.classdemo.util.ScreenUtils;
import neusoft.duanxudong.com.classdemo.util.Utils;

/**
 * Created by duanxudong on 16/4/16.
 */
public class Activity_Post extends BaseActivity {

    @Bind(R.id.addImageButton)
    ImageView mAddImageButton;
    @Bind(R.id.imageLayout)
    RelativeLayout mImageLayout;
    @Bind(R.id.deleteImageButton)
    ImageButton mDeleteImageButton;
    private Uri mUploadImageUri;
    @Bind(R.id.time_ac)
    EditText time_ac;
    @Bind(R.id.state)
    EditText mState;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.imageView)
    ImageView mImageView;

    private File mUpLoadImageFile;
    private static final int TAKE_PHOTO = 1;
    private static final int CROP_PHOTO = 2;
    private static final int GET_FROM_ALBUM = 3;
    private boolean hasImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_post);
        ButterKnife.bind(this);

        initToolbar("发布");
        initAddImageButton();
        initDeleteImageButton();
        initUploadImageUri();

        time_ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog mTimePicker;
                Calendar mcurrentTime = Calendar.getInstance();
                int year = mcurrentTime.get(Calendar.YEAR);
                int monthOfYear = mcurrentTime.get(Calendar.MONTH);
                int dayOfMonth = mcurrentTime.get(Calendar.DAY_OF_MONTH);


                mTimePicker = new DatePickerDialog(Activity_Post.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        time_ac.setText(year + "-" + monthOfYear + "-" + dayOfMonth);
                    }
                }, year, monthOfYear, dayOfMonth);

                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

    }

    private void initToolbar(String title) {
//        mToolbar.setTitle("发布");
        setSupportActionBar(mToolbar);
        mToolbar.setTitle(title);
        mToolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void initDeleteImageButton() {
        mDeleteImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hasImage = false;
                ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 0, 1.0f, 0, Animation.RELATIVE_TO_SELF, 0.5F, Animation.RELATIVE_TO_SELF, 0.5F);
                scaleAnimation.setDuration(200);
                scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        mImageLayout.setVisibility(View.GONE);
                        mAddImageButton.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                mImageLayout.startAnimation(scaleAnimation);
            }
        });
    }


    private void initAddImageButton() {
        mAddImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Activity_Post.this);
                builder.setItems(new String[]{"拍照", "相册"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, mUploadImageUri);
                            startActivityForResult(intent, TAKE_PHOTO);
                        } else {

                            Intent intent = new Intent(Intent.ACTION_GET_CONTENT).setType("image/*").putExtra(MediaStore.EXTRA_OUTPUT, mUploadImageUri);
                            startActivityForResult(intent,
                                    GET_FROM_ALBUM);
                        }
                    }
                });
                builder.create().show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    cropImageIntent(mUploadImageUri);
                }
                break;

            case CROP_PHOTO:
                if (resultCode == RESULT_OK) {
                    mAddImageButton.setVisibility(View.GONE);
                    mImageLayout.setVisibility(View.VISIBLE);

                    mImageView.invalidate();
                    Glide.with(this).load(mUploadImageUri).override(ScreenUtils.dp2px(200f), ScreenUtils.dp2px(200f)).centerCrop().into(mImageView);


                    hasImage = true;
                }
                break;

            case GET_FROM_ALBUM:
                if (resultCode == RESULT_OK) {
                    Uri originalUri = data.getData();
                    if (originalUri == null) {
                        pickImageError();
                        return;
                    }
                    String imagePath = Utils.getImageAbsolutePath(this, originalUri);
                    if (imagePath == null) {
                        pickImageError();
                        return;
                    }
                    Uri imageUri = Uri.fromFile(new File(imagePath));
                    cropImageIntent(imageUri);
                }
                break;
        }
    }


    private void initUploadImageUri() {
        mUpLoadImageFile = new File(Environment.getExternalStorageDirectory() + "/STUer", "upload.jpg");
        try {
            if (!mUpLoadImageFile.exists()) {
                mUpLoadImageFile.mkdirs();
            }
            if (mUpLoadImageFile.exists()) {
                mUpLoadImageFile.delete();
            }
            mUpLoadImageFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mUploadImageUri = Uri.fromFile(mUpLoadImageFile);
    }

    private void cropImageIntent(Uri imageUri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(imageUri, "image/*");
        intent.putExtra("scale", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mUploadImageUri);
        startActivityForResult(intent, CROP_PHOTO);
    }


    private void pickImageError() {
        Toast.makeText(this, "兼容性有问题...请选择其他方式...", Toast.LENGTH_SHORT).show();
    }


//
//    private void sellPost() {
//        mSellInfo.setState(mState.getText().toString());
//        if (hasImage) {
//            mSellInfo.setPicAddress(AppInfoUtils.getUniqueCode());
//            mPresenter.doPostWithPhoto(mUpLoadImageFile, mSellInfo);
//        } else {
//            mSellInfo.setPicAddress("empty");
//            mPresenter.doPost(mSellInfo);
//        }
//    }


//    @Override
//    public void netError() {
//        Toast.makeText(this, "網絡連接失敗", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void doAfterPostSuccessfully() {
//        setResult(RESULT_OK);
//
//        Toast.makeText(this, "发送成功", Toast.LENGTH_SHORT).show();
//        finish();
//    }

}