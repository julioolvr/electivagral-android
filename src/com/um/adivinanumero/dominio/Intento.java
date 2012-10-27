package com.um.adivinanumero.dominio;

public class Intento {
	private NumeroAleatorio numero;
	private String guess;
	private Resultado resultado;
	
	public Intento(NumeroAleatorio numero, String guess) {
		this.numero = numero;
		this.guess = guess;
		this.resultado = numero.comparar(guess);
	}
	
	public Boolean acertado() {
		return resultado.esCorrecto();
	}
	
	public Integer getCorrectos() {
		return resultado.getCorrectos();
	}
	
	public Integer getIncorrectos() {
		return resultado.getIncorrectos();
	}
	
	public Integer getRegulares() {
		return resultado.getRegulares();
	}
}