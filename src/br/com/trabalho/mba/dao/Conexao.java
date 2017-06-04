package br.com.trabalho.mba.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

	private static final String USUARIO = "root";
	private static final String SENHA = "root";
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/bigdata";
	private static final String DRIVER = "com.mysql.jdbc.Driver";

	// Conectar ao banco
	public static Connection abrir() throws Exception {
		Class.forName(DRIVER);
		Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
		return conn;
	}

}