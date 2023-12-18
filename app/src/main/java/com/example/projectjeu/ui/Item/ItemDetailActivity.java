package com.example.projectjeu.ui.Item;

import android.content.Intent;
import android.media.effect.Effect;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.projectjeu.R;

import com.example.projectjeu.ui.Item.ItemListAdapter;
import com.example.projectjeu.ui.Item.Item;
import com.example.projectjeu.ui.Item.ItemActivity;

public class ItemDetailActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_item_detail);

        String name = getIntent().getStringExtra("name");
        String avatar = getIntent().getStringExtra("avatar");
        int quantite = getIntent().getIntExtra("quantite", 0);
        int effet = getIntent().getIntExtra("effet",0);
        String description = getIntent().getStringExtra("description");

        TextView nameTextView = findViewById(R.id.detailName);
        TextView quantiteTextView = findViewById(R.id.detailQuantite);
        TextView effetTextView = findViewById(R.id.Effect);
        TextView descriptionTextView = findViewById(R.id.Description);

        nameTextView.setText(name);
        quantiteTextView.setText("Quantité : " + String.valueOf(quantite));
        effetTextView.setText("Effet: " + String.valueOf(effet));
        descriptionTextView.setText("Description : " + String.valueOf(description));


        int resId = getResources().getIdentifier(avatar, "drawable", getPackageName());
        ImageView avatarImage = findViewById(R.id.detailAvatar);
        if (resId != 0 && avatarImage != null) {
            avatarImage.setImageResource(resId);
        } else {
            avatarImage.setImageResource(R.mipmap.ic_launcher);
        }

        Button Ok = findViewById(R.id.Ok);
        Ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("resultName", name);
                resultIntent.putExtra("resultAvatar", resId);
                resultIntent.putExtra("resultEffet", effet);
                resultIntent.putExtra("resultQuantite", quantite);
                resultIntent.putExtra("resultDescription", description);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
