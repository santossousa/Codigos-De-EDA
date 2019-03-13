package br.ufc.crateus.ST;

public interface BinarySearchTree<Key extends Comparable<Key> ,Value> {
	
	void delete(Key k);
	void put(Key k, Value v);
	int size();
	Value get(Key k);
	Key min();
	Key max();
	Key floor(Key k);
	Key select(int k);
	int rank(Key k);
	void deleteMin();
	void deleteMax();
	int size(Key lo,Key hi);
	boolean contains(Key k);
	Iterable<Key> Keys();
	Iterable<Key> keys(Key lo, Key li);
	
	

}
