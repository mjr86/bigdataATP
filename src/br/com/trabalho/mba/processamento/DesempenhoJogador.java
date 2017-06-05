package br.com.trabalho.mba.processamento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import br.com.trabalho.mba.entidade.Desempenho;
import br.com.trabalho.mba.spark.Conf;

public class DesempenhoJogador implements Serializable {
	
	private static final long serialVersionUID = 1l;
	
	private static final int DERROTA = 10;
	private static final int VITORIA = 9;
	
	public DesempenhoJogador() {
	}
	
	public Collection<Desempenho> getDesempenho(String jogador, boolean vitoria) {
		new Conf();
		SparkSession spark = SparkSession.builder().appName("ATP").getOrCreate();
		
		JavaRDD<Desempenho> desempenho = spark.read().csv(DesempenhoJogador.class.getResource("/csv/Dados.csv").toString().replaceAll("%20", " "))
			.javaRDD().map(line -> {
				Desempenho d = new Desempenho();
				d.setNome(line.getString((vitoria ? VITORIA : DERROTA)));
				try {
					d.setNumSets(Integer.parseInt(line.getString(8)));
				} catch (NumberFormatException e) {}
				
				return d;
			}).filter(new Function<Desempenho, Boolean>() {
				private static final long serialVersionUID = 1L;
				
				@Override
				public Boolean call(Desempenho v1) throws Exception {
					return v1.getNome().equals(jogador);
				}
			}); 
		
		Dataset<Row> atpDF = spark.createDataFrame(desempenho, Desempenho.class);
		atpDF.createOrReplaceTempView("tabela");
		
		Dataset<Row> datasetRows = spark.sql("select nome, count(*) as qtd, numSets from tabela group by nome, numSets");

		List<Row> rows = datasetRows.collectAsList();
		Collection<Desempenho> desempenhos = new ArrayList<Desempenho>();
		for (Row row : rows) {
			Desempenho d = new Desempenho();
			d.setNome(row.getAs("nome"));
			d.setNumSets(row.getAs("numSets"));
			d.setQtd(((Long) row.getAs("qtd")).intValue());
			
			desempenhos.add(d);
		}
		return desempenhos;
	}
}
