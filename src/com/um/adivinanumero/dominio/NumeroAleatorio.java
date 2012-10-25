package com.um.adivinanumero.dominio;

import java.util.ArrayList;
import java.util.Random;

import android.util.SparseIntArray;

public class NumeroAleatorio {
	
	private static int CANTIDAD_DIGITOS = 4;
	
	public static final int CORRECTO = 0;
	public static final int REGULAR = 1;
	public static final int ERROR = 2;
	
	String numero;
	
	public NumeroAleatorio() {
		numero = generarNumeroAleatorio();
	}
	
	public int[] comparar(String guess) {
		int[] resultado = new int[4];
		
		if (guess.length() != 4) {
			throw new IllegalArgumentException("El número adivinado debe ser de 4 dígitos");
		}
		
		for (int caracter = 0; caracter < numero.length(); caracter++) {
			int index = numero.indexOf(guess.charAt(caracter));

			if (index == caracter) {
				resultado[caracter] = CORRECTO;
			} else if (index >= 0) {
				resultado[caracter] = REGULAR;
			} else {
				resultado[caracter] = ERROR;
			}
		}
		
		return resultado;
	}
	
	public boolean acertado(String guess) {
		int[] resultado = comparar(guess);
		
		for (int i = 0; i < resultado.length; i++) {
			if (resultado[i] != CORRECTO) return false;
		}
		
		return true;
	}
	
	public SparseIntArray compararCantidades(String guess) {
		int[] comparacion = comparar(guess);
		
		SparseIntArray resultado = new SparseIntArray(3);
		
		resultado.put(CORRECTO, 0);
		resultado.put(ERROR, 0);
		resultado.put(REGULAR, 0);
		
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
