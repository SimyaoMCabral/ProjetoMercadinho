package application;
	
import java.sql.Connection;


import java.util.ArrayList;

import connectionFactory.ConnectionDatabase;
import dao.ProdutoDAO;
import dao.VendaDAO;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Produto;
import model.Venda;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		//criando objetos
		Venda venda = new Venda();
		VendaDAO vendaDAO = new VendaDAO();//envia as informações
		ArrayList<Venda>vendas=new ArrayList<>();//tem que ter ArrayList para funcionar
			
		
		//CREATE
		
//		venda.setIdCliente("19");
//		venda.setIdFuncionario("36");
//		venda.setValorTotal("500");
//		venda.setQuantTotal("2");
//		venda.setDataVenda("2025-07-03");
//		venda.setDataFab("2023-09-25");
//		venda.setDataVal("2027-09-25");
		
//		vendaDAO.create(venda);
		
		
		//READ
		
//		vendas = vendaDAO.read();
//		for (int i= 0; i<vendas.size();i++) {// começa da linha/casa zero i=0 e vai até ter a ultima quantidade de clintes "i<clientes.size"
//			venda = vendas.get(i);//se não tiver ArrayList da erro (clientes,funcionarios,produtos)
		    
//				System.out.print("| |");
//				System.out.print(venda.getIdVenda());
//				System.out.print(" | ");
//				System.out.print(venda.getIdCliente());
//				System.out.print(" | ");
//				System.out.print(venda.getIdFuncionario());
//				System.out.print(" | ");
//				System.out.print(venda.getValorTotal());
//				System.out.print(" | ");
//				System.out.print(venda.getQuantTotal());
//				System.out.print(" | ");
//				System.out.print(venda.getDataVenda());
//				System.out.print(" | ");
//				System.out.print(venda.getDataFab());
//				System.out.print(" | ");
//				System.out.print(venda.getDataVal());
//				System.out.print(" | ");
//				System.out.println("");
		
//		}	
		
		
		//UPATE
		
//		venda.setIdVenda("10");
//		venda.setIdCliente("17");
//		venda.setIdFuncionario("35");
//		venda.setValorTotal("100.00");
//		venda.setQuantTotal("600");
//		venda.setDataVenda("2023-09-26");
//		venda.setDataVal("2027-11-25");
////		cliente.setCargo("Gerente");
////		cliente.setNivel("10");
		
//		vendaDAO.update(venda);
		
		//DELETE
		
//		vendaDAO.delete("10");//Se tentar deletar cliente com venda da erro
		
		
		//SEARCH
	
//	vendas = vendaDAO.search("4");
//		
//		for (int i= 0; i<vendas.size();i++) {// começa da linha/casa zero i=0 e vai até ter a ultima quantidade de clintes "i<clientes.size"
//			venda = vendas.get(i);//armazena cliente e as informações
//		//os clientes tem que es tar na mesma linha por isso não usa println
//		
//		System.out.print(" | | ");
//		System.out.print(venda.getIdVenda());
//		System.out.print(" | ");
//		System.out.print(venda.getIdCliente());
//		System.out.print(" | ");
//		System.out.print(venda.getIdFuncionario());
//		System.out.print(" | ");
//		System.out.print(venda.getValorTotal());
//		System.out.print(" | ");
//		System.out.print(venda.getQuantTotal());
//		System.out.print(" | ");
//		System.out.print(venda.getDataVenda());
//		System.out.print(" | ");
//		System.out.println("");
//		
//		}
		
		
		
		launch(args);
	}
}
