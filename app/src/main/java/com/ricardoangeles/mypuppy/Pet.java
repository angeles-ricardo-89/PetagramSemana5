package com.ricardoangeles.mypuppy;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class Pet implements Parcelable{

    private String name;
    private int bonies;//score or likes
    private int picture;


    public Pet(String name, int bonies, int picture) {
        this.name = name;
        this.bonies = bonies;
        this.picture = picture;
    }

    public Pet(Parcel in){
        this.name = in.readString();
        this.bonies = in.readInt();
        this.picture = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(bonies);
        dest.writeInt(picture);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Pet> CREATOR = new Creator<Pet>() {
        @Override
        public Pet createFromParcel(Parcel in) {
            return new Pet(in);
        }

        @Override
        public Pet[] newArray(int size) {
            return new Pet[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBonies() {
        return bonies;
    }

    public void setBonies(int bonnies) {
        this.bonies = bonnies;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pet)) return false;
        Pet pet = (Pet) o;
        return getPicture() == pet.getPicture() &&
                Objects.equals(getName(), pet.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPicture());
    }
}
