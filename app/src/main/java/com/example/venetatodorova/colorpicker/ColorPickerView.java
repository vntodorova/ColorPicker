package com.example.venetatodorova.colorpicker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class ColorPickerView extends View {

    private Paint mPaint;
    Bitmap bitmap;
    Canvas myCanvas;
    int bitmapWidth=600;
    int bitmapHeight=600;
    int color;
    OnColorPickedListener listener;

    interface OnColorPickedListener{
        void onColorPicked(int color);
        void onSliderViewColorPicked(int color);
        void setScrollView(boolean enabled);
    }

    public ColorPickerView(Context context, AttributeSet attrs) {
        super(context,attrs);
        init();
    }

    public void setListener(OnColorPickedListener listener) {
        this.listener = listener;
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bitmap = Bitmap.createBitmap(bitmapWidth,bitmapHeight,Bitmap.Config.ARGB_8888);
        myCanvas = new Canvas(bitmap);
    }

    private void drawBitmap(){
        int i;
        int interval = bitmapWidth/6;
        for(i = 0; i < interval; i++){
            mPaint.setShader(new LinearGradient(0,0,0,bitmapHeight,Color.rgb(255,i*255/interval,0),Color.WHITE, Shader.TileMode.CLAMP));
            myCanvas.drawLine(i,0,i,bitmapHeight, mPaint);
        }
        for(i = 0; i < interval; i++){
            mPaint.setShader(new LinearGradient(0,0,0,bitmapHeight,Color.rgb(255-i*255/interval,255,0),Color.WHITE, Shader.TileMode.CLAMP));
            myCanvas.drawLine(i+interval,0,i+interval,bitmapHeight, mPaint);
        }
        for(i = 0; i < interval; i++){
            mPaint.setShader(new LinearGradient(0,0,0,bitmapHeight,Color.rgb(0,255,i*255/interval),Color.WHITE, Shader.TileMode.CLAMP));
            myCanvas.drawLine(i+2*interval,0,i+2*interval,bitmapHeight, mPaint);
        }
        for(i = 0; i < interval; i++){
            mPaint.setShader(new LinearGradient(0,0,0,bitmapHeight,Color.rgb(0,255-i*255/interval,255),Color.WHITE, Shader.TileMode.CLAMP));
            myCanvas.drawLine(i+3*interval,0,i+3*interval,bitmapHeight, mPaint);
        }
        for(i = 0; i < interval; i++){
            mPaint.setShader(new LinearGradient(0,0,0,bitmapHeight,Color.rgb(i*255/interval,0,255),Color.WHITE, Shader.TileMode.CLAMP));
            myCanvas.drawLine(i+4*interval,0,i+4*interval,bitmapHeight, mPaint);
        }
        for(i = 0; i < interval; i++){
            mPaint.setShader(new LinearGradient(0,0,0,bitmapHeight,Color.rgb(255,0,255-i*255/interval),Color.WHITE, Shader.TileMode.CLAMP));
            myCanvas.drawLine(i+5*interval,0,i+5*interval,bitmapHeight, mPaint);
        }
    }

    public int getColor(int x,int y) {
        int pixel = bitmap.getPixel(x,y);
        int red = Color.red(pixel);
        int blue = Color.blue(pixel);
        int green = Color.green(pixel);
        return Color.rgb(red,green,blue);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBitmap();
        canvas.drawBitmap(bitmap,0,0,null);
        mPaint.setShader(null);
        mPaint.setColor( Color.GRAY );
        mPaint.setStrokeWidth( 10f );
        mPaint.setStyle( Paint.Style.STROKE );
        canvas.drawRect(0, 0, bitmapWidth, bitmapHeight, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP){
            listener.setScrollView(true);
        } else {
            listener.setScrollView(false);
            int x = (int) event.getX();
            int y = (int) event.getY();
            if(x>=0 && y>=0 && x<bitmapWidth && y<bitmapHeight) {
                color = getColor(x, y);
                listener.onColorPicked(color);
            }
        }
        return true;
    }
}
