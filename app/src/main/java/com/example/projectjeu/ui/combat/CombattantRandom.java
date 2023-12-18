package com.example.projectjeu.ui.combat;

import com.example.projectjeu.ui.connection_API.ConnectionRest;

import org.json.JSONObject;

import java.util.Random;

public class CombattantRandom {
    private int id;
    private String name;
    private String avatar;
    private int pointsDeVie;
    private String attaque;
    private int degats;

    public CombattantRandom(int id, String name, String avatar, int pointsDeVie, String attaque, int degats) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.pointsDeVie = pointsDeVie;
        this.attaque = attaque;
        this.degats = degats;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAvatar() {
        return avatar;
    }

    public int getPointsDeVie() {
        return pointsDeVie;
    }

    public String getAttaque() {
        return attaque;
    }

    public int getDegats() {
        return degats;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setPointsDeVie(int pointsDeVie) {
        this.pointsDeVie = pointsDeVie;
    }

    public void setAttaque(String attaque) {
        this.attaque = attaque;
    }

    public void setDegats(int degats) {
        this.degats = degats;
    }

    public int getRandomAttackDamage() {
        return new Random().nextInt(degats);
    }

    public void choisirCombattantRandom() {
        try {
            JSONObject combattantData = new ConnectionRest().getCombattantData("");

            if (combattantData != null) {
                this.id = combattantData.optInt("id");
                this.name = combattantData.optString("name");
                this.avatar = combattantData.optString("avatar");
                this.pointsDeVie = combattantData.optInt("pointsDeVie");
                this.attaque = combattantData.optString("attaque");
                this.degats = combattantData.optInt("degats");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
