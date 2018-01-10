package com.example.pjarana.pruebaroom;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created by pjarana on 10/01/18.
 */
@android.arch.persistence.room.Database(entities = {Persona.class,Mascota.class},version = 2)
public abstract class Database extends RoomDatabase {

    public static Database INSTANCE;
    public abstract MyDao getUsuariosDao();
    public final static Migration MIGRATION1_2=new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database)
        {
            database.execSQL("DROP TABLE Mascotas;");
            database.execSQL("CREATE TABLE Mascotas" +
                    "( id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "  nombre TEXT," +
                    "  idPersona INTEGER NOT NULL," +
                    " FOREIGN KEY(idPersona) REFERENCES Personas(id) " +
                    ");");
        }
    };

    public static Database getDatabase(final Context context)
    {
        if(INSTANCE==null)
        {
            synchronized (Database.class)
            {
                if(INSTANCE==null)
                {
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(),Database.class,"Mascoteria").allowMainThreadQueries().addMigrations(MIGRATION1_2).build();
                }
            }
        }
       return INSTANCE;
    }
}
