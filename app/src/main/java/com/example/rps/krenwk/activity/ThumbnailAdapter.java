package com.example.rps.krenwk.activity;


import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.Layout;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rps.krenwk.R;

import org.xmlpull.v1.XmlPullParser;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ThumbnailAdapter extends ArrayAdapter<UsersVideodemand> {

    Context context;
    int textViewResourcedId;
    ArrayList<UsersVideodemand> videoFileList;
    LayoutInflater inflater;
    Calendar date;
    Calendar now;


    public ThumbnailAdapter(Context context, int textViewResourceId,
                            ArrayList<UsersVideodemand> objects) {
        super(context, textViewResourceId, objects);
        this.context = context;
        this.textViewResourcedId = textViewResourceId;
        videoFileList = objects;
        this.inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // TODO Auto-generated constructor stub
    }


    @Override
    public UsersVideodemand getItem(int position){
        return videoFileList.get(position);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        int resID, reslayout;
        String thumb, dateTime = "today";
        now = Calendar.getInstance();
        date = Calendar.getInstance();


        View row=inflater.inflate(this.textViewResourcedId, parent, false);
        //const title variable
        if (!TextUtils.isEmpty(videoFileList.get(position).getThumbnail())) {
            thumb = videoFileList.get(position).getThumbnail();
        }
        else
        {
            thumb = "no_video";
        }
        //const thumbnail variable
        resID = context.getResources().getIdentifier(thumb, "drawable", "com.example.rps.krenwk");
        //const date variable
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date.setTime(format.parse(videoFileList.get(position).getCreatetime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        date.get(Calendar.YEAR);
        now.get(Calendar.YEAR);

        if((now.get(Calendar.YEAR)-date.get(Calendar.YEAR)) != 0) {
            dateTime = Integer.toString((now.get(Calendar.YEAR) - date.get(Calendar.YEAR)))+" years";
        }
        else if((now.get(Calendar.MONTH)-date.get(Calendar.MONTH)) != 0){
            dateTime = Integer.toString((now.get(Calendar.MONTH) - date.get(Calendar.MONTH))+1)+" months";
        }
        else if((now.get(Calendar.DAY_OF_MONTH)-date.get(Calendar.DAY_OF_MONTH)) != 0){
            dateTime = Integer.toString((now.get(Calendar.DAY_OF_MONTH)-date.get(Calendar.DAY_OF_MONTH)))+" days";
        }


        TextView textfilePath = (TextView)row.findViewById(R.id.title);
        textfilePath.setText( videoFileList.get(position).getTitle());
        //textfilePath.setTextSize(48);

        ImageView imageThumbnail = (ImageView)row.findViewById(R.id.thumbnail);
        imageThumbnail.setImageResource(resID);


        TextView dateText = (TextView)row.findViewById(R.id.datetime);
        //dateText.setText(dateTime+" ago");
        dateText.setText((dateTime)+" ago");


        return row;


    }




}
