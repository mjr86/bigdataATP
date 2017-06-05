package br.com.trabalho.mba.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.trabalho.mba.entidade.Confronto;
import br.com.trabalho.mba.processamento.VitoriasPorConfronto;

@WebServlet("/confronto")
public class ConfrontoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private VitoriasPorConfronto vitoriasPorConfronto;
	
	public ConfrontoServlet() {
		vitoriasPorConfronto = new VitoriasPorConfronto();
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String exec = request.getParameter("exec");
		if (exec != null && exec.equals("carregar")) {
			response.setContentType("application/json");
			PrintWriter writer = response.getWriter();
			Confronto confronto = vitoriasPorConfronto.carregarInfoConfronto(request.getParameter("jogador1"), request.getParameter("jogador2"));
			if (confronto != null) {
				writer.println(confronto);
			} else {
				writer.println("{}");
			}
			writer.flush();
		} else {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/confronto.jsp");
			dispatcher.forward(request,response);
		}
	}
	
}
