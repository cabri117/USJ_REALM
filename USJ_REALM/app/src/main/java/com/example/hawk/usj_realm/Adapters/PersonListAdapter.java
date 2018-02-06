package com.example.hawk.usj_realm.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.hawk.usj_realm.Models.Person;
import com.example.hawk.usj_realm.Models.Pet;
import com.example.hawk.usj_realm.R;

import java.util.ArrayList;

import io.realm.RealmList;

/**
 * Created by Chard on 2/4/18.
 */

public class PersonListAdapter extends ArrayAdapter<Person> {
    private final Context context;
    private final ArrayList<Person> values;

    public PersonListAdapter(Context context, ArrayList<Person> values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.personpet_cell, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.textView3);
        textView.setText(values.get(position).getName());

        TextView petView = (TextView) rowView.findViewById(R.id.textView4);
        String petsName= "Mascotas:";
        RealmList<Pet> pets= values.get(position).getPets();
        for(int i=0; i<pets.size(); i++){
                petsName= petsName + " - " + pets.get(i).getName();

        }

        petView.setText(petsName);

        TextView age = (TextView) rowView.findViewById(R.id.textView5);
        int ageValue= values.get(position).getAge();
        age.setText("Edad:"+ageValue);

        return rowView;
    }
}
