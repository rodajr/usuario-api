package br.com.usuarios.api.exception;

public class NaoEncontradoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public NaoEncontradoException(String excecao) {
		super(excecao);
	}
}
