package com.example.projectjeu.ui.deck;

import android.content.Context;
import android.graphics.Color;
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
            convertView = layoutInflater.inflate(R.layout.fragment_deck_detail, null);
            holder = new ViewHolder();
            holder.name = convertView.findViewById(R.id.detailName);
            holder.avatar = convertView.findViewById(R.id.detailAvatar);
            holder.niveau = convertView.findViewById(R.id.detailNiveau);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Deck deck = this.listData.get(position);
        holder.name.setText(deck.getName());
        if (deck.getAvatar() != null && deck.getAvatar().getDrawable() != null) {
            holder.avatar.setImageDrawable(deck.getAvatar().getDrawable());
        }
        holder.niveau.setText(String.valueOf(deck.getNiveau()));

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
