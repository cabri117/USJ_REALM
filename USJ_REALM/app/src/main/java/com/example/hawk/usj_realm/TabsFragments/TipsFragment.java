package com.example.hawk.usj_realm.TabsFragments;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.JsonReader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.hawk.usj_realm.MainActivity;
import com.example.hawk.usj_realm.PetInfoActivity;
import com.example.hawk.usj_realm.R;
import com.example.hawk.usj_realm.SplashActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class TipsFragment extends Fragment {

    ListView listView;
    ArrayList<String> items = new ArrayList<String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tips, container, false);

        Bundle bundle = getArguments();
        items = bundle.getStringArrayList("TIPSDATA");

        listView = (ListView) view.findViewById(R.id.list);;
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, items);
        listView.setAdapter(itemsAdapter);
        return view;
    }

}
