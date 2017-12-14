package com.cibertec.filmder.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cibertec.filmder.beans.Pelicula;
import com.cibertec.filmder.utils.ConexionSQL;

public class PeliculaService {

	public int registrar(Pelicula p) {
		int filasAfectadas = -1;
		String sql = "insert into tb_peliculas values(null,?,?,?,?,?,?,?)";
		try (Connection conexion = ConexionSQL.obtenerConexion();
				PreparedStatement ps = conexion.prepareStatement(sql);) {
			ps.setString(1, p.getTitulo());
			ps.setString(2, p.getSinopsis());
			ps.setString(3, p.getGenero());
			ps.setInt(4, p.getCensura());
			ps.setString(5, p.getFecha_estreno());
			ps.setInt(6, p.getDuracion());
			ps.setString(7, p.getActores());
			ps.setString(8, p.getUrl_portada());
			ps.setString(9, p.getUrl_trailer());

			filasAfectadas = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filasAfectadas;
	}

	public int actualizar(Pelicula p) {
		int filasAfectadas = -1;
		String sql = "update tb_Peliculas set nombre = ?, apellido = ?, correo_electronico = ?, contrasena = ?, fecha_nacimiento = ?, genero = ?, tipo = ? where id = ?";
		try (Connection conexion = ConexionSQL.obtenerConexion();
				PreparedStatement ps = conexion.prepareStatement(sql);) {
			ps.setString(1, p.getTitulo());
			ps.setString(2, p.getSinopsis());
			ps.setString(3, p.getGenero());
			ps.setInt(4, p.getCensura());
			ps.setString(5, p.getFecha_estreno());
			ps.setInt(6, p.getDuracion());
			ps.setString(7, p.getActores());
			ps.setString(8, p.getUrl_portada());
			ps.setString(9, p.getUrl_trailer());
			ps.setInt(8, p.getId());

			filasAfectadas = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filasAfectadas;
	}

	public int eliminar(int id) {
		int filasAfectadas = -1;
		String sql = "delete from tb_peliculas where id_p = ?";
		try (Connection conexion = ConexionSQL.obtenerConexion();
				PreparedStatement ps = conexion.prepareStatement(sql);) {
			ps.setInt(1, id);
			filasAfectadas = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filasAfectadas;
	}

	public List<Pelicula> listado(Pelicula p) {
		List<Pelicula> peliculas = new ArrayList<Pelicula>();
		String sql = "select p.* from tb_peliculas p where titulo like concat(ifnull(?,titulo),'%') and genero_p like concat('%',concat(ifnull(?,genero_p),'%')) and censura = ifnull(?,censura) and fecha_estreno = ifnull(?,fecha_estreno) and actores like concat('%',concat(ifnull(?,actores),'%')) and director like concat(ifnull(?,director),'%') and productora like concat(ifnull(?,productora),'%')";
		try (Connection conexion = ConexionSQL.obtenerConexion();
				PreparedStatement ps = conexion.prepareStatement(sql);) {
			ps.setString(1, p.getTitulo());
			ps.setString(2, p.getGenero());
			if (p.getCensura() == -1) {
				ps.setObject(3, null);
			} else {
				ps.setInt(3, p.getCensura());
			}
			ps.setString(4, p.getFecha_estreno());
			ps.setString(5, p.getActores());
			ps.setString(6, p.getDirector());
			ps.setString(7, p.getProductora());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				p = new Pelicula();
				p.setId(rs.getInt(1));
				p.setTitulo(rs.getString(2));
				p.setSinopsis(rs.getString(3));
				p.setGenero(rs.getString(4));
				p.setCensura(rs.getInt(5));
				p.setFecha_estreno(rs.getString(6));
				p.setDuracion(rs.getInt(7));
				p.setActores(rs.getString(8));
				p.setDirector(rs.getString(9));
				p.setProductora(rs.getString(10));
				p.setUrl_portada(rs.getString(11));
				p.setUrl_trailer(rs.getString(12));
				
				peliculas.add(p);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return peliculas;
	}
	
	public List<Pelicula> listadoAgregados(int id){
		List<Pelicula> lista = new ArrayList<Pelicula>();
		String sql = "SELECT P.* FROM TB_PELICULAS P JOIN TB_MATCHS M ON P.ID_P = M.ID_PELICULA WHERE M.ID_USUARIO = ? AND M.MATCHED = 0";
		try (Connection conexion = ConexionSQL.obtenerConexion();
				PreparedStatement ps = conexion.prepareStatement(sql);) {
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Pelicula p = new Pelicula();
				p.setId(rs.getInt("id_p"));
				p.setTitulo(rs.getString("titulo"));
				p.setSinopsis(rs.getString("sinopsis"));
				p.setGenero(rs.getString("genero_p"));
				p.setCensura(rs.getInt("censura"));
				p.setFecha_estreno(rs.getString("fecha_estreno"));
				p.setDuracion(rs.getInt("duracion"));
				p.setActores(rs.getString("actores"));
				p.setDirector(rs.getString("director"));
				p.setProductora(rs.getString("productora"));
				p.setUrl_portada(rs.getString("url_portada"));
				p.setUrl_trailer(rs.getString("url_trailer"));

				lista.add(p);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Pelicula> listadoFaltante(int id) {
		List<Pelicula> lista = new ArrayList<Pelicula>();
		String sql = "select p.* from tb_peliculas p where id_p not in (select id_pelicula from tb_matchs where id_usuario = ?)";
		try (Connection conexion = ConexionSQL.obtenerConexion();
				PreparedStatement ps = conexion.prepareStatement(sql);) {
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Pelicula p = new Pelicula();
				p.setId(rs.getInt("id_p"));
				p.setTitulo(rs.getString("titulo"));
				p.setSinopsis(rs.getString("sinopsis"));
				p.setGenero(rs.getString("genero_p"));
				p.setCensura(rs.getInt("censura"));
				p.setFecha_estreno(rs.getString("fecha_estreno"));
				p.setDuracion(rs.getInt("duracion"));
				p.setActores(rs.getString("actores"));
				p.setDirector(rs.getString("director"));
				p.setProductora(rs.getString("productora"));
				p.setUrl_portada(rs.getString("url_portada"));
				p.setUrl_trailer(rs.getString("url_trailer"));

				lista.add(p);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

	public Pelicula obtenerPorId(int id) {
		Pelicula p = null;
		String sql = "select * from tb_peliculas where id_p = ?";
		ResultSet rs = null;
		try (Connection conexion = ConexionSQL.obtenerConexion();
				PreparedStatement ps = conexion.prepareStatement(sql);) {
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				p = new Pelicula();
				p.setId(rs.getInt(1));
				p.setTitulo(rs.getString(2));
				p.setSinopsis(rs.getString(3));
				p.setGenero(rs.getString(4));
				p.setCensura(rs.getInt(5));
				p.setFecha_estreno(rs.getString(6));
				p.setDuracion(rs.getInt(7));
				p.setActores(rs.getString(8));
				p.setDirector(rs.getString(9));
				p.setProductora(rs.getString(10));
				p.setUrl_portada(rs.getString(11));
				p.setUrl_trailer(rs.getString(12));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
	}

}
