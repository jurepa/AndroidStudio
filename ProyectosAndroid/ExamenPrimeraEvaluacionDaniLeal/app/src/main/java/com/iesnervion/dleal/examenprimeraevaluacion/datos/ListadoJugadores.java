package com.iesnervion.dleal.examenprimeraevaluacion.datos;

import android.app.Application;

import com.iesnervion.dleal.examenprimeraevaluacion.R;
import com.iesnervion.dleal.examenprimeraevaluacion.model.Imagen;
import com.iesnervion.dleal.examenprimeraevaluacion.model.Jugador;

import java.util.Vector;

/**
 * Created by dleal on 7/12/16.
 */

public class ListadoJugadores extends Application{

    //Atributos
    private  Vector<Jugador> jugadores;
    private int numjugadores;
    private Vector<Imagen> imagenes ;

    public ListadoJugadores() {
        this.jugadores = new Vector<>(0,1);
        this.numjugadores = 0;
        this.imagenes = new Vector<>(0,1);

        imagenes.add(new Imagen(R.drawable.jugador00,false));
        imagenes.add(new Imagen(R.drawable.jugador01,false));
        imagenes.add(new Imagen(R.drawable.jugador02,false));
        imagenes.add(new Imagen(R.drawable.jugador03,false));
        imagenes.add(new Imagen(R.drawable.jugador04,false));
        imagenes.add(new Imagen(R.drawable.jugador05,false));
        imagenes.add(new Imagen(R.drawable.jugador06,false));
        imagenes.add(new Imagen(R.drawable.jugador07,false));
        imagenes.add(new Imagen(R.drawable.jugador08,false));
        imagenes.add(new Imagen(R.drawable.jugador09,false));
        imagenes.add(new Imagen(R.drawable.jugador10,false));
        imagenes.add(new Imagen(R.drawable.jugador11,false));
        imagenes.add(new Imagen(R.drawable.jugador12,false));
        imagenes.add(new Imagen(R.drawable.jugador13,false));
        imagenes.add(new Imagen(R.drawable.jugador14,false));
        imagenes.add(new Imagen(R.drawable.jugador15,false));
        imagenes.add(new Imagen(R.drawable.jugador16,false));
        imagenes.add(new Imagen(R.drawable.jugador17,false));
        imagenes.add(new Imagen(R.drawable.jugador18,false));
        imagenes.add(new Imagen(R.drawable.jugador19,false));
        imagenes.add(new Imagen(R.drawable.jugador20,false));
        imagenes.add(new Imagen(R.drawable.jugador21,false));
        imagenes.add(new Imagen(R.drawable.jugador22,false));
        imagenes.add(new Imagen(R.drawable.jugador23,false));
    }

    public Vector<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(Jugador j) {
        boolean juginsertado=false;

        //Esto es por si borran algun jugador, pueda meter otro en su lugar
        for(int i=0;i<jugadores.size();i++) {
            if(jugadores.elementAt(i)==null) {
                jugadores.add(i,j);
                juginsertado=true;
                numjugadores++;
            }
        }
        if(juginsertado==false && numjugadores < 10){
            jugadores.add(j);
            numjugadores++;
        }
    }
    public void eliminarJugador(Jugador j){
        for(int i=0;i<jugadores.size();i++){
            if(jugadores.elementAt(i).getImg()==j.getImg() ){
                jugadores.removeElementAt(i);
                numjugadores--;
                this.recuperarimagen(j.getImg());
            }
        }
    }
    public Vector<Imagen> getImagenes() {
        return imagenes;
    }

    public void elimarimagen(int imagen){
        for(int i = 0;i<this.imagenes.size();i++){
            if(this.imagenes.elementAt(i).getImg()==imagen){
                imagenes.elementAt(i).setEscogido(true);
            }
        }
    }
    public void recuperarimagen(int imagen){
        for(int i = 0;i<this.imagenes.size();i++){
            if(this.imagenes.elementAt(i).getImg()==imagen){
                imagenes.elementAt(i).setEscogido(false);
            }
        }
    }
}
