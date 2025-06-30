package model;

public class Produto {

	private String idProduto;
	private String nomeProduto;
	private String codBarra;
	private String tipoUnit;
	private String precoUnit;
	private String estoque;
	private String dataFab;
	private String dataVal;
	
	public Produto(String idProduto, String nomeProduto, String codBarra, String tipoUnit, String precoUnit,
			String estoque, String dataFab, String dataVal) {
		super();
		this.idProduto = idProduto;
		this.nomeProduto = nomeProduto;
		this.codBarra = codBarra;
		this.tipoUnit = tipoUnit;
		this.precoUnit = precoUnit;
		this.estoque = estoque;
		this.dataFab = dataFab;
		this.dataVal = dataVal;
	}

	public Produto() {
		super();
	}
	//
	
	public String getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(String idProduto) {
		this.idProduto = idProduto;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public String getCodBarra() {
		return codBarra;
	}

	public void setCodBarra(String codBarra) {
		this.codBarra = codBarra;
	}

	public String getTipoUnit() {
		return tipoUnit;
	}

	public void setTipoUnit(String tipoUnit) {
		this.tipoUnit = tipoUnit;
	}

	public String getPrecoUnit() {
		return precoUnit;
	}

	public void setPrecoUnit(String precoUnit) {
		this.precoUnit = precoUnit;
	}

	public String getEstoque() {
		return estoque;
	}

	public void setEstoque(String estoque) {
		this.estoque = estoque;
	}

	public String getDataFab() {
		return dataFab;
	}

	public void setDataFab(String dataFab) {
		this.dataFab = dataFab;
	}

	public String getDataVal() {
		return dataVal;
	}

	public void setDataVal(String dataVal) {
		this.dataVal = dataVal;
	}
	
	
	
	

	
}
