package com.ipartek.formacion.Dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.ipartek.formacion.pojo.Bolo;

public class BoloDAO {

	// dao
	private static BoloDAO INSTANCE = null;
	private static final String SQL_GET_BY_ID = "{call bolo_getById(?)}";

	// metodo constructor superclase
	private BoloDAO() {
		super();
	}

	// instance singleton
	public synchronized static BoloDAO getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new BoloDAO();
		}
		return INSTANCE;
	}

	public Bolo getById(Long id) {

		Bolo bolo = null; // declaro la palabra

		String sql = "SELECT b.fecha , b.lugar, cw.foh, cw.monitores, cw.luces, b.id_crew,	c.nombre AS 'cliente', c.telefono AS 'telf_cliente',count(*) AS 'num_bolos' FROM bolo AS b, cliente AS c, crew AS cw WHERE b.id =? GROUP BY (b.lugar) ;"; // consulta

		try (Connection conn = ConnectionManager.getConnection(); // Establezco conexion
				PreparedStatement pst = conn.prepareStatement(sql);) { // creo objeto statement

			// parametros de entrada en la consulta
			pst.setLong(1, id);

			try (ResultSet rs = pst.executeQuery()) { // ejecuto contulta

				while (rs.next()) {
					bolo = new Bolo(); // creo la palabra para incluir los resultados de la consulta
					bolo.setId(rs.getLong("id"));
					bolo.setFecha(rs.getDate("fecha"));
					bolo.setLugar(rs.getString("lugar"));
					bolo.setBanda1(rs.getString("banda1"));
					bolo.setBanda2(rs.getString("banda2"));
					bolo.setBanda3(rs.getString("banda3"));
					bolo.setIdCrew(rs.getLong("idCrew"));

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bolo;
	}

	public Bolo getByIdProcedimientoAlmacenado(Long id) {

		Bolo bolo = null; // declaro la palabra

		String sql = SQL_GET_BY_ID; // consulta

		try (Connection conn = ConnectionManager.getConnection(); CallableStatement cs = conn.prepareCall(sql);) {

			// parametros de entrada en la consulta
			cs.setLong(1, id);

			try (ResultSet rs = cs.executeQuery()) { // ejecuto contulta

				while (rs.next()) {
					bolo = new Bolo(); // creo la palabra para incluir los resultados de la consulta
					bolo.setId(rs.getLong("id"));
					bolo.setFecha(rs.getDate("fecha"));
					bolo.setLugar(rs.getString("lugar"));
					bolo.setBanda1(rs.getString("banda1"));
					bolo.setBanda2(rs.getString("banda2"));
					bolo.setBanda3(rs.getString("banda3"));
					bolo.setIdCrew(rs.getLong("idCrew"));

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bolo;
	}

}// fin multa dao