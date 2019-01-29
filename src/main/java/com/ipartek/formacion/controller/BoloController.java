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

import com.ipartek.formacion.Dao.BoloDAO;
import com.ipartek.formacion.pojo.Alerta;
import com.ipartek.formacion.pojo.Bolo;

@WebServlet("/bolo")
public class BoloController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ValidatorFactory factory;
	private Validator validator;
	private Alerta alerta1;

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
	public String alerta = "";
	// dao y objetos
	private BoloDAO boloDAO = null;
	Bolo bolo = null;

	// init para validator, dao , objetos y colecciones
	@Override
	public void init(ServletConfig config) throws ServletException {

		super.init(config);
		boloDAO = BoloDAO.getInstance();
		bolo = new Bolo();
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
			request.setAttribute("mensaje", "error fatal en LOGICA ");
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
		
		//idCrew =request.getParameter("idCrew");
		info =request.getParameter("info");
		
		op = request.getParameter("op");
	}

	private void listar(HttpServletRequest request) {
	
		request.setAttribute("bolos", boloDAO.getAll());
		request.setAttribute("mensaje", "prueba mensaje");
		redirect="a";

	}
	
	private void buscar(HttpServletRequest request) {
		Long idp = Long.parseLong(id);
		bolo=boloDAO.getById(idp);
		request.setAttribute("boloBuscar", bolo);
		request.setAttribute("mensaje", "fecha y hora");
		redirect="b";
	}

	private void fecha(HttpServletRequest request) {
		Long fechaparseo = Long.parseLong(fecha);
		request.setAttribute("bolos", boloDAO.getByFecha(fechaparseo));
		request.setAttribute("mensaje", "fecha y hora");
		redirect="a";
	}
	
	private void crear(HttpServletRequest request) {
			//crear bolo mediante parametros del formulario para mandarlo a la bbdd
			Bolo b = new Bolo();
			Long idp = Long.parseLong(id);
			
			//b.setId( (long)identificador);
			b.setLugar(lugar);
			b.setBanda1(banda1);
			b.setBanda2(banda2);
			b.setBanda3(banda3);
			//b.setIdCrew(idCrew);
			b.setInfo(info);
			
			
			//validar usuario		
			Set<ConstraintViolation<Bolo>> violations = validator.validate(b);
			
			
			if ( violations.size() > 0 ) {              // validacion NO correcta
			 
			  alerta1 = new Alerta( Alerta.TIPO_WARNING, "Los campos introduciodos no son correctos, por favor intentalo de nuevo");		 
			  redirect="c";
			  // volver al formulario, cuidado que no se pierdan los valores en el form
			  request.setAttribute("bolo", b);		  
			  //request.setAttribute("usuarios", daoUsuario.getAll() );
			  
			}else {									  //  validacion correcta
			
				try {
					if ( idp > 0 ) {
						boloDAO.update(b);				
					}else {				
						boloDAO.crear(b);
					}
					alerta1 = new Alerta( Alerta.TIPO_SUCCESS, "Registro guardado con exito");
					listar(request);
					
				}catch ( SQLException e) {
					alerta1 = new Alerta( Alerta.TIPO_WARNING, "Lo sentimos pero el EMAIL ya existe");
					redirect="a";
					  request.setAttribute("bolo", b);	
					//request.setAttribute("usuarios", daoUsuario.getAll() );
				}	
			}	
			
		}
	

}// fin
