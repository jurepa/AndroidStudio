package com.example.pjarana.pruebawebapi;

import android.content.Context;
import android.widget.Toast;

import java.time.Duration;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import okhttp3.Headers;


public class LibroCallback implements Callback<List<Libro>>{
	private Libro libro;
	@Override
	public void onFailure(Call<List<Libro>> arg0, Throwable arg1) {
		// TODO Auto-generated method stub
		int i;
		
		i=0;
		
		
		
	}

	@Override
	public void onResponse(Call<List<Libro>> arg0, Response<List<Libro>> resp) {
		// TODO Auto-generated method stub

	String contentType;
	int code;
	String message;
	boolean isSuccesful;
	
	this.libro = resp.body().get(0);
	
	Headers cabeceras = resp.headers();
	contentType = cabeceras.get("Content-Type");
	code = resp.code();
	message = resp.message();
	isSuccesful = resp.isSuccessful();
	}

	public Libro getLibro() {
		return libro;
	}
}
