package com.um.adivinanumero.dominio;

import java.util.ArrayList;

public class Ranking {
	public static Integer LIMITE_RANKING = 10;
	
	ArrayList<Jugador> jugadores = new ArrayList<Jugador>(LIMITE_RANKING);
	
	public boolean entraEnRanking(Integer intentos) {
		return ultimoLugar() == null || ultimoLugar().getCantidadIntentos() > intentos;
	}
	
	public boolean agregarAlRanking(Jugador jugador) {
		Integer i = 0;
		Boolean entroAlRanking = false;
		
		while(i < jugadores.size() && !entroAlRanking) {
			if (jugadores.get(i).pierdeContra(jugador)) {
				jugadores.add(i, jugador);
				entroAlRanking = true;
				// TODO: Borrar de la BD el que se fue del ranking
			}
		}
		
		return entroAlRanking;
	}
	
	private Jugador ultimoLugar() {
		return jugadores.isEmpty() ? null : jugadores.get(jugadores.size());
	}
}
