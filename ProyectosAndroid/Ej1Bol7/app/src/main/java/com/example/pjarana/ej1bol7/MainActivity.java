package com.example.pjarana.ej1bol7;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<String>activities;
    ListView lista;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activities=new ArrayList<String>();
        activities.add("Cambiar textos");
        activities.add("Calculadora");
        activities.add("Lista filtrada pokemon");
        activities.add("ListaNBA");
        lista=(ListView)findViewById(R.id.lista);
        ArrayAdapter<String>listaAdapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,activities);
        lista.setAdapter(listaAdapter);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(lista.getAdapter().getItem(position).toString())
                {
                    case "Cambiar textos":
                        i=new Intent(getApplicationContext(),Ej1Bol2Activity.class);
                        startActivity(i);
                        break;
                    case "Calculadora":
                        i=new Intent(getApplicationContext(),Ej2Bol2Activity.class);
                        startActivity(i);
                        break;
                    case "Lista filtrada pokemon":
                        i=new Intent(getApplicationContext(),FilterListActivity.class);
                        startActivity(i);
                        break;
                    case "ListaNBA":
                        i=new Intent(Intent.ACTION_MAIN);
                        i.setComponent(new ComponentName("com.example.pjarana.nbagridview","MainActivity.class"));
                        i.addCategory(Intent.CATEGORY_LAUNCHER);
                        startActivity(i);
                        break;
                }
            }
        });

    }
}
