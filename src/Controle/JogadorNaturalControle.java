package Controle;

import java.awt.Point;

import DTO.PosicaoDaJogadaDTO;
import Visao.JogadorNaturalVisao;

public class JogadorNaturalControle{	
	private final JogadorNaturalVisao VISAO = new JogadorNaturalVisao();

	//FUNCOES
	
	public Point coletarPosicaoJogada(String IDENTIFICACAO){
		while(true){ 
			try {
				PosicaoDaJogadaDTO posicaoJogadaDTO = VISAO.coletarPosicaoJogada(IDENTIFICACAO);
				return new Point(posicaoJogadaDTO.coordenadaX, posicaoJogadaDTO.coordenadaY);
			}
			catch( NumberFormatException e ){
				VISAO.apresentarMensagemDeErro("Formato de numero invalido", "Jogada de " + IDENTIFICACAO);
			}
		}
	}
}
