package com.example.rps.krenwk.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.rps.krenwk.R;
import com.example.rps.krenwk.fragment.Camera2VideoBroadcast;
import com.example.rps.krenwk.fragment.Camera2VideoBroadcastTransition;
import com.example.rps.krenwk.fragment.Camera2VideoRoom;

public class VideoBroadcastTransition extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room_video);
        if (null == savedInstanceState) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, Camera2VideoBroadcastTransition.newInstance())
                    .commit();
        }

        Toolbar toolbar = findViewById(R.id.videoroomtoolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
    }

}