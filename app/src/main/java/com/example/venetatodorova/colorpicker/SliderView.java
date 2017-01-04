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

public class SliderView extends View{

    private Paint mPaint;
    Bitmap bitmap;
    Canvas myCanvas;
    float bitmapWidth,bitmapHeight;
    int color;
    ColorPickerView.OnColorPickedListener colorListener;
    ColorPickerView.OnScrollListener scrollListener;

    public SliderView(Context context,AttributeSet attrs) {
        super(context,attrs);
        init();
    }

    public void setColorListener(ColorPickerView.OnColorPickedListener colorListener){
        this.colorListener = colorListener;
    }

    public void setScrollListener(ColorPickerView.OnScrollListener scrollListener) {
        this.scrollListener = scrollListener;
    }

    public void setColor(int color){
        this.color=color;
    }

    private void init() {
        bitmapWidth = getResources().getDimension(R.dimen.sliderViewWidth);
        bitmapHeight = getResources().getDimension(R.dimen.sliderViewHeight);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bitmap = Bitmap.createBitmap((int)bitmapWidth,(int)bitmapHeight,Bitmap.Config.ARGB_8888);
        myCanvas = new Canvas(bitmap);

    }

    private void drawBitmap(){
        int i;
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);

        for(i = 0; i < bitmapHeight; i++){
            mPaint.setShader(new LinearGradient(0,0,bitmapWidth,0, Color.rgb(red,green,blue),Color.BLACK, Shader.TileMode.CLAMP));
            myCanvas.drawLine(0,i,bitmapWidth,i, mPaint);
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
            scrollListener.setScrollView(true);
            return super.onTouchEvent(event);
        }
        scrollListener.setScrollView(false);
        int x = (int) event.getX();
        int y = (int) event.getY();
        if(x>=0 && y>=0 && x<bitmapWidth && y<bitmapHeight){
            color = getColor(x,y);
            colorListener.onColorPicked(color,false);
        }
        return true;
    }

}
