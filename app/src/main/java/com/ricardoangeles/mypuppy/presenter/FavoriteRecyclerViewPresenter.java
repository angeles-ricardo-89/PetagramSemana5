package com.ricardoangeles.mypuppy.presenter;

import android.content.Context;

import com.ricardoangeles.mypuppy.model.Pet;
import com.ricardoangeles.mypuppy.model.PetConstructor;
import com.ricardoangeles.mypuppy.view.IPetsRecyclerViewView;

import java.util.ArrayList;

public class FavoriteRecyclerViewPresenter implements IRecyclerViewPresenter {
    private IPetsRecyclerViewView iPetsRecyclerViewView;
    private Context context;
    private PetConstructor constructor;
    private ArrayList<Pet> pets;

    public FavoriteRecyclerViewPresenter(IPetsRecyclerViewView iPetsRecyclerViewView, Context context) {
        this.iPetsRecyclerViewView = iPetsRecyclerViewView;
        this.context = context;
        viewConfigureRV();
        getPets();
        showPets();
    }

    @Override
    public void getPets() {
        PetConstructor petConstructor= new PetConstructor(context);
        pets = petConstructor.getMyFavoritePets();
        iPetsRecyclerViewView.setShowablePetList(pets);
    }

    @Override
    public void showPets() {
        iPetsRecyclerViewView.showPetList();
    }

    @Override
    public void viewConfigureRV() {
        iPetsRecyclerViewView.configureRVAdapter();
    }

    @Override
    public void petGiveBonie(Pet pet) {

    }
}
