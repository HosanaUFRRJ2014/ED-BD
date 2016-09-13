package teste;

import static org.junit.Assert.*;

import org.junit.Test;

import entity.*;

public class TabelaTest 
{

	@Test
	public void testTabela() 
	{
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testAdicionarElemento() 
	{
		//fail("Not yet implemented"); // 
		String string = "Maria";
		Tabela t = new Tabela(10);
		boolean booleano = t.adicionarElemento(string, 0);
		
		assertTrue("a chave não foi adicionada",booleano);
	}

	@Test
	public void testBuscarChaveRetornaChave() 
	{
		//fail("Not yet implemented");
		
		Chave c;// = new Chave();
		Tabela t = new Tabela(10);
		
		String string = "Maria";
		
		t.adicionarElemento(string, 0);
		
		c = t.buscarChaveRetornaChave(string);
        String stringEncontrada = c.getString();
		
		assertSame(stringEncontrada,string);
		
	//	assertNotNull("A chave não foi encontrada",c);
	}

	@Test
	public void testBuscarChave()
	{
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testBuscarChaveRetornaPosicaoArquivo()
	{
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetHashTable()
	{
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testSetHashTable()
	{
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetTamanho() 
	{
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testSetTamanho() 
	{
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testImprimirTabela()
	{
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetCont()
	{
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testSetCont()
	{
		fail("Not yet implemented"); // TODO
	}

}
