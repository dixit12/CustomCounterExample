package com.dixitgarg.counterviewlibrary.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.drawable.DrawableCompat;

import com.dixitgarg.counterviewlibrary.R;

/**
 * <h3> Shows the Counter View</h3>
 * The Counter View implements an application that shows Counter View on the Screen and
 * on clicking the plus button increments the Initial Value,
 * clicking the minus button decrements the Initial Value
 * where the user can set the limits as minimum and maximum value.
 *
 * @author Dixit Garg
 * @version 1.0
 *
 */
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

    /**
     * Retrieves the Plus Image Button
     *
     * @see #getPlusImageButton()
     *
     * @return Plus Image Button
     */

    public ImageView getPlusImageButton() {
        return plusImageButton;
    }

    /**
     * Specifies a tint for Plus Image Button.
     *
     * @see #setPlusImageButton(int color)
     * @see #getPlusImageButton()
     *
     * @param color Color used for tinting this button
     */

    public void setPlusImageButton(@ColorInt int color) {
        Drawable plusColor = AppCompatResources.getDrawable(context, R.drawable.pluscircle);
        assert plusColor != null;
        Drawable pluswrappedDrawable = DrawableCompat.wrap(plusColor);
        DrawableCompat.setTint(pluswrappedDrawable, color);
        plusImageButton.setImageDrawable(pluswrappedDrawable);
    }

    /**
     * Retrieves the Minus Image Button
     *
     * @see #getMinusImageButton()
     *
     * @return Minus Image Button
     */

    public ImageView getMinusImageButton() {
        return minusImageButton;
    }

    /**
     * Specifies a tint for Minus Image Button.
     *
     * @see #setMinusImageButton(int color)
     * @see #getMinusImageButton()
     * @param color Color used for tinting this button
     */

    public void setMinusImageButton(@ColorInt int color) {
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

    /**
     * Returns a colored integer .If the color of number is not set,then default color from the set is used.
     *
     * @see #getColorNumber()
     *
     * @return Colored Number
     *
     * @exception NullPointerException If the number is not initialized then it throws the exception
     */
    public int getColorNumber() {
        return colorNumber;
    }

    /**
     * Sets the Number color for all the states (normal, selected,
     * focused) to be this color.
     *
     * @param colorNumber A color value in the form 0xAARRGGBB.
     * Do not pass a resource ID. To get a color value from a resource ID, call
     * {@link android.support.v4.content.ContextCompat#getColor(Context, int) getColor}.
     *
     * @see #setColorNumber(int)
     * @see #getColorNumber()
     *
     * @attr ref android.R.styleable#TextView_textColor
     */
    public void setColorNumber(@ColorInt int colorNumber) {
        this.colorNumber = colorNumber;
        tvNumber.setTextColor(colorNumber);
    }


    /**
     * Returns a colored Text .If the color of Text is not set,then default color from the set is used.
     *
     * @see #getColorValue()
     *
     * @return Colored Value Text
     *
     * @exception NullPointerException If the Text is not initialized then it throws the exception
     */

    public int getColorValue() {
        return colorValue;
    }

    /**
     * Sets the text color for all the states (normal, selected,
     * focused) to be this color.
     *
     * @param colorValue A color value in the form 0xAARRGGBB.
     * Do not pass a resource ID. To get a color value from a resource ID, call
     * {@link android.support.v4.content.ContextCompat#getColor(Context, int) getColor}.
     *
     * @see #setColorValue(int color)
     * @see #getColorValue()
     *
     * @attr ref android.R.styleable#TextView_textColor
     */

    public void setColorValue(@ColorInt int colorValue) {
        this.colorValue = colorValue;
        tvTextTitle.setTextColor(colorValue);
    }

    /**
     * Returns the minimum value
     *
     * @see  #getMinValue()
     * @return minValue
     */

    public int getMinValue() {
        return minValue;
    }

    /**
     * Sets the minimum value
     *
     * @param minValue Integer value passed in this parameter to set as minimum value
     *
     * @see #setMinValue(int minValue)
     * @see #getMinValue()
     *
     */
    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    /**
     * Returns the maximum value
     *
     * @see  #getMaxValue()
     * @return maxValue
     */
    public int getMaxValue() {
        return maxValue;
    }

    /**
     * Sets the maximum value of the number.
     *
     * @param maxValue Integer value passed in this parameter to set as maximum value
     *
     * @see #setMaxValue(int maxValue)
     * @see #getMaxValue()
     *
     */

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    /**
     * Retrieves the Initial Value.
     * If the Initial Value is not initialized then 50 is set as Initial Value
     *
     * @return initialValue
     */

    public int getInitialValue() {
        return initialValue;
    }

    /**
     * Sets the Initial Value on the Counter.
     * If the Initial Value is greater than maximum value then
     * Initial value is set as max value
     * and If value is smaller than minimum value then Initial Value is
     * set as minimum value
     * @param initialValue Integer value is passed to set as Initial Value
     */

    public void setInitialValue(int initialValue) {
        this.initialValue = initialValue;
    }

}
