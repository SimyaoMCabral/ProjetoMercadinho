package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectionFactory.ConnectionDatabase;
import model.Venda;

public class VendaDAO {
	
	public void create(Venda venda) {//adiconar o cliente, não sei se é metodo
		Connection con = ConnectionDatabase.getConnection(); //instancia de conexão	
		PreparedStatement stmt = null;//classe responsavel para armazenar string para enviar o comando no sql
		//COMO É UMA TENTATIVA DE CONEXÃO FOI PRECISO FAZER AS INFROMAÇÕES ABAIXO "TRY CATCH"
		
		try {
			stmt= con.prepareStatement("INSERT INTO Venda values(?, ?, ?, ?, ?)");//a interrogação é um espaço reservado que é reconhecido pelo prepareStatement
			stmt.setString(1, venda.getIdCliente());
			stmt.setString(2, venda.getIdFuncionario());
			stmt.setString(3, venda.getValorTotal());
			stmt.setString(4, venda.getQuantTotal());
			stmt.setString(5, venda.getDataVenda());
			///acima estão na ordem de cada espaço vazio que será preenchido
			
			stmt.execute();
			System.out.println("Venda registrada");
			//abaixo caso não der certo
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao registrar!",e);
		}finally{
			ConnectionDatabase.closeConnection(con, stmt);
		}
		
		}
		//
		
		public ArrayList<Venda> read() {
			Connection con = ConnectionDatabase.getConnection();
			PreparedStatement stmt = null;
			ResultSet rs= null;
			ArrayList<Venda> vendas = new ArrayList<>();//irá guardar a lista de cliente 
			
			try {
				stmt = con.prepareStatement("SELECT * FROM Venda");
				rs = stmt.executeQuery();
				// laço de repetição para aparecer a lista de uma vez
				
				while(rs.next()) {
					Venda venda = new Venda();
					venda.setIdVenda(rs.getString("idVenda"));
					venda.setIdCliente(rs.getString("idCliente"));
					venda.setIdFuncionario(rs.getString("idFuncionario"));
					venda.setValorTotal(rs.getString("valorTotal"));
					venda.setQuantTotal(rs.getString("quantTotal"));
					venda.setDataVenda(rs.getString("dataVenda"));
										
					vendas.add(venda);
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("Erro ao ler informações!", e);
			}finally {
				ConnectionDatabase.closeConnection(con, stmt, rs);
			}
			
			return vendas;//por enquanto, para não dar erro
			
		}
		//
		
	    public void update(Venda venda) {
	    	Connection con = ConnectionDatabase.getConnection();
	    	PreparedStatement stmt = null;
	    	
	    	//deu erro tem que colocar dentro de uma try catch 
	    	try {
				stmt =con.prepareStatement("UPDATE Venda SET idCliente =?, idFuncionario = ?, "
						+ "valorTotal =?, quantTotal = ?, dataVenda = ?,"
						+ "where idVenda = ?");//string, não adiciona o idCliente porque é imutavel
				//vai utilizar cpdfCliente para indicar a atualizar "where cpfCliente"
				stmt.setString(1, venda.getIdCliente());
				stmt.setString(2, venda.getIdFuncionario());
				stmt.setString(3, venda.getValorTotal());
				stmt.setString(4, venda.getQuantTotal());
				stmt.setString(5, venda.getDataVenda());
				stmt.setString(6, venda.getIdVenda());
				
				stmt.execute();//para executar o comando
				System.out.println("Venda atualizada!");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("Erro ao atualizar!", e);
			}finally {
				ConnectionDatabase.closeConnection(con, stmt);
			}
	    	
	    }
	    //
		
	    public void delete(String idVenda) {
	    	Connection con = ConnectionDatabase.getConnection();
	    	PreparedStatement stmt = null;
	    	
	    	try {
				stmt = con.prepareStatement("DELETE FROM Produto where idVenda = ?");
				stmt.setString(1,idVenda);
				
				stmt.execute();
				System.out.println("Venda exlcuida!");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException("Erro ao excluir", e);
			}finally {
				ConnectionDatabase.closeConnection(con, stmt);
			}
	    }
	    
	    //
	    public ArrayList<Venda> search(String pesquisa) {// método recebe String para pesquisar no banco de dados
			Connection con = ConnectionDatabase.getConnection();
			PreparedStatement stmt = null;
			ResultSet rs= null;
			ArrayList<Venda> vendas = new ArrayList<>();//irá guardar a lista de cliente 
			
			try {
				stmt = con.prepareStatement("SELECT * FROM Venda where idVenda like ?");//String de pesquisa
				stmt.setString(1, "%" +pesquisa+ "%"); //a % é para pesquisa qualquer coisa que digitei referente aos "?"
	
				
				rs = stmt.executeQuery();
				// laço de repetição para aparecer a lista de uma vez
				
				while(rs.next()) {
					Venda venda = new Venda();
					venda.setIdVenda(rs.getString("idVenda"));
					venda.setIdCliente(rs.getString("idCliente"));
					venda.setIdFuncionario(rs.getString("IdFuncionario"));
					venda.setValorTotal(rs.getString("valorTotal"));
					venda.setQuantTotal(rs.getString("quantTotal"));
					venda.setDataVenda(rs.getString("dataVenda"));
					
					vendas.add(venda);
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("Erro ao ler informações!", e);
			}finally {
				ConnectionDatabase.closeConnection(con, stmt, rs);
			}
			
			return vendas;//por enquanto, para não dar erro
			
		}
		

}
