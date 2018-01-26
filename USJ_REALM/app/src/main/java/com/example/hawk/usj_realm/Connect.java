package com.example.hawk.usj_realm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import io.realm.Realm;

/**
 * Created by hawk on 1/26/18.
 */

public class Connect extends AppCompatActivity {
    Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Realm.init(this);
        realm = Realm.getDefaultInstance();

    }

}
