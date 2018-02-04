package com.example.hawk.usj_realm.pet;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hawk.usj_realm.Connect;
import com.example.hawk.usj_realm.R;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import io.realm.Realm;
import io.realm.RealmResults;

@EActivity(R.layout.delete)
public class DeletePet extends Connect {

    @ViewById
    EditText etxt_name;

    @Click(R.id.button_delete)
    public void delete() {
        try{
            realm.executeTransaction(new Realm.Transaction() {

                @Override
                public void execute(Realm realm) {
                    RealmResults<Pet> rows= realm.where(Pet.class).equalTo("name",
                            etxt_name.getText().toString()).findAll();;
                    if(rows.isEmpty()){
                        DeletePet.this.runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "Pet not found",
                                        Toast.LENGTH_LONG).show();
                            }
                        });
                    }else{
                        rows.deleteAllFromRealm();
                        etxt_name.setText("");
                    }

                }
            });
            Toast.makeText(this, "Pet deleted",
                    Toast.LENGTH_LONG).show();
        } catch (Exception e){
            Toast.makeText(this, "Pet cannot be deleted",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void limpiar(View view) {
        etxt_name.setText("");
    }
}