package br.com.trabalho.mba.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.trabalho.mba.entidade.Partida;

public class MetricasDAO {

	public static void main(String[] args) {
		try {
			//insereCampeoesPorTorneio("abc","joao",2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void insereQtdJogoPorCidade(String cidade, Long qtdJogo) {

		String sql = null;

		sql = "insert into jogo_cidade(qtd_jogo, cidade) values(?,?)";

		Connection conn = null;
		PreparedStatement psmt = null;
		try{
			conn = Conexao.abrir();

			psmt = conn.prepareStatement(sql);

			psmt.setInt(1, qtdJogo.intValue());
			psmt.setString(2, cidade);

			psmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				psmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public void updateDesempenhoPorSuperficie(String jogador, String superficie, Long qtdDerrota){
		String sql = "update desempenho_superficie set qtd_derrota = ? where jogador = ? and superficie = ? ";
		Connection conn = null;
		PreparedStatement psmt = null;
		try{
			conn = Conexao.abrir();

			psmt = conn.prepareStatement(sql);

			psmt.setInt(1, qtdDerrota.intValue());
			psmt.setString(2, jogador);
			psmt.setString(3, superficie);

			int update = psmt.executeUpdate();
			System.out.println("update:"+update);

			if(update==0){
				insereDesempenhoPorSuperficie(jogador, superficie, qtdDerrota, true);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				psmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	public void insereDesempenhoPorSuperficie(String jogador, String superficie, Long qtd, boolean ehDerrota) {

		String sql = null;

		if(!ehDerrota){
			sql = "insert into desempenho_superficie(jogador, superficie, qtd_vitoria) values(?,?,?)";
		}else{
			sql = "insert into desempenho_superficie(jogador, superficie, qtd_derrota) values(?,?,?)";
		}
		Connection conn = null;
		PreparedStatement psmt = null;
		try{
			conn = Conexao.abrir();

			psmt = conn.prepareStatement(sql);

			psmt.setString(1, jogador);
			psmt.setString(2, superficie);
			psmt.setInt(3, qtd.intValue());


			psmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				psmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void insereQtdTorneioPorCidade(Long qtdTorneio, String cidade) {
		String sql = "insert into torneio_cidade(qtd_torneio, cidade) values(?,?)";
		Connection conn = null;
		PreparedStatement psmt = null;
		try{
			conn = Conexao.abrir();

			psmt = conn.prepareStatement(sql);

			psmt.setInt(1,qtdTorneio.intValue());
			psmt.setString(2,cidade);

			psmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				psmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	public void insereCampeoesPorTorneio(String torneio,String vencedor, Long qtdTitulo){
		String sql = "insert into campeao_torneio(nome_torneio, vencedor_torneio, qtd_titulo) values(?,?,?)";
		Connection conn = null;
		PreparedStatement psmt = null;
		try{
			conn = Conexao.abrir();

			psmt = conn.prepareStatement(sql);

			psmt.setString(1,torneio);
			psmt.setString(2,vencedor);
			psmt.setInt(3,qtdTitulo.intValue());

			psmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				psmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}
	
	public void insertConfrontos(Partida partida) {
		String sql = "insert into confronto(jogador1, jogador2, vitoriasJogador1, vitoriasJogador2) values(?,?,?,?)";
		Connection conn = null;
		PreparedStatement psmt = null;
		try{
			conn = Conexao.abrir();

			psmt = conn.prepareStatement(sql);

			psmt.setString(1, partida.getJogador1());
			psmt.setString(2, partida.getJogador2());
			psmt.setString(3, String.valueOf(partida.getVitoriasJogador1()));
			psmt.setString(4, String.valueOf(partida.getVitoriasJogador2()));

			psmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				psmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

}
