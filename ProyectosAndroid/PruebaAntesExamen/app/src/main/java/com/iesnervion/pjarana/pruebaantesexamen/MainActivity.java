package com.iesnervion.pjarana.pruebaantesexamen;

import android.app.FragmentTransaction;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.iesnervion.pjarana.pruebaantesexamen.Fragments.DetailsFragment;
import com.iesnervion.pjarana.pruebaantesexamen.DAO.Usuario;
import com.iesnervion.pjarana.pruebaantesexamen.Fragments.AddUserFragment;
import com.iesnervion.pjarana.pruebaantesexamen.Fragments.ListFragment;

public class MainActivity extends AppCompatActivity /*implements ListFragment.OnFragmentInteractionListener*/{

    Button btnMostrarLista;
    Button btnAddUsuario;
    ListFragment listFragment;
    DetailsFragment detailsFragment;
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
        viewModel.getUsuarioSeleccionado().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(@Nullable Usuario usuario) {
                FragmentTransaction transaction=getFragmentManager().beginTransaction();
                detailsFragment=new DetailsFragment();
                transaction.replace(R.id.fragmentMainActivity,detailsFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
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
