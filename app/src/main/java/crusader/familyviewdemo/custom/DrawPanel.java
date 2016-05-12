package crusader.familyviewdemo.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.View;

import java.util.ArrayList;

import crusader.familyviewdemo.MainActivity;

public class DrawPanel extends View {
    private Paint paint;
    private Paint paint1;
    private Paint paint2;
    // Canvas c;
    MainActivity m1 = new MainActivity();
    int count = 0;
    int ans = 0, ansPrev;
    int temp1, temp2;
    int color = 0;
    String str = "";
    public boolean tmp;
    @SuppressWarnings("rawtypes")
    private ArrayList points;

    @SuppressWarnings("rawtypes")
    private ArrayList strokes;

    @SuppressWarnings("rawtypes")
    public DrawPanel(Context context) {
        super(context);

        points = new ArrayList();
        // points1 = new ArrayList();
        // points2 = new ArrayList();
        strokes = new ArrayList();
        paint = createPaint(Color.BLUE, 11);
        paint1 = createPaint(Color.GREEN, 11);
        paint2 = createPaint(Color.RED, 11);

    }

    @SuppressWarnings("rawtypes")
    public void onDraw(Canvas c) {
        super.onDraw(c);

        // this.setBackgroundColor(Color.WHITE);
        for (Object obj : strokes) {
            drawStroke((ArrayList) obj, c, color);
        }

        drawStroke(points, c, color);
        color = 0;

    }


    public int getColor() {
        return color;
    }

    private void drawStroke(@SuppressWarnings("rawtypes") ArrayList stroke,
            Canvas c, int i1) {

        if (stroke.size() > 0 && i1 == 0) {
            Point p0 = (Point) stroke.get(0);
            for (int i = 1; i < stroke.size(); i++) {
                Point p1 = (Point) stroke.get(i);
                c.drawLine(p0.x, p0.y, p1.x, p1.y, paint);
                p0 = p1;
            }
        } else if (stroke.size() > 0 && i1 == 1) {
            Point p0 = (Point) stroke.get(0);
            for (int i = 1; i < stroke.size(); i++) {
                Point p1 = (Point) stroke.get(i);
                c.drawLine(p0.x, p0.y, p1.x, p1.y, paint1);
                p0 = p1;
            }
        } else if (stroke.size() > 0 && i1 == 2) {
            Point p0 = (Point) stroke.get(0);
            for (int i = 1; i < stroke.size(); i++) {
                Point p1 = (Point) stroke.get(i);
                c.drawLine(p0.x, p0.y, p1.x, p1.y, paint2);
                p0 = p1;
            }
        }

    }


    private Paint createPaint(int color, float width) {
        Paint temp = new Paint();
        temp.setStyle(Paint.Style.STROKE);
        temp.setAntiAlias(true);
        temp.setColor(color);
        temp.setStrokeWidth(width);
        temp.setStrokeCap(Paint.Cap.ROUND);

        return temp;
    }

}