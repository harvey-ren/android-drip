package com.rqphp.publib.view;

/**
 * 滚轮选择器Item项被选中时监听接口
 * Created by Harvey on 2017/11/20.
 */

public interface OnItemSelectedListener {

    /**
     * 当滚轮选择器数据项被选中时回调该方法
     * 滚动选择器滚动停止后会回调该方法并将当前选中的数据和数据在数据列表中对应的位置返回
     *
     * @param picker   滚轮选择器
     * @param data     当前选中的数据
     * @param position 当前选中的数据在数据列表中的位置
     */
    void onItemSelected(WheelPicker picker, Object data, int position);
}
