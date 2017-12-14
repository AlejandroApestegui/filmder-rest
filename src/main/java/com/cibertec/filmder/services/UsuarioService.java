package com.cibertec.filmder.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cibertec.filmder.beans.Usuario;
import com.cibertec.filmder.utils.ConexionSQL;

public class UsuarioService {

	public int login(Usuario u) {
		String sql = "select id_u from tb_usuarios where correo = ? and contrasena = ?";
		ResultSet rs = null;
		try (Connection conexion = ConexionSQL.obtenerConexion();
				PreparedStatement ps = conexion.prepareStatement(sql);) {
			ps.setString(1, u.getCorreo());
			ps.setString(2, u.getContrasena());
			rs = ps.executeQuery();
			if (rs.next()) {
				return 1;
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public int registrar(Usuario u) {
		int filasAfectadas = -1;
		String sql = "insert into tb_usuarios values(null,?,?,?,?,?,?,?)";
		try (Connection conexion = ConexionSQL.obtenerConexion();
				PreparedStatement ps = conexion.prepareStatement(sql);) {
			ps.setString(1, u.getNombre());
			ps.setString(2, u.getApellido());
			ps.setString(3, u.getCorreo());
			ps.setString(4, u.getContrasena());
			ps.setString(5, u.getNacimiento());
			ps.setInt(6, u.getGenero());
			ps.setInt(7, u.getTipo());

			filasAfectadas = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filasAfectadas;
	}

	public int actualizar(Usuario u) {
		int filasAfectadas = -1;
		String sql = "update tb_usuarios set nombre = ?, apellido = ?, correo_electronico = ?, contrasena = ?, nacimiento = ?, genero_u = ?, tipo = ? where id_u = ?";
		try (Connection conexion = ConexionSQL.obtenerConexion();
				PreparedStatement ps = conexion.prepareStatement(sql);) {
			ps.setString(1, u.getNombre());
			ps.setString(2, u.getApellido());
			ps.setString(3, u.getCorreo());
			ps.setString(4, u.getContrasena());
			ps.setString(5, u.getNacimiento().toString());
			ps.setInt(6, u.getGenero());
			ps.setInt(7, u.getTipo());
			ps.setInt(8, u.getId());

			filasAfectadas = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filasAfectadas;
	}

	public int eliminar(int id) {
		int filasAfectadas = -1;
		String sql = "delete from tb_usuarios where id_u = ?";
		try (Connection conexion = ConexionSQL.obtenerConexion();
				PreparedStatement ps = conexion.prepareStatement(sql);) {
			ps.setInt(1, id);
			filasAfectadas = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filasAfectadas;
	}

	public List<Usuario> listado() {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		String sql = "select * from tb_usuarios";
		try (Connection conexion = ConexionSQL.obtenerConexion();
				PreparedStatement ps = conexion.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();) {
			while (rs.next()) {
				Usuario u = new Usuario();
				u.setId(rs.getInt(1));
				u.setNombre(rs.getString(2));
				u.setApellido(rs.getString(3));
				u.setCorreo(rs.getString(4));
				u.setContrasena(rs.getString(5));
				u.setNacimiento(rs.getString(6));
				u.setGenero(rs.getInt(7));
				u.setTipo(rs.getInt(8));

				usuarios.add(u);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuarios;
	}

	public int actualizarContrasena(Usuario u) {
		String sql = "update tb_usuarios set contrasena = ? where id_u = ?";
		try (Connection conexion = ConexionSQL.obtenerConexion();
				PreparedStatement ps = conexion.prepareStatement(sql);) {
			ps.setString(1, u.getContrasena());
			ps.setInt(2, u.getId());

			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public Usuario obtenerUsuarioPorCorreo(String correo) {
		String sql = "select * from tb_usuarios where correo = ?";
		try (Connection conexion = ConexionSQL.obtenerConexion();
				PreparedStatement ps = conexion.prepareStatement(sql);) {
			ResultSet rs = null;
			ps.setString(1, correo);
			rs = ps.executeQuery();
			if (rs.next()) {
				Usuario u = new Usuario();
				
				u.setId(rs.getInt(1));
				u.setNombre(rs.getString(2));
				u.setApellido(rs.getString(3));
				u.setCorreo(rs.getString(4));
				u.setContrasena(rs.getString(5));
				u.setNacimiento(rs.getString(6));
				u.setGenero(rs.getInt(7));
				u.setTipo(rs.getInt(8));
				
				return u;
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Usuario obtenerPorId(int id) {
		Usuario u = null;
		String sql = "select * from tb_usuarios where id_u = ?";
		ResultSet rs = null;
		try (Connection conexion = ConexionSQL.obtenerConexion();
				PreparedStatement ps = conexion.prepareStatement(sql);) {
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				u = new Usuario();
				u.setId(rs.getInt(1));
				u.setNombre(rs.getString(2));
				u.setApellido(rs.getString(3));
				u.setCorreo(rs.getString(4));
				u.setContrasena(rs.getString(5));
				u.setNacimiento(rs.getString(6));
				u.setGenero(rs.getInt(7));
				u.setTipo(rs.getInt(8));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}

}
