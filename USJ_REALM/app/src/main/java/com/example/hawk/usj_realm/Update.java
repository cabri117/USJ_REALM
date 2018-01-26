package com.example.hawk.usj_realm;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ListIterator;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by mikearias on 1/26/18.
 */

public class Update extends Connect {

    private EditText etxt_id;
    private EditText etxt_name;
    private EditText etxt_age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update);

        //Intent i = getIntent();
        //realm = (Realm)i.getSerializableExtra("connection");
        etxt_id = findViewById(R.id.etxt_id);
        etxt_name = findViewById(R.id.etxt_name);
        etxt_age = findViewById(R.id.etxt_age);

    }

    public void actualizar(View view) {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // Update a person

                RealmResults<Person> results = realm.where(Person.class).findAll();
                ListIterator li=results.listIterator();

                Person per=null;
                Long lg = null;
                while(li.hasNext()) {
                    per=(Person)li.next();

                    if (per.getId() == lg.valueOf(etxt_id.getText().toString())) {
                        // Found the Persons
                        per.setName(etxt_name.getText().toString());
                        per.setAge(etxt_age.getText().toString());
                    }
//                    status="Id:"+per.getId()+";"+"Nombre:"+per.getName()+"; "+ "Edad:"+per.getAge()+"\n";
//                    showStatus(status);
                    //status = li."\nSize of result set: " + results.toString();
                }

            }
        });
    }

    public void limpiar(View view) {
        etxt_name.setText("");
        etxt_age.setText("");

    }
}