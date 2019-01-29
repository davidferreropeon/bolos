package com.ipartek.formacion.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.ipartek.formacion.pojo.Bolo;


public class BoloDAO {

	// dao
	private static BoloDAO INSTANCE = null;
	private final static Logger LOG = Logger.getLogger(BoloDAO.class);

	private static final String SQL_GET_BY_ID = "SELECT id, fecha, lugar, banda1, banda2, banda3 FROM apr_producciones.bolo WHERE id =?;";
	private static final String SQL_GET_BY_ALL = "SELECT * FROM bolo ORDER BY id DESC LIMIT 100";
	private static final String SQL_GET_BY_FECHA = "SELECT * FROM apr_producciones.bolo WHERE YEAR (fecha) =?;";
	private static final String SQL_INSERTAR= "INSERT INTO `apr_producciones`.`bolo` (`lugar`, `banda1`, `banda2`, `banda3`, `id_crew`, `info`) VALUES (?,?,?,?,?);" ; 
		
	private static final String SQL_UPDATE= "";

	// array list
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

	// rowmapper pararmetros
	private Bolo rowMapper(ResultSet rs) throws SQLException {
		Bolo bolo = new Bolo(); // creo la palabra para incluir los resultados de la consulta
		bolo.setId(rs.getLong("id"));
		bolo.setFecha(rs.getDate("fecha"));
		bolo.setLugar(rs.getString("lugar"));
		bolo.setBanda1(rs.getString("banda1"));
		bolo.setBanda2(rs.getString("banda2"));
		
		bolo.setBanda3(rs.getString("banda3"));
		
		//bolo.setIdCrew(rs.getLong("idCrew"));

		return bolo;
	}

	// get by id
	public Bolo getById(Long id) {

		Bolo bolo = null; // declaro la palabra

		String sql = SQL_GET_BY_ID; // consulta

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


	// listar get all
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

	// listar get all
	public ArrayList<Bolo> getByFecha(Long fecha) {

		ArrayList<Bolo> bolos = new ArrayList<Bolo>();

		try (Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst = conn.prepareStatement(SQL_GET_BY_FECHA);){

			// parametros de entrada en la consulta
			pst.setLong(1, fecha);

					try (ResultSet rs = pst.executeQuery()) { // ejecuto contulta
			
						while (rs.next()) {

							bolos.add(rowMapper(rs));
						}
					}

		} catch (Exception e) {
			LOG.error(e);
		}
		return bolos;
	
	}
	
	

	public boolean crear(Bolo b) throws SQLException {

		boolean resul = false;
	
		try (Connection conn = ConnectionManager.getConnection(); 
				PreparedStatement pst = conn.prepareStatement(SQL_INSERTAR, Statement.RETURN_GENERATED_KEYS );) {

			pst.setString(1, b.getLugar() );
			pst.setString(2, b.getBanda1() );
			pst.setString(3, b.getBanda2() );
			pst.setString(4, b.getBanda3() );
			pst.setString(5, b.getInfo() );
			//pst.setLong(3, b.getUsuario().getId() ); para dos tabalas distintas
			
			
			int affectedRows = pst.executeUpdate();
			if (affectedRows == 1) {
				
				ResultSet rs = pst.getGeneratedKeys();
				if (rs.next()) {
				    long id  = rs.getLong(1);
				    b.setId(id);				    
				}					
				resul = true;
			}

		}
		return resul;

	}
	
	public boolean update(Bolo b) throws SQLException {

		boolean resul = false;		
		try (Connection conn = ConnectionManager.getConnection();
			 PreparedStatement pst = conn.prepareStatement(SQL_UPDATE);) {
			
			pst.setString(1, b.getLugar() );
			pst.setString(2, b.getBanda1() );
			pst.setString(3, b.getBanda2() );
			pst.setString(4, b.getBanda3() );
			pst.setString(5, b.getInfo() );
			
			
			int affectedRows = pst.executeUpdate();
			if (affectedRows == 1) {
				resul = true;
			}
		}
		return resul;

	}
	
	
	
	
}// fin multa dao