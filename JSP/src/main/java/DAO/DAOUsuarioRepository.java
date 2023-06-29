package DAO;

import java.sql.Connection;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOUsuarioRepository {

	private Connection connection;
	
	public DAOUsuarioRepository() {
		// TODO Auto-generated constructor stub
		connection = SingleConnectionBanco.getConnection();
	}
	
	public void gravarUsuario(ModelLogin modelLogin) {
		
	}
}
