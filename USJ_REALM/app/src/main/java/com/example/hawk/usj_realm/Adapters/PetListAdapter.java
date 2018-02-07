package com.example.hawk.usj_realm.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.hawk.usj_realm.R;

import java.util.ArrayList;

/**
 * Created by usuario on 7/2/18.
 */

public class PetListAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final ArrayList<String> values;

    public PetListAdapter(Context context, ArrayList<String> values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.personpet_cell, parent, false);
        TextView tip = (TextView) rowView.findViewById(R.id.tip);
        tip.setText(values.get(position));
        return rowView;
    }
}