package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import dao.ClienteDAO;
import dao.ProdutoDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Cliente;
import model.Produto;
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

	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		txtTipoUn.getItems().addAll("UN","KG","LT","gm");
		txtFormaPGTO.getItems().addAll("Pix","Cartão","Dinheiro");
		txtVendedor.setText(controllerLogin.funcionario.getNomeFuncionario());//capturar o nome do vendendor
		txtVendedor.setEditable(false);//para não alterar o nome do vendedor
		txtPrecoUn.setEditable(false);//Para preço unitario não ser editavel, pois ele irá calcular de acordo com o tipo UN
		//quer apenas algumas informações
		//abaixo serve para aparecer a lista de clientes
		ClienteDAO clienteDAO = new ClienteDAO();
		String[] listaClientes = new String[clienteDAO.read().size()];
		ArrayList<Cliente> arrayClientes = clienteDAO.read();
		for(int i = 0; i < clienteDAO.read().size(); i++) {
			Cliente cliente = new Cliente();
			cliente = arrayClientes.get(i);
			listaClientes[i] = cliente.getNomeCliente();
		}
		TextFields.bindAutoCompletion(txtNome,listaClientes);

		//tem que fazer para produto
		ProdutoDAO produtoDAO = new ProdutoDAO();
		String[] listaProdutos = new String[produtoDAO.read().size()];
		ArrayList<Produto> arrayProdutos = produtoDAO.read();
		for(int i = 0; i < produtoDAO.read().size(); i++) {
			Produto produto = new Produto();
			produto = arrayProdutos.get(i);
			listaProdutos[i] = produto.getNomeProduto();
		}
		TextFields.bindAutoCompletion(txtProduto,listaProdutos);
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

	

}

