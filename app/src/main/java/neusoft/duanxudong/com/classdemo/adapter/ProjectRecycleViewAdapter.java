package neusoft.duanxudong.com.classdemo.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import neusoft.duanxudong.com.classdemo.R;
import neusoft.duanxudong.com.classdemo.activity.ProjectInfo;
import neusoft.duanxudong.com.classdemo.util.LoggingUtils;


public class ProjectRecycleViewAdapter extends
        UltimateViewAdapter<ProjectRecycleViewAdapter.SimpleAdapterViewHolder> {


    private List<String> statuslist;
    private List<Date> times;
    private List<String> nameList;
    private Context context;
    private LayoutInflater mLayoutInflater;
    private List<String> p_url;


    public ProjectRecycleViewAdapter(Context context, List<String> nameList, List<Date> times, List<String> stringList, List<String> p_url) {
        this.context = context;
        this.statuslist = stringList;
        this.times = times;
        this.nameList = nameList;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.p_url = p_url;
    }


    @Override
    public void onBindViewHolder(final
                                 SimpleAdapterViewHolder holder, final int position) {

        holder.pj_name.setText(nameList.get(position));
        if (statuslist.get(position).equals("0")) {
            holder.pj_status.setText("即将开始");
        } else if (statuslist.get(position).equals("1")) {
            holder.pj_status.setText("已经开始");
        } else if (statuslist.get(position).equals("2")) {
            holder.pj_status.setText("已经结束");
        }

        String s = DateFormat.getDateInstance(DateFormat.FULL).format(times.get(position));
        holder.pj_time.setText(s);
        holder.setIsRecyclable(true);
        holder.arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProjectInfo.class);
                intent.putExtra("url", p_url.get(position));
                context.startActivity(intent);

            }
        });

//        holder.heart.findViewById(R.id.heart_img).setPressed(true);
        holder.heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                holder.heart.findViewById(R.id.heart_img).setPressed(true);

                Toast.makeText(context, "收藏成功", Toast.LENGTH_LONG).show();


            }
        });
        holder.join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.join.findViewById(R.id.join_img).setPressed(true);


                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("是否确定参与");
                builder.setTitle("提示");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context,"成功",Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });


    }

    @Override
    public int getAdapterItemCount() {


        return nameList.size();
    }

    @Override
    public SimpleAdapterViewHolder getViewHolder(View view) {
        return new SimpleAdapterViewHolder(view);

    }

    @Override
    public SimpleAdapterViewHolder onCreateViewHolder(ViewGroup parent) {

        return new SimpleAdapterViewHolder(mLayoutInflater.inflate(R.layout.project_item, parent, false));
    }


    @Override
    public void toggleSelection(int pos) {
        super.toggleSelection(pos);
    }

    @Override
    public void setSelected(int pos) {
        super.setSelected(pos);
    }

    @Override
    public void clearSelection(int pos) {
        super.clearSelection(pos);
    }


    @Override
    public long generateHeaderId(int position) {

        return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup viewGroup) {


        return null;

    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder viewHolder,
                                       int position) {

        //不知道杂么用


    }


    public static class SimpleAdapterViewHolder extends
            UltimateRecyclerviewViewHolder {

        TextView pj_name;
        TextView pj_time;
        TextView pj_status;
        ImageView arrow;


        LinearLayout heart;
        LinearLayout join;


        public SimpleAdapterViewHolder(View itemView) {
            super(itemView);


            pj_name = (TextView) itemView.findViewById(
                    R.id.pj_name);
            pj_time = (TextView) itemView.findViewById(R.id.pj_time);
            pj_status = (TextView) itemView.findViewById(R.id.pj_status);

            arrow = (ImageView) itemView.findViewById(R.id.arrow);
            heart = (LinearLayout) itemView.findViewById(R.id.heart);
            join = (LinearLayout) itemView.findViewById(R.id.join);


        }


        @Override
        public void onItemSelected() {
            //失败

            itemView.setBackgroundColor(Color.BLUE);
        }

        @Override
        public void onItemClear() {
            //失败
            itemView.setBackgroundColor(0);
        }
    }


}