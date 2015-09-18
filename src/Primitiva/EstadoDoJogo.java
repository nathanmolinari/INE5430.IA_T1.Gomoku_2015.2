package Primitiva;

import Modelo.Tabuleiro;

public class EstadoDoJogo {
	public final Tabuleiro TABULEIRO;
	
	public EstadoDoJogo(Tabuleiro _tabuleiro){
		TABULEIRO = _tabuleiro;
	}
	
	private EstadoDoJogo(EstadoDoJogo estadoDoJogo){
		TABULEIRO = estadoDoJogo.TABULEIRO.clone();
	}
	
	//OUTROS
	
	public EstadoDoJogo clone(){
		return new EstadoDoJogo(this);
	}
}
