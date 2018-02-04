package com.example.hawk.usj_realm.person;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hawk.usj_realm.Connect;
import com.example.hawk.usj_realm.R;

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
        etxt_name = findViewById(R.id.etxt_name);
    }

    public void delete(View view) {
        try{
            realm.executeTransaction(new Realm.Transaction() {

                @Override
                public void execute(Realm realm) {
                    RealmResults<Person> rows = realm.where(Person.class).equalTo("name", etxt_name.getText().toString()).findAll();;
                    if(rows.isEmpty()){
                        Delete.this.runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                Toast.makeText(Delete.this, "No se encontro usuario",
                                        Toast.LENGTH_LONG).show();
                            }
                        });
                    }else{
                        rows.deleteAllFromRealm();
                        etxt_name.setText("");
                    }
                }
            });
            Toast.makeText(this, "Usuario borrado",
                    Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Toast.makeText(this, "No se pudo borrar usuario",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void limpiar(View view) {
        etxt_name.setText("");

    }
}