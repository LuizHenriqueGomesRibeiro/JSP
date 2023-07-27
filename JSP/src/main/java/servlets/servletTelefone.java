package servlets;

import java.io.IOException;
import java.util.List;

import DAO.DAOUsuarioRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

/**
 * Servlet implementation class servletTelefone
 */
public class servletTelefone extends ServletGenericUtil {
	private static final long serialVersionUID = 1L;
	
	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
       
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
		
		try {
		
		if (iduser != null && !iduser.isEmpty()) {

			

				ModelLogin modelLogin = daoUsuarioRepository.consultaUsuarioId(Long.parseLong(iduser));
				request.setAttribute("usuario", modelLogin);

				RequestDispatcher redirecionar = request.getRequestDispatcher("principal/telefone.jsp");
				redirecionar.forward(request, response);

		
		}else {

			List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioList(super.getUserLogado(request));
			
			request.setAttribute("modelLogins", modelLogins);
			request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));
		    
			RequestDispatcher redirecionar = request.getRequestDispatcher("principal/usuario.jsp");
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
		
	}

}
