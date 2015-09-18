package Enumeracao;

import java.awt.Point;

public enum DirecaoEnum {
	Norte(		new Point(0, +1)	),
	Nordeste(	new Point(+1, +1)	),
	Leste(		new Point(+1, 0)	),
	Sudeste(	new Point(+1, -1)	),
	Sul(		new Point(0, -1)	),
	Sudoeste(	new Point(-1, -1)	),
	Oeste(		new Point(-1, 0)	),
	Noroeste(	new Point(-1, +1)	);
	
	public Point referenciaCartesiana;
	
	DirecaoEnum(Point _referenciaCartesiana){
		referenciaCartesiana = _referenciaCartesiana;
	}
	
	public DirecaoEnum direcaoOposta(){				
		switch(this){
			case Norte:		return Sul;
			case Nordeste:	return Sudoeste;
			case Leste:		return Oeste;
			case Sudeste:	return Noroeste;
			case Sul:		return Norte;
			case Sudoeste:	return Nordeste;
			case Oeste:		return Leste;
			case Noroeste:	return Sudeste;
		}
		return null;
	}
	
	/*
	public static DirecaoEnum[] listaDeDirecoesAnteriores(){
	 
		return new DirecaoEnum[]{
				Oeste, Noroeste, Norte, Nordeste 
		};
	}
	
	public static DirecaoEnum[] listaDeDirecoesPosteriores(){
		return new DirecaoEnum[]{
				Leste, Sudeste, Sul, Sudoeste 
		};
	}
	*/
}