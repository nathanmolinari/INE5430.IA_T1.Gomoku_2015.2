package Enumeracao;

public enum SentidoEnum {
	Vertical(new DirecaoEnum[]{ DirecaoEnum.Norte, DirecaoEnum.Sul }),
	DiagonalPrincipal(new DirecaoEnum[]{ DirecaoEnum.Nordeste, DirecaoEnum.Sudoeste }),
	Horizontal(new DirecaoEnum[]{ DirecaoEnum.Leste, DirecaoEnum.Oeste }),
	DiagonalSecundaria(new DirecaoEnum[]{ DirecaoEnum.Noroeste, DirecaoEnum.Sudeste});
	
	public final DirecaoEnum[] DIRECOES;
	
	SentidoEnum(DirecaoEnum[] _direcoes){
		DIRECOES = _direcoes;
	}
	
	public static boolean ehIgual(DirecaoEnum[] listaDeDirecoes){
		boolean sentidoEncontrado = false;
		boolean pertenceAoSentido = false;
		
		for(SentidoEnum sentido : SentidoEnum.values()){
			if(!sentidoEncontrado){	
				
				for(DirecaoEnum direcaoAuxiliar : listaDeDirecoes){
					pertenceAoSentido = false;
					for(DirecaoEnum direcaoPrincipal : sentido.DIRECOES){
						pertenceAoSentido = (pertenceAoSentido || direcaoAuxiliar == direcaoPrincipal);
					}
					
					if(pertenceAoSentido){	sentidoEncontrado = true;	}
					else{ break; }
				}
				
			}
		}
		return pertenceAoSentido;
	}
}
