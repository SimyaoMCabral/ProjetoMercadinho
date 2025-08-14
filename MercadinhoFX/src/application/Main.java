package application;
	
import java.io.IOException;
import java.sql.Connection;


import java.util.ArrayList;

import connectionFactory.ConnectionDatabase;
import dao.ProdutoDAO;
import dao.VendaDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Produto;
import model.Venda;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	private static Stage stage;
	private static Scene main;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			stage = primaryStage;
			Parent fxmlLogin = FXMLLoader.load(getClass().getResource("/view/viewLogin.fxml"));
			main = new Scene(fxmlLogin);//nesse método não consio fzer modificação porque inicia com a tela
			
			stage.setTitle("Tela de login");
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/Casa.png")));
			
			primaryStage.setScene(main);//para tela rodar
			primaryStage.show(); //exibir a tela
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	//método para fazer voltar para tela de logim
	public static void TelaLogin() throws IOException {
		FXMLLoader fxmlLogin = new FXMLLoader();
		fxmlLogin.setLocation(Main.class.getResource("/view/viewLogin.fxml"));
		Parent TelaLogin = fxmlLogin.load();
		main = new Scene(TelaLogin);
		stage.setTitle("Tela de login");
		stage.setScene(main);
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();
	}
	
	//Método de transição de tela
	public static void TelaHome() throws IOException {
		FXMLLoader fxmlHome = new FXMLLoader();//nesse método consigo fazer modificações
		fxmlHome.setLocation(Main.class.getResource("/view/viewMainMenu.fxml"));
		Parent TelaHome = fxmlHome.load();
		main = new Scene(TelaHome);
		stage.setTitle("Mercadinho - Menu principal");
		stage.setScene(main);
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();
	}
		
	
	public static void TelaCliente() throws IOException {
		FXMLLoader fxmlCliente = new FXMLLoader();//nesse método consigo fazer modificações
		fxmlCliente.setLocation(Main.class.getResource("/view/viewRelatorioClientes.fxml"));
		Parent TelaCliente = fxmlCliente.load();
		main = new Scene(TelaCliente);
		stage.setTitle("Mercadinho - Relatorio de clientes");
		stage.setScene(main);
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();
	}
	
	public static void TelaProduto() throws IOException {
		FXMLLoader fxmlProduto = new FXMLLoader();//nesse método consigo fazer modificações
		fxmlProduto.setLocation(Main.class.getResource("/view/viewRelatorioProdutos.fxml"));
		Parent TelaProduto = fxmlProduto.load();
		main = new Scene(TelaProduto);
		stage.setTitle("Mercadinho - Relatorio de Produtos");
		stage.setScene(main);
		stage.setResizable(false);
		stage.centerOnScreen();
		stage.show();
	}
	
	private static Stage cadCliente;
	public static void TelaCadastroClin() throws IOException{
		FXMLLoader ClienteCadastro = new FXMLLoader();
		ClienteCadastro.setLocation(Main.class.getResource("/View/viewCadastroCliente.fxml"));
		Parent cadastroClin = ClienteCadastro.load();
		Scene scene2 = new Scene(cadastroClin);
		
		cadCliente = new Stage();
		cadCliente.setTitle("Cadastro/Edição de Cliente");
		cadCliente.initModality(Modality.WINDOW_MODAL);
		cadCliente.setScene(scene2);
		cadCliente.centerOnScreen();
		cadCliente.showAndWait();
	}
	
	private static Stage cadProduto;
	public static void TelaCadastroProd() throws IOException{
		FXMLLoader ProdutoCadastro = new FXMLLoader();
		ProdutoCadastro.setLocation(Main.class.getResource("/View/viewCadastroProduto.fxml"));
		Parent cadastroProd = ProdutoCadastro.load();
		Scene scene2 = new Scene(cadastroProd);
		
		cadProduto = new Stage();
		cadProduto.setTitle("Cadastro/Edição de Produto");
		cadProduto.initModality(Modality.WINDOW_MODAL);
		cadProduto.setScene(scene2);
		cadProduto.centerOnScreen();
		cadProduto.showAndWait();
	}
	
public static void main (String[] args)		{
	 
	launch(args);
	
}
		
		
		
	}
