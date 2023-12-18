package com.example.projectjeu.ui.combat;

import java.util.Random;

public class CombattantRandom {
    private int id;
    private String name;
    private String avatar;
    private int pointDeVie;
    private String attaque1;
    private int degats1;
    private String attaque2;
    private int degats2;
    public CombattantRandom(int id, String name, String avatar, int pointDeVie, String attaque1, int degats1, String attaque2, int degats2) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.pointDeVie = pointDeVie;
        this.attaque1 = attaque1;
        this.degats1 = degats1;
        this.attaque2 = attaque2;
        this.degats2 = degats2;
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

    public String getAttaque1() {
        return attaque1;
    }

    public int getDegats1() {
        return degats1;
    }

    public String getAttaque2() {
        return attaque2;
    }

    public int getDegats2() {
        return degats2;
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

    public void setAttaque1(String attaque1) {
        this.attaque1 = attaque1;
    }

    public void setDegats1(int degats1) {
        this.degats1 = degats1;
    }

    public void setAttaque2(String attaque1) {
        this.attaque1 = attaque1;
    }

    public void setDegats2(int degats1) {
        this.degats1 = degats1;
    }

    public int getRandomAttackDamage() {
        Random random = new Random();
        int randomNum = random.nextInt(2);
        return randomNum == 0 ? getDegats1() : getDegats2();
    }
}
