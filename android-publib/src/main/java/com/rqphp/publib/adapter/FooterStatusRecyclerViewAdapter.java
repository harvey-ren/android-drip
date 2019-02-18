package com.rqphp.publib.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rqphp.publib.R;
import com.rqphp.publib.base.BaseViewHolder;
import com.rqphp.publib.view.ITextView;


/**
 * Created by Harvey on 2018/1/23.
 */

public abstract class FooterStatusRecyclerViewAdapter extends HeaderFooterRecyclerViewAdapter<BaseViewHolder> {

    /**
     * 0 加载更多
     * 1 加载中...
     * 2 没有更多数据了
     */
    private int mFooterStatus = -1;


    @Override
    protected final int getFooterItemViewType(int position) {
        return mFooterStatus;
    }

    /**
     * 隐藏footer
     */
    public final void hideFooter() {
        try {
            if (mFooterStatus != -1) {
                mFooterStatus = -1;
                notifyFooterItemRemoved(0);
            }
        } catch (Exception e) {
        }
    }

    /**
     * footer 正常状态
     */
    public final void setFooterNormal() {
        try {
            if (mFooterStatus == -1) {
                mFooterStatus = 0;
                notifyFooterItemInserted(0);
            } else {
                mFooterStatus = 0;
                notifyFooterItemChanged(0);
            }
        } catch (Exception e) {

        }
    }

    /**
     * footer loading 状态
     */
    public final void setFooterLoading() {
        try {
            if (mFooterStatus == -1) {
                mFooterStatus = 1;
                notifyFooterItemInserted(0);
            } else {
                mFooterStatus = 1;
                notifyFooterItemChanged(0);
            }
        } catch (Exception e) {
        }
    }

    /**
     * footer 没有更多了
     */
    public final void setFooterNoMore() {
        try {
            if (mFooterStatus == -1) {
                mFooterStatus = 2;
                notifyFooterItemInserted(0);
            } else {
                mFooterStatus = 2;
                notifyFooterItemChanged(0);
            }
        } catch (Exception e) {
        }
    }

    @Override
    protected final int getFooterItemCount() {
        return mFooterStatus == -1 ? 0 : 1;
    }

    @Override
    protected BaseViewHolder onCreateFooterItemViewHolder(ViewGroup parent, int footerViewType) {
        View footerView = null;
        ITextView tipView;
        try {
            switch (footerViewType) {
                case 0://上拉可加载更多
                    footerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer_tip, parent, false);
                    tipView = footerView.findViewById(R.id.tv_footer_hint);
                    tipView.setText(R.string.footer_normal);
                    break;
                case 1://加载中...
                    footerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer_status_loading, parent, false);
                    break;
                case 2://没有更多数据了
                    footerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer_tip, parent, false);
                    tipView = footerView.findViewById(R.id.tv_footer_hint);
                    tipView.setText(R.string.footer_no_more);
                    break;
            }
        } catch (Exception e) {
        }
        return new BaseViewHolder(footerView);
    }

}
