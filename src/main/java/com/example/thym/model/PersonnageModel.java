package com.example.thym.model;

public class PersonnageModel {
    private int id;
    private String nom;
    private int hp; //hint point
    private String type;

    public PersonnageModel(int id, String nom, int hp, String type) {
        this.id = id;
        this.nom = nom;
        this.hp = hp;
        this.type = type;
    }
    public PersonnageModel() {
        this.id = 100;
        this.nom = "tooto";
        this.hp = 2;
        this.type = "aaaa";
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
