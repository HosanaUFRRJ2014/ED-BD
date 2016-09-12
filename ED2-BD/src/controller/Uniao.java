package controller;

public class Uniao implements Interpreter 
{
	Interpreter relacaoEsq;
	Interpreter relacaoDir;
	int relacao;
	Tupla T;
	
	public Uniao(Interpreter relacaoEsq, Interpreter relacaoDir) 
	{
		super();
		this.relacaoEsq = relacaoEsq;
		this.relacaoDir = relacaoDir;
		relacao = 0;
		T = null;
	}

	@Override
	public Interpreter open()
	{
		// TODO Auto-generated method stub
		relacaoEsq.open();
		relacaoDir.open();
		return this;
	}

	@Override
	public Interpreter next() 
	{
		// TODO Auto-generated method stub
		if(relacao == 0)
		{
			T = (Tupla) relacaoEsq.next();
			if(T == null)
				relacao++;
		}
		if(relacao != 0)
		{
			T = (Tupla) relacaoDir.next();
			if(T == null)
				relacao = 0;
		}
			
		return T;
	}

	@Override
	public Interpreter close() {
		// TODO Auto-generated method stub
		relacaoEsq.close();
		relacaoDir.close();
		T = null;
		relacao = 0;
		return this;
	}

}
