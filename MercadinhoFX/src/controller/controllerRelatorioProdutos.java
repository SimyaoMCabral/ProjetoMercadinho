package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Main;
import dao.ProdutoDAO;
import dao.ProdutoDAO;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import model.Produto;
import model.Produto;

public class controllerRelatorioProdutos implements Initializable{

    @FXML
    private Button btCadastrar;

    @FXML
    private Button btCliente;

    @FXML
    private Button btEditar;

    @FXML
    private Button btExcluir;

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
    private TableColumn<Produto, String> columnCodB;

    @FXML
    private TableColumn<Produto, String> columnDatFab;

    @FXML
    private TableColumn<Produto, String> columnDatVal;

    @FXML
    private TableColumn<Produto, String> columnEstoque;

    @FXML
    private TableColumn<Produto, String> columnIdProd;

    @FXML
    private TableColumn<Produto, String> columnNomeProd;

    @FXML
    private TableColumn<Produto, String> columnPrecUn;

    @FXML
    private TableColumn<Produto, String> columnTipUn;

    @FXML
    private TableView<Produto> tableProdutos;

    @FXML
    private TextField txtPesquisar;

    @FXML
    private Text txtUser;
    
    public static Produto produtoEditar = new Produto();// criada uma variavel

    @FXML
    void actionCadastrar(ActionEvent event) throws IOException {
    	produtoEditar = null;
    	Main.TelaCadastroProd();
    	carregarTableProduto();
    	
    }

    @FXML
    void actionCliente(ActionEvent event) throws IOException {
    	Main.TelaCliente();//Para voltar a tela

    }

    @FXML
    void actionEditar(ActionEvent event) throws IOException{
    		 
    	    	int linha = tableProdutos.getSelectionModel().getSelectedIndex();//qual linha da tabela está clicada
    	    	//verificador se clicou em alguma linha ou não e alerta para selecionar
    	    	if(linha==-1) {
    	    		Alert aviso = new Alert(AlertType.ERROR);
    	    		aviso.setTitle("Erro!");
    	    		aviso.setContentText("Selecione um cliente primeiro para editar!");
    	    		aviso.show();
    	    	}else {
    	    		produtoEditar = tableProdutos.getItems().get(linha);//clienteEditar é para capturar as informações do cliente
    	    		Main.TelaCadastroProd();
    	    	}
    	        carregarTableProduto();
    	    }


    @FXML
    void actionExcluir(ActionEvent event) {
    	
    	//identificar se clicou em algum produto
    	int linha = tableProdutos.getSelectionModel().getSelectedIndex();//qual linha da tabela está clicada
    	//verificador se clicou em alguma linha ou não e alerta para selecionar
    	if(linha==-1) {
    		Alert aviso = new Alert(AlertType.ERROR);
    		aviso.setTitle("Erro ao realizar login");
    		aviso.setContentText("Selecione um Produto primeiro!");
    		aviso.show();
    		
    	}else {
    		//se tiver selecionado, irá copiar a informação dentro da linha e pegar o cpf
    		Produto produto = new Produto();
    		ProdutoDAO ProdDAO = new ProdutoDAO();
    		produto = tableProdutos.getItems().get(linha);
    		//coNFIRMAR SE deseja excluir
    		Alert msg = new Alert(AlertType.CONFIRMATION);
    		msg.setTitle("Excluir Produto");
    		msg.setHeaderText("Excluir");
    		msg.setContentText("Deseja realmente excluir o produto" +produto.getNomeProduto()+"?");
    		
    		//A variavel confirmacao será usada para capturar a escolha e caso OK deleta
    		Optional<ButtonType> confirmacao = msg.showAndWait();//exibir o Alert e esperar para ver das opções vai escolher
    		if(confirmacao.isPresent() && confirmacao.get() == ButtonType.OK) {
    			ProdDAO.delete(produto.getCodBarra());//se for tentar exluir cliente com VENDA REGISTRADA da ERRO
    			Alert aviso = new Alert(AlertType.INFORMATION);
    			aviso.setTitle("Produto apagado!");
    			aviso.setContentText("O Produto foi apagado com sucesso!");
    			aviso.show();
    			carregarTableProduto();
    		}
    	}

    }

    @FXML
    void actionFuncionario(ActionEvent event) {

    }

    @FXML
    void actionHome(ActionEvent event) throws IOException {
    	
    	Main.TelaHome();//Para voltar a tela

    }

    @FXML
    void actionPesquisa(ActionEvent event) {

    	if(txtUser.getText().equals("")) {
    		carregarTableProduto();
    	}else {
    		pesquisarTableProdutos();
    	}
    }

    @FXML
    void actionProduto(ActionEvent event) {

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
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		txtUser.setText(controllerLogin.funcionario.getNomeFuncionario());
		carregarTableProduto();
	}
	private ObservableList<Produto> arrayProdutos;
	public void carregarTableProduto() {
	ProdutoDAO prodDAO = new ProdutoDAO();
	arrayProdutos = FXCollections.observableArrayList(prodDAO.read());
	
	columnIdProd.setCellValueFactory(new PropertyValueFactory<>("idProduto"));
	columnNomeProd.setCellValueFactory(new PropertyValueFactory<>("nomeProduto"));
	columnCodB.setCellValueFactory(new PropertyValueFactory<>("codBarra"));
	columnTipUn.setCellValueFactory(new PropertyValueFactory<>("tipoUnit"));
	columnPrecUn.setCellValueFactory(new PropertyValueFactory<>("precoUnit"));
	columnEstoque.setCellValueFactory(new PropertyValueFactory<>("estoque"));
	columnDatFab.setCellValueFactory(new PropertyValueFactory<>("dataFab"));
	columnDatVal.setCellValueFactory(new PropertyValueFactory<>("dataVal"));
	
	tableProdutos.setItems(arrayProdutos);
	}
	
	public void pesquisarTableProdutos() {
		ProdutoDAO prodDAO = new ProdutoDAO();
		arrayProdutos = FXCollections.observableArrayList(prodDAO.search(txtPesquisar.getText()));
		
		columnIdProd.setCellValueFactory(new PropertyValueFactory<>("idProduto"));
		columnNomeProd.setCellValueFactory(new PropertyValueFactory<>("nomeProduto"));
		columnCodB.setCellValueFactory(new PropertyValueFactory<>("codBarra"));
		columnTipUn.setCellValueFactory(new PropertyValueFactory<>("tipoUn"));
		columnPrecUn.setCellValueFactory(new PropertyValueFactory<>("precoUn"));
		columnEstoque.setCellValueFactory(new PropertyValueFactory<>("estoque"));
		columnDatFab.setCellValueFactory(new PropertyValueFactory<>("dataFab"));
		columnDatVal.setCellValueFactory(new PropertyValueFactory<>("dataVal"));
		
		tableProdutos.setItems(arrayProdutos);
	}
}
