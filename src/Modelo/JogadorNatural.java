package Modelo;

import Primitiva.EstadoDoJogo;
import Primitiva.Jogada;
import Controle.JogadorNaturalControle;
import Enumeracao.AlinhamentoEnum;

public class JogadorNatural extends JogadorAbstrato{
	private final JogadorNaturalControle CONTROLE = new JogadorNaturalControle(); 
	
	public JogadorNatural(String _identificacao, AlinhamentoEnum _alinhamento){
		super(_identificacao, _alinhamento);
	}
	
	//FUNCOES
	
	public Jogada definirJogada(EstadoDoJogo estadoDoJogoDTO){
		return new Jogada(
				IDENTIFICACAO,
				CONTROLE.coletarPosicaoJogada(IDENTIFICACAO),
				gerarPeca(true)
		);
	}
	
}
