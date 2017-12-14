package com.cibertec.filmder.beans;

public class Pelicula {

	private int id;
    private String titulo;
    private String sinopsis;
    private String genero;
    private int censura;
    private String fecha_estreno;
    private int duracion;
    private String actores;
    private String director;
    private String productora;
    private String url_portada;
    private String url_trailer;

    public Pelicula(int id, String titulo, String sinopsis, String genero, int censura, String fecha_estreno,
			int duracion, String actores, String director, String productora, String url_portada, String url_trailer) {
		this.id = id;
		this.titulo = titulo;
		this.sinopsis = sinopsis;
		this.genero = genero;
		this.censura = censura;
		this.fecha_estreno = fecha_estreno;
		this.duracion = duracion;
		this.actores = actores;
		this.director = director;
		this.productora = productora;
		this.url_portada = url_portada;
		this.url_trailer = url_trailer;
	}

    public Pelicula(){}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getSinopsis() {
		return sinopsis;
	}
	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public int getCensura() {
		return censura;
	}
	public void setCensura(int censura) {
		this.censura = censura;
	}
	public String getFecha_estreno() {
		return fecha_estreno;
	}
	public void setFecha_estreno(String fecha_estreno) {
		this.fecha_estreno = fecha_estreno;
	}
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	public String getActores() {
		return actores;
	}
	public void setActores(String actores) {
		this.actores = actores;
	}
	
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getProductora() {
		return productora;
	}
	public void setProductora(String productora) {
		this.productora = productora;
	}
	public String getUrl_portada() {
		return url_portada;
	}
	public void setUrl_portada(String url_portada) {
		this.url_portada = url_portada;
	}
	public String getUrl_trailer() {
		return url_trailer;
	}
	public void setUrl_trailer(String url_trailer) {
		this.url_trailer = url_trailer;
	}
	
}
