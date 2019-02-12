package com.example.rps.krenwk.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.rps.krenwk.R;
import com.example.rps.krenwk.fragment.Camera2VideoRoom;
import com.example.rps.krenwk.fragment.Camera2VideoSurviellance;

public class VideoSurviellance extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.surviellance_video);
        if (null == savedInstanceState) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, Camera2VideoSurviellance.newInstance())
                    .commit();
        }

//        Toolbar toolbar = findViewById(R.id.videoroomtoolbar);
//        setSupportActionBar(toolbar);
//        ActionBar actionbar = getSupportActionBar();
//        actionbar.setDisplayHomeAsUpEnabled(true);
    }

}