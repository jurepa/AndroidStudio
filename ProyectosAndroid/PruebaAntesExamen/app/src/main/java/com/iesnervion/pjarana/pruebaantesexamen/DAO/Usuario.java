package com.iesnervion.pjarana.pruebaantesexamen.DAO;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pjarana on 20/02/18.
 */
@Entity(tableName = "Usuarios")
public class Usuario implements Parcelable {
    @ColumnInfo(name = "ID")
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nombre;
    public Usuario(int id,String nombre)
    {
        this.id=id;
        this.nombre=nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.nombre);
    }

    protected Usuario(Parcel in) {
        this.id = in.readInt();
        this.nombre = in.readString();
    }

    public static final Parcelable.Creator<Usuario> CREATOR = new Parcelable.Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel source) {
            return new Usuario(source);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };
}
