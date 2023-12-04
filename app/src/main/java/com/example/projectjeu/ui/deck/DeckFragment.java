package com.example.projectjeu.ui.deck;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectjeu.R;
import com.example.projectjeu.databinding.FragmentDeckBinding;

import java.util.List;

public class DeckFragment extends Fragment {

    private FragmentDeckBinding binding;
    private DeckViewModel deckViewModel;
    private DeckAdapter deckAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDeckBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        deckViewModel = new ViewModelProvider(this).get(DeckViewModel.class);

        RecyclerView recyclerView = root.findViewById(R.id.deckList);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Initialize the DeckAdapter with an empty list initially
        deckAdapter = new DeckAdapter();
        recyclerView.setAdapter(deckAdapter);

        // Observe changes to the deck items in the ViewModel
        deckViewModel.getDeckItems().observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> deckItems) {
                // Update the RecyclerView adapter with the new deck items
                deckAdapter.submitList(deckItems);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
