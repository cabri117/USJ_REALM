package com.example.hawk.usj_realm;

/**
 * Created by hawk on 1/26/18.
 */



import android.os.Bundle;


import android.view.View;
import android.widget.EditText;


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

    public void nuevo(View view) {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // Add a person
                Person person = realm.createObject(Person.class);
                person.setId((int)(Math.random()*1000)+1);
                person.setName(etxt_name.getText().toString());
                person.setAge(etxt_age.getText().toString());

            }
        });
    }

    public void limpiar(View view) {
        etxt_name.setText("");
        etxt_age.setText("");

    }
}