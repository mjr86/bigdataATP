package br.com.trabalho.mba.entidade;

import java.io.Serializable;

public class Desempenho implements Serializable {
	
	private static final long serialVersionUID = 1l;
	
	private String nome;
	private int numSets;
	private int qtd;
	
	public Desempenho() {
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getNumSets() {
		return numSets;
	}

	public void setNumSets(int numSets) {
		this.numSets = numSets;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}
	
}
