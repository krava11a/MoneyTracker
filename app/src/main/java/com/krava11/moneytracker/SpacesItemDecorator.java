package com.krava11.moneytracker;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

class SpacesItemDecorator extends RecyclerView.ItemDecoration {
    private int left;
    private int right;
    private int top;
    private int bottom;

    public SpacesItemDecorator(int left, int right, int top, int bottom) {
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public int getTop() {
        return top;
    }

    public int getBottom() {
        return bottom;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.bottom=bottom;
        outRect.top=top;
        outRect.right=right;
        outRect.left=left;
    }
}
