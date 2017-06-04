package br.com.trabalho.mba.processamento;

import java.util.ArrayList;
import java.util.List;
// $example off:programmatic_schema$
// $example on:create_ds$
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.io.Serializable;
import java.text.DateFormat;
// $example off:create_ds$
import java.text.SimpleDateFormat;

import org.apache.spark.SparkConf;
// $example on:schema_inferring$
// $example on:programmatic_schema$
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
// $example off:programmatic_schema$
// $example on:create_ds$
import org.apache.spark.api.java.function.MapFunction;
// $example on:create_df$
// $example on:run_sql$
// $example on:programmatic_schema$
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
// $example off:programmatic_schema$
// $example off:create_df$
// $example off:run_sql$
import org.apache.spark.sql.Encoder;
import org.apache.spark.sql.Encoders;
// $example off:create_ds$
// $example off:schema_inferring$
import org.apache.spark.sql.RowFactory;
// $example on:init_session$
import org.apache.spark.sql.SparkSession;
// $example off:init_session$
// $example on:programmatic_schema$
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import br.com.trabalho.mba.dao.MetricasDAO;
import br.com.trabalho.mba.entidade.DadosATP;
import scala.Tuple2;

// $example off:programmatic_schema$
import org.apache.spark.sql.AnalysisException;
import static org.apache.spark.sql.functions.col;

public class NumeroTorneioPorCidadeAno {
	public static void main(String[] args) throws AnalysisException {
		// $example on:init_session$

		System.out.println(">>>>>>> torneioPorCidade >>>>>>>>>");
		// configuração do Spark
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
					dadosATP.setCidade(parts[1].trim());
					dadosATP.setTorneio(parts[2].trim());
					String ano = parts[3].split("/")[2];
					dadosATP.setAno(Integer.parseInt(ano));
					return dadosATP;

				});

		Dataset<Row> atpDF = spark.createDataFrame(dadosAtpRDD, DadosATP.class);

		// Register the DataFrame as a temporary view
		atpDF.createOrReplaceTempView("atp");

		//numero de jogos por torneio
		//String query="select count(location),tournament from atp group by tournament";

		String queryQtdTorneioPorCidade="select count(*) as qtd_torneio,cidade from (select count(*),cidade,torneio from atp where 1=1  group by cidade,torneio) t1 group by cidade order by qtd_torneio desc";
		String queryQtdTorneioPorCidadeAno = "select count(*) as qtd_torneio,cidade,ano from (select count(*),cidade,torneio,ano from atp where 1=1  group by cidade,torneio,ano) t1 group by cidade,ano";

		Dataset<Row> torneiosPorCidadeDF = spark.sql(queryQtdTorneioPorCidade);
		Dataset<Row> torneiosPorCidadeAnoDF = spark.sql(queryQtdTorneioPorCidadeAno);

		//torneiosPorCidadeDF.show();
		List<Row> listaTorneioPorCidade = torneiosPorCidadeDF.collectAsList();

		List<Row> listaTorneioPorCidadeAno = torneiosPorCidadeAnoDF.collectAsList();

		System.out.println("-------------listaTorneioDiferentesPorCidade");
		for (Row row : listaTorneioPorCidade) {

			System.out.println("total:"+row.getAs("qtd_torneio")+" cidade:"+row.getAs("cidade"));

			System.out.println("-------------");
			MetricasDAO dao = new MetricasDAO();
			//dao.insereQtdTorneioPorCidade(row.getAs("qtd_torneio"), row.getAs("cidade"));
		}

		System.out.println("-------------listaTorneioPorCidadeAno");
		for (Row row : listaTorneioPorCidadeAno) {


			System.out.println("total:"+row.getAs("qtd_torneio")+" cidade:"+row.getAs("cidade")+" ano:"+row.getAs("ano"));


		}



	}
}
