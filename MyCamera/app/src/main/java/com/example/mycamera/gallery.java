package com.example.mycamera;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class gallery extends AppCompatActivity {
    Button save,back;
    ImageView img;
    String path;
    byte[] image ;
    private static final int MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE =1002;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        save = findViewById(R.id.save);
        back = findViewById(R.id.back1);
        img = findViewById(R.id.img);

        Intent intent = getIntent();
        Bitmap bp = (Bitmap) intent.getParcelableExtra("image");

        img.setImageBitmap(bp);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(gallery.this, MainActivity.class);
                startActivity(back);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int permissionwrite = ContextCompat.checkSelfPermission(gallery.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                Toast.makeText(gallery.this,"clicked",Toast.LENGTH_SHORT);
                if (permissionwrite != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(gallery.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE);
                    Toast.makeText(gallery.this, "Storage Permission is required for this app to run", Toast.LENGTH_SHORT)
                            .show();
                }
                path = saveToInternalStorage(bp);
                Toast.makeText(gallery.this,"saved to internal storage -" + path,Toast.LENGTH_LONG).show();
            }
        });

    }
    private String saveToInternalStorage(Bitmap bitmapImage){
        String root = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES).toString();
        File myDir = new File(root,"photos");
        if (!myDir.exists())
            myDir.mkdirs();

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fname = "Mycam_"+ timeStamp +".jpg";

        File file = new File(myDir, fname);
        if (file.exists()) file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
        return (myDir + fname).toString();
    }
}