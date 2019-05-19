package com.ricardoangeles.mypuppy.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ricardoangeles.mypuppy.model.Pet;
import com.ricardoangeles.mypuppy.R;
import com.ricardoangeles.mypuppy.view.IPetsRecyclerViewView;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.PetViewHolder> {

    public PetAdapter(Activity activity, boolean visibleLikeButton, IPetsRecyclerViewView iPetsRecyclerViewView){
        this.activity = activity;

        try{
            this.iPetsRecyclerViewView = iPetsRecyclerViewView;
        } catch (Exception e){
            Log.e(TAG, "PetAdapter: "  );
        }
        this.visibleLikeButton = visibleLikeButton;
    }

    public void setPets(ArrayList<Pet> pets) {
        this.pets = pets;
    }

    private ArrayList<Pet> pets;
    private Activity activity;
    private IPetsRecyclerViewView iPetsRecyclerViewView;
    private boolean visibleLikeButton;


    @NonNull
    @Override
    public PetViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_pet,
                                     viewGroup,
                         false);

        return new PetViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PetViewHolder petViewHolder, final int position) {
        final Pet pet = pets.get(position);
        petViewHolder.tvPetName.setText(pet.getName());
        petViewHolder.tvBonies.setText(Integer.toString(pet.getBonies()));
        petViewHolder.ivPetPicture.setImageResource(pet.getPicture());

        if(visibleLikeButton){
            petViewHolder.ibGiveABonie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(activity, "Le diste un huesito a " + pet.getName(),
                            Toast.LENGTH_SHORT).show();
                    //my_act.setAdapter(); este fue mi primer intento para actualizar el recyclerview
                    //pero sin duda fue mejor usar notifyItemChanged
                    iPetsRecyclerViewView.petGiveBonie(pet);
                    iPetsRecyclerViewView.retrievePetList();
                    notifyItemChanged(position);
                }
            });
        } else {
            petViewHolder.ibGiveABonie.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return pets.size();
    }

    public static class PetViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivPetPicture;
        private ImageButton ibGiveABonie;
        private TextView tvPetName;
        private TextView tvBonies;


        public PetViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPetPicture = (ImageView) itemView.findViewById(R.id.ivPetPicture);
            ibGiveABonie = (ImageButton) itemView.findViewById(R.id.ibGiveABonie);
            tvPetName = (TextView) itemView.findViewById(R.id.tvPetName);
            tvBonies = (TextView) itemView.findViewById(R.id.tvBonies);



        }
    }
}
