package controller;

public class ColunaTupla implements Interpreter 
{
	private String nome;
	private String nomeComposto;
	private String valor;
	private String arquivoOrigem;
	private long enderecoTuplaOrigem;
	
	public ColunaTupla(String nome, String valor, String nomeArquivo, long endereco) 
	{
		super();
		this.nome = nome;
		this.valor = valor;
		this.arquivoOrigem = nomeArquivo;
		this.enderecoTuplaOrigem = endereco;
	}
	
	
	public String getValor()
	{
		return this.valor;
	}
	
	public void setValor(String valor)
	{
		this.valor = valor;
	}
	
	public String getArquivoOrigem() 
	{
		return arquivoOrigem;
	}
	
	public long getEnderecoTuplaOrigem() 
	{
		return enderecoTuplaOrigem;
	}
	
	public void renomearColunaJuncao()
	{
		nomeComposto = nome + arquivoOrigem;
	}
	
	public String getNomeComposto() 
	{
		return nomeComposto;
	}
	
	public String getNome()
	{
		return this.nome;
	}
	
	public void setNome(String nome)
	{
	    this.nome = nome;
	}
	
	//Referente ao Interpreter
	
	public Interpreter open() 
	{
		 
		return null;
	}

	@Override
	public Interpreter next() 
	{
		 
		return null;
	}

	@Override
	public Interpreter close() 
	{
		 
		return null;
	}

}
