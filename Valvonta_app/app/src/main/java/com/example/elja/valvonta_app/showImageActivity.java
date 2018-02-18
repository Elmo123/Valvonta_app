package com.example.elja.valvonta_app;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class showImageActivity extends AppCompatActivity implements View.OnClickListener{
    private Bitmap image = null;
    private ImageView imgv;
    private int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);
        Button change_image;
        imgv = findViewById(R.id.image);
        change_image = findViewById(R.id.change_img);
        change_image.setOnClickListener(this);

/*
        if(getIntent().hasExtra("byteArray")) {
            image = BitmapFactory.decodeByteArray(
                    getIntent().getByteArrayExtra("byteArray"), 0, getIntent().getByteArrayExtra("byteArray").length);
        }
        if (image != null) {
            imgv.setImageBitmap(image);
        }*/
    }
    @Override
    public void onClick(View v){
        int id = v.getId();
        switch (id) {
            case R.id.change_img:
                if(i >= MainActivity.bitmapArrayList.size()){
                    if(MainActivity.bitmapArrayList.isEmpty()) break;
                    else {
                        i = 0;
                    }
                }
                imgv.setImageBitmap(MainActivity.bitmapArrayList.get(i));
                i++;

                break;
        }
    }
}



