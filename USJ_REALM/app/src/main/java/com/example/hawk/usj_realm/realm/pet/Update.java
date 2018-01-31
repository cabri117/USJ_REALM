package com.example.hawk.usj_realm.realm.pet;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.hawk.usj_realm.R;
import com.example.hawk.usj_realm.realm.Connect;

import java.util.ListIterator;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by mikearias on 1/26/18.
 */

public class Update extends Connect {

    private EditText etxt_name;
    private EditText etxt_name_new;
    private EditText etxt_owner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_pet);

        //Intent i = getIntent();
        //realm = (Realm)i.getSerializableExtra("connection");
        etxt_name = findViewById(R.id.etxt_name);
        etxt_name_new = findViewById(R.id.etxt_name_new);
        etxt_owner = findViewById(R.id.etxt_owner);

    }

    public void actualizar(View view) {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // Update a person
                RealmResults<Pet> results = realm.where(Pet.class).findAll();
                ListIterator li=results.listIterator();
                Pet pet = null;
                while(li.hasNext()) {
                    pet = (Pet) li.next();
                    if (pet.getOwner().getName().equalsIgnoreCase(etxt_owner.getText().toString()) &&
                            pet.getName().equalsIgnoreCase(etxt_name.getText().toString())) {
                        // Found the Pet
                        pet.setName(etxt_name_new.getText().toString());

                    }
                }

            }
        });
    }

    public void limpiar(View view) {
        etxt_name.setText("");
        etxt_name_new.setText("");
        etxt_owner.setText("");

    }
}