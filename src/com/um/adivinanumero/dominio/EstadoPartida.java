package com.um.adivinanumero.dominio;

import java.util.ArrayList;

public class EstadoPartida {
	private ArrayList<Intento> intentos;
	private NumeroAleatorio numero;
	
	public EstadoPartida() {
		intentos = new ArrayList<Intento>();
		numero = new NumeroAleatorio();
	}
	
	public NumeroAleatorio getNumero() {
		return numero;
	}
	
	public Integer cantidadIntentos() {
		return intentos.size();
	}
	
	public Integer agregarIntento(Intento intento) {
		intentos.add(intento);
		return intentos.size();
	}
	
	public Integer cantidadCorrectas() {
		return intentos.isEmpty() ? 0 : ultimoIntento().getCorrectos();
	}
	
	public Integer cantidadRegulares() {
		return intentos.isEmpty() ? 0 : ultimoIntento().getRegulares();
	}
	
	public Integer cantidadIncorrectos() {
		return intentos.isEmpty() ? 0 : ultimoIntento().getIncorrectos();
	}
	
	public Intento ultimoIntento() {
		return intentos.get(intentos.size() - 1);
	}
	
	public Boolean acertado() {
		return ultimoIntento().acertado();
	}
	
	public Boolean entraAlRanking() {
		return Ranking.getInstance().entraEnRanking(cantidadIntentos());
	}
	
	public Boolean agregarAlRanking(String nombre) {
		Jugador jugador = new Jugador(nombre, cantidadIntentos());
		return Ranking.getInstance().agregarAlRanking(jugador);
	}
	
	public Ranking getRanking() {
		return Ranking.getInstance();
	}
}
