package com.example.venetatodorova.colorpicker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ColorPickerView extends View {

    private Paint mPaint;
    Bitmap bitmap;
    Canvas anotherCanvas;

    public ColorPickerView(Context context,AttributeSet attrs) {
        super(context,attrs);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(6F);
        mPaint.setStyle(Paint.Style.STROKE);
        anotherCanvas = new Canvas();
        setBitmap();
    }

    private void setBitmap(){
        bitmap = Bitmap.createBitmap(1530, 1530,  Bitmap.Config.ARGB_8888);
        int i;
        for(i = 0; i < 255; i++){
            changeShader(1,i);
            anotherCanvas.drawLine(i,0,i,1530,mPaint);
        }
        for(i = 0; i < 255; i++){
            changeShader(2,i);
            anotherCanvas.drawLine(i+255,0,i+255,1530,mPaint);
        }
        for(i = 0; i < 255; i++){
            changeShader(3,i);
            anotherCanvas.drawLine(i+2*255,0,i+2*255,1530,mPaint);
        }
        for(i = 0; i < 255; i++){
            changeShader(4,i);
            anotherCanvas.drawLine(i+3*255,0,i+3*255,1530,mPaint);
        }
        for(i = 0; i < 255; i++){
            changeShader(5,i);
            anotherCanvas.drawLine(i+4*255,0,i+4*255,1530,mPaint);
        }
        for(i = 0; i < 255; i++){
            changeShader(6,i);
            anotherCanvas.drawLine(i+5*255,0,i+5*255,1530,mPaint);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //canvas.setBitmap(bitmap);
    }

    private void changeShader(int part,int i) {
        if(part == 1) mPaint.setShader(new LinearGradient(0,0,0,1530,Color.rgb(255,i,0),Color.WHITE, Shader.TileMode.CLAMP));
        if(part == 2) mPaint.setShader(new LinearGradient(0,0,0,1530,Color.rgb(255-i,255,0),Color.WHITE, Shader.TileMode.CLAMP));
        if(part == 3) mPaint.setShader(new LinearGradient(0,0,0,1530,Color.rgb(0,255,i),Color.WHITE, Shader.TileMode.CLAMP));
        if(part == 4) mPaint.setShader(new LinearGradient(0,0,0,1530,Color.rgb(0,255-i,255),Color.WHITE, Shader.TileMode.CLAMP));
        if(part == 5) mPaint.setShader(new LinearGradient(0,0,0,1530,Color.rgb(i,0,255),Color.WHITE, Shader.TileMode.CLAMP));
        if(part == 6) mPaint.setShader(new LinearGradient(0,0,0,1530,Color.rgb(255,0,255-i),Color.WHITE, Shader.TileMode.CLAMP));
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
