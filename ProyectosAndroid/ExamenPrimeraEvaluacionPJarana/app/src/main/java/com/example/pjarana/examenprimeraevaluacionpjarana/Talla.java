package com.example.pjarana.examenprimeraevaluacionpjarana;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pjarana on 22/11/17.
 */

public class Talla implements Parcelable {

    private int talla;
    private Color[]colores;

    public Talla() {
        this.talla=0;
        this.colores=new Color[0];
    }

    public Talla(int talla, Color[] colores) {
        this.talla = talla;
        this.colores = colores;
    }

    public int getTalla() {
        return talla;
    }

    public void setTalla(int talla) {
        this.talla = talla;
    }

    public Color[] getColores() {
        return colores;
    }

    public void setColores(Color[] colores) {
        this.colores = colores;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.talla);
        dest.writeTypedArray(this.colores, flags);
    }

    protected Talla(Parcel in) {
        this.talla = in.readInt();
        this.colores = in.createTypedArray(Color.CREATOR);
    }

    public static final Parcelable.Creator<Talla> CREATOR = new Parcelable.Creator<Talla>() {
        @Override
        public Talla createFromParcel(Parcel source) {
            return new Talla(source);
        }

        @Override
        public Talla[] newArray(int size) {
            return new Talla[size];
        }
    };
}
