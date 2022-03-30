package com.example.escommunity.fragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.escommunity.R;
import com.example.escommunity.adapters.MyAdapter;
import com.example.escommunity.constructors.Posts;
import com.example.escommunity.daos.PostsDAO;
import com.example.escommunity.daos.UtilizadoresDAO;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link pagina_inicial#newInstance} factory method to
 * create an instance of this fragment.
 */
public class pagina_inicial extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView recyclerView;
    RecyclerView.Adapter adaptador;
    RecyclerView.LayoutManager layoutManager;

    //loginId
    String loginId;

    //bundle
    Bundle dados;


    public pagina_inicial() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static pagina_inicial newInstance(String param1, String param2) {
        pagina_inicial fragment = new pagina_inicial();
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



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        UtilizadoresDAO utilizadoresDAO = new UtilizadoresDAO(getContext());
        View view = inflater.inflate(R.layout.fragment_pagina_inicial, container,false);

        dados = getArguments();
        if (dados != null){
            loginId = dados.getString("loginId");
        }

        recyclerView = view.findViewById(R.id.rvPosts);
        setAdapter();
        return view;
    }



    private void setAdapter(){
        //buscar a tabela posts
        PostsDAO postsDAO = new PostsDAO(getContext());

        //Carregar a recycler view
        ArrayList<Posts> listaPosts = postsDAO.getPosts();
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        MyAdapter meuAdapter = new MyAdapter(listaPosts);
        recyclerView.setAdapter(meuAdapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onResume(){
        super.onResume();
        setAdapter();

    }
}