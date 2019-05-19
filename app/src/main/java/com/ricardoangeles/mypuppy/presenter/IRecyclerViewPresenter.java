package com.ricardoangeles.mypuppy.presenter;

import com.ricardoangeles.mypuppy.model.Pet;

public interface IRecyclerViewPresenter {
    public void getPets();
    public void showPets();
    public void viewConfigureRV();
    public void petGiveBonie(Pet pet);
}
