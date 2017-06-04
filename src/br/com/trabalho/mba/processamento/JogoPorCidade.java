package br.com.trabalho.mba.processamento;

import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import br.com.trabalho.mba.dao.MetricasDAO;
import scala.Tuple2;

public class JogoPorCidade {
	public static void main(String[] args) {
		JogoPorCidade p = new JogoPorCidade();

		p.jogosPorCidade();

	}

	public void jogosPorCidade() {
		System.out.println(">>>>>>> jogosPorCidade >>>>>>>>>");
		// configuração do Spark
		SparkConf conf = new SparkConf().setMaster("local").setAppName("BusProcessor");
		JavaSparkContext ctx = new JavaSparkContext(conf);

		// carrega os dados
		JavaRDD<String> dadosATP = ctx
				.textFile("/Users/marciojunior/Documents/mba/mba-bigdata/dataSets/atp-tour-20002016/Data.txt");
   
		JavaPairRDD<String, Integer> agrupaCidade = dadosATP
				.mapToPair(s -> new Tuple2<String, Integer>(s.split(",")[1], 1));

		// .mapToPair(s -> new Tuple2<String, Integer>(s.split(",")[1], 1))
		JavaPairRDD<String, Integer> numeroJogos = agrupaCidade.reduceByKey((x, y) -> x + y);

		List<Tuple2<String, Integer>> lista = numeroJogos.collect();

		// mostra as linhas e o número de ônibus da linha
		for (Tuple2<String, Integer> cidadeJogos : lista) {
			System.out.println("Cidade: " + cidadeJogos._1());
			System.out.println("Número de jogos: " + cidadeJogos._2());
			MetricasDAO dao = new MetricasDAO();
			//dao.insereQtdJogoPorCidade(cidadeJogos._1().toString(), cidadeJogos._2().longValue());
		}

	}

}
