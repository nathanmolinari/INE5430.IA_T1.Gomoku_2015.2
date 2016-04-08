package VisaoExterna;

import Controle.GomokuController;
import DTO.PosicaoDaJogadaDTO;
import Enumeracao.AlinhamentoEnum;

public class GomokuState {
	
	public final static int BLACK = 0;
	public final static int NONE = 1;
	public final static int WHITE = 2;
	private GomokuController controller;
	
	public GomokuState(int size) {
		this.controller = new GomokuController();
		this.controller.setTamanhoTabuleiro(size);
	}
	
	public int getPiece(int row, int column){
		return this.controller.getPiece(new PosicaoDaJogadaDTO(row, column));
	}
	
	public int getPlayer(){
		AlinhamentoEnum alinhamento = this.controller.getPlayer();
		return this.alinhamentoEnumToInt(alinhamento);
	}
	
	public int getSize(){
		return this.controller.getSize();
	}
	
	public int getWinner(){
		AlinhamentoEnum alinhamento = this.controller.getWinner();
		return this.alinhamentoEnumToInt(alinhamento);
	}
	
	public void playPiece(int row, int column){
		this.controller.playPiece(new PosicaoDaJogadaDTO(row, column));
	}
	
	public String toString(){
		return this.controller.printTabuleiro();
	}
	
	private int alinhamentoEnumToInt(AlinhamentoEnum alinhamento){
		switch (alinhamento) {
		case Branco:
			return WHITE;
		case Preto:
			return BLACK;
		default:
			return NONE;
		}
	}
}
