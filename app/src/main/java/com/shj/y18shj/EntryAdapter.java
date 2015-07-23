package com.shj.y18shj;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.Collection;
import java.util.List;

/**
 * Created by jash
 * Date: 15-7-17
 * Time: 下午4:52
 */
 //新形势的adapter，继承RecyclerView.Adapter
public class EntryAdapter extends RecyclerView.Adapter<EntryAdapter.ViewHolder>{
    private Context context;
    private List<Entry> entries;//详情页面的集合对象

    public EntryAdapter(Context context, List<Entry> entries) {
        this.context = context;
        this.entries = entries;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {//相当于getView方法
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {//与viewholder绑定
        Entry entry = entries.get(position);
        holder.text.setText(entry.getTitle());
        BitmapHelper.getUtils().display(holder.image, "http://www.yi18.net/" + entry.getImg());
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    public void addAll(Collection<? extends Entry> collection){
        int size = entries.size();
        entries.addAll(collection);
        //为了触发动画
        notifyItemRangeInserted(size, collection.size());
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{//ViewHolder继承了RecyclerView.ViewHolder??
        @ViewInject(R.id.item_text)
        private TextView text;
        @ViewInject(R.id.item_image)
        private ImageView image;
        public ViewHolder(View itemView) {
            super(itemView);
            ViewUtils.inject(this, itemView);
        }
    }
}
