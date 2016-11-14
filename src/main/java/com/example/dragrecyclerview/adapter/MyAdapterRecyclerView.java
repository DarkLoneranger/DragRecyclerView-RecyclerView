package com.example.dragrecyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dragrecyclerview.Bean.Item;
import com.example.dragrecyclerview.R;


import java.util.ArrayList;

/**
 * Created by ZHAO on 2016/11/11.
 */
public class MyAdapterRecyclerView extends RecyclerView.Adapter<MyAdapterRecyclerView.MyViewHolder> {

    private ArrayList<Item> mList;
    OnRecyclerItemClickListener mOnItemClickListener = null;
    OnPinClickListener mOnPinClickListener = null;

    //构造方法
    //Constructor
    public MyAdapterRecyclerView(ArrayList<Item> datas) {
        this.mList = datas;
    }

    //在Activity中数据更新后更新界面时调用
    //If you change the data in the Activity, you can call this method to update in Activity directly.
    public void setData(ArrayList<Item> list) {
        mList = list;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    //使用getTag()方法获取item的位置
                    //getTag() will tell you which item was clicked
                    mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
                }
            }
        });
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        //为item中的子控件设置内容
        //set content for child view
        Item item = mList.get(position);
        holder.setTime(item.getDate());
        holder.setDescription(item.getContent());
        holder.itemView.setTag(position);
        //为item中的子控件设置点击事件
        //set OnClickListener for child view of item
        final int currentPosition = position;
        holder.pin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnPinClickListener != null) {
                    mOnPinClickListener.onPinClick(v, currentPosition);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        if (mList == null) {
            return 0;
        } else {
            return mList.size();
        }
    }

    //为item中的子控件设置点击事件的方法
    //the method of setting OnClickListener for child view of item
    public void setOnPinClickListener(OnPinClickListener listener) {
        this.mOnPinClickListener = listener;
    }

    public interface OnPinClickListener {
        public void onPinClick(View view, int position);
    }

    //为item中的设置点击事件的方法
    //the method of setting OnClickListener for each item
    public void setOnItemClickListener(OnRecyclerItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public interface OnRecyclerItemClickListener {
        public void onItemClick(View view, int position);
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTime, tvDescription;
        ImageView pin;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvTime = (TextView) itemView.findViewById(R.id.time);
            tvDescription = (TextView) itemView.findViewById(R.id.memo_text);
            pin = (ImageView) itemView.findViewById(R.id.pin);
        }

        public void setTime(String time) {
            tvTime.setText(time);
        }

        public void setDescription(String description) {
            tvDescription.setText(description);
        }
    }


}
