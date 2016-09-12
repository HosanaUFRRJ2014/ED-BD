package entity;

import java.io.Serializable;
import java.util.LinkedList;

public class Chave implements Serializable
{
	private String string;
	private LinkedList posicao; //guarda a posicao do arquivo do inicio do registro(começo da linha do registro)
	//private final int tamanhoMaximo = 50; //número máximo de caracteres que uma chave pode ter
	
	public Chave(String string)
	{
		this.string = string;
		posicao = new LinkedList();
	}
	
	
	
	/*Métodos gerados automaticamente pelo Eclipse. Se estiver ruim, não fui eu!!*/

	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((string == null) ? 0 : string.hashCode());
		
		
		return result;
	}

	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		Chave other = (Chave) obj;
		
		if (string == null) 
		{
			if (other.string != null)
				return false;
		} 
		
		else if (!string.equals(other.string))
			return false;
		
		return true;
	}

	public String getString() 
	{
		return string;
	}

	public void setString(String string) 
	{
		this.string = string;
	}

	public long getPosicao(int i) 
	{
		return  (long)posicao.get(i);
	}

    public LinkedList getPosicao()
    {
    	return posicao;
    }

	public boolean adicionarPosicao(long posicao) 
	{
		return this.posicao.add(posicao);
		
	}
	
	
    
	
	/*public int getTamanhoMaximo() 
	{
		return tamanhoMaximo;
	}*/
	
	
	
	
	
	

	
	
	

}
