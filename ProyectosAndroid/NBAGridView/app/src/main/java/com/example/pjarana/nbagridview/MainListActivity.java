package com.example.pjarana.nbagridview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainListActivity extends AppCompatActivity {

    EquipoNBA dallas=new EquipoNBA("Dallas Maverick",R.drawable.dallas);
    EquipoNBA clippers=new EquipoNBA("Los Angeles Clippers",R.drawable.clippers);
    EquipoNBA blazers=new EquipoNBA("Portland Trail Blazers",R.drawable.blazers);
    EquipoNBA denver=new EquipoNBA("Denver Nuggets",R.drawable.denver);
    EquipoNBA grizzlies=new EquipoNBA("Memphis Grizzlies",R.drawable.grizzlies);
    EquipoNBA gsw=new EquipoNBA("Golden State Warriors",R.drawable.gsw);
    EquipoNBA lakers=new EquipoNBA("Angeles Lakers",R.drawable.lakers);
    EquipoNBA minnesota=new EquipoNBA("Minnesota Timberwolves", R.drawable.minnesota);
    EquipoNBA nop=new EquipoNBA("New Orleans Pelicans",R.drawable.nop);
    EquipoNBA rockets=new EquipoNBA("Houston Rockets",R.drawable.rockets);
    EquipoNBA phx=new EquipoNBA("Phoenix Suns",R.drawable.phx);
    EquipoNBA sac=new EquipoNBA("Sacramento Kings",R.drawable.sac);
    EquipoNBA sspurs=new EquipoNBA("San Antonio Spurs",R.drawable.sspurs);
    EquipoNBA thunder=new EquipoNBA("Oklahoma City Thunder",R.drawable.thunder);
    EquipoNBA uta=new EquipoNBA("Utah Jazz",R.drawable.uta);
    ArrayList<EquipoNBA>equipos=new ArrayList<EquipoNBA>();
    String[]equiposAutoComplete={dallas.getNombre(),denver.getNombre(),gsw.getNombre(),rockets.getNombre(),clippers.getNombre(),
    lakers.getNombre(),grizzlies.getNombre(),minnesota.getNombre(),nop.getNombre(),thunder.getNombre(),phx.getNombre(),
    blazers.getNombre(),sac.getNombre(),sspurs.getNombre(),uta.getNombre()};
    ArrayList<String>equiposSpinner=new ArrayList<String>();
    ListView lista;
    AutoCompleteTextView texto;
    Spinner spinner;
    Button button;
    GestoraListNBA gestora;
    ArrayAdapter<String>adapterSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainlist);
        equipos.add(dallas);
        equipos.add(denver);
        equipos.add(gsw);
        equipos.add(rockets);
        equipos.add(clippers);
        equipos.add(lakers);
        equipos.add(grizzlies);
        equipos.add(minnesota);
        equipos.add(nop);
        equipos.add(thunder);
        equipos.add(phx);
        equipos.add(blazers);
        equipos.add(sac);
        equipos.add(sspurs);
        equipos.add(uta);
        gestora=new GestoraListNBA();
        button=(Button)findViewById(R.id.boton);
        spinner=(Spinner)findViewById(R.id.spinner);
        adapterSpinner=new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_item,equiposSpinner);
        spinner.setAdapter(adapterSpinner);
        lista=(ListView)findViewById(R.id.lista);
        texto=(AutoCompleteTextView)findViewById(R.id.textSuggest);
        MyAdapter<EquipoNBA>adapterLista=new MyAdapter<EquipoNBA>(getApplicationContext(),R.layout.row_style,equipos);
        lista.setAdapter(adapterLista);
        ArrayAdapter<String>adapterAutocomplete=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,equiposAutoComplete);
        texto.setAdapter(adapterAutocomplete);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String nombreEquipo=texto.getText().toString();

                if(adapterSpinner.getCount()<4) {
                    if (!gestora.comprobarNombre(nombreEquipo, equipos)) {
                        Toast.makeText(getApplicationContext(), "No se ha encontrado ese equipo", Toast.LENGTH_SHORT);
                    } else {
                        equiposSpinner.add(nombreEquipo);
                        adapterSpinner = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item,R.id.customSpinnerItemTextView, equiposSpinner);
                        spinner.setAdapter(adapterSpinner);
                    }
                }
                else
                {
                    button.setClickable(false);
                }
            }
        });

    }
}
