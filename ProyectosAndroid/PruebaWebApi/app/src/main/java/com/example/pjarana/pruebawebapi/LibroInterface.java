package com.example.pjarana.pruebawebapi;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;



public interface LibroInterface {
	@GET("/libro/{id}")
	Call<List<Libro>> getLibro(@Path("id") int id);

}
