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

    ImageView imageView;
    Bitmap bitmap;
    Button button;
    ColorPickerView colorPickerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.color_picker_fragment,container,false);

        bitmap = Bitmap.createBitmap(1530, 1530,  Bitmap.Config.ARGB_8888);

        colorPickerView = (ColorPickerView) v.findViewById(R.id.colorPicker);
        colorPickerView.setBitmap(bitmap);

        button = (Button) v.findViewById(R.id.fragment_button);

        /*
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int x = (int) motionEvent.getX();
                int y = (int) motionEvent.getY();
                if(x>=0 && y>=0){
                    int pixel = bitmap.getPixel(x,y);
                    int red = Color.red(pixel);
                    int blue = Color.blue(pixel);
                    int green = Color.green(pixel);
                    button.setBackgroundColor(Color.rgb(red,green,blue));
                }
                return true;
            }
        });
        */

        return v;
    }

    public static ColorPickerDialogFragment newInstance() {
        return new ColorPickerDialogFragment();
    }


}
