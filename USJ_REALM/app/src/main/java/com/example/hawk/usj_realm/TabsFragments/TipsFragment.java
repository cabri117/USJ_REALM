package com.example.hawk.usj_realm.TabsFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.hawk.usj_realm.Adapters.PetListAdapter;
import com.example.hawk.usj_realm.R;
import java.util.ArrayList;

public class TipsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "CardViewActivity";

    ListView listView;
    ArrayList<String> items = new ArrayList<String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tips, container, false);

        Bundle bundle = getArguments();
        items = bundle.getStringArrayList("TIPSDATA");

        listView = (ListView) view.findViewById(R.id.list);;
        ArrayAdapter<String> itemsAdapter = new PetListAdapter(view.getContext(), items);
        listView.setAdapter(itemsAdapter);
        return view;
    }

}
