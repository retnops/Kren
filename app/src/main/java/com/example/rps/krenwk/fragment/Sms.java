package com.example.rps.krenwk.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.MergeCursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.rps.krenwk.R;
import com.example.rps.krenwk.activity.Chat;
import com.example.rps.krenwk.activity.FunctionSms;
import com.example.rps.krenwk.activity.MapSmscomparator;
import com.example.rps.krenwk.activity.NewSmsActivity;
import com.lifeofcoding.cacheutlislibrary.CacheUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Sms extends Fragment {

    static final int REQUEST_PERMISSION_KEY = 1;
    ArrayList<HashMap<String, String>> smsList = new ArrayList<HashMap<String, String>>();
    ArrayList<HashMap<String, String>> tmpList = new ArrayList<HashMap<String, String>>();
    static Sms inst;
    LoadSms loadsmsTask;
    InboxAdapter adapter, tmpadapter;;
    ListView listView;
    FloatingActionButton fab_new;
    ProgressBar loader;
    int i;
    public static Sms newInstance() {
        Sms fragment = new Sms();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.sms, container, false);

        CacheUtils.configureCache(getActivity().getApplicationContext());

        listView = (ListView) v.findViewById(R.id.listView);
        loader = (ProgressBar) v.findViewById(R.id.loader);
        fab_new = (FloatingActionButton) v.findViewById(R.id.fab_new);

        listView.setEmptyView(loader);


        fab_new.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getActivity().getApplicationContext(), NewSmsActivity.class));
            }
        });
    return v;
    }



    public void init()
    {
        smsList.clear();
        try{
            tmpList = (ArrayList<HashMap<String, String>>) FunctionSms.readCachedFile  (getActivity().getApplicationContext(), "smsapp");
            tmpadapter = new InboxAdapter(getActivity(), tmpList);
            listView.setAdapter(tmpadapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view,
                                        final int position, long id) {
                    loadsmsTask.cancel(true);
                    Intent intent = new Intent(getActivity().getApplicationContext(), Chat.class);
                    intent.putExtra("name", tmpList.get(+position).get(FunctionSms.KEY_NAME));
                    intent.putExtra("address", tmpList.get(+position).get(FunctionSms.KEY_PHONE));
                    intent.putExtra("thread_id", tmpList.get(+position).get(FunctionSms.KEY_THREAD_ID));
                    startActivity(intent);
                }
            });
        }catch(Exception e) {}

    }


    class LoadSms extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            smsList.clear();
        }

        protected String doInBackground(String... args) {
            String xml = "";

            try {
                Uri uriInbox = Uri.parse("content://sms/inbox");

                Cursor inbox = getActivity().getApplicationContext().getContentResolver().query(uriInbox, null, "address IS NOT NULL) GROUP BY (thread_id", null, null); // 2nd null = "address IS NOT NULL) GROUP BY (address"
                Uri uriSent = Uri.parse("content://sms/sent");
                Cursor sent = getActivity().getApplicationContext().getContentResolver().query(uriSent, null, "address IS NOT NULL) GROUP BY (thread_id", null, null); // 2nd null = "address IS NOT NULL) GROUP BY (address"
                Cursor c = new MergeCursor(new Cursor[]{inbox,sent}); // Attaching inbox and sent sms


                if (c.moveToFirst()) {
                    for (int i = 0; i < c.getCount(); i++) {
                        String name = null;
                        String phone = "";
                        String _id = c.getString(c.getColumnIndexOrThrow("_id"));
                        String thread_id = c.getString(c.getColumnIndexOrThrow("thread_id"));
                        String msg = c.getString(c.getColumnIndexOrThrow("body"));
                        String type = c.getString(c.getColumnIndexOrThrow("type"));
                        String timestamp = c.getString(c.getColumnIndexOrThrow("date"));
                        phone = c.getString(c.getColumnIndexOrThrow("address"));



                        name = CacheUtils.readFile(thread_id);
                        if(name == null)
                        {
                            name = FunctionSms.getContactbyPhoneNumber(getActivity().getApplicationContext().getApplicationContext(), c.getString(c.getColumnIndexOrThrow("address")));
                            CacheUtils.writeFile(thread_id, name);
                        }


                        smsList.add(FunctionSms.mappingInbox(_id, thread_id, name, phone, msg, type, timestamp, FunctionSms.converToTime(timestamp)));
                        c.moveToNext();
                    }
                }
                c.close();

            }catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            Collections.sort(smsList, new MapSmscomparator(FunctionSms.KEY_TIMESTAMP, "dsc")); // Arranging sms by timestamp decending
            ArrayList<HashMap<String, String>> purified = FunctionSms.removeDuplicates(smsList); // Removing duplicates from inbox & sent
            smsList.clear();
            smsList.addAll(purified);

            // Updating cache data
            try{
                FunctionSms.createCachedFile (getActivity().getApplicationContext(),"smsapp", smsList);
            }catch (Exception e) {}
            // Updating cache data

            return xml;
        }

        @Override
        protected void onPostExecute(String xml) {

            if(!tmpList.equals(smsList))
            {
                adapter = new InboxAdapter(getActivity(), smsList);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view,
                                            final int position, long id) {
                        Intent intent = new Intent(getActivity().getApplicationContext(), Chat.class);
                        intent.putExtra("name", smsList.get(+position).get(FunctionSms.KEY_NAME));
                        intent.putExtra("address", tmpList.get(+position).get(FunctionSms.KEY_PHONE));
                        intent.putExtra("thread_id", smsList.get(+position).get(FunctionSms.KEY_THREAD_ID));
                        startActivity(intent);
                    }
                });
            }



        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case REQUEST_PERMISSION_KEY: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    init();
                    loadsmsTask = new LoadSms();
                    loadsmsTask.execute();
                } else
                {
                    Toast.makeText(getActivity().getApplicationContext(), "You must accept permissions.", Toast.LENGTH_LONG).show();
                }
            }
        }

    }


    @Override
    public void onResume() {
        super.onResume();

        String[] PERMISSIONS = {Manifest.permission.READ_SMS, Manifest.permission.SEND_SMS, Manifest.permission.RECEIVE_SMS,
                Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS};
        if(!FunctionSms.hasPermissions(getActivity(), PERMISSIONS)){
            ActivityCompat.requestPermissions(getActivity(), PERMISSIONS, REQUEST_PERMISSION_KEY);
        }else{

            init();
            loadsmsTask = new LoadSms();
            loadsmsTask.execute();
        }

    }


    @Override
    public void onStart() {
        super.onStart();


    }

}


class InboxAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<HashMap< String, String >> data;
    public InboxAdapter(Activity a, ArrayList < HashMap < String, String >> d) {
        activity = a;
        data = d;
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
        InboxViewHolder holder = null;
        if (convertView == null) {
            holder = new InboxViewHolder();
            convertView = LayoutInflater.from(activity).inflate(
                    R.layout.sms_conversationlistitem, parent, false);

            holder.inbox_thumb = (ImageView) convertView.findViewById(R.id.inbox_thumb);
            holder.inbox_user = (TextView) convertView.findViewById(R.id.inbox_user);
            holder.inbox_msg = (TextView) convertView.findViewById(R.id.inbox_msg);
            holder.inbox_date = (TextView) convertView.findViewById(R.id.inbox_date);

            convertView.setTag(holder);
        } else {
            holder = (InboxViewHolder) convertView.getTag();
        }
        holder.inbox_thumb.setId(position);
        holder.inbox_user.setId(position);
        holder.inbox_msg.setId(position);
        holder.inbox_date.setId(position);

        HashMap < String, String > song = new HashMap < String, String > ();
        song = data.get(position);
        try {
            holder.inbox_user.setText(song.get(FunctionSms.KEY_NAME));
            holder.inbox_msg.setText(song.get(FunctionSms.KEY_MSG));
            holder.inbox_date.setText(song.get(FunctionSms.KEY_TIME));

            String firstLetter = String.valueOf(song.get(FunctionSms.KEY_NAME).charAt(0));
            ColorGenerator generator = ColorGenerator.MATERIAL;
            int color = generator.getColor(getItem(position));
            TextDrawable drawable = TextDrawable.builder()
                    .buildRound(firstLetter, color);
            holder.inbox_thumb.setImageDrawable(drawable);
        } catch (Exception e) {}
        return convertView;
    }
}


class InboxViewHolder {
    ImageView inbox_thumb;
    TextView inbox_user, inbox_msg, inbox_date;
}
