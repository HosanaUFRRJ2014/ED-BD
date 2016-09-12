package controller;
import java.io.IOException;
import java.util.Hashtable;

import entity.Tabela;

public class ArquivoHashTable extends ArquivoEstrutura 
{
	private Tabela tabela;


	public ArquivoHashTable(String nomeArquivo, int opcao)
	{
		super(nomeArquivo, opcao);

	}


	//Serializar o objeto. rebece como paramentro um objeto da classe tabela
	public void escreverNoArquivo(Object tabela)
	{
		this.tabela = (Tabela) tabela;
		

		try
		{
			//salva a hashtable, não um objeto da classe tabela
			getObjetoSaidaStream().writeObject(this.tabela.getHashTable()); 		

		}
		
		catch(IOException e)
		{
			e.printStackTrace();
			//System.out.println("Não gravou a hash");
		}
		

	}

    /*A notação @Override serve apenas para auxiliar o programador que deseja sobrescrever um método
     *  da classe pai. Não há diferença em colocar ou não, exceto que, se usado, poderá gerar um erro se não houver
     *   um método a ser sobrescrito na classe pai.
    */
    @Override  
    public Hashtable lerArquivo() throws ClassNotFoundException
	{
		
    	Hashtable hashTable;

		try
		{
			//pega a hashtable, não um objeto da classe Tabela
			hashTable = (Hashtable) getObjetoEntradaStream().readObject(); 		
			
			return hashTable;
		}
		
		catch(IOException e)
		{
			System.out.println("Não leu a hash");
		}
		
		return null;
	}

	// método de criar tabela a partir do método acima, A hashtable h será o retorno do método acima
    public Tabela criarTabela(Hashtable h, int tamanho)
    {
    	Tabela tabela = new Tabela(tamanho);
    	
    	tabela.getHashTable().putAll(h);
    	
    	return tabela;
    }


	public Tabela getTabela() 
	{
		return tabela;
	}



	public void setTabela(Tabela tabela) 
	{
		this.tabela = tabela;
	}



}
