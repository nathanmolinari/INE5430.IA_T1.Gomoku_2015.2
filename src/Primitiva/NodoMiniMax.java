package Primitiva;

import java.awt.Point;
import java.util.ArrayList;

public class NodoMiniMax {
	public final ArrayList<NodoMiniMax> REFERENCIAS = new ArrayList<NodoMiniMax>();
	
	public final Point ULTIMA_POSICAO_JOGADA;
	public final EstadoDoJogo ESTADO_DO_JOGO;
	
	public int[] podaAlfaBeta = { Integer.MAX_VALUE, Integer.MIN_VALUE };
	public int valorMiniMax;
	public Point melhorJogada;
	
	public NodoMiniMax(Point _ultimaPosicaoJogada, EstadoDoJogo _estadoDoJogo){
		ULTIMA_POSICAO_JOGADA = _ultimaPosicaoJogada;
		ESTADO_DO_JOGO = _estadoDoJogo;
	}
	
	public NodoMiniMax(Point _ultimaPosicaoJogada, EstadoDoJogo _estadoDoJogo, int[] _podaAlfaBeta){
		ULTIMA_POSICAO_JOGADA = _ultimaPosicaoJogada;
		ESTADO_DO_JOGO = _estadoDoJogo;
		podaAlfaBeta = _podaAlfaBeta;
	}
	
	/*
	public NodoMiniMax(Point _ultimaPosicaoJogada, EstadoDoJogo _estadoDoJogo, int _valorMiniMax){
	 
		ULTIMA_POSICAO_JOGADA = _ultimaPosicaoJogada;
		ESTADO_DO_JOGO = _estadoDoJogo;
		valorMiniMax = _valorMiniMax;
	}
	*/
	
	//FUNCOES
	
	public void adicionarReferencia(NodoMiniMax nodo){
		REFERENCIAS.add(nodo);
	}
	
}
