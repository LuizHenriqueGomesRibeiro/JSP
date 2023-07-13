package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import DAO.DAOUsuarioRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

/**
 * Servlet implementation class ServletUsuarioController
 */
@WebServlet(urlPatterns = { "/ServletUsuarioController" })
public class ServletUsuarioController extends ServletGenericUtil {
	private static final long serialVersionUID = 1L;

	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletUsuarioController() {
		super();
		// TODO Auto-generated constructor stub usuario
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String acao = request.getParameter("acao");

			if (acao != null & !acao.isEmpty() && acao.equalsIgnoreCase("deletar")) {

				List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioList(super.getUserLogado(request));
				request.setAttribute("modelLogins", modelLogins);

				String id = request.getParameter("id");
				daoUsuarioRepository.deletar_registro(id);

				request.setAttribute("msg", "excluído com sucesso.");
				RequestDispatcher redirecionar = request.getRequestDispatcher("principal/usuario.jsp");
				redirecionar.forward(request, response);
				
			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUserAjax")) {
				String nomeBusca = request.getParameter("nomeBusca");
				List<ModelLogin> dadosJsonUser = daoUsuarioRepository.consultaUsuarioList(nomeBusca, super.getUserLogado(request));
				Gson gson = new Gson();
				String json = gson.toJson(dadosJsonUser);
				PrintWriter printWriter = response.getWriter();
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				printWriter.write(json);
				printWriter.close();

				/*
				 * ObjectMapper mapper = new ObjectMapper(); String json =
				 * mapper.writeValueAsString(dadosJsonUser); response.getWriter().write(json);
				 * 
				 * System.out.println(json);
				 */

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscar")) {

				String id = request.getParameter("id");

				ModelLogin modelLogin = daoUsuarioRepository.consultaUsuarioId(id, super.getUserLogado(request));

				List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioList(super.getUserLogado(request));
				request.setAttribute("modelLogins", modelLogins);

				request.setAttribute("msg", "Usuário em edição");
				request.setAttribute("modelLogin", modelLogin);
				RequestDispatcher redirecionar = request.getRequestDispatcher("principal/usuario.jsp");
				redirecionar.forward(request, response);

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listarUsuario")) {

				List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioList(super.getUserLogado(request));
				request.setAttribute("modelLogins", modelLogins);

				RequestDispatcher redirecionar = request.getRequestDispatcher("principal/usuario.jsp");
				redirecionar.forward(request, response);

				System.out.println(modelLogins);
			} else {
				List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioList(super.getUserLogado(request));
				request.setAttribute("modelLogins", modelLogins);

				request.setAttribute("msg", "Falha ao excluir o usuário. Tente novamente mais tarde.");
				RequestDispatcher redirecionar = request.getRequestDispatcher("principal/usuario.jsp");
				redirecionar.forward(request, response);
			}
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("msg", e);
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			redirecionar.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {
			String msg = "Operação realizada com sucesso!";

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

			/*
			 * if(daoUsuarioRepository.validarLogin(modelLogin.getLogin()) &&
			 * modelLogin.getId() == null) {
			 * if(daoUsuarioRepository.validarEmail(modelLogin.getEmail()) &&
			 * modelLogin.getId() == null) { request.setAttribute("msg_login",
			 * "Já existe um usuário com este login."); request.setAttribute("msg_email",
			 * "Já existe um usuário com este email."); request.setAttribute("modelLogin",
			 * modelLogin); RequestDispatcher redirecionar =
			 * request.getRequestDispatcher("principal/usuario.jsp");
			 * redirecionar.forward(request, response); } else {
			 * request.setAttribute("msg_login", "Já existe um usuário com este login.");
			 * request.setAttribute("modelLogin", modelLogin); RequestDispatcher
			 * redirecionar = request.getRequestDispatcher("principal/usuario.jsp");
			 * redirecionar.forward(request, response); } } else
			 * if(daoUsuarioRepository.validarEmail(modelLogin.getEmail()) &&
			 * modelLogin.getId() == null) { request.setAttribute("msg_email",
			 * "Já existe um usuário com este email."); request.setAttribute("modelLogin",
			 * modelLogin); RequestDispatcher redirecionar =
			 * request.getRequestDispatcher("principal/usuario.jsp");
			 * redirecionar.forward(request, response); } else {
			 * 
			 * daoUsuarioRepository.gravarUsuario(modelLogin); request.setAttribute("msg",
			 * "Operação realizada com sucesso."); request.setAttribute("modelLogin",
			 * modelLogin); RequestDispatcher redirecionar =
			 * request.getRequestDispatcher("principal/usuario.jsp");
			 * redirecionar.forward(request, response); }
			 */

			if (daoUsuarioRepository.validarLogin(modelLogin.getLogin()) && modelLogin.getId() == null) {
				if (daoUsuarioRepository.validarEmail(modelLogin.getEmail()) && modelLogin.getId() == null) {
					
					request.setAttribute("msg_login", "Já existe um usuário com este login.");
					request.setAttribute("msg_email", "Já existe um usuário com este email.");
					request.setAttribute("modelLogin", modelLogin);
					
					RequestDispatcher redirecionar = request.getRequestDispatcher("principal/usuario.jsp");
					redirecionar.forward(request, response);
					
				} else {
					
					request.setAttribute("msg_login", "Já existe um usuário com este login.");
					request.setAttribute("modelLogin", modelLogin);
					
					RequestDispatcher redirecionar = request.getRequestDispatcher("principal/usuario.jsp");
					redirecionar.forward(request, response);
				}
			} else if (daoUsuarioRepository.validarEmail(modelLogin.getEmail()) && modelLogin.getId() == null) {
				request.setAttribute("msg_email", "Já existe um usuário com este email.");
				request.setAttribute("modelLogin", modelLogin);
				RequestDispatcher redirecionar = request.getRequestDispatcher("principal/usuario.jsp");
				redirecionar.forward(request, response);
			} else {
				if (modelLogin.isNovo()) {

					msg = "Gravado com sucesso!";

					modelLogin = daoUsuarioRepository.gravarUsuario(modelLogin, super.getUserLogado(request));

					request.setAttribute("msg", msg);
					request.setAttribute("modelLogin", modelLogin);

					RequestDispatcher redirecionar = request.getRequestDispatcher("principal/usuario.jsp");
					redirecionar.forward(request, response);
				} else {

					msg = "Atualizado com sucesso!";

					modelLogin = daoUsuarioRepository.gravarUsuario(modelLogin, super.getUserLogado(request));

					request.setAttribute("msg", msg);
					request.setAttribute("modelLogin", modelLogin);

					RequestDispatcher redirecionar = request.getRequestDispatcher("principal/usuario.jsp");
					redirecionar.forward(request, response);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			redirecionar.forward(request, response);
		}
	}
}
