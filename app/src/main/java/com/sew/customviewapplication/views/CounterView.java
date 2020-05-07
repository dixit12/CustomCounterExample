package com.sew.customviewapplication.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.drawable.DrawableCompat;
import com.sew.customviewapplication.R;

public class CounterView extends LinearLayout {

    String VALUE="VALUE";

    int currentValue=0,newValue=0;

    int colorNumber = getResources().getColor(R.color.black_color, null);
    int colorValue = getResources().getColor(R.color.default_value_color, null);
    int plusButtonColor = getResources().getColor(R.color.default_button_color, null);
    int minusButtonColor = getResources().getColor(R.color.default_button_color,null);

    int minValue = 0, maxValue = 100, initialValue = 50;
    ImageView plusImageButton, minusImageButton;
    TextView tvNumber;
    TextView tvTextTitle;
    Context context;


    public CounterView(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public CounterView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(context, attrs);
    }

    public CounterView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CounterView, 0, 0);
        try {
            colorNumber = ta.getColor(R.styleable.CounterView_colorNumber, colorNumber);
            colorValue = ta.getColor(R.styleable.CounterView_colorValue, colorValue);
            minValue = ta.getInteger(R.styleable.CounterView_minValue, minValue);
            maxValue = ta.getInteger(R.styleable.CounterView_maxValue, maxValue);
            initialValue = ta.getInteger(R.styleable.CounterView_initialValue, initialValue);
            plusButtonColor = ta.getColor(R.styleable.CounterView_plusButtonColor, plusButtonColor);
            minusButtonColor = ta.getColor(R.styleable.CounterView_minusButtonColor, minusButtonColor);

        } finally {
            ta.recycle();
            init();
        }

    }

    public void init() {

        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        setLayoutParams(params);
        setOrientation(LinearLayout.HORIZONTAL);
        setWeightSum(3);

        minusImageButton = new ImageView(context);
        params = new LayoutParams(0, LayoutParams.MATCH_PARENT, 1.0f);
        minusImageButton.setLayoutParams(params);
        minusImageButton.setImageResource(R.drawable.minuscircle);
        setMinusImageButton(minusButtonColor);
        minusImageButton.setPadding(5, 5, 5, 5);
        minusImageButton.setScaleType(ImageView.ScaleType.FIT_CENTER);
        minusImageButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                currentValue = Integer.valueOf(tvNumber.getText().toString());
                newValue = Math.max(currentValue - 1, minValue);
                if (onValuesClickListener != null) {
                    onValuesClickListener.OnValueChange(currentValue, newValue);
                }
                tvNumber.setText(String.valueOf(newValue));
            }
        });

        LinearLayout llValue = new LinearLayout(context);
        params = new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1.0f);
        llValue.setLayoutParams(params);
        llValue.setOrientation(VERTICAL);
        llValue.setWeightSum(5);

        tvNumber = new TextView(context);
        params = new LayoutParams(LayoutParams.MATCH_PARENT, 0, 3.0f);
        tvNumber.setLayoutParams(params);
        setColorNumber(colorNumber);
        tvNumber.setTextSize(24f);

        if (initialValue>maxValue)
            initialValue=maxValue;

        if (initialValue<minValue)
            initialValue=minValue;

        tvNumber.setText(String.valueOf(initialValue));
        tvNumber.setGravity(Gravity.CENTER);
        tvNumber.setPadding(2, 2, 2, 2);

        tvTextTitle = new TextView(context);
        params = new LayoutParams(LayoutParams.MATCH_PARENT, 0, 2.0f);
        tvTextTitle.setLayoutParams(params);
        setColorValue(colorValue);
        tvTextTitle.setText(VALUE);
        tvTextTitle.setGravity(Gravity.CENTER);
        tvTextTitle.setTextSize(14f);
        tvTextTitle.setPadding(5, 5, 5, 5);

        llValue.addView(tvNumber);
        llValue.addView(tvTextTitle);


        plusImageButton = new ImageView(context);
        params = new LayoutParams(0, LayoutParams.MATCH_PARENT, 1.0f);
        plusImageButton.setLayoutParams(params);
        plusImageButton.setImageResource(R.drawable.pluscircle);
        setPlusImageButton(plusButtonColor);
        plusImageButton.setPadding(5, 5, 5, 5);
        plusImageButton.setScaleType(ImageView.ScaleType.FIT_CENTER);
        plusImageButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                currentValue = Integer.valueOf(tvNumber.getText().toString());
                newValue = Math.min(currentValue + 1, maxValue);
                if (onValuesClickListener != null) {
                    onValuesClickListener.OnValueChange(currentValue, newValue);
                }
                tvNumber.setText(String.valueOf(newValue));
            }
        });

        setPadding(10, 40, 10, 40);

        addView(minusImageButton);
        addView(llValue);
        addView(plusImageButton);
    }

    private OnValuesClickListener onValuesClickListener;

    public interface OnValuesClickListener {
        void OnValueChange(int oldValue, int newValue);
    }

    public void setOnValueChangeListener(OnValuesClickListener listener) {
        this.onValuesClickListener = listener;
    }

    @Override
    public void setOrientation(int orientation) {
        super.setOrientation(orientation);
    }


    public ImageView getPlusImageButton() {
        return plusImageButton;
    }

    public void setPlusImageButton(int color) {
        Drawable plusColor = AppCompatResources.getDrawable(context, R.drawable.pluscircle);
        assert plusColor != null;
        Drawable pluswrappedDrawable = DrawableCompat.wrap(plusColor);
        DrawableCompat.setTint(pluswrappedDrawable, color);
        plusImageButton.setImageDrawable(pluswrappedDrawable);
    }

    public ImageView getMinusImageButton() {
        return minusImageButton;
    }

    public void setMinusImageButton(int color) {
        Drawable minusColor = AppCompatResources.getDrawable(context, R.drawable.minuscircle);
        assert minusColor != null;
        Drawable minuswrappedDrawable = DrawableCompat.wrap(minusColor);
        DrawableCompat.setTint(minuswrappedDrawable, color);
        minusImageButton.setImageDrawable(minuswrappedDrawable);
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public int getNewValue() {
        return newValue;
    }

    public int getColorNumber() {
        return colorNumber;
    }

    public void setColorNumber(int colorNumber) {
        this.colorNumber = colorNumber;
        tvNumber.setTextColor(colorNumber);
    }

    public int getColorValue() {
        return colorValue;
    }

    public void setColorValue(int colorValue) {
        this.colorValue = colorValue;
        tvTextTitle.setTextColor(colorValue);
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public int getInitialValue() {
        return initialValue;
    }

    public void setInitialValue(int initialValue) {
        this.initialValue = initialValue;
    }

}
