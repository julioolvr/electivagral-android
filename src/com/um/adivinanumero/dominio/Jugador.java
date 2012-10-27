package com.um.adivinanumero.dominio;

public class Jugador {
	String nombre;
	Integer cantidadIntentos;
	
	public Jugador(String nombre, Integer cantidadIntentos) {
		this.nombre = nombre;
		this.cantidadIntentos = cantidadIntentos;
	}
	
	public Boolean pierdeContra(Jugador j) {
		return this.cantidadIntentos > j.getCantidadIntentos();
	}
	
	public Integer getCantidadIntentos() {
		return cantidadIntentos;
	}
}
