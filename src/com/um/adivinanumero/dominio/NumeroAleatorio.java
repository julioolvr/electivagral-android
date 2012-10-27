package com.um.adivinanumero.dominio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class NumeroAleatorio {
	
	private static int CANTIDAD_DIGITOS = 4;
	
	public enum Resultado {
		CORRECTO,
		REGULAR,
		ERROR
	}
	
	String numero;
	
	public NumeroAleatorio() {
		numero = generarNumeroAleatorio();
	}
	
	public Resultado[] comparar(String guess) {
		Resultado[] resultado = new Resultado[4];
		
		if (guess.length() != 4) {
			throw new IllegalArgumentException("El número adivinado debe ser de 4 dígitos");
		}
		
		for (int caracter = 0; caracter < numero.length(); caracter++) {
			int index = numero.indexOf(guess.charAt(caracter));

			if (index == caracter) {
				resultado[caracter] = Resultado.CORRECTO;
			} else if (index >= 0) {
				resultado[caracter] = Resultado.REGULAR;
			} else {
				resultado[caracter] = Resultado.ERROR;
			}
		}
		
		return resultado;
	}
	
	public boolean acertado(String guess) {
		Resultado[] resultado = comparar(guess);
		
		for (int i = 0; i < resultado.length; i++) {
			if (resultado[i] != Resultado.CORRECTO) return false;
		}
		
		return true;
	}
	
	public Map<Resultado, Integer> compararCantidades(String guess) {
		Resultado[] comparacion = comparar(guess);
		
		HashMap<Resultado, Integer> resultado = new HashMap<Resultado, Integer>();
		
		resultado.put(Resultado.CORRECTO, 0);
		resultado.put(Resultado.ERROR, 0);
		resultado.put(Resultado.REGULAR, 0);
		
		for(int i = 0; i < comparacion.length; i++) {
			int anterior = resultado.get(comparacion[i]);
			resultado.put(comparacion[i], anterior + 1);
		}
		
		return resultado;
	}
	
	@Override
	public String toString() {
		return numero;
	}
	
	public static String generarNumeroAleatorio() {
		ArrayList<Integer> cifras = getCifras();
		
		String numero = "";
		
		for (int i = 0; i < CANTIDAD_DIGITOS; i++) {
			int indice = new Random().nextInt(cifras.size());
			numero += cifras.get(indice);
			cifras.remove(indice);
		}
		
		return numero;
	}
	
	private static ArrayList<Integer> getCifras() {
		ArrayList<Integer> cifras = new ArrayList<Integer>();
		
		for (int i = 0; i < 10; i++) {
			cifras.add(i);
		}
		
		return cifras;
	}
	
}
