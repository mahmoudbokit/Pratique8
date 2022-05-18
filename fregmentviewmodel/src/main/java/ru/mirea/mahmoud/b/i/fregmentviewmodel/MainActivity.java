package ru.mirea.mahmoud.b.i.fregmentviewmodel;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {
    Button fragmentBtn1, fragmentBtn2;
    TextView fragmentText;
    private SharedViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentBtn1 = findViewById(R.id.fragmentBtn1);
        fragmentBtn2 = findViewById(R.id.fragmentBtn2);

        fragmentText = findViewById(R.id.fragmentText);

        viewModel = new ViewModelProvider(this).get(SharedViewModel.class);
        viewModel.getSelectedItem().observe(this, item->{
            fragmentText.setText(item);
        });

        fragmentBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Fragment1();
            }
        });
        fragmentBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Fragment2();
            }
        });
    }
}