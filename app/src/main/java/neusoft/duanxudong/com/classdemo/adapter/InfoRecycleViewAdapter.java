package neusoft.duanxudong.com.classdemo.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.DrawableUtils;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;
//import com.squareup.picasso.Picasso;

import neusoft.duanxudong.com.classdemo.R;
import neusoft.duanxudong.com.classdemo.activity.DCApplication;

/**
 * Created by lisheng on 2016/3/28.
 */
public class InfoRecycleViewAdapter extends UltimateViewAdapter<InfoRecycleViewAdapter.InfoViewHolder> {

    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private InfoViewHolder mInfoViewHolder;

    private String[] titles;
    private String[] imgUlrs;

    public InfoRecycleViewAdapter(Context context, String[] titles, String[] imgUlrs) {
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        this.titles = titles;
        this.imgUlrs = imgUlrs;
    }

    @Override
    public InfoViewHolder getViewHolder(View view) {
        return new InfoViewHolder(view);
    }

    @Override
    public InfoViewHolder onCreateViewHolder(ViewGroup parent) {
        return new InfoViewHolder(mLayoutInflater.inflate(R.layout.item_info, parent, false));
    }

    @Override
    public int getAdapterItemCount() {
        return titles ==null ? 0 : titles.length;
    }

    @Override
    public long generateHeaderId(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(InfoViewHolder holder, int position) {
        holder.tv_info_title.setText(titles[position]);
        Glide.with(mContext).load(imgUlrs[position]).into(holder.iv_info_img).onLoadFailed(null,ResourcesCompat.getDrawable(DCApplication.context.getResources(),R.drawable.head,null));


    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_info,null);
        TextView textView= (TextView) view.findViewById(R.id.info_title);
        return null;
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    class InfoViewHolder extends UltimateRecyclerviewViewHolder {

        TextView tv_info_title;
        ImageView iv_info_img;

        public InfoViewHolder(View itemView) {
            super(itemView);
            tv_info_title = (TextView) itemView.findViewById(R.id.tv_info_title);

            iv_info_img = (ImageView) itemView.findViewById(R.id.iv_info_img);
        }
    }
}
