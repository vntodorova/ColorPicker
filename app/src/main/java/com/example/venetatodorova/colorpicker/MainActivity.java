package com.example.venetatodorova.colorpicker;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ColorPickerDialogFragment colorPickerDialogFragment;
    Paint paint;
    Bitmap b;
    Canvas c;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ShowColorPicker(View view) {
        colorPickerDialogFragment = ColorPickerDialogFragment.newInstance();
        colorPickerDialogFragment.show(getFragmentManager(),"ColorPicker");
    }
}