package com.um.adivinanumero.dominio;

import java.util.ArrayList;
import java.util.Iterator;

public class Ranking implements Iterable<Jugador> {
	public static Integer LIMITE_RANKING = 10;
	public static Ranking instance = null;
	
	ArrayList<Jugador> jugadores = new ArrayList<Jugador>(LIMITE_RANKING);
	
	private Ranking() {}
	
	public static Ranking getInstance() {
		if (instance == null) {
			instance = new Ranking();
		}
		
		return instance;
	}
	
	/**
	 * Evalúa si un jugador con una determinada cantidad de intentos
	 * entraría en el ranking.
	 * 
	 * @param intentos
	 * 		Cantidad de intentos del nuevo jugador.
	 * @return
	 * 		Si el jugador entraría en el ranking o no.
	 */
	public boolean entraEnRanking(Integer intentos) {
		return ultimoLugar() == null
				|| jugadores.size() < LIMITE_RANKING
				|| ultimoLugar().pierdeContra(intentos);
	}
	
	/**
	 * Agrega un jugador al ranking si es que corresponde, en la posición
	 * adecuada.
	 * 
	 * @param jugador
	 * 		Jugador a agregar al ranking.
	 * @return
	 * 		Si el jugador entró al ranking o no.
	 */
	public boolean agregarAlRanking(Jugador jugador) {
		Integer i = 0;
		Boolean entroAlRanking = false;
		
		while(i < jugadores.size() && !entroAlRanking) {
			if (jugadores.get(i).pierdeContra(jugador)) {
				jugadores.add(i, jugador);
				entroAlRanking = true;
				// TODO: Borrar de la BD el que se fue del ranking
			}
			i++;
		}
		
		/*
		 * Si no le gana a ningún jugador del ranking, pero el ranking no está completo,
		 * agregar este jugador al final.
		 */
		if (!entroAlRanking && i < LIMITE_RANKING) {
			jugadores.add(jugador);
			entroAlRanking = true;
		}
		
		return entroAlRanking;
	}
	
	/**
	 * Devuelve el jugador en el último lugar del ranking.
	 * 
	 * @return
	 * 		El jugador en el último lugar del ranking, o null si el
	 * 		ranking está vacío.
	 */
	private Jugador ultimoLugar() {
		return jugadores.isEmpty() ? null : jugadores.get(jugadores.size() - 1);
	}
	
	/*
	 * Iteración
	 */
	@Override
	public Iterator<Jugador> iterator() {
		return jugadores.iterator();
	}
}
