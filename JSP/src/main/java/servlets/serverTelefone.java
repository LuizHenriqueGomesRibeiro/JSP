package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;
import model.ModelTelefone;

import java.io.IOException;
import java.util.List;

import DAO.DAOUsuarioRepository;
import DAO.daoTelefoneRepository;

/**
 * Servlet implementation class serverTelefone
 */
public class serverTelefone extends ServletGenericUtil {
	private static final long serialVersionUID = 1L;
	
	DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
	
	daoTelefoneRepository daoTelefoneRepository = new daoTelefoneRepository();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public serverTelefone() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Você está dentro do servlet de telefones. Seja bem-vindo!");
		
		String acao = request.getParameter("acao");

		if (acao != null & !acao.isEmpty() && acao.equalsIgnoreCase("listarTelefones")) {

			try {
				String iduser = request.getParameter("iduser");

				ModelLogin modelLogin = daoUsuarioRepository.consultaUsuarioId(Long.parseLong(iduser));
				request.setAttribute("usuario", modelLogin);
				
				List<ModelTelefone> modelTelefones = daoTelefoneRepository.listarTelefone(iduser);
				request.setAttribute("telefones", modelTelefones);

				RequestDispatcher redirecionar = request.getRequestDispatcher("principal/telefone.jsp");
				redirecionar.forward(request, response);
				
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}else if (acao != null && !acao.isEmpty() && acao.equals("excluir")) {
			
			try {
				
				String idfone = request.getParameter("id");
				String userpai = request.getParameter("userpai");
				
				daoTelefoneRepository.deleteTelefone(idfone);

				request.setAttribute("msg_sucesso", "Telefone excluido com sucesso");
				
				ModelLogin modelLogin = daoUsuarioRepository.consultaUsuarioId(Long.parseLong(userpai));
				request.setAttribute("usuario", modelLogin);

				List<ModelTelefone> modelTelefones = daoTelefoneRepository.listarTelefone(modelLogin.getId());
				request.setAttribute("telefones", modelTelefones);

				RequestDispatcher redirecionar = request.getRequestDispatcher("principal/telefone.jsp");
				redirecionar.forward(request, response);
				
				//return;
			} catch (Exception e) {
				// TODO: handle exception
			}
		} 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Você está dentro do servlet de telefones. Seja bem-vindo!");
	}

}
