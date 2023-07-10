package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOUsuarioRepository {

	private Connection connection;

	public DAOUsuarioRepository() {
		// TODO Auto-generated constructor stub
		connection = SingleConnectionBanco.getConnection();
	}

	public ModelLogin gravarUsuario(ModelLogin modelLogin){
		
		try {
			if (modelLogin.isNovo()) {
			
			String sql = "INSERT INTO model_login(login, senha, nome, email) VALUES (?, ?, ?, ?);";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, modelLogin.getLogin());
			statement.setString(2, modelLogin.getSenha());
			statement.setString(3, modelLogin.getNome());
			statement.setString(4, modelLogin.getEmail());

			statement.execute();
			connection.commit();
			
		} else {
			String sql = "UPDATE model_login SET login=?, senha=?, nome=?, email=? WHERE id = ?;";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, modelLogin.getLogin());
			statement.setString(2, modelLogin.getSenha());
			statement.setString(3, modelLogin.getNome());
			statement.setString(4, modelLogin.getEmail());
			statement.setLong(5, modelLogin.getId());

			statement.executeUpdate();
			connection.commit();
		}
		return this.consultaUsuario(modelLogin.getLogin());
		
		} catch (Exception e) {
			// TODO: handle exception
			try {
				connection.rollback();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			return null;
		}		
	}
	
	public List<ModelLogin> consultaUsuarioList(String nome) throws Exception {

		List<ModelLogin> retorno = new ArrayList<ModelLogin>();

		String sql = "select * from model_login  where upper(nome) like upper(?) ";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, "%" + nome + "%");

		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) { /* percorrer as linhas de resultado do SQL */

			ModelLogin modelLogin = new ModelLogin();

			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setNome(resultado.getString("nome"));
			// modelLogin.setSenha(resultado.getString("senha"));

			retorno.add(modelLogin);
		}

		return retorno;
	}

	public ModelLogin consultaUsuario(String login) {

		ModelLogin modelLogin = new ModelLogin();

		try {
			String sql = "SELECT*FROM model_login WHERE login = ?;";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, login);
			ResultSet resultado = statement.executeQuery();

			while (resultado.next()) {
				modelLogin.setId(resultado.getLong("id"));
				modelLogin.setNome(resultado.getString("nome"));
				modelLogin.setEmail(resultado.getString("email"));
				modelLogin.setLogin(resultado.getString("login"));
				modelLogin.setSenha(resultado.getString("senha"));
			}
			return modelLogin;

		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	public ModelLogin consultaUsuarioId(String id) {

		ModelLogin modelLogin = new ModelLogin();

		try {
			String sql = "SELECT*FROM model_login WHERE id = ?;";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(1, Long.parseLong(id));
			ResultSet resultado = statement.executeQuery();

			while (resultado.next()) {
				modelLogin.setId(resultado.getLong("id"));
				modelLogin.setNome(resultado.getString("nome"));
				modelLogin.setEmail(resultado.getString("email"));
				modelLogin.setLogin(resultado.getString("login"));
				modelLogin.setSenha(resultado.getString("senha"));
			}
			return modelLogin;

		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public boolean validarLogin(String login) {

		try {
			String sql = "SELECT count(1) > 0 AS existe FROM model_login WHERE login = ?;";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, login);
			ResultSet resultado = statement.executeQuery();

			resultado.next();

			return resultado.getBoolean("existe");

		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	public boolean validarEmail(String email) {

		try {
			String sql = "SELECT count(1) > 0 AS existe FROM model_login WHERE email = ?;";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, email);
			ResultSet resultado = statement.executeQuery();

			resultado.next();

			return resultado.getBoolean("existe");

		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	public boolean deletar_registro(String id) {
		
		try {
			String sql = "DELETE FROM model_login WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(1,Long.parseLong(id));
			
			statement.executeUpdate();
			connection.commit();
			
			return true;
			
		} catch (Exception e) {
			// TODO: handle exception
			try {
				connection.rollback();
			} catch (Exception e2) {
				// TODO: handle exception
				
			}
		}
		
		return false;
	}
}



















