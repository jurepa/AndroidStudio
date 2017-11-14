package com.example.pjarana.nbagridview;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn;
    TextView nombre;
    ImageView imagen;
    final int cteActivity=5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nombre=(TextView)findViewById(R.id.nombreEquipo);
        imagen=(ImageView)findViewById(R.id.imagenEquipo);
        btn=(Button)findViewById(R.id.boton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),ListActivity.class);
                startActivityForResult(i,cteActivity);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        EquipoNBA equipo=null;
        if (requestCode == cteActivity) {
            if(resultCode == Activity.RESULT_OK){
                equipo=(EquipoNBA)data.getParcelableExtra("equipo");
                nombre.setText(equipo.getNombre());
                imagen.setImageResource(equipo.getLogo());
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(),"Actividad fallida",Toast.LENGTH_SHORT);
            }
        }
    }
}
