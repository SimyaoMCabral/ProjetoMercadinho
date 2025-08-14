package controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import dao.ClienteDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Cliente;

public class controllerCadastroCliente implements Initializable {

    @FXML
    private Button btCancelar;

    @FXML
    private Button btSalvar;

    @FXML
    private TextField txtCpf;

    @FXML
    private DatePicker txtDataNasc;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtEndereco;

    @FXML
    private ChoiceBox<String> txtGenero;// irá receber string. Não instancia os valores nela, quando a tela abre passamos para ela

    @FXML
    private TextField txtNomeCliente;

    @FXML
    private TextField txtTelefone;

    @FXML
    void actionCancelar(ActionEvent event) {
    	
    	controllerRelatorioClientes.clienteEditar = null;//Sempre definit cliente igual null
    	Stage stage = (Stage) btCancelar.getScene().getWindow();
    	stage.close();

    }

    @FXML
    void actionSalvar(ActionEvent event) {
    	//criar objeto func e funcDAO
    	Cliente cliente = new Cliente();
    	ClienteDAO clienteDAO = new ClienteDAO();
    	// tem que verificar se todos os objetos são diferentes de zero
    	
    	if(txtNomeCliente.equals("") || txtDataNasc.getValue() == null|| txtEndereco.equals("")) {
    		Alert erro = new Alert(AlertType.ERROR);
    		erro.setTitle("Erro!");
    		erro.setContentText("Erro ao cadastrar! Verifique se os campos foram Preenchidos corretamente e tente novamente. ");
    		erro.show();
    	}else if(!validarCPF(txtCpf.getText())) {// precisa da exclamação para dar certo
    		Alert erro = new Alert(AlertType.ERROR);
    		erro.setTitle("Erro!");
    		erro.setContentText("Erro ao cadastrar! Verifique se o CPF digitado está correto!");
    		erro.show();
    		
    	}else {
            //Foi deixado aqui, pois essa parte é em comum entre create e upadate
    		cliente.setNomeCliente(txtNomeCliente.getText());
        	cliente.setCpfCliente(txtCpf.getText());
        	cliente.setTelefone(txtTelefone.getText());
        	cliente.setEmail(txtEmail.getText());
        	cliente.setEndereco(txtEndereco.getText());
        	cliente.setGenero(txtGenero.getValue());
        	cliente.setDataNasc(txtDataNasc.getValue().toString());
        	clienteDAO.create(cliente);
    		
    		if(controllerRelatorioClientes.clienteEditar==null) {
    			//para criar
    		clienteDAO.create(cliente);
        	//Mensagem
        	Alert msg = new Alert(AlertType.INFORMATION);
        	msg.setTitle("Sucesso!");
        	msg.setContentText("Cliente cadastrado com sucesso! ");
        	msg.show();
        	Stage stage = (Stage) btSalvar.getScene().getWindow();
        	stage.close();// a janela fecha após o cadastro
    	}else {
    		// para editar
        	clienteDAO.update(cliente);
        	//Mensagem
        	Alert msg = new Alert(AlertType.INFORMATION);
        	msg.setTitle("Sucesso!");
        	msg.setContentText("Cliente cadastrado com sucesso! ");
        	msg.show();
        	controllerRelatorioClientes.clienteEditar = null;
        	Stage stage = (Stage) btSalvar.getScene().getWindow();
        	stage.close();// a janela fecha após o cadastro
    	}
    	}
    	

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		txtGenero.getItems().add("F");
		txtGenero.getItems().add("M");
		
		if(controllerRelatorioClientes.clienteEditar != null) {//se o cliente for editar e for diferente de vazio signfica que ele quer editar
			Cliente cliente = new Cliente();
			cliente = controllerRelatorioClientes.clienteEditar;
			txtNomeCliente.setText(cliente.getNomeCliente());
			txtTelefone.setText(cliente.getTelefone());
			txtEndereco.setText(cliente.getEndereco());
			txtEmail.setText(cliente.getCpfCliente());
			txtGenero.setValue(cliente.getGenero());
			String dataNasc = cliente.getDataNasc();
			dataNasc = dataNasc.replace("-", "/");
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			LocalDate locaDate = LocalDate.parse(dataNasc, format);
			txtDataNasc.setValue(locaDate);
			//quandor for editar um cliente não pode editar um CPF
			txtDataNasc.setValue(locaDate);
			
		}
		
		
	}
	//Método para verificar cpf
	public static boolean validarCPF(String cpf) {
        // Verifica se o CPF tem 11 dígitos
        if (cpf == null || !cpf.matches("\\d{11}")) {
            return false;
        }

        // Verifica se todos os dígitos são iguais (caso inválido)
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        try {
            // Calcula o primeiro dígito verificador
            int soma = 0;
            for (int i = 0; i < 9; i++) {
                soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
            }
            int resto = 11 - (soma % 11);
            int digito1 = (resto == 10 || resto == 11) ? 0 : resto;

            // Calcula o segundo dígito verificador
            soma = 0;
            for (int i = 0; i < 10; i++) {
                soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
            }
            resto = 11 - (soma % 11);
            int digito2 = (resto == 10 || resto == 11) ? 0 : resto;

            // Compara com os dígitos informados
            return digito1 == Character.getNumericValue(cpf.charAt(9)) &&
                   digito2 == Character.getNumericValue(cpf.charAt(10));

        } catch (NumberFormatException e) {
            return false;
        }
    }

}
