package entity;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
//import java.util.HashSet;
import java.util.Hashtable;



//Busca 4:Todas as informações do autor (id do autor informado como parâmetro);

public class Tabela
{
	/*hashTable de hashsets(listas apropriadas para isso), na qual os Integer(numeros) são as
	posicoes que serão encontradas pela função de dispersão dada uma Chave*/
	private Hashtable <Integer, LinkedList<Chave>> hashTable; 
	private int tamanho;
	private int cont = 0;



	public Tabela(int tamanho)
	{
		hashTable = new Hashtable<Integer, LinkedList<Chave>>(tamanho,1);
		this.tamanho = tamanho;
	}

	//só para testes
	private int funcaoDispercao(Chave chave)
	{

		return (chave.getString().hashCode()) % tamanho;

	}


	/*	public boolean adicionarElemento(Chave chave) 
	{
		//mapear posicao na tabela dado uma chave
		int posicao = funcaoDispercao(chave);

		//se a posição mapeada está vazia
		if(hashTable.get(posicao) == null)
		{
			//cria a "lista"
			HashSet <Chave> lista = new HashSet();

			//adiona a chave a "lista". 
			hash.add(chave);

			//põe a "lista" na tabela
			hashTable.put(posicao, hash);

			return true;
		}

		//se já tem algum elemento criado na "lista"
		else
		{
			//Adiciona a chave na "lista" já criada e retorna true
			//Se já tem elemento igual retorna false
			return hashTable.get(posicao).add(chave);


		}


	}*/


	public boolean adicionarElemento(String string, long enderecoArquivo) 
	{

		//	BufferedWriter buffWrite = new BufferedWriter(new FileWriter("resultado.txt"));

		Chave chave = new Chave(string);
		//	chave.setPosicao(enderecoArquivo);

		//mapear posicao na tabela dado uma chave
		int posicao = funcaoDispercao(chave);

		/*	buffWrite.write(chave.getString() + '\n');
		buffWrite.flush();*/
		chave.adicionarPosicao(enderecoArquivo);
		//System.out.println("Posicao na tabela: " + posicao);

		//se a posição mapeada está vazia
		if(hashTable.get(posicao) == null)
		{
			//cria a "lista"
			LinkedList <Chave> lista = new LinkedList<Chave>();

			//chave.adicionarPosicao(enderecoArquivo);

			//adiona a chave a "lista". 
			boolean adicionado = lista.add(chave);


			//põe a "lista" na tabela
			hashTable.put(posicao, lista);
			//System.out.println(count + " - 1");

			return adicionado;
		}

		//se já tem algum elemento criado na "lista"
		else
		{

			//se já existe chave igual, só adicionar o endereço diferente
			if(buscarChave(string) == true)
			{
				return buscarChaveRetornaChave(string).adicionarPosicao(enderecoArquivo);

				
			}

			//se for diferente, só adicona a nova chave
			else
				return hashTable.get(posicao).add(chave);





		}


	}

	public Chave buscarChaveRetornaChave(String stringBuscada)
	{
		Chave temp = new Chave(stringBuscada);

		//calcular a posição na tabela a partir da chave buscada usando a função de dispersão
		int posicao = funcaoDispercao(temp);

		//se a posição não é nula, percorre a lista associada a ela.
		if(hashTable.get(posicao) != null)
		{
			//	LinkedList aux;// = hashTable.get(posicao);

			//for(Object chave :hashTable.get(posicao)/* aux*/)
			int i = 0;
		    while(i <hashTable.get(posicao).size())
			{
				//Chave c = (Chave)chave;
				if(temp.getString().equals(((Chave)hashTable.get(posicao).get(i)).getString()))
				{
					//	System.out.println("A chave " + stringBuscada + " foi encontrada");
				//	return (Chave)chave;
					return (Chave)hashTable.get(posicao).get(i);
				}
				
				i++;

			}

		}

		//System.out.println("A chave " + stringBuscada + " não foi encontrada");
		return null;
	}

	/*Essa função retorna um boolean. Pode ser que possamos retornar o objeto encontrado ou null se não encontrar.*/
	public boolean buscarChave(String stringBuscada)
	{
		Chave temp = new Chave(stringBuscada);

		//calcular a posição na tabela a partir da chave buscada usando a função de dispersão
		int posicao = funcaoDispercao(temp);

		//se a posição não é nula, percorre a lista associada a ela.
		if(hashTable.get(posicao) != null)
		{
			//LinkedList<Chave> aux = hashTable.get(posicao);

			for(Object chave : hashTable.get(posicao))
			{
				//Chave c = (Chave)chave;
				if(temp.equals((Chave)chave))
				{
					//		System.out.println("A chave " + stringBuscada + " foi encontrada");
					cont = cont + ((Chave)chave).getPosicao().size();
					return true;
				}

			}

		}

		//	System.out.println("A chave " + stringBuscada + " não foi encontrada");
		return false;
	}

	/*Esse método retorna a posicao do registro arquivo Binário , dada uma chave*/
	public LinkedList buscarChaveRetornaPosicaoArquivo(String stringBuscada)
	{
		//armazenará todos os endereços de todas as chaves que tiverem o mesmo nome. Lembre: mesmo nome != mesmo registro, por isso foi incluso
		//	LinkedList enderecos = new LinkedList();
		//stringBuscada = stringBuscada.
		Chave temp = new Chave(stringBuscada);

		//calcular a posição na tabela a partir da chave buscada usando a função de dispersão
		int posicao = funcaoDispercao(temp);
		//System.out.println("Posição na HashTable: " + posicao);

		//se a posição não é nula, percorre a lista associada a ela.
		if(hashTable.get(posicao) != null)
		{
			//LinkedList aux = hashTable.get(posicao);

			for(Object chave : /*aux*/hashTable.get(posicao))
			{
				Chave c = (Chave)chave;
				if(temp.equals(c))
				{
					//			System.out.println("A chave " + stringBuscada + " foi encontrada");
					return ((Chave)chave).getPosicao();
				}

			}

		}





		//		System.out.println("A chave " + stringBuscada + " não foi encontrada");
		return null;
	}


	public Hashtable<Integer,LinkedList<Chave>> getHashTable() 
	{
		return hashTable;
	}

	public void setHashTable(Hashtable<Integer, LinkedList<Chave>> hashTable) 
	{
		this.hashTable = hashTable;
	}

	public int getTamanho() 
	{
		return tamanho;
	}

	public void setTamanho(int tamanho) 
	{
		this.tamanho = tamanho;
	}

	public void imprimirTabela() throws IOException
	{
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter("r.txt"));

		for(int i = 0; i < tamanho; i++)
		{
			//System.out.println("posicao "+ i + ":");
			//buffWrite.write("poisição:  " + i + ":" + "\n");
			if(hashTable.get(i) != null)
			{
				//LinkedList<Chave> lista = hashTable.get(i);
				for(Object chave : hashTable.get(i))
				{
					//Chave c = (Chave)chave;
					int h = 0;			//System.out.println(c.getString());
					
					//System.out.println(c.getPosicao().size());
					while(h < ((Chave)chave).getPosicao().size())
					{
						buffWrite.write(((Chave)chave).getString() + '\n');

						buffWrite.flush();
						h++;
					}

				}

				//	System.out.println("posicao "+ i + " ocupada");
			}

			//	else
			//	System.out.println("posicao "+ i + " vazia");

			//	System.out.println("-----vazio------");
		}

		buffWrite.close();
	}

	public int getCont() {
		return cont;
	}

	public void setCont(int cont) {
		this.cont = cont;
	}





}
