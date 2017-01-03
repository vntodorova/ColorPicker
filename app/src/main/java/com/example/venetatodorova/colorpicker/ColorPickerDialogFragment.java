package com.example.venetatodorova.colorpicker;

import android.app.DialogFragment;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

public class ColorPickerDialogFragment extends DialogFragment{

    Button button;
    ColorPickerView colorPickerView;
    SliderView sliderView;
    TextView textView;
    ScrollView scrollView;
    boolean isBlockedScrollView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.color_picker_fragment,container,false);
        button = (Button)v.findViewById(R.id.fragment_button);
        colorPickerView = (ColorPickerView)v.findViewById(R.id.colorPicker);
        sliderView = (SliderView)v.findViewById(R.id.sliderView);
        textView = (TextView)v.findViewById(R.id.text);
        scrollView = (ScrollView) v.findViewById(R.id.scrollView);
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return isBlockedScrollView;
            }
        });
        colorPickerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    isBlockedScrollView = false;
                    return false;
                } else {
                    int x = (int) motionEvent.getX();
                    int y = (int) motionEvent.getY();
                    if(x>=0 && y>=0 && x<colorPickerView.getBitmapWidth() && y<colorPickerView.getBitmapHeight()) {
                        int color = colorPickerView.getColor(x, y);
                        textView.setBackgroundColor(color);
                        textView.setText(String.format("#%06X", (0xFFFFFF & color)));
                        sliderView.setColor(color);
                        sliderView.invalidate();
                    }
                    isBlockedScrollView = true;
                    return true;
                }
            }
        });
        sliderView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int x = (int) motionEvent.getX();
                int y = (int) motionEvent.getY();
                if(x>=0 && y>=0 && x<sliderView.getBitmapWidth() && y<sliderView.getBitmapHeight()){
                    int color = sliderView.getColor(x,y);
                    textView.setBackgroundColor(color);
                    textView.setText(String.format("#%06X", (0xFFFFFF & color)));
                }
                return true;
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        return v;
    }

    public static ColorPickerDialogFragment newInstance() {
        return new ColorPickerDialogFragment();
    }


}
