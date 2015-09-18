package Modelo;

import java.awt.Dimension;
import java.awt.Point;
import java.util.HashMap;

import Excecao.PosicaoOcupadaException;
import Primitiva.Peca;

public class Tabuleiro{
	public final HashMap<Integer, Peca> ESTRUTURA;
	public final HashMap<Peca, Point> CONFIGURACAO_DAS_PECAS;
	public final Dimension DIMENSAO;
	
	public Tabuleiro(Dimension _dimensao){
		ESTRUTURA = new HashMap<Integer, Peca>();
		CONFIGURACAO_DAS_PECAS = new HashMap<Peca, Point>();
		DIMENSAO = _dimensao;
	}
	
	private Tabuleiro(Tabuleiro tabuleiro){
		ESTRUTURA = new HashMap<Integer, Peca>(tabuleiro.ESTRUTURA);
		CONFIGURACAO_DAS_PECAS = new  HashMap<Peca, Point>(tabuleiro.CONFIGURACAO_DAS_PECAS);
		DIMENSAO = new Dimension(tabuleiro.DIMENSAO);
	}
	
	//ACESSO
	
	public Peca getPeca(Point posicao) {
		return ESTRUTURA.get(posicao.hashCode());
	}
	
	public Point getPoint(Peca peca) {
		return CONFIGURACAO_DAS_PECAS.get(peca.hashCode());
	}

	//FUNCOES
	
	public void adicionar(Point posicao, Peca peca) throws PosicaoOcupadaException, IndexOutOfBoundsException{
		validarPontoNaDimensao(posicao);
		validarPosicaoLivre(posicao);
		
		ESTRUTURA.put(posicao.hashCode(), peca);
		CONFIGURACAO_DAS_PECAS.put(peca, posicao);
	}
	
	//VALIDACAO

	private void validarPontoNaDimensao(Point posicao) throws IndexOutOfBoundsException{
		if(posicao.x < 0 || posicao.x > DIMENSAO.width || posicao.y < 0 || posicao.y > DIMENSAO.height){
				throw new IndexOutOfBoundsException("A posicao escolhida esta fora das dimensoes do tabuleiro."
						+ "\n" + "Selecione uma posicao pertencente ao tabuleiro (" + DIMENSAO.width + "x" + DIMENSAO.height + ")." );
		}
	}

	private void validarPosicaoLivre(Point posicao) throws PosicaoOcupadaException {
		if(ESTRUTURA.containsKey(posicao.hashCode())){
			throw new PosicaoOcupadaException();
		}		
	}
	
	//OUTROS

	public String toString(){
		String apresentacao = "";
		
		Peca pecaAtual;
		for (int y = DIMENSAO.height; y >= 0; --y) {				
			for(int x = 0; x < DIMENSAO.width; x++){
				pecaAtual = ESTRUTURA.get(new Point(x, y).hashCode());
				
				if(pecaAtual != null){
					apresentacao += pecaAtual.apresentacao();
				}
				else{
					apresentacao += "_";
				}
				
				apresentacao += " ";
			}
			apresentacao += "\n";
		}
		
		return apresentacao;
	}

	public String[][] toTable(){
		String[][] tabela = new String[DIMENSAO.width][DIMENSAO.height];
		
		for(int x = 0; x < DIMENSAO.width; x++){
			for (int y = 0; y < DIMENSAO.height; y++){	
				int hashcode = new Point(x, y).hashCode();
				
				if(ESTRUTURA.containsKey(hashcode)){
					tabela[x][y] = ESTRUTURA.get(hashcode).apresentacao();
				}
				else{
					tabela[x][y] = " ";
				}	
			}
		}
		return tabela;
	}

	public Tabuleiro clone(){
		return new Tabuleiro(this);
	}


}
