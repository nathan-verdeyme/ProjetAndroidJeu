package com.example.projectjeu.ui.deck;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Collections;
import java.util.List;

public class DeckViewModel extends ViewModel {

    private final MutableLiveData<List<String>> deckItems;

    public DeckViewModel() {
        deckItems = new MutableLiveData<>();
        deckItems.setValue(Collections.singletonList("This is home fragment"));
    }

    public LiveData<List<String>> getDeckItems() {
        return deckItems;
    }
}