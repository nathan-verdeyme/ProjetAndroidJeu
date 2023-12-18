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
        String attaqueDisponible1 = getIntent().getStringExtra("attaque1");
        String attaqueDisponible2 = getIntent().getStringExtra("attaque2");
        int degat1 = getIntent().getIntExtra("degat1",0);
        int degat2 = getIntent().getIntExtra("degat2",0);

        TextView nameTextView = findViewById(R.id.detailName);
        TextView niveauTextView = findViewById(R.id.detailNiveau);
        TextView pointDeVieTextView = findViewById(R.id.detailPointDeVie);
        TextView attaqueDisponible1TextView = findViewById(R.id.detailAttaque1);
        TextView degat1TextView = findViewById(R.id.detailDegat1);
        TextView attaqueDisponible2TextView = findViewById(R.id.detailAttaque2);
        TextView degat2TextView = findViewById(R.id.detailDegat2);

        nameTextView.setText(name);
        niveauTextView.setText("niveau : " + String.valueOf(niveau));
        pointDeVieTextView.setText("Point de vie : " + String.valueOf(pointDeVie));
        attaqueDisponible1TextView.setText("Attaque 1 : " + String.valueOf(attaqueDisponible1));
        degat1TextView.setText("Dégat : "+ String.valueOf(degat1));
        attaqueDisponible2TextView.setText("Attaque 2 : " + String.valueOf(attaqueDisponible2));
        degat2TextView.setText("Dégat : "+ String.valueOf(degat2));


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
                resultIntent.putExtra("attaque1", attaqueDisponible1);
                resultIntent.putExtra("attaque2", attaqueDisponible2);
                resultIntent.putExtra("pointDeVie", pointDeVie);
                resultIntent.putExtra("degat1", degat1);
                resultIntent.putExtra("degat2", degat2);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
