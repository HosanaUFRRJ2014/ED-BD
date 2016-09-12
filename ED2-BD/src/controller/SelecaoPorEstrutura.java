package controller;
import java.io.IOException;
import java.util.LinkedList;

import entity.Tabela;
import entity.Chave;

public class SelecaoPorEstrutura extends Selecao 
{
	//private ArquivoArvoreB arqArvoreB = null;
	//private ArvoreB arvore = null;
	//private int D;
	private ArquivoHashTable arqHash = null;
	private Tabela hash = null;
	private int tamanhoHash = -1;
	
	private int estrutura;// 0 = arvore, 1 = hash
	private ArquivoDado arqRelacao;
	private LinkedList enderecosRetorno;
	private int enderecoAtual = 0;
	
	public SelecaoPorEstrutura(String campo, String valor, Interpreter relacao, ArquivoEstrutura arqE, int tam_ordem, ArquivoDado arqD)
	{
		super(campo, valor, relacao);
		arqRelacao = arqD;
		if(arqE instanceof ArquivoHashTable)
		{
			tamanhoHash = tam_ordem;
			arqHash = (ArquivoHashTable) arqE;
			estrutura = 1;
			//arqArvoreB = null;
		}
		else
		{
			arqHash = null;
			estrutura = 0;
			//arqArvoreB = (ArquivoArvoreB) arqE;
			//D = tam_ordem
		}
			
	}
	
	public LinkedList buscarEnderecoNaEstrutura()
	{
		switch(estrutura)
		{
			case 0:
			{
				
				break;
			}
			case 1:
			{
				try 
				{
					if(hash == null)
					{
						tamanhoHash = 1000;
						hash = arqHash.criarTabela(arqHash.lerArquivo(), tamanhoHash);
					}
					 return hash.buscarChaveRetornaPosicaoArquivo(valorBusca.getValor()); 
				}
				catch (ClassNotFoundException e) 
				{
					// TODO Auto-generated catch block
					System.out.println("Não leu a hash");
					
				}
				break;
			}
		}
		return null;
	}
	
	public Tupla buscarRegistroArquivoDado(long pos) throws IOException
	{
		return arqRelacao.criarTupla(arqRelacao.lerLinhaArquivo(pos));
	}
	
	@Override
	public Interpreter open() 
	{
		if(relacao != null)
			super.open();
		
		switch(estrutura)
		{
			case 0:
			{
				return this;
			}
			case 1:
			{
				try 
				{
					hash = arqHash.criarTabela(arqHash.lerArquivo(), tamanhoHash);
					enderecosRetorno = buscarEnderecoNaEstrutura();
					//System.out.println("qtd enderecos: " + enderecosRetorno.size());
					Interpreter i = null;
					try {
						i = this.buscarRegistroArquivoDado( (long) enderecosRetorno.get(enderecoAtual));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(i == null)
					{
						System.out.println("não existe esta chave");
						return i;
					}
					else
						return i;
				}
				catch (ClassNotFoundException e) 
				{
					// TODO Auto-generated catch block
					System.out.println("Não criou a hash");
				}
				break;
			}
		}
		return null;
		// TODO Auto-generated method stub
	}
	
	@Override
	public Interpreter next()
	{		
		switch(estrutura)
		{
			case 0:
			{
				break;
			}
			case 1:
			{
				if(enderecosRetorno == null)
				{
					enderecosRetorno = buscarEnderecoNaEstrutura();
					if(enderecosRetorno == null)
					{
						System.out.println("Chave não existente");
						return null;
					}
					enderecoAtual = 0;
				}
				
				if(enderecoAtual >= enderecosRetorno.size())
				{
					enderecoAtual = 0;
					return null;
				}
				enderecoAtual++;
				try {
					return this.buscarRegistroArquivoDado( (long) enderecosRetorno.get(enderecoAtual - 1) );
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	@Override
	public Interpreter close() 
	{
		if(relacao != null)
			super.close();
		//arqArvoreB = null;
		//arvore = null;
		try 
		{
			arqHash.fecharArquivo();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		arqHash = null;
		hash = null;
		return this;
		// TODO Auto-generated method stub
	}
}
