package com.example.rps.krenwk.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rps.krenwk.R;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;

import java.util.ArrayList;
import java.util.List;

public class VideoMain extends AppCompatActivity{

    private VideoPlayer player;
    ArrayList<UsersVideodemand> videoFileList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_main);

//        videoFileList.add("https://s3.amazonaws.com/androidvideostutorial/862009639.mp4");
//        videoFileList.add("https://s3.amazonaws.com/androidvideostutorial/862013714.mp4");
//        videoFileList.add("https://s3.amazonaws.com/androidvideostutorial/862014159.mp4");
//        videoFileList.add("https://s3.amazonaws.com/androidvideostutorial/862014159.mp4");
//        videoFileList.add("https://s3.amazonaws.com/androidvideostutorial/862014834.mp4");
//        videoFileList.add("https://s3.amazonaws.com/androidvideostutorial/862017385.mp4");
//        videoFileList.add("https://www.videvo.net/?page_id=123&desc=river_and_rocks_wide.mp4&vid=2540");
//        videoFileList.add("https://nageia.fruithosted.net/dl/n/7ojWz8BmTPgGmENK/nqqprrdpeatekltm/p4afTa05K65rWKvWdWDanI55rYfWucgGQpQXs2Q3GKj1wnFdtV5MWmzKEuSWG4-NkFcGKu0anpxFMZ3X4GKiPkaE2ZXG1TIICC94w9vTSUtazDzgS2imqwm7fBbqVzY7cdEK0ZW-d9E1rOWfoemoFd1hVwBluBa-ssE-k4cn-9Ot3ksVxJFXNKIG1FmFCdEMqrdR4W5g7W5OquOHhxjO40FFpHP6Q_QVE-KBwkKHol1nutvnAb-vChTNI7pZBKMbDGTXk6qXQ3O1zProxh6xU8DWWNoLKNv4W4Lkd_-ROVlKDaIoZqqrJ2V_dTG9YckEdxJrGkkwgg0ccu1er22VTyID_Kmd5YU19sDKXMeAFMsBgYZNDGehTQBc8uje1u425E7NbvIDDhyr3Evc-p2mW17H9l8x1kVgyt5bncmXdgvn07K_xmRM1Ybi7J7L877S/CaptainTsubasa01ENG720p.avi-rh-454.mp4");



        Bundle extras = getIntent().getExtras();

        DBhelper db = new DBhelper(getApplicationContext());
        videoFileList = (ArrayList<UsersVideodemand>) db.getAllVideo();

        player = new VideoPlayer(
                getApplicationContext(),
                (SimpleExoPlayerView)findViewById(R.id.player_view)
        );

        player.addMedia(videoFileList.get(extras.getInt("position")).getUrl());



        ListView listView = (ListView)findViewById(R.id.list_video);
        listView.setAdapter(new ThumbnailAdapter(
                getApplicationContext(), R.layout.row_video, videoFileList));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(), videoFileList.get(position).getTitle(),Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(), VideoMain.class);
                i.putExtra("position", position);
                finish();
                startActivity(i);
            }
        });

    }
}
