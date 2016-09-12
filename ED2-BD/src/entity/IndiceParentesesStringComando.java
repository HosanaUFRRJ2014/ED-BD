package entity;

import java.util.LinkedList;

public class IndiceParentesesStringComando 
{
	public LinkedList getParAbertos() {
		return parAbertos;
	}

	public LinkedList getParFechados() {
		return parFechados;
	}
	
	LinkedList parAbertos;
	LinkedList parFechados;
	
	public IndiceParentesesStringComando()
	{
		parAbertos = new LinkedList();
		parFechados = new LinkedList();
	}
	
	public int addParentesesAberto(Par aberto, int pos)
	{
		if(pos>0)
		{
			while(pos>=parAbertos.size())
				parAbertos.add(null);
			
			if( parAbertos.get(pos) == null)
				parAbertos.set(pos, aberto);
			else
				return -1;
			return pos;

		}
		else
		{
			parAbertos.add(aberto);
			return parAbertos.indexOf(aberto);
		}
	}
	
	public int addParentesesFechado(Par fechado, int pos)
	{
		if(pos>0)
		{
			while(pos>=parFechados.size())
				parFechados.add(null);
			
			if(parFechados.get(pos) == null)
				parFechados.set(pos, fechado);
			else
				return -1;
			
			return pos;
		}
		else
		{
			parFechados.add(fechado);
			return parFechados.indexOf(fechado);
		}
	}
	
	public Par removerUltimoAberto()
	{
		Par p = (Par) parAbertos.getLast();
		while(p == null)
		{
			parAbertos.removeLast();
			p = (Par) parAbertos.getLast();
		}
		parAbertos.remove(p);
		return p;
	}
	
	public Par removerAberto(int pos)
	{
		Par p = (Par) parAbertos.get(pos);
		parAbertos.remove(p);
		return p;
	}

}
