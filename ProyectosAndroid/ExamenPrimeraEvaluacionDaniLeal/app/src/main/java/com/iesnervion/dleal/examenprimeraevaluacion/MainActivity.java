package com.iesnervion.dleal.examenprimeraevaluacion;

import android.app.Application;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import com.iesnervion.dleal.examenprimeraevaluacion.datos.ListadoJugadores;
import com.iesnervion.dleal.examenprimeraevaluacion.model.Jugador;

import java.util.List;
import java.util.Vector;

public class MainActivity extends ListActivity implements View.OnClickListener,AdapterView.OnItemLongClickListener, PopupMenu.OnMenuItemClickListener {

    //Aquí guardamos el jugador que vamos a editar
    private Jugador jugadorSeleccionado;
    Vector<Jugador> jugadores = new Vector<>(0,1);
    ListView lista;
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        //setSupportActionBar(toolbar);ListActivity


            Intent i= getIntent();
            Bundle bundle=i.getExtras();
        if(bundle !=null){
            Jugador j= bundle.getParcelable("Jugador");
            ((ListadoJugadores) getApplication()).setJugadores(j);
            ((ListadoJugadores) getApplication()).elimarimagen(j.getImg());
        }

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);



       getListView().setOnItemLongClickListener(this);



        //Le añadimos adaptador a nuestra lista
        if(((ListadoJugadores) getApplication()).getJugadores()!=null)
            jugadores = ((ListadoJugadores) getApplication()).getJugadores();
        setListAdapter(new MiArrayAdapter(this,R.layout.filajugador,jugadores));

        if(jugadores.size()==10)
            fab.setVisibility(View.INVISIBLE);
        else
            fab.setVisibility(View.VISIBLE);

    }

    @Override
    public void onResume(){
        super.onResume();
        jugadores=((ListadoJugadores) getApplication()).getJugadores();
        MiArrayAdapter adapter=new MiArrayAdapter(this,R.layout.filajugador,jugadores);
        getListView().setAdapter(adapter);
        //lv.setOnItemClickListener(this);
        getListView().setOnItemLongClickListener(this);
        //Aunque lo tenemos controlado para que no se puedan insertar más de 10 jugadores
        //Es mejor no darle la opción al usuario de que pueda insertarlo
        if(jugadores.size()==10)
            fab.setVisibility(View.INVISIBLE);
        else
            fab.setVisibility(View.VISIBLE);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

  /*  @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        //Recibimos el resultado
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode==1)
        {

            Bundle bundle= data.getExtras();


            Jugador j =bundle.getParcelable("Jugador");

            this.lista.setJugadores(j);
        }
    }*/

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.fab:
                Intent intent = new Intent(this, CreaJugador.class);
                startActivity(intent);
            break;

        }
    }


    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        jugadorSeleccionado = jugadores.elementAt(position);
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.show();

        return true;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if(item.getItemId()==R.id.borrar){

            ((ListadoJugadores) getApplication()).eliminarJugador(jugadorSeleccionado);

            //Llamo a onResume para que recargue el array de jugadores
            onResume();
        }else{
            Intent editar=new Intent(this,CreaJugador.class);
            editar.putExtra("jugador",jugadorSeleccionado);
            //Añado la posición que hemos pulsado aquí,porque al utilizar vector.indexOf() no me detecta
            //bien la posición
            //No funciona porque tiene que ser parcelable... :(
            //editar.putExtra("posicion",posicionPulsada);
            startActivity(editar);
        }
        return false;
    }
}
