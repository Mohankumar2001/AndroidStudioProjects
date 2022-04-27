package com.example.myphone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class adapeter extends ArrayAdapter<Contact> {
    public adapeter(Context context, ArrayList<Contact> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Contact user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.c_item, parent, false);
        }
        TextView cnaname = (TextView) convertView.findViewById(R.id.contactname);
        cnaname.setText(user.name);

        return convertView;
    }


}
