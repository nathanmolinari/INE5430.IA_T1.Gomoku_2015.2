package Controle;

import java.awt.Dimension;
import java.util.ArrayList;

import DTO.CadastroDoJogadorDTO;
import DTO.VisualizacaoDoJogoDTO;
import Enumeracao.AlinhamentoEnum;
import Excecao.CamposPreenchidosDeFormaIncorretaException;
import Modelo.Gomoku;
import Modelo.JogadorAbstrato;
import Modelo.JogadorArtificial;
import Modelo.JogadorNatural;
import Modelo.Tabuleiro;
import Visao.GomokuVisao;

public class GomokuControle{
	private final static GomokuVisao VISAO = new GomokuVisao();
	
	public GomokuControle(){
		
	}
	
	//INICIALIZACAO
	
	public Tabuleiro cadastrarTabuleiro(){
		Tabuleiro _tabuleiro;
		
		int tamanhoDoTabuleiro = 0;
		if(Gomoku.LISTA_DE_TAMANHOS_DE_TABUEIRO.length > 1){
			int resposta = VISAO.apresentarDialogoOpitativo("Selecione o tamanho do Tabuleiro", "Inicializacao", Gomoku.LISTA_DE_TAMANHOS_DE_TABUEIRO);
			tamanhoDoTabuleiro = Gomoku.LISTA_DE_TAMANHOS_DE_TABUEIRO[resposta];
		}
		else{	tamanhoDoTabuleiro = Gomoku.LISTA_DE_TAMANHOS_DE_TABUEIRO[0];	}
		
		_tabuleiro = new Tabuleiro(new Dimension(tamanhoDoTabuleiro, tamanhoDoTabuleiro));
		return _tabuleiro;
	}

	public ArrayList<JogadorAbstrato> cadastrarJogadores(){
		ArrayList<JogadorAbstrato> _listaDeJogadores = new ArrayList<JogadorAbstrato>();
		
		int numeroDeJogadores = 0;		
		if(Gomoku.LISTA_DE_NUMERO_DE_JOGADORES.length > 1){
			int resposta = VISAO.apresentarDialogoOpitativo("Selecione o numero de Jogadores", "Inicializacao", Gomoku.LISTA_DE_NUMERO_DE_JOGADORES);
			numeroDeJogadores = Gomoku.LISTA_DE_NUMERO_DE_JOGADORES[resposta];
		}
		else{	numeroDeJogadores = Gomoku.LISTA_DE_NUMERO_DE_JOGADORES[0];	}
		
		AlinhamentoEnum[] listaDeAlinhamentoDisponivel = AlinhamentoEnum.values();
		for (int i = 1; i <= numeroDeJogadores; i++){
			while(true){ 
				try{	
					CadastroDoJogadorDTO cadastroDoJogadorDTO = VISAO.cadastrarJogador(listaDeAlinhamentoDisponivel, ""+i);
					cadastroDoJogadorDTO.validar();
					
					JogadorAbstrato jogador = null;
					switch(cadastroDoJogadorDTO.especie){
						case JogadorNatural:	jogador = new JogadorNatural(cadastroDoJogadorDTO.identificador, cadastroDoJogadorDTO.alinhamento);
												break;
												
						case JogadorArtificial:	jogador = new JogadorArtificial(cadastroDoJogadorDTO.identificador, cadastroDoJogadorDTO.alinhamento);
												break;
					}
					
					//*//
					listaDeAlinhamentoDisponivel = new AlinhamentoEnum[]{ cadastroDoJogadorDTO.alinhamento == AlinhamentoEnum.Branco ? AlinhamentoEnum.Preto : AlinhamentoEnum.Branco };
					
					_listaDeJogadores.add(jogador);
					break;
				}
				catch(CamposPreenchidosDeFormaIncorretaException e){
					VISAO.apresentarMensagemDeErro(e.getMessage(), "Cadastro de Jogador");
				}
			}
		}
		return _listaDeJogadores;
	}

	//FUNCOES
	
	public void atualizarVisualizacaoDoJogo(VisualizacaoDoJogoDTO estadoDoJogoDTO) {
		VISAO.atualizarVisualizacaoDoJogo(estadoDoJogoDTO);
		
	}

}
