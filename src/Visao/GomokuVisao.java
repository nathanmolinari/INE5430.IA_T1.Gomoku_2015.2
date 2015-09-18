package Visao;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;

import DTO.CadastroDoJogadorDTO;
import DTO.VisualizacaoDoJogoDTO;
import Enumeracao.AlinhamentoEnum;
import Enumeracao.JogadorEnum;

public class GomokuVisao extends VisaoAbstrato{
	public final JFrame FRAME_FAMILIAR;
	
	JTabbedPane paginacao;
	private JPanel paginaTabuleiro;
	private JPanel paginaHistoricoDeJogada;
	
	public GomokuVisao(){
		FRAME_FAMILIAR = InterfaceDaAplicacao.invocarInstancia().FRAME_FAMILIAR;
		
		paginaTabuleiro = new JPanel();
		paginaHistoricoDeJogada = new JPanel();
		
		paginacao = new JTabbedPane();
		paginacao.add(paginaTabuleiro, "Tabuleiro");
		paginacao.add(paginaHistoricoDeJogada, "Historico de Jogadas");
		
		FRAME_FAMILIAR.add(paginacao, BorderLayout.CENTER);
		FRAME_FAMILIAR.setVisible(true);
	}
	
	//FUNCOES
	
	public void atualizarVisualizacaoDoJogo(VisualizacaoDoJogoDTO estadoDoJogoDTO){
		paginaTabuleiro.removeAll();
		JTable tabelaTabuleiro = new JTable(estadoDoJogoDTO.TABULEIRO, estadoDoJogoDTO.getColumnNamesTabuleiro());	
			for (int i = 0; i < estadoDoJogoDTO.TABULEIRO[0].length; i++) {
				TableColumn column = tabelaTabuleiro.getColumnModel().getColumn(i);
				column.setPreferredWidth(10);
			}
		paginaTabuleiro.add(tabelaTabuleiro);	
		
		paginaHistoricoDeJogada.removeAll();
		JTable tabelaHistoricoDeJogada = new JTable(estadoDoJogoDTO.HISTORICO_DE_JOGADAS, estadoDoJogoDTO.getColumnNamesHistoricoDeJogadas());	
		for (int i = 0; i < estadoDoJogoDTO.HISTORICO_DE_JOGADAS[0].length; i++) {
			TableColumn column = tabelaHistoricoDeJogada.getColumnModel().getColumn(i);
			column.setPreferredWidth(10);
		}
		paginaHistoricoDeJogada.add(tabelaHistoricoDeJogada);
		
	}

	public CadastroDoJogadorDTO cadastrarJogador(AlinhamentoEnum[] listaDeAlinhamentoDisponivel, String idJogador){
		CadastroDoJogadorDTO cadastroDoJogadorDTO = new CadastroDoJogadorDTO();
		
		ArrayList<Object> listaDeComponentes = new ArrayList<Object>();
		listaDeComponentes.add("Entre com as informacoes do " + idJogador + "º jogador:");
	
		JTextField campoIdentificador = new JTextField(3);
			listaDeComponentes.add("Identificador:");
			listaDeComponentes.add(campoIdentificador);
					
		ButtonGroup grupoEspecie = new ButtonGroup();
		JRadioButton[] campoEspecie = new JRadioButton[JogadorEnum.values().length];
		for (int j = 0; j < JogadorEnum.values().length; j++) {
			campoEspecie[j] = new JRadioButton(JogadorEnum.values()[j].toString());
			grupoEspecie.add(campoEspecie[j]);
		}
			listaDeComponentes.add("Especie:");
			listaDeComponentes.add(campoEspecie);
		
		ButtonGroup grupoAlinhamento = new ButtonGroup();
		JRadioButton[] campoAlinhamento = new JRadioButton[listaDeAlinhamentoDisponivel.length];
		if(listaDeAlinhamentoDisponivel.length > 1){
			for (int j = 0; j < listaDeAlinhamentoDisponivel.length; j++) {
				campoAlinhamento[j] = new JRadioButton(listaDeAlinhamentoDisponivel[j].toString());
				grupoAlinhamento.add(campoAlinhamento[j]);
			}
				listaDeComponentes.add("Alinhamento:");
				listaDeComponentes.add(campoAlinhamento);
		}
		else{
			campoAlinhamento[0] = new JRadioButton(listaDeAlinhamentoDisponivel[0].toString(), true);
		}
	
		apresentarDialogoPersonalizado(listaDeComponentes.toArray(), "Cadastro de Jogador");
		
		cadastroDoJogadorDTO.identificador = campoIdentificador.getText();
		
		for(int i = 0; i < campoEspecie.length; i++){
			if(campoEspecie[i].isSelected()){
				cadastroDoJogadorDTO.especie = JogadorEnum.values()[i];
				break;
			}
		}
		
		for(int i = 0; i < campoAlinhamento.length; i++){
			if(campoAlinhamento[i].isSelected()){
				cadastroDoJogadorDTO.alinhamento = AlinhamentoEnum.values()[i];
				break;
			}
		}		
		
		return cadastroDoJogadorDTO;
	}
	
	private JPanel gerarPaginaTabuleiro() {
		JPanel pagina = new JPanel();
		JLabel label = new JLabel("TABULEIRO:");
		pagina.add(label);
	/*		
		JButton acao1 = new JButton("Inserir manualmente");
		acao1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setAtividade("Inserir", "Gramatica Livre de Contexto");
				
				controle.inserirGramaticaManualmente("");
			}
		});
		pagina.add(acao1);

		JButton acao2 = new JButton("Inserir via arquivo");
		acao2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setAtividade("Inserir", "Gramatica Livre de Contexto");
				
				controle.inserirGramaticaViaArquivo();
			}
		});
		pagina.add(acao2);
	*/
		return pagina;
	}

	
}
