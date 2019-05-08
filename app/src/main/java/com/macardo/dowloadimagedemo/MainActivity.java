package com.macardo.dowloadimagedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private ImageView iv;
    private String imagePath = "https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1557330772&di=1b789a59e0be9ff033f7722ce4080d05&src=http://s02.ourgame.com.cn/g1/M00/1C/60/wKgCyVLQ6hWKSexrAAEeK5kNWvE185.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        iv = findViewById(R.id.imageView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DownLoadAsyncTask(iv).execute(imagePath);
            }
        });
    }
}
