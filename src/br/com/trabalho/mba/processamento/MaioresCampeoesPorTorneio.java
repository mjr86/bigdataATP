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

public class MaioresCampeoesPorTorneio {
	
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
				    	dadosATP.setTorneio(parts[2].trim());
				    	dadosATP.setFaseTorneio(parts[7].trim());
				    	dadosATP.setVencedorTorneio(parts[9].trim());
				        return dadosATP;
			    	  
			      });
		
		Dataset<Row> atpDF = spark.createDataFrame(dadosAtpRDD, DadosATP.class);
		
		// Register the DataFrame as a temporary view
		atpDF.createOrReplaceTempView("atp");
		
		String queryMaioresCampeosPorTorneio="select torneio, vencedorTorneio, count(*) as total from atp where 1=1 and faseTorneio = 'The Final' group by vencedorTorneio,torneio order by total desc";
		
		Dataset<Row> maioresCampeosPorTorneioDF = spark.sql(queryMaioresCampeosPorTorneio);

		//torneiosPorCidadeDF.show();
		List<Row> listaMaioresCampeosPorTorneioDF = maioresCampeosPorTorneioDF.collectAsList();
		
		System.out.println("-------------listaMaioresCampeosPorTorneioDF");
		for (Row row : listaMaioresCampeosPorTorneioDF) {
			
			System.out.println("torneio:"+row.getAs("torneio")+" vencedorTorneio:"+row.getAs("vencedorTorneio") + " qtdTitulos"+row.getAs("total"));
			
			System.out.println("-------------");
			MetricasDAO dao = new MetricasDAO();
			try {
				//dao.insereCampeoesPorTorneio(row.getAs("torneio"), row.getAs("vencedorTorneio"), row.getAs("total"));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
