

import Controle.GerenteDaAplicacao;

public class Main {

	public static void main(String[] args) {
		
//		HashSet<SequenciaDePecas> conjunto = new HashSet<SequenciaDePecas>();
//		SequenciaDePecas[] listaDeSequencias = {
//			new SequenciaDePecas(new Point(8, 8), DirecaoEnum.Norte, 5, AlinhamentoEnum.Preto),
//			new SequenciaDePecas(new Point(8, 12), DirecaoEnum.Sul, 5, AlinhamentoEnum.Preto)
//		};
//		System.out.println(listaDeSequencias[0].equals(listaDeSequencias[1]));
//		for (SequenciaDePecas sequenciaDePecas : listaDeSequencias) {
//			conjunto.add(sequenciaDePecas);
//		}
//		System.out.println(conjunto.size());
		
		GerenteDaAplicacao.invocarInstancia().iniciar();
	}
	
}
