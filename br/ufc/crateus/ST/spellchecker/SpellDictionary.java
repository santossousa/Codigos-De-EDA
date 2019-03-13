package br.ufc.crateus.ST.spellchecker;
import java.lang.*;
import java.io.*;
import java.util.*;
import java.io.FileReader;

/**
 *  Takes a dictionary and checks for words and near matches
 *  
 *  
 *  
 */

public class SpellDictionary { 
    // PUT CLASS FIELDS HERE (IF ANY), WITH JAVADOC FOR EACH
    /** The dictionary */
    public HashMap<String, String> dictionary;
    //dictionary = dictionary.createDict();

    //BufferedReader file = new BufferedReader(new FileReader("inputtext.txt"));
    // PUT CONSTRUCTORS NEXT (IF ANY), WITH JAVADOC FOR EACH
    /** Constructor for a dictionary */
     public HashMap<String, String> createDict() {
        FileReader txtWordsReader;
        try {
            txtWordsReader = new FileReader("/home/fabio/eclipse-workspace/bag/src/br/ufc/crateus/ST/spellchecker/mendicionario");

        }
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
        StreamTokenizer words = new StreamTokenizer(txtWordsReader);
        dictionary = new HashMap(500000);
        while (words.ttype != words.TT_EOF) {
            String word = words.sval;
            dictionary.put(word, null);
            try {
                words.nextToken();
            } catch(IOException e) {
                System.out.println(e.toString());
            }
        }
        System.out.println("Hi");
        System.out.println(dictionary);
        return dictionary;

    }

     // PUT CLASS METHODS NEXT (IF ANY), WITH JAVADOC FOR EACH
     /*
      * Checks if a word is in the dictionary.
      *
      *  @param word, the word to be checked
      *  @return boolean that is true if it is a word
      */
      public Boolean inDict(String word) {
       // System.out.println(word);
       // dictionary.createDict();

       // System.out.println("HELLO");
        //System.out.println(x);
        if (dictionary.containsKey(word.toLowerCase())) {
            return true;
        } else {
            return false;
        }
      }

      /*
       * Finds "near misses" to words in dictionary
       *
       *  @param word, word to find similar words to
       *  @return  similarWords, words similar to that word
       */
       public ArrayList<String> findSimilar(String word) {
            dictionary = this.createDict();
            ArrayList<String> similarWords = new ArrayList();
            char[] wordChar = word.toCharArray();
            int i;
            String search;
            char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

            //Find deletions
            i = 0;
            while (i < word.length()) {
                search = word.substring(0, i).concat(word.substring(i + 1));
                if (inDict(search)) {
                  System.out.println("hi");
                  similarWords.add(search);
                }
                i++;
            }

            //Find insertions
            i = 0;
            while (i < word.length()) {
              for (char letter : alphabet) {
                search = word.substring(0, i).concat(Character.toString(letter)).concat(word.substring(i));
                if (inDict(search)) {
                  similarWords.add(search);
                }
              }
              i++;
            }

            //Find substitutions
            i = 0;
            while (i < word.length()) {
              for (char letter : alphabet) {
                search = word.substring(0, i).concat(Character.toString(letter)).concat(word.substring(i +  1));
                if (inDict(search)) {
                  similarWords.add(search);
                }
              }
              i++;
            }

            //Transposition
            i = 0;
            while (i + 1 < word.length()) {
              char swap1 = word.charAt(i);
              char swap2 = word.charAt(i + 1);
              String swapped = Character.toString(swap2).concat(Character.toString(swap1));
              search = word.substring(0, i).concat(swapped).concat(word.substring(i+2));
              if (inDict(search)) {
                similarWords.add(search);
              }
            }

            //Find splits
            i = 1;
            while (i + 1 < word.length()) {
              search = word.substring(0, i);
              String search2 = word.substring(i, 0);
              if (inDict(search) && inDict(search2)) {
                similarWords.add(search.concat(" ").concat(search2));
              }
            }

            return similarWords;
       }
}