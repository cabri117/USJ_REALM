package com.example.hawk.usj_realm;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class PetInfoActivity extends AppCompatActivity {

    ListView listView;
    final ArrayList<String> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_info);

        listView = (ListView) findViewById(R.id.list);;
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL githubEndpoint = new URL("https://api.myjson.com/bins/bwhix");

                    HttpURLConnection myConnection = (HttpURLConnection) githubEndpoint.openConnection();
                    // myConnection.setRequestProperty("User-agent", "my-rest-app-v0.1");
                    // myConnection.setRequestProperty("Accept", "application/vnd.github.v3+json");
                    // myConnection.setRequestProperty("Contact-Me", "char0394@gmail.com");

                    if(myConnection.getResponseCode()== 200){
                        InputStream responseBody = myConnection.getInputStream();
                        InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");
                        JsonReader jsonReader = new JsonReader(responseBodyReader);


                        jsonReader.beginArray();
                        while (jsonReader.hasNext()){

                            jsonReader.beginObject();
                            while (jsonReader.hasNext()) {
                                String keu = jsonReader.nextName();
                                if(keu.equals("Tip")){
                                    items.add(jsonReader.nextString());
                                }

                            }
                            jsonReader.endObject();

                        }

                        jsonReader.endArray();

                        runOnUiThread(new Runnable(){
                            public void run() {
                                ArrayAdapter<String> itemsAdapter =
                                        new ArrayAdapter<String>(PetInfoActivity.this, android.R.layout.simple_list_item_1, items);
                                listView.setAdapter(itemsAdapter);
                            }
                        });



                        myConnection.disconnect();
                    }else{

                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
