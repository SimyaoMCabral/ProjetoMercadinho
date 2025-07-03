package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectionFactory.ConnectionDatabase;

import model.Cliente;

public class ClienteDAO {
	
	//create - Criar/ inserir informaçõe no banco
	//read - ler informações do banco
	//update - atualizar informações do banco
	//delete - apagar informações do banco
	//search - pesquisar e ler informações do banco
	
	public void create(Cliente cliente) {//adiconar o cliente, não sei se é metodo
	Connection con = ConnectionDatabase.getConnection(); //instancia de conexão	
	PreparedStatement stmt = null;//classe responsavel para armazenar string para enviar o comando no sql
	//COMO É UMA TENTATIVA DE CONEXÃO FOI PRECISO FAZER AS INFROMAÇÕES ABAIXO "TRY CATCH"
	
	try {
		stmt= con.prepareStatement("INSERT INTO Cliente values(?, ?, ?, ?, ?, ?, ?)");//a interrogação é um espaço reservado que é reconhecido pelo prepareStatement
		stmt.setString(1, cliente.getNomeCliente());
		stmt.setString(2, cliente.getCpfCliente());
		stmt.setString(3, cliente.getDataNasc());
		stmt.setString(4, cliente.getTelefone());
		stmt.setString(5, cliente.getEmail());
		stmt.setString(6, cliente.getEndereco());
		stmt.setString(7, cliente.getGenero());
		///acima estão na ordem de cada espaço vazio que será preenchido
		
		stmt.execute();
		System.out.println("Cliente cadastrado");
		//abaixo caso não der certo
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		throw new RuntimeException("Erro ao cadastrar!",e);
	}finally{
		ConnectionDatabase.closeConnection(con, stmt);
	}
	
	}
	//
	
	public ArrayList<Cliente> read() {
		Connection con = ConnectionDatabase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs= null;
		ArrayList<Cliente> clientes = new ArrayList<>();//irá guardar a lista de cliente 
		
		try {
			stmt = con.prepareStatement("SELECT * FROM Cliente");
			rs = stmt.executeQuery();
			// laço de repetição para aparecer a lista de uma vez
			
			while(rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setIdCliente(rs.getString("idCliente"));
				cliente.setNomeCliente(rs.getString("nomeCliente"));
				cliente.setCpfCliente(rs.getString("cpfCliente"));
				cliente.setDataNasc(rs.getString("dataNasc"));
				cliente.setTelefone(rs.getString("telefone"));
				cliente.setEmail(rs.getString("email"));
				cliente.setEndereco(rs.getString("endereco"));
				cliente.setGenero(rs.getString("genero"));
				
				clientes.add(cliente);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao ler informações!", e);
		}finally {
			ConnectionDatabase.closeConnection(con, stmt, rs);
		}
		
		return clientes;//por enquanto, para não dar erro
		
	}
	//
	
    public void update(Cliente cliente) {
    	Connection con = ConnectionDatabase.getConnection();
    	PreparedStatement stmt = null;
    	
    	//deu erro tem que colocar dentro de uma try catch 
    	try {
			stmt =con.prepareStatement("UPDATE Cliente SET nomeCliente =?, cpfCliente = ?, dataNasc = ?,"
					+ " telefone = ?, email = ?, endereco = ?, genero =? "
					+ "where cpfCliente = ?");//string, não adiciona o idCliente porque é imutavel
			//vai utilizar cpdfCliente para indicar a atualizar "where cpfCliente"
			stmt.setString(1, cliente.getNomeCliente());
			stmt.setString(2, cliente.getCpfCliente());
			stmt.setString(3, cliente.getDataNasc());
			stmt.setString(4, cliente.getTelefone());
			stmt.setString(5, cliente.getEmail());
			stmt.setString(6, cliente.getEndereco());
			stmt.setString(7, cliente.getGenero());
			stmt.setString(8, cliente.getCpfCliente());
			
			stmt.execute();//para executar o comando
			System.out.println("Cliente atualizado!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao atualizar!", e);
		}finally {
			ConnectionDatabase.closeConnection(con, stmt);
		}
    	
    }
    //
	
    public void delete(String cpf) {
    	Connection con = ConnectionDatabase.getConnection();
    	PreparedStatement stmt = null;
    	
    	try {
			stmt = con.prepareStatement("DELETE FROM Cliente where cpfCliente = ?");
			stmt.setString(1,cpf);
			
			stmt.execute();
			System.out.println("Cliente exlcuido!");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("Erro ao excluir", e);
		}finally {
			ConnectionDatabase.closeConnection(con, stmt);
		}
    }
    
    //COpiei e colei de read
    public ArrayList<Cliente> search(String pesquisa) {// método recebe String para pesquisar no banco de dados
		Connection con = ConnectionDatabase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs= null;
		ArrayList<Cliente> clientes = new ArrayList<>();//irá guardar a lista de cliente 
		
		try {
			stmt = con.prepareStatement("SELECT * FROM Cliente where nomeCliente like ? or cpfCliente like ?");//String de pesquisa
			stmt.setString(1, "%" +pesquisa+ "%"); //a % é para pesquisa qualquer coisa que digitei referente aos "?"
			stmt.setString(2, "%" +pesquisa+ "%");
			
			rs = stmt.executeQuery();
			// laço de repetição para aparecer a lista de uma vez
			
			while(rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setIdCliente(rs.getString("idCliente"));
				cliente.setNomeCliente(rs.getString("nomeCliente"));
				cliente.setCpfCliente(rs.getString("cpfCliente"));
				cliente.setDataNasc(rs.getString("dataNasc"));
				cliente.setTelefone(rs.getString("telefone"));
				cliente.setEmail(rs.getString("email"));
				cliente.setEndereco(rs.getString("endereco"));
				cliente.setGenero(rs.getString("genero"));
				
				clientes.add(cliente);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao ler informações!", e);
		}finally {
			ConnectionDatabase.closeConnection(con, stmt, rs);
		}
		
		return clientes;//por enquanto, para não dar erro
		
	}
	
    
}
