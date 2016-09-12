package controller;

import java.util.LinkedList;

public class Tupla implements Interpreter 
{
	private LinkedList<ColunaTupla> colunas;
	private int colunaAtual;

	public Tupla()
	{
		super();
		colunaAtual = 0;
		colunas = new LinkedList<ColunaTupla>();
	}
	
	//cria uma nova colunaTupla, e adiciona essa colunaTupla criada à tupla
	
	public void imprimirNomeColunasTupla()
	{
		int auxColunaAtual = colunaAtual;
		colunaAtual = 0;
		ColunaTupla c;
		do
		{
			c = (ColunaTupla) this.next();
			if(c==null)
				break;
			System.out.print(c.getNome() + "	");
		}
		while(colunaAtual != 0);
		System.out.print('\n');
		colunaAtual = auxColunaAtual;
	}
	
	public void imprimirValoresColunasTupla()
	{
		int auxColunaAtual = colunaAtual;
		colunaAtual = 0;
		ColunaTupla c;
		do
		{
			c = (ColunaTupla) this.next();
			if(c==null)
				break;
			System.out.flush();
			System.out.print(c.getValor() + "	");
		}
		while(colunaAtual != 0);
		System.out.print('\n');
		colunaAtual = auxColunaAtual;
	}
	
	//***Opinião Hosana: Acho que tem que passar o endereço do arquivo por parâmetro. Como acessar esssa informações depois?
	public void addColunaTupla(String c, String valor) 
	{
		ColunaTupla colunaTupla = new ColunaTupla(c, valor, "", -1);
		colunas.add(colunaTupla);
	}
	
	//adiciona uma colunaTupla já existente a Tupla
	public void associarColunaTupla(ColunaTupla c)   
	{
		colunas.add(c);
	}
	
	//criado por Hosana. Vi a necessidade de adicionar tudo de vez
	public void associarMuitasColunaTupla(LinkedList<ColunaTupla> c)
	{
		colunas.addAll(0, c);
	}
	
	public ColunaTupla getColunaTupla(int index)
	{
		return colunas.get(index);
	}
	
	//métodos referentes ao interpreter
	
	@Override
	public Interpreter open() 
	{
		colunaAtual = 0;
		return this;
	}

	@Override
	public Interpreter next() 
	{
		colunaAtual++;
		if(colunaAtual > colunas.size())
		{
			colunaAtual = 0;
			return null;
		}
		return (Interpreter) colunas.get(colunaAtual - 1);
		//return (Interpreter) colunas.get(colunaAtual - 1);
	}

	@Override
	public Interpreter close() 
	{
		
		colunaAtual = 0;
		return this;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tupla other = (Tupla) obj;
		if (colunas == null)
		{ 
			if (other.colunas != null)
				return false;
		}else if (!colunas.equals(other.colunas))
			return false;

		return true;
	}

}
