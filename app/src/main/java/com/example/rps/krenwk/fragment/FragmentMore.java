package com.example.rps.krenwk.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rps.krenwk.R;
import com.example.rps.krenwk.activity.MoreProfile;


public class FragmentMore extends Fragment implements View.OnClickListener {
    private CardView
            moreProfile,
            moreDigitalform,
            moreDashboard,
            moreFormmanager,
            moreCalendar,
            moreKrenwallet,
            moreSetting;

    public static FragmentMore newInstance() {
        FragmentMore fragment = new FragmentMore();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.more_fragment, container, false);
        moreProfile = (CardView) rootview.findViewById(R.id.moreprofile);
        moreDigitalform = (CardView) rootview.findViewById(R.id.moredigitalform);
        moreDashboard = (CardView) rootview.findViewById(R.id.moredashboard);
        moreFormmanager = (CardView) rootview.findViewById(R.id.moreformmanager);
        moreCalendar = (CardView) rootview.findViewById(R.id.morecalendar);
        moreKrenwallet = (CardView) rootview.findViewById(R.id.morekrenwallet);
        moreSetting = (CardView) rootview.findViewById(R.id.moresetting);

        moreProfile.setOnClickListener(this);
        moreDigitalform.setOnClickListener(this);
        moreDashboard.setOnClickListener(this);
        moreFormmanager.setOnClickListener(this);
        moreCalendar.setOnClickListener(this);
        moreKrenwallet.setOnClickListener(this);
        moreSetting.setOnClickListener(this);

        return rootview;
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.moreprofile:
                i = new Intent(getActivity().getApplicationContext(), MoreProfile.class);
                startActivity(i);
                break;
            case R.id.moredigitalform:
                i = new Intent(getActivity().getApplicationContext(), MoreProfile.class);
                startActivity(i);
                break;
            case R.id.moredashboard:
                i = new Intent(getActivity().getApplicationContext(), MoreProfile.class);
                startActivity(i);
                break;
            case R.id.moreformmanager:
                i = new Intent(getActivity().getApplicationContext(), MoreProfile.class);
                startActivity(i);
                break;
            case R.id.morecalendar:
                i = new Intent(getActivity().getApplicationContext(), MoreProfile.class);
                startActivity(i);
                break;
            case R.id.morekrenwallet:
                i = new Intent(getActivity().getApplicationContext(), MoreProfile.class);
                startActivity(i);
                break;
            case R.id.moresetting:
                i = new Intent(getActivity().getApplicationContext(), MoreProfile.class);
                startActivity(i);
                break;
                default:
                    break;


        }
    }
}


