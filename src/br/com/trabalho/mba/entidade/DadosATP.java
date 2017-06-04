package br.com.trabalho.mba.entidade;

import java.io.Serializable;

public class DadosATP implements Serializable {
	
	/**
	 * UID
	 */
	private static final long serialVersionUID = 4825327089578803013L;
	public DadosATP(){}
	
	public DadosATP(String vencedorTorneio, int qtdTitulo) {
		super();
		this.vencedorTorneio = vencedorTorneio;
		this.qtdTitulo = qtdTitulo;
	}
	
	public DadosATP(String jogador, int qtdVitoria, int qtdDerrota) {
		this.setJogador(jogador);
		this.setQtdVitoria(qtdVitoria);
		this.setQtdDerrota(qtdDerrota);
	}
	
	private String cidade; 
	private String torneio;
	private int ano;
	
	private String faseTorneio;
	private String vencedorTorneio;
	
	private String jogadorVencedor;
	private String jogadorPerdedor;
	private String tipoSuperficieQuadra;
	
	private int qtdTitulo;
	
	private String jogador;
	private int qtdVitoria;
	private int qtdDerrota;
	
	
	
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getTorneio() {
		return torneio;
	}
	public void setTorneio(String torneio) {
		this.torneio = torneio;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public String getFaseTorneio() {
		return faseTorneio;
	}
	public void setFaseTorneio(String faseTorneio) {
		this.faseTorneio = faseTorneio;
	}
	public String getVencedorTorneio() {
		return vencedorTorneio;
	}
	public void setVencedorTorneio(String vencedorTorneio) {
		this.vencedorTorneio = vencedorTorneio;
	}
	public String getTipoSuperficieQuadra() {
		return tipoSuperficieQuadra;
	}
	public void setTipoSuperficieQuadra(String tipoSuperficieQuadra) {
		this.tipoSuperficieQuadra = tipoSuperficieQuadra;
	}
	public String getJogadorVencedor() {
		return jogadorVencedor;
	}
	public void setJogadorVencedor(String jogadorVencedor) {
		this.jogadorVencedor = jogadorVencedor;
	}
	public String getJogadorPerdedor() {
		return jogadorPerdedor;
	}
	public void setJogadorPerdedor(String jogadorPerdedor) {
		this.jogadorPerdedor = jogadorPerdedor;
	}
	public int getQtdTitulo() {
		return qtdTitulo;
	}
	public void setQtdTitulo(int qtdTitulo) {
		this.qtdTitulo = qtdTitulo;
	}
	public String getJogador() {
		return jogador;
	}
	public void setJogador(String jogador) {
		this.jogador = jogador;
	}
	public int getQtdVitoria() {
		return qtdVitoria;
	}
	public void setQtdVitoria(int qtdVitoria) {
		this.qtdVitoria = qtdVitoria;
	}
	public int getQtdDerrota() {
		return qtdDerrota;
	}
	public void setQtdDerrota(int qtdDerrota) {
		this.qtdDerrota = qtdDerrota;
	}
	
	
	
}
