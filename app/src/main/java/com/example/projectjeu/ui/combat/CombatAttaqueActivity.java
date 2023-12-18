package com.example.projectjeu.ui.combat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectjeu.R;
import com.example.projectjeu.ui.connection_API.ConnectionRest;
import com.example.projectjeu.ui.deck.Deck;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class CombatAttaqueActivity extends AppCompatActivity {
    private ImageView avatarUtilisateur;
    private ImageView avatarRandom;
    private TextView vieUtilisateur;
    private TextView vieRandom;
    private Button attackButton1;
    private Button attackButton2;
    private Button retourButton;
    private CombattantUtilisateur userCombattant;
    private CombattantRandom randomCombattant;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_combatattack);

        TextView vieUtilisateur = findViewById(R.id.vieUtilisateur);
        TextView vieRandom = findViewById(R.id.vieRandom);
        ImageView avatarUtilisateur= findViewById(R.id.avatarUtilisateur);
        ImageView avatarRandom = findViewById(R.id.avatarRandom);
        attackButton1 = findViewById(R.id.button_attack1);
        
        updateCombatantViews(userCombattant, randomCombattant);
        userCombattant = getCombattantUtilisateur();
        randomCombattant = getRandomCombattant();

        // Affichage des points de vie
        updateUserHealthDisplay();
        updateRandomHealthDisplay();
        attackButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performAttack(userCombattant, randomCombattant, userCombattant.getAttaqueDegat());
                performRandomAttack();
                checkEndOfCombat();
            }
        });
          /*attackButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performAttack(userCombattant, randomCombattant, userCombattant.getAttack2Damage());
                performRandomAttack();
                checkEndOfCombat();
            }
        });*/
    }

    private void updateCombatantViews(CombattantUtilisateur userCombattant, CombattantRandom randomCombattant) {
       int resId=  userCombattant.getCombattantAvatarResId();
       String tag= "hey";
        Log.v(tag,"avatar"+resId);
            avatarUtilisateur.setImageResource(resId);
        vieUtilisateur.setText(String.valueOf(userCombattant.getCombattantVie()));

        int resId1 = getResources().getIdentifier(randomCombattant.getAvatar(), "drawable", getPackageName());
        if (resId1 != 0 && avatarRandom != null) {
            avatarRandom.setImageResource(resId1);
        } else {
            avatarRandom.setImageResource(R.mipmap.ic_launcher);
        }
        vieRandom.setText(String.valueOf(randomCombattant.getPointsDeVie()));
    }

    private void reinitialiserVieCombattants() {
        userCombattant.setCombattantVie(userCombattant.getCombattantVie());

        // Réinitialiser la vie du combattant random
        randomCombattant.setPointsDeVie(randomCombattant.getPointsDeVie());
    }
    private void performAttack(CombattantUtilisateur attacker, CombattantRandom defender, int damage) {

        int vieActuel = defender.getPointsDeVie() - damage;
        defender.setPointsDeVie(Math.max(vieActuel, 0));
        checkEndOfCombat();
        //updateUserHealthDisplay();
        //updateRandomHealthDisplay();
    }

    private void performRandomAttack() {
        int damage = randomCombattant.getRandomAttackDamage();

        // Update user's health
        int vieActuel = userCombattant.getCombattantVie() - damage;
        userCombattant.setCombattantVie(Math.max(vieActuel, 0));
        checkEndOfCombat();
        updateUserHealthDisplay();
        updateRandomHealthDisplay();
    }
    private void endCombat(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

        finish();}
    private void checkEndOfCombat() {
        if (userCombattant.getCombattantVie() <= 0) {
            endCombat("Vous avez perdu !");
        } else if (randomCombattant.getPointsDeVie() <= 0) {
            endCombat("Vous avez gagné !");
        }
        reinitialiserVieCombattants();
    }

    private void updateUserHealthDisplay() {
        vieUtilisateur.setText("Vie: " + userCombattant.getCombattantVie());
    }

    private void updateRandomHealthDisplay() {
        vieRandom.setText("Vie: " + getRandomCombattant().getPointsDeVie());
    }

    private CombattantUtilisateur getCombattantUtilisateur(){
        CombattantUtilisateur combattantUtil = new CombattantUtilisateur(this); // Assurez-vous d'initialiser sharedPref dans le constructeur

        combattantUtil.getCombattantId();
        combattantUtil.getCombattantAttaque();
        combattantUtil.getCombattantAvatarResId();
        combattantUtil.getAttaqueDegat();
        combattantUtil.getCombattantVie();

        return combattantUtil;
    }

    private CombattantRandom getRandomCombattant() {
        List<Deck> combattants = getListData();
        if (combattants == null || combattants.isEmpty()) {
            return null;
        }

        Random random = new Random();
        int randomIndex = random.nextInt(combattants.size());
        Deck randomCombattant = combattants.get(randomIndex);

        CombattantRandom cr = new CombattantRandom(
                randomCombattant.getId(),
                randomCombattant.getName(),
                randomCombattant.getAvatar(),
                randomCombattant.getPointDeVie(),
                randomCombattant.getAttaque(),
                randomCombattant.getDegat()
        );

        return cr;
    }

    private ArrayList<Deck> getListData() {
        try {
            // Assuming you have a method for fetching and parsing JSON data in ConnectionRest
            ConnectionRest connectionRest = new ConnectionRest();
            connectionRest.execute("GET");
            String listJsonObjs = connectionRest.get();

            if (listJsonObjs != null) {
                return connectionRest.parse(listJsonObjs);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}
