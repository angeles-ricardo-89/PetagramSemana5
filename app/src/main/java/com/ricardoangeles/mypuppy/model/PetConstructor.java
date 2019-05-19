package com.ricardoangeles.mypuppy.model;

import android.content.Context;

import com.ricardoangeles.mypuppy.R;
import com.ricardoangeles.mypuppy.db.PetDatabase;

import java.util.ArrayList;

public class PetConstructor {
    private Context context;

    public PetConstructor(Context context){
        this.context = context;
    }

    public ArrayList<Pet> getPets(){
        PetDatabase db = new PetDatabase(context);
        insertSamplePets(db);
        return db.getPetsWithRank();
    }

    public void insertSamplePets(PetDatabase db){
        try {
            db.insertPet(new Pet(1, "Perro", 3, R.drawable.perro));
            db.insertPet(new Pet(2, "Rufus", 5, R.drawable.dumb_dog_colour));
            db.insertPet(new Pet(3, "Firulais", 10, R.drawable.gerald_g_black_lab));
            db.insertPet(new Pet(4, "Pulgas", 7, R.drawable.gerald_g_dog_simple_drawing_8));
            db.insertPet(new Pet(5, "Pecas", 8, R.drawable.machovka_dalmatin_head));
            db.insertPet(new Pet(6, "Negro", 0, R.drawable.descarga));
            db.insertPet(new Pet(7, "Rambo", 0, R.drawable.rambo));
            db.insertPet(new Pet(8, "Barbas", 0, R.drawable.barbas));
        } catch (Exception e){
            //already filled
        }
    }

    public void registerBonieToPet(Pet pet){
        PetDatabase db = new PetDatabase(context);
        db.registerPetBonie(pet);
    }

    public ArrayList<Pet> getMyFavoritePets(){
        PetDatabase db = new PetDatabase(context);
        return db.getPetsWithRank(true, 5);
    }

}
