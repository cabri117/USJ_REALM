package com.example.hawk.usj_realm;

/**
 * Created by hawk on 1/26/18.
 */



import android.os.Bundle;


import android.util.Log;
import android.view.View;
import android.widget.EditText;


import java.util.ArrayList;

import io.realm.Realm;

//import com.example.david.realm301.R;

public class Add extends Connect {

    private EditText etxt_name;
    private EditText etxt_age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);

        //Intent i = getIntent();
        //realm = (Realm)i.getSerializableExtra("connection");
        etxt_name = findViewById(R.id.etxt_name);
        etxt_age = findViewById(R.id.etxt_age);

    }

    Integer getPK() {
        return (int) (Math.random() * 1000) + 1;
    }

    public void nuevo(View view) {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // Add a person
//                int pk = (int) (Math.random() * 1000) + 1;
                Person person = realm.createObject(Person.class, getPK());
                person.setName(etxt_name.getText().toString());
                person.setAge(etxt_age.getText().toString());
//                Mascota pet1 = realm.createObject(Mascota.class, getPK());
//                pet1.setName("Prueba");
//                pet1.setAge("5");
//                pet1.setType("Perro");
//                Mascota pet2 = realm.createObject(Mascota.class, getPK());
//                pet2.setName("Prueba");
//                pet2.setAge("3");
//                pet2.setType("Gato");
//                person.getMascotas().add(pet1);
//                person.getMascotas().add(pet2);
//
//
//                Log.d("Cantidad de Mascotas: ", Integer.toString(person.getMascotas().size()));
//
//                Log.d("Mascota: ", "");

            }
        });
    }

    public void limpiar(View view) {
        etxt_name.setText("");
        etxt_age.setText("");

    }
}