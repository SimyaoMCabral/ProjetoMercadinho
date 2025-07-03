package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectionFactory.ConnectionDatabase;
import model.Funcionario;

public class FuncionarioDAO {

	public void create(Funcionario funcionario) {//adiconar o cliente, não sei se é metodo
		Connection con = ConnectionDatabase.getConnection(); //instancia de conexão	
		PreparedStatement stmt = null;//classe responsavel para armazenar string para enviar o comando no sql
		//COMO É UMA TENTATIVA DE CONEXÃO FOI PRECISO FAZER AS INFROMAÇÕES ABAIXO "TRY CATCH"
		
		try {
			stmt= con.prepareStatement("INSERT INTO Funcionario values(?, ?, ?, ?, ?, ?, ?, ?, ?)");//a interrogação é um espaço reservado que é reconhecido pelo prepareStatement
			stmt.setString(1, funcionario.getNomeFuncionario());
			stmt.setString(2, funcionario.getCpfFuncionario());
			stmt.setString(3, funcionario.getDataNasc());
			stmt.setString(4, funcionario.getTelefone());
			stmt.setString(5, funcionario.getEmail());
			stmt.setString(6, funcionario.getEndereco());
			stmt.setString(7, funcionario.getGenero());
			stmt.setString(8, funcionario.getCargo());
			stmt.setString(9, funcionario.getNivel());
			///acima estão na ordem de cada espaço vazio que será preenchido
			
			stmt.execute();
			System.out.println("Funcionario cadastrado");
			//abaixo caso não der certo
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao cadastrar!",e);
		}finally{
			ConnectionDatabase.closeConnection(con, stmt);
		}
		
		}
		//
		
		public ArrayList<Funcionario> read() {
			Connection con = ConnectionDatabase.getConnection();
			PreparedStatement stmt = null;
			ResultSet rs= null;
			ArrayList<Funcionario> funcionarios = new ArrayList<>();//irá guardar a lista de cliente 
			
			try {
				stmt = con.prepareStatement("SELECT * FROM Funcionario");
				rs = stmt.executeQuery();
				// laço de repetição para aparecer a lista de uma vez
				
				while(rs.next()) {
					Funcionario funcionario = new Funcionario();
					funcionario.setIdFuncionario(rs.getString("idFuncionario"));
					funcionario.setNomeFuncionario(rs.getString("nomeFuncionario"));
					funcionario.setCpfFuncionario(rs.getString("cpfFuncionario"));
					funcionario.setDataNasc(rs.getString("dataNasc"));
					funcionario.setTelefone(rs.getString("telefone"));
					funcionario.setEmail(rs.getString("email"));
					funcionario.setEndereco(rs.getString("endereco"));
					funcionario.setGenero(rs.getString("endereco"));
					funcionario.setCargo(rs.getString("cargo"));
					funcionario.setNivel(rs.getString("nivel"));
					funcionarios.add(funcionario);
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("Erro ao ler informações!", e);
			}finally {
				ConnectionDatabase.closeConnection(con, stmt, rs);
			}
			
			return funcionarios;//por enquanto, para não dar erro
			
		}
		//
		
	    public void update(Funcionario funcionario) {
	    	Connection con = ConnectionDatabase.getConnection();
	    	PreparedStatement stmt = null;
	    	
	    	//deu erro tem que colocar dentro de uma try catch 
	    	try {
				stmt =con.prepareStatement("UPDATE Funcionario SET nomeFuncionario =?, cpfFuncionario = ?, dataNasc = ?,"
						+ " telefone = ?, email = ?, endereco = ?, genero =?, cargo =?, nivel =? "
						+ "where cpfFuncionario = ?");//string, não adiciona o idCliente porque é imutavel
				//vai utilizar cpdfCliente para indicar a atualizar "where cpfCliente"
				stmt.setString(1, funcionario.getNomeFuncionario());
				stmt.setString(2, funcionario.getCpfFuncionario());
				stmt.setString(3, funcionario.getDataNasc());
				stmt.setString(4, funcionario.getTelefone());
				stmt.setString(5, funcionario.getEmail());
				stmt.setString(6, funcionario.getEndereco());
				stmt.setString(7, funcionario.getGenero());
				stmt.setString(8, funcionario.getCargo());
				stmt.setString(9, funcionario.getNivel());
				stmt.setString(10, funcionario.getCpfFuncionario());
				
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
				stmt = con.prepareStatement("DELETE FROM Funcionario where cpfFuncionario = ?");
				stmt.setString(1,cpf);
				
				stmt.execute();
				System.out.println("Funcionario exlcuido!");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException("Erro ao excluir", e);
			}finally {
				ConnectionDatabase.closeConnection(con, stmt);
			}
	    }
	    
	    //COpiei e colei de read
	    public ArrayList<Funcionario> search(String pesquisa) {// método recebe String para pesquisar no banco de dados
			Connection con = ConnectionDatabase.getConnection();
			PreparedStatement stmt = null;
			ResultSet rs= null;
			ArrayList<Funcionario> funcionarios = new ArrayList<>();//irá guardar a lista de funcionario
			
			try {
				stmt = con.prepareStatement("SELECT * FROM Funcionario where nomeFuncionario like ? or cpfFuncionario like ?");//String de pesquisa
				stmt.setString(1, "%" +pesquisa+ "%"); //a % é para pesquisa qualquer coisa que digitei referente aos "?"
				stmt.setString(2, "%" +pesquisa+ "%");
				
				rs = stmt.executeQuery();
				// laço de repetição para aparecer a lista de uma vez
				
				while(rs.next()) {
					Funcionario funcionario = new Funcionario();
					funcionario.setIdFuncionario(rs.getString("idFuncionario"));
					funcionario.setNomeFuncionario(rs.getString("nomeFuncionario"));
					funcionario.setCpfFuncionario(rs.getString("cpfFuncionario"));
					funcionario.setDataNasc(rs.getString("dataNasc"));
					funcionario.setTelefone(rs.getString("telefone"));
					funcionario.setEmail(rs.getString("email"));
					funcionario.setEndereco(rs.getString("endereco"));
					funcionario.setGenero(rs.getString("genero"));
					funcionario.setCargo(rs.getString("cargo"));
					funcionario.setNivel(rs.getString("nivel"));
					
					
					funcionarios.add(funcionario);
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("Erro ao ler informações!", e);
			}finally {
				ConnectionDatabase.closeConnection(con, stmt, rs);
			}
			
			return funcionarios;//por enquanto, para não dar erro
			
		}
		
	
}
