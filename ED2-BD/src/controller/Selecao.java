package controller;

public class Selecao implements Interpreter 
{
	protected ColunaTupla valorBusca;//valor que deseja ser buscado com nome da coluna
	public ColunaTupla getValorBusca() {
		return valorBusca;
	}

	public void setValorBusca(ColunaTupla valorBusca) {
		this.valorBusca = valorBusca;
	}

	protected Interpreter relacao;//operador ou arquivo
	
	public Selecao(String campo, String valor, Interpreter relacao)
	{
		super();
		valorBusca = new ColunaTupla(campo, valor, "", -1);
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
		
		Tupla t = null;
		Interpreter aux = relacao.next();
		while(aux != null)
		{
			boolean temCampoBuscado = false;
			if(aux instanceof Tupla)
			{
				t = (Tupla) aux;
				t.open();
				ColunaTupla c = (ColunaTupla) t.next();
				//pode ser substituido pelo indice da lista se o catalogo funcionar e tiver o indice da coluna;
				while(c!=null)
				{
					if(c.getNome().equals(valorBusca.getNome()))
					{
						temCampoBuscado = true;
						//System.out.println(c.getValor() + " == " + valorBusca.getValor());
						if(c.getValor().equals(valorBusca.getValor()))
						{
							//System.out.println("achou");
							return t;
						}
						else
							break;
					}
					c = (ColunaTupla) t.next();
				}
			}
			else
			{
				System.out.println("nao é tupla na selecao");
				return null;
			}
			if(temCampoBuscado == false)
				return null;
			
			aux = relacao.next();
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
