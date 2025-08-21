package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Main;
import dao.RelatorioVendaDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import model.RelatorioVenda;

public class controllerRelatorioVendas implements Initializable{

    @FXML
    private Button btCliente;

    @FXML
    private Button btFuncionario;

    @FXML
    private Button btHome;

    @FXML
    private Button btPesquisa;

    @FXML
    private Button btProduto;

    @FXML
    private Button btRegistrarVenda;

    @FXML
    private Button btSair;

    @FXML
    private Button btVenda;

    @FXML
    private TableColumn<RelatorioVenda, String> columnDataVend;

    @FXML
    private TableColumn<RelatorioVenda, String> columnIdVenda;

    @FXML
    private TableColumn<RelatorioVenda, String> columnNomeCli;

    @FXML
    private TableColumn<RelatorioVenda, String> columnNomeFun;

    @FXML
    private TableColumn<RelatorioVenda, String> columnNomeProd;

    @FXML
    private TableColumn<RelatorioVenda, String> columnPrecUn;

    @FXML
    private TableColumn<RelatorioVenda, String> columnPrecoTotal;

    @FXML
    private TableColumn<RelatorioVenda, String> columnQuant;

    @FXML
    private TableView<RelatorioVenda> tableVendas;

    @FXML
    private TextField txtPesquisar;

    @FXML
    private Text txtUser;

    @FXML
    void actionCliente(ActionEvent event) throws IOException {

    	Main.TelaCliente();
    	
    }

    @FXML
    void actionFuncionario(ActionEvent event) {
    	
    	//fica vazio
    }

    @FXML
    void actionHome(ActionEvent event) throws IOException {
    	Main.TelaHome();

    }

    @FXML
    void actionPesquisa(ActionEvent event) {
    	if(txtPesquisar.getText().equals("")) {
    		carregarTableRelatorioVendas();
    	}else {
    		pesquisarTableRelatorioVendas();
    	}
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
    void actionVenda(ActionEvent event) {
    	//fica vazio

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		carregarTableRelatorioVendas();
		txtUser.setText(controllerLogin.funcionario.getNomeFuncionario());
	}

	private ObservableList<RelatorioVenda>listaVendas;
	public void carregarTableRelatorioVendas() {
		RelatorioVendaDAO relatorioVendaDAO = new RelatorioVendaDAO();
		listaVendas = FXCollections.observableArrayList(relatorioVendaDAO.read());
		
		columnIdVenda.setCellValueFactory(new PropertyValueFactory<>("idVenda"));
		columnNomeCli.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
		columnNomeFun.setCellValueFactory(new PropertyValueFactory<>("idFuncionario"));
		columnNomeProd.setCellValueFactory(new PropertyValueFactory<>("idProduto"));
		columnPrecUn.setCellValueFactory(new PropertyValueFactory<>("precoUn"));
		columnQuant.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
		columnPrecoTotal.setCellValueFactory(new PropertyValueFactory<>("precoTotal"));
		columnDataVend.setCellValueFactory(new PropertyValueFactory<>("dataVenda"));
		
		tableVendas.setItems(listaVendas);
	}
	
	public void pesquisarTableRelatorioVendas() {
		RelatorioVendaDAO relatorioVendaDAO = new RelatorioVendaDAO();
		listaVendas = FXCollections.observableArrayList(relatorioVendaDAO.serach(txtPesquisar.getText()));
		
		columnIdVenda.setCellValueFactory(new PropertyValueFactory<>("idVenda"));
		columnNomeCli.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
		columnNomeFun.setCellValueFactory(new PropertyValueFactory<>("idFuncionario"));
		columnNomeProd.setCellValueFactory(new PropertyValueFactory<>("idProduto"));
		columnPrecUn.setCellValueFactory(new PropertyValueFactory<>("precoUn"));
		columnQuant.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
		columnPrecoTotal.setCellValueFactory(new PropertyValueFactory<>("precoTotal"));
		columnDataVend.setCellValueFactory(new PropertyValueFactory<>("dataVenda"));
		
		tableVendas.setItems(listaVendas);
	}
}
