package br.com.trabalho.mba.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.trabalho.mba.entidade.DadosATP;

public class DashBoardDAO {

	public Map<String,Integer> recuperaNumeroJogosPorCidade(){

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement psmt = null;
		Map<String,Integer> mapa = new HashMap<String,Integer>();
		try{
			conn = Conexao.abrir();

			String sql = "select qtd_jogo,cidade from jogo_cidade order by cidade limit 10";

			psmt = conn.prepareStatement(sql);

			rs = psmt.executeQuery();

			while(rs.next()){
				mapa.put(rs.getString(2), rs.getInt(1));
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				psmt.close();
				rs.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return mapa;
	}

	public Map<String,List<DadosATP>> recuperaMaioresCampeoesPorTorneio() {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement psmt = null;
		Map<String,List<DadosATP>> mapa = new HashMap<String,List<DadosATP>>();
		List<DadosATP> lista = null;
		try{
			conn = Conexao.abrir();

			String sql = "select nome_torneio, vencedor_torneio, qtd_titulo from campeao_torneio order by nome_torneio asc,qtd_titulo desc";

			psmt = conn.prepareStatement(sql);

			rs = psmt.executeQuery();

			while(rs.next()){
				String nomeTorneio = rs.getString("nome_torneio");
				nomeTorneio = nomeTorneio.replaceAll("'","").replaceAll("�", "");
				String vencedorTorneio = rs.getString("vencedor_torneio");
				int qtdTitulo = rs.getInt("qtd_titulo");

				if(mapa.containsKey(nomeTorneio)){
					(mapa.get(nomeTorneio)).add(new DadosATP(vencedorTorneio, qtdTitulo));
				}else{

					lista = new ArrayList<DadosATP>();

					lista.add(new DadosATP(vencedorTorneio, qtdTitulo));

					mapa.put(nomeTorneio, lista);
				}

			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				psmt.close();
				rs.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return mapa;
	}

	public Map<String, List<DadosATP>> recuperaDesempenhoPorSuperficie() {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement psmt = null;
		Map<String,List<DadosATP>> mapa = new HashMap<String,List<DadosATP>>();
		List<DadosATP> lista = null;
		try{
			conn = Conexao.abrir();

			String sql = "select jogador, superficie, qtd_vitoria, qtd_derrota from desempenho_superficie order by superficie, qtd_vitoria desc";

			psmt = conn.prepareStatement(sql);

			rs = psmt.executeQuery();

			while(rs.next()){

				String superficie = rs.getString("superficie");
				String jogador = rs.getString("jogador");
				int qtd_vitoria = rs.getInt("qtd_vitoria");
				int qtd_derrota = rs.getInt("qtd_derrota");

				if(mapa.containsKey(superficie)){
					if((mapa.get(superficie)).size()<=10){
						(mapa.get(superficie)).add(new DadosATP(jogador, qtd_vitoria, qtd_derrota));
					}
				}else{

					lista = new ArrayList<DadosATP>();

					lista.add(new DadosATP(jogador, qtd_vitoria, qtd_derrota));

					mapa.put(superficie, lista);
				}

			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				psmt.close();
				rs.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return mapa;
	}

	public Map<String, Integer> recuperaQtdTorneioPorCidade() {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement psmt = null;
		Map<String,Integer> mapa = new HashMap<String,Integer>();
		try{
			conn = Conexao.abrir();

			String sql = "select qtd_torneio, cidade from torneio_cidade order by qtd_torneio desc limit 10";

			psmt = conn.prepareStatement(sql);

			rs = psmt.executeQuery();

			while(rs.next()){
				mapa.put(rs.getString("cidade"), rs.getInt("qtd_torneio"));
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				psmt.close();
				rs.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return mapa;
	}

}
