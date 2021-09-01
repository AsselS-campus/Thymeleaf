package com.example.thym.Form;

public class PersonnageForm {

    private int id;
    private String nom;
    private int hp; //hint point
    private PersonnageType type;

    public PersonnageForm() {}

    public PersonnageForm(int id, String nom, int hp, PersonnageType type) {
        this.id = id;
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
