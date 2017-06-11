package com.bawei.lvwenjing.xiangmu_er_zhoukao;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by lenovo-pc on 2017/5/23.
 */

public class ResLiuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<Beans> list;


    public ResLiuAdapter(Context context, List<Beans> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder viewHolder = null;
        //根据viewType生成viewHolder
        switch (viewType) {
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hords, null);
                viewHolder = new VRescyView(view);
                break;
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_two_hordes, null);
                viewHolder = new CRescyView(view);
                break;
        }
        return viewHolder;

    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //根据条目的类型给holder中的控件填充数据
        int itemViewType = getItemViewType(position);
        switch (itemViewType) {
            case 0:
                VRescyView vholder = (VRescyView) holder;
                Glide.with(context).load(list.get(position).getPic()).into(vholder.iv);
                break;
            case 1:
                CRescyView choadr = (CRescyView) holder;
                String s = list.get(position).getPic();
                String[] split = s.split("\\|");
                Glide.with(context).load(split[0]).into(choadr.iv1);

                break;

        }

    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getType() == 1) {
            return 0;
        } else {
            return 1;
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class VRescyView extends RecyclerView.ViewHolder {

        TextView title;
        TextView infor;
        ImageView iv;

        public VRescyView(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.imager_ones);
        }
    }

    class CRescyView extends RecyclerView.ViewHolder {


        ImageView iv1;
        ImageView iv2;
        TextView tv;


        public CRescyView(View itemView) {
            super(itemView);
            iv1 = (ImageView) itemView.findViewById(R.id.iv1s);

        }
    }


}