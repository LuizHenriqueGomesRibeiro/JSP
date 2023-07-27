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
		
		String iduser = request.getParameter("iduser");

			if (iduser != null && !iduser.isEmpty()) {

				ModelLogin modelLogin = daoUsuarioRepository.consultaUsuarioId(Long.parseLong(iduser));
				request.setAttribute("usuario", modelLogin);

				RequestDispatcher redirecionar = request.getRequestDispatcher("principal/telefone.jsp");
				redirecionar.forward(request, response);
			} 
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {

			String usuario_pai_id = request.getParameter("id");
			String telefone = request.getParameter("telefone");
			ModelTelefone modelTelefone = new ModelTelefone();

			modelTelefone.setNumero(telefone);
			modelTelefone.setUsuario_pai_id(daoUsuarioRepository.consultaUsuarioId(Long.parseLong(usuario_pai_id)));
			modelTelefone.setUsuario_cad_id(super.getUserLogadoObjeto(request));
			
			daoTelefoneRepository.gravaTelefone(modelTelefone);
			
			List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioList(super.getUserLogado(request));
			request.setAttribute("modelLogins", modelLogins);

			request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));
			
			RequestDispatcher redirecionar = request.getRequestDispatcher("principal/usuario.jsp");
			redirecionar.forward(request, response);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
