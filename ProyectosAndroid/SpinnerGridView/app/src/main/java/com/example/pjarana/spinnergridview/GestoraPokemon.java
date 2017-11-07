package com.example.pjarana.spinnergridview;

import java.util.ArrayList;

/**
 * Created by pjarana on 7/11/17.
 */

public class GestoraPokemon {

    public ArrayList<Pokemon> pokemonsFuego(ArrayList<Pokemon>pokedex)
    {
        ArrayList<Pokemon>pokemons=new ArrayList<Pokemon>();
        for(int i=0;i<pokedex.size();i++)
        {   if(pokedex.get(i).getTipo()[0].equals("Fuego"))
            {
                pokemons.add(pokedex.get(i));
            }
        }
        return pokemons;
    }
    public ArrayList<Pokemon> pokemonsPlanta(ArrayList<Pokemon>pokedex)
    {
        ArrayList<Pokemon>pokemons=new ArrayList<Pokemon>();
        for(int i=0;i<pokedex.size();i++)
        {   if(pokedex.get(i).getTipo()[0].equals("Planta"))
        {
            pokemons.add(pokedex.get(i));
        }
        }
        return pokemons;
    }
    public ArrayList<Pokemon> pokemonsAgua(ArrayList<Pokemon>pokedex)
    {
        ArrayList<Pokemon>pokemons=new ArrayList<Pokemon>();
        for(int i=0;i<pokedex.size();i++)
        {   if(pokedex.get(i).getTipo()[0].equals("Agua"))
        {
            pokemons.add(pokedex.get(i));
        }
        }
        return pokemons;
    }
    public ArrayList<Pokemon> pokemonsOtros(ArrayList<Pokemon>pokedex)
    {
        ArrayList<Pokemon>pokemons=new ArrayList<Pokemon>();
        for(int i=0;i<pokedex.size();i++)
        {
            if(!pokedex.get(i).getTipo()[0].equals("Fuego")&&!pokedex.get(i).getTipo()[0].equals("Agua")&&
                !pokedex.get(i).getTipo()[0].equals("Planta"))
            {
                pokemons.add(pokedex.get(i));
            }
        }
        return pokemons;
    }

}
