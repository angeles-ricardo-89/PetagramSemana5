package com.ricardoangeles.mypuppy.view;

import com.ricardoangeles.mypuppy.model.Pet;

import java.util.ArrayList;

public interface IPetsRecyclerViewView {
    public void setShowablePetList(ArrayList<Pet> pets);
    public void showPetList();
    public void configureRVAdapter();
    public void retrievePetList();
    public void petGiveBonie(Pet pet);
}
