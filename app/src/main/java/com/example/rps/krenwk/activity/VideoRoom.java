package com.example.rps.krenwk.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.rps.krenwk.R;
import com.example.rps.krenwk.fragment.Camera2VideoRoom;

public class VideoRoom extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room_video);
        if (null == savedInstanceState) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, Camera2VideoRoom.newInstance())
                    .commit();
    }
        setTitle("Kren Work");
        Toolbar toolbar = findViewById(R.id.videoroomtoolbar);
        toolbar.setTitleTextColor(android.graphics.Color.WHITE);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        //actionbar.setLogo(R.drawable.rsz_2logo);
        actionbar.setDisplayHomeAsUpEnabled(true);
    }

}


