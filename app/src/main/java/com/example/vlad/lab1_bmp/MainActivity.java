package com.example.vlad.lab1_bmp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    Button button2;
    Button button1;
    Button button3;
    ImageView imageView;
    Bitmap bitmap;
    Bitmap mutableBitmap;
    String folderToSave = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
    OutputStream fOut = null;
    TextView w;
    TextView h;
    TextView s;
    TextView d;



    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         button2 = (Button) findViewById(R.id.button2);
         button1 = (Button) findViewById(R.id.button);
         button3 = (Button) findViewById(R.id.button3);
         imageView = (ImageView) findViewById(R.id.imageView2);



        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pic);

                mutableBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
              //  imageView.setImageBitmap(bitmap);
                imageView.setImageResource(R.drawable.pic);

                String height = String.valueOf(imageView.getHeight());
                String width = String.valueOf(imageView.getWidth());

                w = (TextView) findViewById(R.id.width);
                w.setText("width= "+ width);
                h = (TextView) findViewById(R.id.height);
                h.setText("height= "+ height);
                s = (TextView) findViewById(R.id.size);
                s.setText("size= "+getString(R.string.size));
                d = (TextView) findViewById(R.id.depth);
                d.setText("depth= " + String.valueOf(Integer.valueOf(getString(R.string.size))*8
                        /(Integer.valueOf(width)*Integer.valueOf(height))));





            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i < 20; i++) {
                    for(int j =0; j < 20; j++){
                        mutableBitmap.setPixel(50+i,50+j,Color.RED);
                    }
                }

                imageView.setImageBitmap(mutableBitmap);




            }





        });




        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {

                    File file = new File(folderToSave, "photo.jpeg");
                    fOut = new FileOutputStream(file);
                    mutableBitmap.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
                    fOut.flush();
                    fOut.close();


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Toast toast = Toast.makeText(MainActivity.this,folderToSave,Toast.LENGTH_LONG);
                toast.show();
            }
        });




    }
}
