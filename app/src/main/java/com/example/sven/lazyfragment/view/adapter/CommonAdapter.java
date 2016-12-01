package com.example.sven.lazyfragment.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public abstract class CommonAdapter<T> extends RecyclerView.Adapter<CommonViewHolder> {


    public List<T> list;

    private LayoutInflater mInflater;

    private int layoutId;

    public CommonAdapter(Context mContext, List<T> list, int layoutId) {
        super();
        this.list = list;
        this.layoutId = layoutId;
        mInflater = LayoutInflater.from(mContext);
    }

    public void refresh(List<T> list) {
        if (this.list != null) {
            this.list.clear();
            this.list.addAll(list);
        } else {
            this.list = list;
        }
        notifyDataSetChanged();
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /**
         * 加载item布局，生成ViewHolder并返回
         */
        View itemView = mInflater.inflate(layoutId, parent, false);
        return new CommonViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final CommonViewHolder holder, int position) {

        init(holder, list.get(position), position);
        /**
         * item单击事件监听
         */
        if (onItemClickLinster != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickLinster.onItemClick(
                            holder.itemView, holder.getLayoutPosition());//或者 position 有添加删除最好用 getLayoutPosition
                }
            });
        }

        /**
         * item长按事件监听
         */
        if (onItemLongClickLinster != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemLongClickLinster.onItemLongClick(
                            holder.itemView, holder.getLayoutPosition());
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public void addItem(T t, int position) {
        list.add(position, t);
        notifyItemInserted(position);
    }

    void removeItem(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    public abstract void init(CommonViewHolder holder, T bean, int position);

    public interface OnItemClickLinster {
        void onItemClick(View view, int position);
    }

    private OnItemClickLinster onItemClickLinster;

    public void setOnItemClickLinster(OnItemClickLinster onItemClickLinster) {
        this.onItemClickLinster = onItemClickLinster;
    }

    interface OnItemLongClickLinster {
        void onItemLongClick(View view, int position);
    }

    private OnItemLongClickLinster onItemLongClickLinster;

    public void setOnItemLongClickLinster(OnItemLongClickLinster onItemLongClickLinster) {
        this.onItemLongClickLinster = onItemLongClickLinster;
    }


}
