package com.example.pjarana.nbagridview;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pjarana on 8/11/17.
 */

public class EquipoNBA implements Parcelable {

    private String nombre;
    private int logo;

    public EquipoNBA()
    {
        this.nombre="";
        this.logo=0;
    }

    public EquipoNBA(String nombre, int logo)
    {
        this.nombre = nombre;
        this.logo = logo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nombre);
        dest.writeInt(this.logo);
    }

    protected EquipoNBA(Parcel in) {
        this.nombre = in.readString();
        this.logo = in.readInt();
    }

    public static final Parcelable.Creator<EquipoNBA> CREATOR = new Parcelable.Creator<EquipoNBA>() {
        @Override
        public EquipoNBA createFromParcel(Parcel source) {
            return new EquipoNBA(source);
        }

        @Override
        public EquipoNBA[] newArray(int size) {
            return new EquipoNBA[size];
        }
    };
}
