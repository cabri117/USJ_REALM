package com.example.hawk.usj_realm.realm.pet;

/**
 * Created by hawk on 1/26/18.
 */


import android.os.Bundle;
import android.widget.TextView;

import com.example.hawk.usj_realm.R;
import com.example.hawk.usj_realm.realm.Connect;
import com.example.hawk.usj_realm.realm.person.Person;

import java.util.ListIterator;

import io.realm.RealmResults;

//import com.example.david.realm301.R;

public class Listar extends Connect {

    private TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
        texto = (TextView) findViewById(R.id.texto);
        RealmResults<Pet> results = realm.where(Pet.class).findAll();
        ListIterator li=results.listIterator();
        String status = "";
        Pet pet = null;
        while(li.hasNext()) {
            pet = (Pet) li.next();
            status="Id:"+pet.getId()+";"+"Nombre:"+pet.getName()+"; "+ "Propietario:"+pet.getOwner().getName()+"\n";
            showStatus(status);
        }
    }

    private void showStatus(String txt) {
        texto.setText(texto.getText() + txt);
    }
}
