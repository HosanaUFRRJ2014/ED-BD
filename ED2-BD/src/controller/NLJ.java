package controller;

public class NLJ implements Interpreter 
{
	private Interpreter relacaoEsq;
	private Interpreter relacaoDir;
	private Interpreter tuplaAtualEsq;
	private Interpreter tuplaAtualDir;
	private ColunaTupla campoJuncao;
	//private boolean terminouDir;
	
	public NLJ(Interpreter relacaoEsq, Interpreter relacaoDir,
			ColunaTupla campoJuncao) {
		super();
		this.relacaoEsq = relacaoEsq;
		this.relacaoDir = relacaoDir;
		this.campoJuncao = campoJuncao;
		
		//this.terminouDir = true;
		
		this.tuplaAtualDir = null;
		this.tuplaAtualEsq = null;
	}
	
	public NLJ(Interpreter relacaoEsq, Interpreter relacaoDir,
			String nomeCampo, String valorCampo) {
		super();
		this.relacaoEsq = relacaoEsq;
		this.relacaoDir = relacaoDir;
		this.campoJuncao = new ColunaTupla(nomeCampo, valorCampo, "", -1);
		
		//this.terminouDir = true;
		
		this.tuplaAtualDir = null;
		this.tuplaAtualEsq = null;
	}

	@Override
	public Interpreter open() 
	{
		relacaoEsq.open();
		relacaoDir.open();
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public Interpreter next() 
	{
		if(tuplaAtualDir == null) // caso da primeira execução
			tuplaAtualEsq = relacaoEsq.next();

		tuplaAtualDir = relacaoDir.next(); // o direito sempre troca entao n tem necessidade de ficar dentro do if acima
		
		if(tuplaAtualDir == null) // caso esteja parado na ultima linha da dir, isso será vdd e atualiza-se as duas tuplas
		{
			tuplaAtualEsq = relacaoEsq.next();
			tuplaAtualDir = relacaoDir.next();		
		}
		
		if(tuplaAtualEsq == null) // se isso acontecer, após o código acima então chegou ao fim das duas relações
		{
			return null;
		}
		
		Tupla tuplaEsq = null, tuplaDir = null;//auxiliares para receber as tuplas
		if(tuplaAtualEsq instanceof Tupla)
			tuplaEsq = (Tupla) tuplaAtualEsq;
		else // apenas para testes
		{
			System.out.println("tupla esquerda não é tupla");
			return null;
		}	
		
		if(tuplaAtualDir instanceof Tupla)
			tuplaDir = (Tupla) tuplaAtualDir;
		else // apenas para testes
		{
			System.out.println("tupla direita não é tupla");
			return null;
		}
		//boolean auxiliar pra dizer se achou uma tupla que procurava nas duas relações
		boolean achou = false;
		
		while(achou == false)// a principio n achou
		{
			if(tuplaEsq == null) //se isso acontece chegou no fim da relacao esq entao volta ao inicio e continua
			{
				tuplaAtualEsq = relacaoEsq.next();
				return null;
			}
			
			if(this.existeCampo(tuplaEsq)) //olhar os comentários no método
			{
				while(tuplaDir != null) // se existe, procura um equivalente na direita ate o fim da relação
				{
					if(this.existeCampo(tuplaDir)) // olhar os comentários no método
					{// se achar então achou uma nova tupla correspondente nas duas relações
						achou = true;
						break;//break no while(tuplaDir!=null)
					}
					else // se n for uma tupla correspondente
					{
						tuplaAtualDir = relacaoDir.next(); // atualiza a tupla
						if(tuplaAtualDir == null)
							tuplaDir = null; //gambiarra feita no final pra não alterar o código
						else
							tuplaDir = (Tupla) tuplaAtualDir; // passa para a proxima linha da direita ate o fim da relação da direita
					}
				}
				
				if(tuplaDir == null) // se sair do while acima sendo null
				{
					tuplaAtualEsq = relacaoEsq.next(); //quer dizer que passou toda a relação da direita e agora vai para proxima linha da esq
					if(tuplaAtualEsq == null)
						tuplaEsq = null; // mesma gambiarra de cima
					else
						tuplaEsq = (Tupla) tuplaAtualEsq;
					
					continue; // vai para o proximo while(achou == false)
				}
				else // se sair do while sem ser null quer dizer que achou, entao sai do while(achou == false)
				{
					break;
				}
				
			}
			else //se n for uma tupla da esq correspondente troca para a proxima
			{
				tuplaAtualEsq = relacaoEsq.next();
				if(tuplaAtualEsq == null)
					tuplaEsq = null;
				else
					tuplaEsq = (Tupla) tuplaAtualEsq;
				
				continue;
			}
			
		}
		//aqui qnd acha monta a tupla de retorno que é o somatorio das duas tuplas correspondentes
		Tupla retorno = new Tupla();
		tuplaEsq.open(); //volta o indice pro inicio, só por precaução (olhar o método na classe)
		ColunaTupla colEsq = (ColunaTupla) tuplaEsq.next(); 
		while(colEsq!=null) // pegar todas as tuplas da esquerda
		{
			retorno.associarColunaTupla(colEsq);
			colEsq = (ColunaTupla) tuplaEsq.next();
		}
		
		tuplaDir.open();
		ColunaTupla colDir = (ColunaTupla) tuplaDir.next();
		while(colDir!=null) // pegar todas as tuplas da direita
		{
			retorno.associarColunaTupla(colDir);
			colDir = (ColunaTupla) tuplaDir.next();
		}
		
		return retorno;
		
		/*
		while(tuplaEsq != null)
		{
			boolean temCampo = false;
			tuplaEsq.open();
			tuplaDir.open();
			
			ColunaTupla auxEsq = (ColunaTupla) tuplaEsq.next();
			while(temCampo == false || auxEsq == null)
			{
				if(auxEsq.getNome() == campoJuncao.getNome() )
				{
					if(auxEsq.getNome)
				}
			}
		}*/
		
		// TODO Auto-generated method stub
	}
	//método auxiliar pra dizer se uma tupla tem o campo que to buscando
	// facilita a leitura do código do next
	private boolean existeCampo(Tupla t) 
	{
		t.open();
		ColunaTupla c = (ColunaTupla)t.next(); 
		while(c != null)
		{
			if(c.getNome().equals(campoJuncao.getNome()))
				if(c.getValor().equals(campoJuncao.getValor()))
					return true;
				else
				{
					c = (ColunaTupla)t.next();
				}
		}
		return false;
	}

	@Override
	public Interpreter close() {
		relacaoEsq.close();
		relacaoDir.close();
		// TODO Auto-generated method stub
		return this;
	}

}
