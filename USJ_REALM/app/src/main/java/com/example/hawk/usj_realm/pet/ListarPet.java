package com.example.hawk.usj_realm.pet;

import android.os.Bundle;
import android.widget.TextView;

import com.example.hawk.usj_realm.Connect;
import com.example.hawk.usj_realm.R;

import java.util.ListIterator;

import io.realm.RealmResults;

public class ListarPet extends Connect {

    private TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
        texto = (TextView) findViewById(R.id.texto);
        texto.setText("");
        RealmResults<Pet> results =  realm.where(Pet.class).findAll();
        ListIterator li=results.listIterator();
        String status;
        Pet pet;
        while(li.hasNext()) {
            pet = (Pet) li.next();
            status = "Id: " + pet.getId() + " Name: " + pet.getName() + " Owner: " + pet.getOwner().getName() + "\n";
            showStatus(status);
        }
    }

    private void showStatus(String txt) {
        texto.setText(texto.getText() + txt);
    }
}
