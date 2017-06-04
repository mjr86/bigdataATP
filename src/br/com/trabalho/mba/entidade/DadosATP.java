package br.com.trabalho.mba.entidade;

import java.io.Serializable;

public class DadosATP implements Serializable {
	
	/**
	 * UID
	 */
	private static final long serialVersionUID = 4825327089578803013L;
	
	private String cidade; 
	private String torneio;
	private int ano;
	
	private String faseTorneio;
	private String vencedorTorneio;
	
	private String jogadorVencedor;
	private String jogadorPerdedor;
	private String tipoSuperficieQuadra;
	
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
	
	
}
