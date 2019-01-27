package com.ipartek.formacion.controller;

import java.io.IOException;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.Dao.BoloDAO;

import com.ipartek.formacion.pojo.Bolo;

@WebServlet("/bolo")
public class BoloController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// variables parametros

	public String id = "";
	public String op = "";
	

	// dao y objetos
	private BoloDAO boloDAO = null;
	Bolo bolo = null;

	// init para validator, dao , objetos y colecciones
	@Override
	public void init(ServletConfig config) throws ServletException {

		super.init(config);
		boloDAO = BoloDAO.getInstance();
		bolo = new Bolo();

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

			}

		} catch (Exception e) {
			request.setAttribute("mensaje", "error fatal en LOGICA ");
		} finally {

			request.getRequestDispatcher("listar.jsp").forward(request, response);

		}

	}// fin doproces

	private void getParametros(HttpServletRequest request) {
		// palabra parametros en un string
		id = request.getParameter("id");
		op = request.getParameter("op");
	

	

	}

	private void listar(HttpServletRequest request) {
		int idParseo = Integer.parseInt(id);	
		request.setAttribute("bolo", boloDAO.getById( (long)idParseo));

	}

}// fin
