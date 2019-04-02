package com.ricardoangeles.mypuppy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.support.v7.widget.Toolbar;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ActivityWithAdapter, PetFavHistory {
    ArrayList<Pet> pets;
    private RecyclerView rvPets;
    private ArrayList<Pet> favPets;

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.act_menu, menu);

        MenuItem actionMenuItem = menu.findItem(R.id.action_fav_history);
        actionMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent(MainActivity.this, LastRewardedActivity.class);
                intent.putParcelableArrayListExtra("favoritos", favPets);
                startActivity(intent);
                return false;
            }
        });

        return true;
    }

    void get_pet_list(){
        pets = new ArrayList<Pet>();

        pets.add(new Pet("Perro", 3, R.drawable.perro));
        pets.add(new Pet("Rufus", 5, R.drawable.dumb_dog_colour));
        pets.add(new Pet("Firulais", 10, R.drawable.gerald_g_black_lab));
        pets.add(new Pet("Pulgas", 7, R.drawable.gerald_g_dog_simple_drawing_8));
        pets.add(new Pet("Chanclas",8, R.drawable.machovka_dalmatin_head));

    }

    public void setAdapter(){
        PetAdapter adapter = new PetAdapter(pets, this, true);
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
        setContentView(R.layout.activity_main);
        rvPets = (RecyclerView) findViewById(R.id.rvPets);
        favPets = new ArrayList<Pet>();
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvPets.setLayoutManager(llm);
        get_pet_list();
        setAdapter();
        configure_toolbar();





    }

    @Override
    public void pushFavPet(Pet pet) {
        int count;
        count = favPets.size();
        if(count == 0){
            favPets.add(pet);
        } else if(favPets.get(count - 1) != pet){
            favPets.add(pet);
        }

        if(favPets.size() > 5){
            favPets.remove(0);
        }


    }
}
