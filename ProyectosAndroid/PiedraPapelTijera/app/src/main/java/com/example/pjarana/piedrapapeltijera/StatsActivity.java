package com.example.pjarana.piedrapapeltijera;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class StatsActivity extends AppCompatActivity {

    SharedPreferences estadisticas;
    TextView partidasGanadas;
    TextView partidasEmpatadas;
    TextView partidasPerdidas;
    TextView rachaGanadas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        partidasGanadas=(TextView)findViewById(R.id.partidasGanadas);
        partidasEmpatadas=(TextView)findViewById(R.id.partidasEmpatadas);
        partidasPerdidas=(TextView)findViewById(R.id.partidasPerdidas);
        rachaGanadas=(TextView)findViewById(R.id.rachaGanadas);
        estadisticas=getSharedPreferences("Stats",Context.MODE_PRIVATE);
        partidasGanadas.setText(partidasGanadas.getText()+" "+estadisticas.getInt("partidas_ganadas",0));
        partidasEmpatadas.setText(partidasEmpatadas.getText()+" "+estadisticas.getInt("partidas_empatadas",0));
        partidasPerdidas.setText(partidasPerdidas.getText()+" "+estadisticas.getInt("partidas_perdidas",0));
        rachaGanadas.setText(rachaGanadas.getText()+" "+estadisticas.getInt("racha_ganadas",0));
    }
}
