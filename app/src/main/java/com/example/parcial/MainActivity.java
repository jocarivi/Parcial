package com.example.parcial;

import android.content.Intent;
import android.os.Bundle;

import com.example.parcial.clases.Canciones;
import com.example.parcial.mostrarlista.Adaptador;
import com.example.parcial.mostrarlista.Api;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static List<Canciones.Track> listaCanciones= new ArrayList<>();
    RecyclerView recycler;
    Adaptador adaptador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,  SecondActivity.class);
                startActivity(intent);
            }
        });

        traer();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onStart() {
        super.onStart();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,  SecondActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    private void traer(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ws.audioscrobbler.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        Call<Canciones> call = api.traerCancion();
        call.enqueue(new Callback<Canciones>() {
            @Override
            public void onResponse(Call<Canciones> call, Response<Canciones> response) {
                if(response.isSuccessful()){
                    Canciones canciones = response.body();
                    Canciones.Tracks listCanciones = canciones.getTracks();
                    listaCanciones = listCanciones.getTrack();
                    recycler = findViewById(R.id.recycler);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                    recycler.setLayoutManager(linearLayoutManager);
                    adaptador = new Adaptador(listaCanciones);
                    recycler.setAdapter(adaptador);
                }else{
                    Toast.makeText(getApplicationContext(),"CODE REQUEST: "+response.code(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Canciones> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"BAD REQUEST: "+ t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
