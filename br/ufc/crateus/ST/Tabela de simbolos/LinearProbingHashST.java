package br.ufc.crateus.ST;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;


public class LinearProbingHashST<K, V> implements ST<K,V> {

	private int N = 0;
	private int M = 16;
	private K[] keys;
	private V[] value;
	
	public LinearProbingHashST(int M) {
		this.M = M;
		keys = (K[]) new Object[M];
		value = (V[]) new Object[M];
	
	
	}
	public LinearProbingHashST() {
		this(20);
	}

	private int hash(K k) {
		return (6*(k.hashCode() & 0x7fffffff))%M;
	}
	@Override
	public void put(K k, V v) {
		if(v == null) {
			delete(k);
			return;
		}

		
		if(N >= M/2)
		resize(2*M);
		
		
		int i;
		
		for(i = hash(k); keys[i] != null; i = (i+1)%M) 
			if(keys[i].equals(k)) 
				
				break;
			
		keys[i] = k;
		value[i] = v;
		N++;
		
		
	}

	@Override
	public boolean contains(K k) {
		return get(k) != null;
	}

	@Override
	public boolean isEmpty() {
		
		return N == 0;
	}
	
	private void resize(int capacidade) {
		LinearProbingHashST<K, V> vector = new LinearProbingHashST<>(capacidade);
		
		for(int i = 0; i < M; i++) {
			if(keys[i] != null)
				vector.put(keys[i], value[i]);
			keys = vector.keys;
			value = vector.value;
			M = vector.M;
		}
	}

	@Override
	public void delete(K k) {
		if(!contains(k))return;
		int i = hash(k);
		
		while(!k.equals(keys[i]))
			i = (i + 1) % M;
		keys[i] = null;
		value[i] = null;
		
		while(keys[i] != null) {
			K t = keys[i];
			V vals = value[i];
			keys[i] = null;
			value[i] = null;
			N--;
			put(t,vals);
			i = (i + 1) % M;
			
		}
		N--;
		
		if(N > 0 && N == M/8)
			resize(M/2);
	}

	@Override
	public V get(K k) {
		
		for(int i = hash(k); keys[i] != null ; i = (i +1) % M)
			if(keys[i].equals(k))
				return value[i]; 
		return null;
	}

	@Override
	public int size() {
		
		return N;
	}
	public String toString() {
		int cont = 0;
		StringBuilder str = new StringBuilder();
		for(K k : keys) {
			if(k == null) {
				continue;
			}
			else {
				str.append("\narray [ ").append(cont).append(" ] ").append(k.toString());
				cont++;
			}
			
		
			
		}
		return str.toString();
		
	}
	public void imprimer() {
		
			
		for(int i = 0; i < M; i++) {
			if(keys[i] == null) {
				System.out.println("__");
			}
			else {
				System.out.println(keys[i] + " ");
			}
		}
		
	}
	@Override
	public Iterable<K> keys() {
		Queue<K> key = new LinkedList<K>();
		for(int i = 0; i < M; i++) 
			if(keys[i] != null)key.add(keys[i]);
		return key;
	}
	
}
