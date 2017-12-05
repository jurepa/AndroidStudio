package com.example.pjarana.piedrapapeltijera;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class PlayActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView imagenContrincante;
    ImageButton piedra;
    ImageButton papel;
    ImageButton tijeras;
    Button otra;
    Button salir;
    TextView descripcionJugada;
    SharedPreferences estadisticas;
    SharedPreferences.Editor editor;
    int contadorGanadas;
    int contadorPerdidas;
    int contadorEmpates;
    int resultadoAnterior;
    int rachaGanadas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        estadisticas=getSharedPreferences("Stats",Context.MODE_PRIVATE);
        contadorEmpates=estadisticas.getInt("partidas_empatadas",1);
        contadorGanadas=estadisticas.getInt("partidas_ganadas",1);
        contadorPerdidas=estadisticas.getInt("partidas_perdidas",1);
        rachaGanadas=0;
        imagenContrincante=(ImageView)findViewById(R.id.imagenContrincante);
        piedra=(ImageButton)findViewById(R.id.piedra);
        papel=(ImageButton)findViewById(R.id.papel);
        tijeras=(ImageButton)findViewById(R.id.tijeras);
        piedra.setOnClickListener(this);
        tijeras.setOnClickListener(this);
        papel.setOnClickListener(this);
        otra=(Button)findViewById(R.id.jugarOtraVez);
        salir=(Button)findViewById(R.id.salir);
        descripcionJugada=(TextView)findViewById(R.id.descripcionJugada);

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        otra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagenContrincante.setVisibility(View.INVISIBLE);
                descripcionJugada.setText("");
                otra.setVisibility(View.INVISIBLE);
                salir.setVisibility(View.INVISIBLE);
                piedra.setVisibility(View.VISIBLE);
                tijeras.setVisibility(View.VISIBLE);
                papel.setVisibility(View.VISIBLE);
                piedra.setClickable(true);
                tijeras.setClickable(true);
                papel.setClickable(true);
            }
        });
    }


    @Override
    public void onClick(View v)
    {
        Random rnd=new Random();
        int decisionPC=rnd.nextInt(3);
        int resultado;
        salir.setVisibility(View.VISIBLE);
        otra.setVisibility(View.VISIBLE);
        imagenContrincante.setVisibility(View.VISIBLE);
        switch ( decisionPC)
        {
            case 0:
                imagenContrincante.setImageResource(R.drawable.rsz_tijeras);
                imagenContrincante.setTag(R.drawable.rsz_tijeras);
                break;
            case 1:
                imagenContrincante.setImageResource(R.drawable.rsz_papel);
                imagenContrincante.setTag(R.drawable.rsz_papel);
                break;
            case 2:
                imagenContrincante.setImageResource(R.drawable.rsz_piedra);
                imagenContrincante.setTag(R.drawable.rsz_piedra);
                break;
        }
        switch(v.getId())
        {
            case R.id.papel:
                piedra.setVisibility(View.INVISIBLE);
                tijeras.setVisibility(View.INVISIBLE);
                papel.setClickable(false);
                break;
            case R.id.tijeras:
                papel.setVisibility(View.INVISIBLE);
                piedra.setVisibility(View.INVISIBLE);
                tijeras.setClickable(false);
                break;
            case R.id.piedra:
                papel.setVisibility(View.INVISIBLE);
                tijeras.setVisibility(View.INVISIBLE);
                piedra.setClickable(false);
                break;
        }
        resultado=comprobarResultado(imagenContrincante,v.getId());
        descripcionJugada.setTextColor(Color.RED);
        editor=estadisticas.edit();
        switch (resultado)
        {
            case 0:
                descripcionJugada.setText("EMPATE!"+contadorEmpates);
                contadorEmpates++;
                editor.putInt("partidas_empatadas",contadorEmpates);
                break;
            case 1:
                descripcionJugada.setText("GANASTE!"+contadorGanadas);
                contadorGanadas++;
                if(rachaGanadas==0)
                {
                    rachaGanadas++;
                }
                editor.putInt("partidas_ganadas",contadorGanadas);
                break;
            case -1:
                descripcionJugada.setText("PERDISTE..."+contadorPerdidas);
                contadorPerdidas++;
                editor.putInt("partidas_perdidas",contadorPerdidas);
                break;
        }
        if(resultadoAnterior==1 && resultado==1 &&rachaGanadas>1)
        {
            rachaGanadas++;
            if(rachaGanadas>estadisticas.getInt("racha_ganadas",0))
            {
                editor.putInt("racha_ganadas", rachaGanadas);
            }
        }
        else if(resultadoAnterior!=1 && resultado!=1 &&rachaGanadas>1)
        {
            rachaGanadas=0;
        }
        resultadoAnterior=resultado;
        editor.commit();
    }

    public int comprobarResultado(ImageView contrincante, int imagenUsuario)
    {
        int resultado=0;
        if(imagenUsuario==R.id.piedra)
        {
            if((int)contrincante.getTag()==R.drawable.rsz_tijeras)
            {
                resultado=1;
            }
            else if((int)contrincante.getTag()==R.drawable.rsz_papel)
            {
                resultado=-1;
            }

        }
        else if (imagenUsuario==R.id.papel)
        {
            if((int)contrincante.getTag()==R.drawable.rsz_tijeras)
            {
                resultado=-1;
            }
            else if((int)contrincante.getTag()==R.drawable.rsz_piedra)
            {
                resultado=1;
            }
        }
        else
        {
            if((int)contrincante.getTag()==R.drawable.rsz_papel)
            {
                resultado=1;
            }
            else if((int)contrincante.getTag()==R.drawable.rsz_piedra)
            {
                resultado=-1;
            }
        }
        return resultado;
    }
}
