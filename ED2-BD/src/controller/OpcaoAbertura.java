package controller;

public enum OpcaoAbertura 
{
	ESCRITA(0),LEITURA(1);
	
	public int valor;
	
	OpcaoAbertura(int opcao)
	{
		valor = opcao;
	}
	
	public int getValor()
	{
		return valor;
	}

}
