package com.ricardoangeles.mypuppy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.ricardoangeles.mypuppy.adapters.PetAdapter;
import com.ricardoangeles.mypuppy.model.Pet;
import com.ricardoangeles.mypuppy.presenter.FavoriteRecyclerViewPresenter;
import com.ricardoangeles.mypuppy.presenter.IRecyclerViewPresenter;
import com.ricardoangeles.mypuppy.view.IPetsRecyclerViewView;

import java.util.ArrayList;

public class LastRewardedActivity extends AppCompatActivity implements IPetsRecyclerViewView {

    private RecyclerView rvPets;
    private PetAdapter rvAdapter;
    private IRecyclerViewPresenter presenter;

    public void configure_toolbar()
    {
        Toolbar abActionBar = (Toolbar) findViewById(R.id.abActionBar);
        setSupportActionBar(abActionBar);
        getSupportActionBar().setIcon(R.drawable.icons8_huella_de_gato_24);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_rewarded);

        rvPets = (RecyclerView) findViewById(R.id.rvPets);
        presenter = new FavoriteRecyclerViewPresenter(this, this.getApplicationContext());
        configure_toolbar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public void setShowablePetList(ArrayList<Pet> pets) {
        rvAdapter.setPets(pets);
    }

    @Override
    public void showPetList() {
        rvAdapter.notifyDataSetChanged();
    }

    @Override
    public void configureRVAdapter() {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvPets.setLayoutManager(llm);
        rvAdapter = new PetAdapter(this, false, this);
        rvPets.setAdapter(rvAdapter);
    }

    @Override
    public void retrievePetList() {
        presenter.getPets();
    }

    @Override
    public void petGiveBonie(Pet pet) {

    }
}
