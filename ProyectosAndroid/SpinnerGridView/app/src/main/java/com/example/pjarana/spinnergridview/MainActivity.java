package com.example.pjarana.spinnergridview;
//Si elijo pokemons de tipo fuego, crear un array en tiempo de ejecucion que recorra la lista entera de pokemons en introducimos en el los pokemons de tipo fuego;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {


    String[]tiposSpinner={"Todos","Fuego","Agua","Planta","Otros"};
    GridView grid;
    Spinner spinner;
    ListaPokemon listaPokemon=new ListaPokemon();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        grid=(GridView)findViewById(R.id.grid);
        spinner=(Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String>spinnerAdapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,tiposSpinner);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        final ArrayAdapter<Pokemon>gridAdapter=new ArrayAdapter<Pokemon>(getApplicationContext(),android.R.layout.simple_list_item_1,listaPokemon.getListPokemon());

        grid.setAdapter(gridAdapter);
        spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                switch(tiposSpinner[position])
                {
                    case "Todos":

                        if(listaPokemon.comprobarFaltaAgua())
                        {
                            listaPokemon.addPokemonsAgua();
                        }
                        if(listaPokemon.comprobarFaltaFuego())
                        {
                            listaPokemon.addPokemonsFuego();
                        }
                        if(listaPokemon.comprobarFaltaPlanta())
                        {
                            listaPokemon.addPokemonsPlanta();
                        }
                        if(listaPokemon.comprobarFaltaOtros())
                        {
                            listaPokemon.addPokemonsOtros();
                        }
                        listaPokemon.getListPokemon().sort(new Comparator<Pokemon>() {
                            @Override
                            public int compare(Pokemon p1, Pokemon p2) {
                                int compare=0;
                                if (p1.getId()>p2.getId())
                                {
                                    compare=1;
                                }
                                else if(p1.getId()<p2.getId()) {
                                    compare = -1;
                                }
                                return compare;
                            }
                        });
                        gridAdapter.notifyDataSetChanged();
                        break;
                    case "Fuego":
                        break;
                    case "Agua":
                        break;
                    case "Planta":
                        break;
                    case "Otros":
                        break;
                }

            }
        });
    }
}
