package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import dao.ClienteDAO;
import dao.FuncionarioDAO;
import dao.ProdutoDAO;
import dao.VendaDAO;
import dao.VendaProdutoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Cliente;
import model.Funcionario;
import model.Produto;
import model.Venda;
import model.VendaProduto;

public class controllerRegistrarVenda implements Initializable{

	@FXML
	private Button btAdicionar;

	@FXML
	private Button btCancelar;

	@FXML
	private Button btRegistrar;

	@FXML
	private TableColumn<VendaProduto, String> columnIdRegVenda;

	@FXML
	private TableColumn<VendaProduto, String> columnNomeProduto;

	@FXML
	private TableColumn<VendaProduto, String> columnPrecoTotal;

	@FXML
	private TableColumn<VendaProduto, String> columnPrecoUn;

	@FXML
	private TableColumn<VendaProduto, String> columnQuantidade;

	@FXML
	private TableColumn<VendaProduto, String> columnTipoUn;

	@FXML
	private TableView<VendaProduto> tableRegistrarVenda;

	@FXML
	private TextField txtCpf;

	@FXML
	private TextField txtDesconto;

	@FXML
	private ChoiceBox<String> txtFormaPGTO;

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtPrecoUn;

	@FXML
	private TextField txtProduto;

	@FXML
	private TextField txtQTD;

	@FXML
	private ChoiceBox<String> txtTipoUn;

	@FXML
	private TextField txtTotalCompra;

	@FXML
	private TextField txtVendedor;

	@FXML
	void actionAdicionar(ActionEvent event) {
		//Se tiverem avisios retornara erro
		if(txtPrecoUn.getText().isEmpty() || txtNome.getText().isEmpty() || 
				txtCpf.getText().isEmpty() || txtQTD.getText().isEmpty()) {
			Alert aviso = new Alert(AlertType.ERROR);
			aviso.setTitle("Erro!");
			aviso.setContentText("Preencha os campos corretamente antes de adicionar!");
			aviso.show();
		}else {
			VendaProduto vendaProduto = new VendaProduto();
			vendaProduto.setIdProduto(txtProduto.getText());
			vendaProduto.setPrecoUnit(txtPrecoUn.getText());
			vendaProduto.setTipoUn(txtTipoUn.getValue());
			vendaProduto.setQuantindade(txtQTD.getText());
			
			double desconto = Double.parseDouble(txtDesconto.getText().replace(",", "."));
			double precoUni = Double.parseDouble(txtPrecoUn.getText().replace(",", "."));
			double quantidade = Double.parseDouble(txtQTD.getText());//conversão para colocar o valor total dentro do campo
			
			double precoTotal = precoUni * quantidade - desconto;
			vendaProduto.setPrecoTotal("R$ "+ String.format("%.2f", precoTotal));
			Double totalCompra = Double.parseDouble(txtTotalCompra.getText().replace(",", "."));
			totalCompra = totalCompra + precoTotal;
			txtTotalCompra.setText("" + String.format("%.2f", totalCompra));
			
			arrayProdutos.add(vendaProduto); //o add adiciona o objeto cada vez que chama ele
			carregarTableProdutos(arrayProdutos);
			//limpa os campos após alteração
			txtProduto.setText("");
			txtPrecoUn.setText("");
			txtDesconto.setText("0,00");
			txtQTD.setText("");
			txtTipoUn.setValue("");
	}
	}

	@FXML
	void actionCancelar(ActionEvent event) {

		Stage stage = (Stage) btCancelar.getScene().getWindow();
		stage.close();

	}

	//Agora irá colocar uma lista suspensa em alguns campos, vai ter soma dos produtos e nome do vendendor
	//baixar uma biblioteca externa para tela suspensa
	@FXML
	void actionRegistrar(ActionEvent event) {
		//registrar venda apenas com id
		Venda venda = new Venda();
		
		Funcionario func = new Funcionario();
		FuncionarioDAO funcDAO = new FuncionarioDAO();
		Cliente clin = new Cliente();
		ClienteDAO clinDAO = new ClienteDAO();
		
		clin.setCpfCliente(txtCpf.getText());
		func.setCpfFuncionario(controllerLogin.funcionario.getCpfFuncionario());
		
		//retorno
		clin = clinDAO.search(clin.getCpfCliente()).get(0);
		func = funcDAO.search(func.getCpfFuncionario()).get(0);
		
		venda.setIdFuncionario(func.getIdFuncionario());//estava CpfFuncionario, está errado, Erro: "A conversão do valor nvarchar '45678901234' estourou uma coluna de inteiros."
		venda.setIdCliente(clin.getIdCliente());
		venda.setValorTotal(txtTotalCompra.getText().replace(",", "."));// tem que ser o formato que o banco de dados aceita
		venda.setQuantTotal("" + arrayProdutos.size());
		
		VendaDAO vendaDAO = new VendaDAO();
		vendaDAO.create(venda);//precisa capturar o id da ultima venda que foi feita e relaiconar com os produtos
		//tem que relacionar a venda com todos produtos vendidos
		
		venda = vendaDAO.readLastId().get(0);
		
		for(int i = 0; i< arrayProdutos.size(); i++) {
			VendaProduto vendaProduto = new VendaProduto();
			VendaProdutoDAO vendaProdDAO = new VendaProdutoDAO();
			
			vendaProduto = arrayProdutos.get(i);
			vendaProduto.setIdVenda(venda.getIdVenda());
			vendaProduto.setPrecoTotal(vendaProduto.getPrecoTotal().trim().replace("R$", ""));//vai tirar o R$ e colocar "nada", que é o aceitado no banco de dados.
			//pegar o IdProduto que adicinou na tela
			Produto prod = new Produto();
			ProdutoDAO prodDAO = new ProdutoDAO();
			prod.setNomeProduto(vendaProduto.getIdProduto());
			prod = prodDAO.search(prod.getNomeProduto()).get(0);
			vendaProduto.setIdProduto(prod.getIdProduto());
			
			vendaProdDAO.create(vendaProduto);
			
			
		}
		
		Alert aviso = new Alert(AlertType.INFORMATION);
		aviso.setTitle("Venda registrada");
		aviso.setContentText("A venda foi registrada com sucesso");
		aviso.show();
		arrayProdutos.clear();
		carregarTableProdutos(arrayProdutos);
		txtProduto.setText("");
		txtPrecoUn.setText("");
		txtDesconto.setText("0,00");
		txtQTD.setText("");
		txtTipoUn.setValue("");
		txtNome.setText("");
		txtCpf.setText("");
		txtTotalCompra.setText("0,00");
		
		

	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		txtTipoUn.getItems().addAll("UN","KG","LT","gm");
		txtFormaPGTO.getItems().addAll("Pix","Cartão","Dinheiro");
		txtVendedor.setText(controllerLogin.funcionario.getNomeFuncionario());//capturar o nome do vendendor
		txtVendedor.setEditable(false);//para não alterar o nome do vendedor
		txtPrecoUn.setEditable(false);//Para preço unitario não ser editavel, pois ele irá calcular de acordo com o tipo UN
		txtTotalCompra.setText("0,00");
		txtTotalCompra.setEditable(false);//para não poder editrar preço total
		//quer apenas algumas informações
		//Argumento Para aparecer listaSuspensa*
		//abaixo serve para aparecer a lista de clientes
		ClienteDAO clienteDAO = new ClienteDAO();
		String[] listaClientes = new String[clienteDAO.read().size()];
		ArrayList<Cliente> arrayClientes = clienteDAO.read();
		for(int i = 0; i < clienteDAO.read().size(); i++) {
			Cliente cliente = new Cliente();
			cliente = arrayClientes.get(i);
			listaClientes[i] = cliente.getNomeCliente();
		}
		
		//Resolver um problema de complentar o cpf
		TextFields.bindAutoCompletion(txtNome,listaClientes).setOnAutoCompleted(event -> actionCPFClick(null));
		
		//Argumento Para aparecer listaSuspensa*
		//tem que fazer para produto
		ProdutoDAO produtoDAO = new ProdutoDAO();
		String[] listaProdutos = new String[produtoDAO.read().size()];
		ArrayList<Produto> arrayProdutos = produtoDAO.read();
		for(int i = 0; i < produtoDAO.read().size(); i++) {
			Produto produto = new Produto();
			produto = arrayProdutos.get(i);
			listaProdutos[i] = produto.getNomeProduto();
		}
		//Resolver um problema de complentar 
		TextFields.bindAutoCompletion(txtProduto,listaProdutos).setOnAutoCompleted(event -> actionProdutoClick(null));
	}
	
	//Métodos para teclado, para quando incluir o nome do cliente aparecer o cpf
	@FXML
    void actionCPFClick(MouseEvent event) {
		//vai vereficar se digitei texto maior que 3 digitos e trazer o cpf
		if(txtNome.getText().length() > 3) {
            ClienteDAO clienteDAO = new ClienteDAO();
            Cliente cliente = new Cliente();
            cliente.setNomeCliente(txtNome.getText());
            ArrayList<Cliente> clientes = new ArrayList<>();        
            clientes = clienteDAO.search(cliente.getNomeCliente());
            cliente = clientes.get(0);
            txtCpf.setText(cliente.getCpfCliente());
        }else {
        	 txtCpf.setText("");
        }
    }

    @FXML
    void actionCPFType(KeyEvent event) {
    	
    	if(txtNome.getText().length() > 3) {
            ClienteDAO clienteDAO = new ClienteDAO();
            Cliente cliente = new Cliente();
            cliente.setNomeCliente(txtNome.getText());
            ArrayList<Cliente> clientes = new ArrayList<>();        
            clientes = clienteDAO.search(cliente.getNomeCliente());
            cliente = clientes.get(0);
            txtCpf.setText(cliente.getCpfCliente());                        
        }else {
        	 txtCpf.setText("");
        }
    }

    //Para completar as informações sobre o produto em outros campos
    @FXML
    void actionProdutoClick(MouseEvent event) {
    	//para mouse
    	if(txtProduto.getText().length()>3) {
    		ProdutoDAO produtoDAO = new ProdutoDAO();
    		Produto produto = new Produto();
    		produto.setNomeProduto(txtProduto.getText());
    		ArrayList<Produto> produtos = new ArrayList<>();
    		produtos = produtoDAO.search(produto.getNomeProduto());
    		produto = produtos.get(0);
    		if(produto != null) {
    			txtTipoUn.setValue(produto.getTipoUnit());
    			//tira as cadas decimais após a virgula
    			double precoUn = Double.parseDouble(produto.getPrecoUnit());
    			txtPrecoUn.setText(String.format("%.2f", precoUn));
    		}
    	}
    }

    @FXML
    void actionProdutoType(KeyEvent event) {
    	if(txtProduto.getText().length()>3) {
    		ProdutoDAO produtoDAO = new ProdutoDAO();
    		Produto produto = new Produto();
    		produto.setNomeProduto(txtProduto.getText());
    		ArrayList<Produto> produtos = new ArrayList<>();
    		produtos = produtoDAO.search(produto.getNomeProduto());
    		produto = produtos.get(0);
    		if(produto != null) {
    			txtTipoUn.setValue(produto.getTipoUnit());
    			
    			double precoUn = Double.parseDouble(produto.getPrecoUnit());
    			txtPrecoUn.setText(String.format("%.2f", precoUn));
    		}
    	}
    }
	//Para quando digitar a quantidade calcular
    @FXML
    void actionQuantidade(KeyEvent event) {
    	// A negação "!" e se tiver algo dentro
    	if(!txtQTD.getText().isEmpty()) {
    		double quantidade = Double.parseDouble(txtQTD.getText());
    		double precoUni = Double.parseDouble(txtPrecoUn.getText().replace(",", "."));//converter
    		if(quantidade >=15) {
    			double desconto = (precoUni * quantidade)*0.10;
    			txtDesconto.setText(""+String.format("%.2f",desconto));//dar desconto quando tiver mais de 15 itens
    		}else if(quantidade < 15) {
    			txtDesconto.setText("0.00");
    		}
    	}
    }
    private ArrayList<VendaProduto> arrayProdutos = new ArrayList<>();//antes de utilizar carregarTableProdutos tem carregar essa linha.
    private void carregarTableProdutos(ArrayList<VendaProduto>ArrayProdutos) {
    	//ArrayProdutos são do produtos
    	ObservableList<VendaProduto> produtosVendidos = FXCollections.observableArrayList(ArrayProdutos);
    	columnIdRegVenda.setCellValueFactory(new PropertyValueFactory<>("idVendaProduto"));
    	columnNomeProduto.setCellValueFactory(new PropertyValueFactory<>("idProduto"));
    	columnQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantindade"));
    	columnPrecoUn.setCellValueFactory(new PropertyValueFactory<>("precoUnit"));
    	columnPrecoTotal.setCellValueFactory(new PropertyValueFactory<>("precoTotal"));
    	columnTipoUn.setCellValueFactory(new PropertyValueFactory<>("precoUnit"));
    	
    	tableRegistrarVenda.setItems(produtosVendidos);
    			
    }

}

