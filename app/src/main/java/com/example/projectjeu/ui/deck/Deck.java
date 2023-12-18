package com.example.projectjeu.ui.deck;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.widget.ImageView;

import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import android.util.Base64;

public class Deck {
    private final int id;
    private final String name;
    private final String avatar;
    private final int niveau;
    private final String attaque;
    private final int degat;
    private final int pointDeVie;
    private int vieActuel;
    public Deck(JSONObject jObject) {
        this.id = jObject.optInt("id");
        this.name = jObject.optString("name");
        this.avatar = jObject.optString("avatar");
        this.niveau = jObject.optInt("niveau");
        this.attaque = jObject.optString("attaque");
        this.pointDeVie = jObject.optInt("pointDeVie");
        this.degat = jObject.optInt("degat");
        this.vieActuel = pointDeVie;

    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getAvatar() { return avatar; }
    public int getNiveau() { return niveau; }
    public String getAttaque() { return attaque; }
    public  int getPointDeVie() { return  pointDeVie; }
    public int getDegat() { return degat; }

    public void setPointDeVie(int vieActuel) {
    }
}
