package com.cibertec.filmder.beans;

public class Match {

	private int pelicula;
	private int usuario;
	private int matched; //0 false - 1 true
	
	public Match(){}
	
	public Match(int pelicula, int usuario, int matched) {
		this.pelicula = pelicula;
		this.usuario = usuario;
		this.matched = matched;
	}

	public int getPelicula() {
		return pelicula;
	}
	public void setPelicula(int pelicula) {
		this.pelicula = pelicula;
	}
	public int getUsuario() {
		return usuario;
	}
	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}
	public int getMatched() {
		return matched;
	}
	public void setMatched(int matched) {
		this.matched = matched;
	}
	
	
}
