package com.iesnervion.dleal.examenprimeraevaluacion;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.iesnervion.dleal.examenprimeraevaluacion.datos.ListadoJugadores;
import com.iesnervion.dleal.examenprimeraevaluacion.model.Jugador;

import org.w3c.dom.Text;

import java.util.Vector;

public class CreaJugador extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, AdapterView.OnItemSelectedListener {

    TextView nombre;
    ImageButton imagen;
    Spinner altura,peso;
    RadioButton base,escolta,alero,alapivot,pivot;
    int jugbase=0,jugescolta=0,jugalero=0,jugalapivot=0,jugpivot=0;
    String posicion="";
    int img = R.drawable.silueta;
    Vector<Integer> pesos = new Vector<>(0,1);
    Vector<Integer> alturas = new Vector<>(0,1);
    int propaltura=0,proppeso=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crea_jugador);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton guardar = (FloatingActionButton) findViewById(R.id.guardarjugador);
        guardar.setOnClickListener(this);

        //Recogemos el nombre
        nombre=(TextView) findViewById(R.id.editnombre);

        //Recogemos la imagen
        imagen = (ImageButton) findViewById(R.id.imagen);
        imagen.setOnClickListener(this);

        //Creamos las alturas
        altura=(Spinner) findViewById(R.id.altura);


        for (int i=150;i<251;i++){
            alturas.add(i);
        }
        altura.setAdapter(new ArrayAdapter<Integer>(this,R.layout.support_simple_spinner_dropdown_item,alturas));
        altura.setOnItemSelectedListener(this);

        //Creamos los pesos
        peso=(Spinner) findViewById(R.id.pesospiner);


        for (int i=45;i<251;i++){
            pesos.add(i);
        }
        peso.setAdapter(new ArrayAdapter<Integer>(this,R.layout.support_simple_spinner_dropdown_item,pesos));
        peso.setOnItemSelectedListener(this);


        //Definimos los radiobuttons
        base=(RadioButton) findViewById(R.id.base);
        escolta=(RadioButton) findViewById(R.id.escolta);
        alero=(RadioButton) findViewById(R.id.alero);
        alapivot = (RadioButton) findViewById(R.id.alapivot);
        pivot = (RadioButton) findViewById(R.id.pivot);

        //Recogemos el numero de jugadores por cada posicion
        Vector<Jugador> jugadores=((ListadoJugadores) getApplication()).getJugadores();
        for (int i=0 ; i< jugadores.size();i++){
            switch (jugadores.elementAt(i).getPosicion()){
                case "base":
                    jugbase++;
                    break;
                case "escolta":
                    jugescolta++;
                    break;
                case "alero":
                    jugalero++;
                    break;
                case "alapivot":
                    jugalapivot++;
                    break;
                case "pivot":
                    jugpivot++;
                    break;
            }

        }

        if(jugbase==2){
            base.setEnabled(false);
        }else{
            base.setEnabled(true);
        }
        if(jugescolta==2){
            escolta.setEnabled(false);
        }else{
            escolta.setEnabled(true);
        }
        if(jugalero==2){
            alero.setEnabled(false);
        }else{
            alero.setEnabled(true);
        }
        if(jugalapivot==2){
            alapivot.setEnabled(false);
        }else{
            alapivot.setEnabled(true);
        }
        if(jugpivot==2){
            pivot.setEnabled(false);
        }
        else{
            pivot.setEnabled(true);
        }

        Intent i= getIntent();
        Bundle bundle=i.getExtras();
        if(bundle !=null){
            Jugador j= bundle.getParcelable("jugador");

            nombre.setText(j.getNombre());
            imagen.setImageResource(j.getImg());
            switch (j.getPosicion()){
                case "base":
                    base.setChecked(true);
                    break;
                case "escolta":
                    escolta.setChecked(true);
                    break;
                case "alero":
                    alero.setChecked(true);
                    break;
                case "alapivot":
                    alapivot.setChecked(true);
                    break;
                case "pivot":
                    pivot.setChecked(true);
                    break;
            }
            altura.setSelection(j.getAltura()-150);
            peso.setSelection(j.getPeso()-45);
        }

        base.setOnCheckedChangeListener(this);
        escolta.setOnCheckedChangeListener(this);
        alero.setOnCheckedChangeListener(this);
        alapivot.setOnCheckedChangeListener(this);
        pivot.setOnCheckedChangeListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){


            case R.id.imagen:
                Intent intent = new Intent(this,EscogerImagen.class);
                startActivityForResult(intent,2);

                break;

            case R.id.guardarjugador:

                if((nombre.getText().toString()!="") && (img!= R.drawable.silueta) && (posicion!="") && propaltura!=0 && proppeso!=0 ){
                    Intent i = new Intent(this,MainActivity.class);
                    Jugador j= new Jugador(nombre.getText().toString(),posicion,img,propaltura,proppeso);
                    i.putExtra("Jugador",j);


                    startActivity(i);

                }
                break;

        }


    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked) {
            switch (buttonView.getId()) {
                case R.id.base:
                    escolta.setChecked(false);
                    alapivot.setChecked(false);
                    alero.setChecked(false);
                    pivot.setChecked(false);
                    posicion = "base";

                    break;
                case R.id.escolta:
                    base.setChecked(false);
                    alapivot.setChecked(false);
                    alero.setChecked(false);
                    pivot.setChecked(false);
                    posicion = "escolta";
                    break;

                case R.id.alapivot:
                    escolta.setChecked(false);
                    base.setChecked(false);
                    alero.setChecked(false);
                    pivot.setChecked(false);
                    posicion = "ala-pivot";
                    break;
                case R.id.alero:
                    escolta.setChecked(false);
                    alapivot.setChecked(false);
                    base.setChecked(false);
                    pivot.setChecked(false);
                    posicion = "alero";
                    break;
                case R.id.pivot:
                    escolta.setChecked(false);
                    alapivot.setChecked(false);
                    alero.setChecked(false);
                    base.setChecked(false);
                    posicion = "pivot";
                    break;
            }
        }
        else{
            buttonView.setChecked(false);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if(parent.getId()==R.id.pesospiner) {

                this.proppeso=this.pesos.elementAt(position);

        }
        else{

                this.propaltura=this.alturas.elementAt(position);

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        //Recibimos el resultado
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode==2)
        {

            Bundle bundle= data.getExtras();
            this.img= bundle.getInt("Imagen");
            imagen.setImageResource(img);

        }
    }
}
