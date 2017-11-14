package com.example.pjarana.ej4bol7;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String[]localizaciones={"Sevilla","Paris","New York","Berlin","Bali","Madagascar","Tokyo","Vancouver"};
    ArrayAdapter<String>spinnerAdapter;
    Spinner spinner;
    TextView texto;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        texto=(TextView)findViewById(R.id.texto);
        spinner=(Spinner)findViewById(R.id.spinner);
        spinnerAdapter=new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_item,R.id.customSpinnerItemTextView,localizaciones);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String ciudadElegida=spinner.getAdapter().getItem(position).toString();
                switch (ciudadElegida)
                {
                    case "Sevilla":
                        i=new Intent(Intent.ACTION_VIEW, Uri.parse("geo:37.3914105,-5.9591776"));
                        startActivity(i);
                        break;
                    case "Paris":
                        break;
                    case "New York":
                        break;
                    case "Berlin":
                        break;
                    case "Madagascar":
                        break;
                    case "Bali":
                        break;
                    case "Tokyo":
                        break;
                    case "Vancouver":
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                texto.setTextColor(Color.RED);
            }
        });
    }
}
