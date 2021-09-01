package shamarket.com.shamarket.news;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CustomTopBottomNavigationView extends BottomNavigationView {

    private Path mPath;
    private Paint mPaint;
    public final int CURVE_CIRCLE_RADIUS = 256 / 3;
    // координаты первой кривой
    public Point mFirstCurveStartPoint = new Point();
    public Point mFirstCurveEndPoint = new Point();
    public Point mFirstCurveControlPoint2 = new Point();
    public Point mFirstCurveControlPoint1 = new Point();
    //координаты второй кривой
    @SuppressWarnings("FieldCanBeLocal")
    public Point mSecondCurveStartPoint = new Point();
    public Point mSecondCurveEndPoint = new Point();
    public Point mSecondCurveControlPoint1 = new Point();
    public Point mSecondCurveControlPoint2 = new Point();
    public static int mNavigationBarWidth;
    public int mNavigationBarHeight;


    public CustomTopBottomNavigationView(Context context) {
        super(context);
        init();

    }

    public CustomTopBottomNavigationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomTopBottomNavigationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setColor(Color.WHITE);
        setBackgroundColor(Color.TRANSPARENT);
}

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        mNavigationBarWidth = getWidth();
        mNavigationBarHeight = getHeight();
        switch (getId()){
            case 2:
                mFirstCurveStartPoint.set((mNavigationBarWidth/2 ) -(CURVE_CIRCLE_RADIUS * 2) - (CURVE_CIRCLE_RADIUS / 3), 0);
                mFirstCurveEndPoint.set(mNavigationBarWidth / 2, CURVE_CIRCLE_RADIUS + (CURVE_CIRCLE_RADIUS / 4));
                mSecondCurveEndPoint.set((mNavigationBarWidth / 2) + (CURVE_CIRCLE_RADIUS * 2) + (CURVE_CIRCLE_RADIUS / 3), 0);
                break;
            case 3:
                mFirstCurveStartPoint.set((mNavigationBarWidth ) -(mNavigationBarWidth/3), 0);
                mFirstCurveEndPoint.set((mNavigationBarWidth ) - (mNavigationBarWidth/6), CURVE_CIRCLE_RADIUS + (CURVE_CIRCLE_RADIUS / 4));
                mSecondCurveEndPoint.set((mNavigationBarWidth ) - (mNavigationBarWidth/6) + (CURVE_CIRCLE_RADIUS * 2) + (CURVE_CIRCLE_RADIUS / 3), 0);
                break;
            case 0:
            case 1:
            default :
                mFirstCurveStartPoint.set((mNavigationBarWidth/6 ) -(CURVE_CIRCLE_RADIUS * 2) - (CURVE_CIRCLE_RADIUS / 3), 0);
                mFirstCurveEndPoint.set(mNavigationBarWidth / 6, CURVE_CIRCLE_RADIUS + (CURVE_CIRCLE_RADIUS / 4));
                mSecondCurveEndPoint.set((mNavigationBarWidth / 6) + (CURVE_CIRCLE_RADIUS * 2) + (CURVE_CIRCLE_RADIUS / 3), 0);
                break;
        }

        mSecondCurveStartPoint = mFirstCurveEndPoint; 
        // координаты (x, y) 1-й контрольной точки на кубической кривой
        mFirstCurveControlPoint1.set(mFirstCurveStartPoint.x + CURVE_CIRCLE_RADIUS + (CURVE_CIRCLE_RADIUS / 4), mFirstCurveStartPoint.y);
        // координаты (x, y) 2-й контрольной точки на кубической кривой
        mFirstCurveControlPoint2.set(mFirstCurveEndPoint.x -(CURVE_CIRCLE_RADIUS * 2) + CURVE_CIRCLE_RADIUS, mFirstCurveEndPoint.y);
        mSecondCurveControlPoint1.set(mSecondCurveStartPoint.x + (CURVE_CIRCLE_RADIUS * 2) - CURVE_CIRCLE_RADIUS, mSecondCurveStartPoint.y);
        mSecondCurveControlPoint2.set(mSecondCurveEndPoint.x - (CURVE_CIRCLE_RADIUS + (CURVE_CIRCLE_RADIUS / 4)), mSecondCurveEndPoint.y);

        mPath.reset();
        mPath.moveTo(0, 0);
        mPath.lineTo(mFirstCurveStartPoint.x, mFirstCurveStartPoint.y);
        mPath.cubicTo(mFirstCurveControlPoint1.x, mFirstCurveControlPoint1.y,
                mFirstCurveControlPoint2.x, mFirstCurveControlPoint2.y,
                mFirstCurveEndPoint.x, mFirstCurveEndPoint.y);
        mPath.cubicTo(mSecondCurveControlPoint1.x, mSecondCurveControlPoint1.y,
                mSecondCurveControlPoint2.x, mSecondCurveControlPoint2.y,
                mSecondCurveEndPoint.x, mSecondCurveEndPoint.y);
        mPath.lineTo(mNavigationBarWidth, 0);
        mPath.lineTo(mNavigationBarWidth, mNavigationBarHeight);
        mPath.lineTo(0, mNavigationBarHeight);
        mPath.close();
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public  void setId(int id) {
        super.setId(id);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    @Override
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath, mPaint);
    }


}
