package com.example.projectjeu.ui.deck;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectjeu.R;

public class DeckAdapter extends ListAdapter<String, DeckAdapter.DeckViewHolder> {

    protected DeckAdapter() {
        super(new DeckItemDiffCallback());
    }

    @NonNull
    @Override
    public DeckViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_deck, parent, false);
        return new DeckViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeckViewHolder holder, int position) {
        String deckItem = getItem(position);
        holder.bind(deckItem);
    }

    static class DeckViewHolder extends RecyclerView.ViewHolder {


        DeckViewHolder(@NonNull View itemView) {
            super(itemView);
           }

        void bind(String deckItem) {

            // You can bind other views or set click listeners if needed
        }
    }

    private static class DeckItemDiffCallback extends DiffUtil.ItemCallback<String> {
        @Override
        public boolean areItemsTheSame(@NonNull String oldItem, @NonNull String newItem) {
            return oldItem.equals(newItem);
        }

        @Override
        public boolean areContentsTheSame(@NonNull String oldItem, @NonNull String newItem) {
            return oldItem.equals(newItem);
        }
    }
}
