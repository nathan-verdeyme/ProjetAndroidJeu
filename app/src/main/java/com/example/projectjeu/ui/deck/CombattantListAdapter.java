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
            convertView = layoutInflater.inflate(R.layout.fragment_deck, null);
            holder = new ViewHolder();
            holder.name = convertView.findViewById(R.id.detailName);
            holder.avatar = convertView.findViewById(R.id.detailAvatar);
            holder.niveau = convertView.findViewById(R.id.detailNiveau);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (position % 2 == 0) {
            convertView.setBackgroundColor(Color.rgb(150, 245, 170));
        }

        Deck deck = this.listData.get(position);
        holder.id.setText(String.valueOf(deck.getId()));
        holder.name.setText(deck.getName());
        holder.avatar.setImageResource(deck.getAvatarId());
        holder.niveau.setText(String.valueOf(deck.getNiveau()));
        return convertView;
    }

    static class ViewHolder {
        TextView id;
        TextView name;
        ImageView avatar;
        TextView niveau;
    }

    public int getCount() {
        return (listData != null) ? listData.size() : 0;
    }

    public Object getItem(int position) {
        return listData.get(position);
    }

    public long getItemId(int position) {
        return position;
    }
}
