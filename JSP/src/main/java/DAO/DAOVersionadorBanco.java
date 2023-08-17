package DAO;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.SingleConnectionBanco;

public class DAOVersionadorBanco implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Connection connection;
	
	public DAOVersionadorBanco() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public void gravaArquivoSqlRodado(String nome_file) throws Exception{
		String sql = "INSERT INTO versionadorbanco(arquivo_sql) VALUES (?);";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, nome_file);
		statement.execute();
	}
	
	public boolean arquivoSqlRodado(String nome_do_arquivo) throws SQLException {
		String sql = "SELECT count(1) > 0 AS rodado FROM versionadorbanco WHERE arquivo_sql = ?";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, nome_do_arquivo);
		
		ResultSet rs = statement.executeQuery();
		
		rs.next();
		
		return rs.getBoolean("rodado");	
	}
}
