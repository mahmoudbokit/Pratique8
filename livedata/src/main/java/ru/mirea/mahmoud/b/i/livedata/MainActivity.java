package ru.mirea.mahmoud.b.i.livedata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements Observer<Long> {
    private TextView networkNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        networkNameTextView = findViewById(R.id.textView);
        TimeLiveData.getTime().observe(this, this);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                TimeLiveData.setTime();
            }
        }, 5000);
    }

    @Override
    public void onChanged(Long aLong) {
        Log.d(MainActivity.class.getSimpleName(), aLong + "");
        networkNameTextView.setText("" + aLong);
    }
}
class TimeLiveData{
    private static MutableLiveData<Long> data = new MutableLiveData<Long>();
    static LiveData<Long> getTime(){
        data.setValue(new Date().getTime());
        return data;
    }
    static void  setTime(){
        data.setValue(new Date().getTime());
    }
}