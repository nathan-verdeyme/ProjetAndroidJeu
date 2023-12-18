package com.example.projectjeu.ui.deck;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectjeu.R;
import com.example.projectjeu.ui.Item.ItemActivity;
import com.example.projectjeu.ui.connection_API.ConnectionRest;
import com.example.projectjeu.ui.home.HomeActivity;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class DeckActivity extends AppCompatActivity {

    private static final int DECK_DETAIL_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_deck);

        Button home = findViewById(R.id.buttonHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DeckActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        Button item = findViewById(R.id.buttonItem);
        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DeckActivity.this, ItemActivity.class);
                startActivity(intent);
            }
        });
        ArrayList<Deck> listData = getListData();
        final ListView listView = findViewById(R.id.ListView);
        listView.setAdapter(new CombattantListAdapter(this, listData));

        // When the user clicks on the ListItem
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                Deck deck = (Deck) o;

                Intent intent = new Intent(DeckActivity.this, DeckDetailActivity.class);
                intent.putExtra("id", deck.getId());
                intent.putExtra("name", deck.getName());
                intent.putExtra("avatar", deck.getAvatar());
                intent.putExtra("niveau", deck.getNiveau());
                intent.putExtra("attaque",deck.getAttaque());
                intent.putExtra("pointDeVie",deck.getPointDeVie());
                intent.putExtra("degat",deck.getDegat());
                startActivityForResult(intent, DECK_DETAIL_REQUEST);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == DECK_DETAIL_REQUEST && resultCode == RESULT_OK) {
            String resultName = data.getStringExtra("resultName");
            int resultAvatarResId = data.getIntExtra("resultAvatar", R.drawable.ic_launcher_foreground);
            int combattantId = data.getIntExtra("id", -1);
            String attaque = data.getStringExtra("attaque");
            int pointDeVie = data.getIntExtra("pointDeVie", 0);
            int degat = data.getIntExtra("degat", 0);




            TextView resultNameTextView = findViewById(R.id.resultName);
            ImageView resultAvatarImageView = findViewById(R.id.resultAvatar);

            resultNameTextView.setText(resultName);
            resultAvatarImageView.setImageResource(resultAvatarResId);

            resultNameTextView.setVisibility(View.VISIBLE);
            resultAvatarImageView.setVisibility(View.VISIBLE);
            SharedPreferences sharedPref = getSharedPreferences("combUtilisateur", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();


            editor.putInt("combattantId", combattantId);
            editor.putString("combattantName", resultName);
            editor.putInt("combattantAvatarResId", resultAvatarResId);
            editor.putString("combattantAttaque", attaque);
            editor.putInt("combattantVie", pointDeVie);
            editor.putInt("attaqueDegat", degat);
            editor.apply();
        }


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
