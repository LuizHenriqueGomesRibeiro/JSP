package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOUsuarioRepository {

	private Connection connection;

	public DAOUsuarioRepository() {
		// TODO Auto-generated constructor stub
		connection = SingleConnectionBanco.getConnection();
	}

	public ModelLogin gravarUsuario(ModelLogin modelLogin) {

		try {
			String sql = "INSERT INTO model_login(login, senha, nome, email) VALUES (?, ?, ?, ?);";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, modelLogin.getLogin());
			statement.setString(2, modelLogin.getSenha());
			statement.setString(3, modelLogin.getNome());
			statement.setString(4, modelLogin.getEmail());

			statement.execute();
			connection.commit();
			
			return this.consultaUsuario(modelLogin.getLogin());
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			
			return null;
		}
	}
	
	public ModelLogin consultaUsuario(String login) {
		
		ModelLogin modelLogin = new ModelLogin();
		
		
		try {
			String sql = "SELECT*FROM model_login WHERE login = ?;";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, login);
			ResultSet resultado=statement.executeQuery();
			
			while(resultado.next()) {
				modelLogin.setId(resultado.getLong("id"));
				modelLogin.setNome(resultado.getString("nome"));
				modelLogin.setNome(resultado.getString("email"));
				modelLogin.setNome(resultado.getString("login"));
				modelLogin.setNome(resultado.getString("senha"));
			}
			
			return modelLogin;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	
	
}
