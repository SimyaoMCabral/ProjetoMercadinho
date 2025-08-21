package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connectionFactory.ConnectionDatabase;
import model.RelatorioVenda;
import model.VendaProduto;

public class RelatorioVendaDAO {
	
	public ArrayList<RelatorioVenda> read() {
		Connection con = ConnectionDatabase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs= null;
		ArrayList<RelatorioVenda> relatorioVendas = new ArrayList<>();//irá guardar a lista de cliente 
		
		try {
			stmt = con.prepareStatement("SELECT * FROM VW_RelatorioVendas");
			rs = stmt.executeQuery();
			// laço de repetição para aparecer a lista de uma vez
			
			while(rs.next()) {
				RelatorioVenda relatorioVenda = new RelatorioVenda();
				relatorioVenda.setIdVenda(rs.getString("idVenda"));
				relatorioVenda.setIdProduto(rs.getString("nomeProduto"));
				relatorioVenda.setIdFuncionario(rs.getString("nomeFuncionario"));
				relatorioVenda.setIdCliente(rs.getString("nomeCliente"));
				relatorioVenda.setPrecoUn(rs.getString("precoUnit"));
				relatorioVenda.setQuantidade(rs.getString("quantindade"));
				relatorioVenda.setPrecoTotal(rs.getString("precoTotal"));
				relatorioVenda.setDataVenda(rs.getString("dataVenda"));
				relatorioVendas.add(relatorioVenda);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao ler informações!", e);
		}finally {
			ConnectionDatabase.closeConnection(con, stmt, rs);
		}
		
		return relatorioVendas;//por enquanto, para não dar erro
		
	}
	
	
	public ArrayList<RelatorioVenda> serach(String pesquisa) {
		Connection con = ConnectionDatabase.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs= null;
		pesquisa = "%" + pesquisa + "%";
		ArrayList<RelatorioVenda> relatorioVendas = new ArrayList<>();//irá guardar a lista de cliente 
		
		try {
			stmt = con.prepareStatement("SELECT * FROM VW_RelatorioVendas where nomeFuncionario like ?"
					+ " or nomeCliente like ?");
			stmt.setString(1, pesquisa);
			stmt.setString(2, pesquisa);
			rs = stmt.executeQuery();
			// laço de repetição para aparecer a lista de uma vez
			
			while(rs.next()) {
				RelatorioVenda relatorioVenda = new RelatorioVenda();
				relatorioVenda.setIdVenda(rs.getString("idVenda"));
				relatorioVenda.setIdProduto(rs.getString("nomeProduto"));
				relatorioVenda.setIdFuncionario(rs.getString("nomeFuncionario"));
				relatorioVenda.setIdCliente(rs.getString("nomeCliente"));
				relatorioVenda.setPrecoUn(rs.getString("precoUnit"));
				relatorioVenda.setQuantidade(rs.getString("quantindade"));
				relatorioVenda.setPrecoTotal(rs.getString("precoTotal"));
				relatorioVenda.setDataVenda(rs.getString("dataVenda"));
				relatorioVendas.add(relatorioVenda);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("Erro ao ler informações!", e);
		}finally {
			ConnectionDatabase.closeConnection(con, stmt, rs);
		}
		
		return relatorioVendas;//por enquanto, para não dar erro
		
	}

}
