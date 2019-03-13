package br.ufc.crateus.ST.corretoortografico;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
			Locale e = new Locale("pt-br");
			String key = "Teste";
			Words w = new Words(key,key.length());
			
				//SpellChecker p = new SpellChecker();
				File dp = new File("/home/fabio/eclipse-workspace/bag/src/br/ufc/crateus/ST/corretoortografico/brazilian");
				
				SpellDictionaryHashMap ser = new SpellDictionaryHashMap(dp);
				ser.addWord("Teste");
				ser.addWord("MANGA");
				ser.getSuggestions("Test", 0);
				System.out.println(ser.isCorrect("Teste"));
				
				
			//	BufferedReader in;
				ser.createDictionary(new BufferedReader(new FileReader(dp)));
				
				SpellChecker ps = new SpellChecker(ser);
				ps.getConfiguration();
				Scanner in = new Scanner(System.in);
				System.out.println("ENTRADA DE DADOS");
					String arquivo = in.nextLine();
					System.out.println(ps.addToDictionary(arquivo));
				
				
				
				
				
		
	}

}
