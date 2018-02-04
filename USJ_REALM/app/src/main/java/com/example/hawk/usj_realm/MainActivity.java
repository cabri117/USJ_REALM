package com.example.hawk.usj_realm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import io.realm.Realm;

public class MainActivity extends Connect {

    public static final String TAG = MainActivity.class.getName();
    private LinearLayout rootLayout = null;

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the Realm instance
        Realm.init(this);
        realm = Realm.getDefaultInstance();

    }

    public void add(View view) {
        Intent intent = new Intent(this, AddPersonActivity.class);
        // intent.putExtra("connection",(Serializable) realm);
        startActivity(intent);
    }

    public void listar(View view) {
        Intent intent = new Intent(this, PersonListActivity.class);
        // intent.putExtra("connection",(Serializable) realm);
        startActivity(intent);
    }

    public void getPetInfo(View view) {
        Intent intent = new Intent(this, PetInfoActivity.class);
        // intent.putExtra("connection",(Serializable) realm);
        startActivity(intent);
    }
}
