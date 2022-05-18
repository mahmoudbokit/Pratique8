package ru.mirea.mahmoud.b.i.viewmodel;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

public class MainActivity extends AppCompatActivity {
    private ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bar = findViewById(R.id.progressBar);
        ProgressViewModel viewModel = new ProgressViewModel();
        viewModel.getProgressState().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isVisibleProgressBar) {
                if (isVisibleProgressBar){
                    bar.setVisibility(View.VISIBLE);
                }else {
                    bar.setVisibility(View.GONE);
                }
            }
        });
        viewModel.showProgress();
    }
}
class ProgressViewModel extends ViewModel{
    private static MutableLiveData<Boolean>  isShowProgress = new MutableLiveData<>();
    void showProgress(){
        isShowProgress.postValue(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                isShowProgress.postValue(false);
            }
        }, 10000);
    }
    MutableLiveData<Boolean> getProgressState(){
        return isShowProgress;

    }
}