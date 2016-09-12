package entity;

import java.io.IOException;
import java.util.Scanner;

import controller.*;

public class PrincipalApresentacao 
{
	public static void main(String[] args) throws IOException, ClassNotFoundException
	{
		Scanner s= new Scanner(System.in);
		int opcao = 0;

		/*
		do
		{
			
			System.out.println("------Escolha uma das opções de busca abaixo:------");
			System.out.println();
			System.out.println("Digite 1 - Os livros escritos por um autor");
			System.out.println("Digite 2 - O telefone de uma editora");
			System.out.println("Digite 3 - O nome do autor e o nome da editora de um livro");
			System.out.println("Digite 4 - Todas as informações do autor");
			System.out.println("Digite 5 - Todas as informações de uma editora");
			System.out.println("Digite 6 - Sair");

			opcao = Integer.parseInt(s.nextLine());

			switch(opcao)
			{
			    case 1:
			    {
			    	System.out.println("Digite o nome do autor desejado");
			    	String nomeAutor = s.nextLine();
			    	String [] vetQ1 = {"isbnlivroBinario.data","títulolivroBinario.data", "id_autorlivroBinario.data", "id_editoralivroBinario.data"};
			    	Expressao e1 = new Expressao(new Projecao(vetQ1, 4, 
																new HashJoin( new SelecaoPorEstrutura("nome", nomeAutor, null,
																										new ArquivoHashTable("arquivoHashNomeArquivoAutor.data", (byte)1), 1000,
																										new ArquivoAutor("autorBinario.data", (byte)1)
																										),
																				new SelecaoPorEstrutura("id_autor", "", null,
																										new ArquivoHashTable("arquivoHashIdAutorArquivoLivro.data", (byte)1), 1000,
																										new ArquivoLivro("livroBinario.data", (byte)1)
																										),
																				"id_autor"
																				)
			    												)
			    									);
			    	
			    	e1.Executar();
			    	e1.imprimirLista();

				     break;
			    }
			
			    case 2:
			    {
			    	 System.out.println("Digite o nome da editora desejada");
			    	 String nomeEditora = s.nextLine();
			    	 
			    	 String [] vetQ2 = {"telefone"};

			 		 Expressao e2 = new Expressao(new Projecao(vetQ2, 1, 
			 														new Selecao("nome",nomeEditora, 
			 																		new ArquivoEditora("editoraBinario.data",(byte)1)
			 																	)
			 												)
			 									);
			 		
			 		  e2.Executar();
			 		  e2.imprimirLista();
				

				     break;
			     }
			
			     case 3:
			     {
			    	 
				

				     break;
			     }
			
			     case 4:
			     {
			    	 System.out.println("Digite o id do autor desejado");
			    	 String id = s.nextLine();
			    	 
			    	 System.out.println("Realizando busca sequencial");
			    	 Expressao e4_0 = new Expressao(new Selecao("id_autor", id, 
			 														new ArquivoAutor("autorBinario.data",(byte)1)
			    			 									)
			 										);
			    	 
			    	 e4_0.Executar();
			    	 e4_0.imprimirLista();
			    	 
			    	 System.out.println("Realizando busca binária");
			    	 Expressao e4 = new Expressao(new SelecaoPorEstrutura("id_autor", id, null, 
																			new ArquivoHashTable("arquivoHashIdArquivoAutor.data", (byte)1), 1000, 
																			new ArquivoAutor("autorBinario.data", (byte)1)
			    			 												)
			    			 						);
			    	 
			    	 e4.Executar();
			    	 e4.imprimirLista();

				     break;
			     }
			
			     case 5:
			     {
			    	 System.out.println("Digite o id da editora desejada");
			    	 String id = s.nextLine();
			    	 Expressao e5 = new Expressao(new Selecao("id_editora",id, 
																new ArquivoEditora("editoraBinario.data",(byte)1)
			    			 									)
			    			 						);
			    	 
			    	 e5.Executar();
			    	 e5.imprimirLista();

				     break;
			    }
			
			    case 6:
			    {
				     opcao = 6;

				     break;
			    }
			
			    default:
			         System.out.println("Opção inválida");
			    }
			
			System.out.println();

		}while(opcao != 6);
*/
	}
}
