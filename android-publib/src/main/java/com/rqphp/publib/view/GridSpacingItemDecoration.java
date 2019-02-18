package com.rqphp.publib.view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Harvey on 2017/11/3.
 * <p>
 * 九宫格的间隔处理类
 */
public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

    /**
     * 每行的个数
     */
    private int numPerRow;

    /**
     * 间距
     */
    private int spacing;

    /**
     * 是否包括边界
     */
    private boolean isIncludeEdge;

    public GridSpacingItemDecoration(int numPerRow, int spacing, boolean isIncludeEdge) {
        this.numPerRow = numPerRow;
        this.spacing = spacing;
        this.isIncludeEdge = isIncludeEdge;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);

        //列数
        int column = position % numPerRow;

        if (isIncludeEdge) {
            if (column == 0) {
                outRect.left = spacing;
            }
            if (position < numPerRow) {
                outRect.top = spacing;
            }
            outRect.right = spacing;
            outRect.bottom = spacing;
        } else {
            if (column < numPerRow - 1) {
                outRect.right = spacing;
            }
            if (position >= numPerRow) {
                outRect.top = spacing;
            }
        }
    }
}
