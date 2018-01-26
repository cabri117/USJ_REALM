package com.example.hawk.usj_realm;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import io.realm.Realm;

/**
 * Created by mikearias on 1/26/18.
 */

public class Update extends Connect {

    private EditText etxt_name;
    private EditText etxt_age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update);

        //Intent i = getIntent();
        //realm = (Realm)i.getSerializableExtra("connection");
        etxt_name = findViewById(R.id.etxt_name);
        etxt_age = findViewById(R.id.etxt_age);

    }

    public void actualizar(View view) {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // Update a person

            }
        });
    }

    public void limpiar(View view) {
        etxt_name.setText("");
        etxt_age.setText("");

    }
}