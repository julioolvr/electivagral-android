package com.um.adivinanumero;

import android.app.Application;

import com.um.adivinanumero.dominio.NumeroAleatorio;

public class Aplicacion extends Application {
	private NumeroAleatorio numero;
	private Integer intentos;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		inicializarPartida();
	}
	
	public void inicializarPartida() {
		numero = new NumeroAleatorio();
		intentos = 0;
	}
	
	public NumeroAleatorio getNumero() {
		return numero;
	}
	
	public Integer getIntentos() {
		return intentos;
	}
	
	public Integer incrementarIntentos() {
		return ++intentos;
	}
}
