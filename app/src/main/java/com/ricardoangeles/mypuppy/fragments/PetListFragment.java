package com.ricardoangeles.mypuppy.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ricardoangeles.mypuppy.model.Pet;
import com.ricardoangeles.mypuppy.R;
import com.ricardoangeles.mypuppy.adapters.PetAdapter;
import com.ricardoangeles.mypuppy.presenter.IRecyclerViewPresenter;
import com.ricardoangeles.mypuppy.presenter.MainRecyclerViewPresenter;
import com.ricardoangeles.mypuppy.view.IPetsRecyclerViewView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PetListFragment extends Fragment implements IPetsRecyclerViewView {
    private RecyclerView rvPets;
    private IRecyclerViewPresenter presenter;
    private PetAdapter rvadapter;

    public PetListFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_pet_list, container, false);
        rvPets = (RecyclerView) v.findViewById(R.id.rvPets);

        presenter = new MainRecyclerViewPresenter(this, getContext());


        return v;
    }

    @Override
    public void setShowablePetList(ArrayList<Pet> pets) {
        rvadapter.setPets(pets);
    }

    @Override
    public void showPetList() {
        rvadapter.notifyDataSetChanged();
    }

    @Override
    public void configureRVAdapter() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvPets.setLayoutManager(llm);
        rvadapter = new PetAdapter(getActivity(), true, this);
        rvPets.setAdapter(rvadapter);

    }

    @Override
    public void retrievePetList() {
        presenter.getPets();
    }

    @Override
    public void petGiveBonie(Pet pet) {
        presenter.petGiveBonie(pet);
    }
}
