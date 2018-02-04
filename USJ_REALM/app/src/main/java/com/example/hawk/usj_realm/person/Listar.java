package com.example.hawk.usj_realm.person;

/**
 * Created by hawk on 1/26/18.
 */


import android.os.Bundle;


import android.widget.TextView;


import com.example.hawk.usj_realm.Connect;
import com.example.hawk.usj_realm.R;

import java.util.ListIterator;

import io.realm.RealmResults;

public class Listar extends Connect {

    private TextView texto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
        texto = (TextView) findViewById(R.id.texto);

        RealmResults<Person> results = realm.where(Person.class).findAll();
        ListIterator li=results.listIterator();
        String status="";
        Person per=null;
        while(li.hasNext()) {
            per=(Person)li.next();
            status="Id:"+per.getId()+";"+"Nombre:"+per.getName()+"; "+ "Edad:"+per.getAge()+"\n";
            showStatus(status);
            //status = li."\nSize of result set: " + results.toString();
        }

    }
    private void showStatus(String txt) {

        texto.setText( texto.getText()+txt);

    }
}
