package com.example.projectjeu.ui.combat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectjeu.R;
import com.example.projectjeu.ui.connection_API.ConnectionRest;
import com.example.projectjeu.ui.deck.Deck;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class CombatActivity extends AppCompatActivity {
    private ImageView avatarUtilisateur;
    private ImageView avatarRandom;
    private TextView vieUtilisateur;
    private TextView vieRandom;
    private Button attackButton;
    private CombattantUtilisateur userCombattant;
    private CombattantRandom randomCombattant;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_combat);

        // Initialisation des vues
        vieUtilisateur = findViewById(R.id.vieUtilisateur);
        vieRandom = findViewById(R.id.vieRandom);
        avatarUtilisateur= findViewById(R.id.avatarUtilisateur);
        avatarRandom = findViewById(R.id.avatarRandom);
        attackButton = findViewById(R.id.button_attack);



        userCombattant = getCombattantUtilisateur();
        randomCombattant = getRandomCombattant();

        // Affichage des points de vie
        updateUserHealthDisplay();
        updateRandomHealthDisplay();
        // Gestion des attaques
        attackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CombatActivity.this, CombatAttaqueActivity.class);
                startActivity(intent);
            }
        });
    }


    private void updateUserHealthDisplay() {
        vieUtilisateur.setText("Vie: " + userCombattant.getCombattantVie());
    }

    private void updateRandomHealthDisplay() {
        vieRandom.setText("Vie: " + randomCombattant.getPointsDeVie());
    }

    private CombattantUtilisateur getCombattantUtilisateur(){
        CombattantUtilisateur combattantUtil = new CombattantUtilisateur(this);

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