package entity;
import java.util.LinkedList;
import java.util.regex.Pattern;

import controller.*;

public class Shell 
{
	public static LinkedList<Par> verificarBalanceamento(String s)
	{
		int i = 0, j = 0, k = 0;
		
		char schar [] = s.toCharArray();
		Par p;
		IndiceParentesesStringComando parenteses = new IndiceParentesesStringComando();
		
		while(i<schar.length)
		{
			if(schar[i] == '(')
			{
				k++;
				p = new Par(i);
				parenteses.addParentesesAberto(p, i);
			}
			else if(schar[i] == ')')
			{
					k--;
					p = parenteses.removerUltimoAberto();
					p.setIndiceFechado(i);
					parenteses.addParentesesFechado(p, p.getIndiceAberto());
			}
			
			if(k<0)
				return null;
			
			i++;
		}
		
		if(k == 0)
			return parenteses.getParFechados();
		
		return null;
	}
	public static Interpreter lerString(String s)
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
			String sarray [] = s.trim().split(Pattern.quote("("));
			String val = sarray[0].trim();
			val.toLowerCase();
			
			switch(val)
			{
				case "produtocartesiano":
					break;
				case "renomeacao"://ideia no txt de comandos
					break;
				case "buscasequencial":
					break;
				case "buscaarvoreb":
					break;
				case "buscahash":
					break;
				case "hashjoin":
					break;
				case "ordenacao":
					break;
				case "juncaonatural":
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
					
					relacaoDir = lerString(s1);
					relacaoEsq = lerString(s2);
					if(relacaoDir != null && relacaoEsq != null)
						return new JuncaoNatural(relacaoEsq, relacaoDir, nomeCampo,valorCampo);
					
					return null;
					
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
					
					relacaoDir = lerString(s1);
					relacaoEsq = lerString(s2);
					if(relacaoDir != null && relacaoEsq != null)
						return new NLJ(relacaoEsq, relacaoDir, nomeCampo,valorCampo);
					
					return null;
					
				case "projecao": //projetar("campo1 campo2...", relacao())
					
					parametros = sarray[1].split(",");
					campos = parametros[0].replace('"', ' ').trim().split(" ");
					
					
					//remove o parenteses fechado correspondente a essa operacao (testar)
					temp = s.toCharArray();
					temp[pares.get(s.indexOf(val)+ val.length()).getIndiceFechado()] = ' ';
					s = String.valueOf(temp).trim();
							
					sProximo = s.trim().substring(s.trim().indexOf(parametros[1].trim()));
					relacao = lerString(sProximo);
					if(relacao != null)
						return new Projecao(campos,campos.length,relacao);
					
					return null;
					
				case "selecao": //selecao("campo = valor", relacao(<parametros>))
					parametros = sarray[1].trim().split(",");
					aux = parametros[0].replace('"', ' ').trim().split("=");
					nomeCampo = aux[0].trim();
					valorCampo = aux[1].trim();
					
					temp = s.toCharArray();
					temp[pares.get(s.indexOf(val)+ val.length()).getIndiceFechado()] = ' ';
					s = String.valueOf(temp).trim();
					
					sProximo = s.substring(s.indexOf(parametros[1].trim()));
					
					relacao = lerString(sProximo);
					
					if(relacao != null)
						return new Selecao(nomeCampo, valorCampo, relacao);
					
					return null;

					//             0       1                   
				case "uniao": //uniao(relacaoDir(<parametros>),relacaoEsq(<parametros>))
					s1 = sarray[1].trim();
					indiceAS1 = s.indexOf(s1) + s1.length();
					indiceFS1 = pares.get(indiceAS1).getIndiceFechado();
					s1 += s.substring(indiceAS1, indiceFS1+1);//inclui o inicial e exclui o final
					indiceIS2 = indiceFS1+2;
					
					
					temp = s.toCharArray();
					temp[pares.get(s.indexOf(val)+val.length()).getIndiceFechado()] = ' ';
					s = String.valueOf(temp).trim();
					
					s2 = s.substring(indiceIS2).trim();

					relacaoDir = lerString(s1);
					relacaoEsq = lerString(s2);
					if(relacaoDir != null && relacaoEsq != null)
						return new Uniao(relacaoDir, relacaoEsq);
					
					return null;
									// identico ao anterior
				case "diferenca": //diferenca(relacaoDir(<parametros>),relacaoEsq(<parametros>))
					s1 = sarray[1].trim();
					indiceAS1 = s.indexOf(s1) + s1.length();
					indiceFS1 = pares.get(indiceAS1).getIndiceFechado();
					s1 += s.substring(indiceAS1, indiceFS1+1);//inclui o inicial e exclui o final
					indiceIS2 = indiceFS1+2;
					
					
					temp = s.toCharArray();
					temp[pares.get(s.indexOf(val)+val.length()).getIndiceFechado()] = ' ';
					s = String.valueOf(temp).trim();
					
					s2 = s.substring(indiceIS2).trim();

					relacaoDir = lerString(s1);
					relacaoEsq = lerString(s2);
					if(relacaoDir != null && relacaoEsq != null)
						return new Diferenca(relacaoDir, relacaoEsq);
					
					return null;
									//identico ao anterior
				case "intesecao": //intersacao(relacaoDir(<parametros>),relacaoEsq(<parametros>))
					s1 = sarray[1].trim();
					indiceAS1 = s.indexOf(s1) + s1.length();
					indiceFS1 = pares.get(indiceAS1).getIndiceFechado();
					s1 += s.substring(indiceAS1, indiceFS1+1);//inclui o inicial e exclui o final
					indiceIS2 = indiceFS1+2;
					
					
					temp = s.toCharArray();
					temp[pares.get(s.indexOf(val)+val.length()).getIndiceFechado()] = ' ';
					s = String.valueOf(temp).trim();
					
					s2 = s.substring(indiceIS2).trim();

					relacaoDir = lerString(s1);
					relacaoEsq = lerString(s2);
					if(relacaoDir != null && relacaoEsq != null)
						return new Interseccao(relacaoDir, relacaoEsq);

					return null;
			}
		}
		return null;
	}
}
