package com.example.myphone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CallContact extends AppCompatActivity {
    Button call;
    TextView contactname , number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_contact);
        call = findViewById(R.id.call1);
        contactname = findViewById(R.id.cntname);
        number = findViewById(R.id.phnno);
        Contact mycontact = (Contact) getIntent().getSerializableExtra("contact");
        contactname.setText(mycontact.name);
        number.setText(mycontact.phoneNumber);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+ mycontact.phoneNumber));
                startActivity(callIntent);
            }
        });

    }
}