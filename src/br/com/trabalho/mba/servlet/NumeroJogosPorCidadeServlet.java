package br.com.trabalho.mba.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.trabalho.mba.dao.DashBoardDAO;

@WebServlet("/jogos/cidade")
public class NumeroJogosPorCidadeServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DashBoardDAO dao = new DashBoardDAO();
		Map<String,Integer> mapaNumeroJogosCidade = dao.recuperaNumeroJogosPorCidade();
		request.setAttribute("mapaNumeroJogosCidade", mapaNumeroJogosCidade);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/numeroJogosCidade.jsp");
		dispatcher.forward(request,response);
	}

}
