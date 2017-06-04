package br.com.trabalho.mba.processamento;

import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import br.com.trabalho.mba.dao.MetricasDAO;
import br.com.trabalho.mba.entidade.DadosATP;

public class VitoriasJogadorPorSuperficieDeQuadra {

	public static void main(String[] args) {

		SparkConf conf = new SparkConf().setMaster("local").setAppName("BusProcessor");

		JavaSparkContext ctx = new JavaSparkContext(conf);

		SparkSession spark = SparkSession
				.builder()
				.appName("Java Spark SQL basic example")
				.config("spark.some.config.option", "some-value")
				.getOrCreate();

		JavaRDD<DadosATP> dadosAtpRDD = spark.read()
				.textFile("/Users/marciojunior/Documents/mba/mba-bigdata/dataSets/atp-tour-20002016/Data.txt")
				.javaRDD()
				.map(line -> {
					String[] parts = line.split(",");
					DadosATP dadosATP = new DadosATP();
					dadosATP.setTipoSuperficieQuadra(parts[6].trim());
					dadosATP.setJogadorVencedor(parts[9].trim());
					dadosATP.setJogadorPerdedor(parts[10].trim());
					return dadosATP;

				});

		Dataset<Row> atpDF = spark.createDataFrame(dadosAtpRDD, DadosATP.class);

		// Register the DataFrame as a temporary view
		atpDF.createOrReplaceTempView("atp");

		String queryVitoriasPorSuperficieQuadra= "SELECT  t2.tipoSuperficieQuadra, t2.jogadorVencedor, count(*) total_vitorias FROM atp t2 WHERE 1=1  GROUP BY t2.jogadorVencedor,t2.tipoSuperficieQuadra";
		String queryDerrotasPorSuperficieQuadra="SELECT  t2.tipoSuperficieQuadra, t2.jogadorPerdedor, count(*) total_derrotas FROM atp t2 WHERE 1=1  GROUP BY t2.jogadorPerdedor,t2.tipoSuperficieQuadra";

		Dataset<Row> vitoriasPorSuperficieQuadraDF = spark.sql(queryVitoriasPorSuperficieQuadra);
		Dataset<Row> derrotasPorSuperficieQuadraDF = spark.sql(queryDerrotasPorSuperficieQuadra);

		//torneiosPorCidadeDF.show();
		List<Row> listaVitoriasPorSuperficieQuadra = vitoriasPorSuperficieQuadraDF.collectAsList();
		List<Row> listaDerrotasPorSuperficieQuadra = derrotasPorSuperficieQuadraDF.collectAsList();

		System.out.println("-------------vitorias por superficie");
		for (Row row : listaVitoriasPorSuperficieQuadra) {

			System.out.println("total_vitorias:"+row.getAs("total_vitorias")+" tipoSuperficieQuadra:"+row.getAs("tipoSuperficieQuadra")+" jogador:"+row.getAs("jogadorVencedor"));

			System.out.println("-------------");
			
			MetricasDAO dao = new MetricasDAO();
			//dao.insereDesempenhoPorSuperficie(row.getAs("jogadorVencedor"), row.getAs("tipoSuperficieQuadra"), row.getAs("total_vitorias"), false);
		}
		System.out.println("-------------derrotas por superficie");
		for (Row row : listaDerrotasPorSuperficieQuadra) {

			System.out.println("total_derrotas:"+row.getAs("total_derrotas")+" tipoSuperficieQuadra:"+row.getAs("tipoSuperficieQuadra")+" jogador:"+row.getAs("jogadorPerdedor"));

			System.out.println("-------------");
			
			MetricasDAO dao = new MetricasDAO();
			//dao.updateDesempenhoPorSuperficie(row.getAs("jogadorPerdedor"), row.getAs("tipoSuperficieQuadra"), row.getAs("total_derrotas"));
		}
	}
}
