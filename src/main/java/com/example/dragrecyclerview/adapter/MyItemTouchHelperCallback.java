package com.example.dragrecyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.dragrecyclerview.interfaces.CallbackItemTouch;
/**
 * Created by ZHAO on 2016/11/11.
 */

public class MyItemTouchHelperCallback extends ItemTouchHelper.Callback {

    CallbackItemTouch callbackItemTouch;

    public MyItemTouchHelperCallback(CallbackItemTouch callbackItemTouch){
        this.callbackItemTouch = callbackItemTouch;
    }
    //是否开启长按拖拽
    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }
    //是否开启侧拉删除
    @Override
    public boolean isItemViewSwipeEnabled() {
        return false;
    }

    //设置移动的方向，如果只想实现垂直方向的拖拽可以只使用UP | DOWN
    //set the orientation of drag, if you just want the item to move in vertical orientation, you can just use UP | DOWN
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN|ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT;
        return makeFlag( ItemTouchHelper.ACTION_STATE_DRAG , dragFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        callbackItemTouch.itemTouchOnMove(viewHolder.getAdapterPosition(),target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

    }
}
