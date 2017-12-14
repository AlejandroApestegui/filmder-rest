package com.cibertec.filmder.controllers;

import java.io.InputStream;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.cibertec.filmder.beans.Pelicula;
import com.cibertec.filmder.services.PeliculaService;
import com.cibertec.filmder.utils.CDN;
import com.cibertec.filmder.utils.Formatos;
import com.cibertec.filmder.utils.ResponseHelper;
import com.sun.jersey.multipart.FormDataBodyPart;
import com.sun.jersey.multipart.FormDataMultiPart;

@Path("/peliculas")
public class PeliculaController {

	PeliculaService ps = new PeliculaService();
	CDN cdn = new CDN();
	
	@GET
	@Path("/")
	@Produces(Formatos.JSON)
	public Response listado(
			@QueryParam("titulo")String titulo,
			@QueryParam("genero")String genero,
			@DefaultValue("-1")@QueryParam("censura")int censura,
			@QueryParam("fecha_estreno")String fecha_estreno,
			@QueryParam("actores")String actores,
			@QueryParam("director")String director,
			@QueryParam("productora")String productora
			) {
		ResponseHelper response = new ResponseHelper();
		Pelicula p = new Pelicula(-1, titulo, null, genero, censura, fecha_estreno, -1, actores, director, productora, null, null);
		List <Pelicula> peliculas = ps.listado(p);
		if(peliculas.isEmpty()){
			response.setResult(-1);
			response.setMessage("No hay data");
		}else{
			response.setResult(1);
			response.setMessage("Listado correctamente");
		}
		response.setData(peliculas);
		return Response.status(Status.OK).entity(response).build();
	}
	
	@GET
	@Path("/agregadas/{id}")
	@Produces(Formatos.JSON)
	public Response listadoAgregadas(@PathParam("id") int id) {
		ResponseHelper response = new ResponseHelper();
		List <Pelicula> peliculas = ps.listadoAgregados(id);
		if(peliculas.isEmpty()){
			response.setResult(-1);
			response.setMessage("No hay data");
		}else{
			response.setResult(1);
			response.setMessage("Listado correctamente");
		}
		response.setData(peliculas);
		return Response.status(Status.OK).entity(response).build();
	}
	
	@GET
	@Path("/faltantes/{id}")
	@Produces(Formatos.JSON)
	public Response listadoFaltante(@PathParam("id") int id) {
		ResponseHelper response = new ResponseHelper();
		List <Pelicula> peliculas = ps.listadoFaltante(id);
		if(peliculas.isEmpty()){
			response.setResult(-1);
			response.setMessage("No hay data");
		}else{
			response.setResult(1);
			response.setMessage("Listado correctamente");
		}
		response.setData(peliculas);
		return Response.status(Status.OK).entity(response).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(Formatos.JSON)
	public Response obtenerPorId(@PathParam("id") int id) {
		ResponseHelper response = new ResponseHelper();
		Pelicula p = ps.obtenerPorId(id);
		if(p == null){
			response.setResult(-1);
			response.setMessage("No hay data");
		}else{
			response.setResult(1);
			response.setMessage("Pelicula encontrada");
		}
		response.setData(p);
		return Response.status(Status.OK).entity(response).build();
	
	}

	@PUT
	@Path("/actualizar")
	@Consumes(Formatos.JSON)
	@Produces(Formatos.JSON)
	public Response actualizar(Pelicula p) {
		ResponseHelper response = new ResponseHelper();
		if (ps.actualizar(p) > 0) {
			response.setResult(-1);
			response.setMessage("Error al actualizar");
		} else {
			response.setResult(1);
			response.setMessage("Actualizado exitosamente");
		}
		return Response.status(Status.OK).entity(response).build();
	}

	@POST
	@Path("/registrar")
	@Consumes(Formatos.JSON)
	@Produces(Formatos.JSON)
	public Response registrar(Pelicula p) {
		ResponseHelper response = new ResponseHelper();
		if (ps.registrar(p) > 0) {
			response.setResult(-1);
			response.setMessage("Error al registrar");
		} else {
			response.setResult(1);
			response.setMessage("Registrado exitosamente");
		}
		return Response.status(Status.OK).entity(response).build();
	}

	@DELETE
	@Path("/eliminar/{id}")
	@Produces(Formatos.JSON)
	public Response eliminar(@PathParam("id") int id) {
		ResponseHelper response = new ResponseHelper();
		if (ps.eliminar(id) > 0) {
			response.setResult(-1);
			response.setMessage("Error al actualizar");
		} else {
			response.setResult(1);
			response.setMessage("Actualizado exitosamente");
		}
		return Response.status(Status.OK).entity(response).build();
	}

	@Path("/subir-archivo")
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(Formatos.JSON)
	public Response uploadFile(FormDataMultiPart multiPart) {
		ResponseHelper response = new ResponseHelper();
		FormDataBodyPart bodyPart = multiPart.getField("file");
		InputStream is = bodyPart.getEntityAs(InputStream.class);
		String url = cdn.subirArchivo(is);
		if(url != null){
			response.setResult(1);
			response.setMessage("Archivo subido correctamente");
			response.setData(url);
		}else{
			response.setResult(-1);
			response.setMessage("Error al subir archivo");
		}
		return Response.status(Status.OK).entity(response).build();
	}

}