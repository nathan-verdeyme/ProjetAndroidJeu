package com.example.projectjeu.ui.combat;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.projectjeu.R;

public class CombattantUtilisateur {
    private SharedPreferences sharedPref;

    public CombattantUtilisateur(Context context) {
        sharedPref = context.getSharedPreferences("combUtilisateur", Context.MODE_PRIVATE);
    }
    public int getCombattantId() {
        return sharedPref.getInt("combattantId", -1);
    }

    public String getCombattantName() {
        return sharedPref.getString("combattantName", null);
    }

    public int getCombattantAvatarResId() {
        return sharedPref.getInt("combattantAvatarResId", R.drawable.ic_launcher_foreground);
    }


    public String getCombattantAttaque() {
        return sharedPref.getString("combattantAttaque", null);
    }

    public int getCombattantVie() {
        return sharedPref.getInt("combattantVie", 0);
    }

    public int getAttaqueDegat() {
        return sharedPref.getInt("attaqueDegat", 0);
    }

    public void setCombattantVie(int vieActuel) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("combattantVie", vieActuel);
        editor.apply();
    }
}
