package com.example.parcial.mostrarlista;

import com.example.parcial.clases.Busqueda;
import com.example.parcial.clases.Canciones;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("2.0/?method=chart.gettoptracks&api_key=b284db959637031077380e7e2c6f2775&format=json")
    Call<Canciones> traerCancion();

    @GET("2.0")
    Call<Busqueda> traerBusqueda(@Query("method") String method, @Query("track") String track, @Query("api_key") String api_key, @Query("format") String format);

}
