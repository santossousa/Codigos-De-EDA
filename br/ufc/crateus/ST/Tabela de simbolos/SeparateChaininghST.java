package br.ufc.crateus.ST;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Random;
import java.util.Vector;

import javax.management.Query;





public class SeparateChaininghST<K extends Comparable<K>, V> implements ST<K,V>{
	
	private static int M = 97;
	
	private int N = 0;
	private int L =  26;
	public class SequencialST<K extends Comparable<K> , V>{
		private class Node{
			private K k;
			private V v;
			Node next;
			public Node(K k,V v,Node next) {
				this.k = k;
				this.v = v;
				this.next = next;
			}
			public String toString() {
				return " "+k;
			}
			
			
		}
		public SequencialST() {
			
		}
		private  Node  first;
		int n = 0;
		private K min() {
			return min(first).k;
		}
		private Node min(Node n) {
			K k = n.k;
			while(true) {
				if(k.compareTo(n.k) < 0) {
					return n;
					
				}
				n = n.next;
			}
			
		}
		
		public void put(K k, V v) {
			if(k == null) {		
				return;
			}
			if(v == null) {
				delete(k);
			}
			for(Node x = first; x != null; x = x.next ) {
				if(k.equals(x.k)) {
					x.v = v;
					break;
				}
			}
			first = new Node(k,v,first);
			n++;
		}


		public boolean contains(K k) {
			return get(k) != null;
		}

		
		public boolean isEmpty() {
			// TODO Auto-generated method stub
			return first == null;
		}


		public void delete(K k) {
			Node h  = new Node(null,null,first);
			for(Node x = h; x.next != null;x = x.next) {
				if(x.next.k.equals(k)) {
					x.next = x.next.next;
					n--;
					break;
				}
			}
			first = h.next;
		}

		
		public V get(K k) {
			for(Node x = first;x != null;x = x.next) {
				if(k.equals(x.k)) {
					return x.v;
				}
			}
			return null;
		}
		public String toString() {
			int cout = 0;
			StringBuilder buff = new StringBuilder();
			for(Node list = first; list != null; list = list.next) {
				
				if(list == null) {
					continue;
				}
					buff.append(list.toString());
					cout++;
					Node tmp = list.next;
					while(tmp != null) {
						buff.append(" -->");
						buff.append(tmp.toString());
						tmp = tmp.next;
					
					}
				
			}
			return buff.toString();
		}
		
		public int size() {
			
			return n;
		}

		
		public Iterable<K> keys() {
			Queue<K> keys = new LinkedList<>();
			for(Node x = first; x != null; x = x.next) {
				keys.add(x.k);
			}
			return keys;
		}


		

	}
	
	SequencialST<K,V>[] st  = new SequencialST[M];
	public SeparateChaininghST() {
		this(20);
		st = (SequencialST<K, V>[]) new SequencialST[M];
		
		for(int i = 0;i < M;i++) 
			st[i] = new SequencialST<>();
		
		
	
	}
	public SeparateChaininghST(int M) {
		this.M = M;
		st = (SequencialST<K, V>[]) new SequencialST[M];
		
		for(int i = 0;i < M;i++) 
			st[i] = new SequencialST<>();
		
		
	}
	
	
	@Override
	public void put(K k, V v) {
		
		
		
		int i = hash(k);
		if(!contains(k))N++;
		st[i].put(k, v);
		
		
		
		
	}
	public void imprime() {
	
	}
	private int hash(K k) {
		return (k.hashCode() & 0x7fffffff) % M;
	}
	@Override
	public boolean contains(K k) {
		// TODO Auto-generated method stub
		return get(k) != null;
	}
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void delete(K k) {
		int i = hash(k);
		st[i].delete(k);
		N--;
		
		if(M > 4 && N <= 2*M)rensize(M/2);
	}
	private void rensize(int capacity) {
		
		SeparateChaininghST<K, V> temp = new SeparateChaininghST<>(capacity);
		for(int i = 0;i < M;i++) {
			for(K k: st[i].keys()) {
				temp.put(k, st[i].get(k));
			}
		}
		this.M = M;
		this.N= N;
		this.st = temp.st;
		
	}
	public K min() {
		return st[0].min();
	}
	public void inteiros(int N) {
		
		Random r = new Random();
		
		
			
			
		
			
			
			
		SeparateChaininghST<Integer,Integer> sttable = new SeparateChaininghST<>(N/100);
		
		for(int  i = 0; i < N;i++) {
			int value = r.nextInt(Integer.MAX_VALUE);
			
			 sttable.put(value, value);
			
		}
		int sortinilength = Integer.MAX_VALUE;
		int sortfimlist = Integer.MIN_VALUE;
		
		for(SeparateChaininghST.SequencialST list : sttable.st   ) {
			if(list != null) {
				if(list.size() < sortinilength) {
					sortinilength = list.size();
				}
				else if(list.size() > sortfimlist) {
					sortfimlist = list.size();
				}
			}

			
		}
		for(Integer k : sttable.keys()) {
			System.out.println(k+","+sttable.get(k));
		}
		System.out.println("Lista menor "+sortinilength);
		System.out.println("Lista maior "+sortfimlist);
		
		
		

		
		
	}
	
	@Override
	public V get(K k) {
		if(k == null)return null;
		int i = hash(k);
		return st[i].get(k);
	}
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return N;
	}
	@Override
	public Iterable<K> keys() {
		Queue<K> keys = new LinkedList<>();
		for(int i = 0; i < M;i++) {
			for(K k : st[i].keys()) {
				keys.add(k);
				
			}
		}
		return keys;
	}
	public void imprimer() {
		for(int i = 0;i < M;i++) {
			System.out.println(st[i].toString());
		}
		
	}
	
	
	//fiz o node generico, pq estava tendo erro no cast
	}
