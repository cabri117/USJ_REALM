package com.example.hawk.usj_realm.pet;

import android.widget.EditText;

import com.example.hawk.usj_realm.Connect;
import com.example.hawk.usj_realm.R;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ListIterator;

import io.realm.Realm;
import io.realm.RealmResults;

@EActivity(R.layout.update_pet)
public class UpdatePet extends Connect {

    @ViewById(R.id.etPetName)
    EditText etxt_name;
    @ViewById(R.id.etPetNewName)
    EditText etxt_name_new;
    @ViewById(R.id.etOwnerName)
    EditText etxt_owner;

    @Click(R.id.btnUpdatePet)
    public void update() {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Pet> results = realm.where(Pet.class).findAll();
                ListIterator li = results.listIterator();
                Pet pet = null;
                while(li.hasNext()) {
                    pet = (Pet) li.next();
                    if (pet.getOwner().getName().equalsIgnoreCase(etxt_owner.getText().toString()) &&
                            pet.getName().equalsIgnoreCase(etxt_name.getText().toString())) {
                        pet.setName(etxt_name_new.getText().toString());
                    }
                }
            }
        });
    }

    @Click(R.id.btnReset)
    public void clean() {
        etxt_name.setText("");
        etxt_name_new.setText("");
        etxt_owner.setText("");
    }
}