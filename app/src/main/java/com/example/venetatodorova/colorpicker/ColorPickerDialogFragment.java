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

public class ColorPickerDialogFragment extends DialogFragment {

    Button button;
    ColorPickerView colorPickerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.color_picker_fragment,container,false);
        button = (Button)v.findViewById(R.id.fragment_button);
        colorPickerView = (ColorPickerView)v.findViewById(R.id.colorPicker);
        colorPickerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int x = (int) motionEvent.getX();
                int y = (int) motionEvent.getY();
                if(x>=0 && y>=0 && x<colorPickerView.getBitmapWidth() && y<colorPickerView.getBitmapHeight()){
                    int color = colorPickerView.getColor(x,y);
                    button.setBackgroundColor(color);
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
