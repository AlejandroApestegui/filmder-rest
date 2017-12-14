package com.cibertec.filmder.controllers;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.cibertec.filmder.beans.Match;
import com.cibertec.filmder.services.MatchService;
import com.cibertec.filmder.utils.Formatos;
import com.cibertec.filmder.utils.ResponseHelper;

@Path("/matches")
public class MatchController {

	MatchService ms = new MatchService();
	
	@POST
	@Path("/registrar")
	@Produces(Formatos.JSON)
	public Response registrar(Match m) {
		ResponseHelper response = new ResponseHelper();
		
		System.out.println("Pelicula:" + m.getPelicula());
		System.out.println("Usuario:" + m.getUsuario());
		System.out.println("Match:" + m.getMatched());
		
		if (ms.registrar(m) > 0) {
			response.setResult(1);
			response.setMessage("Grabado correctamente");
		} else {
			response.setResult(-1);
			response.setMessage("No se pudo grabar");
		}
		return Response.status(Status.OK).entity(response).build();
	}
	
	@POST
	@Path("/actualizar")
	@Produces(Formatos.JSON)
	public Response actualizar(Match m) {
		ResponseHelper response = new ResponseHelper();
		
		System.out.println("Pelicula:" + m.getPelicula());
		System.out.println("Usuario:" + m.getUsuario());
		
		if (ms.actualizar(m) > 0) {
			response.setResult(1);
			response.setMessage("Actualizado correctamente");
		} else {
			response.setResult(-1);
			response.setMessage("No se pudo actualizar");
		}
		return Response.status(Status.OK).entity(response).build();
	}
	
	
}
