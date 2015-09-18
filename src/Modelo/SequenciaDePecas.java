package Modelo;

import java.awt.Point;

import Enumeracao.AlinhamentoEnum;
import Enumeracao.DirecaoEnum;
import Enumeracao.SentidoEnum;

public class SequenciaDePecas {
	public final Point RAIZ;
	public final DirecaoEnum DIRECAO;
	public final int TAMANHO;
	public final boolean[] EXPANSIVEL;
	public final AlinhamentoEnum ALINHAMENTO;

	public SequenciaDePecas(Point _raiz, DirecaoEnum _direcao, int _tamanho, AlinhamentoEnum _alinhamento, boolean[] _expansivel){
		RAIZ = _raiz;
		DIRECAO = _direcao;
		TAMANHO = _tamanho;
		EXPANSIVEL = _expansivel;
		ALINHAMENTO = _alinhamento;
	}
	
	//ACESSO
	
	public boolean contem(Point posicao){
		for(int i = 0; i < TAMANHO; i++){
			if(
				posicao.x == RAIZ.x + (i * DIRECAO.referenciaCartesiana.x) &&
				posicao.y == RAIZ.y + (i * DIRECAO.referenciaCartesiana.y)
			){
				return true;
			}
		}
		return false;
	}
	
	//FUNCOES
	
	public Point[] abrangencia(){
		int constanteAuxiliar = 1;
		Point[] abrangencia = new Point[(EXPANSIVEL[0] ? constanteAuxiliar : 0) + /* TAMANHO + */ (EXPANSIVEL[1] ? constanteAuxiliar : 0)];
		
		int i = 0;
		if(EXPANSIVEL[0]){
			for(int j = constanteAuxiliar; j > 0; j--){
				abrangencia[i++] = new Point(RAIZ.x + (j * DIRECAO.referenciaCartesiana.x), RAIZ.y + (j * DIRECAO.referenciaCartesiana.y));
			}
		}
		
		/*
		for(int j = 0; j < TAMANHO; j++){
			abrangencia[i++] = new Point(RAIZ.x + (j * DIRECAO.referenciaCartesiana.x), RAIZ.y + (j * DIRECAO.referenciaCartesiana.y));
		}
		*/
		
		if(EXPANSIVEL[1]){
			for(int j = 0; j < constanteAuxiliar; j++){
				abrangencia[i++] = new Point(RAIZ.x + ((j+TAMANHO) * DIRECAO.referenciaCartesiana.x), RAIZ.y + ((j+TAMANHO) * DIRECAO.referenciaCartesiana.y));
			}
		}
		
		return abrangencia;
	}
	
	//OUTROS
	
	public boolean equals(Object o){
		SequenciaDePecas outro = (SequenciaDePecas) o;
		
		if(ALINHAMENTO == outro.ALINHAMENTO){
			if(TAMANHO == outro.TAMANHO){
				if((EXPANSIVEL[0] && outro.EXPANSIVEL[0]) || (!EXPANSIVEL[0] && !outro.EXPANSIVEL[0])){
					if((EXPANSIVEL[1] && outro.EXPANSIVEL[1]) || (!EXPANSIVEL[1] && !outro.EXPANSIVEL[1])){
						if(SentidoEnum.ehIgual(new DirecaoEnum[]{ DIRECAO, outro.DIRECAO})){
							if(contem(outro.RAIZ)){
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	public int hashCode(){
		return ALINHAMENTO.hashCode() + TAMANHO;
	}
}
