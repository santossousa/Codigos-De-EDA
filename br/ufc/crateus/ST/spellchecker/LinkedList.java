package br.ufc.crateus.ST.spellchecker;

import java.util.Iterator;
import java.util.NoSuchElementException;

import br.ufc.crateus.ST.LinkedListST;


public class LinkedList<K>{

	
	private class Node{
		
			K key;
			
			Node next;
			public Node(K key,Node next) {
				this.key = key;
				this.next = next;
				
			}
			public K getKey() {
				return key;
			}
		}
		
		private Node list;
		private int count = 0;
		
	

	public Iterator<K> iterator() {
		return new Iterator<K>(){
			Node curt = list;

			@Override
			public boolean hasNext() {
				return curt != null;
			}

			@Override
			public K next() {
				
				if(curt != null) {
					K keys = curt.key;
					curt = curt.next;
					return keys;
					}
				
					throw new NoSuchElementException();
				
					
				
				
			}
			
		};
	}

	
	public void put(K k) {
		if(k == null) delete(k);
		Node node = getNode(k);
		if(node == null) {
			list = new Node(k,list);
			count++;
		}
		
		
	}
	private Node getNode(K k) {
		for(Node p = list;p  != null;p = p.next) {
			if(p.key.equals(k))return p;
		}
		return null;
	}

	
	public boolean contains(K k) {
		return get(k) != null;
	}

	
	public boolean isEmpty() {
		
		return list == null;
	}

	
	public int size() {
		return count;
	}

	public Iterable<K> keys() {
		return this::iterator;
	}
	public void printar() {
		Iterator<K> m = iterator();
		while(m.hasNext()) {
			System.out.println(m.next());
		}
		
	}

	
	public void delete(K k) {
		Node h = new Node(null,list);
		for(Node p = h; p.next != null;p = p.next) {
			if(p.next.key.equals(k)) {
				p.next = p.next.next;
				count--;
				break;
			}
		}
		list = h.next;
		
		
	}
	public String toString() {
		if(this.count == 0) {
			return "[]";
		}
		StringBuilder builer = new StringBuilder("[");
		Node atual = list;
		for(int i = 0; i < count-1;i++) {
			builer.append(atual.key);
			builer.append(",");
			atual = atual.next;
		}
		builer.append(atual.key);
		builer.append("]");
		return builer.toString();
		
		
		
	}

	public K get(K k) {
		Node node = getNode(k);
		return (node != null)? node.key: null;
	}
	public static void main(String[] agrs) {
		LinkedList lista = new LinkedList();
		lista.put("Joao");
		lista.put("Fabio");
		lista.put("Maria");
		lista.put("Joana");
		
		Iterator e = lista.iterator();
		while(e.hasNext()) {
			System.out.println(e.next());
			System.out.println();
		}
	}

}
