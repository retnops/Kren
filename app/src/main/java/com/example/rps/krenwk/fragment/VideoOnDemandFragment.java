package com.example.rps.krenwk.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.VideoView;

import com.example.rps.krenwk.R;
import com.example.rps.krenwk.activity.DBhelper;
import com.example.rps.krenwk.activity.ThumbnailAdapter;
import com.example.rps.krenwk.activity.UsersVideodemand;
import com.example.rps.krenwk.activity.VideoMain;
import com.example.rps.krenwk.activity.VideoPlayer;

import java.util.ArrayList;


public class VideoOnDemandFragment extends Fragment {

    VideoView videoView;
    ProgressBar progressBar;
    private VideoPlayer player;

    ArrayList<UsersVideodemand> videoFileList = new ArrayList<UsersVideodemand>();

    private String url ;



    public static VideoOnDemandFragment newInstance() {
        VideoOnDemandFragment fragment = new VideoOnDemandFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.video_on_demand, container, false);


        DBhelper db = new DBhelper(getActivity().getApplicationContext());
        videoFileList = (ArrayList<UsersVideodemand>) db.getAllVideo();
        url = videoFileList.get(0).getUrl();


        ListView listView =
                (ListView)v.findViewById(R.id.list_video);
//        listView.setExpanded(true);
        listView.setAdapter(new ThumbnailAdapter(
                getActivity().getApplicationContext(), R.layout.row_video_on_demand, videoFileList));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //  Toast.makeText(getActivity().getApplicationContext(), toString(position),Toast.LENGTH_LONG).show();
                Intent i = new Intent(getActivity().getApplicationContext(), VideoMain.class);
                i.putExtra("position", position);
                startActivity(i);

            }
        });

        return v;
    }
}