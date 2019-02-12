package com.example.rps.krenwk.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rps.krenwk.R;


public class FragmentVideo extends Fragment {
    public static FragmentVideo newInstance() {
        FragmentVideo fragment = new FragmentVideo();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.video_fragment, container, false);

        //create tab Host
        FragmentTabHost tabHost = (FragmentTabHost)rootView.findViewById(R.id.tab_host);
        tabHost.setup(getActivity(),getChildFragmentManager(),android.R.id.tabcontent);
        tabHost.addTab(tabHost.newTabSpec("Remote Survielance").setIndicator("Remote Survielance"),
                FragmentVideosurviellance.class,null);
        tabHost.addTab(tabHost.newTabSpec("Live Broadcast").setIndicator("Live Broadcast"),
                FragmentVideobroadcast.class,null);
        tabHost.addTab(tabHost.newTabSpec("Video on Demand").setIndicator("Video on Demand"),
                VideoOnDemandFragment.class,null);

        return rootView;
    }
}
