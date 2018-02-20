package com.iesnervion.pjarana.pruebaantesexamen.DAO;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.provider.ContactsContract;

/**
 * Created by pjarana on 20/02/18.
 */
@android.arch.persistence.room.Database(entities = {Usuario.class},version = 1)
public abstract class Database extends RoomDatabase{

    public static Database INSTANCE;
    public abstract MyDAO getUsuariosDAO();
    public static Database getDatabase(final Context context)
    {
        if(INSTANCE==null)
        {
            synchronized (Database.class)
            {
                if(INSTANCE==null)
                {
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(),Database.class,"Videoteca").allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }
}
