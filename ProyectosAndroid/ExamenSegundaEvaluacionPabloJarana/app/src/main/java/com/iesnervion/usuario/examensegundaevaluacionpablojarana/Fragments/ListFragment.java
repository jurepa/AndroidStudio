package com.iesnervion.usuario.examensegundaevaluacionpablojarana.Fragments;

import android.app.Fragment;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.iesnervion.usuario.examensegundaevaluacionpablojarana.CustomListView.MyAdapter;
import com.iesnervion.usuario.examensegundaevaluacionpablojarana.DAO.FutbolistaPosicion;
import com.iesnervion.usuario.examensegundaevaluacionpablojarana.DAO.Posicion;
import com.iesnervion.usuario.examensegundaevaluacionpablojarana.Models.FutbolistaConPosiciones;
import com.iesnervion.usuario.examensegundaevaluacionpablojarana.R;
import com.iesnervion.usuario.examensegundaevaluacionpablojarana.VMyRepositorios.ListRepository;
import com.iesnervion.usuario.examensegundaevaluacionpablojarana.VMyRepositorios.ListaViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 *  interface
 * to handle interaction events.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private MyAdapter<FutbolistaConPosiciones> adapter;
    private ArrayList<FutbolistaConPosiciones> futbolistasConPosiciones;
    private ListView lista;
    private ListaViewModel viewModel;
    private FutbolistaConPosiciones futbolistaConPosiciones;
    //private OnFragmentInteractionListener mListener;

    public ListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListFragment newInstance(String param1, String param2) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        viewModel= ViewModelProviders.of((FragmentActivity) getActivity()).get(ListaViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_list, container, false);
        futbolistasConPosiciones=new ArrayList<FutbolistaConPosiciones>();
        lista=(ListView)v.findViewById(R.id.listaFutbolistas);
        futbolistaConPosiciones=new FutbolistaConPosiciones();
        adapter=new MyAdapter<FutbolistaConPosiciones>(getActivity().getApplicationContext(),R.layout.row_style,futbolistasConPosiciones);
        viewModel.getLiveDataFutbolistaPosicion().observe((LifecycleOwner) getActivity(), new Observer<List<FutbolistaPosicion>>() {
            @Override
            public void onChanged(@Nullable List<FutbolistaPosicion> futbolistaPosicions) {
                if(futbolistaPosicions!=null) {
                    for (int i = 0; i < futbolistaPosicions.size(); i++) {
                        futbolistaConPosiciones.setId(futbolistaPosicions.get(i).getIdFutbolista());
                        viewModel.setFutbolista(futbolistaConPosiciones);
                        viewModel.getPosicionesDeFutbolista();
                        viewModel.getLiveDataPosicionesDeFutbolista().observe((LifecycleOwner) getActivity(), new Observer<List<Posicion>>() {
                            @Override
                            public void onChanged(@Nullable List<Posicion> posicions) {
                                for (int i = 0; i < posicions.size(); i++) {
                                    futbolistaConPosiciones.getPosiciones().add(posicions.get(i));
                                }
                                futbolistasConPosiciones.add(futbolistaConPosiciones);
                                lista.setAdapter(adapter);
                            }
                        });
                    }
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
    /*public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
}