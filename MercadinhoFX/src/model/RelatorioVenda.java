package model;

public class RelatorioVenda {
//foi criada para ser o espelho de uma view (VW_VW_RelatorioVendas), para comportar os atributos que também ira aparecer na tabela
	//Nas outras models não tem essas informações por completo
	//como criamos um novo objeto temos vriar classe DAO
	private String idVenda;
	private String idCliente;
	private String idProduto;
	private String idFuncionario;
	private String precoUn;
	private String quantidade;
	private String precoTotal;
	private String dataVenda;
	
	
	public RelatorioVenda(String idVenda, String idCliente, String idProduto, String idFuncionario, String precoUn,
			String quantidade, String precoTotal, String dataVenda) {
		super();
		this.idVenda = idVenda;
		this.idCliente = idCliente;
		this.idProduto = idProduto;
		this.idFuncionario = idFuncionario;
		this.precoUn = precoUn;
		this.quantidade = quantidade;
		this.precoTotal = precoTotal;
		this.dataVenda = dataVenda;
	}


	public RelatorioVenda() {
		super();
	}


	public String getIdVenda() {
		return idVenda;
	}


	public void setIdVenda(String idVenda) {
		this.idVenda = idVenda;
	}


	public String getIdCliente() {
		return idCliente;
	}


	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}


	public String getIdProduto() {
		return idProduto;
	}


	public void setIdProduto(String idProduto) {
		this.idProduto = idProduto;
	}


	public String getIdFuncionario() {
		return idFuncionario;
	}


	public void setIdFuncionario(String idFuncionario) {
		this.idFuncionario = idFuncionario;
	}


	public String getPrecoUn() {
		return precoUn;
	}


	public void setPrecoUn(String precoUn) {
		this.precoUn = precoUn;
	}


	public String getQuantidade() {
		return quantidade;
	}


	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}


	public String getPrecoTotal() {
		return precoTotal;
	}


	public void setPrecoTotal(String precoTotal) {
		this.precoTotal = precoTotal;
	}


	public String getDataVenda() {
		return dataVenda;
	}


	public void setDataVenda(String dataVenda) {
		this.dataVenda = dataVenda;
	}
	
	
	
}
