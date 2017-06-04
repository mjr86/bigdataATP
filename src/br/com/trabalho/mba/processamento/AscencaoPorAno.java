package br.com.trabalho.mba.processamento;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import br.com.trabalho.mba.entidade.JogadorRanking;
import br.com.trabalho.mba.spark.Conf;

public class AscencaoPorAno {
	
	public AscencaoPorAno() {
	}
	
	public Collection<JogadorRanking> carregar(String nomeJogador) {
		new Conf();

		SparkSession spark = SparkSession.builder().appName("ATP").getOrCreate();
		Dataset<Row> dataset = spark.read().csv(AscencaoPorAno.class.getResource("/csv/Dados.csv").toString().replaceAll("%20", " "));
		
		JavaRDD<JogadorRanking> jogador = dataset 
				.javaRDD().map(line -> {
					JogadorRanking jr = new JogadorRanking();
					jr.setNome(line.getString(9));
					jr.setRanking(line.getString(11));
					jr.setData(line.getString(3));
					
					return jr;
				});
		
		JavaRDD<JogadorRanking> jogadorPerdedor = dataset
				.javaRDD().map(line -> {
					JogadorRanking jr = new JogadorRanking();
					jr.setNome(line.getString(10));
					jr.setRanking(line.getString(12));
					jr.setData(line.getString(3));
					
					return jr;
				});

		jogador = jogador.union(jogadorPerdedor);
		
		Dataset<Row> atpDF = spark.createDataFrame(jogador, JogadorRanking.class);
		atpDF.createOrReplaceTempView("jogadores");

		Dataset<Row> dataSetRows = spark.sql("SELECT nome, MIN(ranking) AS ranking, split(data, '/')[2] AS ano FROM jogadores WHERE nome = '" + nomeJogador + "' GROUP BY nome, split(data, '/')[2] ORDER BY split(data, '/')[2] ASC");
		List<Row> rows = dataSetRows.collectAsList();

		Collection<JogadorRanking> rankings = new ArrayList<JogadorRanking>();
		for (Row row : rows) {
			JogadorRanking jr = new JogadorRanking();
			jr.setNome(row.getAs("nome"));
			jr.setRanking(row.getAs("ranking"));
			jr.setData(row.getAs("ano"));

			rankings.add(jr);
		}
		
		return rankings;
	}
	
}
