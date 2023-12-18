package com.example.projectjeu.ui.combat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectjeu.R;
import com.example.projectjeu.ui.Item.ItemActivity;
import com.example.projectjeu.ui.connection_API.ConnectionRest;
import com.example.projectjeu.ui.deck.Deck;
import com.example.projectjeu.ui.deck.DeckActivity;

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
    private Button buttonItem;
    private Button buttonDeck;
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
        buttonItem = findViewById(R.id.button_item);
        buttonDeck = findViewById(R.id.button_deck);


        userCombattant = getCombattantUtilisateur();
        randomCombattant = getRandomCombattant();

        updateUserHealthDisplay();
        updateRandomHealthDisplay();
        updateAvatarDisplayUtilisateur(avatarUtilisateur, userCombattant.getCombattantAvatarResId());
        updateAvatarDisplay(avatarRandom, randomCombattant.getAvatar());
        attackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CombatActivity.this, CombatAttaqueActivity.class);
                intent.putExtra("utilisateurvie", userCombattant.getCombattantVie());
                intent.putExtra("randomVie",randomCombattant.getPointDeVie());
                intent.putExtra("avatarRandom", randomCombattant.getAvatar());
                intent.putExtra("avatarUtilisateur", userCombattant.getCombattantAvatarResId());
                intent.putExtra("idRandom", randomCombattant.getId());
                intent.putExtra("name",randomCombattant.getName());
               intent.putExtra("attaque1", randomCombattant.getAttaque1());
               intent.putExtra("degat1", randomCombattant.getDegats1());
                intent.putExtra("attaque2", randomCombattant.getAttaque2());
                intent.putExtra("degat2", randomCombattant.getDegats2());
                startActivity(intent);
            }
        });
        buttonDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CombatActivity.this, DeckActivity.class);
                startActivity(intent);
            }
        });

        buttonItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CombatActivity.this, ItemActivity.class);
                startActivity(intent);
            }
        });
    }


    private void updateUserHealthDisplay() {
        vieUtilisateur.setText("Vie: " + userCombattant.getCombattantVie());
    }

    private void updateRandomHealthDisplay() {
        vieRandom.setText("Vie: " + randomCombattant.getPointDeVie());
    }
    private void updateAvatarDisplay(ImageView imageView, String avatarResource) {

        int imageResId = getResources().getIdentifier(avatarResource, "drawable", getPackageName());
        imageView.setImageResource(imageResId);
    }
    private void updateAvatarDisplayUtilisateur(ImageView imageView, int avatarResource) {

       imageView.setImageResource(avatarResource);
    }

    private CombattantUtilisateur getCombattantUtilisateur(){
        CombattantUtilisateur combattantUtil = new CombattantUtilisateur(this);

        combattantUtil.getCombattantId();
        combattantUtil.getCombattantAttaque1();
        combattantUtil.getCombattantAttaque2();
        combattantUtil.getCombattantAvatarResId();
        combattantUtil.getAttaqueDegat1();
        combattantUtil.getAttaqueDegat2();
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
                randomCombattant.getAttaque1(),
                randomCombattant.getDegat1(),
                randomCombattant.getAttaque2(),
                randomCombattant.getDegat2()
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
