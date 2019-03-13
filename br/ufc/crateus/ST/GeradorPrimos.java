package br.ufc.crateus.ST;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GeradorPrimos {
	
	
	public static void main(String[] agrs) {
		Scanner in = new Scanner(System.in);
		Integer n = 0;
		System.out.println("---------------------");
		System.out.println("|     Digite numero    |");
		System.out.println("---------------------");
		try {
			n = in.nextInt();
			
		}
		catch(InputMismatchException e) {
			System.out.println("Voce digitou errado "+e.getMessage());
		}
		

        boolean[] Prime = new boolean[n];

        for (int i = 2; i < n; i++)
            Prime[i] = true;

        
        for (int factor = 2; factor*factor < n; factor++) {
            if (Prime[factor]) {
                for (int j = factor; factor*j < n; j++)
                    Prime[factor*j] = false;
            }
        }

        
        int primes = 0;
        for (int i = 2; i < n; i++)
            if (Prime[i]) primes++;

        

        
        int[] list = new int[primes];
        int count = 0;
        for (int i = 0; i < n; i++)
            if (Prime[i]) list[count++] = i;

        int left = 0, right = count-1;
        while (left <= right) {
            if      (list[left] + list[right] == n) break;
            else if (list[left] + list[right]  < n) left++;
            else right--;
        }
        if (list[left] + list[right] == n)
            System.out.println(list[left] + " + " + list[right]+" = "+ n);
        else
            System.out.println(n + " : Erro 404 !");
    }
}

