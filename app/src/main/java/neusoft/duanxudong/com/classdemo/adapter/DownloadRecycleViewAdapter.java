package neusoft.duanxudong.com.classdemo.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import neusoft.duanxudong.com.classdemo.R;
import neusoft.duanxudong.com.classdemo.activity.DownloadActivity;

/**
 * Created by lisheng on 2016/3/24.
 */
public class DownloadRecycleViewAdapter extends RecyclerView.Adapter<DownloadRecycleViewAdapter.NormalDownloadViewHolder> {
    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private String[] names;
    private String[] uploaders;
    private int[] sizes;
    private String[] uris;
    private Bitmap[] btm;

    public DownloadRecycleViewAdapter(Context context, String[] names, String[] uploaders, int[] sizes, String[] uris, Bitmap[] btm) {
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(mContext);
        this.names = names;
        this.uploaders = uploaders;
        this.sizes = sizes;
        this.uris = uris;
        this.btm = btm;
    }


    @Override
    public NormalDownloadViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NormalDownloadViewHolder(mLayoutInflater.inflate(R.layout.item_download, parent, false));
    }

    @Override
    public void onBindViewHolder(NormalDownloadViewHolder holder, final int position) {
//        holder.
        holder.tv_name.setText(names[position]);
        holder.tv_uploader.setText(uploaders[position]);
        holder.tv_size.setText(sizes[position] + "");
        holder.file_img.setImageBitmap(btm[position]);

        holder.btn_download.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                DownloadActivity.download(uris[position],names[position]);
            }
        });
    }

    @Override
    public int getItemCount() {
        return names == null ? 0 : names.length;
    }

    public static class NormalDownloadViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name;
        TextView tv_uploader;
        TextView tv_size;
        Button btn_download;
        ImageView file_img;

        public NormalDownloadViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_uploader = (TextView) itemView.findViewById(R.id.tv_uploader);
            tv_size = (TextView) itemView.findViewById(R.id.tv_size);
            btn_download = (Button) itemView.findViewById(R.id.btn_download);
            file_img = (ImageView) itemView.findViewById(R.id.file_img);
        }
    }

}
