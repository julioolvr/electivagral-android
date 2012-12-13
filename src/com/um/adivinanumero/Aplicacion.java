package com.um.adivinanumero;

import android.app.Application;

import com.um.adivinanumero.dominio.EstadoPartida;
import com.um.adivinanumero.dominio.Intento;
import com.um.adivinanumero.dominio.Ranking;

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
	
	public Boolean entraAlRanking() {
		return estado.entraAlRanking();
	}
	
	public Boolean agregarAlRanking(String nombre) {
		return estado.agregarAlRanking(nombre);
	}
	
	public Ranking getRanking() {
		return estado.getRanking();
	}
}
