package com.example.pjarana.spinnergridview;
//Si elijo pokemons de tipo fuego, crear un array en tiempo de ejecucion que recorra la lista entera de pokemons en introducimos en el los pokemons de tipo fuego;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {


    String[]tiposSpinner={"Todos","Fuego","Agua","Planta","Otros"};
    String[]tipoFuego={"Fuego"};
    String[]tipoFuegoVolador={"Fuego","Volador"};
    String[]tipoPlantaVeneno={"Planta","Veneno"};
    String[]tipoAgua={"Agua"};
    String[]tipoSiniestroVolador={"Siniestro","Volador"};
    Pokemon charmander=new Pokemon(1,"Charmander",tipoFuego,R.drawable.charmander,R.raw.charmandergrito,"16");
    Pokemon charmeleon=new Pokemon(2,"Charmeleon",tipoFuego,R.drawable.charmeleon_icon,R.raw.charmeleongrito,"36");
    Pokemon charizard=new Pokemon(3,"Charizard",tipoFuegoVolador,R.drawable.charizard_icon,R.raw.charizardgrito,"No evoluciona");
    Pokemon bulbasaur=new Pokemon(4,"Bulbasaur",tipoPlantaVeneno,R.drawable.bullbasaur,R.raw.bulbasaurgrito,"16");
    Pokemon ivysaur=new Pokemon(5,"Ivysaur",tipoPlantaVeneno,R.drawable.ivysaur_icon,R.raw.ivysaurgrito,"32");
    Pokemon venasaur=new Pokemon(6,"Venusaur",tipoPlantaVeneno,R.drawable.venusaur_icon,R.raw.venasaurgrito,"No evoluciona");
    Pokemon squirtle=new Pokemon(7,"Squirtle",tipoAgua,R.drawable.squirtle,R.raw.squirtlegrito,"16");
    Pokemon wartortle=new Pokemon(8,"Wartortle",tipoAgua,R.drawable.wartortle_icon,R.raw.wartortlegrito,"36");
    Pokemon blastoise=new Pokemon(9,"Blastoise",tipoAgua,R.drawable.blastoise_icon,R.raw.blastoisegrito,"No evoluciona");
    Pokemon magmortar=new Pokemon(10,"Magmortar", tipoFuego,R.drawable.magmortar,R.raw.magmortargrito,"No evoluciona");
    Pokemon magmar=new Pokemon(11,"Magmar",tipoFuego,R.drawable.magmar,R.raw.magmargrito,"Intercambiando con magmatizador");
    Pokemon feebas=new Pokemon(12,"Feebas",tipoAgua,R.drawable.feebas,R.raw.feebasgrito,"Intercambiando con Escama Bella");
    Pokemon milotic=new Pokemon(13,"Milotic",tipoAgua,R.drawable.milotic,R.raw.miloticgrito,"No evoluciona");
    Pokemon yveltal=new Pokemon(14,"Yveltal", tipoSiniestroVolador,R.drawable.yveltal,R.raw.yveltalgrito,"No evoluciona");
    ArrayList<Pokemon>pokedex=new ArrayList<Pokemon>();
    GridView grid;
    Spinner spinner;
    MyAdapter<Pokemon>gridAdapter;
    GestoraPokemon gp;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pokedex.add(charmander);
        pokedex.add(charmeleon);
        pokedex.add(charizard);
        pokedex.add(squirtle);
        pokedex.add(wartortle);
        pokedex.add(blastoise);
        pokedex.add(bulbasaur);
        pokedex.add(ivysaur);
        pokedex.add(venasaur);
        pokedex.add(magmar);
        pokedex.add(magmortar);
        pokedex.add(feebas);
        pokedex.add(milotic);
        pokedex.add(yveltal);
        gp=new GestoraPokemon();
        tv=(TextView)findViewById(R.id.textView);
        grid=(GridView)findViewById(R.id.grid);
        spinner=(Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String>spinnerAdapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_item,R.id.customSpinnerItemTextView,tiposSpinner);
        spinner.setAdapter(spinnerAdapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l)
            {
                Pokemon pokemon = (Pokemon) adapterView.getAdapter().getItem(pos);
                MediaPlayer mp1= MediaPlayer.create(getApplicationContext(),pokemon.getSonido());
                mp1.start();
                if(pokemon.getTipo().length==2)
                {
                    Toast.makeText(getApplicationContext(),"Tipo "+pokemon.getTipo()[0]+" y "+pokemon.getTipo()[1], Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Tipo "+pokemon.getTipo()[0], Toast.LENGTH_SHORT).show();
                }
            }
        });

        grid.setAdapter(gridAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(tiposSpinner[position])
                {
                    case "Todos":
                        gridAdapter=new MyAdapter<Pokemon>(getApplicationContext(),pokedex);
                        grid.setAdapter(gridAdapter);
                        break;
                    case "Fuego":
                        gridAdapter=new MyAdapter<Pokemon>(getApplicationContext(),gp.pokemonsFuego(pokedex));
                        grid.setAdapter(gridAdapter);
                        break;
                    case "Agua":
                        gridAdapter=new MyAdapter<Pokemon>(getApplicationContext(),gp.pokemonsAgua(pokedex));
                        grid.setAdapter(gridAdapter);
                        break;
                    case "Planta":
                        gridAdapter=new MyAdapter<Pokemon>(getApplicationContext(),gp.pokemonsPlanta(pokedex));
                        grid.setAdapter(gridAdapter);
                        break;
                    case "Otros":
                        gridAdapter=new MyAdapter<Pokemon>(getApplicationContext(),gp.pokemonsOtros(pokedex));
                        grid.setAdapter(gridAdapter);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                tv.setTextColor(Color.RED);
            }
        });
    }
}
