package DTO;

import Enumeracao.AlinhamentoEnum;
import Enumeracao.JogadorEnum;
import Excecao.CamposPreenchidosDeFormaIncorretaException;

public class CadastroDoJogadorDTO {
	public String identificador;
	public JogadorEnum especie;
	public AlinhamentoEnum alinhamento;
	
	public CadastroDoJogadorDTO(){
		
	}
	
	public CadastroDoJogadorDTO(String _identificador, JogadorEnum _especie, AlinhamentoEnum _alinhamento){
		identificador = _identificador;
		especie = _especie;
		alinhamento = _alinhamento;
	}
	
	//VALIDACAO
	
	public void validar(){
		validarIdentificador();
		validarEspecie();
		validarAlinhamento();
	}

	private void validarIdentificador() {
		if(identificador == null || identificador.isEmpty()){
			throw new CamposPreenchidosDeFormaIncorretaException();
		}
	}

	private void validarEspecie(){
		if(especie == null){
			throw new CamposPreenchidosDeFormaIncorretaException();
		}
	}

	public void validarAlinhamento(){
		if(alinhamento == null){
			throw new CamposPreenchidosDeFormaIncorretaException();
		}
	}
}
