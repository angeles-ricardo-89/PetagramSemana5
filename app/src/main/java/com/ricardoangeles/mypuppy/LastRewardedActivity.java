package com.ricardoangeles.mypuppy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.ricardoangeles.mypuppy.adapters.ActivityWithAdapter;
import com.ricardoangeles.mypuppy.adapters.PetAdapter;

import java.util.ArrayList;

public class LastRewardedActivity extends AppCompatActivity implements ActivityWithAdapter {

    private ArrayList<Pet> rewardedPets;
    private RecyclerView rvPets;

    public void setAdapter(){
        PetAdapter adapter = new PetAdapter(rewardedPets, this, false);
        rvPets.setAdapter(adapter);
    }

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

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvPets.setLayoutManager(llm);
        //get_pet_list();
        rewardedPets = getIntent().getParcelableArrayListExtra("favoritos");
        setAdapter();
        configure_toolbar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
