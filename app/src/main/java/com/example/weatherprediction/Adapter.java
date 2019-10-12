package com.example.weatherprediction;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends ArrayAdapter<Model> {
    public Adapter(Context context, ArrayList<Model> Models) {
        super(context, 0, Models);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Model Model = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView Name = (TextView) convertView.findViewById(R.id.city);
        Name.setText(Model.city);

        return convertView;
    }
}
