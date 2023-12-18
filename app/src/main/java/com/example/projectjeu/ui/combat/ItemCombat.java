package com.example.projectjeu.ui.combat;

import android.content.Context;
import android.content.SharedPreferences;

public class ItemCombat {
    private SharedPreferences sharedPref;

    private boolean isUsed = false;
    public ItemCombat(Context context) {
        sharedPref = context.getSharedPreferences("itemUtilisateur", Context.MODE_PRIVATE);
    }

    public String getNameItem() {
        return sharedPref.getString("name",null);
    }

    public int getAvatarItem() {
        return sharedPref.getInt("avatar", 0);
    }
    public String getDescriptionItem() {
        return sharedPref.getString("description", null);
    }
    public int getEffetItem() {
        return sharedPref.getInt("effet", 0);
    }
    public int getQuantiteItem() {
        return sharedPref.getInt("quantite", 0);
    }


    public boolean isItemUsed() {
        return isUsed;
    }

    public void setItemUsed(boolean used) {
        isUsed = used;
    }

}
