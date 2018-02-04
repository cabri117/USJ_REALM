package com.example.hawk.usj_realm;

/**
 * Created by hawk on 1/26/18.
 */



import android.os.Bundle;


import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.example.hawk.usj_realm.Helpers.ObjectWrapperForBinder;
import com.example.hawk.usj_realm.Models.Person;
import com.example.hawk.usj_realm.Models.Pet;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

//import com.example.david.realm301.R;

public class AddPersonActivity extends Connect {

    private EditText etxt_name;
    private EditText etxt_age;
    private LinearLayout petsLayout;
    private Button saveButton;
    private Button deleteButton;
    Person person;
    ArrayList<String> pets = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        //Intent i = getIntent();
        //realm = (Realm)i.getSerializableExtra("connection");
        etxt_name = findViewById(R.id.editText);
        etxt_age = findViewById(R.id.editText2);
        petsLayout= findViewById(R.id.petsLayout);
        saveButton= findViewById(R.id.button);
        deleteButton= findViewById(R.id.button7);
        setTitle("Nuevo");
        getData();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) // Press Back Icon
        {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    void getData(){
        try{
            final Object objReceived = ((ObjectWrapperForBinder)getIntent().getExtras().getBinder("person")).getData();
            if(objReceived != null){
                person= (Person)objReceived;
                Log.d("", "received object=" + objReceived);
                etxt_name.setText(person.getName());
                etxt_age.setText(""+person.getAge());
                RealmList<Pet> pets=person.getPets();
                if(pets.size()> 0){
                    for(int i=0;i<pets.size();i++){
                        drawPet(pets.get(i).getName());
                    }
                }
                deleteButton.setVisibility(View.VISIBLE);
                setTitle("Editar");
            }
        }catch (Exception ex){}
    }


    public void delete(View view) {
        try{
            realm.executeTransaction(new Realm.Transaction() {

                @Override
                public void execute(Realm realm) {
                    // AddPersonActivity a person

                    RealmResults<Person> rows= realm.where(Person.class).equalTo("id", person.getId()).findAll();;

                    if(rows.isEmpty()){
                        AddPersonActivity.this.runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                Toast.makeText(AddPersonActivity.this, "No se encontro usuario",
                                        Toast.LENGTH_LONG).show();
                            }
                        });

                    }else{
                        rows.deleteAllFromRealm();
                        etxt_name.setText("");
                    }

                }
            });

            finish();
            Toast.makeText(this, "Usuario borrado",
                    Toast.LENGTH_LONG).show();

        }catch (Exception e){
            Toast.makeText(this, "No se pudo borrar usuario",
                    Toast.LENGTH_LONG).show();
        }

    }
    public void addPet(View view) {
        drawPet(null);

    }

    void drawPet(String name){
        final LinearLayout ll = new LinearLayout(this);
        ll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ll.setOrientation(LinearLayout.HORIZONTAL);

        EditText text = new EditText(this);
        text.setHint("Inserte mascota");
        ll.addView(text);
        if(name!= null){
            text.setText(name);
        }
        Button delete = new Button(this);
        delete.setText("Borrar");
        ll.addView(delete);
        delete.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                petsLayout.removeView(ll);
            }
        });
        petsLayout.addView(ll);
    }

    public void nuevo(View view) {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Person newPerson= null;
                if(person==null){
                    int pk = (int) (Math.random() * 1000) + 1;
                    newPerson = realm.createObject(Person.class, pk);
                }else{
                    newPerson = realm.where(Person.class).equalTo("id", person.getId()).findFirst();
                    newPerson.getPets().deleteAllFromRealm();
                }
                newPerson.setName(etxt_name.getText().toString());
                newPerson.setAge(etxt_age.getText().toString());
                ArrayList<String> pets = getPets();
                if(pets.size() > 0){
                    for (int i = 0; i < pets.size(); i++) {
                        int petPk = (int) (Math.random() * 1000) + 1;
                        Pet pet = realm.createObject(Pet.class, petPk);
                        pet.setName(pets.get(i));
                        newPerson.getPets().add(pet);
                    }
                }
            }
        });

        this.finish();
        Toast.makeText(this, "Usuario guardardo",
                Toast.LENGTH_LONG).show();
    }

    ArrayList<String> getPets(){
        ArrayList<String> pets = new ArrayList<String>();
        int count = petsLayout.getChildCount();
        for (int i = 0; i < count; i++) {
            LinearLayout v = (LinearLayout)petsLayout.getChildAt(i);
            int count2 = v.getChildCount();
            for (int y = 0; y < count2; y++) {
                View v2 = v.getChildAt(y);
                if(v2 instanceof EditText){
                    EditText text = (EditText)v2;

                    String petName = text.getText().toString();
                    if(!petName.isEmpty()){
                        pets.add(petName);
                    }
                }

            }
        }
        return pets;
    }
}