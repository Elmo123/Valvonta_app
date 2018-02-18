package com.example.elja.valvonta_app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import org.jcodec.api.SequenceEncoder;
import org.jcodec.api.android.AndroidSequenceEncoder;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.io.SeekableByteChannel;
import org.jcodec.common.model.Rational;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSettings;
    private Button btnRecodings;
    private Button btnShowImage;
    private Button btnMakeVideo;
    //public static Bitmap image = null;
    public static ArrayList<Bitmap> bitmapArrayList = new ArrayList<Bitmap>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSettings = findViewById(R.id.settings_btn);
        btnRecodings = findViewById(R.id.recordings_btn);
        btnShowImage = findViewById(R.id.imageView_btn);
        btnMakeVideo = findViewById(R.id.makeVideo_btn);

        btnSettings.setOnClickListener(this);
        btnRecodings.setOnClickListener(this);
        btnShowImage.setOnClickListener(this);
        btnMakeVideo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        int id = v.getId();
        switch (id){
            case R.id.settings_btn:
                new SettingsActivity().execute("send_video");
                break;
            case R.id.recordings_btn:
                intent = new Intent(MainActivity.this, TallenteetActivity.class);
                startActivity(intent);
                break;
            case R.id.imageView_btn:
                intent = new Intent(MainActivity.this, showImageActivity.class);

                Log.e("asdf", "BitmapinArray size: " + bitmapArrayList.size());
                /*if (image != null) {
                    Log.e("asdf", "not null");
                    ByteArrayOutputStream bs = new ByteArrayOutputStream();
                    bitmapArrayList.get(i).compress(Bitmap.CompressFormat.JPEG, 100, bs);
                    intent.putExtra("byteArray", bs.toByteArray());
                    i++;
                }*/
                startActivity(intent);
                break;
            case R.id.makeVideo_btn:
                makeVideo();
                break;
        }
    }

    public void makeVideo (){
        SeekableByteChannel out = null;
        try {
            Log.e("asdf", "Tas");
            out = NIOUtils.writableFileChannel("/tmp/output.mp4");
            Log.e("asdf", "Tas2");
            // for Android use: AndroidSequenceEncoder
            AndroidSequenceEncoder encoder = new AndroidSequenceEncoder(out, Rational.R(25, 1));
            Log.e("asdf", "Tas3");
            for (int i = 0; i < bitmapArrayList.size(); i++) {
                // Generate the image, for Android use Bitmap
                Bitmap image = bitmapArrayList.get(0);
                // Encode the image
                encoder.encodeImage(image);
            }
            // Finalize the encoding, i.e. clear the buffers, write the header, etc.
            encoder.finish();
            Toast.makeText(MainActivity.this, "Success",
                    Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            NIOUtils.closeQuietly(out);
        }

    }
}
