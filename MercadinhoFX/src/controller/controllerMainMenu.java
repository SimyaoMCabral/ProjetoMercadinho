package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Main;
import dao.FuncionarioDAO;
import dao.ProdutoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import model.Produto;

public class controllerMainMenu implements Initializable{//está apontando erro porque não tem espaço 

    @FXML
    private Button btCliente;

    @FXML
    private Button btFuncionario;

    @FXML
    private Button btHome;

    @FXML
    private Button btProduto;

    @FXML
    private Button btRegistrarVenda;

    @FXML
    private Button btSair;

    @FXML
    private Button btVenda;

    @FXML
    private TableColumn<Produto, String> columnCodBar;

    @FXML
    private TableColumn<Produto, String> columnCodBar2;

    @FXML
    private TableColumn<Produto, String> columnDataValid;

    @FXML
    private TableColumn<Produto, String> columnEstoque;

    @FXML
    private TableColumn<Produto, String> columnTipoUnit2;

    @FXML
    private TableColumn<Produto, String> columnIdBaix;

    @FXML
    private TableColumn<Produto, String> columnIdVenc;

    @FXML
    private TableColumn<Produto, String> columnNome;

    @FXML
    private TableColumn<Produto, String> columnNome2;

    @FXML
    private TableColumn<Produto, String> columnTipoUnit;

    @FXML
    private TableView<Produto> tableProdEstBaixo;

    @FXML
    private TableView<Produto> tableProdPV;

    @FXML
    private Text txtTotalVenda;

    @FXML
    private Text txtUser;

    @FXML
    void actionCliente(ActionEvent event) throws IOException {
    	Main.TelaCliente();
    	

    }

    @FXML
    void actionFuncionario(ActionEvent event) {

    }

    @FXML
    void actionHome(ActionEvent event) {

    }

    @FXML
    void actionProduto(ActionEvent event) throws IOException {
    	
    	Main.TelaProduto();

    }

    @FXML
    void actionRegistrarVenda(ActionEvent event) throws IOException {
    	Main.TelaRegistrarVenda();

    }

    @FXML
    void actionSair(ActionEvent event) throws IOException {
    	Alert msg = new Alert(AlertType.CONFIRMATION);
    	msg.setHeaderText("Sair do sistema");
    	msg.setContentText("SAIR");
    	msg.setTitle("Deseja sair do sistema?");
    	
    	Optional<ButtonType> sair = msg.showAndWait();
    	if(sair.isPresent()&& sair.get()==ButtonType.OK) {
    		Main.TelaLogin();
    	}
    	

    }

    @FXML
    void actionVenda(ActionEvent event) throws IOException {
    	Main.TelaRelatorioVenda();

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {// serve para chamar outros métodos
		// TODO Auto-generated method stub
		txtUser.setText(controllerLogin.funcionario.getNomeFuncionario());// Para aparecer o Nome do funcionario no lugar de USer no menu da tela
		carregarTableProdAV();
		carregarTableProdEB();
		
		//fazer um verificador para caso o funcionario não tiver venda aparacer null
		FuncionarioDAO funcDAO = new FuncionarioDAO();
		String totalVendido = funcDAO.readTotalVendido(controllerLogin.funcionario.getCpfFuncionario());
		if(totalVendido == null) {
			txtTotalVenda.setText("R$ 0,00");
		}else {
			double totalVenda = Double.parseDouble(totalVendido);
			txtTotalVenda.setText("R$" +String.format("%.2f", totalVenda));
		}
		
	}
//o método abaixo foi criado para aparecer os dados da do banco de dados na tabela 
	private ObservableList<Produto> arrayProdutoAV;
	public void carregarTableProdAV() {
		ProdutoDAO prodDAO = new ProdutoDAO();
		arrayProdutoAV = FXCollections.observableArrayList(prodDAO.readProdAV());
		
		columnIdVenc.setCellValueFactory(new PropertyValueFactory<>("idProduto"));
		columnNome2.setCellValueFactory(new PropertyValueFactory<>("nomeProduto"));
		columnCodBar2.setCellValueFactory(new PropertyValueFactory<>("codBarra"));
		columnTipoUnit2.setCellValueFactory(new PropertyValueFactory<>("tipoUnit"));// NA tebela estava escrito Estoque, mudei para Tipo Unit (id: columnTipoUnit2)
		columnDataValid.setCellValueFactory(new PropertyValueFactory<>("dataVal"));
		
		tableProdPV.setItems(arrayProdutoAV);
		
		
		
	}
	private ObservableList<Produto> arrayProdutoEB;
	public void carregarTableProdEB() {
		ProdutoDAO prodDAO = new ProdutoDAO();
		arrayProdutoEB = FXCollections.observableArrayList(prodDAO.readProdEB());
		
		columnIdBaix.setCellValueFactory(new PropertyValueFactory<>("idProduto"));
		columnNome.setCellValueFactory(new PropertyValueFactory<>("nomeProduto"));
		columnCodBar.setCellValueFactory(new PropertyValueFactory<>("codBarra"));
		columnTipoUnit.setCellValueFactory(new PropertyValueFactory<>("tipoUnit"));// NA tebela estava escrito Estoque, mudei para Tipo Unit (id: columnTipoUnit2)
		columnEstoque.setCellValueFactory(new PropertyValueFactory<>("dataVal"));
		
		tableProdEstBaixo.setItems(arrayProdutoEB);
		
		
		
	}
	
}
