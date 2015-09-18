package DTO;

public class PosicaoDaJogadaDTO {
	public int coordenadaX;
	public int coordenadaY;
	
	public PosicaoDaJogadaDTO(String x, String y){
		coordenadaX = Integer.parseInt(x);
		coordenadaY = Integer.parseInt(y);
	}
}
