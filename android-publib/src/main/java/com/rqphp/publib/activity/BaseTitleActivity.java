package com.rqphp.publib.activity;


import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rqphp.publib.R;
import com.rqphp.publib.config.FrameworkConfig;

/**
 * Created by Harvey on 2017/7/25.
 */
public abstract class BaseTitleActivity extends BaseActivity {

    private LinearLayout mTitleContainer;

    private FrameLayout mMainContainer;

    private LinearLayout mLayoutLeft;

    private LinearLayout mLayoutRight;

    private RelativeLayout mLayoutTitle;

    /**
     * 标题
     */
    private TextView mTvTitle;

    /**
     * 是否显示title
     */
    public boolean mIsShowTitle = true;

    /**
     * 是否显示返回图标
     */
    public boolean mIsShowBackIcon = true;


    @Override
    protected void postSetContentView() {
        super.postSetContentView();
        initTitleView();
        initMainView();
        //  initWholeView();
    }

    @Override
    protected int setContentViewId() {
        return R.layout.activity_layout_base;
    }

    /**
     * 初始化title布局
     */
    private void initTitleView() {
        mTitleContainer = findViewById(R.id.title_container);
        mLayoutTitle = findViewById(R.id.layout_title);
        mLayoutTitle.setBackgroundColor(getResources().getColor(setTitleBgColor()));
        mLayoutLeft = findViewById(R.id.layout_left);
        mLayoutRight = findViewById(R.id.layout_right);

        mTvTitle = findViewById(R.id.tv_title);

        if (isShowTitle()) {
            mTitleContainer.setVisibility(View.VISIBLE);
            if (isShowBackIcon()) {
                addLeftBackIcon();
            }
        } else {
            mTitleContainer.setVisibility(View.GONE);
        }
    }

//    private void initWholeView() {
//        View view = findViewById(R.id.layout_whole);
//        onInit(view);
//    }

    /**
     *
     */
    private void initMainView() {
        mMainContainer = findViewById(R.id.main_container);
        getLayoutInflater().inflate(setLayoutResId(), mMainContainer);
    }


    /**
     * 设置布局文件
     *
     * @return
     */
    protected abstract int setLayoutResId();


    /**
     * 设置标题栏文字
     *
     * @param title
     */
    public void setTitleText(String title) {
        this.setTitleTextAndColor(title, getResources().getColor(setTitleTextColor()));
    }

    /**
     * 设置标题字体颜色
     *
     * @return
     */
    public int setTitleTextColor() {
        int titleTextColor = FrameworkConfig.getIFrameworkConfig().getFrameworkResConfig().getTitleTextColor();
        if (titleTextColor == 0) {
            titleTextColor = R.color.color_white;
        }
        return titleTextColor;
    }


    /**
     * 设置标题栏文字和文字颜色
     *
     * @param title
     * @param color
     */
    private void setTitleTextAndColor(String title, int color) {
        mTvTitle.setText(title);
        mTvTitle.setTextColor(color);
    }

    /**
     * 设置title的背景颜色
     *
     * @return
     */
    public int setTitleBgColor() {
        int titleBgColor = FrameworkConfig.getIFrameworkConfig().getFrameworkResConfig().getTitleBgColor();
        if (titleBgColor == 0) {
            titleBgColor = R.color.color_blue_35;
        }
        return titleBgColor;
    }


    /**
     * 设置左边返回图标
     */
    public void addLeftBackIcon() {
        createActionBarIcon(mLayoutLeft, setBackIcon());
        mLayoutLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    private int setBackIcon() {
        int backIcon = FrameworkConfig.getIFrameworkConfig().getFrameworkResConfig().getBackIcon();
        if (backIcon == 0) {
            backIcon = R.drawable.ic_back;
        }
        return backIcon;

    }

    public LinearLayout addRightIcon(int drawableID) {
        if (drawableID != -1) {
            createActionBarIcon(mLayoutRight, drawableID);
        }
        return mLayoutRight;
    }

    public void hiddenBackIcon() {
        if (imgLeft != null) {
            imgLeft.setVisibility(View.GONE);
        }
    }

    ImageView imgLeft;

    /**
     * 设置标题栏左右图标
     */
    public View createActionBarIcon(ViewGroup root, int resImageId) {
        View view = getLayoutInflater().inflate(R.layout.view_title_img, root);
        imgLeft = view.findViewById(R.id.img_title);
        imgLeft.setImageResource(resImageId);
        return view;
    }

    /**
     * 设置是否显示title
     *
     * @return
     */
    public boolean isShowTitle() {
        return mIsShowTitle;
    }


    /**
     * 设置是否显示title
     *
     * @return
     */
    public boolean isShowBackIcon() {
        return mIsShowBackIcon;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
