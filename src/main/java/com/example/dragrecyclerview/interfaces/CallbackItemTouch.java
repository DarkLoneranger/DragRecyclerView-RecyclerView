package com.example.dragrecyclerview.interfaces;

/**
 * Created by ZHAO on 2016/11/11.
 */
public interface CallbackItemTouch {

    /**
     * Called when an item has been dragged
     * @param oldPosition start position
     * @param newPosition end position
     */
    void itemTouchOnMove(int oldPosition, int newPosition);
}
