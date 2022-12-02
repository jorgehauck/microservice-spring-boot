package br.com.alura.microservice.lojafloricultura.dto;

public class InfoFornecedorDTO {

	private String endereco;
	
	public InfoFornecedorDTO() {}

	public InfoFornecedorDTO(String endereco) {
		this.endereco = endereco;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
}
