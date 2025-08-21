package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Main;
import dao.ClienteDAO;
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
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import model.Cliente;

public class controllerRelatorioClientes implements Initializable{

    @FXML
    private Button btCadastrar;

    @FXML
    private Button btCliente;

    @FXML
    private Button btEditar;

    @FXML
    private Button btExcluir;//estava a imagem como bt excluir agora foi corrigido

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
    private TableColumn<Cliente, String> columnCpf;//objeto e parâmetro de objeto. É utilizado strinf porque é o tipo de todas as variasveis da tabela

    @FXML
    private TableColumn<Cliente, String> columnDataN;

    @FXML
    private TableColumn<Cliente, String> columnEmail;

    @FXML
    private TableColumn<Cliente, String> columnEnder;

    @FXML
    private TableColumn<Cliente, String> columnGenero;

    @FXML
    private TableColumn<Cliente, String> columnIdCliet;

    @FXML
    private TableColumn<Cliente, String> columnNomeC;

    @FXML
    private TableColumn<Cliente, String> columnTelef;

    @FXML
    private TableView<Cliente> tableClientes;

    @FXML
    private TextField txtPesquisar;

    @FXML
    private Text txtUser;

    @FXML
    void actionCadastrar(ActionEvent event) throws IOException {
    	
    	Main.TelaCadastroClin(); 
    	carregarTableCliente();

    }

    @FXML
    void actionCliente(ActionEvent event) {

    }

    public static Cliente clienteEditar = new Cliente();
    @FXML
    void actionEditar(ActionEvent event) throws IOException {
    	int linha = tableClientes.getSelectionModel().getSelectedIndex();//qual linha da tabela está clicada
    	//verificador se clicou em alguma linha ou não e alerta para selecionar
    	if(linha==-1) {
    		Alert aviso = new Alert(AlertType.ERROR);
    		aviso.setTitle("Erro!");
    		aviso.setContentText("Selecione um cliente primeiro para editar!");
    		aviso.show();
    	}else {
    		clienteEditar = tableClientes.getItems().get(linha);//clienteEditar é para capturar as informações do cliente
    		Main.TelaCadastroClin();
    	}
        carregarTableCliente();
    }

    @FXML
    void actionExcluir(ActionEvent event) {
    	
    	//identificar se clicou em algum cliente
    	int linha = tableClientes.getSelectionModel().getSelectedIndex();//qual linha da tabela está clicada
    	//verificador se clicou em alguma linha ou não e alerta para selecionar
    	if(linha==-1) {
    		Alert aviso = new Alert(AlertType.ERROR);
    		aviso.setTitle("Erro ao realizar login");
    		aviso.setContentText("Selecione um cliente primeiro!");
    		aviso.show();
    		
    	}else {
    		//se tiver selecionado, irá copiar a informação dentro da linha e pegar o cpf
    		Cliente cliente = new Cliente();
    		ClienteDAO clienteDAO = new ClienteDAO();
    		cliente = tableClientes.getItems().get(linha);
    		//coNFIRMAR SE deseja excluir
    		Alert msg = new Alert(AlertType.CONFIRMATION);
    		msg.setTitle("Excluir Cliente");
    		msg.setHeaderText("Excluir");
    		msg.setContentText("Deseja realmente excluir o cliente" +cliente.getNomeCliente()+"?");
    		
    		//A variavel confirmacao será usada para capturar a escolha e caso OK deleta
    		Optional<ButtonType> confirmacao = msg.showAndWait();//exibir o Alert e esperar para ver das opções vai escolher
    		if(confirmacao.isPresent() && confirmacao.get() == ButtonType.OK) {
    			clienteDAO.delete(cliente.getCpfCliente());//se for tentar exluir cliente com VENDA REGISTRADA da ERRO
    			Alert aviso = new Alert(AlertType.INFORMATION);
    			aviso.setTitle("Cliente apagado!");
    			aviso.setContentText("O cliente foi apagado com sucesso!");
    			aviso.show();
    			carregarTableCliente();
    		}
    	}

    }

    @FXML
    void actionFuncionario(ActionEvent event) {

    }

    @FXML
    void actionHome(ActionEvent event) throws IOException {
    	Main.TelaHome();

    }

    @FXML
    void actionPesquisa(ActionEvent event) {
    	//verificador
    	//se tiver vazio, chama o carregar
    	if(txtPesquisar.getText().isEmpty()) {
    		carregarTableCliente();
    	}else {
    		//se tiver preechido
    		pesquisarTableCliente();
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
    void actionSair(ActionEvent event) throws IOException  {

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
		carregarTableCliente();
		//forçar ser vazio
		clienteEditar = null;
		
	}
	ObservableList<Cliente> arrayClientes;
	public void carregarTableCliente() {
		ClienteDAO clienteDAO = new ClienteDAO();
		arrayClientes = FXCollections.observableArrayList(clienteDAO.read());
		
		columnIdCliet.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
		columnNomeC.setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));
		columnCpf.setCellValueFactory(new PropertyValueFactory<>("cpfCliente"));
		columnDataN.setCellValueFactory(new PropertyValueFactory<>("dataNasc"));
		columnTelef.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		columnEnder.setCellValueFactory(new PropertyValueFactory<>("endereco"));
		columnGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
		tableClientes.setItems(arrayClientes);
		
	}

	
	public void pesquisarTableCliente() {
		ClienteDAO clienteDAO = new ClienteDAO();
		arrayClientes = FXCollections.observableArrayList(clienteDAO.search(txtPesquisar.getText()));
		
		columnIdCliet.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
		columnNomeC.setCellValueFactory(new PropertyValueFactory<>("nomeCliente"));
		columnCpf.setCellValueFactory(new PropertyValueFactory<>("cpfCliente"));
		columnDataN.setCellValueFactory(new PropertyValueFactory<>("dataNasc"));
		columnTelef.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		columnEnder.setCellValueFactory(new PropertyValueFactory<>("endereco"));
		columnGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
		tableClientes.setItems(arrayClientes);
		
	}
}
