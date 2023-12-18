package com.example.projectjeu.ui.Item;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectjeu.R;

import com.example.projectjeu.ui.Item.Item;


import java.util.ArrayList;

public class ItemListAdapter extends BaseAdapter {
    private ArrayList<Item> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public ItemListAdapter(Context aContext, ArrayList<Item> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_item, parent, false);
            holder = new ViewHolder();
            holder.name = convertView.findViewById(R.id.nameTextView);
            holder.avatar = convertView.findViewById(R.id.avatarImageView);
            holder.quantite = convertView.findViewById(R.id.quantiteTextView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Item item = this.listData.get(position);
        holder.name.setText(item.getName());
        holder.quantite.setText("Quantité " + String.valueOf(item.getQuantite()));

        String avatarName = item.getAvatar(); // Assurez-vous que ceci renvoie le nom de la ressource
        int resId = context.getResources().getIdentifier(avatarName, "drawable", context.getPackageName());
        if (resId != 0) {
            holder.avatar.setImageResource(resId);
        } else {
            holder.avatar.setImageResource(R.drawable.avatar1); // Image par défaut
        }


        if (position % 2 == 0) {
            convertView.setBackgroundColor(Color.rgb(150, 245, 170));
        } else {
            // Définir une autre couleur ou laisser transparent
            convertView.setBackgroundColor(Color.TRANSPARENT);
        }

        return convertView;
    }

    static class ViewHolder {
        TextView name;
        ImageView avatar;
        TextView quantite;
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
