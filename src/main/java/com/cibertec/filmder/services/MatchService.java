package com.cibertec.filmder.services;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.cibertec.filmder.beans.Match;
import com.cibertec.filmder.utils.ConexionSQL;

public class MatchService {

	public int registrar(Match m) {
		int filasAfectadas = -1;
		String sql = "insert into tb_matchs values(?,?,?)";
		try (Connection conexion = ConexionSQL.obtenerConexion();
				PreparedStatement ps = conexion.prepareStatement(sql);) {
			ps.setInt(1, m.getPelicula());
			ps.setInt(2, m.getUsuario());
			ps.setInt(3, m.getMatched());

			filasAfectadas = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filasAfectadas;
	}
	
	public int actualizar(Match m) {
		int filasAfectadas = -1;
		String sql = "update tb_matchs set matched = 1 where id_pelicula = ? and id_usuario = ?";
		try (Connection conexion = ConexionSQL.obtenerConexion();
				PreparedStatement ps = conexion.prepareStatement(sql);) {
			ps.setInt(1, m.getPelicula());
			ps.setInt(2, m.getUsuario());

			filasAfectadas = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filasAfectadas;
	}
	
	

}
