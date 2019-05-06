package com.ricardoangeles.mypuppy.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ricardoangeles.mypuppy.AlbumPhoto;
import com.ricardoangeles.mypuppy.R;

import java.util.ArrayList;

public class AlbumPhotoAdapter extends RecyclerView.Adapter<AlbumPhotoAdapter.AlbumPhotoViewHolder> {

    ArrayList<AlbumPhoto> photos;
    Activity parentActivity;

    public AlbumPhotoAdapter(ArrayList<AlbumPhoto> photos, Activity activity) {
        this.photos = photos;
        this.parentActivity = activity;
    }

    @NonNull
    @Override
    public AlbumPhotoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_photo,
                                                                viewGroup, false);
        return new AlbumPhotoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumPhotoViewHolder albumPhotoViewHolder, int i) {
        final AlbumPhoto photo = photos.get(i);
        albumPhotoViewHolder.tvBonies.setText(Integer.toString(photo.getBonies()));
        albumPhotoViewHolder.ivPetPicture.setImageResource(photo.getPhoto_source());
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public static class AlbumPhotoViewHolder extends RecyclerView.ViewHolder{

        TextView tvBonies;
        ImageView ivPetPicture;

        public AlbumPhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvBonies = (TextView) itemView.findViewById(R.id.tvBonies);
            ivPetPicture = (ImageView) itemView.findViewById(R.id.ivPetPicture);
        }
    }
}
