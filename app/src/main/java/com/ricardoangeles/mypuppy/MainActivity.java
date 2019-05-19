package com.ricardoangeles.mypuppy;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v7.widget.Toolbar;


import com.ricardoangeles.mypuppy.adapters.PageAdapter;
import com.ricardoangeles.mypuppy.fragments.PetListFragment;
import com.ricardoangeles.mypuppy.fragments.PetProfileFragment;
import com.ricardoangeles.mypuppy.model.Pet;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {
    private Toolbar toolbar;
    private TabLayout mainTabLayout;
    private ViewPager mainPager;
    private ArrayList<Pet> favPets;


    public boolean onCreateOptionsMenu(Menu menu) {
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

        MenuItem actionContactItem = menu.findItem(R.id.act_contact);
        actionContactItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent(MainActivity.this, ContactDeveloperActivity.class);

                startActivity(intent);
                return false;
            }
        });

        MenuItem actionAboutItem = menu.findItem(R.id.act_about);
        actionAboutItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);

                return false;
            }
        });

        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

    }



    public void configure_toolbar() {
        Toolbar abActionBar = (Toolbar) findViewById(R.id.abActionBar);
        setSupportActionBar(abActionBar);
        getSupportActionBar().setIcon(R.drawable.icons8_huella_de_gato_24);
    }

    private ArrayList<Fragment> addFragments(){
        ArrayList<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(new PetListFragment());
        fragments.add(new PetProfileFragment());

        return fragments;
    }

    public void setupViewPager()
    {
        mainPager.setAdapter(new PageAdapter(getSupportFragmentManager(), addFragments()));
        mainTabLayout.setupWithViewPager(mainPager);
        mainTabLayout.getTabAt(0).setIcon(R.drawable.iconfinder_05_home_106243);
        mainTabLayout.getTabAt(1).setIcon(R.drawable.iconfinder_dog_pet_puppy_canine_animal_4536075);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainTabLayout = (TabLayout) findViewById(R.id.tlMainTab);
        mainPager = (ViewPager) findViewById(R.id.vpMyPager);
        favPets = new ArrayList<Pet>();
        setupViewPager();
        configure_toolbar();


    }





}
