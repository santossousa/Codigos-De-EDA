package br.ufc.crateus.ST.spellchecker;

import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.*;


import br.ufc.crateus.ST.LinearProbingHashST;
import br.ufc.crateus.ST.SeparateChaininghST;

public class Dictionary {
	SeparateChaininghST<String, Integer> dictionary;
	LinkedList<String> liste; 
	
	private void putWord(String word) {
		Pattern p = Pattern.compile("\\w+");
		Matcher m = p.matcher(word.toLowerCase());
		
		while(m.find()) {
			dictionary.put((word = m.group()), dictionary.contains(word) ? dictionary.get(word) + 1 : 1);
		}
		 
		liste.put(word);
		
	}
	
	
	
	public SeparateChaininghST<String, Integer> create() {
		dictionary = new SeparateChaininghST<>(16 * 1024);
		liste = new LinkedList<>();
		try {
			
			InputStream file = new FileInputStream("/home/fabio/eclipse-workspace/bag/src/br/ufc/crateus/ST/spellchecker/mendicionario");
			BufferedReader dictReader = new BufferedReader(new InputStreamReader(file,Charset.forName("UTF-8")));
			Pattern p = Pattern.compile("\\w+");
			while(dictReader.ready()) {
				String st = dictReader.readLine();
				Matcher m = p.matcher(st.toLowerCase());
				while(m.find()) {
					dictionary.put((st = m.group()) ,dictionary.contains(st)? dictionary.get(st) + 1 :1 );
					
				}
				
				//liste.put(st);
			}
			dictReader.close();
			
			
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
			
			
		}
		/*for(String k : dictionary.keys()) {
			System.out.println(k+","+dictionary.get(k));
		}*/
		
		
		
		return dictionary;
		
	}
	int tam(char[] i) {
		int ttot= 0;
		while(i[ttot] != '\0')
			ttot++;
		return ttot;
		
		
	}
	//metodo inutil
	public void erro(String sd) {
		int size = 0;
		
		
	}
	//adicona no dicionario
	public boolean addWord(String word) throws IOException {
		putWord(word);
		File fp = new File("/home/fabio/eclipse-workspace/bag/src/br/ufc/crateus/ST/spellchecker/mendicionario");
		if( fp != null) {
			try {
				BufferedWriter buff = new BufferedWriter(new FileWriter(fp, true));
				buff.write(word);
				buff.write("\n");
				buff.close();
				return true;
			}catch(IOException ex) {
				System.out.println("Erro 404 !!");
				ex.printStackTrace();
				
			}
			
		}
		return false;
		
	}
	
	
	public Boolean check(String word) {
		if(dictionary.contains(word.toLowerCase())) {
			return true;
		}else {
			return false;
			
		}
		
		
	}
	//editandos as palavras
	private ArrayList<String> edites(String word){
		ArrayList<String> resp = new ArrayList<>();
		
		for(int i = 0; i < word.length(); i++) {
			resp.add(word.substring(0, i)+word.substring(i + 1));
		}
		for(int i = 0; i < word.length() - 1; i++) {
			resp.add(word.substring(0,i) + word.substring(i + 1,i + 2) + word.substring(i, i + 1) + word.substring(i + 2));
		}
		for(int i = 0; i < word.length(); i++) {
			for(char c = 'a';  c <= 'z';c++) {
				resp.add(word.substring(0,1) + String.valueOf(c) + word.substring(i + 1));
			}
		}
		for(int i = 0;i <= word.length(); i++) {
			for(char c = 'a';c <= 'z';c++) {
				resp.add(word.substring(0,i) + String.valueOf(c) + word.substring(i));
				
			}

		}
		return resp;
	}
	//verificar se esta correto e da susgestao
	public String corret(String word) {
		if(dictionary.contains(word)) {
			return word;
		}
		ArrayList<String> list = edites(word);
		HashMap<Integer,String> cadidates = new HashMap();
	
		for(String k : list) {
			if(dictionary.contains(k)) {
				cadidates.put(dictionary.get(k),k);
			}
		}
		if(cadidates.size() > 0) {
			return cadidates.get(Collections.max(cadidates.keySet()));
		}
		for(String k : list) {
			for(String s : edites(k)) {
				if(dictionary.contains(s)) {
					cadidates.put(dictionary.get(s), s);
				}
			}
		}
		return cadidates.size() > 0 ? cadidates.get(Collections.max(cadidates.keySet())) : "Sorry";
	}
	
		private Integer size(String s) {
		Integer size =0;
		for(int i = 0;i < s.length();i++) {
			size++;
		}
		return size;
		
	}
	
	
	/*public static void main(String[] args) throws IOException {
		Dictionary pt = new Dictionary();
		pt.create();
		pt.addWord("seu");
		System.out.println(pt.check("Teste"));
	
		//pt.wordsSimilar("Teste");
		//pt.sugestoes("sd");
		System.out.println(pt.corret(""));

		System.out.println("depois");
		
	}*/

}
