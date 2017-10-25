package com.iesnervion.usuario.listviewprueba;

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


public class MainActivity extends AppCompatActivity
{
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
    private final Pokemon[]pokedex={charmander,charizard,squirtle,venasaur,wartortle,charmeleon,blastoise,bulbasaur,ivysaur,magmar,magmortar,venasaur,feebas,milotic,yveltal};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lista=(ListView)findViewById(R.id.pokemon);
        final IconicAdapter <Pokemon> adapter=new IconicAdapter<Pokemon>(this,R.layout.filatipofuego, R.id.nombrePokemon, pokedex);
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
        @Override
        public int getViewTypeCount() //Devuelve el número de tipo de filas
        {
            return 3;
        }
        @Override
        public  int getItemViewType(int pos) //Devuelve un número que indica el tipo de fila
        {
            Pokemon pokemon=pokedex[pos];
            int view;
            if(pokemon.getTipo()[0].equals("Fuego"))
            {
                view=0;
            }
            else if(pokemon.getTipo()[0].equals("Agua"))
            {
                view=1;
            }
            else
            {
                view=2;
            }
            return view;
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
            ViewHolderFuego vhFuego=null; //Un ViewHolder por cada tipo de fila
            ViewHolderAgua vhAgua=null;
            ViewHolderPlanta vhPlanta=null;
            Pokemon pokemon;
            int rowType=getItemViewType(pos);
            if (convertView==null)//El primer convertView siempre sera null, por lo que entramos aquí para inicializarlo e inflarlo
            {
                LayoutInflater li=getLayoutInflater(); //Creamos el layoutInflater
                switch(rowType) //Dependiendo el tipo de fila lo inflamos con un tipo de fila u otra
                {
                    case 0:
                        convertView = li.inflate(R.layout.filatipofuego, parent, false);
                        vhFuego=new ViewHolderFuego(convertView); //Creamos el ViewHolder e inicializamos sus atributos
                        convertView.setTag(vhFuego); //Le  decioms que convertview tendrá como  tag al viewHolder
                        break;
                    case 1:
                        convertView = li.inflate(R.layout.filatipoagua, parent, false);
                        vhAgua=new ViewHolderAgua(convertView);
                        convertView.setTag(vhAgua);
                        break;
                    case 2:
                        convertView=li.inflate(R.layout.filatipoplanta,parent,false);
                        vhPlanta=new ViewHolderPlanta(convertView);
                        convertView.setTag(vhPlanta);
                        break;
                }
            }
            else
            {
                switch (rowType)
                {
                    case 0:
                        vhFuego = (ViewHolderFuego) convertView.getTag(); //Para evitar llamar a findViewById, haremos que los "nuevos" viewHolder tengan el  mismo tag que el primero
                        break;
                    case 1:
                        vhAgua = (ViewHolderAgua) convertView.getTag();
                        break;
                    case 2:
                        vhPlanta=(ViewHolderPlanta)convertView.getTag();
                        break;
                }
            }

            pokemon=pokedex[pos];
            if (pokemon!=null)
            {
                switch(rowType) {
                    case 0:
                        vhFuego.nombre.setText("Nombre: " + pokemon.getNombre());
                        vhFuego.nombre.setTextColor(Color.RED);
                        vhFuego.nivelEvolucion.setText("Nivel de evolución: " + pokemon.getNivelEvolucion());
                        if (pokemon.getTipo().length == 2) {
                            vhFuego.tipo.setText("Tipos: " + pokemon.getTipo()[0] + " y " + pokemon.getTipo()[1]);
                        } else {
                            vhFuego.tipo.setText("Tipo: " + pokemon.getTipo()[0]);
                        }
                        vhFuego.icono.setImageResource(pokemon.getIcono());
                        vhFuego.nombre.setTag(pokemon.hashCode());
                        break;
                    case 1:
                        vhAgua.nombre.setText("Nombre: " + pokemon.getNombre());
                        vhAgua.nombre.setTextColor(Color.BLUE);
                        vhAgua.nivelEvolucion.setText("Nivel de evolución: " + pokemon.getNivelEvolucion());
                        if (pokemon.getTipo().length == 2) {
                            vhAgua.tipo.setText("Tipos: " + pokemon.getTipo()[0] + " y " + pokemon.getTipo()[1]);
                        } else {
                            vhAgua.tipo.setText("Tipo: " + pokemon.getTipo()[0]);
                        }
                        vhAgua.icono.setImageResource(pokemon.getIcono());
                        vhAgua.nombre.setTag(pokemon.hashCode());
                        break;
                    case 2:
                        vhPlanta.nombre.setText("Nombre: " + pokemon.getNombre());
                        vhPlanta.nombre.setTextColor(Color.GREEN);
                        vhPlanta.nivelEvolucion.setText("Nivel de evolución: " + pokemon.getNivelEvolucion());
                        if (pokemon.getTipo().length == 2) {
                            vhPlanta.tipo.setText("Tipos: " + pokemon.getTipo()[0] + " y " + pokemon.getTipo()[1]);
                        } else {
                            vhPlanta.tipo.setText("Tipo: " + pokemon.getTipo()[0]);
                        }
                        vhPlanta.icono.setImageResource(pokemon.getIcono());
                        vhPlanta.nombre.setTag(pokemon.hashCode());
                        break;

                }
            }
            return convertView;
        }
    }
    class ViewHolderFuego //A su cosntructor le pasaremos el converView y los R.id.loquesea dependiendo lo q tengamos en el layout de la fila
    {
        TextView nombre;
        TextView tipo;
        TextView nivelEvolucion;
        ImageView icono;

        public ViewHolderFuego(View row)
        {
                this.nombre =(TextView)row.findViewById(R.id.nombrePokemon);
                this.tipo=(TextView)row.findViewById(R.id.tipo);
                this.nivelEvolucion=(TextView)row.findViewById(R.id.nivelEvolucion);
                this.icono=(ImageView)row.findViewById(R.id.imagenPokemon);
        }

        public TextView getNombre()
        {
            return nombre;

        }
        public ImageView getIcono()
        {
            return icono;
        }
        public TextView getTipo() {
            return tipo;
        }

        public TextView getNivelEvolucion() {
            return nivelEvolucion;
        }
    }
    class ViewHolderAgua //A su cosntructor le pasaremos el converView y los R.id.loquesea dependiendo lo q tengamos en el layout de la fila
    {
        TextView nombre;
        TextView tipo;
        TextView nivelEvolucion;
        ImageView icono;

        public ViewHolderAgua(View row)
        {
            this.nombre =(TextView)row.findViewById(R.id.nombrePokemonAgua);
            this.tipo=(TextView)row.findViewById(R.id.tipoAgua);
            this.nivelEvolucion=(TextView)row.findViewById(R.id.nivelEvolucionAgua);
            this.icono=(ImageView)row.findViewById(R.id.imagenPokemonAgua);
        }

        public TextView getNombre()
        {
            return nombre;

        }
        public ImageView getIcono()
        {
            return icono;
        }
        public TextView getTipo() {
            return tipo;
        }

        public TextView getNivelEvolucion() {
            return nivelEvolucion;
        }
    }
    class ViewHolderPlanta //A su cosntructor le pasaremos el converView y los R.id.loquesea dependiendo lo q tengamos en el layout de la fila
    {
        TextView nombre;
        TextView tipo;
        TextView nivelEvolucion;
        ImageView icono;

        public ViewHolderPlanta(View row)
        {
            this.nombre =(TextView)row.findViewById(R.id.nombrePlanta);
            this.tipo=(TextView)row.findViewById(R.id.tipoPlanta);
            this.nivelEvolucion=(TextView)row.findViewById(R.id.nivelEvolucionPlanta);
            this.icono=(ImageView)row.findViewById(R.id.imagenPlanta);
        }

        public TextView getNombre()
        {
            return nombre;

        }
        public ImageView getIcono()
        {
            return icono;
        }
        public TextView getTipo() {
            return tipo;
        }

        public TextView getNivelEvolucion() {
            return nivelEvolucion;
        }
    }
}
