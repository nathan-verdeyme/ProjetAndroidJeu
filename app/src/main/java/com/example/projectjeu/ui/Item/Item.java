package com.example.projectjeu.ui.Item;

import org.json.JSONObject;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.widget.ImageView;

import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import android.util.Base64;

public class Item {

    private final String name;
    private final  String avatar;
    private final  String description ;
    private final int effet;
    private final int quantite;
    public Item(JSONObject jObject) {
        this.name = jObject.optString("name");
        this.avatar = jObject.optString("avatar");
        this.description = jObject.optString("description");
        this.effet = jObject.optInt("effet");
        this.quantite = jObject.optInt("quantite");

    }

    public String getName() { return name; }
    public  String getAvatar() { return avatar; }
    public  String getDescription() { return description; }
    public  int getEffet() { return effet; }
    public  int getQuantite() { return  quantite; }
}
