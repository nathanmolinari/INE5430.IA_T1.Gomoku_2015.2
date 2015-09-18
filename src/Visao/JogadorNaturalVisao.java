package Visao;

import javax.swing.JTextField;

import DTO.PosicaoDaJogadaDTO;

public class JogadorNaturalVisao extends VisaoAbstrato{

	public PosicaoDaJogadaDTO coletarPosicaoJogada(String identificacao){
		JTextField coordenadaX = new JTextField(2);
		JTextField coordenadaY = new JTextField(2);
		
		Object[] listaDeComponentes = {
				"Entre com as coordenadas da jogada.\n",
				"x:", coordenadaX,
				"y:", coordenadaY
		};
		apresentarDialogoPersonalizado(listaDeComponentes, "Jogada de " + identificacao);
		return new PosicaoDaJogadaDTO(coordenadaX.getText(), coordenadaY.getText());
	}
}
