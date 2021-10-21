package com.example.listazakupowmodyfikowalna;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Produkt> produkty= new ArrayList<>();
    private RecyclerView zakupyRecyclerView;
    private ZakupyAdapter zakupyAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //produkty.add(new Produkt("maslo"));
        //produkty.add(new Produkt("chleb"));

        zakupyRecyclerView = findViewById(R.id.recyclerView);
        zakupyAdapter = new ZakupyAdapter(this,produkty);
        zakupyRecyclerView.setAdapter(zakupyAdapter);
        zakupyRecyclerView
                .setLayoutManager
                        (new LinearLayoutManager(this));
        Button dodajButton = (Button) findViewById(R.id.button);
        dodajButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dodawanie elementu z textview
                //do arraylist produkty
            }
        });
        Button usunButton = (Button) findViewById(R.id.button2);
        usunButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //usuwanie zaznaczonych elementów
                //z listy produkty
            }
        });
    }
}