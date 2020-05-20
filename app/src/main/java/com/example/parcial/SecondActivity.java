package com.example.parcial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.parcial.clases.Busqueda;
import com.example.parcial.clases.Canciones;
import com.example.parcial.mostrarlista.Api;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SecondActivity extends AppCompatActivity {
    List<Busqueda.Track> trackList = new ArrayList<>();
    EditText song,artista,album,duracion,buscador;
    ImageButton buscarImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        buscador = (EditText)findViewById(R.id.buscador);
        song = (EditText)findViewById(R.id.idCancion);
        artista = (EditText)findViewById(R.id.idArtista);
        album = (EditText)findViewById(R.id.idAlbum);
        duracion = (EditText)findViewById(R.id.idDuracion);


        buscarImg = findViewById(R.id.imageButton);
        buscarImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                traerCancion();
            }
        });

    }

    private void agregarCancion(){
        try{
            Canciones.Track songs =new Canciones.Track();
            Canciones.Artist artistaCanciones = new Canciones.Artist();
            artistaCanciones.name = artista.getText().toString();
            songs.artist = artistaCanciones;
            songs.name = song.getText().toString();
            songs.duration = "00:00";
            MainActivity.listaCanciones.add(songs);
        }catch (Exception IO){
            Toast.makeText(getApplicationContext(),"BAD REQUEST",Toast.LENGTH_SHORT).show();
        }
    }

    private void traerCancion() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ws.audioscrobbler.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        String search = buscador.getText().toString();
        Call<Busqueda>call = api.traerBusqueda("track.search",search,"b284db959637031077380e7e2c6f2775","json");
        try{
            call.enqueue(new Callback<Busqueda>() {
                @Override
                public void onResponse(Call<Busqueda> call, Response<Busqueda> response) {
                    if(response.isSuccessful()){
                        Busqueda busqueda = response.body();
                        Busqueda.Results results = busqueda.getResults();
                        Busqueda.Trackmatches trackmatches = results.getTrackmatches();
                        trackList = trackmatches.getTrack();
                        song.setText(trackList.get(0).getName());
                        artista.setText(trackList.get(0).getArtist());
                        album.setText(trackList.get(0).getName());
                        duracion.setText("00:00");

                    }else{
                        Toast.makeText(getApplicationContext(),"CODE REQUEST: "+response.code(),Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Busqueda> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),"BAD REQUEST: "+ t.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception IO){
            Toast.makeText(getApplicationContext(),"BAD REQUEST",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu m) {
        getMenuInflater().inflate(R.menu.menu_agregar, m);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.guardar:
                agregarCancion();
                finish();
                return true;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
