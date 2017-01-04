package com.example.venetatodorova.colorpicker;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class ColorPickerDialogFragment
        extends DialogFragment
        implements ColorPickerView.OnColorPickedListener, ColorPickerView.OnScrollListener {

    public static final double HALF_255 = 127.5;
    private ColorPickerView colorPickerView;
    private SliderView sliderView;
    private TextView textView;
    private MyScrollView scrollView;
    private int color;
    private ColorPickerView.OnColorPickedListener listener;

    public static ColorPickerDialogFragment newInstance() {
        return new ColorPickerDialogFragment();
    }

    public void setListener(ColorPickerView.OnColorPickedListener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.color_picker_fragment, container, false);
        Button button = (Button) v.findViewById(R.id.fragment_button);
        colorPickerView = (ColorPickerView) v.findViewById(R.id.colorPicker);
        sliderView = (SliderView) v.findViewById(R.id.sliderView);
        textView = (TextView) v.findViewById(R.id.text);
        scrollView = (MyScrollView) v.findViewById(R.id.scrollView);
        sliderView.setColorListener(this);
        sliderView.setScrollListener(this);
        colorPickerView.setColorListener(this);
        colorPickerView.setScrollListener(this);

        if (savedInstanceState != null) {
            setScrollView(true);
            color = savedInstanceState.getInt("Color");
            onColorPicked(color, true);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onColorPicked(color, false);
                dismiss();
            }
        });
        return v;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("Color", color);
    }

    @Override
    public void onColorPicked(int color, boolean onColorPicker) {
        this.color = color;
        int red = Color.red(color);
        int blue = Color.blue(color);
        int green = Color.green(color);
        textView.setBackgroundColor(color);
        textView.setText(String.format("#%06X", (0x00FFFFFF & color)));
        if ((red + green + blue) / 3 > HALF_255) {
            textView.setTextColor(Color.BLACK);
        } else {
            textView.setTextColor(Color.WHITE);
        }
        if (onColorPicker) {
            sliderView.setColor(color);
            sliderView.invalidate();
        }
    }

    public void setScrollView(boolean enabled) {
        scrollView.setScrollingEnabled(enabled);
    }
}
