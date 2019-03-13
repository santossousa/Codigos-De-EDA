package br.ufc.crateus.ST;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.plaf.basic.BasicTreeUI.TreeCancelEditingAction;

import br.ufc.crateus.ST.Tst;

public class App {

	

	public static void main(String[] args) throws IOException {

		Tst<Integer> trieOrdered = new Tst();
		//trieOrdered.put("aksas", 2);
		//System.out.println(trieOrdered.contains("aksas"));
		//System.out.println(trieOrdered.get("aksas"));
		
		int index;
    	String str;
    	String line;
    	int i = 1;
		       
		try {
        FileReader file = new FileReader("/home/fabio/eclipse-workspace/bag/src/br/ufc/crateus/ST/arquivo movel.txt");
        BufferedReader buff = new BufferedReader(file);
        
        while(buff.ready()) {
        	
        	
        	line = buff.readLine();
        	//System.out.println(str);
        	
        	index = line.indexOf(" (");
        	i++;
        	
        	
        	 str = line.substring(0, index);
        	
        	 
       
        	
        
        	str = realceString(str);
        	
        	// System.out.println(str);
        	 trieOrdered.put(str, i);

        		 
        
        	
        	        	//System.out.println(str);

        	
    		
   
        }
        	buff.close();
        
        	
        	//String d = trieOrdered.longestPrefixOf("ro");
        	//System.out.println(d);
        	
		}
		
		catch(IOException e) {
			System.out.println(e.getMessage());
		}
		prefixComTitulos(trieOrdered);
		prefixoMaislongo(trieOrdered);
		prefixoCoringa(trieOrdered);
		
    	
    	
		

        
        
        

	}
	/*nos teste que foram feito obtive alguns resultados porem, 
	 * o prefix mais logo retornou o resultado associado a ele,
	 * o com caractere coringa levou ao filme especifico com os caractere coriga, 
	 * , mas o keysWithprefix conseguir listar os resultados dos filmes 
	 * que comecaom com determinado prefixo
	 * */
	public static String realceString(String str) {
		String setr = "";
		int i = 0;
		for(int j = 0 ;j < str.length(); j++) {
			i = str.charAt(j);
			if((0 <= i && i <= 47) && (32 != i && i != 46)) setr += "?";
			else if(58  <= i && i <= 64 )setr += "?";
			else if(65 <= i && i <= 90) setr += str.charAt(j);
			else if(91 <= i && i <= 96) setr += "?";
			else if(123 <= i && i <= 191) setr += "?";
			else if(i > 266) setr += "?";
			else setr += str.charAt(j);
			
		}
		setr = setr.toLowerCase();//ignora os maiuculos de minusculas 
		
		setr = setr.replaceAll("[ã,â,à,á,ä]","a")//caracter com acentos levado a um primitvo sem nada
	        	.replaceAll("[ê,è,é,ë]", "e")
	        	.replaceAll("[î,ì,í,ï]", "i")
	        	.replaceAll("[õ,ô,ò,ó,ö]", "o")
	        	.replaceAll("[û,ú,ù,ü]", "u")
	        	.replaceAll("Ã,Â,À,Á,Ä", "A")
	        	.replaceAll("[Ê,È,É,Ë]", "E")
	        	.replaceAll("Î,Ì,Í,Ï", "I")
	        	.replaceAll("Õ,Ô,Ò,Ó,Ö", "O")
	        	.replaceAll("[Û,Ù,Ú,Ü]", "U")
	        	.replace('ç','c')
	        	.replace('Ç', 'C')
	        	.replace('ñ', 'n')
	        	.replace('Ñ', 'n');
		return setr;
	}
	public static void prefixoMaislongo(Tst t) {
		System.out.println("----------------------------------");
		System.out.println("   [ Titulo com prefix mais longo ]  ");
		System.out.println("-----------------------------------");
		System.out.println("\t"+t.longestPrefixOf("a quelques jours pres"));
		System.out.println("-----------------------------------");
		
		
	}
	public static void prefixComTitulos(Tst<Integer> t) {
	System.out.println("-----------------------------------");
	System.out.println("   [ Titulos com prefix especifico ]  ");
	System.out.println("-----------------------------------");
		for(String k : t.keysWithPrefix("ro")){
    		System.out.println("\t"+k);
    	}
	System.out.println("-----------------------------------");
	}
	public static void prefixoCoringa(Tst<Integer> t) {
		System.out.println("-------------------------------------");
		System.out.println("   [ Titulo com caracter coringa ]    ");
		System.out.println("-------------------------------------");
		for(String k : t.keysThatMatch("z.p.g.")) {
    		
	    	  System.out.println("\t"+k);
	    		
	    	}
		System.out.println("-------------------------------------");
		
	}
	

}
