package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/*Classe responsável pela manipulação de arquivos da aplicação.*/

public abstract class Arquivo
{
	private File arquivo;
	
	private String nomeArquivo;
	private int opcao;
	
	private FileInputStream arquivoEntradaStream;
	private FileOutputStream arquivoSaidaStream;
	
	OpcaoAbertura opcaoAbertura;


	public Arquivo(String nomeArquivo, int opcao) 
	{
		
		this.nomeArquivo = nomeArquivo;
		this.opcao = opcao;
	}


	public String getNomeArquivo() 
	{
		return nomeArquivo;
	}


	/*private void setNomeArquivo(String nomeArquivo) 
	{
		this.nomeArquivo = nomeArquivo;
	}*/


	public int getOpcao() 
	{
		return opcao;
	}


	/*public void setOpcao(byte opcao) 
	 * {
		this.opcao = opcao;
	}*/
	
	public File getArquivo() 
	{
		return arquivo;
	}

	public void setArquivo(File arquivo) 
	{
		this.arquivo = arquivo;
	}
	
	public FileInputStream getArquivoEntradaStream() 
	{
		return arquivoEntradaStream;
	}

	public void setArquivoEntradaStream(FileInputStream arquivo) 
	{
		this.arquivoEntradaStream = arquivo;
	}


	public FileOutputStream getArquivoSaidaStream() 
	{
		return arquivoSaidaStream;
	}


	public void setArquivoSaidaStream(FileOutputStream arquivoSaidaStream) 
	{
		this.arquivoSaidaStream = arquivoSaidaStream;
	}

	
	
	

}
