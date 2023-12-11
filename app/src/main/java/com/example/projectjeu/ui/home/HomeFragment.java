package com.example.projectjeu.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectjeu.R;
import com.example.projectjeu.ui.combat.combatFragment;
import com.example.projectjeu.ui.deck.DeckFragment;

public class HomeFragment extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);

        Button deck = findViewById(R.id.buttonDeck);
        deck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeFragment.this, DeckFragment.class);
                startActivity(intent);
            }
        });

        Button combat = findViewById(R.id.buttonCombat);
        deck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeFragment.this, combatFragment.class);
                startActivity(intent);
            }
        });
    }
}