package controller;

public class JuncaoNatural implements Interpreter {

	Interpreter relacaoEsq;
	Interpreter relacaoDir;
	Conteiner resultadoIntermediario;
	ColunaTupla campoJuncao;
	Interpreter NLJ;
	Interpreter projecao;
	
	public JuncaoNatural(Interpreter relacaoEsq, Interpreter relacaoDir, ColunaTupla campoJuncao) {
		super();
		this.relacaoEsq = relacaoEsq;
		this.relacaoDir = relacaoDir;
		this.campoJuncao = campoJuncao;
		NLJ = new NLJ(relacaoEsq, relacaoDir, campoJuncao);
	}
	
	public JuncaoNatural(Interpreter relacaoEsq, Interpreter relacaoDir, String nomeCampo, String valorCampo) {
		super();
		this.relacaoEsq = relacaoEsq;
		this.relacaoDir = relacaoDir;
		this.campoJuncao = new ColunaTupla(nomeCampo,valorCampo, "", -1);
		NLJ = new NLJ(relacaoEsq, relacaoDir, campoJuncao);
	}

	@Override
	public Interpreter open() 
	{
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public Interpreter next() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Interpreter close() {
		// TODO Auto-generated method stub
		return null;
	}

}
