package DTO;

public class VisualizacaoDoJogoDTO {
	public final String[][] TABULEIRO;
	public final String[][] HISTORICO_DE_JOGADAS;
	
	public VisualizacaoDoJogoDTO(String[][] _tabuleiro, String[][] _historicoDeJogadas) {
		TABULEIRO = _tabuleiro;
		HISTORICO_DE_JOGADAS = _historicoDeJogadas;
	}
	
	//FUNCOES
	
	public String[] getColumnNamesTabuleiro(){
		String[] columnNames = new String[TABULEIRO[0].length];
		for (int i = 0; i < columnNames.length; i++) {
			columnNames[i] = "";
		}
		return columnNames;
	}
	
	public String[] getColumnNamesHistoricoDeJogadas(){
		String[] columnNames = { "Jogador", "jogada" }; 
		return columnNames;
	}
	
	
}
