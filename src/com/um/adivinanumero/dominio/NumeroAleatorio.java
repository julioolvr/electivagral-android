package com.um.adivinanumero.dominio;

import java.util.ArrayList;
import java.util.Random;

public class NumeroAleatorio {
	
	public static int CANTIDAD_DIGITOS = 4;
	
	String numero;
	
	public NumeroAleatorio() {
		numero = generarNumeroAleatorio();
	}
	
	public Resultado comparar(String guess) {
		Resultado resultado = new Resultado();
		
		if (guess.length() != 4) {
			throw new IllegalArgumentException("El número adivinado debe ser de 4 dígitos");
		}
		
		Integer correctos = 0;
		Integer regulares = 0;
		Integer incorrectos = 0;
		
		for (int caracter = 0; caracter < numero.length(); caracter++) {
			int index = numero.indexOf(guess.charAt(caracter));

			if (index == caracter) {
				correctos++;
			} else if (index >= 0) {
				regulares++;
			} else {
				incorrectos++;
			}
		}
		
		resultado.setCorrectos(correctos);
		resultado.setIncorrectos(incorrectos);
		resultado.setRegulares(regulares);
		
		return resultado;
	}
	
	@Override
	public String toString() {
		return numero;
	}
	
	private static String generarNumeroAleatorio() {
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
