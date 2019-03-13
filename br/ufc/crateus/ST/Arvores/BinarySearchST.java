package br.ufc.crateus.ST;

import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;

import javax.swing.SizeRequirements;

public class BinarySearchST<K extends Comparable<K>, V> implements ST<K, V> {

	
	private K[] key;
	private V[] value;
	private int count = 0;
    public BinarySearchST(int capacity) {
		this.key = (K[]) new Comparable[capacity];
		this.value = (V[]) new Object[capacity];
		
	}
	
	public Iterator<K> iterator() {
		
		return new Iterator<K>() {
			private int i = count;

			@Override
			public boolean hasNext() {
				
				return i > 0;
			}

			@Override
			public K next() {
				
				return key[--i];
			}
			
		};
	}
	public void printar() {
		Iterator<K> it = iterator();
		Iterator<V> its = iterator1();
		int i = 0;
		while(it.hasNext() && its.hasNext()) {
			
			System.out.println(it.next()+" , "+its.next());
		}
	}
	
	public Iterator<V> iterator1(){
		return new Iterator<V>() {
			int i = count;

		
			public boolean hasNext() {
				
				return i > 0;
			}

		
			public V next() {
				
				return value[--i];
			}
			
		};
	}

	@Override
	public void put(K k, V v) {
		int i = rank(k);
		if(i < count && key[i].compareTo(k) == 0) {
			 value[i] = v;
			 return;
			
		}
		for(int j = count; j > i;j--) {
			key[j] = key[j-1];
			value[j] = value[j-1];
		}
			
			key[i] = k;
			value[i] = v;
			count++;
		
		

	}
	public int rank(K k) {
		int lo = 0,hi = count - 1;
		while(lo <= hi) {
			int mid = lo + (hi - lo)/2;
			int cmp = k.compareTo(key[mid]);
			if(cmp < 0) hi = mid - 1;
			else if(cmp >  0) lo = mid + 1;
			else return mid;
		}
		return lo;
	}
	@Override
	public boolean contains(K k) {
		// TODO Auto-generated method stub
		return get(k) != null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size() == 0;
		
	}

	@Override
	public void delete(K k) {
		int mid = rank(k);
		if(k.equals(key[mid])) {
			
		key[mid] = null;
		for(int i = mid ; i < count; i++) {
			key[i] = key[i+1];
			}
			count--;
		}
		
		

	}
	

	@Override
	public V get(K k) {
		if(isEmpty())return null;
		int i = rank(k);
		if(i < count && key[i].compareTo(k) == 0) return value[i];
		else return null;
		
	}

	@Override
	public int size() {
		return count;
	}

	
	@Override
	public Iterable<K> keys() {
		// TODO Auto-generated method stub
		return null;
	}
	public static void main(String[] args) {
		BinarySearchST lista = new BinarySearchST(10);
		
		lista.put(1,"fabio");
		lista.put(6, "Santos");
		lista.put(10, "joau");
		lista.put(2, "Maria");
		lista.put(5, "jose");
		lista.delete(5);
		
		//System.out.println(lista.toString());
		lista.printar();
		
		/*Iterator it = lista.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}*/
	}
}
