package Primitiva;

import java.awt.Point;

public class Jogada{
	public final String IDENTIFICACAO_DO_JOGADOR;
	public final Point POSICAO;
	public final Peca PECA;
	
	public Jogada(String _identificadorJogador, Point _posicao, Peca _peca){
		IDENTIFICACAO_DO_JOGADOR = _identificadorJogador;
		POSICAO = _posicao;
		PECA = _peca;
	}
	
	//ACESSO
	
	public String[] toTable(){
		return new String[]{ 
				POSICAO.x + "," + POSICAO.y,
				PECA.ALINHAMENTO.toString() + " (" + PECA.ALINHAMENTO.APRESENTACAO + ")" 
		};
	}
}
