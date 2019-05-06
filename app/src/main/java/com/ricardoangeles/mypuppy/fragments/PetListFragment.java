package com.ricardoangeles.mypuppy.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ricardoangeles.mypuppy.Pet;
import com.ricardoangeles.mypuppy.R;
import com.ricardoangeles.mypuppy.adapters.ActivityWithAdapter;
import com.ricardoangeles.mypuppy.adapters.PetAdapter;
import com.ricardoangeles.mypuppy.adapters.PetFavHistory;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PetListFragment extends Fragment implements ActivityWithAdapter {
    ArrayList<Pet> pets;
    private RecyclerView rvPets;

    void get_pet_list() {
        pets = new ArrayList<Pet>();

        pets.add(new Pet("Perro", 3, R.drawable.perro));
        pets.add(new Pet("Rufus", 5, R.drawable.dumb_dog_colour));
        pets.add(new Pet("Firulais", 10, R.drawable.gerald_g_black_lab));
        pets.add(new Pet("Pulgas", 7, R.drawable.gerald_g_dog_simple_drawing_8));
        pets.add(new Pet("Chanclas", 8, R.drawable.machovka_dalmatin_head));

    }

    public void setAdapter() {
        PetAdapter adapter = new PetAdapter(pets, getActivity(), true);
        rvPets.setAdapter(adapter);
    }
    public PetListFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_pet_list, container, false);
        rvPets = (RecyclerView) v.findViewById(R.id.rvPets);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvPets.setLayoutManager(llm);
        get_pet_list();
        setAdapter();
        return v;
    }

}
