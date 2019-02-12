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


public class FragmentHome extends Fragment {
    public static FragmentHome newInstance() {
        FragmentHome fragment = new FragmentHome();
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_fragment, container, false);
        //create tab Host
        FragmentTabHost tabHost = (FragmentTabHost)rootView.findViewById(R.id.tab_host);
        tabHost.setup(getActivity(),getChildFragmentManager(),android.R.id.tabcontent);
        tabHost.addTab(tabHost.newTabSpec("Message").setIndicator("Message"),
                FragmentDraftmessage.class,null);
        tabHost.addTab(tabHost.newTabSpec("Enterprise").setIndicator("Enterprise"),
                Sms.class,null);


        return rootView;
    }




}
