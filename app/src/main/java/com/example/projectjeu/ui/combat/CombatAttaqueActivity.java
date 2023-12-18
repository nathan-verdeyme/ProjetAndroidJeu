package com.example.projectjeu.ui.combat;

import android.os.Bundle;
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

        retourButton = findViewById(R.id.retour);
        retourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        vieUtilisateur = findViewById(R.id.vieUtilisateur);
        vieRandom = findViewById(R.id.vieRandom);
        avatarUtilisateur = findViewById(R.id.avatarUtilisateur);
        avatarRandom = findViewById(R.id.avatarRandom);
        attackButton1 = findViewById(R.id.button_attack1);
        attackButton2 = findViewById(R.id.button_attack2);


        int index = getIntent().getIntExtra("idRandom", 0);
        String name = getIntent().getStringExtra("name");
        String avatar = getIntent().getStringExtra("avatarRandom");
        int vie = getIntent().getIntExtra("randomVie",0);
        String attaque1 = getIntent().getStringExtra("attaque1");
        int degats1 = getIntent().getIntExtra("degat1",0);
        String attaque2 = getIntent().getStringExtra("attaque2");
        int degats2 = getIntent().getIntExtra("degat2",0);

        // Initialisation des combattants
        userCombattant = getCombattantUtilisateur();
        randomCombattant = getRandomCombattant(index,name, avatar, vie, attaque1, degats1, attaque2, degats2);

        attackButton1.setText(userCombattant.getCombattantAttaque1());
        attackButton2.setText(userCombattant.getCombattantAttaque2());

        // Mise à jour des informations des combattants
        updateCombatantViews(userCombattant, randomCombattant);
        // Affichage des points de vie
        updateUserHealthDisplay();
        updateAvatarDisplayUtilisateur(avatarUtilisateur, userCombattant.getCombattantAvatarResId());
        updateAvatarDisplay(avatarRandom, avatar);
        attackButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performAttack(userCombattant, randomCombattant, userCombattant.getAttaqueDegat1());
                performRandomAttack();
                checkEndOfCombat();
            }
        });
          attackButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performAttack(userCombattant, randomCombattant, userCombattant.getAttaqueDegat2());
                performRandomAttack();
                checkEndOfCombat();
            }
        });
    }

    private void updateCombatantViews(CombattantUtilisateur userCombattant, CombattantRandom randomCombattant) {
        if (userCombattant != null && randomCombattant != null) {
            vieUtilisateur.setText(String.valueOf(userCombattant.getCombattantVie()));
            updateAvatarDisplayUtilisateur(avatarUtilisateur, userCombattant.getCombattantAvatarResId());
            updateAvatarDisplay(avatarRandom, randomCombattant.getAvatar());
            vieRandom.setText(String.valueOf(randomCombattant.getPointDeVie()));
        }
    }
    private void updateAvatarDisplay(ImageView imageView, String avatarResource) {

        int imageResId = getResources().getIdentifier(avatarResource, "drawable", getPackageName());
        imageView.setImageResource(imageResId);
    }
    private void updateAvatarDisplayUtilisateur(ImageView imageView, int avatarResource) {

        imageView.setImageResource(avatarResource);
    }
    private void reinitialiserVieCombattants() {
        userCombattant.setCombattantVie(userCombattant.getCombattantVie());

        randomCombattant.setPointDeVie(randomCombattant.getPointDeVie());
    }
    private void performAttack(CombattantUtilisateur attacker, CombattantRandom defender, int damage) {

        int vieActuel = defender.getPointDeVie() - damage;
        defender.setPointDeVie(Math.max(vieActuel, 0));
        checkEndOfCombat();
        updateUserHealthDisplay();
        updateRandomHealthDisplay();
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
        } else if (randomCombattant.getPointDeVie() <= 0) {
            endCombat("Vous avez gagné !");
        }
        reinitialiserVieCombattants();
    }

    private void updateUserHealthDisplay() {
        vieUtilisateur.setText("Vie: " + userCombattant.getCombattantVie());
    }

    private void updateRandomHealthDisplay() {
        vieRandom.setText("Vie: " + randomCombattant.getPointDeVie());
    }

    private CombattantUtilisateur getCombattantUtilisateur(){
        CombattantUtilisateur combattantUtil = new CombattantUtilisateur(this);

        combattantUtil.getCombattantId();
        combattantUtil.getCombattantAttaque1();
        combattantUtil.getCombattantAvatarResId();
        combattantUtil.getAttaqueDegat1();
        combattantUtil.getCombattantVie();
        combattantUtil.getCombattantAttaque2();
        combattantUtil.getAttaqueDegat2();

        return combattantUtil;
    }

    private CombattantRandom getRandomCombattant(int index, String name, String avatar, int vie, String attaque1, int degat1, String attaque2, int degat2) {
        // Directement créer un CombattantRandom avec les paramètres fournis
        CombattantRandom cr = new CombattantRandom(index, name, avatar, vie, attaque1, degat1,attaque2, degat2);
        return cr;
    }
}
