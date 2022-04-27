package com.example.myphone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText phone;
    Button call,contact;
    private static final int Call_req = 10001;
    private static final int read = 10002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        phone= findViewById(R.id.input);
        call = findViewById(R.id.call);
        contact =findViewById(R.id.contact);
        int permissionCall = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        int permissionread = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);
        if (permissionCall != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.CALL_PHONE}, Call_req);
            Toast.makeText(MainActivity.this, "call Permission is required for this app to run", Toast.LENGTH_SHORT)
                    .show();
        }
        requestPermissions(new String[]{
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.ACCESS_FINE_LOCATION},
                read);
        if (permissionread != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.READ_CONTACTS}, read);
            Toast.makeText(MainActivity.this, "Read contacts  is required for this app to run", Toast.LENGTH_SHORT)
                    .show();
        }


        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ph = "+91" + phone.getText().toString();
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+ ph));
                startActivity(callIntent);
            }
        });
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Mycontacts.class));
            }
        });
    }
}