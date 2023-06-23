package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnectionBanco {
	
	private static String banco="jdbc:postgresql://localhost:5432/JSP?autoReconnect=true";
	private static String user="postgres";
	private static String senha="MySQL";
	private static Connection connection=null;
	
	public static Connection getConnection() {
		return connection;
	}
	
	static {
		conectar();
	}
	
	public SingleConnectionBanco() {
		// TODO Auto-generated constructor stub
		conectar();
	}
	
	private static void conectar() {
		try {
			if(connection==null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(banco, user, senha);
				connection.setAutoCommit(false);	
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
