package controller;

public class HashJoin implements Interpreter 
{
	private Interpreter relacaoEsq;
	private Interpreter relacaoDir;
	private Interpreter tuplaAtualEsq;
	private Interpreter tuplaAtualDir;
	private String campoJuncao;
	private String valorJuncao = "";
	private int indiceValorjuncao = -1;
	
	public HashJoin(Interpreter relacaoEsq, Interpreter relacaoDir,
			String campoJuncao) {
		super();
		this.relacaoEsq = relacaoEsq;
		this.relacaoDir = relacaoDir;
		this.tuplaAtualEsq = null;
		this.tuplaAtualDir = null;
		this.campoJuncao = campoJuncao;
	}
	
	@Override
	public Interpreter open() 
	{
		tuplaAtualEsq = relacaoEsq.open();
		indiceValorjuncao = this.existeCampo((Tupla)tuplaAtualEsq);
		if(!((Tupla)tuplaAtualEsq).getColunaTupla(indiceValorjuncao).equals(valorJuncao))
			this.alterarValorJuncao((Tupla)tuplaAtualEsq);
		
		relacaoDir.open();
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Interpreter next() 
	{
		if(tuplaAtualDir == null) // caso da primeira execução
		{
			tuplaAtualEsq = relacaoEsq.next();
			if(tuplaAtualEsq != null)
			{
				if(!((Tupla)tuplaAtualEsq).getColunaTupla(indiceValorjuncao).equals(valorJuncao))
					this.alterarValorJuncao((Tupla)tuplaAtualEsq);
			}
			
		}
		
		tuplaAtualDir = relacaoDir.next();
		
		
		if(tuplaAtualDir == null) // caso esteja parado na ultima linha da dir, isso será vdd e atualiza-se as duas tuplas
		{
			tuplaAtualEsq = relacaoEsq.next();
			if(tuplaAtualEsq != null)
			{
				if(!((Tupla)tuplaAtualEsq).getColunaTupla(indiceValorjuncao).equals(valorJuncao))
					this.alterarValorJuncao((Tupla)tuplaAtualEsq);
			}
			tuplaAtualDir = relacaoDir.next();
		}
		
		if(tuplaAtualEsq == null) // se isso acontecer, após o código acima então chegou ao fim das duas relações
		{
			
			tuplaAtualEsq = relacaoEsq.next();
			tuplaAtualDir = relacaoDir.next();
			if(tuplaAtualEsq != null)
			{
				if(!((Tupla)tuplaAtualEsq).getColunaTupla(indiceValorjuncao).equals(valorJuncao))
				this.alterarValorJuncao((Tupla)tuplaAtualEsq);
			}
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
		int indiceTuplaEsq = -1;
		int indiceTuplaDir = -1;
		while(achou == false)// a principio n achou
		{
			if(tuplaEsq == null) //se isso acontece chegou no fim da relacao esq entao volta ao inicio e continua
			{
				tuplaAtualEsq = relacaoEsq.next();
				if(tuplaAtualEsq != null)
				{
					if(!((Tupla)tuplaAtualEsq).getColunaTupla(indiceValorjuncao).equals(valorJuncao))
						this.alterarValorJuncao((Tupla)tuplaAtualEsq);
				}
				if(tuplaAtualDir == null)
					tuplaAtualDir = relacaoDir.next();
				return null;
			}
			indiceTuplaEsq = this.existeCampo(tuplaEsq);
			if(indiceTuplaEsq>=0) //olhar os comentários no método
			{
				while(tuplaDir != null) // se existe, procura um equivalente na direita ate o fim da relação
				{
					indiceTuplaDir = this.existeCampo(tuplaDir);
					if(indiceTuplaDir>=0) // olhar os comentários no método
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
					if(tuplaAtualEsq != null)
					{
						if(!((Tupla)tuplaAtualEsq).getColunaTupla(indiceValorjuncao).equals(valorJuncao))
						this.alterarValorJuncao((Tupla)tuplaAtualEsq);
					}
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
				if(tuplaAtualEsq != null)
				{
					if(!((Tupla)tuplaAtualEsq).getColunaTupla(indiceValorjuncao).equals(valorJuncao))
					this.alterarValorJuncao((Tupla)tuplaAtualEsq);
				}
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
			colEsq.renomearColunaJuncao();
			//System.out.println(colEsq.getNomeComposto());
			retorno.associarColunaTupla(colEsq);
			colEsq = (ColunaTupla) tuplaEsq.next();
		}
		
		tuplaDir.open();
		ColunaTupla colDir = (ColunaTupla) tuplaDir.next();
		while(colDir!=null) // pegar todas as tuplas da direita
		{
			colDir.renomearColunaJuncao();
			//System.out.println(colDir.getNomeComposto());
			retorno.associarColunaTupla(colDir);
			colDir = (ColunaTupla) tuplaDir.next();
		}
		
		return retorno;	
		
		// TODO Auto-generated method stub
	}

	@Override
	public Interpreter close() {
		// TODO Auto-generated method stub
		relacaoEsq.close();
		relacaoDir.close();
		return null;
	}
	
	private int existeCampo(Tupla t) 
	{
		t.open();
		ColunaTupla c = (ColunaTupla)t.next(); 
		int indiceColTupla = 0;
		while(c != null)
		{
			if(c.getNome().equals(campoJuncao))
					return indiceColTupla;
			else
			{
				c = (ColunaTupla)t.next();
				indiceColTupla++;
			}
		}
		return -1;
	}
	
	private void alterarValorJuncao(Tupla t)
	{
		t.open();
		ColunaTupla c = (ColunaTupla) t.next();
		while(c != null)
		{
			if(c.getNome().equals(campoJuncao))
			{
				
				valorJuncao = c.getValor();
				System.out.println("pegou valor do campo na direita no open");
			}
			c = (ColunaTupla)tuplaAtualEsq.next();
		}
		if(relacaoDir instanceof Selecao)
			((Selecao)relacaoDir).getValorBusca().setValor(valorJuncao);
	}
}
