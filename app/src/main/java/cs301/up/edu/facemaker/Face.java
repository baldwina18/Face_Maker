package cs301.up.edu.facemaker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
//import android.os.Build;
import android.util.AttributeSet;
import android.view.SurfaceView;

/**
 * Created by alexabaldwin on 2/7/16.
 */
public class Face extends SurfaceView {
    protected int skinColor;
    protected int eyeColor;
    protected int hairColor;
    protected int hairStyleIndex;
    protected Path[] hairStyles;
    protected int eyeStyle;
    protected int noseStyle;


    public Face(Context context) {
        super(context);
        setWillNotDraw(false);
    }

    public Face(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
    }

    public Face(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);
    }

    public Face(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setWillNotDraw(false);
    }

    public void randomize() {
        //randomize
    }

    public void onDraw(Canvas canvas) {
        drawHair(canvas);
        Paint skinColor = new Paint();
        skinColor.setColor(Color.BLUE);
        Paint green = new Paint();
        green.setColor(Color.GREEN);
        canvas.drawOval(250.0f, 100.0f, 1000.0f, 1000.0f, skinColor);



    }

    public void drawHair(Canvas canvas) {
        Paint redPaint = new Paint();
        redPaint.setColor(Color.RED);
        Path spikedHair = new Path();
        spikedHair.moveTo(275.0f, 400.0f);
        spikedHair.lineTo(350.0f, 50.0f);
        spikedHair.lineTo(400.0f, 100.0f);
        spikedHair.lineTo(500.0f, 50.0f);
        spikedHair.lineTo(600.0f,100.0f);
        spikedHair.lineTo(700.0f,50.0f);
        spikedHair.lineTo(800.0f, 100.0f);
        spikedHair.lineTo(900.0f,50.0f);
        spikedHair.lineTo(975.0f,400.0f);

        //hairStyles[0] = spikedHair;
        canvas.drawPath(spikedHair, redPaint);
    }

}
