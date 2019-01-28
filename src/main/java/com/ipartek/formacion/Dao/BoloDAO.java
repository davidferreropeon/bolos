package com.ipartek.formacion.Dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.ipartek.formacion.pojo.Bolo;

public class BoloDAO {

	// dao
	private static BoloDAO INSTANCE = null;
	private final static Logger LOG = Logger.getLogger(BoloDAO.class);
	private static final String SQL_GET_BY_ID = "{call bolo_getById(?)}";


	private static final String SQL_GET_BY_ALL = "SELECT * FROM bolo ORDER BY id DESC LIMIT 100";

	private static ArrayList bolos = null;

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

	private Bolo rowMapper(ResultSet rs) throws SQLException {
		Bolo bolo = new Bolo(); // creo la palabra para incluir los resultados de la consulta
		bolo.setId(rs.getLong("id"));
		bolo.setFecha(rs.getDate("fecha"));
		bolo.setLugar(rs.getString("lugar"));
		bolo.setBanda1(rs.getString("banda1"));
		bolo.setBanda2(rs.getString("banda2"));
		//bolo.setIdCrew(rs.getLong("idCrew"));
		bolo.setBanda3(rs.getString("banda3"));
		
		return bolo;
	}

	public Bolo getById(Long id) {

		Bolo bolo = null; // declaro la palabra
// falta array list
		String sql = " SELECT id, fecha, lugar, banda1, banda2, banda3 FROM apr_producciones.bolo WHERE id =?;"; // consulta

		try (Connection conn = ConnectionManager.getConnection(); // Establezco conexion
				PreparedStatement pst = conn.prepareStatement(sql);) { // creo objeto statement

			// parametros de entrada en la consulta
			pst.setLong(1, id);

			try (ResultSet rs = pst.executeQuery()) { // ejecuto contulta

				while (rs.next()) {
					bolo = rowMapper(rs);
					
					

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
					bolo = rowMapper(rs);
					

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bolo;
	}

	public ArrayList<Bolo> getAll() {

		ArrayList<Bolo> bolos = new ArrayList<Bolo>();

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(SQL_GET_BY_ALL);
				ResultSet rs = pst.executeQuery()) {

			while (rs.next()) {

				bolos.add(rowMapper(rs));
			}

		} catch (Exception e) {
			LOG.error(e);
		}
		return bolos;
	}

}// fin multa dao