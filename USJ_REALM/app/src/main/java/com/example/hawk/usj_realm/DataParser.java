package com.example.hawk.usj_realm;

import android.os.AsyncTask;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by mikearias on 2/5/18.
 */

public class DataParser {
    public static ArrayList<String> places = new ArrayList<>();

    public static void getJson() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                String sURL = "https://api.myjson.com/bins/urb0x"; //just a string
                // Connect to the URL using java's native library
                URL url = null;
                try {
                    url = new URL(sURL);
                    HttpURLConnection request = (HttpURLConnection) url.openConnection();
                    request.connect();
                    // Convert to a JSON object to print data
                    JsonParser jp = new JsonParser(); //from gson
                    JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
                    JsonObject rootobj = root.getAsJsonObject(); //May be an array, may be an object.
                    JsonArray rootarray = rootobj.get("lugares").getAsJsonArray();
                    int i = 0;
                    while (i < rootarray.size()) {
                        places.add(rootarray.get(i).getAsJsonObject().get("name").toString());
                        i++;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
