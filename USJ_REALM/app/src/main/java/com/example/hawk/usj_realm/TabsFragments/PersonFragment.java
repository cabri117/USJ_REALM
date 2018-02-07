package com.example.hawk.usj_realm.TabsFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.hawk.usj_realm.Adapters.RecyclerViewPersonAdapter;
import com.example.hawk.usj_realm.AddPersonActivity;
import com.example.hawk.usj_realm.Helpers.ObjectWrapperForBinder;
import com.example.hawk.usj_realm.Models.Person;
import com.example.hawk.usj_realm.R;

import java.util.ArrayList;

import io.realm.Realm;

public class PersonFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "CardViewActivity";

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

        list = new ArrayList(realm.where(Person.class).findAll());

        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_viewPerson);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecyclerViewPersonAdapter(list);
        mRecyclerView.setAdapter(mAdapter);

        // Code to Add an item with default animation
        //((MyRecyclerViewAdapter) mAdapter).addItem(obj, index);

        // Code to remove an item with default animation
        //((MyRecyclerViewAdapter) mAdapter).deleteItem(index);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((RecyclerViewPersonAdapter) mAdapter).setOnItemClickListener(new RecyclerViewPersonAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent intent = new Intent(v.getContext(), AddPersonActivity.class);
                final Bundle bundle = new Bundle();
                bundle.putBinder("person", new ObjectWrapperForBinder(list.get(position)));
                startActivity(new Intent(v.getContext(), AddPersonActivity.class).putExtras(bundle));
                Log.i(LOG_TAG, " Clicked on Item " + position);
            }
        });

        list = new ArrayList(realm.where(Person.class).findAll());
        mAdapter = new RecyclerViewPersonAdapter(list);
        mRecyclerView.setAdapter(mAdapter);
    }

}
