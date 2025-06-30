package model;

public class Venda {

	private String idVenda;
	private String idCliente;
	private String idFuncionario;
	private String valorTotal;
	private String quantTotal;
	private String dataVenda;
	
	public Venda(String idVenda, String idCliente, String idFuncionario, String valorTotal, String quantTotal,
			String dataVenda) {
		super();
		this.idVenda = idVenda;
		this.idCliente = idCliente;
		this.idFuncionario = idFuncionario;
		this.valorTotal = valorTotal;
		this.quantTotal = quantTotal;
		this.dataVenda = dataVenda;
	}

	public Venda() {
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

	public String getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(String idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public String getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(String valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getQuantTotal() {
		return quantTotal;
	}

	public void setQuantTotal(String quantTotal) {
		this.quantTotal = quantTotal;
	}

	public String getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(String dataVenda) {
		this.dataVenda = dataVenda;
	}
	
	
	
}
