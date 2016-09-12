package controller;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;

public class ArquivoTxt extends Arquivo 
{
	//para ler o arquivo em txt
	public FileInputStream arquivoEntradaStreamTxt;
	public DataInputStream filtroTxt;
	
	private String [] tipoDado;
	private String [] nomeCampo;
	private long posicaoInicial;
	private long posicaoComecoColunaTupla;
//	private ArquivoDado arquivoDado;

	private int contadorDelinhas = 0;
	
	public ArquivoTxt(String nomeArquivo, int opcao) 
	{
		super(nomeArquivo, opcao);
		
		openTxt(nomeArquivo);
		
	//	tipoDado = new String[];
		
		
	}
	
	public long lerCabecalhoTxt()
	{
		
		try 
		{
			String cabecalho = filtroTxt.readLine();
			contadorDelinhas++;
			
			posicaoInicial = arquivoEntradaStreamTxt.getChannel().position();
			
			String [] campoETipo = cabecalho.split("\t");
			String [][] aux = new String[campoETipo.length][];
			nomeCampo = new String[campoETipo.length];
			tipoDado = new String[campoETipo.length];
			for(int i = 0; i < campoETipo.length; i++)
			{
				aux[i] = campoETipo[i].split(" ");
				nomeCampo[i] = aux[i][0];
				tipoDado[i] = aux[i][1];
		//		System.out.print(nomeCampo[i]);
		//		System.out.println(tipoDado[i]);
				
			}
			
			return arquivoEntradaStreamTxt.getChannel().position();
					
		
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return 0;
	}

	//ler uma linha de registro do arquivo .txt, dada uma posição do arquivo e retornar um array de Strings
	public String [] lerLinhaArquivoRetornaStrings(long posicao)
	{
		String registro = "";
		
		char character = ' ';

		try 
		{
			//pode ser que posicaoComecoColunaTupla tenha que ser variável local.Isso para não dar problema.
			posicaoComecoColunaTupla = posicao;//arquivo.getChannel().position();
			arquivoEntradaStreamTxt.getChannel().position(posicaoComecoColunaTupla);
			

			registro = filtroTxt.readLine();
			
		
			posicaoComecoColunaTupla = arquivoEntradaStreamTxt.getChannel().position();

			return registro.split("\t");



		}
		catch (IOException e) 
		{
			System.out.println("Problema na leitura de linha");
			return null;
		}
 

	}
	
/*	public void escreverNoArquivo(ArquivoDado arquivoDado,String [] coluna) throws IOException
	{
		escreverCabecalhoNoArquivo(arquivoDado);
		escreverConteudoNoArquivo(arquivoDado,coluna);
	}*/
	
	public void escreverCabecalhoNoArquivo(ArquivoDado arquivoDado) throws IOException
	{
		ByteBuffer b1,b2;
		//escrever o número de linhas
		b1 = ByteBuffer.allocate(4);
		b1.putInt(/*arquivoDado.getNumLinhas()*/contadorDelinhas);
		arquivoDado.getFiltroEscrita().write(b1.array());
		
		//escrever o número de colunas
		b2 = ByteBuffer.allocate(4);
		b2.putInt(/*arquivoDado.getNumColunas()*/tipoDado.length);
		arquivoDado.getFiltroEscrita().write(b2.array());
		
		//escrever o vetor de tipo de dados
		for(int i = 0; i < tipoDado.length; i++)
		{
			byte [] b;
			
			b = new byte[2 * tipoDado[i].length()];
			b = tipoDado[i].getBytes();
			arquivoDado.getFiltroEscrita().writeInt(b.length);
			arquivoDado.getFiltroEscrita().write(b);
			
			b = new byte[2* nomeCampo[i].length()];
			b = nomeCampo[i].getBytes();
			arquivoDado.getFiltroEscrita().writeInt(b.length);
			arquivoDado.getFiltroEscrita().write(b);
		
		}
		
	}
	
	//dado um conjunto de strings vindo da função acima, escrever no arquivo de acordo com tipoDado[]
	public void escreverConteudoNoArquivo(ArquivoDado arquivoDado,String [] coluna) throws IOException
	{
		//System.out.println(1);
		for(int i = 0; i < tipoDado.length; i++)
		{
			//System.out.println(2);
			ByteBuffer b;
			
			switch(tipoDado[i])
			{
				
				case "(char)":
					arquivoDado.getFiltroEscrita().writeChar(Integer.parseInt(coluna[i]));
					break;
				
				case "(String)":
					b = ByteBuffer.allocate(2 * coluna[i].length());
					b.put(coluna[i].getBytes());
					arquivoDado.getFiltroEscrita().writeInt(b.array().length);
					arquivoDado.getFiltroEscrita().write(b.array());
					break;
					
				case "(int)":
					arquivoDado.getFiltroEscrita().writeInt(Integer.valueOf(coluna[i]));
					break;
					
				case "(float)":
					arquivoDado.getFiltroEscrita().writeFloat(Float.valueOf(coluna[i]));
					break;
					
				case "(double)":
					arquivoDado.getFiltroEscrita().writeDouble(Double.valueOf(coluna[i]));
					break;
					
				case "(short)":
					arquivoDado.getFiltroEscrita().writeShort(Short.valueOf(coluna[i]));
					break;
					
				case "(long)":
					arquivoDado.getFiltroEscrita().writeLong(Long.valueOf(coluna[i]));
					break;
					
				case "(boolean)":
					arquivoDado.getFiltroEscrita().writeBoolean(Boolean.valueOf(coluna[i]));
					break;
					
				case "(byte)":	
					arquivoDado.getFiltroEscrita().writeByte(Byte.valueOf(coluna[i]));
					break;
					
				default:
					System.out.println("Erro no reconhecimento de tipo");
					//System.out.println(tipoDado[i]);
					break;
					
		
			}
		}
		
	}
	
	public void openTxt(String nomeTxt)
	{
		try 
		{
		
			arquivoEntradaStreamTxt = new FileInputStream(nomeTxt);
			
			filtroTxt = new DataInputStream(arquivoEntradaStreamTxt);
	
		}

		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void closeTxt()
	{

		try 
		{
			arquivoEntradaStreamTxt.close();
		} 

		catch (IOException e) 
		{
			e.printStackTrace();
		}

	}

	public String getTipoDado(int i) 
	{
		return tipoDado[i];
	}

	public void setTipoDado(String tipoDado, int i) 
	{
		this.tipoDado[i] = tipoDado;
	}

	public String getNomeCampo(int i) 
	{
		return nomeCampo[i];
	}

	public void setNomeCampo(String nomeCampo, int i) 
	{
		this.nomeCampo[i] = nomeCampo;
	}

	public FileInputStream getArquivoEntradaStreamTxt()
	{
		return arquivoEntradaStreamTxt;
	}

	public void setArquivoEntradaStreamTxt(FileInputStream arquivoEntradaStreamTxt)
	{
		this.arquivoEntradaStreamTxt = arquivoEntradaStreamTxt;
	}

	public DataInputStream getFiltroTxt() 
	{
		return filtroTxt;
	}

	public void setFiltroTxt(DataInputStream filtroTxt) 
	{
		this.filtroTxt = filtroTxt;
	}

	public long getPosicaoInicial()
	{
		return posicaoInicial;
	}

	public void setPosicaoInicial(long posicaoInicial) 
	{
		this.posicaoInicial = posicaoInicial;
	}

	public long getPosicaoComecoColunaTupla() 
	{
		return posicaoComecoColunaTupla;
	}

	public void setPosicaoComecoColunaTupla(long posicaoComecoColunaTupla) 
	{
		this.posicaoComecoColunaTupla = posicaoComecoColunaTupla;
	}

/*	public ArquivoDado getArquivoDado() 
	{
		return arquivoDado;
	}

	public void setArquivoDado(ArquivoDado arquivoDado) 
	{
		this.arquivoDado = arquivoDado;
	}*/
	
	
	
	
	

	
	

}
