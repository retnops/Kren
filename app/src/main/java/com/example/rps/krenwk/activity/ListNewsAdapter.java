package com.example.rps.krenwk.activity;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rps.krenwk.R;
import com.example.rps.krenwk.fragment.FragmentNews;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;


public class ListNewsAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<HashMap<String, String>> data;

    public ListNewsAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
        activity = a;
        data=d;
    }
    public int getCount() {
        return data.size();
    }
    public Object getItem(int position) {
        return position;
    }
    public long getItemId(int position) {
        return position;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        ListNewsViewHolder holder = null;
        if (convertView == null) {
            holder = new ListNewsViewHolder();
            convertView = LayoutInflater.from(activity).inflate(
                    R.layout.rowrss, parent, false);
            holder.galleryImage = (ImageView) convertView.findViewById(R.id.galleryImage);
            holder.author = (TextView) convertView.findViewById(R.id.author);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.sdetails = (TextView) convertView.findViewById(R.id.sdetails);
            holder.time = (TextView) convertView.findViewById(R.id.time);
            convertView.setTag(holder);
        } else {
            holder = (ListNewsViewHolder) convertView.getTag();
        }
        holder.galleryImage.setId(position);
        holder.author.setId(position);
        holder.title.setId(position);
        holder.sdetails.setId(position);
        holder.time.setId(position);

        HashMap<String, String> song = new HashMap<String, String>();
        song = data.get(position);

        try{
            holder.author.setText(song.get(FragmentNews.KEY_AUTHOR));
            holder.title.setText(song.get(FragmentNews.KEY_TITLE));
            holder.time.setText(song.get(FragmentNews.KEY_PUBLISHEDAT));
            holder.sdetails.setText(song.get(FragmentNews.KEY_DESCRIPTION));

            if(song.get(FragmentNews.KEY_URLTOIMAGE).toString().length() < 5)
            {
                holder.galleryImage.setVisibility(View.GONE);
            }else{
                Picasso.with(activity)
                        .load(song.get(FragmentNews.KEY_URLTOIMAGE).toString())
                        .resize(300, 200)
                        .into(holder.galleryImage);
            }
        }catch(Exception e) {}
        return convertView;
    }
}

class ListNewsViewHolder {
    ImageView galleryImage;
    TextView author, title, sdetails, time;
}