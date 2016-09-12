package controller;

public class Interseccao implements Interpreter 
{
	Interpreter relacaoEsq;
	Interpreter relacaoDir;
	Conteiner TuplasEsqDiferencaDir;
	Diferenca d;
	
	public Interseccao(Interpreter relacaoEsq, Interpreter relacaoDir) {
		super();
		this.relacaoEsq = relacaoEsq;
		this.relacaoDir = relacaoDir;
		d = new Diferenca(relacaoEsq, relacaoDir);
		TuplasEsqDiferencaDir = new Conteiner();
	}

	@Override
	public Interpreter open() 
	{
		// TODO Auto-generated method stub
		relacaoEsq.open();
		relacaoDir.open();
		d.open();
		Tupla T = (Tupla) d.next();
		while(T!=null)
		{
			TuplasEsqDiferencaDir.associarTupla(T);
			T = (Tupla) d.next();
		}
		d.close();
		d = new Diferenca(relacaoEsq, TuplasEsqDiferencaDir);
		d.open();
		return this;
	}

	@Override
	public Interpreter next() 
	{
		return d.next();
		// TODO Auto-generated method stub
	}

	@Override
	public Interpreter close() {
		// TODO Auto-generated method stub
		d.close();
		return null;
	}
	
	

}
