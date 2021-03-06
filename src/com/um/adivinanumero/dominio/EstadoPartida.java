package com.um.adivinanumero.dominio;

import java.util.ArrayList;
import java.util.List;

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
	
	public List<Intento> getUltimosIntentos(Integer n) {
		if (n >= intentos.size()) {
			return intentos;
		} else {
			return intentos.subList(intentos.size() - n, intentos.size());
		}
	}
	
	public Boolean acertado() {
		return ultimoIntento().acertado();
	}
}
