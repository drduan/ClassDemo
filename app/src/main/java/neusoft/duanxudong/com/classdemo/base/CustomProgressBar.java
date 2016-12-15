package neusoft.duanxudong.com.classdemo.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import neusoft.duanxudong.com.classdemo.R;


@SuppressLint({"HandlerLeak", "HandlerLeak"})
public class CustomProgressBar extends View {

    // This mode user can't see the loading progress.
    public static final int SPIN_MODE = 1;
    // This mode user can know the loading progress.
    public static final int SWEEP_MODE = 2;
    private static final int DEFAULT_DIAMETER = 60;
    /* Default data for the spinner progress bar. */
    private static final double PI = Math.PI;
    // Default mode is spinner mode.
    private int mProgressMode = SPIN_MODE;
    private int mBarStrokeWidth = 3;
    private int mCircleStrokeWidth = mBarStrokeWidth;
    private float mBarLength = 0.0f;
    private int mBarLengthRatio = 8;
    // In the sweep mode or horizontal style progress bar. all progress will
    // start at 0.
    private int mProgress = 0;
    private Handler mSweepBarHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            if (mProgress > 360) {
                mProgress = 360;
            }
            invalidate();
        }

    };
    private int mSpinSpeed = 8;
    private Handler mSpinBarHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            // The
            // progress'
            // change
            // happen in
            // here.
            if (msg.what == 0) {
                mProgress += mSpinSpeed;

                if (mProgress > 360) {
                    mProgress = 0;
                }
                invalidate();
            }

            mSpinBarHandler.sendEmptyMessage(0);

        }

    };
    private float mPadding = 2.0f;
    /* Default color data */
    private int mCircleColor = Color.GRAY;
    private int mBarColor = Color.RED;
    /* Paints */
    private Paint mCirclePaint;
    private Paint mBarPaint;
    /* Bounds */
    private RectF mCircleRectF;

    public CustomProgressBar(Context context) {

        super(context);
        init();
    }

    public CustomProgressBar(Context context, AttributeSet attrs) {

        super(context, attrs);

        extraAttributes(context.obtainStyledAttributes(attrs,
                R.styleable.apps));

        init();

    }

    private void init() {

        mCirclePaint = new Paint();
        mBarPaint = new Paint();
        this.mBarColor = getContrastColor(mCircleColor);
    }

    private void extraAttributes(TypedArray array) {

        this.mCircleColor = array.getColor(R.styleable.apps_circleColor,
                mCircleColor);
        this.mBarColor = array.getColor(R.styleable.apps_barColor, mBarColor);
        this.mBarLengthRatio = (int) array.getDimension(R.styleable.apps_barLengthRatio,
                mBarLengthRatio);
        this.mPadding = array.getDimension(R.styleable.apps_padding, mPadding);
    }

    @Override
    protected void onAttachedToWindow() {

        // Set up the paints and bounds before draw
        super.onAttachedToWindow();
        // Check the view's size. if set the view in xml as
        // wrap_content. The
        // should give a default vaule
        if (this.getLayoutParams().width < 0
                || this.getLayoutParams().height < 0) {
            this.getLayoutParams().width = this.getLayoutParams().height = DEFAULT_DIAMETER;
        }

        setUpBarLength(this.getLayoutParams().width,
                this.getLayoutParams().height);
        setUpBounds();
        setUpPaints();

        invalidate();
    }

    /**
     * At first calculate the circle's circumference. Then use the bar length
     * ratio to calculate the bar length the default bar length is 1 /8 of
     * circle's circumference.
     *
     * @param width  this view's width
     * @param height this view's height
     */
    private void setUpBarLength(int width, int height) {

        // First calculate the circle's diameter.
        float diameter = width - 2 * mPadding;
        double circumference = diameter * PI;
        mBarLength = (float) circumference / mBarLengthRatio;
    }

    private void setUpPaints() {

        mCirclePaint.setColor(mCircleColor);
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setStyle(Style.STROKE);
        mCirclePaint.setStrokeWidth(mCircleStrokeWidth);

        mBarPaint.setColor(mBarColor);
        mBarPaint.setAntiAlias(true);
        mBarPaint.setStyle(Style.STROKE);
        mBarPaint.setStrokeWidth(mBarStrokeWidth);
    }

    private void setUpBounds() {

        mCircleRectF = new RectF(mPadding, mPadding,
                this.getLayoutParams().width - mPadding,
                this.getLayoutParams().height - mPadding);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        // Draw the outer circle.
        canvas.drawArc(mCircleRectF, 360, 360, false, mCirclePaint);

        switch (mProgressMode) {
            case SPIN_MODE:
                // draw a bar that its length is fixed. The bar
                // only spin in the
                // circle.
                drawSpinBar(canvas);
                break;

            case SWEEP_MODE:
                // draw a bar that its length is not fixed. And
                // the bar's will grow
                // in a pattern.
                drawSweepBar(canvas);
                break;
        }

    }

    private void drawSweepBar(Canvas canvas) {

        canvas.drawArc(mCircleRectF, -90, mProgress, false, mBarPaint);
    }

    private void drawSpinBar(Canvas canvas) {

        canvas.drawArc(mCircleRectF, mProgress - 90, mBarLength, false,
                mBarPaint);
    }

    /**
     * Execute the progress. And this method has to be called.
     */
    public void execute() {

        /*
         * 0 : Speed up 1 : Do not speed up
         */
        int what = isShown() ? 1 : 0;

        switch (mProgressMode) {
            case SPIN_MODE:
                mSpinBarHandler.sendEmptyMessage(what);
                break;

            case SWEEP_MODE:
                mSweepBarHandler.sendEmptyMessage(what);
                break;
        }
    }

    /**
     * Reset the loading
     */
    public void reset() {

        mProgress = 0;
        mSpinBarHandler.removeMessages(0);
        mSweepBarHandler.removeMessages(0);
    }

    public int getBarLengthRatio() {

        return mBarLengthRatio;
    }

    public void setBarLengthRatio(int barLengthRatio) {

        this.mBarLengthRatio = barLengthRatio;
    }

    public float getSpinSpeed() {

        return mSpinSpeed;
    }

    public void setSpinSpeed(int spinSpeed) {

        this.mSpinSpeed = spinSpeed;
    }

    public float getPadding() {

        return mPadding;
    }

    public void setPadding(float padding) {

        this.mPadding = padding;
    }

    public void setCircleColor(int circleColor) {

        this.mCircleColor = circleColor;
        mBarColor = getContrastColor(circleColor);
    }

    public void setBarColor(int barColor) {

        this.mBarColor = barColor;
    }

    public void setProgressMode(int progressMode) {

        this.mProgressMode = progressMode;
    }

    public int getProgress() {

        return mProgress;
    }

    public void setProgress(int progress) {

        this.mProgress = progress;
    }

    /**
     * The method is used for generating contrast color based on a given color
     * code. The result contrast color is either 'black' or 'white'. ref link :
     * http://24ways.org/2010/calculating-color-contrast/
     *
     * @param aColor a given color
     * @return result contrast color
     */
    private int getContrastColor(int aColor) {

        int r = Color.red(aColor);
        int g = Color.green(aColor);
        int b = Color.blue(aColor);

        int yiq = ((r * 299) + (g * 587) + (b * 114)) / 1000;

        return (yiq >= 128) ? Color.BLACK : Color.WHITE;

    }
}
