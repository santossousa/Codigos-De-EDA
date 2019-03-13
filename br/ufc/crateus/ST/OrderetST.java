package br.ufc.crateus.ST;

public interface OrderetST<K extends Comparable<K>, V> extends ST<K,V>{
		K min();
		
		K max();
		
		K floor(K e);
		
		K ceiling(K e);
		
		K select(int index);
		
		Iterable<K> keys(K lo, K hi);
		
		int rank(K e);
		
		void deleteMin();
		
		void deleteMax();
		
		int size(K lo, K hi);

		Iterable<K> Keys();

}
