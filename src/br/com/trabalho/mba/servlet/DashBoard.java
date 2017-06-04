package br.com.trabalho.mba.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.trabalho.mba.dao.DashBoardDAO;
import br.com.trabalho.mba.entidade.DadosATP;

/**
 * Servlet implementation class DashBoard
 */
@WebServlet("/DashBoard")
public class DashBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Service");
		// TODO Auto-generated method stub
		DashBoardDAO dao = new DashBoardDAO();
		
		//carregando dados do mysql
		
		Map<String,Integer> mapaNumeroJogosCidade = dao.recuperaNumeroJogosPorCidade();
		
		Map<String, List<DadosATP>> mapaMaioresCampeoesPorTorneio = dao.recuperaMaioresCampeoesPorTorneio();
		
		Map<String, List<DadosATP>> mapaDesempenhoPorSuperficie = dao.recuperaDesempenhoPorSuperficie();
		
		Map<String,Integer> mapaNumeroTorneioCidade = dao.recuperaQtdTorneioPorCidade();
		
		
		
		// adicionando mapas no request
		
		request.setAttribute("mapaNumeroJogosCidade", mapaNumeroJogosCidade);
		
		request.setAttribute("mapaMaioresCampeoesPorTorneio", mapaMaioresCampeoesPorTorneio);
		
		request.setAttribute("mapaDesempenhoPorSuperficie", mapaDesempenhoPorSuperficie);
		
		request.setAttribute("mapaNumeroTorneioCidade",mapaNumeroTorneioCidade);
		
		String jsp = "/dashBoard.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jsp);
		dispatcher.forward(request,response);
	}

	

}
