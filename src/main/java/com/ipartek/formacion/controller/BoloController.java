package com.ipartek.formacion.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.log4j.Logger;

import com.ipartek.formacion.Dao.BoloDAO;
import com.ipartek.formacion.pojo.Alerta;
import com.ipartek.formacion.pojo.Bolo;

@WebServlet("/bolo")
public class BoloController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	// LOG
	private final static Logger LOG = Logger.getLogger(BoloController.class);
	

	private ValidatorFactory factory;
	private Validator validator;



	// variables parametros
	
	public String id = "";	
	public Long idp = null;  // parseo
	public String fecha = "";
	
	public String lugar = "";
	public String banda1 = "";
	public String banda2 = "";
	public String banda3 = "";
	
	public String idCrew = "";	
	public Long idpCrew = null;  // parseo
	
	public String info = "";
	
	public String redirect="a";
	public String op = "";
	
	// dao y objetos
	private BoloDAO boloDAO = null;
	Bolo bolo = null;
	Alerta alerta = null;

	// init para validator, dao , objetos y colecciones
	@Override
	public void init(ServletConfig config) throws ServletException {

		super.init(config);
		boloDAO = BoloDAO.getInstance();
		bolo = new Bolo();
		alerta = new Alerta();
		factory  = Validation.buildDefaultValidatorFactory();
    	validator  = factory.getValidator();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			getParametros(request);
			// realizar operacion
			switch (op) {
			case "listar":
				listar(request);
				break;
			case "buscar":
				buscar(request);
				break;
			case "fecha":
				fecha(request);
				break;
			case "crear":
				crear(request);
				break;
			
			
			}

		} catch (Exception e) {
			LOG.debug("error catch logica");
			request.setAttribute("mensaje", "Algo a salido mal ");
		} finally {
			if (redirect.equals("a")) {
				
				request.getRequestDispatcher("listar.jsp").forward(request, response);
				
			}else  if (redirect.equals("b")) {
			request.getRequestDispatcher("buscar.jsp").forward(request, response);
			}else  if (redirect.equals("c")) {
				request.getRequestDispatcher("nuevo.jsp").forward(request, response);
				}
		}

	}// fin doproces

	private void getParametros(HttpServletRequest request) {
		// palabra parametros en un string
		id = request.getParameter("id");
		fecha =request.getParameter("fecha");
		lugar =request.getParameter("lugar");
		
		banda1 =request.getParameter("banda1");
		banda2 =request.getParameter("banda2");
		banda3 =request.getParameter("banda3");
		
		idCrew =request.getParameter("idCrew");
		info =request.getParameter("info");
		
		op = request.getParameter("op");
	}

	private void listar(HttpServletRequest request) {
		request.setAttribute("alerta", alerta); 
		request.setAttribute("bolos", boloDAO.getAll());
		request.setAttribute("mensaje", "Aqui tienes el listado de bolos realiados");
		 alerta = new Alerta( Alerta.TIPO_SUCCESS, "LISTADO VERDE ");
		
		redirect="a";

	}
	
	// buscar por id
	private void buscar(HttpServletRequest request) {
		Long idp = Long.parseLong(id);
		bolo=boloDAO.getById(idp);
		request.setAttribute("boloBuscar", bolo);
		request.setAttribute("mensaje", "Resultados de tu busqueda");
		 alerta = new Alerta( Alerta.TIPO_WARNING, "resultados de tu busqueda AMARILLO ");
		 request.setAttribute("alerta", alerta); 

		redirect="b";
	}

	// buscar por fecha
	private void fecha(HttpServletRequest request) {
		Long fechaparseo = Long.parseLong(fecha);
		request.setAttribute("bolos", boloDAO.getByFecha(fechaparseo));
		request.setAttribute("mensaje", ("bolos realizados en : " + fecha));
		 alerta = new Alerta( Alerta.TIPO_PRIMARY, "bolos tipò AZUL");
		 request.setAttribute("alerta", alerta); 
	 
		redirect="a";
	}
	
	
	// crear bolo
	private void crear(HttpServletRequest request) {
			//crear bolo mediante parametros del formulario para mandarlo a la bbdd
			Bolo b = new Bolo();
			Long idp = Long.parseLong(id);
			Long idpCrew = Long.parseLong(idCrew);
			
			//b.setId( (long)identificador);
			b.setLugar(lugar);
			b.setBanda1(banda1);
			b.setBanda2(banda2);
			b.setBanda3(banda3);
			b.setIdCrew(idpCrew);
			b.setInfo(info);
			
			
			//validar usuario		
			Set<ConstraintViolation<Bolo>> violations = validator.validate(b);
			
			
			if ( violations.size() > 0 ) {              // validacion NO correcta
			 
			  alerta = new Alerta( Alerta.TIPO_WARNING, "Los campos introduciodos no son correctos, por favor intentalo de nuevo");		 
			  redirect="c";
			  // volver al formulario, cuidado que no se pierdan los valores en el form
			  request.setAttribute("bolo", b);		  
			  //request.setAttribute("usuarios", daoUsuario.getAll() );
			  
			}else {									  //  validacion correcta
			
				try {
					if ( idp > 0 ) {
						boloDAO.update(b);	
						request.setAttribute("mensaje", " modificado con exito");
						alerta = new Alerta( Alerta.TIPO_SUCCESS, "alerta vertde ACTUALIZADO con exito");
						request.setAttribute("alerta", alerta); 
				
					}else {				
						boloDAO.crear(b);
						request.setAttribute("mensaje", " guardado con exito");
						alerta = new Alerta( Alerta.TIPO_DANGER, "alerta ROJA CREADO  con exito");
						request.setAttribute("alerta", alerta); 
					
					}
					
					listar(request);
					
				}catch ( SQLException e) {
					alerta = new Alerta( Alerta.TIPO_WARNING, "Lo sentimos pero el bolo ya existe");
					request.setAttribute("alerta", alerta); 
					request.setAttribute("mensaje", "Mensaje ya existe");
					redirect="a";
					  request.setAttribute("bolo", b);	
					//request.setAttribute("usuarios", daoUsuario.getAll() );
				}	
			}	
			
		}
	

}// fin
