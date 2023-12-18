package com.example.projectjeu.ui.deck;

import org.json.JSONObject;

public class Deck {
    private final int id;
    private final String name;
    private final String avatar;
    private final int niveau;
    private final String attaque1;
    private final int degat1;
    private final String attaque2;
    private final int degat2;
    private final int pointDeVie;
    private int vieActuel;
    public Deck(JSONObject jObject) {
        this.id = jObject.optInt("id");
        this.name = jObject.optString("name");
        this.avatar = jObject.optString("avatar");
        this.niveau = jObject.optInt("niveau");
        this.attaque1 = jObject.optString("attaque1");
        this.attaque2 = jObject.optString("attaque2");
        this.pointDeVie = jObject.optInt("pointDeVie");
        this.degat1 = jObject.optInt("degat1");
        this.degat2 = jObject.optInt("degat2");
        this.vieActuel = pointDeVie;

    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getAvatar() { return avatar; }
    public int getNiveau() { return niveau; }
    public String getAttaque1() { return attaque1; }
    public  int getPointDeVie() { return  pointDeVie; }
    public int getDegat1() { return degat1; }
    public int getDegat2() { return degat2; }
    public String getAttaque2() { return attaque2; }
}
