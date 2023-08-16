package servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

import DAO.DAOUsuarioRepository;
import Util.ReportUtil;
import beandto.BeanDtoGraficoSalarioUser;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.ModelLogin;

@MultipartConfig
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
				request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));

				RequestDispatcher redirecionar = request.getRequestDispatcher("principal/usuario.jsp");
				redirecionar.forward(request, response);

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUserAjaxPage")) {

				String nomeBusca = request.getParameter("nomeBusca");
				String pagina = request.getParameter("pagina");

				List<ModelLogin> dadosJsonUser = daoUsuarioRepository.consultaUsuarioListOffSet(nomeBusca,
						super.getUserLogado(request), Integer.parseInt(pagina));
				Gson gson = new Gson();
				String json = gson.toJson(dadosJsonUser);
				response.addHeader("TotalPagina", "" 
				+ daoUsuarioRepository.consultaUsuarioListTotalPagina(nomeBusca, super.getUserLogado(request)));
				PrintWriter printWriter = response.getWriter();
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				printWriter.write(json);
				printWriter.close();

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUserAjax")) {

				String nomeBusca = request.getParameter("nomeBusca");
				List<ModelLogin> dadosJsonUser = daoUsuarioRepository.consultaUsuarioList(nomeBusca, super.getUserLogado(request));
				Gson gson = new Gson();
				String json = gson.toJson(dadosJsonUser);
				response.addHeader("TotalPagina",""+daoUsuarioRepository.consultaUsuarioListTotalPagina(nomeBusca, super.getUserLogado(request)));
				PrintWriter printWriter = response.getWriter();
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				printWriter.write(json);
				printWriter.close();

				request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));

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

				request.setAttribute("cep", modelLogin.getCep());

				request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));
				RequestDispatcher redirecionar = request.getRequestDispatcher("principal/usuario.jsp");
				redirecionar.forward(request, response);

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listarUsuario")) {

				List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioList(super.getUserLogado(request));
				request.setAttribute("modelLogins", modelLogins);

				request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));

				RequestDispatcher redirecionar = request.getRequestDispatcher("principal/usuario.jsp");
				redirecionar.forward(request, response);

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("download")) {

				String id = request.getParameter("id");

				ModelLogin modelLogin = daoUsuarioRepository.consultaUsuarioId(id, super.getUserLogado(request));
				request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));

				if (modelLogin.getFotoUser() != null && !modelLogin.getFotoUser().isEmpty()) {
					response.setHeader("Content-Disposition",
							"attachment;filename=arquivo." + modelLogin.getExtensaofotouser());
					new Base64();
					response.getOutputStream().write(Base64.decodeBase64(modelLogin.getFotoUser().split("\\,")[1]));
				}

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("paginar")) {

				Integer offset = Integer.parseInt(request.getParameter("pagina"));

				List<ModelLogin> modelLogins = daoUsuarioRepository
						.consultaUsuarioListPaginada(super.getUserLogado(request), offset);
				request.setAttribute("modelLogins", modelLogins);

				request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));

				RequestDispatcher redirecionar = request.getRequestDispatcher("principal/usuario.jsp");
				redirecionar.forward(request, response);

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("voltar")) {

				RequestDispatcher redirecionar = request.getRequestDispatcher("principal/principal.jsp");
				redirecionar.forward(request, response);

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("printForm")) {

				String dataInicial = request.getParameter("dataInicial");
				String dataFinal = request.getParameter("dataFinal");

				if (dataInicial == null || dataInicial.isEmpty() && dataFinal == null || dataFinal.isEmpty()) {
					request.setAttribute("modelLogins",
							daoUsuarioRepository.consultaUsuarioListRelatorio(super.getUserLogado(request)));
				} else {
					request.setAttribute("modelLogins", daoUsuarioRepository
							.consultaUsuarioListRelatorio(super.getUserLogado(request), dataInicial, dataFinal));
				}

				request.setAttribute("dataInicial", dataInicial);
				request.setAttribute("dataFinal", dataFinal);

				RequestDispatcher redirecionar = request.getRequestDispatcher("principal/relatorio.jsp");
				redirecionar.forward(request, response);

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("printFormPDF")) {

				String dataInicial = request.getParameter("dataInicial");
				String dataFinal = request.getParameter("dataFinal");

				List<ModelLogin> modelLogins = null;

				if (dataInicial == null || dataInicial.isEmpty() && dataFinal == null || dataFinal.isEmpty()) {

					modelLogins = daoUsuarioRepository.consultaUsuarioListRelatorio(super.getUserLogado(request));

				} else {

					modelLogins = daoUsuarioRepository.consultaUsuarioListRelatorio(super.getUserLogado(request),
							dataInicial, dataFinal);
				}

				HashMap<String, Object> params = new HashMap<String, Object>();
				params.put("PARAM_SUB_REPORT", request.getServletContext().getRealPath("relatorio") + File.separator);

				byte[] relatorio = new ReportUtil().geraReltorioPDF(modelLogins, "resl-user-jsp", params, request.getServletContext());

				response.setHeader("Content-Disposition", "attachment;filename=arquivo.pdf");
				response.getOutputStream().write(relatorio);

			}else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("graficoSalario")) {

				String dataInicial = request.getParameter("dataInicial");
				String dataFinal = request.getParameter("dataFinal");
				
				System.out.println("Estamos em grafico Servlet");

				if (dataInicial == null || dataInicial.isEmpty() && dataFinal == null || dataFinal.isEmpty()) {
					
					BeanDtoGraficoSalarioUser beanDtoGraficoSalarioUser = 
							daoUsuarioRepository.montarGraficoMediaSalario(super.getUserLogado(request));
					
					System.out.println(beanDtoGraficoSalarioUser);
					
					Gson gson = new Gson();
					String json = gson.toJson(beanDtoGraficoSalarioUser);
					System.out.println(json);
					PrintWriter printWriter = response.getWriter();
					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					printWriter.write(json);
					printWriter.close();
				} else {

					
				}
				
			}else{

				List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioList(super.getUserLogado(request));

				request.setAttribute("modelLogins", modelLogins);
				request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));
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
			String perfil = request.getParameter("perfil");
			String sexo = request.getParameter("sexo");
			String cep = request.getParameter("cep");
			String rua = request.getParameter("rua");
			String bairro = request.getParameter("bairro");
			String localidade = request.getParameter("localidade");
			String uf = request.getParameter("uf");
			String numero = request.getParameter("numero");
			String dataNascimento = request.getParameter("dataNascimento");
			String renda = request.getParameter("renda");

			renda = renda.split("\\ ")[1].replaceAll("\\.", "").replaceAll("\\,", ".");

			ModelLogin modelLogin = new ModelLogin();

			modelLogin.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
			modelLogin.setNome(nome);
			modelLogin.setLogin(login);
			modelLogin.setEmail(email);
			modelLogin.setSenha(senha);
			modelLogin.setPerfil(perfil);
			modelLogin.setSexo(sexo);
			modelLogin.setCep(cep);
			modelLogin.setRua(rua);
			modelLogin.setBairro(bairro);
			modelLogin.setLocalidade(localidade);
			modelLogin.setUf(uf);
			modelLogin.setNumero(numero);
			modelLogin.setRenda(Double.valueOf(renda));

			SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date dataUtil;
			try {

				dataUtil = formatador.parse(dataNascimento);
				Date dataSql = new Date(dataUtil.getTime());
				modelLogin.setDataNascimento(dataSql);

			} catch (Exception e) {
				e.printStackTrace();
			}

			Part part = request.getPart("fileFoto");
			byte[] foto = IOUtils.toByteArray(part.getInputStream());
			new Base64();
			String imagemBase64 = "data:image/" + part.getContentType().split("\\/")[1] + ";base64,"
					+ Base64.encodeBase64String(foto);
			modelLogin.setFotoUser(imagemBase64);
			modelLogin.setExtensaofotouser(part.getContentType().split("\\/")[1]);

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
					request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));
					List<ModelLogin> modelLogins = daoUsuarioRepository
							.consultaUsuarioList(super.getUserLogado(request));
					request.setAttribute("modelLogins", modelLogins);

					RequestDispatcher redirecionar = request.getRequestDispatcher("principal/usuario.jsp");
					redirecionar.forward(request, response);
				} else {

					msg = "Atualizado com sucesso!";

					modelLogin = daoUsuarioRepository.gravarUsuario(modelLogin, super.getUserLogado(request));

					request.setAttribute("msg", msg);
					request.setAttribute("modelLogin", modelLogin);

					List<ModelLogin> modelLogins = daoUsuarioRepository
							.consultaUsuarioList(super.getUserLogado(request));
					request.setAttribute("modelLogins", modelLogins);
					request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));

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
