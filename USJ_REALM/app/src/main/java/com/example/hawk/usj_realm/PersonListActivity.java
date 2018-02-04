package com.example.hawk.usj_realm;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toolbar;

import com.example.hawk.usj_realm.Adapters.PersonListAdapter;
import com.example.hawk.usj_realm.Helpers.ObjectWrapperForBinder;
import com.example.hawk.usj_realm.Models.Person;

import java.util.ArrayList;

public class PersonListActivity extends Connect {

    ListView listView;
    ArrayList<Person> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = (ListView) findViewById(R.id.petList);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
                Intent intent = new Intent(PersonListActivity.this, AddPersonActivity.class);
                final Bundle bundle = new Bundle();
                bundle.putBinder("person", new ObjectWrapperForBinder(list.get(position)));

                startActivity(new Intent(PersonListActivity.this, AddPersonActivity.class).putExtras(bundle));

            }
        });

        setTitle("Personas");
    }

    @Override
    protected void onResume() {
        super.onResume();
        list = new ArrayList(realm.where(Person.class).findAll());
        ListAdapter customAdapter = new PersonListAdapter(this, list);
        listView.setAdapter(customAdapter);
    }
}
