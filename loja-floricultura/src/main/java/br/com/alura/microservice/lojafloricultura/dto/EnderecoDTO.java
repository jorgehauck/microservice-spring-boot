package br.com.alura.microservice.lojafloricultura.dto;

public class EnderecoDTO {

	private String rua;
	private Integer numero;
	private String estado;
	
	public EnderecoDTO() {}

	public EnderecoDTO(String rua, Integer numero, String estado) {
		this.rua = rua;
		this.numero = numero;
		this.estado = estado;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "EnderecoDTO [rua=" + rua + ", numero=" + numero + ", estado=" + estado + "]";
	}
}
