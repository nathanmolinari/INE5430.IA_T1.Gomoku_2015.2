package Enumeracao;

public enum AlinhamentoEnum{
	Branco("◇"), Preto("◆");
	
	public final String APRESENTACAO;
	
	private AlinhamentoEnum(String _representacao){
		APRESENTACAO = _representacao;
	}
	
	//FUNCOES
		
	public static AlinhamentoEnum alinhamentoOposto(AlinhamentoEnum alinhamento){
		switch(alinhamento){
			case Branco:	return Preto;
			case Preto:		return Branco;
		}
		return null;
	}
}
