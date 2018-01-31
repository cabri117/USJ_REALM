package com.example.hawk.usj_realm.realm.pet;

/**
 * Created by hawk on 1/26/18.
 */


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.hawk.usj_realm.R;
import com.example.hawk.usj_realm.realm.Connect;
import com.example.hawk.usj_realm.realm.person.Person;

import io.realm.Realm;

//import com.example.david.realm301.R;

public class Add extends Connect {

    private EditText etxt_person;
    private EditText etxt_name;
    private EditText etxt_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_pet);

        //Intent i = getIntent();
        //realm = (Realm)i.getSerializableExtra("connection");
        etxt_name = findViewById(R.id.etxt_name);
        etxt_type = findViewById(R.id.etxt_type);
        etxt_person = findViewById(R.id.etxt_person);

    }

    public void nuevo(View view) {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // Add a person
                Person person = realm.where(Person.class).equalTo("name", etxt_person.getText().toString()).findFirst();
                if(person != null) {
                    Pet pet = realm.createObject(Pet.class, (int) (Math.random() * 1000) + 1);
                    pet.setOwner(person);
                    pet.setName(etxt_name.getText().toString());
                    pet.setType(etxt_type.getText().toString());
                    person.getPets().add(pet);
                }
            }
        });
    }

    public void limpiar(View view) {
        etxt_name.setText("");
        etxt_type.setText("");
        etxt_person.setText("");
    }
}