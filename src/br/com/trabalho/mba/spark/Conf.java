package br.com.trabalho.mba.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

public class Conf {

	private static boolean config = false;
	
	public Conf() {
		if (!config) {
			SparkConf conf = new SparkConf().setMaster("local").setAppName("ATP");
			JavaSparkContext ctx = new JavaSparkContext(conf);
			config = true;
		}
	}
	
}
