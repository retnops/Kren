package com.example.rps.krenwk.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rps.krenwk.R;

import java.util.ArrayList;

public class VideoListAdapter extends ArrayAdapter<UsersVideodemand>{
    private int lastposition = -1;

    private ArrayList<UsersVideodemand> dataset;
    Context context;

    private static class ViewHolder{
        TextView txtTitle;
        TextView txtDesc;
        ImageView img;
    }

    public VideoListAdapter(ArrayList<UsersVideodemand> data, Context context){
        super(context, R.layout.video_item, data);
        this.dataset = data;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        UsersVideodemand datamodel = getItem(position);
        ViewHolder viewHolder;
        final View result;

        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.video_item, parent, false);
            viewHolder.txtTitle = (TextView) convertView.findViewById(R.id.video_list_title);
            viewHolder.txtDesc = (TextView)convertView.findViewById(R.id.video_list_desc);
            viewHolder.img = (ImageView) convertView.findViewById(R.id.video_list_thumbnail);

            result=convertView;

            convertView.setTag(viewHolder);
        }
        else {
            viewHolder= (ViewHolder)convertView.getTag();
            result = convertView;
        }

        viewHolder.txtTitle.setText(datamodel.getTitle());
        viewHolder.txtDesc.setText(datamodel.getDescription());

        return convertView;
    }


}
