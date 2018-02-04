package com.example.hawk.usj_realm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.hawk.usj_realm.person.Add;
import com.example.hawk.usj_realm.person.Delete;
import com.example.hawk.usj_realm.person.Listar;
import com.example.hawk.usj_realm.person.Update;
import com.example.hawk.usj_realm.pet.AddPet_;
import com.example.hawk.usj_realm.pet.DeletePet_;
import com.example.hawk.usj_realm.pet.ListarPet;
import com.example.hawk.usj_realm.pet.UpdatePet_;

import io.realm.Realm;

public class MainActivity extends Connect {

    public static final String TAG = MainActivity.class.getName();
    private LinearLayout rootLayout = null;

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Realm.init(this);
        realm = Realm.getDefaultInstance();
    }

    public void add(View view) {
        Intent intent = new Intent(this, Add.class);
        startActivity(intent);
    }

    public void listar(View view) {
        Intent intent = new Intent(this, Listar.class);
        startActivity(intent);
    }

    public void update(View view) {
        Intent intent = new Intent(this, Update.class);
        startActivity(intent);
    }

    public void delete(View view) {
        Intent intent = new Intent(this, Delete.class);
        startActivity(intent);
    }

    public void addPet(View view) {
        Intent intent = new Intent(this, AddPet_.class);
        startActivity(intent);
    }

    public void listarPet(View view) {
        Intent intent = new Intent(this, ListarPet.class);
        startActivity(intent);
    }

    public void updatePet(View view) {
        Intent intent = new Intent(this, UpdatePet_.class);
        startActivity(intent);
    }

    public void deletePet(View view) {
        Intent intent = new Intent(this, DeletePet_.class);
        startActivity(intent);
    }
}
