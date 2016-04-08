package Modelo;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

import Controle.GomokuControle;
import DTO.VisualizacaoDoJogoDTO;
import Enumeracao.AlinhamentoEnum;
import Enumeracao.DirecaoEnum;
import Enumeracao.SentidoEnum;
import Excecao.PosicaoOcupadaException;
import Excecao.VitoriaAtingidaException;
import Primitiva.EstadoDoJogo;
import Primitiva.Jogada;

public class Gomoku extends ModeloAbstrato{	
	public final static Integer[] LISTA_DE_NUMERO_DE_JOGADORES = new Integer[]{ 2 };
	public final static Integer[] LISTA_DE_TAMANHOS_DE_TABUEIRO = new Integer[]{ 5, 10, 15 };
	
	private final GomokuControle CONTROLE = new GomokuControle();
	
	public final Tabuleiro TABULEIRO;
	public final ArrayList<JogadorAbstrato> LISTA_DE_JOGADORES;
	public final ArrayList<Jogada> HISTORICO_DE_JOGADAS;
	
	public Gomoku(){
		TABULEIRO = CONTROLE.cadastrarTabuleiro();
		LISTA_DE_JOGADORES = CONTROLE.cadastrarJogadores();
		HISTORICO_DE_JOGADAS = new ArrayList<Jogada>();
	}

	public Gomoku(Tabuleiro _tabuleiro, ArrayList<JogadorAbstrato> _listaDeJogadores){
		TABULEIRO = _tabuleiro;
		LISTA_DE_JOGADORES = _listaDeJogadores;
		HISTORICO_DE_JOGADAS = new ArrayList<Jogada>();
	}
	
	public static Gomoku invocarInstanciaExemplo(){
		Tabuleiro _tabuleiro = new Tabuleiro(new Dimension(15, 15));
		ArrayList<JogadorAbstrato> _listaDeJogadores = new ArrayList<JogadorAbstrato>();
			_listaDeJogadores.add(new JogadorArtificial("Computador", AlinhamentoEnum.Preto));
			_listaDeJogadores.add(new JogadorNatural("Humano", AlinhamentoEnum.Branco));
		
		return new Gomoku(_tabuleiro, _listaDeJogadores);
	}	
	
	//ACESSO

	public JogadorAbstrato getJogadorDaVez(){
		int indiceJogadorDaJogada = HISTORICO_DE_JOGADAS.size() % LISTA_DE_JOGADORES.size();
		return LISTA_DE_JOGADORES.get(indiceJogadorDaJogada);
	}
	
	public String[][] historicoDeJogadasToTable(int numeroDeJogadas){
		String[][] tabela = new String[numeroDeJogadas][2];
		int deltaJogadas = HISTORICO_DE_JOGADAS.size() <= 10 ? HISTORICO_DE_JOGADAS.size() : HISTORICO_DE_JOGADAS.size() - numeroDeJogadas;

		for (int i = 0; i + deltaJogadas < HISTORICO_DE_JOGADAS.size(); i++) {
			Jogada jogada = HISTORICO_DE_JOGADAS.get(i + deltaJogadas);
			tabela[i] = jogada.toTable();
		}
		return tabela;
	}
	
	//INICIALIZACAO
	
	
	public void iniciar(){
		for (JogadorAbstrato jogador : LISTA_DE_JOGADORES){
			jogador.ingressarJogo(this);
		}
		
		atualizarVisualizacaoDoJogo();
	}
	
	//FUNCOES

	public EstadoDoJogo gerarEstadoDoJogo() {
		return new EstadoDoJogo((Tabuleiro) TABULEIRO.clone());
	}
	
	protected void atualizarVisualizacaoDoJogo(){
		CONTROLE.atualizarVisualizacaoDoJogo(gerarVisualizacaoDoJogo());
	}
	
		public VisualizacaoDoJogoDTO gerarVisualizacaoDoJogo(){
			return new VisualizacaoDoJogoDTO(TABULEIRO.toTable(), historicoDeJogadasToTable(10));
		}
	
	public void realizarTurno() throws VitoriaAtingidaException{
		getJogadorDaVez().realizarJogada(gerarEstadoDoJogo());
	}

	protected void contabilizarJogada(Jogada jogada) throws IndexOutOfBoundsException, PosicaoOcupadaException, VitoriaAtingidaException {
		TABULEIRO.adicionar(jogada.POSICAO, jogada.PECA);
		HISTORICO_DE_JOGADAS.add(jogada);
		atualizarVisualizacaoDoJogo();
		
		verificarVitoria();		
	}

	private void verificarVitoria() throws VitoriaAtingidaException{
		Jogada ultimaJogada = HISTORICO_DE_JOGADAS.get(HISTORICO_DE_JOGADAS.size() -1);
		
		for (SentidoEnum sentido : SentidoEnum.values()){
			int sequencia = 0;
			
			for (DirecaoEnum direcao : sentido.DIRECOES) {
				Point posicaoAtual = ultimaJogada.POSICAO;
				while(true){
					posicaoAtual.translate(
						direcao.referenciaCartesiana.x,
						direcao.referenciaCartesiana.y
					);
					
					if(TABULEIRO.getPeca(posicaoAtual) != null &&  TABULEIRO.getPeca(posicaoAtual).ALINHAMENTO == ultimaJogada.PECA.ALINHAMENTO){
						sequencia += 1;
					}
					else{
						break;
					}
				}
			}
			
			if(sequencia >= 5){
				throw new VitoriaAtingidaException(ultimaJogada.IDENTIFICACAO_DO_JOGADOR);
			}
		}
		
	}
}
