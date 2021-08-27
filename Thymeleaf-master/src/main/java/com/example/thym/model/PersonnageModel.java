package com.example.thym.model;

import java.util.concurrent.atomic.AtomicInteger;

public class PersonnageModel {
    public enum PersonnageType {
        GUERRIER,
        MAGE,
        VOLEUR
    }

    private static AtomicInteger at = new AtomicInteger(1);

    private int id;
    private String nom;
    private int hp; //hint point
    private PersonnageType type;

    public PersonnageModel(String nom, int hp, PersonnageType type) {
        this.id = at.getAndIncrement();
        this.nom = nom;
        this.hp = hp;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public PersonnageType getType() {
        return type;
    }

    public void setType(PersonnageType type) {
        this.type = type;
    }
}
