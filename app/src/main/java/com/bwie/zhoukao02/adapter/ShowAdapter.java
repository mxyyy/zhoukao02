package com.bwie.zhoukao02.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.zhoukao02.R;
import com.bwie.zhoukao02.bean.ShowBean;


import java.util.List;


public class ShowAdapter  extends RecyclerView.Adapter<ShowAdapter.ViewHolder>{
    private Context context;
    private List<ShowBean.DataBean>list;

    public ShowAdapter(Context context, List<ShowBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = View.inflate(context, R.layout.item_show, null);
        ViewHolder viewHolder=new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getPic_url()).into(holder.imgShow);
        holder.tvShow.setText(list.get(position).getAddress());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgShow;
        private TextView tvShow;
        public ViewHolder(View itemView) {
            super(itemView);
           imgShow= itemView.findViewById(R.id.img_show);
           tvShow= itemView.findViewById(R.id.tv_show);
        }
    }
}
