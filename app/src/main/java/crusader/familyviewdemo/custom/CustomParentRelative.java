package crusader.familyviewdemo.custom;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import crusader.familyviewdemo.model.LayoutRelationModel;

/**
 * Created by linus on 10/5/16.
 */
public class CustomParentRelative extends RelativeLayout {

    private static final String TAG = "CustomParentRelative";

    Paint m_paint;

    ArrayList<LayoutRelationModel> layoutRelationModels;

    ViewGroup firstLayout;
    ViewGroup secondLayout;

    public CustomParentRelative(Context context) {
        super(context);
    }

    public CustomParentRelative(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomParentRelative(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomParentRelative(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public ArrayList<LayoutRelationModel> getLayoutRelationModels() {
        return layoutRelationModels;
    }

    public void setLayoutRelationModels(ArrayList<LayoutRelationModel> layoutRelationModels) {
        this.layoutRelationModels = layoutRelationModels;
    }


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

//    public CustomParentRelative(Context context, ViewGroup firstLayout,
//                                ViewGroup secondLayout) {
//        super(context);
//        this.firstLayout = firstLayout;
//        this.secondLayout = secondLayout;
//        initPaintComponent();
//    }

    public void initPaintComponent() {
        m_paint = new Paint();
        m_paint.setColor(Color.BLACK);
        m_paint.setStrokeWidth(10);
        m_paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG, "onDraw: ");
        if(layoutRelationModels != null && layoutRelationModels.size() > 0){
            for (int i = 0; i < layoutRelationModels.size(); i++) {
                firstLayout = layoutRelationModels.get(i).getFirstLayout();
                secondLayout = layoutRelationModels.get(i).getSecondLayout();
                if(firstLayout != null && secondLayout != null){
                    if(layoutRelationModels.get(i).getRelationType() == LayoutRelationModel.RELATIONSHIP_TYPE_VERTICAL){
                        Rect myFirstViewRect = new Rect();
                        firstLayout.getGlobalVisibleRect(myFirstViewRect);

                        Rect mySecondViewRect = new Rect();
                        secondLayout.getGlobalVisibleRect(mySecondViewRect);


                        //Drawing line from top to bottom
//                        canvas.drawLine((firstLayout.getX() + (firstLayout.getWidth()/2)), (firstLayout.getY() + (firstLayout.getHeight())), (secondLayout.getX() + (secondLayout.getWidth()/2)), secondLayout.getY(), m_paint);
//                        canvas.drawLine(myFirstViewRect.exactCenterX(), myFirstViewRect.bottom, mySecondViewRect.exactCenterX(), mySecondViewRect.top, m_paint);
                        canvas.drawLine((getRelativeLeft(firstLayout) + (firstLayout.getWidth()/2)), (getRelativeTop(firstLayout) + (firstLayout.getHeight() / 2)), (getRelativeLeft(secondLayout) + (secondLayout.getWidth()/2)), (getRelativeTop(secondLayout) - (secondLayout.getHeight() / 2)), m_paint);
                    }else if (layoutRelationModels.get(i).getRelationType() == LayoutRelationModel.RELATIONSHIP_TYPE_HORIZONTAL){
                        //Drawing line from left to right
                        canvas.drawLine((firstLayout.getX() + firstLayout.getWidth()), (firstLayout.getY() + (firstLayout.getHeight() / 2) - 100), (secondLayout.getX()), (secondLayout.getY() + (secondLayout.getHeight() / 2) - 100), m_paint);
                    }
                }
            }
        }
//        if (firstLayout != null && secondLayout != null) {
////            canvas.drawLine((firstLayout.getX() + firstLayout.getWidth() + 100), firstLayout.getY() + 50, secondLayout.getX() + 25, secondLayout.getY(), m_paint);
//            canvas.drawLine((firstLayout.getX() + (firstLayout.getWidth()/2)), (firstLayout.getY() + (firstLayout.getHeight())), (secondLayout.getX() + (secondLayout.getWidth()/2)), secondLayout.getY(), m_paint);
//        }
    }

    private int getRelativeLeft(View myView) {
        if (myView.getParent() == myView.getRootView())
            return myView.getLeft();
        else
            return myView.getLeft() + getRelativeLeft((View) myView.getParent());
    }

    private int getRelativeTop(View myView) {
        if (myView.getParent() == myView.getRootView())
            return myView.getTop();
        else
            return myView.getTop() + getRelativeTop((View) myView.getParent());
    }
}
