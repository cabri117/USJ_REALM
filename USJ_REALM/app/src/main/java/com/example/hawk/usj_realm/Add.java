package com.example.hawk.usj_realm;

/**
 * Created by hawk on 1/26/18.
 */



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;


import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;


import java.io.File;

import io.realm.Realm;

//import com.example.david.realm301.R;

public class Add extends Connect {

    private EditText etxt_name;
    private EditText etxt_age;
    private ImageButton img;
    private String nameFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);

        //Intent i = getIntent();
        //realm = (Realm)i.getSerializableExtra("connection");
        etxt_name = findViewById(R.id.etxt_name);
        etxt_age = findViewById(R.id.etxt_age);
        nameFoto = etxt_name.getText().toString() + etxt_age.getText().toString();
        img = (ImageButton)findViewById(R.id.imageButton);

    }

    public void hacerFoto(View v) {
        Intent intento1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File foto = new File(getExternalFilesDir(null), nameFoto);
        //mCurrentPhotoPath = "file:" + foto.getAbsolutePath();
        Uri photoURI = FileProvider.getUriForFile(this,
                BuildConfig.APPLICATION_ID + ".provider", foto);
        intento1.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intento1.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
        startActivity(intento1);
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
                person.setFoto(nameFoto);
            }
        });
    }

    public void limpiar(View view) {
        etxt_name.setText("");
        etxt_age.setText("");

    }
}