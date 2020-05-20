package com.example.parcial.mostrarlista;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.parcial.R;
import com.example.parcial.clases.Canciones;

import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder> {

    public final List<Canciones.Track> listaCanciones;

    public Adaptador(List<Canciones.Track> listaCanciones) {
        this.listaCanciones = listaCanciones;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cancion,parent,false);
        return new ViewHolder(view,this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Canciones.Track cancion = listaCanciones.get(position);
        holder.cancionItem.setText(cancion.name+" - "+cancion.artist.name + " - " + cancion.duration);
    }

    @Override
    public int getItemCount() {
        return listaCanciones.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView cancionItem;

        Adaptador adaptador;

        public ViewHolder(@NonNull View itemView,Adaptador adaptador) {
            super(itemView);
            cancionItem =itemView.findViewById(R.id.listadoItem);
            this.adaptador = adaptador;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
