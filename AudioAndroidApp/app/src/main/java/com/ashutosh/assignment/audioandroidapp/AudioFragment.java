package com.ashutosh.assignment.audioandroidapp;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class AudioFragment extends Fragment {

    private SeekBar time;
    private TextView startTime;
    private FloatingActionButton playPause;
    private BaseViewModel baseViewModel;
    private boolean isPlaying=false;
    private int timeElapsed=0;
    private MediaPlayer mediaPlayer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_audio, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView thumbnail = view.findViewById(R.id.audio_thumbnail);
        time = view.findViewById(R.id.audio_time);
        time.setEnabled(false);
        startTime = view.findViewById(R.id.audio_start_time);
        TextView endTime = view.findViewById(R.id.audio_end_time);
        playPause = view.findViewById(R.id.audio_play_pause);
        baseViewModel = new ViewModelProvider(this).get(BaseViewModel.class);
        mediaPlayer = MediaPlayer.create(requireContext(), R.raw.inspirational);
        Glide.with(this).load(R.drawable.soothing).into(thumbnail);
        time.setMax(mediaPlayer.getDuration());
        String formattedTime = formatTime(mediaPlayer.getDuration());
        endTime.setText(formattedTime);
        playPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isPlaying) {
                    isPlaying=true;
                    playPause.setImageDrawable(requireContext().getDrawable(R.drawable.ic_pause));
                    startIncrementingTime();
                    if(mediaPlayer.getCurrentPosition()!=timeElapsed)
                        mediaPlayer.seekTo(timeElapsed);
                    if(!mediaPlayer.isPlaying())
                        mediaPlayer.start();
                }else{
                    isPlaying=false;
                    mediaPlayer.pause();
                    playPause.setImageDrawable(requireContext().getDrawable(R.drawable.ic_play));
                }
            }
        });
    }

    private void startIncrementingTime() {
        if(isPlaying && timeElapsed <= (mediaPlayer.getDuration()-1000)) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    timeElapsed += 1000;
                    startTime.setText(formatTime(timeElapsed));
                    baseViewModel.setElapsedTime(timeElapsed);
                    time.setProgress(timeElapsed);
                    startIncrementingTime();
                }
            }, 1000);
        }
    }

    private String formatTime(int duration) {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        SimpleDateFormat df = new SimpleDateFormat("mm:ss");
        df.setTimeZone(tz);
        String time = df.format(new Date(duration));
        return time;
    }

    @Override
    public void onResume() {
        super.onResume();
        timeElapsed = baseViewModel.getTimeElapsed();
        startTime.setText(formatTime(timeElapsed));
        time.setProgress(timeElapsed);
    }

    @Override
    public void onPause() {
        super.onPause();
        isPlaying=false;
        playPause.setImageDrawable(requireContext().getDrawable(R.drawable.ic_play));
        if (mediaPlayer!=null && mediaPlayer.isPlaying())
            mediaPlayer.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isPlaying=false;
        playPause.setImageDrawable(requireContext().getDrawable(R.drawable.ic_play));
        if(mediaPlayer!=null)
            mediaPlayer.release();
    }
}
