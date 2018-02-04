package com.example.hawk.usj_realm.pet;

import android.widget.EditText;

import com.example.hawk.usj_realm.Connect;
import com.example.hawk.usj_realm.R;
import com.example.hawk.usj_realm.person.Person;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import io.realm.Realm;


@EActivity(R.layout.add_pet)
public class AddPet extends Connect {

    @ViewById
    EditText etxt_person;
    @ViewById
    EditText etxt_name;
    @ViewById
    EditText etxt_type;

    @Click(R.id.button_add_pet)
    public void newPet() {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Person person = realm.where(Person.class).equalTo("name", etxt_person.getText().toString()).findFirst();
                if(person != null) {
                    Number currentIdNum = realm.where(Pet.class).max("id");
                    int pk = currentIdNum == null ? 1 : currentIdNum.intValue() + 1;
                    Pet pet = realm.createObject(Pet.class, pk);
                    pet.setOwner(person);
                    pet.setName(etxt_name.getText().toString());
                    pet.setType(etxt_type.getText().toString());
                    person.getPets().add(pet);
                }
            }
        });
    }

    @Click(R.id.button_reset)
    public void clean() {
        etxt_name.setText("");
        etxt_type.setText("");
        etxt_person.setText("");
    }
}