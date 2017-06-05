package br.com.trabalho.mba.entidade;

public class Confronto {
	
	private String jogador1;
	private int vitoriasJogador1;
	private String jogador2;
	private int vitoriasJogador2;
	
	public Confronto() {
	}

	public String getJogador1() {
		return jogador1;
	}

	public void setJogador1(String jogador1) {
		this.jogador1 = jogador1;
	}

	public int getVitoriasJogador1() {
		return vitoriasJogador1;
	}

	public void setVitoriasJogador1(int vitoriasJogador1) {
		this.vitoriasJogador1 = vitoriasJogador1;
	}

	public String getJogador2() {
		return jogador2;
	}

	public void setJogador2(String jogador2) {
		this.jogador2 = jogador2;
	}

	public int getVitoriasJogador2() {
		return vitoriasJogador2;
	}

	public void setVitoriasJogador2(int vitoriasJogador2) {
		this.vitoriasJogador2 = vitoriasJogador2;
	}
	
	@Override
	public String toString() {
		return "{\"jogador1\": \"" + jogador1 + "\", \"jogador2\": \"" + jogador2 + "\", \"vitoriasJogador1\": " + vitoriasJogador1 + ", \"vitoriasJogador2\": " + vitoriasJogador2 + "}";
	}
	
}
