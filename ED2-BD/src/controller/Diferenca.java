package controller;

public class Diferenca implements Interpreter 
{
	Interpreter relacaoEsq;
	Interpreter relacaoDir;
	Tupla T;

	public Diferenca(Interpreter relacaoEsq, Interpreter relacaoDir) {
		super();
		this.relacaoEsq = relacaoEsq;
		this.relacaoDir = relacaoDir;
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
	public Interpreter next() {
		// TODO Auto-generated method stub
		
		T = (Tupla) relacaoEsq.next();
		while(T != null)
		{
			boolean naoTemNaDir = true;
			while(naoTemNaDir)
			{
				Tupla Tdir = (Tupla) relacaoDir.next();
				if(Tdir == null)
				{
					relacaoDir.open();
					return T;
				}
				if(T.equals(Tdir))
				{
					naoTemNaDir = false;
					relacaoDir.open();
				}
			}
			
			T = (Tupla) relacaoEsq.next();
		}
		
		return null;
	}

	@Override
	public Interpreter close() 
	{
		// TODO Auto-generated method stub
		relacaoEsq.close();
		relacaoDir.close();
		T = null;
		return null;
	}

}
