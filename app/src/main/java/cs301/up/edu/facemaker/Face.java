package cs301.up.edu.facemaker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.SurfaceView;

/**
 * @author Alexa Baldwin
 * @version 12 February 2016
 */
public class Face extends SurfaceView {
    protected int skinColor;
    protected int eyeColor;
    protected int hairColor;
    protected int hairStyleIndex;
    protected Path[] hairStyles = new Path[3];
    protected int eyeStyle;
    protected int noseStyle;
    protected Paint whitePaint;
    protected Paint blackPaint;
    protected Paint irisColor;
    protected Paint faceColor;
    protected Paint hairStyleColor;

    /**
     * Face - constructor
     * @param context
     */
    public Face(Context context) {
        super(context);
        setWillNotDraw(false);
        drawHair();
        randomize();
    }//ctor

    /**
     * Face - constructor
     * @param context
     * @param attrs
     */
    public Face(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        drawHair();
        randomize();
    }//ctor

    /**
     * Face - constructor
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public Face(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);
        drawHair();
        randomize();
    }//ctor

    /**
     * Face - constructor
     * @param context
     * @param attrs
     * @param defStyleAttr
     * @param defStyleRes
     */
    public Face(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setWillNotDraw(false);
        drawHair();
        randomize();
    }//ctor

    /**
     * randomize - chooses random integers for the hair, eyes and nose
     * also assigns random colors for the skin, eyes and hair
     */
    public void randomize() {
        hairStyleIndex = (int)(Math.random()*3);
        noseStyle = (int)(1+Math.random()*3);
        eyeStyle = (int)(1+Math.random()*3);
        for (int i=0;i<3;i++) {
            int red = (int) (Math.random() * 255);
            int blue = (int) (Math.random() * 255);
            int green = (int) (Math.random() * 255);
            if (i==0)
                skinColor = Color.argb(255,red,green,blue);
            if (i==1)
                eyeColor = Color.argb(255,red,green,blue);
            if (i==2)
                hairColor = Color.argb(255,red,green,blue);
        }
    }//randomize

    /**
     * onDraw - draws all elements of the face based on the specifications
     * @param canvas - the canvas object on which the face will be drawn on
     */
    public void onDraw(Canvas canvas) {
        setPaint();
        canvas.drawPath(hairStyles[hairStyleIndex],hairStyleColor);         //hair
        canvas.drawOval(250.0f, 100.0f, 1000.0f, 1000.0f, faceColor);       //face
        drawEyes(canvas, eyeStyle);                                         //eyes
        drawNose(canvas, noseStyle);                                        //nose
        canvas.drawArc(450.0f,600.0f,800.0f,850.0f,0,180,true,blackPaint);  //mouth
    }//onDraw

    /**
     * drawHair - creates three hairstyles and assigns each to a Path array
     */
    public void drawHair() {
        //spiked hair
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
        hairStyles[0] = spikedHair;

        //mohawk
        Path mohawk = new Path();
        mohawk.moveTo(400.0f, 200.0f);
        mohawk.lineTo(625.0f, 25.0f);
        mohawk.lineTo(850.0f,200.0f);
        hairStyles[1] = mohawk;

        //long hair
        Path longHair = new Path();
        longHair.moveTo(250.0f, 800.0f);
        longHair.lineTo(250.0f, 90.0f);
        longHair.lineTo(1000.0f,90.0f);
        longHair.lineTo(1000.0f,800.0f);
        longHair.lineTo(850.0f,1000.0f);
        longHair.lineTo(750.0f,900.0f);
        longHair.lineTo(650.0f,1050.0f);
        longHair.lineTo(550.0f,950.0f);
        longHair.lineTo(400.0f,1000.0f);
        longHair.lineTo(250.0f, 800.0f);
        hairStyles[2] = longHair;
    }//drawHair

    /**
     * setPaint - setter method to initialize paints
     */
    public void setPaint() {
        whitePaint = new Paint();
        whitePaint.setColor(Color.WHITE);
        blackPaint = new Paint();
        blackPaint.setColor(Color.BLACK);
        irisColor = new Paint();
        irisColor.setColor(eyeColor);
        faceColor = new Paint();
        faceColor.setColor(skinColor);
        hairStyleColor = new Paint();
        hairStyleColor.setColor(hairColor);
    }//setPaint

    /**
     * drawEyes - creates three different styles of eyes
     * @param canvas - the canvas object that the eyes will be drawn on
     * @param eyeStyle - integer to determine which eye style to draw
     */
    public void drawEyes(Canvas canvas, int eyeStyle) {
        if (eyeStyle==1) { //wide eyes
            canvas.drawOval(425.0f,300.0f,575.0f,400.0f,whitePaint);
            canvas.drawOval(700.0f,300.0f,850.0f,400.0f,whitePaint);
        } else if (eyeStyle==2) { //diamond eyes
            Path diamondEyeLeft = new Path(); //left diamond eye
            diamondEyeLeft.moveTo(425.0f, 350.0f);
            diamondEyeLeft.lineTo(500.0f, 250.0f);
            diamondEyeLeft.lineTo(575.0f, 350.0f);
            diamondEyeLeft.lineTo(500.0f, 450.0f);
            diamondEyeLeft.lineTo(425.0f, 350.0f);
            canvas.drawPath(diamondEyeLeft, whitePaint);
            Path diamondEyeRight = new Path(); //right diamond eye
            diamondEyeRight.moveTo(700.0f, 350.0f);
            diamondEyeRight.lineTo(775.0f, 250.0f);
            diamondEyeRight.lineTo(850.0f, 350.0f);
            diamondEyeRight.lineTo(775.0f, 450.0f);
            diamondEyeRight.lineTo(700.0f, 350.0f);
            canvas.drawPath(diamondEyeRight, whitePaint);
        } else if (eyeStyle==3) { //pretty eyes
            canvas.drawArc(425.0f,275.0f,575.0f,410.0f,180,180,false,whitePaint);
            canvas.drawArc(700.0f,275.0f,850.0f,410.0f,180,180,false,whitePaint);
        }
        //center of eye
        canvas.drawOval(450.0f,300.0f,550.0f,400.0f,irisColor);
        canvas.drawOval(725.0f, 300.0f, 825.0f, 400.0f, irisColor);
        canvas.drawOval(460.0f, 310.0f, 540.0f, 390.0f, blackPaint);
        canvas.drawOval(735.0f, 310.0f, 815.0f, 390.0f, blackPaint);
    }//drawEyes

    /**
     * drawNose - creates three different nose styles
     * @param canvas - the canvas object on which the noses are drawn
     * @param noseStyle - integer to determine which style to draw
     */
    public void drawNose(Canvas canvas, int noseStyle) {
        if (noseStyle==1) { //triangle nose
            Path triangleNose = new Path();
            triangleNose.moveTo(575.0f,600.0f);
            triangleNose.lineTo(625.0f, 500.0f);
            triangleNose.lineTo(675.0f, 600.0f);
            canvas.drawPath(triangleNose, blackPaint);
        } else if (noseStyle==2) { //pig nose
            canvas.drawOval(550.0f,500.0f,600.0f,600.0f,blackPaint);
            canvas.drawOval(650.0f,500.0f,700.0f,600.0f,blackPaint);
        } else if (noseStyle==3) { //normal nose
            Path normalNose = new Path();
            normalNose.moveTo(575.0f, 550.0f);
            normalNose.lineTo(625.0f, 400.0f);
            normalNose.lineTo(675.0f, 550.0f);
            canvas.drawPath(normalNose, blackPaint);
            canvas.drawArc(550.0f,500.0f,700.0f,625.0f,180,180,true,blackPaint);
        }
    }//drawNose

}//class Face
