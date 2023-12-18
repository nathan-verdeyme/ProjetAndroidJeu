package com.example.projectjeu.ui.Item;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectjeu.R;
import com.example.projectjeu.ui.connection_API.ConnectionRest;
import com.example.projectjeu.ui.Item.ItemListAdapter;
import com.example.projectjeu.ui.Item.Item;
import com.example.projectjeu.ui.Item.ItemActivity;


import com.example.projectjeu.ui.deck.DeckActivity;
import com.example.projectjeu.ui.home.HomeActivity;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ItemActivity extends AppCompatActivity {


    private static final int ITEM_DETAIL_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_item);

        Button home = findViewById(R.id.buttonHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ItemActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });


        Button combattant = findViewById(R.id.buttonCombattant);
        combattant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ItemActivity.this, DeckActivity.class);
                startActivity(intent);
            }
        });
        ArrayList<Item> listData = getListData();
        final ListView listView = findViewById(R.id.ListView);
        listView.setAdapter(new ItemListAdapter(this, listData));

        // When the user clicks on the ListItem
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                Item item = (Item) o;

                Intent intent = new Intent(ItemActivity.this, ItemDetailActivity.class);
                intent.putExtra("name", item.getName());
                intent.putExtra("avatar", item.getAvatar());
                intent.putExtra("description",item.getDescription());
                intent.putExtra("effet",item.getEffet());
                intent.putExtra("quantite",item.getQuantite());
                startActivityForResult(intent, ITEM_DETAIL_REQUEST);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ITEM_DETAIL_REQUEST && resultCode == RESULT_OK) {
            String resultName = data.getStringExtra("resultName");
            int resultAvatarResId = data.getIntExtra("resultAvatar", R.drawable.ic_launcher_foreground);
            String resultDescription = data.getStringExtra("resultDescription");
            int resultEffet = data.getIntExtra("resultEffet",0);
            int resultQuantite = data.getIntExtra("resultQuantite",0);

            TextView resultNameTextView = findViewById(R.id.resultName);
            ImageView resultAvatarImageView = findViewById(R.id.resultAvatar);

            resultNameTextView.setText(resultName);
            resultAvatarImageView.setImageResource(resultAvatarResId);

            // Assurez-vous que les vues pour afficher les résultats sont visibles
            resultNameTextView.setVisibility(View.VISIBLE);
            resultAvatarImageView.setVisibility(View.VISIBLE);

            SharedPreferences sharedPref = getSharedPreferences("itemUtilisateur", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();


            editor.putString("name", resultName);
            editor.putInt("avatar",resultAvatarResId);
            editor.putString("description",resultDescription);
            editor.putInt("effet",resultEffet);
            editor.putInt("quantite",resultQuantite);
            editor.apply();

        }
    }

    private ArrayList<Item> getListData() {
        try {
            // Assuming you have a method for fetching and parsing JSON data in ConnectionRest
            ConnectionRest connectionRest = new ConnectionRest();
            connectionRest.setAction("Item");
            connectionRest.execute("GET");
            String listJsonObjs = connectionRest.get();

            if (listJsonObjs != null) {
                return connectionRest.parse1(listJsonObjs);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

}
