package Controle;

import Excecao.OperacaoCanceladaException;
import Excecao.VitoriaAtingidaException;
import Modelo.Gomoku;
import Visao.InterfaceDaAplicacao;

public class GerenteDaAplicacao{
	private static GerenteDaAplicacao INSTANCIA;
	private final InterfaceDaAplicacao INTERFACE_DA_APLICACAO = InterfaceDaAplicacao.invocarInstancia();
	
	private Gomoku gomoku;
	
	private GerenteDaAplicacao(){
		
	}
	
	public static GerenteDaAplicacao invocarInstancia(){
		if(INSTANCIA == null){
			INSTANCIA = new GerenteDaAplicacao();
		}
		
		return INSTANCIA;
	}
	
	//FUNCOES

	public void iniciar(){		
		try{
			switch(INTERFACE_DA_APLICACAO.iniciar()){
				case 0:		gomoku = new Gomoku();
							break;
							
				case 1:		gomoku = Gomoku.invocarInstanciaExemplo();		
			}
			
			gomoku.iniciar();
			for(int i = 0; true; i++){
				try{
					gomoku.realizarTurno();
				}
				catch(VitoriaAtingidaException e){
					INTERFACE_DA_APLICACAO.apresentarMensagemDeAlerta(e.getMessage(), "Vitoria");
					break;
				}
			}
		}
		catch(OperacaoCanceladaException e){
			System.exit(0);
		}
	}

	
}
