package Controle;

import DTO.PosicaoDaJogadaDTO;
import Enumeracao.AlinhamentoEnum;

public class GomokuController {
	
	private int tamanhoTabuleiro;
	/**
	 * Inicializa todos os objetos do jogo
	 */
	public GomokuController(){
		
	}
	
	public int getPiece(PosicaoDaJogadaDTO dto){
		return 0;
	}
	
	public AlinhamentoEnum getPlayer(){
		return AlinhamentoEnum.Branco;
	}
	
	public int getSize(){
		return 0;
	}
	
	public AlinhamentoEnum getWinner(){
		return AlinhamentoEnum.Branco;
	}
	
	public void playPiece(PosicaoDaJogadaDTO dto){
	}
	
	public String printTabuleiro(){
		return "";
	}
	
	public void setTamanhoTabuleiro(int tamanho){
		this.tamanhoTabuleiro = tamanho;
	}

}
