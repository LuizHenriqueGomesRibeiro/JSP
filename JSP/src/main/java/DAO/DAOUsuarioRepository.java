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

	public ModelLogin gravarUsuario(ModelLogin modelLogin, Long userLogado) {

		try {
			if (modelLogin.isNovo()) {

				String sql = "INSERT INTO model_login(login, senha, nome, email, usuario_id, perfil, sexo, cep, rua, bairro, localidade, uf, numero)  VALUES (?, ?, ?, ?, ?, ?,?, ?, ?, ?,?,?,?);";
				PreparedStatement statement = connection.prepareStatement(sql);
				
				statement.setString(1, modelLogin.getLogin());
				statement.setString(2, modelLogin.getSenha());
				statement.setString(3, modelLogin.getNome());
				statement.setString(4, modelLogin.getEmail());
				statement.setLong(5, userLogado);
				statement.setString(6, modelLogin.getPerfil());
				statement.setString(7, modelLogin.getSexo());
				
				statement.setString(8, modelLogin.getCEP());
				statement.setString(9, modelLogin.getRua());
				statement.setString(10, modelLogin.getBairro());
				statement.setString(11, modelLogin.getLocalidade());
				statement.setString(12, modelLogin.getUf());
				statement.setString(13, modelLogin.getNumero());
				
				statement.execute();
				
				connection.commit();
				
				if (modelLogin.getFotoUser() != null && !modelLogin.getFotoUser().isEmpty()) {
					sql = "update model_login set fotouser =?, extensaofotouser=? where login =?";
					
					statement = connection.prepareStatement(sql);
					
					statement.setString(1, modelLogin.getFotoUser());
					statement.setString(2, modelLogin.getExtensaofotouser());
					statement.setString(3, modelLogin.getLogin());
					
					statement.execute();
					
					connection.commit();
				}

			} else {
				String sql = "UPDATE model_login SET login=?, senha=?, nome=?, email=?, perfil=?, sexo=?, cep=?, rua=?, bairro =?, localidade=?, uf=?, numero =? WHERE id =  "+modelLogin.getId()+";";
				
				PreparedStatement	statement = connection.prepareStatement(sql);
				
				statement.setString(1, modelLogin.getLogin());
				statement.setString(2, modelLogin.getSenha());
				statement.setString(3, modelLogin.getNome());
				statement.setString(4, modelLogin.getEmail());
				statement.setString(5, modelLogin.getPerfil());
				statement.setString(6, modelLogin.getSexo());
			
				statement.setString(7, modelLogin.getCEP());
				statement.setString(8, modelLogin.getRua());
				statement.setString(9, modelLogin.getBairro());
				statement.setString(10, modelLogin.getLocalidade());
				statement.setString(11, modelLogin.getUf());
				statement.setString(12, modelLogin.getNumero());
				
				statement.executeUpdate();
				
				connection.commit();
				
				
				if (modelLogin.getFotoUser() != null && !modelLogin.getFotoUser().isEmpty()) {
					sql = "UPDATE model_login set fotouser =?, extensaofotouser=? where id =?";
					
					statement = connection.prepareStatement(sql);
					
					statement.setString(1, modelLogin.getFotoUser());
					statement.setString(2, modelLogin.getExtensaofotouser());
					statement.setLong(3, modelLogin.getId());
					
					statement.execute();
					
					connection.commit();
				}
				
			}
			return this.consultaUsuario(modelLogin.getLogin(), userLogado);

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

	public List<ModelLogin> consultaUsuarioList(Long userLogado) throws Exception {

		List<ModelLogin> retorno = new ArrayList<ModelLogin>();

		String sql = "SELECT * FROM model_login WHERE useradmin IS FALSE AND usuario_id = " + userLogado;
		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) { /* percorrer as linhas de resultado do SQL */

			ModelLogin modelLogin = new ModelLogin();

			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setSexo(resultado.getString("sexo"));
			// modelLogin.setSenha(resultado.getString("senha"));

			retorno.add(modelLogin);
		}

		return retorno;
	}

	public List<ModelLogin> consultaUsuarioList(String nome, Long userLogado) throws Exception {

		List<ModelLogin> retorno = new ArrayList<ModelLogin>();

		String sql = "SELECT * FROM model_login WHERE nome LIKE ? AND useradmin IS FALSE AND usuario_id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, "%" + nome + "%");
		statement.setLong(2, userLogado);

		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) { /* percorrer as linhas de resultado do SQL */

			ModelLogin modelLogin = new ModelLogin();

			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setSexo(resultado.getString("sexo"));
			modelLogin.setCEP(resultado.getString("cep"));
			modelLogin.setRua(resultado.getString("rua"));
			modelLogin.setBairro(resultado.getString("bairro"));
			modelLogin.setLocalidade(resultado.getString("localidade"));
			modelLogin.setUf(resultado.getString("uf"));
			modelLogin.setNumero(resultado.getString("numero"));

			retorno.add(modelLogin);
		}

		return retorno;
	}

	public ModelLogin consultaUsuarioLogado(String login) {

		ModelLogin modelLogin = new ModelLogin();

		try {
			String sql = "SELECT*FROM model_login WHERE login = ?";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, login);
			ResultSet resultado = statement.executeQuery();

			while (resultado.next()) {
				modelLogin.setId(resultado.getLong("id"));
				modelLogin.setLogin(resultado.getString("login"));
				modelLogin.setUseradmin(resultado.getBoolean("useradmin"));
				modelLogin.setPerfil(resultado.getString("perfil"));
				modelLogin.setSexo(resultado.getString("sexo"));
				modelLogin.setFotoUser(resultado.getString("fotouser"));
				modelLogin.setExtensaofotouser(resultado.getString("extensaofotouser"));
				modelLogin.setCEP(resultado.getString("cep"));
				modelLogin.setRua(resultado.getString("rua"));
				modelLogin.setBairro(resultado.getString("bairro"));
				modelLogin.setLocalidade(resultado.getString("localidade"));
				modelLogin.setUf(resultado.getString("uf"));
				modelLogin.setNumero(resultado.getString("numero"));
			}
			return modelLogin;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

			return null;
		}
	}

	public ModelLogin consultaUsuario(String login) {

		ModelLogin modelLogin = new ModelLogin();

		try {
			String sql = "SELECT*FROM model_login WHERE login = ? AND useradmin IS FALSE";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, login);
			ResultSet resultado = statement.executeQuery();

			while (resultado.next()) {
				modelLogin.setId(resultado.getLong("id"));
				modelLogin.setNome(resultado.getString("nome"));
				modelLogin.setEmail(resultado.getString("email"));
				modelLogin.setLogin(resultado.getString("login"));
				modelLogin.setSenha(resultado.getString("senha"));
				modelLogin.setPerfil(resultado.getString("perfil"));
				modelLogin.setSexo(resultado.getString("sexo"));
				modelLogin.setFotoUser(resultado.getString("fotouser"));
				modelLogin.setExtensaofotouser(resultado.getString("extensaofotouser"));
				modelLogin.setCEP(resultado.getString("cep"));
				modelLogin.setRua(resultado.getString("rua"));
				modelLogin.setBairro(resultado.getString("bairro"));
				modelLogin.setLocalidade(resultado.getString("localidade"));
				modelLogin.setUf(resultado.getString("uf"));
				modelLogin.setNumero(resultado.getString("numero"));
			}
			return modelLogin;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return modelLogin;
	}

	public ModelLogin consultaUsuario(String login, Long userLogado) {

		ModelLogin modelLogin = new ModelLogin();

		try {
			String sql = "SELECT*FROM model_login WHERE login = ? AND useradmin IS FALSE AND usuario_id = ?";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, login);
			statement.setLong(2, userLogado);
			ResultSet resultado = statement.executeQuery();

			while (resultado.next()) {
				modelLogin.setId(resultado.getLong("id"));
				modelLogin.setNome(resultado.getString("nome"));
				modelLogin.setEmail(resultado.getString("email"));
				modelLogin.setLogin(resultado.getString("login"));
				modelLogin.setSenha(resultado.getString("senha"));
				modelLogin.setPerfil(resultado.getString("perfil"));
				modelLogin.setSexo(resultado.getString("sexo"));
				modelLogin.setFotoUser(resultado.getString("fotouser"));
				modelLogin.setExtensaofotouser(resultado.getString("extensaofotouser"));
				modelLogin.setCEP(resultado.getString("cep"));
				modelLogin.setRua(resultado.getString("rua"));
				modelLogin.setBairro(resultado.getString("bairro"));
				modelLogin.setLocalidade(resultado.getString("localidade"));
				modelLogin.setUf(resultado.getString("uf"));
				modelLogin.setNumero(resultado.getString("numero"));
			}
			return modelLogin;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return modelLogin;
	}

	public ModelLogin consultaUsuarioId(String id, Long userLogado) {

		ModelLogin modelLogin = new ModelLogin();

		try {
			String sql = "SELECT*FROM model_login WHERE id = ? AND useradmin IS FALSE AND usuario_id = ?";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(1, Long.parseLong(id));
			statement.setLong(2, userLogado);
			ResultSet resultado = statement.executeQuery();

			while (resultado.next()) {
				modelLogin.setId(resultado.getLong("id"));
				modelLogin.setNome(resultado.getString("nome"));
				modelLogin.setEmail(resultado.getString("email"));
				modelLogin.setLogin(resultado.getString("login"));
				modelLogin.setSenha(resultado.getString("senha"));
				modelLogin.setPerfil(resultado.getString("perfil"));
				modelLogin.setSexo(resultado.getString("sexo"));
				modelLogin.setFotoUser(resultado.getString("fotouser"));
				modelLogin.setExtensaofotouser(resultado.getString("extensaofotouser"));
				modelLogin.setCEP(resultado.getString("cep"));
				modelLogin.setRua(resultado.getString("rua"));
				modelLogin.setBairro(resultado.getString("bairro"));
				modelLogin.setLocalidade(resultado.getString("localidade"));
				modelLogin.setUf(resultado.getString("uf"));
				modelLogin.setNumero(resultado.getString("numero"));
				
				System.out.println("--------------------------------------------------------------------------------");
				System.out.println("Prompt: DAOUsuarioReposity consultaUsuarioId");
				System.out.println(modelLogin.getNome());
				System.out.println(modelLogin.getCEP());
				System.out.println(modelLogin.getPerfil());
				System.out.println("--------------------------------------------------------------------------------");
			}
			return modelLogin;

		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public boolean validarLogin(String login) {

		try {
			String sql = "SELECT count(1) > 0 AS existe FROM model_login WHERE login = ? AND useradmin IS FALSE;";

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
			String sql = "DELETE FROM model_login WHERE id = ? AND useradmin IS FALSE";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(1, Long.parseLong(id));

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
