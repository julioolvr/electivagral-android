package com.um.adivinanumero;

import java.util.List;

import android.app.Application;

import com.um.adivinanumero.dominio.EstadoPartida;
import com.um.adivinanumero.dominio.Intento;

public class Aplicacion extends Application {
	private EstadoPartida estado;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		inicializarPartida();
	}
	
	public void inicializarPartida() {
		estado = new EstadoPartida();
	}
	
	public String getNumero() {
		return estado.getNumero().toString();
	}
	
	public Integer getIntentos() {
		return estado.cantidadIntentos();
	}
	
	public List<Intento> getUltimosIntentos(Integer n) {
		return estado.getUltimosIntentos(n);
	}
	
	public Integer intentar(String guess) {
		Intento ultimoIntento = new Intento(estado.getNumero(), guess);
		return estado.agregarIntento(ultimoIntento);
	}
	
	public Boolean acertado() {
		return estado.acertado();
	}
	
	public Integer cantidadIntentos() {
		return estado.cantidadIntentos();
	}
	
	public Integer cantidadCorrectas() {
		return estado.cantidadCorrectas();
	}
	
	public Integer cantidadRegulares() {
		return estado.cantidadRegulares();
	}
	
	public Integer cantidadIncorrectos() {
		return estado.cantidadIncorrectos();
	}
}
