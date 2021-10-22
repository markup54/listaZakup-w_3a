package com.example.listazakupowmodyfikowalna;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Produkt> produkty;
    private RecyclerView zakupyRecyclerView;
    private ZakupyAdapter zakupyAdapter;
    private SharedPreferences listaSharedPrefences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //produkty.add(new Produkt("maslo"));
        //produkty.add(new Produkt("chleb"));
        listaSharedPrefences = getPreferences(MODE_PRIVATE);
        // tu metoda z ładowaniem listy produktów
        produkty=odczytajSharedPreferences();
        zakupyRecyclerView = findViewById(R.id.recyclerView);
        zakupyAdapter = new ZakupyAdapter(this,produkty);
        //zakupyAdapter.dodajProdukt(new Produkt("mleko"));
        zakupyRecyclerView.setAdapter(zakupyAdapter);
        zakupyRecyclerView
                .setLayoutManager
                        (new GridLayoutManager(this,
                                2));
        Button dodajButton = (Button) findViewById(R.id.button);
        dodajButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText wpisanyProdukt
                        = (EditText) findViewById(R.id.editText);
                String produktTekst
                        = String.valueOf(wpisanyProdukt.getText());
                if(produktTekst.length()>0){
                    Produkt produkt=new Produkt(produktTekst);
                    zakupyAdapter.dodajProdukt(produkt);
                    wpisanyProdukt.setText("");
                }
                //dodawanie elementu z textview
                //do arraylist produkty
            }
        });
        Button usunButton = (Button) findViewById(R.id.button2);
        usunButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zakupyAdapter.usunZListy();
                //usuwanie zaznaczonych elementów
                //z listy produkty
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        zapiszSharedPrefences();
    }

    private void zapiszSharedPrefences(){
        SharedPreferences.Editor edytor
                = listaSharedPrefences.edit();
        //serializacja danych Gson
        Gson gson = new Gson();
        String listaGson = gson.toJson(produkty);
        edytor.putString("LISTA_Z",listaGson);
        edytor.apply();
    }
    private ArrayList<Produkt> odczytajSharedPreferences(){
        String gsonLista
                = listaSharedPrefences
                .getString("LISTA_Z","");
        //deserializacja danych
        Gson gson = new Gson();
        Type typ
                = new TypeToken<List<Produkt>>(){}
                .getType();
        ArrayList<Produkt> produktyZGson
                =gson.fromJson(gsonLista,typ);
        if(produktyZGson == null){
            produktyZGson = new ArrayList<>();
        }
        return produktyZGson;
    }
}