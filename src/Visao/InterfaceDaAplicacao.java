package Visao;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Excecao.OperacaoCanceladaException;

public class InterfaceDaAplicacao extends VisaoAbstrato{
	private static InterfaceDaAplicacao INSTANCIA;
	
	protected final JFrame FRAME_FAMILIAR;
	
	private InterfaceDaAplicacao(){
		FRAME_FAMILIAR = new JFrame("INE5430 • 1º Trabalho • Gomoku");		
		FRAME_FAMILIAR.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		FRAME_FAMILIAR.setLocation(150, 150);
		FRAME_FAMILIAR.setSize(500, 500);
	}
	
	public static InterfaceDaAplicacao invocarInstancia(){
		if(INSTANCIA == null){
			INSTANCIA = new InterfaceDaAplicacao();
		}	
		return INSTANCIA;
	}

	//FUNCOES
	
	public int iniciar() {
		String mensagemInicializacao = "UNIVERSIDADE FEDERAL DE SANTA CATARINA"
				+ "\n" + "INTELIGÊNCIA ARTIFICIAL"
				+ "\n\n" + "Arthur Henrique Della Fraga \t 11200623";
		
		int retorno = apresentarDialogoOpitativo(mensagemInicializacao, "TRABALHO 1 • METODOS DE BUSCA", new String[]{"Inicializar do zero", "Inicializar Gomoku"});
		
		if(retorno == JOptionPane.CLOSED_OPTION){
			throw new OperacaoCanceladaException();
		}
		
		return retorno;
	}
}
