package Modelo;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

import Enumeracao.AlinhamentoEnum;
import Enumeracao.DirecaoEnum;
import Enumeracao.SentidoEnum;
import Excecao.PosicaoOcupadaException;
import Primitiva.EstadoDoJogo;
import Primitiva.Jogada;
import Primitiva.NodoMiniMax;
import Primitiva.Peca;


public class JogadorArtificial extends JogadorAbstrato{
	public static int DIFICULDADE = 5;
	
	public JogadorArtificial(String _identificador, AlinhamentoEnum _alinhamento) {
		super(_identificador, _alinhamento);
	}

	//FUNCOES
	
	public Jogada definirJogada(EstadoDoJogo estadoDoJogo) {
		NodoMiniMax melhorAlternativa = refletirSobreAsPossibilidades(true, new NodoMiniMax(null, estadoDoJogo), DIFICULDADE);
		
		if(melhorAlternativa.melhorJogada != null){
			new Jogada(IDENTIFICACAO, melhorAlternativa.melhorJogada, gerarPeca(true));
		}
		return gerarJogadaExemplo();
	}
	
	private NodoMiniMax refletirSobreAsPossibilidades(boolean meuTurno, NodoMiniMax possibilidadeAtual, int intensidade){				
		if(intensidade > 0){
			HashSet<SequenciaDePecas> conjuntoDeSequenciasDoJogo = analizarSequenciasDoJogo(possibilidadeAtual.ESTADO_DO_JOGO);
			HashMap<Integer, HashSet<Point>> rankDeImpacto = calcularRankDeImpacto(conjuntoDeSequenciasDoJogo);
			
			int grauDeImpacto = 0;
			for(Integer _grauDeImpacto : rankDeImpacto.keySet()){
				grauDeImpacto = (_grauDeImpacto > grauDeImpacto ? _grauDeImpacto : grauDeImpacto);
			}
			for(; grauDeImpacto >= 0; grauDeImpacto--){
				for(Point posicao : rankDeImpacto.get(grauDeImpacto)){
					EstadoDoJogo estadoPossivel = possibilidadeAtual.ESTADO_DO_JOGO.clone();
					NodoMiniMax possibilidade = new NodoMiniMax(posicao, estadoPossivel, possibilidadeAtual.podaAlfaBeta);
					
					try{
						estadoPossivel.TABULEIRO.adicionar(posicao, gerarPeca(meuTurno));
						possibilidade = refletirSobreAsPossibilidades(!meuTurno, possibilidade, intensidade-1);
					}
					catch(PosicaoOcupadaException | IndexOutOfBoundsException e){	}
					
					if(possibilidade.valorMiniMax < possibilidadeAtual.podaAlfaBeta[0] &&
					possibilidade.valorMiniMax > possibilidadeAtual.podaAlfaBeta[1]){
						if((meuTurno && possibilidade.valorMiniMax > possibilidadeAtual.valorMiniMax) ||
						!meuTurno && possibilidade.valorMiniMax < possibilidadeAtual.valorMiniMax){
							possibilidadeAtual.valorMiniMax = possibilidade.valorMiniMax;
							possibilidadeAtual.podaAlfaBeta[meuTurno ? 1 : 0] = possibilidade.valorMiniMax;
							possibilidadeAtual.melhorJogada = possibilidade.ULTIMA_POSICAO_JOGADA;							
							
							if(possibilidadeAtual.podaAlfaBeta[0] <= possibilidadeAtual.podaAlfaBeta[1]){
								break;
							}
						}
					}	
				}
				if(possibilidadeAtual.podaAlfaBeta[0] <= possibilidadeAtual.podaAlfaBeta[1]){
					break;
				}
			}
		}
		else {
			possibilidadeAtual.valorMiniMax = qualificarEstadoDoJogo(possibilidadeAtual.ESTADO_DO_JOGO);
		}
		return possibilidadeAtual;
	}

	private int qualificarEstadoDoJogo(EstadoDoJogo estadoDoJogo) {
		int qualidadeDoJogo = 0;
		HashSet<SequenciaDePecas> conjuntoDeSequenciasDoJogo = analizarSequenciasDoJogo(estadoDoJogo);
		
		for(SequenciaDePecas sequenciaDePecas : conjuntoDeSequenciasDoJogo){
			int valorDaSequencia = (int) Math.pow(sequenciaDePecas.TAMANHO, sequenciaDePecas.TAMANHO);
			int expansibilidadeDaSequencia = (0 + (sequenciaDePecas.EXPANSIVEL[0] ? 1 : 0) + (sequenciaDePecas.EXPANSIVEL[1] ? 1 : 0));
			
			int relevanciaDaSequencia = valorDaSequencia * expansibilidadeDaSequencia * (sequenciaDePecas.ALINHAMENTO == ALINHAMENTO ? 1 : -1);
			qualidadeDoJogo += relevanciaDaSequencia;
		}
		
		return qualidadeDoJogo;
	}

	private HashMap<Integer, HashSet<Point>> calcularRankDeImpacto(HashSet<SequenciaDePecas> conjuntoDeSequencias){
		HashMap<Integer, HashSet<Point>> rankDeImpacto = new HashMap<Integer, HashSet<Point>>();
		rankDeImpacto.put(0, new HashSet<Point>());
		
		for(SequenciaDePecas sequenciaDePecas : conjuntoDeSequencias){
			for(Point posicao : sequenciaDePecas.abrangencia()){
				boolean rankeado = false;
				
				for(Integer grauDeImpacto : rankDeImpacto.keySet()){
					if(rankDeImpacto.get(grauDeImpacto).contains(posicao)){
						rankDeImpacto.get(grauDeImpacto).remove(posicao);
						
						if(rankDeImpacto.get(grauDeImpacto+1) != null){
							rankDeImpacto.get(grauDeImpacto+1).add(posicao);
						}
						else{
							HashSet<Point> rank = new HashSet<Point>();
							rank.add(posicao);
							rankDeImpacto.put(grauDeImpacto+1, rank);
						}
						rankeado = true;
						break;
					}
				}
				if(!rankeado){
					rankDeImpacto.get(0).add(posicao);
				}
				
				
			}
		}
		
		return rankDeImpacto;
	}

		private Jogada gerarJogadaExemplo() {
			return new Jogada(
					IDENTIFICACAO,
					new Point((int) (5 + (Math.random() * 10)/2), (int) (5 + (Math.random() * 10)/2)),
					gerarPeca(true)
			);
		}
		
	private HashSet<SequenciaDePecas> analizarSequenciasDoJogo(EstadoDoJogo estadoDoJogo){
		HashSet<SequenciaDePecas> conjuntoDeSequenciaDePecas = new HashSet<SequenciaDePecas>();
		
		for(Entry<Peca, Point> entryAtual : estadoDoJogo.TABULEIRO.CONFIGURACAO_DAS_PECAS.entrySet()){
			for(SentidoEnum sentido : SentidoEnum.values()){	
				SequenciaDePecas sequenciaDePecas = verificarSequenciaDePecas(estadoDoJogo.TABULEIRO.ESTRUTURA, entryAtual.getKey(), entryAtual.getValue(), sentido);
				if(sequenciaDePecas != null){
					conjuntoDeSequenciaDePecas.add(sequenciaDePecas);
				}
			}
		}
		
		return conjuntoDeSequenciaDePecas;
	}

	private SequenciaDePecas verificarSequenciaDePecas(HashMap<Integer, Peca> estruturaDoTabuleiro, Peca peca, Point posicao, SentidoEnum sentido){
		Point _raiz = posicao;
		DirecaoEnum _direcao = sentido.DIRECOES[0];
		int _tamanho = 0;
		boolean[] _expansivel = {false, false};
		
		int i = 1;
		for(DirecaoEnum direcao : sentido.DIRECOES){
			Point posicaoAtual = posicao;
			while(true){
				posicaoAtual.translate(
					direcao.referenciaCartesiana.x,
					direcao.referenciaCartesiana.y
				);
				if(estruturaDoTabuleiro.containsKey(posicaoAtual.hashCode())){
					if(estruturaDoTabuleiro.get(posicaoAtual.hashCode()).ALINHAMENTO == peca.ALINHAMENTO){
						_raiz = (i == 0) ? posicaoAtual : _raiz;
						_tamanho++;
					}
					else{
						_expansivel[i] = false;
						break;
					}
				}
				else{
					_expansivel[i] = true;
					break;
				}
			}
			i--;
		}
			
		return _tamanho > 1 ? new SequenciaDePecas(_raiz, _direcao, _tamanho, peca.ALINHAMENTO, _expansivel) : null;
	}
}
