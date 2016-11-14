package com.example.dragrecyclerview;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.example.dragrecyclerview.Bean.Item;
import com.example.dragrecyclerview.adapter.MyAdapterRecyclerView;
import com.example.dragrecyclerview.adapter.MyItemTouchHelperCallback;
import com.example.dragrecyclerview.interfaces.CallbackItemTouch;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by ZHAO on 2016/11/11.
 */
public class MainActivity extends AppCompatActivity implements CallbackItemTouch {
    private RecyclerView mRecyclerView;
    private MyAdapterRecyclerView myAdapterRecyclerView;
    private ArrayList<Item> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initList();
    }

    private void initData() {
        String[] month = new String[]{
                "一月/January",
                "二月/February",
                "三月/March",
                "四月/April",
                "五月/May",
                "六月/June",
                "七月/July",
                "八月/August",
                "九月/September",
                "十月/October",
                "十一月/November",
                "十二月/December"
        };

        String content = "内容/content";

        mList = new ArrayList<>();
        for (int i = 0; i < month.length; i++) {
            mList.add(new Item(month[i], content));
        }

    }

    private void initList() {

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //可以使用该方法设置间距，但可能会出现拖拽移动后条目间距发生改变，所以建议在条目固定时使用该方法，当条目可以移动时通过item的margin来实现
        //when this method was called and the item can be dragged at the same time, the space between items will change during dragging. In this case, we can set the space through setting the margin of item instead of using this method.
        //  mRecyclerView.addItemDecoration(new SpaceItemDecoration(5));

       //可以换为LinearLayoutManager/GridLayoutManager，StaggeredGridLayoutManager可以结合条目高度wrap_content使用，如果不想过高或过低可以设置max/minlines等属性
        // You can change the LayoutManager, StaggeredGridLayoutManager used combined with wrap_content will be better. If you don't expect the item to be too high or too low, you can set maxlines or minlines for textview.
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        myAdapterRecyclerView = new MyAdapterRecyclerView(mList);
        mRecyclerView.setAdapter(myAdapterRecyclerView);

        //设置recyclerview的条目点击事件
        //set the clicklistener for each item
        myAdapterRecyclerView.setOnItemClickListener(new MyAdapterRecyclerView.OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ToastUtil.showToast(MainActivity.this, position + "条目被点击/This item " + position + " is clicked.");
            }
        });
        //设置recyclerview的条目的子控件的点击事件
        //set the clicklistener for the childview of each item
        myAdapterRecyclerView.setOnPinClickListener(new MyAdapterRecyclerView.OnPinClickListener() {
            @Override
            public void onPinClick(View view, int position) {
                ToastUtil.showToast(MainActivity.this, position + "条目的子控件被点击/A child view of this item " + position + " is clicked.");
            }
        });

        ItemTouchHelper.Callback callback = new MyItemTouchHelperCallback(this);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(mRecyclerView);

    }


    @Override
    public void itemTouchOnMove(int oldPosition, int newPosition) {
        Collections.swap(mList, oldPosition, newPosition);
        myAdapterRecyclerView.notifyItemMoved(oldPosition, newPosition);
    }

}
