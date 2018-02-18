package com.example.elja.valvonta_app;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.util.Log;


import org.jcodec.common.io.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class SettingsActivity extends AsyncTask<String, Void, Void> {
    private byte[] imgBytes;
    private InputStream inputStream;
    @Override
    protected Void doInBackground(String... params){
        Log.e("asdf", "Hei0");
        while (true) {
            try {
                Socket socket = new Socket("176.72.180.64", 8080);
                PrintWriter outToServer = new PrintWriter(
                        new OutputStreamWriter(
                                socket.getOutputStream()
                        )
                );
                outToServer.write("send_video");
                outToServer.flush();

                Log.e("asdf", "Hei1");
                inputStream = socket.getInputStream();
            /*byte[] buf = new byte[50264];
            inputStream.read(buf);*/
                //byte[] bytes = IOUtils.toByteArray(inputStream);
                Bitmap bitmap = null;
                Log.e("asdf", "InputStream");

                bitmap = BitmapFactory.decodeStream(inputStream);

                Log.e("asdf", "connect");
                outToServer.close();
                socket.close();
                if (bitmap != null) {
                    Log.e("asdf", "Hei5");
                }
                MainActivity.bitmapArrayList.add(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
        return null;
    }
}

