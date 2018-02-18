package com.example.elja.valvonta_app;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class TallenteetActivity extends AppCompatActivity {
    Button play_button;
    VideoView videov;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tallenteet);
        play_button = findViewById(R.id.playButton);
        videov = findViewById(R.id.videoScreen);


    }
    public void videoplay(View v){
        String videopath = "android.resource://com.example.elja.valvonta_app/"+R.raw.nani;
        Uri uri = Uri.parse(videopath);

        videov.setVideoURI(uri);
        videov.start();
    }
}
