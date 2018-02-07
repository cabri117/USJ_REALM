package com.example.hawk.usj_realm.person;

/**
 * Created by hawk on 1/26/18.
 */


import com.example.hawk.usj_realm.pet.Pet;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

// Your model just have to extend RealmObject.
// This will inherit an annotation which produces proxy getters and setters for all fields.
public class Person extends RealmObject {

    // All fields are by default persisted.
    private String name, foto;
    private int age;
    @PrimaryKey
    private long id;
    private RealmList<Pet> pets = null;
    // Let your IDE generate getters and setters for you!
    // Or if you like you can even have public fields and no accessors! See Dog.java and Cat.java
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    int getAge() {
        return age;
    }

    public void setAge(String age) {
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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public RealmList<Pet> getPets() {
        return pets;
    }

    public void setPets(RealmList<Pet> pets) {
        this.pets = pets;
    }
}