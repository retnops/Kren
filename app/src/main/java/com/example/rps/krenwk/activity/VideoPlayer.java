package com.example.rps.krenwk.activity;

import android.content.Context;
import android.net.Uri;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.DynamicConcatenatingMediaSource;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class VideoPlayer {

    private Context context;
    private DynamicConcatenatingMediaSource dynamicConcatenatingMediaSource;
    private SimpleExoPlayerView playerView;
    private SimpleExoPlayer player;

    public VideoPlayer(Context context, SimpleExoPlayerView playerView){
        this.context = context;
        this.dynamicConcatenatingMediaSource = new DynamicConcatenatingMediaSource();
        this.playerView = playerView;
        init_player();
    }

    private void init_player(){
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);
        player = ExoPlayerFactory.newSimpleInstance(context, trackSelector);
        playerView.setPlayer(player);
    }

    public void addMedia(String url){
        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(
                context, Util.getUserAgent(context, "cuc_enny"),bandwidthMeter
        );
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
        MediaSource mediaSource = new ExtractorMediaSource(Uri.parse(url),
                dataSourceFactory, extractorsFactory, null, null);
        if(dynamicConcatenatingMediaSource.getSize()==0){
            dynamicConcatenatingMediaSource.addMediaSource(mediaSource);
            player.prepare(dynamicConcatenatingMediaSource);
            player.setPlayWhenReady(true);
        }
        else {
            dynamicConcatenatingMediaSource.addMediaSource(mediaSource);
        }
    }
}
