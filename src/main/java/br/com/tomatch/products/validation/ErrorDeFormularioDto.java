package br.com.tomatch.products.validation;

public class ErrorDeFormularioDto {
	private String campo;
	private String erro;
	
	public String getCampo() {
		return campo;
	}

	public String getErro() {
		return erro;
	}

	public ErrorDeFormularioDto(String campo, String erro) {
		this.campo = campo;
		this.erro = erro;
	}
}
