package application;
	
import java.sql.Connection;

import connectionFactory.ConnectionDatabase;
import dao.ClienteDAO;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Cliente;
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
		Cliente cliente = new Cliente();
		ClienteDAO clienteDAO = new ClienteDAO();//envia as informações
		
		//adicionar um cliente ao banco de dados
		cliente.setNomeCliente("Alfredo");
		cliente.setCpfCliente("78967843299");
		cliente.setDataNasc("2000-05-22");
		cliente.setTelefone("63992898987");
		cliente.setEmail("alfredo@gmail.com");
		cliente.setEndereco("Rua das rapaduras");
		cliente.setGenero("P");
		
		clienteDAO.delete("89012345678");
		
		launch(args);
	}
}
