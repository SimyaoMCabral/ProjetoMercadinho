package controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import dao.ProdutoDAO;
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
import model.Produto;

public class controllerCadastroProduto implements Initializable {

    @FXML
    private Button btCancelar;

    @FXML
    private Button btSalvar;

    @FXML
    private TextField txtCodBarr;

    @FXML
    private DatePicker txtDataFab;

    @FXML
    private DatePicker txtDataVal;

    @FXML
    private TextField txtEstoque;

    @FXML
    private TextField txtNomeProd;

    @FXML
    private TextField txtPrecUn;

    @FXML
    private ChoiceBox<String> txtTipoUn;

    @FXML
    void actionCancelar(ActionEvent event) {
    	
    	Stage stage = (Stage) btCancelar.getScene().getWindow();
    	stage.close();

    }

    @FXML
    void actionSalvar(ActionEvent event) {
    	
    	//Todas os campos são obrigatorios, dessa forma irá ser feito um processo de verificação
    	if(txtNomeProd.getText().equals("") || txtCodBarr.getText().equals("") || txtPrecUn.getText().equals("") || 
    			txtDataFab.getValue().toString().equals("") || txtDataVal.getValue().toString().equals("") ||
    			txtEstoque.getText().equals("") || txtTipoUn.getValue().equals("")) {
    		Alert erro = new Alert(AlertType.ERROR);
    		erro.setTitle("Erro!");
    		erro.setContentText("Erro ao cadastrar! Verifique se os campos estão preenchidos corretamente e tente novamente");
    		erro.show();
    		
    		
    	}else if(!isDouble(txtPrecUn.getText())) {
    		Alert erro = new Alert(AlertType.ERROR);
    		erro.setTitle("Erro!");
    		erro.setContentText("Erro ao cadastrar! Verifique se os campos estão preenchidos corretamente e tente novamente");
    		erro.show();
    		
    	}else if(!isDouble(txtEstoque.getText())) {
    		Alert erro = new Alert(AlertType.ERROR);
    		erro.setTitle("Erro!");
    		erro.setContentText("Erro ao cadastrar! Verifique se os campos estão preenchidos corretamente e tente novamente");
    		erro.show();
    	}else {
    		//se tudo tiver preenchido
    		Produto produto = new Produto();
    		ProdutoDAO prodDAO = new ProdutoDAO();
    		produto.setNomeProduto(txtNomeProd.getText());
    		produto.setCodBarra(txtCodBarr.getText());
    		produto.setEstoque(txtEstoque.getText());
    		produto.setPrecoUnit(txtPrecUn.getText());
    		produto.setTipoUnit(txtTipoUn.getValue());
    		produto.setDataFab(txtDataFab.getValue().toString());
    		produto.setDataVal(txtDataVal.getValue().toString());
    		
    		//verificado para diferenciar entre cadastrar e update
    		if(controllerRelatorioProdutos.produtoEditar == null) {
    			prodDAO.create(produto);
    			
    			Alert msg = new Alert(AlertType.INFORMATION);
        		msg.setTitle("Sucesso!");
        		msg.setContentText("Produto Cadastrado com sucesso! ");
        		msg.show();
        		Stage stage = (Stage) btSalvar.getScene().getWindow();
        		stage.close();
    			
    		}else {
    			prodDAO.update(produto);
    			
    			Alert msg = new Alert(AlertType.INFORMATION);
        		msg.setTitle("Sucesso!");
        		msg.setContentText("Produto atualizado com sucesso! ");
        		msg.show();
        		Stage stage = (Stage) btSalvar.getScene().getWindow();
        		stage.close();
    		}   		
    		
    	}

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		txtTipoUn.getItems().add("UN");
		txtTipoUn.getItems().add("KG");
		txtTipoUn.getItems().add("LT");
		txtTipoUn.getItems().add("GM");
		
		if(controllerRelatorioProdutos.produtoEditar!=null) {
			Produto produto = new Produto();
			produto = controllerRelatorioProdutos.produtoEditar;
			
			txtNomeProd.setText(produto.getNomeProduto());
			txtCodBarr.setText(produto.getCodBarra());
			txtEstoque.setText(produto.getEstoque());
			txtTipoUn.setValue(produto.getTipoUnit());
			txtPrecUn.setText(produto.getPrecoUnit());
			
			String dataFab = produto.getDataFab();
			dataFab = dataFab.replace("-", "/");
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			LocalDate locaDate = LocalDate.parse(dataFab, format);
			txtDataFab.setValue(locaDate);
			
			String dataVal = produto.getDataVal();
			dataVal = dataVal.replace("-", "/");
			DateTimeFormatter format2 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			LocalDate locaDate2 = LocalDate.parse(dataVal, format2);
			txtDataFab.setValue(locaDate2);
						
		}
		
	}
	
	//abaixo ira verificar se a string deve ser convertida para double
	public static boolean isDouble(String valor) {
		if (valor == null || valor.trim().isEmpty()) {
		return false;
		}
		try {
		Double.parseDouble(valor);
		return true;
		} catch (NumberFormatException e) {
		return false;
		}
		}

}
