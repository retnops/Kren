package com.example.rps.krenwk.activity;

//import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.Fragment;
import android.widget.TextView;
import android.widget.Toast;


import com.example.rps.krenwk.R;
import com.example.rps.krenwk.fragment.FragmentDraftmessage;
import com.example.rps.krenwk.fragment.FragmentEnterprisedirectory;
import com.example.rps.krenwk.fragment.FragmentHome;
import com.example.rps.krenwk.fragment.FragmentMore;
import com.example.rps.krenwk.fragment.FragmentNearby;
import com.example.rps.krenwk.fragment.FragmentNearbypeople;
import com.example.rps.krenwk.fragment.FragmentNearbyplace;
import com.example.rps.krenwk.fragment.FragmentNews;
import com.example.rps.krenwk.fragment.FragmentVideo;
import com.example.rps.krenwk.fragment.FragmentVideobroadcast;
import com.example.rps.krenwk.fragment.FragmentVideodemand;
import com.example.rps.krenwk.fragment.FragmentVideosurviellance;
import com.example.rps.krenwk.fragment.Sms;
import com.example.rps.krenwk.fragment.VideoOnDemandFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //private FragmentHome fragmentHome;

    private Sms sms;

    private FragmentEnterprisedirectory fragmentEnterprisedirectory;

    private FragmentVideo fragmentVideo;
    private FragmentVideobroadcast fragmentVideobroadcast;
    private FragmentVideosurviellance fragmentVideosurviellance;
    private VideoOnDemandFragment videoOnDemandFragment;

    private FragmentNearby fragmentNearby;
    private FragmentNearbypeople fragmentNearbypeople;
    private FragmentNearbyplace fragmentNearbyplace;

    private FragmentNews fragmentNews;

    private FragmentMore fragmentMore;
    TextView nameNav;
    TextView emailNav;

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        Context context = getApplicationContext();
        // Toast toast = new Toast(context);
        if (id == R.id.drawer_unified) {
            setFragment(sms);
        } else if (id == R.id.drawer_enterprise) {
            setFragment(fragmentEnterprisedirectory);
        } else if (id == R.id.drawer_video) {
            setFragment(videoOnDemandFragment);
        } else if (id == R.id.drawer_near) {
            setFragment(fragmentNearby);
        } else if (id == R.id.drawer_news) {
            setFragment(fragmentNews);
        } else if (id == R.id.drawer_more) {
            setFragment(fragmentMore);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getSupportActionBar().hide();
        setTitle("Kren Work");
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(android.graphics.Color.WHITE);
        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();
        //actionbar.setLogo(R.drawable.rsz_2logo);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.Open, R.string.Close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        nameNav = findViewById(R.id.nameNav);
        emailNav = findViewById(R.id.emailNav);

        sms = new Sms();
        fragmentEnterprisedirectory = new FragmentEnterprisedirectory();
        fragmentVideo = new FragmentVideo();
        fragmentVideobroadcast = new FragmentVideobroadcast();
        fragmentVideosurviellance = new FragmentVideosurviellance();
        videoOnDemandFragment = new VideoOnDemandFragment();

        fragmentNearby = new FragmentNearby();
        fragmentNearbypeople = new FragmentNearbypeople();
        fragmentNearbyplace = new FragmentNearbyplace();

        fragmentNews = new FragmentNews();
        fragmentMore = new FragmentMore();

        setFragment(sms);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.opt_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.all:

                break;
            case R.id.fave:

                break;
            case R.id.videoroom:
                Intent j = new Intent(this, VideoRoom.class);
                startActivity(j);
                break;

            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}