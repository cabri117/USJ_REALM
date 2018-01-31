package com.example.hawk.usj_realm.realm.pet;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hawk.usj_realm.R;
import com.example.hawk.usj_realm.realm.Connect;
import com.example.hawk.usj_realm.realm.person.Person;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Chard on 1/26/18.
 */

public class Delete  extends Connect {

    private EditText etxt_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete);

        //Intent i = getIntent();
        //realm = (Realm)i.getSerializableExtra("connection");
        etxt_name = findViewById(R.id.etxt_name);

    }

    public void nuevo(View view) {

        try{


            realm.executeTransaction(new Realm.Transaction() {

                @Override
                public void execute(Realm realm) {
                    // Delete a pet


                    RealmResults<Pet> rows= realm.where(Pet.class).equalTo("name", etxt_name.getText().toString()).findAll();;

                    if(rows.isEmpty()){
                        Delete.this.runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                Toast.makeText(Delete.this, "No se encontro mascota",
                                        Toast.LENGTH_LONG).show();
                            }
                        });

                    }else{
                        rows.deleteAllFromRealm();
                        etxt_name.setText("");
                    }

                }
            });

            Toast.makeText(this, "Mascota borrada",
                    Toast.LENGTH_LONG).show();

        }catch (Exception e){
            Toast.makeText(this, "No se pudo borrar mascota",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void limpiar(View view) {
        etxt_name.setText("");

    }
}