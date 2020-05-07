package com.sew.customviewapplication.activities;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.sew.customviewapplication.R;
import com.sew.customviewapplication.views.CounterView;

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
