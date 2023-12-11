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
    private final ImageView avatar;
    private final int niveau;

    public Deck(JSONObject jObject) {
        this.id = jObject.optInt("id");
        this.name = jObject.optString("name");
        String avatarBase64 = jObject.optString("avatar");
        byte[] imageBytes = Base64.decode(avatarBase64, Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        this.avatar = new ImageView(null);
        this.avatar.setImageBitmap(decodedImage);

        this.niveau = jObject.optInt("niveau");
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public ImageView getAvatar() { return avatar; }

    public int getNiveau() { return niveau; }
}
