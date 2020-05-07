package com.dixitgarg.counterviewlibrary.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dixitgarg.counterviewlibrary.R;
import com.dixitgarg.counterviewlibrary.views.CounterView;

public class MainActivity extends AppCompatActivity implements CounterView.OnValuesClickListener{

    CounterView counterView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        counterView = findViewById(R.id.counter);
        counterView.setOnValueChangeListener(this);
    }

    @Override
    public void OnValueChange(int oldValue, int newValue) {

        Toast.makeText(this, "New Value: "+counterView.getNewValue()+"\nOld Value: "+counterView.getCurrentValue(), Toast.LENGTH_SHORT).show();

    }

}
