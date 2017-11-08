package com.example.pjarana.nbagridview;

import java.util.ArrayList;

/**
 * Created by pjarana on 8/11/17.
 */

public class GestoraListNBA
{
    public boolean comprobarNombre(String nombreEquipo, ArrayList<EquipoNBA>equipos)
    {
        boolean encontrado=false;
        for(int i=0;i<equipos.size()&&!encontrado;i++)
        {
            if(nombreEquipo.equals(equipos.get(i).getNombre()))
            {
                encontrado=true;
            }
        }
        return encontrado;
    }

}
