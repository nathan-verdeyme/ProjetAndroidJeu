package com.example.projectjeu.ui.combat;

import android.content.Intent;
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
import com.example.projectjeu.ui.deck.DeckActivity;
import com.example.projectjeu.ui.home.HomeActivity;

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
    private Button buttonHome;
    private CombattantUtilisateur userCombattant;
    private CombattantRandom randomCombattant;

    private ItemCombat itemUtilisateur;

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
        buttonHome = findViewById(R.id.button_home);


        userCombattant = getCombattantUtilisateur();
        randomCombattant = getRandomCombattant();
        itemUtilisateur = getItemUtilisateur();

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
                intent.putExtra("itemUsed", itemUtilisateur.isItemUsed());
                startActivity(intent);
            }
        });
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CombatActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        buttonItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                utiliserItem();
            }
        });
    }

    public void utiliserItem() {
        if (!itemUtilisateur.isItemUsed()) {
                if (itemUtilisateur.getEffetItem() < 0){
                    int vieActuel = randomCombattant.getPointDeVie() + itemUtilisateur.getEffetItem();
                    randomCombattant.setPointDeVie(Math.max(vieActuel, 0));
                    Toast.makeText(this, "L'item "+itemUtilisateur.getNameItem()+" à affecter votre aversaire de "+itemUtilisateur.getEffetItem(), Toast.LENGTH_SHORT).show();
                }else {
                    int vieActuel = userCombattant.getCombattantVie() + itemUtilisateur.getEffetItem();
                    userCombattant.setCombattantVie(Math.max(vieActuel, 0));
                    Toast.makeText(this, "L'item "+itemUtilisateur.getNameItem()+" vous à rajouter "+itemUtilisateur.getEffetItem(), Toast.LENGTH_SHORT).show();

                }
            updateUserHealthDisplay();
            updateRandomHealthDisplay();
            checkEndOfCombat();
            itemUtilisateur.setItemUsed(true);
        } else {
            Toast.makeText(this, "Cet item a déjà été utilisé dans ce combat.", Toast.LENGTH_SHORT).show();
        }
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
    private void reinitialiserVieCombattants() {
        userCombattant.setCombattantVie(userCombattant.getCombattantVie());

        randomCombattant.setPointDeVie(randomCombattant.getPointDeVie());
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
    private ItemCombat getItemUtilisateur(){
        ItemCombat itemCombat = new ItemCombat(this);

        itemCombat.getNameItem();
        itemCombat.getDescriptionItem();
        itemCombat.getAvatarItem();
        itemCombat.getEffetItem();
        itemCombat.getQuantiteItem();

        return itemCombat;
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
