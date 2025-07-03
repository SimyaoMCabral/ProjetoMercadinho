package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectionFactory.ConnectionDatabase;
import model.VendaProduto;


public class VendaProdutoDAO {
	
	public void create(VendaProduto vendaproduto) {//adiconar o cliente, não sei se é metodo
		Connection con = ConnectionDatabase.getConnection(); //instancia de conexão	
		PreparedStatement stmt = null;//classe responsavel para armazenar string para enviar o comando no sql
		//COMO É UMA TENTATIVA DE CONEXÃO FOI PRECISO FAZER AS INFROMAÇÕES ABAIXO "TRY CATCH"
		
		try {
			stmt= con.prepareStatement("INSERT INTO Cliente values(?, ?, ?, ?, ?)");//a interrogação é um espaço reservado que é reconhecido pelo prepareStatement
			stmt.setString(1, vendaproduto.getIdVenda());
			stmt.setString(2, vendaproduto.getIdProduto());
			stmt.setString(3, vendaproduto.getPrecoUnit());
			stmt.setString(4, vendaproduto.getQuantindade());
			stmt.setString(5, vendaproduto.getPrecoTotal());
			///acima estão na ordem de cada espaço vazio que será preenchido
			
			stmt.execute();
			System.out.println("VendaProduto cadastrado");
			//abaixo caso não der certo
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao cadastrar!",e);
		}finally{
			ConnectionDatabase.closeConnection(con, stmt);
		}
		
		}
		//
		
		public ArrayList<VendaProduto> read() {
			Connection con = ConnectionDatabase.getConnection();
			PreparedStatement stmt = null;
			ResultSet rs= null;
			ArrayList<VendaProduto> vendaprodutos = new ArrayList<>();//irá guardar a lista de cliente 
			
			try {
				stmt = con.prepareStatement("SELECT * FROM VendaProduto");
				rs = stmt.executeQuery();
				// laço de repetição para aparecer a lista de uma vez
				
				while(rs.next()) {
					VendaProduto vendaproduto = new VendaProduto();
					vendaproduto.setIdVendaProduto(rs.getString("idVendaProduto"));
					vendaproduto.setIdVenda(rs.getString("idVenda"));
					vendaproduto.setIdProduto(rs.getString("idProduto"));
					vendaproduto.setPrecoUnit(rs.getString("precoUnit"));
					vendaproduto.setQuantindade(rs.getString("quantindade"));
					vendaproduto.setPrecoTotal(rs.getString("precoTotal"));
					
					vendaprodutos.add(vendaproduto);
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("Erro ao ler informações!", e);
			}finally {
				ConnectionDatabase.closeConnection(con, stmt, rs);
			}
			
			return vendaprodutos;//por enquanto, para não dar erro
			
		}
		//
		
	    public void update(VendaProduto vendaproduto) {
	    	Connection con = ConnectionDatabase.getConnection();
	    	PreparedStatement stmt = null;
	    	
	    	//deu erro tem que colocar dentro de uma try catch 
	    	try {
				stmt =con.prepareStatement("UPDATE VendaProduto SET idVenda =?, idProduto = ?, PrecoUnit = ?,"
						+ " quantindade = ?, precoTotal = ? where idVendaProduto = ?");//string, não adiciona o idCliente porque é imutavel
				//vai utilizar cpdfCliente para indicar a atualizar "where cpfCliente"
				stmt.setString(1, vendaproduto.getIdVenda());
				stmt.setString(2, vendaproduto.getIdProduto());
				stmt.setString(3, vendaproduto.getPrecoUnit());
				stmt.setString(4, vendaproduto.getQuantindade());
				stmt.setString(5, vendaproduto.getPrecoTotal());
				stmt.setString(6, vendaproduto.getIdVendaProduto());
				
				
				stmt.execute();//para executar o comando
				System.out.println("VendaProduto atualizado!");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("Erro ao atualizar!", e);
			}finally {
				ConnectionDatabase.closeConnection(con, stmt);
			}
	    	
	    }
	    //
		
	    public void delete(String idVendaProduto) {
	    	Connection con = ConnectionDatabase.getConnection();
	    	PreparedStatement stmt = null;
	    	
	    	try {
				stmt = con.prepareStatement("DELETE FROM VendaProduto where idVendaProduto = ?");
				stmt.setString(1,idVendaProduto);
				
				stmt.execute();
				System.out.println("VendaProduto exlcuido!");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException("Erro ao excluir", e);
			}finally {
				ConnectionDatabase.closeConnection(con, stmt);
			}
	    }
	    
	    //COpiei e colei de read
	    public ArrayList<VendaProduto> search(String pesquisa) {// método recebe String para pesquisar no banco de dados
			Connection con = ConnectionDatabase.getConnection();
			PreparedStatement stmt = null;
			ResultSet rs= null;
			ArrayList<VendaProduto> vendaprodutos = new ArrayList<>();//irá guardar a lista de cliente 
			
			try {
				stmt = con.prepareStatement("SELECT * FROM VendaProduto where idVendaProduto like ? ");//String de pesquisa
				stmt.setString(1, "%" +pesquisa+ "%"); //a % é para pesquisa qualquer coisa que digitei referente aos "?"
				
				
				rs = stmt.executeQuery();
				// laço de repetição para aparecer a lista de uma vez
				
				while(rs.next()) {
					VendaProduto vendaproduto = new VendaProduto();
					vendaproduto.setIdVendaProduto(rs.getString("idVendaProduto"));
					vendaproduto.setIdVenda(rs.getString("idVenda"));
					vendaproduto.setIdProduto(rs.getString("idProduto"));
					vendaproduto.setPrecoUnit(rs.getString("precoUnit"));
					vendaproduto.setQuantindade(rs.getString("quantindade"));
					vendaproduto.setPrecoTotal(rs.getString("precoTotal"));
					
					vendaprodutos.add(vendaproduto);
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("Erro ao ler informações!", e);
			}finally {
				ConnectionDatabase.closeConnection(con, stmt, rs);
			}
			
			return vendaprodutos;//por enquanto, para não dar erro
			
		}
		

}
