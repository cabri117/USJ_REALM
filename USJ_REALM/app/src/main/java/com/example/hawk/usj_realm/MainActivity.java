package com.example.hawk.usj_realm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.hawk.usj_realm.realm.Connect;
import com.example.hawk.usj_realm.realm.person.Add;
import com.example.hawk.usj_realm.realm.person.Delete;
import com.example.hawk.usj_realm.realm.person.Listar;
import com.example.hawk.usj_realm.realm.person.Update;

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
        Intent intent = new Intent(this, Add.class);
        // intent.putExtra("connection",(Serializable) realm);
        startActivity(intent);
    }

    public void listar(View view) {
        Intent intent = new Intent(this, Listar.class);
        // intent.putExtra("connection",(Serializable) realm);
        startActivity(intent);
    }

    public void update(View view) {
        Intent intent = new Intent(this, Update.class);
        startActivity(intent);
    }

    public void delete(View view) {
        Intent intent = new Intent(this, Delete.class);
        // intent.putExtra("connection",(Serializable) realm);
        startActivity(intent);
    }

    public void addPet(View view) {
        Intent intent = new Intent(this, com.example.hawk.usj_realm.realm.pet.Add.class);
        // intent.putExtra("connection",(Serializable) realm);
        startActivity(intent);
    }

    public void listarPet(View view) {
        Intent intent = new Intent(this, com.example.hawk.usj_realm.realm.pet.Listar.class);
        // intent.putExtra("connection",(Serializable) realm);
        startActivity(intent);
    }

    public void updatePet(View view) {
        Intent intent = new Intent(this, com.example.hawk.usj_realm.realm.pet.Update.class);
        startActivity(intent);
    }

    public void deletePet(View view) {
        Intent intent = new Intent(this, com.example.hawk.usj_realm.realm.pet.Delete.class);
        // intent.putExtra("connection",(Serializable) realm);
        startActivity(intent);
    }


}
