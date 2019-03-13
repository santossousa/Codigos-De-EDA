package br.ufc.crateus.heaps;

import java.util.Iterator;

public interface Heaps<Key>{
	void insert(int i, Key key);
	int Extract_Min();
	void decreaseKey(int i, Key key);
	boolean contains(int i);
	public int size();
	boolean isEmpty();
	
	Key min();
	
	void delete(int i);
	

}
