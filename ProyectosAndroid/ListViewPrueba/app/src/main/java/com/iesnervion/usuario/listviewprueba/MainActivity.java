package com.iesnervion.usuario.listviewprueba;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.onClick;


public class MainActivity extends AppCompatActivity
{
    String[]tipoFuego={"Fuego"};
    String[]tipoFuegoVolador={"Fuego","Volador"};
    String[]tipoPlantaVeneno={"Planta","Veneno"};
    String[]tipoAgua={"Agua"};
    Pokemon charmander=new Pokemon("Charmander",tipoFuego,R.drawable.charmander,R.raw.charmandergrito);
    Pokemon charmeleon=new Pokemon("Charmeleon",tipoFuego,R.drawable.charmeleon_icon,R.raw.charmeleongrito);
    Pokemon charizard=new Pokemon("Charizard",tipoFuegoVolador,R.drawable.charizard_icon,R.raw.charizardgrito);
    Pokemon bulbasaur=new Pokemon("Bulbasaur",tipoPlantaVeneno,R.drawable.bullbasaur,R.raw.bulbasaurgrito);
    Pokemon ivysaur=new Pokemon("Ivysaur",tipoPlantaVeneno,R.drawable.ivysaur_icon,R.raw.ivysaurgrito);
    Pokemon venasaur=new Pokemon("Venusaur",tipoPlantaVeneno,R.drawable.venusaur_icon,R.raw.venasaurgrito);
    Pokemon squirtle=new Pokemon("Squirtle",tipoAgua,R.drawable.squirtle,R.raw.squirtlegrito);
    Pokemon wartortle=new Pokemon("Wartortle",tipoAgua,R.drawable.wartortle_icon,R.raw.wartortlegrito);
    Pokemon blastoise=new Pokemon("Blastoise",tipoAgua,R.drawable.blastoise_icon,R.raw.blastoisegrito);
    private final Pokemon[]pokedex={charmander,charmeleon,charizard,squirtle,wartortle,blastoise,bulbasaur,ivysaur,venasaur};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lista=(ListView)findViewById(R.id.pokemon);
        final IconicAdapter <Pokemon> adapter=new IconicAdapter<Pokemon>(this,R.layout.estilo_lista, R.id.nombrePokemon, pokedex);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l)
            {
                Pokemon pokemon = adapter.getItem(posicion);

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


    }
    class IconicAdapter<T> extends ArrayAdapter<T>
    {
        public IconicAdapter(Context c, int resourceId, int textId, T[]objects)
        {
            super(c,resourceId,textId,objects);
        }
        /*public View getView(int posicion, View convertView, ViewGroup parent)
        {
            View fila = super.getView(posicion, convertView, parent);
            ViewHolder vh=new ViewHolder();
            fila.setTag(new ViewHolder());
            ImageView imagen=(ImageView)fila.findViewById(R.id.imagenPokemon);
            TextView nombre=(TextView)fila.findViewById(R.id.nombrePokemon);
            Pokemon pokemon=pokedex[posicion];
            imagen.setImageResource(pokemon.getIcono());
            nombre.setText(pokemon.getNombre());
            if(pokemon.getTipo()[0].equals("Fuego"))
            {
                nombre.setTextColor(Color.RED);
            }
            else if(pokemon.getTipo()[0].equals("Planta"))
            {
                nombre.setTextColor(Color.GREEN);
            }
            else
            {
                nombre.setTextColor(Color.BLUE);
            }
            return fila;
        }*/
        @Override
        public View getView(int pos, View convertView, ViewGroup parent)
        {
            ViewHolder vh;
            Pokemon pokemon;
            if (convertView==null)//El primer convertView siempre sera null, por lo queentramos aquí para inicializarlo
            {
                LayoutInflater li=getLayoutInflater(); //Creamos el layoutInflater
                convertView = li.inflate(R.layout.estilo_lista, parent, false); //Le decimos con qué estilo inflaremos la fila
                vh=new ViewHolder(convertView,R.id.nombrePokemon,R.id.imagenPokemon); //Creamos el ViewHolder e inicializamos sus atributos
                convertView.setTag(vh); //Le  decioms que convertview tendrá como  tag al viewHolder
            }
            else
            {
                vh=(ViewHolder)convertView.getTag(); //Para evitar llamar a findViewById, haremos que los "nuevos" viewHolder tengan el  mismo tag que el primero
            }

            pokemon=pokedex[pos];
            if (pokemon!=null)
            {
                vh.texto.setText(pokemon.getNombre());
                if(pokemon.getTipo()[0].equals("Fuego"))
                {
                    vh.texto.setTextColor(Color.RED);
                }
                else if(pokemon.getTipo()[0].equals("Planta"))
                {
                    vh.texto.setTextColor(Color.GREEN);
                }
                else
                {
                    vh.texto.setTextColor(Color.BLUE);
                }
                vh.icono.setImageResource(pokemon.getIcono());
                vh.texto.setTag(pokemon.hashCode());
            }
            return convertView;
        }
    }
    class ViewHolder //A su cosntructor le pasaremos el converView y los R.id.loquesea dependiendo lo q tengamos en el layout de la fila
    {
        TextView texto;
        ImageView icono;

        public ViewHolder(View row, int idtexto, int idicono)
        {
            this.texto=(TextView)row.findViewById(idtexto);
            this.icono=(ImageView)row.findViewById(idicono);
        }

        public TextView getTexto()
        {
            return texto;
        }
        public ImageView getIcono()
        {
            return icono;
        }
    }
}
