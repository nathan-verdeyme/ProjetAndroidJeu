package com.example.projectjeu.ui.combat;

public class CombattantRandom {
    private int id;
    private String name;
    private String avatar;
    private int pointDeVie;
    private String attaque;
    private int degats;

    public CombattantRandom(int id, String name, String avatar, int pointDeVie, String attaque, int degats) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.pointDeVie = pointDeVie;
        this.attaque = attaque;
        this.degats = degats;
    }

    public CombattantRandom() {

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

    public int getPointDeVie() {
        return pointDeVie;
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

    public void setPointDeVie(int pointDeVie) {
        this.pointDeVie = pointDeVie;
    }

    public void setAttaque(String attaque) {
        this.attaque = attaque;
    }

    public void setDegats(int degats) {
        this.degats = degats;
    }

    public int getRandomAttackDamage() {
        return getDegats();
    }
}
