package com.example.projectjeu.ui.deck;

import android.widget.ImageView;

import org.json.JSONObject;

public class Deck {
    private final int id;
    private final String name;
    private final int avatarId;
    private final int niveau;

    public Deck(JSONObject jObject) {
        this.id = jObject.optInt("id");
        this.name = jObject.optString("name");
        this.avatarId = jObject.optInt("avatarId");
        this.niveau = jObject.optInt("niveau");
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAvatarId() { return avatarId; }

    public int getNiveau() { return niveau; }
}
