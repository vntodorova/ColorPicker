package com.example.venetatodorova.colorpicker;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ColorPickerDialogFragment extends DialogFragment implements ColorPickerView.OnColorPickedListener{

    ColorPickerView colorPickerView;
    SliderView sliderView;
    TextView textView;
    MyScrollView scrollView;
    int color;

    public static ColorPickerDialogFragment newInstance() {
        return new ColorPickerDialogFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.color_picker_fragment,container,false);
        Button button = (Button)v.findViewById(R.id.fragment_button);
        colorPickerView = (ColorPickerView)v.findViewById(R.id.colorPicker);
        sliderView = (SliderView)v.findViewById(R.id.sliderView);
        textView = (TextView)v.findViewById(R.id.text);
        scrollView = (MyScrollView) v.findViewById(R.id.scrollView);
        sliderView.setListener(this);
        colorPickerView.setListener(this);

        if(savedInstanceState!=null){
            color = savedInstanceState.getInt("Color");
            onColorPicked(color);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        return v;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("Color",color);
    }

    @Override
    public void onColorPicked(int color) {
        this.color = color;
        textView.setBackgroundColor(color);
        textView.setText(String.format("#%06X", (0xFFFFFF & color)));
        sliderView.setColor(color);
        sliderView.invalidate();
    }

    @Override
     public void onSliderViewColorPicked(int color) {
        this.color = color;
        textView.setBackgroundColor(color);
        textView.setText(String.format("#%06X", (0xFFFFFF & color)));
     }

    public void setScrollView(boolean enabled) {
        scrollView.setScrollingEnabled(enabled);
    }

}
