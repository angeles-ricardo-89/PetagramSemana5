package com.ricardoangeles.mypuppy.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class AlbumPhoto implements Parcelable {
    int photo_source;

    public AlbumPhoto(Parcel in) {
        this.photo_source = in.readInt();
        this.bonies = in.readInt();
    }

    public int getBonies() {
        return bonies;
    }

    public void setBonies(int bonies) {
        this.bonies = bonies;
    }

    int bonies;
    public AlbumPhoto(int photo_source, int bonies) {
        this.photo_source = photo_source;
        this.bonies = bonies;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(photo_source);
        parcel.writeInt(bonies);
    }

    public int getPhoto_source() {
        return photo_source;
    }

    public void setPhoto_source(int photo_source) {
        this.photo_source = photo_source;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AlbumPhoto)) return false;
        AlbumPhoto that = (AlbumPhoto) o;
        return getBonies() == that.getBonies() &&
                Objects.equals(getPhoto_source(), that.getPhoto_source());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPhoto_source(), getBonies());
    }
}
