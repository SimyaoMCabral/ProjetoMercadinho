package connectionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionDatabase {

	//as variaveis são privadas, estaticas
	private static final String Driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// "final" faz que não possa que nenhuma outro lugar possa alterar
	private static final String url = "jdbc:sqlserver://localhost:1433;encrypt=false;databaseName=Mercado"; 
	private static final String user = "sa";
	private static final String password = "Senailab03";
	
	//conexão com banco de dados, toda vez que for fazer uma conexão é preciso fechar a conexaõ
	public static Connection getConnection() {
		
		try {
			Class.forName(Driver);
			System.out.println("Conexão Estabelecida");
			return DriverManager.getConnection(url, user, password);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro de conexão!", e);		
			
		}
		
	}
	
	//metodo para fechar a conexaõ
	public static void closeConnection(Connection con) {
		
		try {
			if(con != null) {
				con.close();
				System.out.println("Conexão Fechada!");
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException("Erro ao fechar conexão!", e);
		}
	}
	//Precisa fechar a conexaõ e limpa a PreparedStatement(armazena o comando que envio no banco de dados)
	public static void closeConnection(Connection con, PreparedStatement stmt) {
		closeConnection(con);
		// é preciso fechar dos dois objetos o método abaixo fecha con e stmt
		try {
			if(stmt != null) {
				stmt.close();
				System.out.println("Conexão Fechada!");
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException("Erro ao fechar conexão!", e);
		}
		
	}
	
	//método de fechamento de conexão, a ResultSet é responsavel o amazenar a informações
	public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {
		//chamar o método já criado
		closeConnection(con, stmt);
		
		//fechar rs, esse método são necessarios pois não pode deixar aberto
		try {
			if(rs != null) {
				rs.close();
				System.out.println("Conexão Fechada!");
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException("Erro ao fechar conexão!", e);
		}
		
		
	}
	
}


