package com.rqphp.publib.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
/**
 *
 */
public class WaveView extends View {

    // 水波颜色默认颜色
    private int mAboveColor = 0x99356ccf;
    private int mBehindColor = 0x33356ccf;

    // 水波进度
    private final float DEFAULT_LEVEL_RATIO = 0.5f;
    private float mWaveLevelRatio = DEFAULT_LEVEL_RATIO;
    // 水波高度
    private final float DEFAULT_AMPLITUDE_RATIO = 0.05f;
    private float mWaveAmplitudeRatio = DEFAULT_AMPLITUDE_RATIO;
    // 水波周期
    private float mWaveCycleRatio = 1.0f;
    // 水波位移
    private float mWaveTranslateRatio = 1.0f;
    // 水波进度形状（方形）
    private ShapeType mShapeType = ShapeType.Square;

    public enum ShapeType {
        Circle,Square
    }

    private Paint mPaint;
    private Matrix mShaderMatrix;
    private Shader mShader;
    private Paint mBorderPaint;

    public WaveView(Context context) {
        this(context, null);
    }

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mShaderMatrix = new Matrix();
    }

    /**
     * 设置水波的颜色
     * @param behindColor 后面水波颜色
     * @param aboveColor  前面水波颜色
     */
    public void setWaveColor(int behindColor, int aboveColor) {
        mBehindColor = behindColor;
        mAboveColor = aboveColor;
        // 如果shader是null，代表onSizeChange还未执行。当前不需要创建shader
        if(mShader != null) {
            createShader();
            invalidate();
        }
    }

    /**
     * 设置边框样式
     * @param width 边框宽度
     * @param color 边框颜色
     */
    public void setBorder(int width,int color) {
        if(mBorderPaint == null) {
            mBorderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mBorderPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        }
        mBorderPaint.setStrokeWidth(width);
        mBorderPaint.setColor(color);
        invalidate();
    }

    /**
     * 设置水波进度条形状
     * @param shapeType
     */
    public void setShapeType(ShapeType shapeType) {
        mShapeType = shapeType;
        invalidate();
    }

    /**
     * 设置水波进度
     * @param waveLevelRatio 区间 0.0f~1.0f
     */
    public void setWaveLevelRatio(float waveLevelRatio) {
        if(mWaveLevelRatio != waveLevelRatio) {
            mWaveLevelRatio = waveLevelRatio;
            invalidate();
        }
    }

    public float getWaveLevelRatio() {
        return mWaveLevelRatio;
    }

    /**
     * 设置水波振幅，默认是控件高度的0.05f
     * @param waveAmplitudeRatio  建议区间 0.0001f ~ 0.05f
     */
    public void setWaveAmplitudeRatio(float waveAmplitudeRatio) {
        if(mWaveAmplitudeRatio != waveAmplitudeRatio) {
            this.mWaveAmplitudeRatio = waveAmplitudeRatio;
            invalidate();
        }
    }

    public float getWaveAmplitudeRatio() {
        return mWaveAmplitudeRatio;
    }

    /**
     * 设置水波X轴的位移
     * @param waveTranslateRatio
     */
    public void setWaveTranslateRatio(float waveTranslateRatio) {
        if(mWaveAmplitudeRatio != waveTranslateRatio && waveTranslateRatio >0) {
            this.mWaveTranslateRatio = waveTranslateRatio;
            invalidate();
        }
    }

    public float getWaveTranslateRatio() {
        return mWaveTranslateRatio;
    }


    public float getWaveCycleRatio() {
        return mWaveCycleRatio;
    }

    /**
     * 设置水波的周期（宽度）。
     * @param waveCycleRatio
     */
    public void setWaveCycleRatio(float waveCycleRatio) {
        if(mWaveCycleRatio != waveCycleRatio) {
            this.mWaveCycleRatio = waveCycleRatio;
            invalidate();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        createShader();
    }

    // y=Asin(ωx+φ)+k
    private void createShader() {
        int height = getHeight();
        int width = getWidth();
        // ω周期  让一个周期的宽度正好是width
        double frequency = 2 * Math.PI / width;
        // A振幅  默认的振幅是高度的0.05f
        float amplitude = height * DEFAULT_AMPLITUDE_RATIO;
        // k（y轴偏移量，进度） 默认的进度是50%
        float level = height * DEFAULT_LEVEL_RATIO;

        Bitmap waveBitmap = Bitmap.createBitmap(getWidth(),getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(waveBitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);

        Path abovePath = new Path();
        Path behindPath = new Path();
        abovePath.moveTo(0, height);
        behindPath.moveTo(0, height);
        for(int x = 0; x<=width; x++) {
            // y=Asin(ωx+φ)+k
            float aboveY = (float) (amplitude * Math.sin(frequency * x))+ level;
            // 背面的水波偏移一些，和前面的错开。
            float behindY = (float) (amplitude * Math.sin(frequency * x + width/4*frequency))+ level;
            abovePath.lineTo(x, aboveY);
            behindPath.lineTo(x, behindY);
        }
        abovePath.lineTo(width + 1, height);
        behindPath.lineTo(width + 1, height);
        paint.setColor(mBehindColor);
        canvas.drawPath(behindPath, paint);
        paint.setColor(mAboveColor);
        canvas.drawPath(abovePath, paint);

        mShader = new BitmapShader(waveBitmap, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setShader(mShader);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // dy  ±0.5f
        mShaderMatrix.setTranslate(mWaveTranslateRatio * getWidth(),
                (DEFAULT_LEVEL_RATIO - mWaveLevelRatio) * getHeight());
        mShaderMatrix.postScale(mWaveCycleRatio, mWaveAmplitudeRatio / DEFAULT_AMPLITUDE_RATIO, 0, getHeight() * (1 - mWaveLevelRatio));
        mShader.setLocalMatrix(mShaderMatrix);

        int borderWidth = mBorderPaint == null ? 0 : (int) mBorderPaint.getStrokeWidth();
        switch (mShapeType) {
            case Square:
                if(borderWidth > 0) {
                    canvas.drawRect(borderWidth /2f,borderWidth/2f,getWidth()-borderWidth/2f,getHeight() -borderWidth/2f,mBorderPaint);
                }
                canvas.drawRect(borderWidth, borderWidth, getWidth() - borderWidth, getHeight()-borderWidth , mPaint);
                break;
            case Circle:
                if(borderWidth > 0) {
                    canvas.drawCircle(getWidth()/2f, getHeight()/2f,getWidth()/2f-borderWidth/2f,mBorderPaint);
                }
                canvas.drawCircle(getWidth()/2f, getHeight()/2f,getWidth()/2f -borderWidth,mPaint);
                break;
        }

    }
}
