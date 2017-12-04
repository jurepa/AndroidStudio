package com.example.pjarana.piedrapapeltijera;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class PlayActivity extends AppCompatActivity {

    ImageView imagenContrincante;
    ImageButton piedra;
    ImageButton papel;
    ImageButton tijeras;
    Button otra;
    Button salir;
    TextView descripcionJugada;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        imagenContrincante=(ImageView)findViewById(R.id.imagenContrincante);
        piedra=(ImageButton)findViewById(R.id.piedra);
        papel=(ImageButton)findViewById(R.id.papel);
        tijeras=(ImageButton)findViewById(R.id.tijeras);
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
            }
        });
    }
}
