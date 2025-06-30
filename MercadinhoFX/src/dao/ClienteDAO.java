package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
	
	public void read() {
		
	}
	
    public void update(Cliente cliente) {
    	Connection con = ConnectionDatabase.getConnection();
    	PreparedStatement stmt = null;
    	
    	//deu erro tem que colocar dentro de uma try catch 
    	try {
			stmt =con.prepareStatement("UPDATE Cliente SET nomeCliente =?, cpfCliente = ?, dataNasc = ?,"
					+ " telefone = ?, email = ?, endereco = ?, genero =? ");//string, não adiciona o idCliente porque é imutavel
			stmt.setString(1, cliente.getNomeCliente());
			stmt.setString(2, cliente.getCpfCliente());
			stmt.setString(3, cliente.getDataNasc());
			stmt.setString(4, cliente.getTelefone());
			stmt.setString(5, cliente.getEmail());
			stmt.setString(6, cliente.getEndereco());
			stmt.setString(7, cliente.getGenero());
			
			stmt.execute();//para executar o comando
			System.out.println("Cliente atualizado!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao atualizar!", e);
		}finally {
			ConnectionDatabase.closeConnection(con, stmt);
		}
    	
    }
	
}
