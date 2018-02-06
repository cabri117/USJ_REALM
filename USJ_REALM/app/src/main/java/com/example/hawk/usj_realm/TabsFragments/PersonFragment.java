package com.example.hawk.usj_realm.TabsFragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.hawk.usj_realm.Adapters.PersonListAdapter;
import com.example.hawk.usj_realm.AddPersonActivity;
import com.example.hawk.usj_realm.Connect;
import com.example.hawk.usj_realm.Helpers.ObjectWrapperForBinder;
import com.example.hawk.usj_realm.MainActivity;
import com.example.hawk.usj_realm.Models.Person;
import com.example.hawk.usj_realm.PersonListActivity;
import com.example.hawk.usj_realm.R;

import java.util.ArrayList;

import io.realm.Realm;

public class PersonFragment extends Fragment {

    ListView listView;
    ArrayList<Person> list;
    private Realm realm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_person, container, false);

        // Create the Realm instance
        Realm.init(view.getContext());
        realm = Realm.getDefaultInstance();

        listView = (ListView) view.findViewById(R.id.petList);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
                Intent intent = new Intent(view.getContext(), AddPersonActivity.class);
                final Bundle bundle = new Bundle();
                bundle.putBinder("person", new ObjectWrapperForBinder(list.get(position)));
                startActivity(new Intent(view.getContext(), AddPersonActivity.class).putExtras(bundle));
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        list = new ArrayList(realm.where(Person.class).findAll());
        ListAdapter customAdapter = new PersonListAdapter(this.getContext(), list);
        listView.setAdapter(customAdapter);
    }

}
