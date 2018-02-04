package com.example.hawk.usj_realm;

/**
 * Created by mikearias on 2/4/18.
 */

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

// Your model just have to extend RealmObject.
// This will inherit an annotation which produces proxy getters and setters for all fields.
public class Mascota extends RealmObject {

    // All fields are by default persisted.
    private String name;
    private String type;
    private int age;
    @PrimaryKey
    private long id;

    // Let your IDE generate getters and setters for you!
    // Or if you like you can even have public fields and no accessors! See Dog.java and Cat.java
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    int getAge() {
        return age;
    }

    void setAge(String age) {
        int n=0;
        try{
            n=Integer.parseInt(age);
        }catch(Exception e){
            n=0;
        }

        this.age = n;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}