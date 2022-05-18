package ru.mirea.mahmoud.b.i.livedatastring;

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

public class MainActivity extends AppCompatActivity implements Observer<String> {
    private TextView networkNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        networkNameTextView = findViewById(R.id.textView);
        TimeLiveData.getDate().observe(this, this);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                TimeLiveData.setTime();
            }
        }, 5000);
    }

    @Override
    public void onChanged(String s) {
        Log.d(MainActivity.class.getSimpleName(), s + "");
        networkNameTextView.setText("" + s);
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
    private static LiveData getStringTime = Transformations.map(data, new Function<Long, String>() {
        @Override
        public String apply(Long input) {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return dateFormat.format(calendar.getTime());
        }
    });
    static LiveData<String> getDate(){
        return getStringTime;
    }

}