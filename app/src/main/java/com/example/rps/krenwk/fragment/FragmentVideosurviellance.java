package com.example.rps.krenwk.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rps.krenwk.R;



public class FragmentVideosurviellance extends Fragment {

    public static FragmentVideosurviellance newInstance() {
        FragmentVideosurviellance fragment = new FragmentVideosurviellance();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.videosurviellance_fragment, container, false);
    }
}
