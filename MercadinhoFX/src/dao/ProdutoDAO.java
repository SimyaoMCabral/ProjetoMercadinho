package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectionFactory.ConnectionDatabase;
import model.Produto;

public class ProdutoDAO {

	public void create(Produto produto) {//adiconar o cliente, não sei se é metodo
		Connection con = ConnectionDatabase.getConnection(); //instancia de conexão	
		PreparedStatement stmt = null;//classe responsavel para armazenar string para enviar o comando no sql
		//COMO É UMA TENTATIVA DE CONEXÃO FOI PRECISO FAZER AS INFROMAÇÕES ABAIXO "TRY CATCH"
		
		try {
			stmt= con.prepareStatement("INSERT INTO Produto values(?, ?, ?, ?, ?, ?, ?)");//a interrogação é um espaço reservado que é reconhecido pelo prepareStatement
			stmt.setString(1, produto.getNomeProduto());
			stmt.setString(2, produto.getCodBarra());
			stmt.setString(3, produto.getTipoUnit());
			stmt.setString(4, produto.getPrecoUnit());
			stmt.setString(5, produto.getEstoque());
			stmt.setString(6, produto.getDataFab());
			stmt.setString(7, produto.getDataVal());
			///acima estão na ordem de cada espaço vazio que será preenchido
			
			stmt.execute();
			System.out.println("Produto cadastrado");
			//abaixo caso não der certo
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao cadastrar!",e);
		}finally{
			ConnectionDatabase.closeConnection(con, stmt);
		}
		
		}
		//
		
		public ArrayList<Produto> read() {
			Connection con = ConnectionDatabase.getConnection();
			PreparedStatement stmt = null;
			ResultSet rs= null;
			ArrayList<Produto> produtos = new ArrayList<>();//irá guardar a lista de cliente 
			
			try {
				stmt = con.prepareStatement("SELECT * FROM Produto");
				rs = stmt.executeQuery();
				// laço de repetição para aparecer a lista de uma vez
				
				while(rs.next()) {
					Produto produto = new Produto();
					produto.setIdProduto(rs.getString("idProduto"));
					produto.setNomeProduto(rs.getString("nomeProduto"));
					produto.setCodBarra(rs.getString("codBarra"));
					produto.setTipoUnit(rs.getString("tipoUnit"));
					produto.setPrecoUnit(rs.getString("precoUnit"));
					produto.setEstoque(rs.getString("estoque"));
					produto.setDataFab(rs.getString("dataFab"));
					produto.setDataVal(rs.getString("dataVal"));
					
					produtos.add(produto);
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("Erro ao ler informações!", e);
			}finally {
				ConnectionDatabase.closeConnection(con, stmt, rs);
			}
			
			return produtos;//por enquanto, para não dar erro
			
		}
		//
		
	    public void update(Produto produto) {
	    	Connection con = ConnectionDatabase.getConnection();
	    	PreparedStatement stmt = null;
	    	
	    	//deu erro tem que colocar dentro de uma try catch 
	    	try {
				stmt =con.prepareStatement("UPDATE Produto SET nomeProduto =?, codBarra = ?, tipoUnit = ?,"
						+ " precoUnit = ?, estoque = ?, dataFab = ?, dataVal =? "
						+ "where codBarra = ?");//string, não adiciona o idCliente porque é imutavel
				//vai utilizar cpdfCliente para indicar a atualizar "where cpfCliente"
				stmt.setString(1, produto.getNomeProduto());
				stmt.setString(2, produto.getCodBarra());
				stmt.setString(3, produto.getTipoUnit());
				stmt.setString(4, produto.getPrecoUnit());
				stmt.setString(5, produto.getEstoque());
				stmt.setString(6, produto.getDataFab());
				stmt.setString(7, produto.getDataVal());
				stmt.setString(8, produto.getCodBarra());
				
				stmt.execute();//para executar o comando
				System.out.println("Produto atualizado!");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("Erro ao atualizar!", e);
			}finally {
				ConnectionDatabase.closeConnection(con, stmt);
			}
	    	
	    }
	    //
		
	    public void delete(String cod) {
	    	Connection con = ConnectionDatabase.getConnection();
	    	PreparedStatement stmt = null;
	    	
	    	try {
				stmt = con.prepareStatement("DELETE FROM Produto where codBarra = ?");
				stmt.setString(1,cod);
				
				stmt.execute();
				System.out.println("Produto exlcuido!");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException("Erro ao excluir", e);
			}finally {
				ConnectionDatabase.closeConnection(con, stmt);
			}
	    }
	    
	    //COpiei e colei de read
	    public ArrayList<Produto> search(String pesquisa) {// método recebe String para pesquisar no banco de dados
			Connection con = ConnectionDatabase.getConnection();
			PreparedStatement stmt = null;
			ResultSet rs= null;
			ArrayList<Produto> produtos = new ArrayList<>();//irá guardar a lista de produto
			
			try {
				stmt = con.prepareStatement("SELECT * FROM Produto where nomeProduto like ? or codBarra like ?");//String de pesquisa
				stmt.setString(1, "%" +pesquisa+ "%"); //a % é para pesquisa qualquer coisa que digitei referente aos "?"
				stmt.setString(2, "%" +pesquisa+ "%");
				
				rs = stmt.executeQuery();
				// laço de repetição para aparecer a lista de uma vez
				
				while(rs.next()) {
					Produto produto = new Produto();
					produto.setIdProduto(rs.getString("idProduto"));
					produto.setNomeProduto(rs.getString("nomeProduto"));
					produto.setCodBarra(rs.getString("codBarra"));
					produto.setTipoUnit(rs.getString("tipoUnit"));
					produto.setPrecoUnit(rs.getString("precoUnit"));
					produto.setEstoque(rs.getString("estoque"));
					produto.setDataFab(rs.getString("dataFab"));
					produto.setDataVal(rs.getString("dataVal"));
					
					produtos.add(produto);
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("Erro ao ler informações!", e);
			}finally {
				ConnectionDatabase.closeConnection(con, stmt, rs);
			}
			
			return produtos;//por enquanto, para não dar erro
			
		}
	    
	    public ArrayList<Produto> readProdAV() {
			Connection con = ConnectionDatabase.getConnection();
			PreparedStatement stmt = null;
			ResultSet rs= null;
			ArrayList<Produto> produtos = new ArrayList<>();//irá guardar a lista de cliente 
			
			try {
				stmt = con.prepareStatement("SELECT * FROM VW_ProdutoAVencer");
				rs = stmt.executeQuery();
				int i = 1;
				// laço de repetição para aparecer a lista de uma vez
				
				while(rs.next()) {
					Produto produto = new Produto();
					produto.setIdProduto(""+i);
					
					produto.setNomeProduto(rs.getString("nomeProduto"));
					produto.setCodBarra(rs.getString("codBarra"));
					produto.setTipoUnit(rs.getString("tipoUnit"));
					produto.setDataVal(rs.getString("dataVal"));
					produtos.add(produto);
					i++;// para adicionar o valores 
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("Erro ao ler informações!", e);
			}finally {
				ConnectionDatabase.closeConnection(con, stmt, rs);
			}
			
			return produtos;//por enquanto, para não dar erro
			
		}
	    
	    public ArrayList<Produto> readProdEB() {
			Connection con = ConnectionDatabase.getConnection();
			PreparedStatement stmt = null;
			ResultSet rs= null;
			ArrayList<Produto> produtos = new ArrayList<>();//irá guardar a lista de cliente 
			
			try {
				stmt = con.prepareStatement("SELECT * FROM VW_EstoqueBaixo");
				rs = stmt.executeQuery();
				int i = 1;
				// laço de repetição para aparecer a lista de uma vez
				
				while(rs.next()) {
					Produto produto = new Produto();
					produto.setIdProduto(""+i);
					
					produto.setNomeProduto(rs.getString("nomeProduto"));
					produto.setCodBarra(rs.getString("codBarra"));
					produto.setTipoUnit(rs.getString("tipoUnit"));
					produto.setEstoque(rs.getString("estoque"));
					produtos.add(produto);
					i++;// para adicionar o valores 
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("Erro ao ler informações!", e);
			}finally {
				ConnectionDatabase.closeConnection(con, stmt, rs);
			}
			
			return produtos;//por enquanto, para não dar erro
			
		}
	
}
