package br.com.trabalho.mba.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

}
