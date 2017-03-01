package com.googl.xcdq.mytest;

import android.content.ClipData;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xcdq on 2017/2/20.
 */

public class Myadapter extends RecyclerView.Adapter<Myadapter.ViewHoider> {
    private List<String> datasSet;
    private List<Integer> mHeight;

    //构造器，接受数据集
    public Myadapter(List<String> datas) {
        datasSet = datas;
//        mHeight = new ArrayList<>();
//        //随机获取一个mHeight值
//        for (int i = 0; i < datasSet.size(); i++) {
//            mHeight.add((int) (100 + Math.random() * 300));
//        }
    }

    @Override
    public ViewHoider onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载布局文件
        View vh = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_adapter, parent, false);
        ViewHoider v = new ViewHoider(vh);
        return v;
    }

    @Override
    public void onBindViewHolder(final ViewHoider holder, int position) {
        //将数据填充到具体的view中
        holder.mTextView.setText(datasSet.get(position));
//        //绑定数据的同时，修改每个ItemView的高度
//        ViewGroup.LayoutParams vl = holder.itemView.getLayoutParams();
//        vl.height = mHeight.get(position);
//        holder.itemView.setLayoutParams(vl);
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, position);
                }
            });
        }

        if (mOnItemLongClickListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = holder.getLayoutPosition();
                    mOnItemLongClickListener.onItemLongClick(holder.itemView, position);
                    return true;
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return datasSet.size();
    }

    static class ViewHoider extends RecyclerView.ViewHolder {
        public TextView mTextView;

        public ViewHoider(View itemView) {
            super(itemView);
            //由于itemView是item的布局文件，我们需要的是里面的textView，因此利用itemView.findViewById获
            //取里面的textView实例，后面通过onBindViewHolder方法能直接填充数据到每一个textView了
            mTextView = (TextView) itemView.findViewById(R.id.textView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setmOnItemLongClickListener(OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }

    //从RecyclerView上移出数据
    public void revomeDatas(int position) {
        datasSet.remove(position);
        notifyItemRemoved(position);
    }

    //添加数据到RecyclerView上
    public void addDatas(int position) {
        datasSet.add(position, "add on");
        notifyItemInserted(position);
    }

    public void chageDatas(int position) {
        datasSet.set(position, "Item has changed");
        notifyItemChanged(position);
    }

}
