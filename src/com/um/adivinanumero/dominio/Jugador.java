package com.um.adivinanumero.dominio;

public class Jugador {
	Long id;
	String nombre;
	Integer cantidadIntentos;
	
	public Jugador(Long id, String nombre, Integer cantidadIntentos) {
		this.id = id;
		this.nombre = nombre;
		this.cantidadIntentos = cantidadIntentos;
	}
	
	public Jugador(String nombre, Integer cantidadIntentos) {
		this.nombre = nombre;
		this.cantidadIntentos = cantidadIntentos;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public Integer getCantidadIntentos() {
		return cantidadIntentos;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * Devuelve si este jugador pierde en el ranking contra el 
	 * jugador pasado como parámetro.
	 * 
	 * @param j
	 * 		Jugador para comparar
	 * @return
	 * 		Si este jugador pierde contra el parámetro
	 */
	public Boolean pierdeContra(Jugador j) {
		return this.pierdeContra(j.getCantidadIntentos());
	}
	
	/**
	 * Devuelve si este jugador perdería contra un jugador
	 * con la cantidad de intentos pasada como parámetro.
	 * 
	 * @param intentos
	 * 		Cantidad de intentos para comparar
	 * @return
	 * 		Si perdería contra un jugador con esta cantidad de intentos
	 */
	public Boolean pierdeContra(Integer intentos) {
		return this.cantidadIntentos > intentos;
	}
}
