package controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/*Classe responsável pela escrita(opcao == 0) e leitura(opcao == 1) das estruturas Tabela Hash e ÁrvoreB*/
 
public abstract class ArquivoEstrutura extends Arquivo implements Interpreter
{
	

	private FileOutputStream arquivoSaidaStream;
	private ObjectOutputStream objetoSaidaStream; 

	//private FileInputStream arquivoEntradaStream; //está na classe arquivo
	private ObjectInputStream objetoEntradaStream; 


	public ArquivoEstrutura(String nomeArquivo, int opcao) //opcao indica se vai abrir escrita(0) ou leitura(1)
	{
		super(nomeArquivo, opcao);

		setArquivo(new File(nomeArquivo));

		if(opcao == opcaoAbertura.ESCRITA.getValor())
		{

			try
			{

				arquivoSaidaStream = new FileOutputStream(getArquivo());
				objetoSaidaStream = new ObjectOutputStream(arquivoSaidaStream); 

			}

			catch(IOException e)
			{
				System.out.println("Arquivo não encontrado");
			}

		}

		else //abrir arquivo para leitura
		{

			try
			{
				setArquivoEntradaStream(new FileInputStream(getArquivo()));
				objetoEntradaStream = new ObjectInputStream(getArquivoEntradaStream());
			}

			catch(IOException e)
			{
				System.out.println("Arquivo não encontrado");
			}

		}
	}

	public abstract void escreverNoArquivo(Object tabelaOuArvore);


	/*Retorna o objeto deserializado. na continuação do programa, deve-se fazer downcast o hash ou a 
	 * árvore lida para seus tipos*/
	public abstract Object lerArquivo() throws ClassNotFoundException;

	public void fecharArquivo() throws IOException
	{
		if (getOpcao() == opcaoAbertura.ESCRITA.getValor())
		{
			getObjetoSaidaStream().flush();
			getObjetoSaidaStream().close();
			getArquivoSaidaStream().close();
		}

		else
		{
			getObjetoEntradaStream().close();
			getArquivoEntradaStream().close();
		}
	}



	public FileOutputStream getArquivoSaidaStream() 
	{
		return arquivoSaidaStream;
	}

	public void setArquivoSaidaStream(FileOutputStream arquivoSaidaStream) 
	{
		this.arquivoSaidaStream = arquivoSaidaStream;
	}

	public ObjectOutputStream getObjetoSaidaStream() 
	{
		return objetoSaidaStream;
	}

	public void setObjetoSaidaStream(ObjectOutputStream objetoSaidaStream) 
	{
		this.objetoSaidaStream = objetoSaidaStream;
	}

	/*public FileInputStream getArquivoEntradaStream() 
	{
		return arquivoEntradaStream;
	}

	public void setArquivoEntradaStream(FileInputStream arquivoEntradaStream) 
	{
		this.arquivoEntradaStream = arquivoEntradaStream;
	}*/

	public ObjectInputStream getObjetoEntradaStream() 
	{
		return objetoEntradaStream;
	}

	public void setObjetoEntradaStream(ObjectInputStream objetoEntradaStream) 
	{
		this.objetoEntradaStream = objetoEntradaStream;
	}

	@Override
	public Interpreter open() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Interpreter next() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Interpreter close() {
		// TODO Auto-generated method stub
		return null;
	}




}
