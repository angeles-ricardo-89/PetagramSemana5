package com.ricardoangeles.mypuppy.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ricardoangeles.mypuppy.AlbumPhoto;
import com.ricardoangeles.mypuppy.R;
import com.ricardoangeles.mypuppy.adapters.AlbumPhotoAdapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PetProfileFragment extends Fragment {

    ArrayList<AlbumPhoto> photos;
    private RecyclerView rvPhotos;

    void getPhotoList(){
        photos = new ArrayList<AlbumPhoto>();
        photos.add(new AlbumPhoto(R.drawable.solovino, 13));
        photos.add(new AlbumPhoto(R.drawable.solovino, 20));
        photos.add(new AlbumPhoto(R.drawable.solovino, 39));
        photos.add(new AlbumPhoto(R.drawable.solovino, 37));
        photos.add(new AlbumPhoto(R.drawable.solovino, 33));
        photos.add(new AlbumPhoto(R.drawable.solovino, 433));
        photos.add(new AlbumPhoto(R.drawable.solovino, 23));
        photos.add(new AlbumPhoto(R.drawable.solovino, 11));
        photos.add(new AlbumPhoto(R.drawable.solovino, 20));
        photos.add(new AlbumPhoto(R.drawable.solovino, 10));
        photos.add(new AlbumPhoto(R.drawable.solovino, 2));
    }

    void setAdapter(){
        AlbumPhotoAdapter adapter = new AlbumPhotoAdapter(photos, getActivity());
        rvPhotos.setAdapter(adapter);

    }

    public PetProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_pet_profile, container, false);
        rvPhotos = (RecyclerView) v.findViewById(R.id.rvPhotos);

        GridLayoutManager glManager = new GridLayoutManager(getActivity(), 3);
        rvPhotos.setLayoutManager(glManager);
        getPhotoList();
        setAdapter();

        return v;
    }

}
