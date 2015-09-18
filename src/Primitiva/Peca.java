package Primitiva;

import Enumeracao.AlinhamentoEnum;

public class Peca{
	public final AlinhamentoEnum ALINHAMENTO;
		
	public Peca(AlinhamentoEnum _alinhamento){
		ALINHAMENTO = _alinhamento;
	}

	//FUNCOES
	
	public String apresentacao() {
		return ALINHAMENTO.APRESENTACAO;
	}
}
