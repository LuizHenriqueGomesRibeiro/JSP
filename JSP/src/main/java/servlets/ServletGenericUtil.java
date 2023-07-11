package servlets;

import java.io.Serializable;
import DAO.DAOUsuarioRepository;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ServletGenericUtil extends HttpServlet implements Serializable {
	private static final long serialVersionUID = 1;

	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();

	public Long getUserLogado(HttpServletRequest request) {

		try {
			HttpSession session = request.getSession();

			String usuarioLogado = (String) session.getAttribute("usuario");

			return daoUsuarioRepository.consultaUsuarioLogado(usuarioLogado).getId();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

			return null;
		}
	}
}
