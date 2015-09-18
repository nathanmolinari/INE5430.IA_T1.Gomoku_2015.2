package Excecao;

public class VitoriaAtingidaException extends Exception {
	
	public VitoriaAtingidaException(String identificacaoVencedor){
		super("Vitoria atingida por " + identificacaoVencedor);
	}
}
