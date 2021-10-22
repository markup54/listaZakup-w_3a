package com.example.listazakupowmodyfikowalna;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ZakupyAdapter
        extends RecyclerView.Adapter<ZakupyAdapter.ProduktViewHolder> {
    private ArrayList<Produkt> produkty;
    private LayoutInflater inflater;

    public ZakupyAdapter(Context context,
                         ArrayList<Produkt> produkty) {
        inflater =LayoutInflater.from(context);
        this.produkty = produkty;
    }
    //dodawanie do listy zakupów
    //dodajemy do ArrayList produkty
    //aktualizujemy wyświetlanie
    public void dodajProdukt(Produkt produkt){
        produkty.add(produkt);
        notifyItemInserted(produkty.size()-1);
    }
    //usuwanie z listy zakupów
    public void usunZListy(){
        produkty.removeIf(x -> x.isZaznaczony());
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ProduktViewHolder onCreateViewHolder
            (@NonNull ViewGroup parent, int viewType) {
        View produktItemView = inflater.inflate
                (R.layout.item_recycler_view,
                        parent,
                        false);
        //tworzenie widoku tylko na początku
        return new ProduktViewHolder
                (produktItemView,this);
        //zwracamy obiekt klasy ProduktViewHolder
        //do którego przekazujemy widok pojedynczego
        //elementu listy
        //łaczymy z adapterem
    }

    @Override
    public void onBindViewHolder
            (@NonNull ProduktViewHolder holder,
             int position) {
        //wypełnianie widoku danymi na bieżąco
        Produkt aktualnyProdukt = produkty.get(position);
        holder.checkBoxItem
                .setText(aktualnyProdukt.getNazwa());
        holder.checkBoxItem
                .setChecked(aktualnyProdukt.isZaznaczony());
        holder.checkBoxItem.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(
                            CompoundButton compoundButton,
                            boolean b) {
                        aktualnyProdukt.setZaznaczony(b);
                        if(b){
                            holder.checkBoxItem
                                    .setPaintFlags(
                                            holder.checkBoxItem.getPaintFlags()
                                    | Paint.STRIKE_THRU_TEXT_FLAG);
                            //prezkreślenie
                        }
                        else{
                            holder.checkBoxItem
                                    .setPaintFlags(
                                            holder.checkBoxItem.getPaintFlags()
                                    & ~Paint.STRIKE_THRU_TEXT_FLAG);
                            //uswuwanie przekreslenia
                        }
                    }
                }
        );
    }

    @Override
    public int getItemCount() {
        //liczba elementów do wyświetlenia
        return produkty.size();
    }

    public class ProduktViewHolder
            extends RecyclerView.ViewHolder {
        public CheckBox checkBoxItem;
        public final ZakupyAdapter zakupyAdapter;

        public ProduktViewHolder(@NonNull View itemView,
                                 ZakupyAdapter adapter) {
            super(itemView);
            this.checkBoxItem =itemView
                    .findViewById(R.id.checkBox);
            this.zakupyAdapter = adapter;

        }
    }
}
