package com.cibertec.filmder.controllers;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.cibertec.filmder.beans.Usuario;
import com.cibertec.filmder.services.UsuarioService;
import com.cibertec.filmder.utils.Correo;
import com.cibertec.filmder.utils.Formatos;
import com.cibertec.filmder.utils.ResponseHelper;

@Path("/usuarios")
public class UsuarioController {

	UsuarioService us = new UsuarioService();

	@POST
	@Path("/login")
	@Consumes(Formatos.JSON)
	@Produces(Formatos.JSON)
	public Response login(Usuario u) {
		ResponseHelper response = new ResponseHelper();
		if (us.login(u) > 0) {
			response.setData(us.obtenerUsuarioPorCorreo(u.getCorreo()));
			response.setMessage("Datos correctos");
			response.setResult(1);
		} else {
			response.setMessage("Datos incorrectos");
			response.setResult(-1);
		}
		return Response.status(Status.OK).entity(response).build();
	}

	@GET
	@Path("/")
	@Produces(Formatos.JSON)
	public Response listado() {
		ResponseHelper response = new ResponseHelper();
		List<Usuario> lista = us.listado();
		if (lista.isEmpty()) {
			response.setMessage("No hay data");
			response.setResult(-1);
			response.setData(lista);
		} else {
			response.setMessage("Listado correctamente");
			response.setResult(1);
			response.setData(lista);
		}
		return Response.status(Status.OK).entity(response).build();
	}

	@GET
	@Path("/{id}")
	@Produces(Formatos.JSON)
	public Response obtenerPorId(@PathParam("id") int id) {
		ResponseHelper response = new ResponseHelper();
		Usuario u = us.obtenerPorId(id);
		if (u == null) {
			response.setMessage("No existe usuario con ese codigo");
			response.setResult(-1);
			response.setData(u);
		} else {
			response.setMessage("Usuario encontrado");
			response.setResult(1);
			response.setData(u);
		}
		return Response.status(Status.OK).entity(response).build();
	}

	@POST
	@Path("/registrar")
	@Consumes(Formatos.JSON)
	@Produces(Formatos.JSON)
	public Response registrar(Usuario u) {
		ResponseHelper response = new ResponseHelper();
		if (us.obtenerUsuarioPorCorreo(u.getCorreo()) == null) {
			if (us.registrar(u) > 0) {
				Correo.enviar(u, 0);
				response.setResult(1);
				response.setMessage("Registrado correctamente");
			} else {
				response.setResult(-1);
				response.setMessage("Error al registrar");
			}
		} else {
			response.setResult(-2);
			response.setMessage("correo ya existente");
		}
		return Response.status(Status.OK).entity(response).build();
	}

	@PUT
	@Path("/actualizar")
	@Consumes(Formatos.JSON)
	@Produces(Formatos.JSON)
	public Response actualizar(Usuario u) {
		ResponseHelper response = new ResponseHelper();
		if (us.actualizar(u) > 0) {
			response.setResult(1);
			response.setMessage("actualizado correctamente");
		} else {
			response.setResult(-1);
			response.setMessage("error al actualizar");
		}
		return Response.status(Status.OK).entity(response).build();
	}

	@DELETE
	@Path("/eliminar/{id}")
	@Produces(Formatos.JSON)
	public Response eliminar(@PathParam("id") int id) {
		ResponseHelper response = new ResponseHelper(); 
		if (us.eliminar(id) > 0) {
			response.setResult(1);
			response.setMessage("Eliminado correctamente");
		} else {
			response.setResult(-1);
			response.setMessage("Error al eliminar");
		}
		return Response.status(Status.OK).entity(response).build();
	}

	@GET
	@Path("/recuperar-contrasena/{correo}")
	@Produces(Formatos.JSON)
	public Response recuperarContrasena(@PathParam("correo") String correo) {
		ResponseHelper response = new ResponseHelper();
		Usuario u = us.obtenerUsuarioPorCorreo(correo);
		if (u == null) {
			response.setResult(-1);
			response.setMessage("Usuario no existente");
		} else {
			Correo.enviar(u, 1);
			response.setResult(1);
			response.setMessage("Contraseña enviada a correo");
		}
		return Response.status(Status.OK).entity(response).build();
	}

	@POST
	@Path("/actualizar-contrasena")
	@Consumes(Formatos.JSON)
	@Produces(Formatos.JSON)
	public Response actualizarContrasena(Usuario u) {
		ResponseHelper response = new ResponseHelper();
		if (us.actualizarContrasena(u) > 0) {
			response.setResult(-1);
			response.setMessage("Error al actualizar");
		} else {
			response.setResult(1);
			response.setMessage("Contraseña actualizada");
		}
		return Response.status(Status.OK).entity(response).build();
	}

}
