package servlets;

import java.io.IOException;

import DAO.DAOUsuarioRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

/**
 * Servlet implementation class ServletUsuarioController
 */
public class ServletUsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletUsuarioController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {
			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String email = request.getParameter("email");

			ModelLogin modelLogin = new ModelLogin();
			
			modelLogin.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
			modelLogin.setNome(nome);
			modelLogin.setLogin(login);
			modelLogin.setEmail(email);
			modelLogin.setSenha(senha);
			
			if(daoUsuarioRepository.validarLogin(modelLogin.getLogin()) && modelLogin.getId() == null) {
				if(daoUsuarioRepository.validarEmail(modelLogin.getEmail()) && modelLogin.getId() == null) {
					request.setAttribute("msg_login", "Já existe um usuário com este login.");
					request.setAttribute("msg_email", "Já existe um usuário com este email.");
					request.setAttribute("modelLogin", modelLogin);
					RequestDispatcher redirecionar = request.getRequestDispatcher("principal/usuario.jsp");
					redirecionar.forward(request, response);
				}
				else {
					request.setAttribute("msg_login", "Já existe um usuário com este login.");
					request.setAttribute("modelLogin", modelLogin);
					RequestDispatcher redirecionar = request.getRequestDispatcher("principal/usuario.jsp");
					redirecionar.forward(request, response);
				}
			}
			else if(daoUsuarioRepository.validarEmail(modelLogin.getEmail()) && modelLogin.getId() == null) {
				request.setAttribute("msg_email", "Já existe um usuário com este email.");
				request.setAttribute("modelLogin", modelLogin);
				RequestDispatcher redirecionar = request.getRequestDispatcher("principal/usuario.jsp");
				redirecionar.forward(request, response);
			}
			else {
				
				daoUsuarioRepository.gravarUsuario(modelLogin);
				request.setAttribute("msg", "Operação realizada com sucesso.");
				request.setAttribute("modelLogin", modelLogin);
				RequestDispatcher redirecionar = request.getRequestDispatcher("principal/usuario.jsp");
				redirecionar.forward(request, response);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}
	}
}
