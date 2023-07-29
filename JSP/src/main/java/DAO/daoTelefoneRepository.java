package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnectionBanco;
import model.ModelLogin;
import model.ModelTelefone;

public class daoTelefoneRepository {
	
	private Connection connection;
	
	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
	
	public daoTelefoneRepository() {
		connection = SingleConnectionBanco.getConnection();	
	}

	public void gravaTelefone(ModelTelefone modelTelefone) throws SQLException {
		
		String sql = "INSERT INTO telefone(numero, usuario_pai_id, usuario_cad_id) VALUES (?, ?, ?)";
		
		ModelLogin usuario_pai_id = modelTelefone.getUsuario_pai_id();
		ModelLogin usuario_cad_id = modelTelefone.getUsuario_cad_id();

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, modelTelefone.getNumero());
		statement.setLong(2, usuario_pai_id.getId());
		// statement.setLong(1, modelTelefone.getUsuario_pai_id().getId());
		statement.setLong(3, usuario_cad_id.getId());
		statement.execute();
		
		connection.commit();
	}
	
	public void deleteTelefone(Long id) throws SQLException {
		
		String sql = "DELETE FROM telefone WHERE id = ?";

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, id);
		statement.executeUpdate();
			
		connection.commit();
	}
	
	public List<ModelTelefone> listarTelefone(Long id) throws SQLException{
		
		List<ModelTelefone> retorno = new ArrayList<ModelTelefone>();
		
		String sql = "SELECT * FROM telefone WHERE usuario_pai_id = ?";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
		
		while(resultado.next()) {
			
			ModelTelefone modelTelefone = new ModelTelefone();
			
			ModelLogin modelLogin_cad = daoUsuarioRepository.consultaUsuarioId(resultado.getLong("usuario_cad_id"));
			ModelLogin modelLogin_pai = daoUsuarioRepository.consultaUsuarioId(resultado.getLong("usuario_cad_id"));
			modelTelefone.setId(resultado.getLong("id"));
			modelTelefone.setNumero(resultado.getString("numero"));
			modelTelefone.setUsuario_cad_id(modelLogin_cad);
			modelTelefone.setUsuario_pai_id(modelLogin_pai);
			
			retorno.add(modelTelefone);
		}
		
		return retorno;
	}
	
public List<ModelTelefone> listarTelefone(String id) throws SQLException{
		
		List<ModelTelefone> retorno = new ArrayList<ModelTelefone>();
		
		String sql = "SELECT * FROM telefone WHERE usuario_pai_id = ?";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
		
		while(resultado.next()) {
			
			ModelTelefone modelTelefone = new ModelTelefone();
			
			ModelLogin modelLogin_cad = daoUsuarioRepository.consultaUsuarioId(resultado.getLong("usuario_cad_id"));
			ModelLogin modelLogin_pai = daoUsuarioRepository.consultaUsuarioId(resultado.getLong("usuario_cad_id"));
			modelTelefone.setId(resultado.getLong("id"));

			modelTelefone.setNumero(resultado.getString("numero"));
			modelTelefone.setUsuario_cad_id(modelLogin_cad);
			modelTelefone.setUsuario_pai_id(modelLogin_pai);
			
			retorno.add(modelTelefone);
		}
		
		return retorno;
	}
}
