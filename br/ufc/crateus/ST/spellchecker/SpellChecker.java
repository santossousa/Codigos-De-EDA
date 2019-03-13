package br.ufc.crateus.ST.spellchecker;
import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

import br.ufc.crateus.ST.LinearProbingHashST;
import br.ufc.crateus.ST.SeparateChaininghST;

public class SpellChecker {
	
	public static void main(String[] agrs) throws IOException {
		SpellChecker p = new SpellChecker();
		//p.putWord("Joao da silva");
		//p.check("test");
		//System.out.println(p.sugestoes("impres"));
		
	}
	

	

	SeparateChaininghST<String, String> dictionary ;
	boolean suggestWord;
	public SpellChecker() {
		dictionary = new SeparateChaininghST(16 * 1024);
		try {

			InputStream file = new FileInputStream("/home/fabio/eclipse-workspace/bag/src/br/ufc/crateus/ST/spellchecker/palavrasErradas");
			BufferedReader buff2 = new BufferedReader(new InputStreamReader(file,Charset.forName("UTF-8")));
			Dictionary d = new Dictionary();
			d.create();
			while(buff2.ready()) {
				String str = buff2.readLine();
				String[] vect = str.split("\\s");
				for(int i = 0; i < vect.length;i++) {
					String wordout = checkWord(vect[i]);
					System.out.println("palavra errada : ->"+vect[i]+"---"+"palavra certa : "+d.corret(wordout)+"\n");
				}
				
			}
			
			
		
		
		}
		catch(IOException e) {
			e.getMessage();
		}
	}
	public void check(String sd) {		
	Dictionary d = new Dictionary();
	d.create();
	d.erro(sd);
		
	}
	public void putWord(String word) throws IOException {
		Dictionary d = new Dictionary();
		d.create();
		d.addWord(word);
		
	}
	public String sugestoes(String file) throws FileNotFoundException {
		
		Dictionary n = new Dictionary();
		n.create();
		String AUX = null;
		String aux2 = null;
		
			//BufferedReader b = new BufferedReader(new FileReader(file));
			
				String[] vect = file.split("\\s");
				for(int i = 0;i < vect.length;i++) {
					String out = checkWord(vect[i]);
					if(true) {
						System.out.println("palavra : "+vect[i]+", palavra sugerida ---> "+n.corret(out)+"\n");
						AUX = n.corret(out);
						aux2 = vect[i];
					}
					
					
				}
				System.out.println("Deseja subistuir ?");
				Scanner in = new Scanner(System.in);
				String opc = in.nextLine();
				if(opc.equals("sim") || opc.equals("Sim"))return AUX;
				
				return aux2;
				
			
		
	}
	//checar a String passada
	 public String checkWord(String wordToCheck) 
	    {
	        String wordCheck, unpunctWord;
	        String word = wordToCheck.toLowerCase();
	        
	        
	        if ((wordCheck = (String)dictionary.get(word)) != null)
	        {
	            suggestWord = false;            
	            return wordCheck;
	        }
	        
	          int length = word.length();
	        
	 
	         
	        if (length > 1 && word.substring(0,1).equals("\"")) 
	        {
	            unpunctWord = word.substring(1, length);
	            
	            if ((wordCheck = (String)dictionary.get(unpunctWord)) != null)
	            {
	                suggestWord = false;          
	                return wordCheck ;
	            }
	            else 
	            	return unpunctWord;                  
	        }
	 
	        if( word.substring(length - 1).equals(".")  || word.substring(length - 1).equals(",") ||  word.substring(length - 1).equals("!")
	        ||  word.substring(length - 1).equals(";") || word.substring(length - 1).equals(":"))
	        {
	            unpunctWord = word.substring(0, length-1);
	            
	            if ((wordCheck = (String)dictionary.get(unpunctWord)) != null)
	            {
	                suggestWord = false;            
	                return wordCheck;
	            }
	            else
	            {
	                return unpunctWord;                  
	            }
	        }

	        if (length > 2 && word.substring(length-2).equals(",\"")  || word.substring(length-2).equals(".\"") 
	            || word.substring(length-2).equals("?\"") || word.substring(length-2).equals("!\"") )
	        {
	            unpunctWord = word.substring(0, length-2);
	            
	            if ((wordCheck = (String)dictionary.get(unpunctWord)) != null)
	            {
	                suggestWord = false;            
	             return wordCheck ;
	            }
	            else 
	                return unpunctWord;                  
	        }
	        
	        
	        
	        return word;
	    }
	    
	



}