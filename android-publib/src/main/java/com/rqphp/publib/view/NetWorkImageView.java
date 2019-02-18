package com.rqphp.publib.view;


import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.rqphp.publib.R;

import java.io.File;


/**
 * Created by Harvey on 2017/8/28.
 */

public class NetWorkImageView extends AppCompatImageView {


    /**
     * 默认图片的id
     */
    private int mDefaultImageId;


    /**
     * 加载出错图片的id
     */
    private int mErrorImageId;

    private Context mContext;

    /**
     * 默认缩放类型
     */
    private ScaleType mScaleType = ScaleType.CENTER_CROP;

    /**
     * 圆角
     */
    private int mRound;


    /**
     * 图片的类型，圆形or圆角
     */
    private int mType = TYPE_ROUND;

    private static final int TYPE_ROUND = 0;

    private static final int TYPE_CIRCLE = 1;


    public NetWorkImageView(Context context) {
        this(context, null);
    }

    public NetWorkImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NetWorkImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NetWorkImageView);
            mDefaultImageId = typedArray.getResourceId(R.styleable.NetWorkImageView_defaultImg, 0);
            mErrorImageId = typedArray.getResourceId(R.styleable.NetWorkImageView_errorImg, 0);
            mRound = typedArray.getDimensionPixelOffset(R.styleable.NetWorkImageView_round, 0);
            mType = typedArray.getInt(R.styleable.NetWorkImageView_type, 0);

            if (typedArray != null) {
                typedArray.recycle();
            }
        }
    }


    /**
     * 设置圆角
     *
     * @param round
     */
    public void setRound(int round) {
        mRound = round;
    }


    /**
     * 设置默认图片
     *
     * @param defaultImageId
     */
    public void setDefaultImageId(int defaultImageId) {
        mDefaultImageId = defaultImageId;
    }


    /**
     * 设置出错时的图片
     *
     * @param errorImageId
     */
    public void setErrorImageId(int errorImageId) {
        this.mErrorImageId = errorImageId;
    }


    /**
     * 设置缩放类型
     *
     * @param scaleType
     */
    public void setScaleType(ScaleType scaleType) {
        mScaleType = scaleType;
    }


    /**
     * 设置图片URL
     *
     * @param url
     */
    public void setImgUrl(String url) {

        if (url == null)
            url = "";

        String newUrl = url;

        if (!newUrl.equals("")) {
            newUrl = url.replace("\\", "/");
        }

        RequestOptions requestOptions = new RequestOptions();

        requestOptions.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
        if (mDefaultImageId != 0) {
            requestOptions.placeholder(mDefaultImageId);
        }

        if (mErrorImageId != 0) {
            requestOptions.error(mErrorImageId);
        }

        if (mScaleType == ScaleType.CENTER_CROP) {
            if (mType == TYPE_ROUND) {
                requestOptions.transforms(new CenterCrop(), new RoundedCorners(mRound));
            } else if (mType == TYPE_CIRCLE) {
                requestOptions.transforms(new CenterCrop(), new CircleCrop());
            }
        } else {
            if (mType == TYPE_ROUND) {
                requestOptions.transforms(new FitCenter(), new RoundedCorners(mRound));
            } else if (mType == TYPE_CIRCLE) {
                requestOptions.transforms(new FitCenter(), new CircleCrop());
            }
        }

        RequestManager requestManager = Glide.with(mContext);
        String lastStr = newUrl.substring(newUrl.length() - 4).toLowerCase();
        if (lastStr.equals(".gif")) {
            requestManager.asGif();
        } else {
            requestManager.asBitmap();
        }
        DrawableTransitionOptions drawableTransitionOptions = new DrawableTransitionOptions().crossFade(300);
        requestManager.load(url).transition(drawableTransitionOptions).apply(requestOptions).into(this);
    }

    /**
     * @param file
     */
    public void setFile(File file) {

        RequestBuilder requestBuilder = Glide.with(mContext).load(file);
        RequestOptions requestOptions = new RequestOptions();
        if (mScaleType == ScaleType.CENTER_CROP) {
            requestOptions.centerCrop();
        } else {
            requestOptions.fitCenter();
        }
        requestBuilder.apply(requestOptions);

        requestBuilder.into(this);
    }

}
