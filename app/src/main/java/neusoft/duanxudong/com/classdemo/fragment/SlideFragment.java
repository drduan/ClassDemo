package neusoft.duanxudong.com.classdemo.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Field;

import neusoft.duanxudong.com.classdemo.R;
import neusoft.duanxudong.com.classdemo.activity.PlayerActivity;


/**
 * Created by zfeng on 4/8/15.
 */
public class SlideFragment extends Fragment implements View.OnClickListener {
    private ImageView mImageView;
    private TextView mTextView;
    private TextView mPointer;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.slide_fragment, container, false);
        initUI(view);
        initDatas();
        return view;
    }

    private void initUI(View view) {
        mImageView = (ImageView) view.findViewById(R.id.slide_image);
        mTextView = (TextView) view.findViewById(R.id.slide_title);
        mPointer = (TextView) view.findViewById(R.id.slide_title_point);
        mImageView.setOnClickListener(this);
    }

    private void initDatas() {
        Bitmap bitmap = getArguments().getParcelable("slide_image");
        String title = getArguments().getString("slide_title");
        String pointer = getArguments().getString("slide_title_point");
        mImageView.setImageBitmap(bitmap);
        mTextView.setText(title);
        mPointer.setText(pointer);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), PlayerActivity.class);
        intent.putExtra("video_url", getArguments().getString("video_url"));
        startActivity(intent);
    }


    // fill illegal
    @Override
    public void onDetach() {
        super.onDetach();

        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
