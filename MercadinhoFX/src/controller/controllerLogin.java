package controller;

import java.io.IOException;

import application.Main;
import dao.FuncionarioDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Funcionario;

public class controllerLogin {

    @FXML
    private Button btLogin;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private TextField txtUser;

    static Funcionario funcionario = new Funcionario();//Os outros objetos precisam ser visualizados
	FuncionarioDAO funcDAO = new FuncionarioDAO();//Esse dois metodos serão publicos
    
    @FXML
    void actionLogin(ActionEvent event) throws IOException {
    	String usuario = txtUser.getText();
    	String senha = txtSenha.getText();
    	
    	funcionario = funcDAO.autenticarUser(usuario, senha);
    	//abaixo foi utilizado para fazer debug, mas nãp foi preciso porque eu estava colocando nome do funcionario ao inves de cpf
    	System.out.println(funcionario.getCpfFuncionario());
    	System.out.println(funcionario.getSenha());
    	//
    	if(funcionario.getCpfFuncionario()!=null) {//Abertura IF
    	if(usuario.equals("") || senha.equals("")) {
    		Alert aviso = new Alert(AlertType.ERROR);
    		aviso.setTitle("Erro ao realizar login");
    		aviso.setContentText("Verifique se as informações estão corretas e tente novamente");
    		aviso.show();
    		
    	}else if(funcionario.getCpfFuncionario().equals(usuario) && funcionario.getSenha().equals(senha)){
    		Alert saudacao = new Alert(AlertType.INFORMATION);
    		saudacao.setHeaderText("Seja bem vindo!");
    		saudacao.setContentText("Seja bem vindo de volta"+ funcionario.getNomeFuncionario() + "!");
    		saudacao.show();
    		Main.TelaHome(); 		
    	}//Fechamneto de IF
    	}else {
    		Alert aviso = new Alert(AlertType.ERROR);
    		aviso.setTitle("Erro ao realizar login");
    		aviso.setContentText("Verifique se as informações estão corretas e tente novamente");
    		aviso.show();
    	}
    }

}
