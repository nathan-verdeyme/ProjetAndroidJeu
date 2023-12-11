package com.example.projectjeu.ui.deck;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projectjeu.R;
import com.example.projectjeu.ui.connection_API.ConnectionRest;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class DeckFragment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_deck);

        ArrayList<Deck> listData = getListData();
        final ListView listView = findViewById(R.id.ListView);
        listView.setAdapter(new CombattantListAdapter(this, listData));

        // When the user clicks on the ListItem
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                Deck deck = (Deck) o;

                Intent intent = new Intent(DeckFragment.this, DeckDetailActivity.class);
                intent.putExtra("id", deck.getId());
                intent.putExtra("name", deck.getName());
                String base64Avatar = String.valueOf(deck.getAvatar());
                byte[] decodedString = Base64.decode(base64Avatar, Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                intent.putExtra("avatar", decodedByte);
                intent.putExtra("niveau", deck.getNiveau());
                startActivity(intent);
            }
        });
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
