package entity;

public class Par 
{
	//PARAMETROS----------------------------------
	int indiceAberto;
	int indiceFechado;
	
	//CONSTRUTORES--------------------------------
	public Par()
	{
		indiceAberto = -1;
		indiceFechado = -1;
	}
	
	public Par(int aberto)
	{
		indiceAberto = aberto;
	}
	
	public Par(int aberto, int fechado)
	{
		indiceAberto = aberto;
		indiceFechado = fechado;
	}
	//GETERS AND SETERS-------------------------------------
	
	public int getIndiceAberto() {
		return indiceAberto;
	}

	public int getIndiceFechado() {
		return indiceFechado;
	}
	
	public void setIndiceAberto(int indiceAberto) {
		this.indiceAberto = indiceAberto;
	}

	public void setIndiceFechado(int indiceFechado) {
		this.indiceFechado = indiceFechado;
	}
	
	//VERIFICADORES-------------------------------------------------
	
	public boolean temIndiceAberto()
	{
		if(this.getIndiceAberto()>0)
			return true;
		return false;
	}

	public boolean temIndiceFechado()
	{
		if(this.getIndiceFechado()>0)
			return true;
		return false;
	}
	
	public boolean ehParPronto()
	{
		if(temIndiceAberto() && temIndiceFechado())
			return true;
		else
			return false;
	}
}
