package com.example.pjarana.spinnergridview;

import android.graphics.YuvImage;

import java.util.ArrayList;

/**
 * Created by pjarana on 6/11/17.
 */

public class ListaPokemon {

    private ArrayList<Pokemon>listPokemon;
    private final String[]tipoFuego={"Fuego"};
    private final String[]tipoFuegoVolador={"Fuego","Volador"};
    private final String[]tipoPlantaVeneno={"Planta","Veneno"};
    private final String[]tipoAgua={"Agua"};
    private final String[]tipoSiniestroVolador={"Siniestro","Volador"};
    private final Pokemon charmander=new Pokemon(1,"Charmander",tipoFuego,R.drawable.charmander,R.raw.charmandergrito,"16");
    private final Pokemon charmeleon=new Pokemon(2,"Charmeleon",tipoFuego,R.drawable.charmeleon_icon,R.raw.charmeleongrito,"36");
    private final Pokemon charizard=new Pokemon(3,"Charizard",tipoFuegoVolador,R.drawable.charizard_icon,R.raw.charizardgrito,"No evoluciona");
    private final Pokemon bulbasaur=new Pokemon(4,"Bulbasaur",tipoPlantaVeneno,R.drawable.bullbasaur,R.raw.bulbasaurgrito,"16");
    private final Pokemon ivysaur=new Pokemon(5,"Ivysaur",tipoPlantaVeneno,R.drawable.ivysaur_icon,R.raw.ivysaurgrito,"32");
    private final Pokemon venasaur=new Pokemon(6,"Venusaur",tipoPlantaVeneno,R.drawable.venusaur_icon,R.raw.venasaurgrito,"No evoluciona");
    private final Pokemon squirtle=new Pokemon(7,"Squirtle",tipoAgua,R.drawable.squirtle,R.raw.squirtlegrito,"16");
    private final Pokemon wartortle=new Pokemon(8,"Wartortle",tipoAgua,R.drawable.wartortle_icon,R.raw.wartortlegrito,"36");
    private final Pokemon blastoise=new Pokemon(9,"Blastoise",tipoAgua,R.drawable.blastoise_icon,R.raw.blastoisegrito,"No evoluciona");
    private final Pokemon magmortar=new Pokemon(10,"Magmortar", tipoFuego,R.drawable.magmortar,R.raw.magmortargrito,"No evoluciona");
    private final Pokemon magmar=new Pokemon(11,"Magmar",tipoFuego,R.drawable.magmar,R.raw.magmargrito,"Intercambiando con magmatizador");
    private final Pokemon feebas=new Pokemon(12,"Feebas",tipoAgua,R.drawable.feebas,R.raw.feebasgrito,"Intercambiando con Escama Bella");
    private final Pokemon milotic=new Pokemon(13,"Milotic",tipoAgua,R.drawable.milotic,R.raw.miloticgrito,"No evoluciona");
    private final Pokemon yveltal=new Pokemon(14,"Yveltal", tipoSiniestroVolador,R.drawable.yveltal,R.raw.yveltalgrito,"No evoluciona");


    public ListaPokemon()
    {
        listPokemon.add(charmander);
        listPokemon.add(charmeleon);
        listPokemon.add(charizard);
        listPokemon.add(bulbasaur);
        listPokemon.add(ivysaur);
        listPokemon.add(venasaur);
        listPokemon.add(squirtle);
        listPokemon.add(wartortle);
        listPokemon.add(blastoise);
        listPokemon.add(magmar);
        listPokemon.add(magmortar);
        listPokemon.add(feebas);
        listPokemon.add(milotic);
        listPokemon.add(yveltal);
    }
    public ListaPokemon (ArrayList<Pokemon>lista)
    {
        this.listPokemon=lista;
    }
    public  ListaPokemon(ListaPokemon l)
    {
        this.listPokemon=l.listPokemon;
    }

    public ArrayList<Pokemon> getListPokemon()
    {
        return listPokemon;
    }
    public void deletePokemonsByType(String tipo)
    {
        for (int i=0;i<listPokemon.size();i++)
        {
            if(listPokemon.get(i).getTipo()[0].equals(tipo))
            {
                listPokemon.remove(i);
            }
        }
    }
    public void addPokemonsFuego()
    {
        listPokemon.add(charmander);
        listPokemon.add(charmeleon);
        listPokemon.add(charizard);
        listPokemon.add(magmar);
        listPokemon.add(magmortar);
    }
    public void addPokemonsAgua()
    {
        listPokemon.add(squirtle);
        listPokemon.add(wartortle);
        listPokemon.add(blastoise);
        listPokemon.add(feebas);
        listPokemon.add(milotic);
    }
    public void addPokemonsPlanta()
    {
        listPokemon.add(bulbasaur);
        listPokemon.add(ivysaur);
        listPokemon.add(venasaur);
    }
    public void addPokemonsOtros()
    {
        listPokemon.add(yveltal);
    }
    public boolean comprobarFaltaFuego()
    {
        boolean faltaFuego=true;
        for(int i=0;i<listPokemon.size()&&faltaFuego;i++)
        {
            if(listPokemon.get(i).getTipo()[0].equals("Fuego"))
            {
                faltaFuego=false;
            }
        }
        return faltaFuego;
    }
    public boolean comprobarFaltaAgua()
    {
        boolean faltaAgua=true;
        for(int i=0;i<listPokemon.size()&&faltaAgua;i++)
        {
            if(listPokemon.get(i).getTipo()[0].equals("Agua"))
            {
                faltaAgua=false;
            }
        }
        return faltaAgua;
    }
    public boolean comprobarFaltaPlanta()
    {
        boolean faltaPlanta=true;
        for(int i=0;i<listPokemon.size()&&faltaPlanta;i++)
        {
            if(listPokemon.get(i).getTipo()[0].equals("Planta"))
            {
                faltaPlanta=false;
            }
        }
        return faltaPlanta;
    }
    public boolean comprobarFaltaOtros()
    {
        boolean faltaOtros=true;
        for(int i=0;i<listPokemon.size()&&faltaOtros;i++)
        {
            if(listPokemon.get(i).getTipo()[0].equals("Planta"))
            {
                faltaOtros=false;
            }
        }
        return faltaOtros;
    }
}
