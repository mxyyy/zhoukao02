package com.bwie.zhoukao02.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bwie.zhoukao02.R;
import com.bwie.zhoukao02.bean.NineBean;


import java.util.List;



public class NineAdapter extends RecyclerView.Adapter<NineAdapter.ViewHolder>{
    private Context context;
    private List<NineBean.DataBean> list;

    public NineAdapter(Context context, List<NineBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = View.inflate(context, R.layout.item_nine, null);
        ViewHolder viewHolder=new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getIcon()).into(holder.imgNine);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgNine;
        public ViewHolder(View itemView) {
            super(itemView);
            imgNine= itemView.findViewById(R.id.img_nine);

        }
    }
}
