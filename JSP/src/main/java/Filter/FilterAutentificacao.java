package Filter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import DAO.DAOVersionadorBanco;
import connection.SingleConnectionBanco;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = { "/principal/*" })
public class FilterAutentificacao implements Filter {

	private static Connection connection;

	public FilterAutentificacao() {
	}

	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		try {

			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();

			String usuarioLogado = (String) session.getAttribute("usuario");

			String urlParaAutenticar = req.getServletPath();

			if (usuarioLogado == null && !urlParaAutenticar.equalsIgnoreCase("/principal/ServletLogin")) {

				RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticar);
				request.setAttribute("msg", "Por favor realize o login!");
				redireciona.forward(request, response);
				return;

			} else {
				chain.doFilter(request, response);
			}
			
			connection.commit();

		} catch (Exception e) {
			
			e.printStackTrace();
			
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
			
			try {
				connection.rollback();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {

		connection = SingleConnectionBanco.getConnection();
		
		DAOVersionadorBanco daoVersionadorBanco = new DAOVersionadorBanco();
		
 		String caminhoPastaSQL = fConfig.getServletContext().getRealPath("versionadorbancosql") + File.separator;
		File[] filesSql = new File(caminhoPastaSQL).listFiles();
		
		try {		
			for (File file : filesSql){
				
				boolean arquivoJaRodado = daoVersionadorBanco.arquivoSqlRodado(file.getName());
				
				if (!arquivoJaRodado){
					FileInputStream entradaArquivo = new FileInputStream(file);
					Scanner lerArquivo = new Scanner(entradaArquivo, "UTF-8");	
					StringBuilder sql = new StringBuilder();
					
					while (lerArquivo.hasNext()){
						 sql.append(lerArquivo.nextLine());
						 sql.append("\n");
					}	
					
					connection.prepareStatement(sql.toString()).execute();
					daoVersionadorBanco.gravaArquivoSqlRodado(file.getName());
					
					connection.commit();
					lerArquivo.close();
				}
			}
		}catch (Exception e){
			try{
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
}
