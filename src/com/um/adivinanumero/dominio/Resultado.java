package com.um.adivinanumero.dominio;

public class Resultado {
	private Integer correctos = 0;
	private Integer regulares = 0;
	private Integer incorrectos = 0;
	
	public void setCorrectos(Integer correctos) {
		this.correctos = correctos;
	}
	
	public void setIncorrectos(Integer incorrectos) {
		this.incorrectos = incorrectos;
	}
	
	public void setRegulares(Integer regulares) {
		this.regulares = regulares;
	}
	
	public Integer getCorrectos() {
		return correctos;
	}
	
	public Integer getIncorrectos() {
		return incorrectos;
	}
	
	public Integer getRegulares() {
		return regulares;
	}
	
	public Boolean esCorrecto() {
		return correctos == NumeroAleatorio.CANTIDAD_DIGITOS;
	}
}
