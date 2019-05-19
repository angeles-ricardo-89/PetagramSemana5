package com.ricardoangeles.mypuppy.presenter;

import android.content.Context;

import com.ricardoangeles.mypuppy.model.Pet;
import com.ricardoangeles.mypuppy.model.PetConstructor;
import com.ricardoangeles.mypuppy.view.IPetsRecyclerViewView;

import java.security.InvalidParameterException;
import java.util.ArrayList;

public class MainRecyclerViewPresenter implements IRecyclerViewPresenter {
    private IPetsRecyclerViewView iPetsRecyclerViewView;
    private Context context;
    private PetConstructor constructor;
    private ArrayList<Pet> pets;


    public MainRecyclerViewPresenter(IPetsRecyclerViewView iPetsRecyclerViewView, Context context) {
        this.iPetsRecyclerViewView = iPetsRecyclerViewView;
        this.context = context;
        viewConfigureRV();
        getPets();
        showPets();
    }

    @Override
    public void viewConfigureRV(){
        iPetsRecyclerViewView.configureRVAdapter();
    }

    @Override
    public void petGiveBonie(Pet pet) {
        PetConstructor petConstructor= new PetConstructor(context);
        petConstructor.registerBonieToPet(pet);
    }

    @Override
    public void getPets() {
        PetConstructor petConstructor= new PetConstructor(context);
        pets = petConstructor.getPets();
        iPetsRecyclerViewView.setShowablePetList(pets);
    }

    @Override
    public void showPets() {

        iPetsRecyclerViewView.showPetList();
    }
}
