package com.example.pjarana.ej1bol7;
//Usar notifydatasetchanged, solo un array que se actualiza !!!!!!!!!!!!!!!!!!!

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class FilterListActivity extends AppCompatActivity {

    String[]tipoFuego={"Fuego"};
    String[]tipoFuegoVolador={"Fuego","Volador"};
    String[]tipoPlantaVeneno={"Planta","Veneno"};
    String[]tipoAgua={"Agua"};
    String[]tipoSiniestroVolador={"Siniestro","Volador"};
    Pokemon charmander=new Pokemon("Charmander",tipoFuego,R.drawable.charmander,R.raw.charmandergrito,"16");
    Pokemon charmeleon=new Pokemon("Charmeleon",tipoFuego,R.drawable.charmeleon_icon,R.raw.charmeleongrito,"36");
    Pokemon charizard=new Pokemon("Charizard",tipoFuegoVolador,R.drawable.charizard_icon,R.raw.charizardgrito,"No evoluciona");
    Pokemon bulbasaur=new Pokemon("Bulbasaur",tipoPlantaVeneno,R.drawable.bullbasaur,R.raw.bulbasaurgrito,"16");
    Pokemon ivysaur=new Pokemon("Ivysaur",tipoPlantaVeneno,R.drawable.ivysaur_icon,R.raw.ivysaurgrito,"32");
    Pokemon venasaur=new Pokemon("Venusaur",tipoPlantaVeneno,R.drawable.venusaur_icon,R.raw.venasaurgrito,"No evoluciona");
    Pokemon squirtle=new Pokemon("Squirtle",tipoAgua,R.drawable.squirtle,R.raw.squirtlegrito,"16");
    Pokemon wartortle=new Pokemon("Wartortle",tipoAgua,R.drawable.wartortle_icon,R.raw.wartortlegrito,"36");
    Pokemon blastoise=new Pokemon("Blastoise",tipoAgua,R.drawable.blastoise_icon,R.raw.blastoisegrito,"No evoluciona");
    Pokemon magmortar=new Pokemon("Magmortar", tipoFuego,R.drawable.magmortar,R.raw.magmortargrito,"No evoluciona");
    Pokemon magmar=new Pokemon("Magmar",tipoFuego,R.drawable.magmar,R.raw.magmargrito,"Intercambiando con magmatizador");
    Pokemon feebas=new Pokemon("Feebas",tipoAgua,R.drawable.feebas,R.raw.feebasgrito,"Intercambiando con Escama Bella");
    Pokemon milotic=new Pokemon("Milotic",tipoAgua,R.drawable.milotic,R.raw.miloticgrito,"No evoluciona");
    Pokemon yveltal=new Pokemon("Yveltal", tipoSiniestroVolador,R.drawable.yveltal,R.raw.yveltalgrito,"No evoluciona");
    String[]tiposSpinner={"Todos","Fuego","Agua","Planta","Otros"};
    private final Pokemon[]pokedex={charmander,charmeleon,charizard,squirtle,wartortle,blastoise,bulbasaur,ivysaur,venasaur,magmar,magmortar,feebas,milotic,yveltal};
    private final Pokemon[]pokemonsFuego={charmander,charmeleon,charizard,magmar,magmortar};
    private final Pokemon[]pokemonsAgua={squirtle,wartortle,blastoise,feebas,milotic};
    private final Pokemon[]pokemonsPlanta={bulbasaur,ivysaur,venasaur};
    private final Pokemon[]Otros={yveltal};
    ListView lista;
    Spinner spinner;
    MyAdapter<Pokemon>adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filterlist);
        final TextView textView=(TextView)findViewById(R.id.texto);
        spinner= (Spinner)findViewById(R.id.spinner);
        lista=(ListView)findViewById(R.id.lista);
        ArrayAdapter<String>tipos=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,tiposSpinner);
        tipos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(tipos);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l)
            {
                switch(tiposSpinner[pos])
                {
                    case "Todos":
                            adapter=new MyAdapter<Pokemon>(getApplicationContext(),R.layout.filatipofuego,pokedex);
                            lista.setAdapter(adapter);

                        break;
                    case "Fuego":
                            adapter=new MyAdapter<Pokemon>(getApplicationContext(),R.layout.filatipofuego,pokemonsFuego);
                            lista.setAdapter(adapter);
                        break;
                    case "Agua":
                            adapter=new MyAdapter<Pokemon>(getApplicationContext(),R.layout.filatipoagua,pokemonsAgua);
                            lista.setAdapter(adapter);
                            break;
                    case "Planta":
                            adapter=new MyAdapter<Pokemon>(getApplicationContext(),R.layout.filatipoplanta,pokemonsPlanta);
                            lista.setAdapter(adapter);
                            break;
                    case "Otros":
                            adapter=new MyAdapter<Pokemon>(getApplicationContext(),R.layout.filatipofuego,Otros);
                            lista.setAdapter(adapter);
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                textView.setTextColor(Color.RED);
            }
        });
    }


}
