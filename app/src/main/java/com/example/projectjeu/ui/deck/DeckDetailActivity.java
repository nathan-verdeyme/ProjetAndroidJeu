package com.example.projectjeu.ui.deck;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.projectjeu.R;

public class DeckDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_deck_detail);

        // Retrieve data from intent
        int id = getIntent().getIntExtra("id", 0);
        String name = getIntent().getStringExtra("name");
        int niveau = getIntent().getIntExtra("niveau", 0);
        int pointDeVie = getIntent().getIntExtra("pointDeVie", 0);
        int attaqueDisponible = getIntent().getIntExtra("attaqueDisponible", 0);

        // Display data in TextViews
        //TextView idTextView = findViewById(R.id.detailId);
        TextView nameTextView = findViewById(R.id.detailName);
        TextView niveauTextView = findViewById(R.id.detailNiveau);
        //TextView pointDeVieTextView = findViewById(R.id.detailPointDeVie);
        //TextView attaqueDisponibleTextView = findViewById(R.id.detailAttaqueDisponible);

        //idTextView.setText(String.valueOf(id));
        nameTextView.setText(name);
        niveauTextView.setText(String.valueOf(niveau));
        //pointDeVieTextView.setText(String.valueOf(pointDeVie));
        //attaqueDisponibleTextView.setText(String.valueOf(attaqueDisponible));

        // Set avatar image
        ImageView avatarImageView = findViewById(R.id.detailAvatar);
        avatarImageView.setImageResource(getIntent().getIntExtra("avatar", R.mipmap.ic_launcher));
    }
}
