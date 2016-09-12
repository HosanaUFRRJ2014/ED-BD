package controller;

import java.util.LinkedList;

public class Conteiner implements Interpreter 
{
	LinkedList<Tupla> Tuplas;
	int indice;
	public Conteiner() 
	{
		super();
		Tuplas = new LinkedList<Tupla>();
	}

	@Override
	public Interpreter open() 
	{
		// TODO Auto-generated method stub
		indice = 0;
		return null;
	}

	@Override
	public Interpreter next() 
	{
		if(indice < Tuplas.size())
		{
			indice++;
			return Tuplas.get(indice-1);
		}
		else
		{
			indice = 0;
			return null;
		}
		// TODO Auto-generated method stub
	}

	@Override
	public Interpreter close() 
	{
		// TODO Auto-generated method stub
		indice = 0;
		return null;
	}
	
	public void associarTupla(Tupla t)
	{
		Tuplas.add(t);
	}
	
	//criado por Hosana. Vi a necessidade de adicionar tudo de vez
	public void associarTuplas(LinkedList<Tupla> t)
	{
		Tuplas.addAll(Tuplas.size(), t);
	}
	
	public void removerTuplas()
	{
		Tuplas.removeAll(Tuplas);
	}

}
