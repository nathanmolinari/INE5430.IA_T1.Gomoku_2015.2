package Modelo;

import Enumeracao.AlinhamentoEnum;
import Excecao.PosicaoOcupadaException;
import Excecao.VitoriaAtingidaException;
import Primitiva.EstadoDoJogo;
import Primitiva.Jogada;
import Primitiva.Peca;

public abstract class JogadorAbstrato extends ModeloAbstrato{
	public final String IDENTIFICACAO;
	public final AlinhamentoEnum ALINHAMENTO;
	
	protected Gomoku gomoku;
	
	protected JogadorAbstrato(String _identificador, AlinhamentoEnum _alinhamento){
		IDENTIFICACAO = _identificador;
		ALINHAMENTO = _alinhamento;
	}

	//FUNCOES
	
	public void ingressarJogo(Gomoku _gomoku){
		gomoku = _gomoku;
	}
	
	protected abstract Jogada definirJogada(EstadoDoJogo estadoDoJogo);
	
	public void realizarJogada(EstadoDoJogo estadoDoJogo) throws VitoriaAtingidaException{
		while(true){
			try{
				Jogada jogada = definirJogada(estadoDoJogo);
				gomoku.contabilizarJogada(jogada);
				break;
			}
			catch(PosicaoOcupadaException | IndexOutOfBoundsException e){
				INTERFACE_DA_APLICACAO.apresentarMensagemDeErro(e.getMessage(), "Jogada de " + IDENTIFICACAO);
			}
		}
	}
	
	protected Peca gerarPeca(boolean aliadaAdversaria){
		return new Peca(aliadaAdversaria ? ALINHAMENTO : AlinhamentoEnum.alinhamentoOposto(ALINHAMENTO));
	}
}
