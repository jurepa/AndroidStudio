package com.iesnervion.pjarana.pruebaantesexamen;

import android.app.FragmentTransaction;
import android.arch.lifecycle.ViewModelProviders;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.iesnervion.pjarana.pruebaantesexamen.DAO.Database;
import com.iesnervion.pjarana.pruebaantesexamen.DAO.Usuario;

public class MainActivity extends AppCompatActivity /*implements ListFragment.OnFragmentInteractionListener*/{

    Button btnMostrarLista;
    Button btnAddUsuario;
    ListFragment listFragment;
    AddUserFragment addUserFragment;
    MainViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Usuario usuario=new Usuario(0,"Pablo");
        Database.getDatabase(this).getUsuariosDAO().insertUsuarios(usuario);*/
        listFragment=new ListFragment();
        addUserFragment=new AddUserFragment();
        btnMostrarLista=(Button)findViewById(R.id.btnLista);
        btnAddUsuario=(Button)findViewById(R.id.btnAddUser);
        viewModel= ViewModelProviders.of(this).get(MainViewModel.class);
        btnMostrarLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction=getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentMainActivity,listFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        btnAddUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction=getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentMainActivity,addUserFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

    }

    /*
    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }*/
}
