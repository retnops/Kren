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
import com.example.rps.krenwk.activity.ContactActivity;


public class FragmentEnterprisedirectory extends Fragment {

    public static FragmentEnterprisedirectory newInstance(){
        FragmentEnterprisedirectory fragment = new FragmentEnterprisedirectory();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.enterprisedirectory_fragment, container, false);

        //create tab Host
        FragmentTabHost tabHost = (FragmentTabHost)rootView.findViewById(R.id.tab_host);
        tabHost.setup(getActivity(),getChildFragmentManager(),android.R.id.tabcontent);
        tabHost.addTab(tabHost.newTabSpec("Organisation").setIndicator("Organisation"),
                FragmentEnterpriseOrganisation.class,null);

        tabHost.addTab(tabHost.newTabSpec("Private").setIndicator("Private"),
                FragmentEnterprisePrivate.class,null);

        return rootView;


    }

}