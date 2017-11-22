package com.example.pjarana.examenprimeraevaluacionpjarana;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pjarana on 22/11/17.
 */

public class Color implements Parcelable {

    private String color;
    private int foto;

    public Color() {
        this.color="";
        this.foto=0;
    }

    public Color(String color, int foto) {
        this.color = color;
        this.foto = foto;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.color);
        dest.writeInt(this.foto);
    }

    protected Color(Parcel in) {
        this.color = in.readString();
        this.foto = in.readInt();
    }

    public static final Parcelable.Creator<Color> CREATOR = new Parcelable.Creator<Color>() {
        @Override
        public Color createFromParcel(Parcel source) {
            return new Color(source);
        }

        @Override
        public Color[] newArray(int size) {
            return new Color[size];
        }
    };
}
