package br.com.trabalho.mba.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.trabalho.mba.entidade.JogadorRanking;
import br.com.trabalho.mba.processamento.AscencaoPorAno;

@WebServlet("/ascencao")
public class AscencaoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private AscencaoPorAno ascencaoPorAno;
	
	public AscencaoServlet() {
		ascencaoPorAno = new AscencaoPorAno();
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String exec = request.getParameter("exec");
		if (exec != null) {
			if (exec.equals("carregar")) {
				response.setContentType("application/json");
				PrintWriter writer = response.getWriter();
				writer.println(carregarHistoricoDoJogador(request.getParameter("nomeJogador")));
				writer.flush();
			}
		} else {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ascencao.jsp");
			dispatcher.forward(request,response);
		}
	}
	
	private String carregarHistoricoDoJogador(String jogador) {
		Collection<JogadorRanking> ranking = ascencaoPorAno.carregar(jogador);
		
		String json = "";
		for (JogadorRanking jr : ranking) {
			json += "{\"nome\": \"" + jr.getNome() + "\", \"ranking\": " + jr.getRanking() + ", \"ano\": \"" + jr.getData() + "\"},";
		}
		json = json.substring(0, json.length() - 1);
		 
		return "[" + json + "]";
	}
	
}
