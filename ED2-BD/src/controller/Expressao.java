package controller;

import java.util.LinkedList;

public class Expressao implements Interpreter 
{
	//essa classe precisa ser refeita
	private Interpreter operadorFinal;
	private LinkedList <Tupla>tuplas;

	public Interpreter getOperadorFinal() 
	{
		return operadorFinal;
	}

	public Expressao(Interpreter operadorFinal) 
	{
		super();
		this.operadorFinal = operadorFinal;
		tuplas = new LinkedList<Tupla>();
	}
	
	public void Executar()
	{
		operadorFinal.open();
		boolean parar = false;
		do
		{
			Interpreter i = operadorFinal.next();
			//System.out.println("aaa1	" + Auxi + "	" + parar );
			if(i == null)
			{
				
				parar = true;
				break;
			}
			Tupla t;
			
			if(i instanceof Tupla)
			{
				t = (Tupla) i;
				tuplas.add(t);
			}
		}
		while(!parar);
		//Auxi = 0;
		operadorFinal.close();
		return;
		
	}
	
	public void imprimirLista()
	{
		int i = 0;
		if(!tuplas.isEmpty())
		{	
			tuplas.get(0).imprimirNomeColunasTupla();
			while(i<tuplas.size())
			{
				tuplas.get(i).imprimirValoresColunasTupla();
				i++;
			}
		}
		else
		{
			System.out.println("Registo não encontrado - tuplas vazias na expressão");
			return;
		}
	}
	
	@Override
	public Interpreter open() 
	{
		// TODO Auto-generated method stub
		return operadorFinal.open();
	}

	@Override
	public Interpreter next() 
	{
		// TODO Auto-generated method stub
		Interpreter i = operadorFinal.next();
		if(i == null)
			return null;
		Tupla t = null;
		if(i instanceof Tupla)
			t = (Tupla) i;
		else
		{
			System.out.println("retorno não foi uma tupla");
			return null;
		}
		tuplas.add(t);
		return i;
	}

	@Override
	public Interpreter close() 
	{
		// TODO Auto-generated method stub
		return operadorFinal.close();
	}

	public LinkedList <Tupla>getTuplas() 
	{
		return tuplas;
	}

}
