package com.iesnervion.pjarana.pruebaantesexamen.Fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.iesnervion.pjarana.pruebaantesexamen.DAO.Usuario;
import com.iesnervion.pjarana.pruebaantesexamen.MainViewModel;
import com.iesnervion.pjarana.pruebaantesexamen.R;
import com.iesnervion.pjarana.pruebaantesexamen.UsuarioRepository;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 *  interface
 * to handle interaction events.
 * Use the {@link DetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private Usuario usuario;
    private MainViewModel viewModel;
    private TextView txtID;
    private TextView txtNombre;
    private ToggleButton interruptor;
    private Button btnGuardar;
    private EditText editTextNombreUsuario;
    private UsuarioRepository repository;
    //private String mParam2;

    //private OnFragmentInteractionListener mListener;

    public DetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment DetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailsFragment newInstance(Usuario param1) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            usuario = getArguments().getParcelable(ARG_PARAM1);
        }
        viewModel= ViewModelProviders.of((FragmentActivity) getActivity()).get(MainViewModel.class);
        repository=new UsuarioRepository(getActivity().getApplication());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_details, container, false);
        usuario=viewModel.getUsuarioSeleccionado().getValue();
        txtID=(TextView)v.findViewById(R.id.idUsuarioDetails);
        txtNombre=(TextView)v.findViewById(R.id.nombreUsuarioDetails);
        interruptor=(ToggleButton)v.findViewById(R.id.interruptor);
        txtID.setText(txtID.getText().toString()+String.valueOf(usuario.getId()));
        txtNombre.setText(txtNombre.getText().toString()+usuario.getNombre());
        btnGuardar=(Button)v.findViewById(R.id.btnGuardar);
        editTextNombreUsuario=(EditText)v.findViewById(R.id.editTextNombreUsuario);
        btnGuardar.setVisibility(View.INVISIBLE);
        editTextNombreUsuario.setVisibility(View.INVISIBLE);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                usuario.setNombre(editTextNombreUsuario.getText().toString());
                repository.updateUsuarios(usuario);
            }
        });
        interruptor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    txtID.setVisibility(View.INVISIBLE);
                    txtNombre.setVisibility(View.INVISIBLE);
                    btnGuardar.setVisibility(View.VISIBLE);
                    editTextNombreUsuario.setVisibility(View.VISIBLE);
                    editTextNombreUsuario.setText(usuario.getNombre());
                }
                else
                {
                    txtID.setVisibility(View.VISIBLE);
                    txtNombre.setVisibility(View.VISIBLE);
                    btnGuardar.setVisibility(View.INVISIBLE);
                    editTextNombreUsuario.setVisibility(View.INVISIBLE);
                    editTextNombreUsuario.setText(usuario.getNombre());
                }
            }
        });
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
   /* public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }*/

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
   /* public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
}
