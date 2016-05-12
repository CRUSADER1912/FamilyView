package crusader.familyviewdemo.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by linus on 10/5/16.
 */
public class LineView extends View {

    ViewGroup firstLayout;
    ViewGroup secondLayout;
    Paint m_paint;
    private static final String TAG = "LineView";

    public LineView(Context context) {
        super(context);
    }

    //    public LineView(Context context) {
//        super(context);
////        setWillNotDraw(false);
//    }
//
//    public LineView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        setWillNotDraw(false);
////        invalidate();
//    }
//
//    public LineView(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//    }
//
//    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//    public LineView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }
//
//    @Override
//    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//
//    }
//
//    public ViewGroup getFirstLayout() {
//        return firstLayout;
//    }
//
//    public void setFirstLayout(ViewGroup firstLayout) {
//        this.firstLayout = firstLayout;
//    }
//
//    public ViewGroup getSecondLayout() {
//        return secondLayout;
//    }
//
//    public void setSecondLayout(ViewGroup secondLayout) {
//        this.secondLayout = secondLayout;
//    }
//
    public LineView(Context context, ViewGroup firstLayout, ViewGroup secondLayout) {
        this(context);
        this.firstLayout = firstLayout;
        this.secondLayout = secondLayout;
        m_paint = new Paint();
        m_paint.setColor(Color.BLACK); // You could setup the background etc here
        m_paint.setStyle(Paint.Style.FILL);
    }
//
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        setWillNotDraw(true);
//
////        canvas.drawLine(0, 0, getWidth(), getHeight(), m_paint);
//
//        if(firstLayout != null && secondLayout != null) {
//            canvas.drawLine(firstLayout.getX() + 25, firstLayout.getY() + 50, secondLayout.getX() + 25, secondLayout.getY(), m_paint);
//        }
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG, "onDraw: ");
        if (firstLayout != null && secondLayout != null) {
            canvas.drawLine(firstLayout.getX() + 25, firstLayout.getY() + 50, secondLayout.getX() + 25, secondLayout.getY(), m_paint);
        }

        canvas.drawLine(0, 0, 100, 100, m_paint);
    }
}
