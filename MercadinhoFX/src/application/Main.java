package application;
	
import java.sql.Connection;

import java.util.ArrayList;

import connectionFactory.ConnectionDatabase;
import dao.FuncionarioDAO;
import dao.ProdutoDAO;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Funcionario;
import model.Produto;
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
		Funcionario funcionario = new Funcionario();
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();//envia as informações
		
			
		
		//CREATE
//		funcionario.setNomeFuncionario("Canario Negro");
//		funcionario.setCpfFuncionario("89767854971");
//		funcionario.setDataNasc("1999-05-15");
//		funcionario.setTelefone("63989014523");
//		funcionario.setEmail("canario@gmail.com");
//		funcionario.setEndereco("Rua Gotham, n 47");
//		funcionario.setGenero("M");
//		funcionario.setCargo("Gerente");
//		funcionario.setNivel("10");
//		
//		funcionarioDAO.create(funcionario);
//		
		
		//READ
		
//		funcionarios = funcionarioDAO.read();
//		for (int i= 0; i<funcionarios.size();i++) {// começa da linha/casa zero i=0 e vai até ter a ultima quantidade de clintes "i<clientes.size"
//			funcionario = funcionarios.get(i);
//		    
//				System.out.print("| |");
//				System.out.print(funcionario.getIdFuncionario());
//				System.out.print(" | ");
//				System.out.print(funcionario.getNomeFuncionario());
//				System.out.print(" | ");
//				System.out.print(funcionario.getDataNasc());
//				System.out.print(" | ");
//				System.out.print(funcionario.getTelefone());
//				System.out.print(" | ");
//				System.out.print(funcionario.getEmail());
//				System.out.print(" | ");
//				System.out.print(funcionario.getEndereco());
//				System.out.print(" | ");
//				System.out.print(funcionario.getGenero());
//				System.out.print(" | ");
//				System.out.print(funcionario.getCargo());
//				System.out.print(" | ");
//				System.out.print(funcionario.getNivel());
//				System.out.print(" | ");
//				System.out.println("");
//		
//		
//		}	
		
		
		//UPATE
		
//	funcionario.setNomeFuncionario("Canario Negro");
//		funcionario.setCpfFuncionario("89767854971");
//	funcionario.setDataNasc("1999-05-15");
//		funcionario.setTelefone("63989764523");
//		funcionario.setEmail("canario@gmail.com");
//		funcionario.setEndereco("Rua Gotham, n 47");
//		funcionario.setGenero("F");
//		funcionario.setCargo("Gerente");
//		funcionario.setNivel("10");
//		
//		funcionarioDAO.update(funcionario);
		
		//DELETE
		
//		funcionarioDAO.delete("90967854323");
		
		
		//SEARCH
	
//		ArrayList<Funcionario> funcionarios =  new ArrayList<>();
//		funcionarios = funcionarioDAO.read(); //todos os clientes vçao está
//		funcionarios = funcionarioDAO.search("Canario Negro");
//		
//		for (int i= 0; i<funcionarios.size();i++) {// começa da linha/casa zero i=0 e vai até ter a ultima quantidade de clintes "i<clientes.size"
//			funcionario = funcionarios.get(i);//armazena cliente e as informações
//			//os clientes tem que estar na mesma linha por isso não usa println
//		
//		System.out.print(" | | ");
//		System.out.print(funcionario.getIdFuncionario());
//		System.out.print(" | ");
//		System.out.print(funcionario.getNomeFuncionario());
//		System.out.print(" | ");
//		System.out.print(funcionario.getCpfFuncionario());
//		System.out.print(" | ");
//		System.out.print(funcionario.getDataNasc());
//		System.out.print(" | ");
//		System.out.print(funcionario.getTelefone());
//		System.out.print(" | ");
//		System.out.print(funcionario.getEmail());
//		System.out.print(" | ");	
//		System.out.print(funcionario.getEndereco());
//		System.out.print(" | ");	
//		System.out.print(funcionario.getGenero());
//		System.out.print(" | ");
//		System.out.print(funcionario.getCargo());
//		System.out.print(" | ");
//		System.out.print(funcionario.getNivel());
//		System.out.print(" | ");
//		System.out.println("");
//		
//		}
		
		
		
		launch(args);
	}
}
