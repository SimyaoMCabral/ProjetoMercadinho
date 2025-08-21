package model;

public class VendaProduto {
	
	private String idVendaProduto;
	private String idVenda;
	private String idProduto;
	private String precoUnit;
	private String quantindade;
	private String precoTotal;
	private String tipoUn;
	
	
	
public String getTipoUn() {
		return tipoUn;
	}
	public void setTipoUn(String tipoUn) {
		this.tipoUn = tipoUn;
	}
	//	
	public VendaProduto(String idVendaProduto, String idVenda, String idProduto, String precoUnit, String quantindade,
			String precoTotal) {
		super();
		this.idVendaProduto = idVendaProduto;
		this.idVenda = idVenda;
		this.idProduto = idProduto;
		this.precoUnit = precoUnit;
		this.quantindade = quantindade;
		this.precoTotal = precoTotal;
	}
//
	public VendaProduto() {
		super();
	}
//
	public String getIdVendaProduto() {
		return idVendaProduto;
	}

	public void setIdVendaProduto(String idVendaProduto) {
		this.idVendaProduto = idVendaProduto;
	}

	public String getIdVenda() {
		return idVenda;
	}

	public void setIdVenda(String idVenda) {
		this.idVenda = idVenda;
	}

	public String getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(String idProduto) {
		this.idProduto = idProduto;
	}

	public String getPrecoUnit() {
		return precoUnit;
	}

	public void setPrecoUnit(String precoUnit) {
		this.precoUnit = precoUnit;
	}

	public String getQuantindade() {
		return quantindade;
	}

	public void setQuantindade(String quantindade) {
		this.quantindade = quantindade;
	}

	public String getPrecoTotal() {
		return precoTotal;
	}

	public void setPrecoTotal(String precoTotal) {
		this.precoTotal = precoTotal;
	}
	
	

}
