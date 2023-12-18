package com.example.projectjeu.ui.deck;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.projectjeu.R;

import java.util.ArrayList;

public class CombattantListAdapter extends BaseAdapter {
    private ArrayList<Deck> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public CombattantListAdapter(Context aContext, ArrayList<Deck> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_deck, parent, false);
            holder = new ViewHolder();
            holder.name = convertView.findViewById(R.id.nameTextView);
            holder.avatar = convertView.findViewById(R.id.avatarImageView);
            holder.niveau = convertView.findViewById(R.id.niveauTextView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Deck deck = this.listData.get(position);
        holder.name.setText(deck.getName());
        holder.niveau.setText("niveau " + String.valueOf(deck.getNiveau()));
        holder.niveau.setTextColor(Color.BLACK);

        String avatarName = deck.getAvatar(); // Assurez-vous que ceci renvoie le nom de la ressource
        int resId = context.getResources().getIdentifier(avatarName, "drawable", context.getPackageName());
        if (resId != 0) {
            holder.avatar.setImageResource(resId);
        } else {
            holder.avatar.setImageResource(R.drawable.avatar1); // Image par défaut
        }


        if (position % 2 == 0) {

            convertView.setBackgroundColor(Color.rgb(150, 220, 170));
            holder.name.setTextColor(Color.BLACK);

        } else {
            // Définir une autre couleur ou laisser transparent
            convertView.setBackgroundColor(Color.rgb(150, 220, 170));
            holder.name.setTextColor(Color.BLACK);

        }

        return convertView;
    }

    static class ViewHolder {
        TextView name;
        ImageView avatar;
        TextView niveau;
    }

    public int getCount() {
        return listData.size();
    }

    public Object getItem(int position) {
        return listData.get(position);
    }

    public long getItemId(int position) {
        return position;
    }
}
