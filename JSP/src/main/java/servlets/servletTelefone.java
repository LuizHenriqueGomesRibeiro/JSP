package servlets;

import java.io.IOException;
import java.util.List;

import DAO.DAOUsuarioRepository;
import DAO.daoTelefoneRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;
import model.ModelTelefone;

/**
 * Servlet implementation class servletTelefone
 */
public class servletTelefone extends ServletGenericUtil {
	private static final long serialVersionUID = 1L;
	
	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
	
	private daoTelefoneRepository daoTelefoneRepository = new daoTelefoneRepository();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletTelefone() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String acao = request.getParameter("acao");
			
			if (acao != null && !acao.isEmpty() && acao.equals("excluir")) {
				
				String idfone = request.getParameter("id");
				
				daoTelefoneRepository.deleteTelefone(idfone);
				
				String userpai = request.getParameter("userpai");
				
				ModelLogin modelLogin = daoUsuarioRepository.consultaUsuarioId(Long.parseLong(userpai));
				request.setAttribute("usuario", modelLogin);
				
				ModelLogin modelLogin1 = daoUsuarioRepository.consultaUsuarioId(Long.parseLong(userpai));
				
				List<ModelTelefone> modelTelefones = daoTelefoneRepository.listarTelefone(modelLogin1.getId());
				request.setAttribute("telefones", modelTelefones);
				
				request.setAttribute("msg_sucesso", "Telefone excluido com sucesso");

				RequestDispatcher redirecionar = request.getRequestDispatcher("principal/telefone.jsp");
				redirecionar.forward(request, response);
				
				return;
			} 
			
			String iduser = request.getParameter("id");
			
			if (iduser != null && !iduser.isEmpty()) {
				
				List<ModelTelefone> modelTelefones = daoTelefoneRepository.listarTelefone(iduser);
				request.setAttribute("telefones", modelTelefones);

				ModelLogin modelLogin = daoUsuarioRepository.consultaUsuarioId(Long.parseLong(iduser));
				request.setAttribute("usuario", modelLogin);

				RequestDispatcher redirecionar = request.getRequestDispatcher("principal/telefone.jsp");
				redirecionar.forward(request, response);
				
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try { 
			
			String iduser = request.getParameter("id");
			
			ModelLogin modelLogin = daoUsuarioRepository.consultaUsuarioId(Long.parseLong(iduser));
			request.setAttribute("usuario", modelLogin);

			String usuario_pai_id = request.getParameter("id");
			String telefone = request.getParameter("telefone");
			ModelTelefone modelTelefone = new ModelTelefone();

			modelTelefone.setNumero(telefone);
			modelTelefone.setUsuario_pai_id(daoUsuarioRepository.consultaUsuarioId(Long.parseLong(usuario_pai_id)));
			modelTelefone.setUsuario_cad_id(super.getUserLogadoObjeto(request));
			
			daoTelefoneRepository.gravaTelefone(modelTelefone);

			request.setAttribute("pai_telefone", daoUsuarioRepository.consultaUsuarioId(Long.parseLong(usuario_pai_id)));
			request.setAttribute("msg_sucesso", "Telefone cadastrado com sucesso");
			
			List<ModelTelefone> modelTelefones = daoTelefoneRepository.listarTelefone(usuario_pai_id);
			request.setAttribute("telefones", modelTelefones);
			
			RequestDispatcher redirecionar = request.getRequestDispatcher("principal/telefone.jsp");
			redirecionar.forward(request, response);
	
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
