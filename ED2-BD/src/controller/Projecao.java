package controller;


public class Projecao implements Interpreter 
{
	private String[] campos;
	private Interpreter relacao;
	private int qtdCampos;
	
	public Projecao(String[] campos, int n, Interpreter relacao) {
		super();
		this.campos = campos.clone();// equivale ao abaixo?
		/*for(int i = 0;i < n;i++)
			this.campos[i] = campos[i];*/
		qtdCampos = n;
		this.relacao = relacao;
	}
	@Override
	public Interpreter open() 
	{
		return relacao.open();
		// TODO Auto-generated method stub
	}

	@Override
	public Interpreter next() 
	{
		Interpreter aux = relacao.next();
		if(aux == null)
			return null;
		
		boolean temCampoBuscado[] = new boolean[qtdCampos];
				
		Tupla tOriginal = null;
		Tupla tFinal = new Tupla();
		if(aux instanceof Tupla)
		{
			tOriginal = (Tupla) aux;
			tOriginal.open(); // volta o indicador para a primeira colunaTupla
			
			for(int i = 0; i < this.qtdCampos;i++) // para cada campo da projecao procura seu equivalente na tupla
			{
				ColunaTupla c = (ColunaTupla) tOriginal.next();
				temCampoBuscado[i] = false;
				String nomeCampo = "";
				while(c!=null)
				{
					if((relacao instanceof HashJoin) || (relacao instanceof NLJ))
					{
						if(c.getNomeComposto().startsWith(campos[i]))
						{
							temCampoBuscado[i] = true;
							tFinal.associarColunaTupla(c);
							break;
						}
					}	
					else
					{
					
						if(c.getNome().equals(campos[i]))
						{
							temCampoBuscado[i] = true;
							tFinal.associarColunaTupla(c);
							break;
						} // qnd retorna null zera o indicador de colunaTupla
					}
						c = (ColunaTupla) tOriginal.next();
				}
				if(temCampoBuscado[i] == false) // apenas para testes
				{
					System.out.println("campo " + campos[i] + "não encontrado na tupla de projeção");
					return null;
				}
				tOriginal.close(); // volta o indicador para a primeira colunaTupla
			}
			return tFinal;
			
		}
		else // apenas para testes
		{
			System.out.println("não é tupla na projecao");
		}
			
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Interpreter close()
	{
		return relacao.close();
		// TODO Auto-generated method stub
	}

}
