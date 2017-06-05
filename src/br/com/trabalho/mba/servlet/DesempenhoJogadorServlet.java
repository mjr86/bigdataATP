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

import br.com.trabalho.mba.entidade.Desempenho;
import br.com.trabalho.mba.processamento.DesempenhoJogador;

@WebServlet("/desempenho/jogador")
public class DesempenhoJogadorServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private DesempenhoJogador desempenhoJogador;
	
	public DesempenhoJogadorServlet() {
		desempenhoJogador = new DesempenhoJogador();
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String exec = request.getParameter("exec");
		if (exec != null && exec.equals("carregar")) {
			response.setContentType("application/json");
			PrintWriter writer = response.getWriter();
			writer.println(getDesempenho(request.getParameter("jogador")));
			writer.flush();
		} else {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/desempenho.jsp");
			dispatcher.forward(request,response);
		}
	}
	
	private String getDesempenho(String jogador) {
		String vitorias = ""; 
		Collection<Desempenho> v = desempenhoJogador.getDesempenho(jogador, true);
		for (Desempenho d : v) {
			vitorias += "{\"nome\": \"" + d.getNome() + "\", \"numSets\": \"" + d.getNumSets() + "\",  \"qtd\": \"" + d.getQtd() + "\"},";
		}
		System.out.println(vitorias);
		vitorias = "\"vitorias\": [" + vitorias.substring(0, vitorias.length() - 1) + "]";
		
		String derrotas = "";
		v = desempenhoJogador.getDesempenho(jogador, false);
		for (Desempenho d : v) {
			derrotas += "{\"nome\": \"" + d.getNome() + "\", \"numSets\": \"" + d.getNumSets() + "\",  \"qtd\": \"" + d.getQtd() + "\"},";
		}
		derrotas = "\"derrotas\": [" + derrotas.substring(0, derrotas.length() - 1) + "]";
		
		return "{" + vitorias + "," + derrotas + "}";
	}
	
}
