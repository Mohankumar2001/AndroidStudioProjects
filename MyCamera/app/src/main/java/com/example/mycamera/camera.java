package com.example.mycamera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class camera extends AppCompatActivity {
    private static final int Image_Capture_Code = 1;
    Button retry ,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent,Image_Capture_Code);
        retry= findViewById(R.id.retry);
        back= findViewById(R.id.back);
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent,Image_Capture_Code);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(camera.this, MainActivity.class);
                startActivity(back);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Image_Capture_Code && resultCode == RESULT_OK && data != null) {
                Bitmap image;
                image = (Bitmap)data.getExtras().get("data");
                Intent intent = new Intent(camera.this, gallery.class);
                intent.putExtra("image", image);
                setResult(RESULT_OK, intent);
                startActivity(intent);
        }
        }

        /*
        if (requestCode == Image_Capture_Code) {
            if (resultCode == RESULT_OK) {
                byte[] image ;
                image = data.getByteArrayExtra("data");
                Intent intent = new Intent(camera.this, gallery.class);
                intent.putExtra("image", image);
                setResult(RESULT_OK, intent);
                startActivity(intent);
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            }
        }
*/

    }

/*Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
ByteArrayOutputStream stream = new ByteArrayOutputStream();
bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
byte[] byteArray = stream.toByteArray();
 */


/*super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            Uri uri = data.getData();
            try {
                img_view.setVisibility(View.VISIBLE);
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byteArr = stream.toByteArray();

                Path_Image_model path_image_model = new Path_Image_model();
                path_image_model.setPath(byteArr.toString());

                Log.d(TAG, "===byteArr_PickImage==" + byteArr);
                img_view.setImageBitmap(bitmap);
                  global_bitmap=bitmap;


                resizedFile = new File(uri.getPath());
                Log.d(TAG, "===FileImage==" + resizedFile);


            } catch (IOException e) {
                e.printStackTrace();
            }

 */