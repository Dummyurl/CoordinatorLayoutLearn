package com.superman.coordinatorlayout.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.superman.coordinatorlayout.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者 Superman
 * 日期 2018/11/28 10:17.
 * 文件 CoordinatorLayoutLearn
 * 描述
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.RecycleViewHolder> {
    private List<String> data = new ArrayList<>();

    public ItemAdapter(List<String> data) {
        this.data = data;
    }

    @Override
    public RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new RecycleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecycleViewHolder holder, int position) {
        holder.textView.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class RecycleViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public RecycleViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
