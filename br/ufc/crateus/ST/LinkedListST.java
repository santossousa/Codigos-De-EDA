package br.ufc.crateus.ST;

import java.util.Iterator;
import java.util.NoSuchElementException;




public class LinkedListST<K,V> implements ST<K, V> {
	
	private class Node{
		
			K key;
			V value;
			Node next;
			public Node(K key,V value,Node next) {
				this.key = key;
				this.value = value;
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

	@Override
	public void put(K k, V v) {
		if(v == null) delete(k);
		Node node = getNode(k);
		if(node == null) {
			list = new Node(k,v,list);
			count++;
		}
		else {
			node.value = v;
		}
		
	}
	private Node getNode(K k) {
		for(Node p = list;p  != null;p = p.next) {
			if(p.key.equals(k))return p;
		}
		return null;
	}

	@Override
	public boolean contains(K k) {
		return get(k) != null;
	}

	@Override
	public boolean isEmpty() {
		
		return list == null;
	}

	@Override
	public int size() {
		return count;
	}

	@Override
	public Iterable<K> keys() {
		return this::iterator;
	}
	public void printar() {
		Iterator<K> m = iterator();
		while(m.hasNext()) {
			System.out.println(m.next());
		}
		
	}

	@Override
	public void delete(K k) {
		Node h = new Node(null,null,list);
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
			builer.append(atual.value);
			builer.append(",");
			atual = atual.next;
		}
		builer.append(atual.value);
		builer.append("]");
		return builer.toString();
		
		
		
	}

	@Override
	public V get(K k) {
		Node node = getNode(k);
		return (node != null)? node.value: null;
	}
	public static void main(String[] agrs) {
		LinkedListST lista = new LinkedListST();
		lista.put(1, "Joao");
		lista.put(6, "Fabio");
		lista.put(9, "Maria");
		lista.put(11, "Joana");
		lista.put(20, "jose");
		lista.delete(1);
		
		lista.printar();
		
	//	System.out.println(lista.toString());
		
		
	
	}

}
