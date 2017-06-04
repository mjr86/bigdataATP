package br.com.trabalho.mba.processamento;

import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import br.com.trabalho.mba.dao.MetricasDAO;
import br.com.trabalho.mba.entidade.Partida;

public class VitoriasPorConfronto {
	
	public static void main(String[] args) {

		SparkConf conf = new SparkConf().setMaster("local").setAppName("BusProcessor");
		JavaSparkContext ctx = new JavaSparkContext(conf);

		SparkSession spark = SparkSession.builder().appName("ATP").getOrCreate();
		
		Dataset<String> partidas = spark.read().textFile("C:/Users/Carlos Libanio/Downloads/jogosTenisATP.csv");
		
		JavaRDD<Partida> vitoriasJ1 = partidas
				.javaRDD().map(line -> {
					String[] parts = line.split(",");
					
					Partida p = new Partida();
					p.setJogador1(parts[9]);
					p.setJogador2(parts[10]);
					p.setVitoriasJogador1(1);
					return p;
				});
		
		JavaRDD<Partida> vitoriasJ2 = partidas
				.javaRDD().map(line -> {
					String[] parts = line.split(",");
					
					Partida p = new Partida();
					p.setJogador1(parts[10]);
					p.setJogador2(parts[9]);
					p.setVitoriasJogador2(1);
					return p;
				});
		
		vitoriasJ1 = vitoriasJ1.union(vitoriasJ2);

		Dataset<Row> set = spark.createDataFrame(vitoriasJ1, Partida.class);
		set.createOrReplaceTempView("confrontos");

		Dataset<Row> maioresCampeosPorTorneioDF = spark.sql("select jogador1, sum(vitoriasJogador1) as vitoriasJ1, jogador2, SUM(vitoriasJogador2) as vitoriasJ2 from confrontos group by jogador1, jogador2 order by jogador1 asc)");
		List<Row> listaMaioresCampeosPorTorneioDF = maioresCampeosPorTorneioDF.collectAsList();

		MetricasDAO dao = new MetricasDAO();
		for (Row row : listaMaioresCampeosPorTorneioDF) {
			Partida partida = new Partida();
			partida.setJogador1(row.getAs("jogador1"));
			partida.setJogador2(row.getAs("jogador2"));
			partida.setVitoriasJogador1(((Long) row.getAs("vitoriasJ1")).intValue());
			partida.setVitoriasJogador2(((Long) row.getAs("vitoriasJ2")).intValue());
			
			dao.insertConfrontos(partida);
		}
	}
}
