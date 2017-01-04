package com.example.venetatodorova.colorpicker;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Shader;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static android.R.attr.bitmap;
import static android.R.attr.text;
import static android.R.attr.textScaleX;

public class MainActivity extends AppCompatActivity implements ColorPickerView.OnColorPickedListener {

    public static final String COLOR_PICKER = "ColorPicker";
    ColorPickerDialogFragment colorPickerDialogFragment;
    TextView textView;
    int color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);

        if (savedInstanceState != null) {
            color = savedInstanceState.getInt("Color");
            onColorPicked(color, false);
        }
    }

    @Override
    protected void onResume() {
        colorPickerDialogFragment = (ColorPickerDialogFragment) getFragmentManager().findFragmentByTag(COLOR_PICKER);
        if (colorPickerDialogFragment != null) {
            colorPickerDialogFragment.setListener(this);
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        colorPickerDialogFragment = (ColorPickerDialogFragment) getFragmentManager().findFragmentByTag(COLOR_PICKER);
        if (colorPickerDialogFragment != null) {
            colorPickerDialogFragment.setListener(null);
        }
    }

    public void showColorPicker(View view) {
        if (getFragmentManager().findFragmentByTag(COLOR_PICKER) == null) {
            colorPickerDialogFragment = ColorPickerDialogFragment.newInstance();
            colorPickerDialogFragment.show(getFragmentManager(), COLOR_PICKER);
            colorPickerDialogFragment.setListener(this);
        }
    }

    @Override
    public void onColorPicked(int color, boolean onColorPicker) {
        this.color = color;
        textView.setBackgroundColor(color);
        textView.setText(String.format("#%06X", (0xFFFFFF & color)));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("Color", color);
    }
}
