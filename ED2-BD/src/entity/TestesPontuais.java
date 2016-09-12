package entity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Pattern;

import controller.ArquivoDado;
import controller.ColunaTupla;
import controller.Interpreter;
import controller.NLJ;
import controller.Projecao;
import controller.Selecao;
import controller.Uniao;

public class TestesPontuais 
{
	
	//projecao("campo1 campo2 campo3", relacao(<parametros>))
	//uniao(projecao("campo1 campo2 campo3", relacao(<parametros>)), projecao("campo1 campo2 campo3", relacao(<parametros>)))
	//selecao("campo = valor", relacao(<parametros>))
	//nlj("campoJuncao = valorCampoJuncao", projecao1("campo1 campo2 campo3", relacao(<parametros>)), projecao2("campo1 campo2 campo3", relacao(<parametros>)))
	
	
	//teste para recursividade
	//selecao("campo = valor", projecao("campo1 campo2 campo3", uniao(projecao("campo1 campo2 campo3", relacao(<parametros>)), projecao("campo1 campo2 campo3", relacao(<parametros>)))))
	
	public static void testesArquivo() throws IOException
	{
		//testar com diretorio
		ArquivoDado ad = new ArquivoDado("/home/aluno/editora.txt","pseudonimo",0); //abre o .data para escrita
		
		long pos = ad.lerCabecalhoDoArquivo();
	
		
		/*LinkedList<ColunaTupla>  c = ad.lerLinhaArquivo(pos);
		
		for(int i = 0; i < c.size(); i++)
			System.out.println(c.get(i).getNome() + ": " + c.get(i).getValor());*/
		
		
 	}
	
	public static void testeFuncaoBalanceado(String s)
	{
		LinkedList<Par> indice = Shell.verificarBalanceamento(s);

		if(indice != null)
		{
			System.out.println("INDICES :");
			
			for(int i = 0; i < indice.size();i++)
			{
				if(indice.get(i) != null)
				{
					System.out.println(i + " = (" + indice.get(i).getIndiceAberto() + "," + indice.get(i).getIndiceFechado() + ")");
					System.out.println();
				}
			}
		}
		else
		{
			System.out.println("não está balanceada");
		}
		
		return;
	}
	
	public static void testeShell(String s)
	{
		LinkedList<Par> pares = Shell.verificarBalanceamento(s);
		char [] temp;
		String s1;
		String s2;
		int indiceAS1;
		int indiceFS1;
		int indiceIS2;
		Interpreter relacao;
		Interpreter relacao1;
		Interpreter relacao2;
		String [] parametros;
		String [] campos;
		String [] valores;
		String [] aux;
		String nomeCampo;
		String valorCampo;
		String sProximo;
		Interpreter relacaoEsq;
		Interpreter relacaoDir;
		
		if(pares!=null)
		{
			String [] sarray = s.trim().split(Pattern.quote("("));
			String val = sarray[0].trim();
			System.out.println(val);
			switch(val)
			{
				case "hashjoin":
					break;
				case "juncaoNatural":
					
					parametros = sarray[1].split(",");
					aux = parametros[0].replace('"', ' ').trim().split("=");
					nomeCampo = aux[0].trim();
					valorCampo = aux[1].trim();
					
					s1 = parametros[1].trim();
					indiceAS1 = s.indexOf(s1) + s1.length();
					indiceFS1 = pares.get(indiceAS1).getIndiceFechado();
					s1 += s.substring(indiceAS1, indiceFS1+1);//inclui o inicial e exclui o final
					indiceIS2 = indiceFS1+2;
					
					
					temp = s.toCharArray();
					temp[pares.get(s.indexOf(val)+val.length()).getIndiceFechado()] = ' ';
					s = String.valueOf(temp).trim();
					
					s2 = s.substring(indiceIS2).trim();
					
					System.out.println(nomeCampo);
					System.out.println(valorCampo);
					System.out.println("S1 : " + s1);
					System.out.println("S2 : " + s2);
					
					break;
				case "nlj": //nlj("campoJuncao = valorCampoJuncao", relacaoDir(<parametros>), relacaoEsq(<parametros>))
					parametros = sarray[1].split(",");
					aux = parametros[0].replace('"', ' ').trim().split("=");
					nomeCampo = aux[0].trim();
					valorCampo = aux[1].trim();
					
					s1 = parametros[1].trim();
					indiceAS1 = s.indexOf(s1) + s1.length();
					indiceFS1 = pares.get(indiceAS1).getIndiceFechado();
					s1 += s.substring(indiceAS1, indiceFS1+1);//inclui o inicial e exclui o final
					indiceIS2 = indiceFS1+2;
					
					
					temp = s.toCharArray();
					temp[pares.get(s.indexOf(val)+val.length()).getIndiceFechado()] = ' ';
					s = String.valueOf(temp).trim();
					
					s2 = s.substring(indiceIS2).trim();
					
					System.out.println(nomeCampo);
					System.out.println(valorCampo);
					System.out.println("S1 : " + s1);
					System.out.println("S2 : " + s2);
					
					/*relacaoDir = lerString(s1);
					relacaoEsq = lerString(s2);
					if(relacaoDir != null && relacaoEsq != null)
						return new NLJ(relacaoEsq, relacaoDir, nomeCampo,valorCampo);
					
					return null;*/
					break;
				case "projecao": //projetar("campo1 campo2...", relacao())
					
					parametros = sarray[1].split(",");
					campos = parametros[0].replace('"', ' ').trim().split(" ");
					
					System.out.println("CAMPOS:");
					for(int i=0;i<campos.length;i++)
					{
						//campos[i] = campos[i].trim();
						System.out.print(campos[i] + "\t");
					}
					
					System.out.println();
					System.out.println();
					
					//remove o parenteses fechado correspondente a essa operacao (testar)
					System.out.println("S : " + s);
					temp = s.toCharArray();
					
					System.out.println(s.indexOf(val)+ val.length());
					System.out.println(pares.get(s.indexOf(val)+ val.length()).getIndiceFechado());
					
					temp[pares.get(s.indexOf(val)+ val.length()).getIndiceFechado()] = ' ';
					System.out.println(temp);
					s = String.valueOf(temp).trim();
					System.out.println("nS : " + s);
					
					sProximo = s.trim().substring(s.trim().indexOf(parametros[1].trim()));
					System.out.println("sProximo : " + sProximo);

					//Interpreter relacao = lerString(sProximo);
					/*if(relacao != null)
						return new Projecao(campos,campos.length,relacao);*/
					
					break;
					//             0       1                   
				case "uniao": //uniao(relacaoDir(<parametros>),relacaoEsq(<parametros>))
					s1 = sarray[1].trim();
					System.out.println(s1);
					indiceAS1 = s.indexOf(s1) + s1.length();
					System.out.println(indiceAS1);		
					indiceFS1 = pares.get(indiceAS1).getIndiceFechado();//------
					System.out.println(indiceFS1);
					s1 += s.substring(indiceAS1, indiceFS1+1);//inclui o inicial e exclui o final
					System.out.println(s1);
					indiceIS2 = indiceFS1+2;
					System.out.println(indiceIS2);
					
					temp = s.toCharArray();
					temp[pares.get(s.indexOf(val)+val.length()).getIndiceFechado()] = ' ';
					s = String.valueOf(temp).trim();
					System.out.println("S : " + s);
					
					s2 = s.substring(indiceIS2).trim();
					System.out.println("S2 : " + s2);
					
					//Interpreter relacaoDir = lerString(s1);
					//Interpreter relacaoEsq = lerString(s2);
					/*if(relacaoDir != null && relacaoEsq != null)
						return new Uniao(relacaoDir, relacaoEsq);*/
					break;
					
				case "selecao": //selecao("campo = valor", relacao(<parametros>))
					parametros = sarray[1].trim().split(",");
					aux = parametros[0].replace('"', ' ').trim().split("=");
					nomeCampo = aux[0].trim();
					valorCampo = aux[1].trim();
					
					temp = s.toCharArray();
					temp[pares.get(s.indexOf(val)+ val.length()).getIndiceFechado()] = ' ';
					s = String.valueOf(temp).trim();
					
					sProximo = s.substring(s.indexOf(parametros[1].trim()));
					System.out.println(nomeCampo);
					System.out.println(valorCampo);
					System.out.println(sProximo);
					//relacao = lerString(sProximo);
					
					/*if(relacao != null)
						return new Selecao(nomeCampo, valorCampo, relacao);
					
					return null;*/
					break;
				default:
					break;
						
			}
		}
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException
	{/*
		Scanner leitor = new Scanner(System.in);
		String s = leitor.nextLine();
		testeFuncaoBalanceado(s);
		testeShell(s);*/
		testesArquivo();
		
	}
}
