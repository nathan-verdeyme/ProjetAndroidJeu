package com.example.projectjeu.ui.deck;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.projectjeu.R;

public class DeckDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_deck_detail);

        String name = getIntent().getStringExtra("name");
        String avatar = getIntent().getStringExtra("avatar");
        int niveau = getIntent().getIntExtra("niveau", 0);
        int pointDeVie = getIntent().getIntExtra("pointDeVie",0);
        String attaqueDisponible = getIntent().getStringExtra("attaque");

        TextView nameTextView = findViewById(R.id.detailName);
        TextView niveauTextView = findViewById(R.id.detailNiveau);
        TextView pointDeVieTextView = findViewById(R.id.detailPointDeVie);
        TextView attaqueDisponibleTextView = findViewById(R.id.detailAttaque);

        nameTextView.setText(name);
        niveauTextView.setText(String.valueOf(niveau));
        pointDeVieTextView.setText(String.valueOf(pointDeVie));
        attaqueDisponibleTextView.setText(String.valueOf(attaqueDisponible));


        int resId = getResources().getIdentifier(avatar, "drawable", getPackageName());
        ImageView avatarImage = findViewById(R.id.detailAvatar);
        if (resId != 0 && avatarImage != null) {
            avatarImage.setImageResource(resId);
        } else {
            avatarImage.setImageResource(R.mipmap.ic_launcher);
        }

        Button selectionner = findViewById(R.id.selectionner);
        selectionner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("resultName", name);
                resultIntent.putExtra("resultAvatar", resId);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
