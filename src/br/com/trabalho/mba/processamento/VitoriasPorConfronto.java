package br.com.trabalho.mba.processamento;

import java.util.List;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import br.com.trabalho.mba.entidade.Confronto;
import br.com.trabalho.mba.entidade.Partida;
import br.com.trabalho.mba.spark.Conf;

public class VitoriasPorConfronto {
	
	public VitoriasPorConfronto() {
	}
	
	public Confronto carregarInfoConfronto(String jogador1, String jogador2) {
		new Conf();

		SparkSession spark = SparkSession.builder().appName("ATP").getOrCreate();
		Dataset<Row> dataset = spark.read().csv(AscencaoPorAno.class.getResource("/csv/Dados.csv").toString().replaceAll("%20", " "));
		
		JavaRDD<Partida> vitoriasJ1 = dataset
				.javaRDD().map(line -> {
					Partida p = new Partida();
					p.setJogador1(line.getString(9));
					p.setJogador2(line.getString(10));
					p.setVitoriasJogador1(1);
					return p;
				});
		
		JavaRDD<Partida> vitoriasJ2 = dataset
				.javaRDD().map(line -> {
					Partida p = new Partida();
					p.setJogador1(line.getString(10));
					p.setJogador2(line.getString(9));
					p.setVitoriasJogador2(1);
					return p;
				});
		
		vitoriasJ1 = vitoriasJ1.union(vitoriasJ2);
		
		Dataset<Row> set = spark.createDataFrame(vitoriasJ1, Partida.class);
		set.createOrReplaceTempView("confrontos");

		String WHERE = " WHERE (jogador1 = '" + jogador1 + "' OR jogador2 = '" + jogador1 + "') AND (jogador1 = '" + jogador2 + "' OR jogador2 = '" + jogador2 + "')";
		String SQL = "select jogador1, sum(vitoriasJogador1) as vitoriasJ1, jogador2, SUM(vitoriasJogador2) as vitoriasJ2 from confrontos " + WHERE + " group by jogador1, jogador2 order by jogador1 asc limit 1";
		
		Dataset<Row> rowDataset = spark.sql(SQL);
		List<Row> row = rowDataset.collectAsList();

		if (row != null && row.size() > 0) {
			Confronto confronto = new Confronto();
			confronto.setJogador1(row.get(0).getAs("jogador1"));
			confronto.setJogador2(row.get(0).getAs("jogador2"));
			confronto.setVitoriasJogador1(((Long) row.get(0).getAs("vitoriasJ1")).intValue());
			confronto.setVitoriasJogador2(((Long) row.get(0).getAs("vitoriasJ2")).intValue());
			return confronto;
		}
		return null;
	}
	
}
