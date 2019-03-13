package br.ufc.crateus.heaps;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Heap_D_ary<Key> implements Heaps<Key>,Iterable<Integer> {
	private int n;
	private int maxN;
	private int[] arrayaux;
	private int[] arrayinverse;
	private final int d;
	private Key[] keys;
	private Comparator<Key> comp;
	
	public Heap_D_ary(int n,int d) {
		this.d = d;
		maxN = n;
		arrayaux = new int[maxN + d];
		arrayinverse = new int[maxN + d];
		keys = (Key[]) new Comparable[maxN + d];
		for(int i = 0;i < maxN+d;arrayinverse[i++] = -1)
			comp = new MyComparator();
	}
	public Heap_D_ary(int n,Comparator<Key> C,int d) {
		this.d = d;
		maxN = n;
		arrayaux = new int[maxN+d];
		arrayinverse = new int[maxN+d];
		keys = (Key[]) new Comparable[maxN+d];
		for (int i = 0; i < maxN+d; arrayinverse[i++] = -1);
		comp = C;
	}
	
	@Override
	public Iterator<Integer> iterator() {
		// TODO Auto-generated method stub
		return new MyIterator();
	}
	@Override
	public void insert(int i, Key key) {
		keys[i+d] = key;
		arrayaux[n+d] = i;
		arrayinverse[i+d] = n;
		swim(n++);
				
	}
	@Override
	public int Extract_Min() {
		
		int min = arrayaux[d];
		exch(0, --n);
		sink(0);
		arrayinverse[min + d] = -1;
		keys[arrayaux[n+d] + d] = null;
		arrayaux[n+d] = -1;
		return min;

	}
	private void exch(int x, int y) {
		int i = x+d, j = y+d;
		int swap = arrayaux[i];
		arrayaux[i] = arrayaux[j];
		arrayaux[j] = swap;
		arrayinverse[arrayaux[i]+d] = x;
		arrayinverse[arrayaux[j]+d] = y;
	}
	private void swim(int i) {
		if (i > 0 && greater((i-1)/d, i)) {
			exch(i, (i-1)/d);
			swim((i-1)/d);
		}
	}
	
	private void sink(int i) {
		if (d*i+1 >= n) return;
		int min = minChild(i);
		while (min < n && greater(i, min)) {
			exch(i, min);
			i = min;
			min = minChild(i);
		}
	}
	private int minChild(int i) {
		int loBound = d*i+1, hiBound = d*i+d;
		int min = loBound;
		for (int cur = loBound; cur <= hiBound; cur++) {
			if (cur < n && greater(min, cur)) min = cur;
		}
		return min;
	}

	private class MyComparator implements Comparator<Key> {
		@Override
		public int compare(Key key1, Key key2) {
			return ((Comparable<Key>) key1).compareTo(key2);
		}
	}
	private boolean greater(int i, int j) {
		return comp.compare(keys[arrayaux[i+d]+d], keys[arrayaux[j+d]+d]) > 0;
	}
	

	@Override
	public void decreaseKey(int i, Key key) {
		keys[i+ d] = key;
		swim(arrayinverse[i+d]);
				
	}
	@Override
	public boolean contains(int i) {
		return arrayinverse[i + d] != -1;
	}
	@Override
	public int size() {
		return n;
	}
	@Override
	public boolean isEmpty() {
		return n == 0;
	}
	@Override
	public Key min() {
		 return keys[arrayaux[d] + d];
	}
	@Override
	public void delete(int i) {
		if (i < 0 || i >= maxN) throw new IllegalArgumentException();
		if (! contains(i)) throw new NoSuchElementException("Specified index is not in the queue");
		
		int idx = arrayinverse[i+d];
		exch(idx, --n);
		swim(idx);
		sink(idx);
		keys[i+d] = null;
		arrayinverse[i+d] = -1;
		
	}
	private class MyIterator implements Iterator<Integer> {
		Heap_D_ary<Key> clone;
		
		public MyIterator() {
			clone = new Heap_D_ary<Key>(maxN, comp, d);
			for (int i = 0; i < n; i++) {
				clone.insert(arrayaux[i+d], keys[arrayaux[i + d] + d]);
			}
		}

		public boolean hasNext() {
			return !clone.isEmpty();
		}
		
		public Integer next() {
               if (!hasNext()) throw new NoSuchElementException();
			return clone.Extract_Min();
		}
		
	}
	

	public static void main(String[] args) {
		Heap_D_ary<String> ps = new Heap_D_ary<>(20, 200);
		int[] str = {4,5,6,7,8,9,10};
		String[] strb = {"mino","perceus","javalange","doceAFoga","mergesort","naoseidasuavida","soldado"	};
		for(int i = 0;i < str.length && i < strb.length;i++) {
			ps.insert(str[i], strb[i]);
		}
		ps.decreaseKey(5, "builia" );
		//4, 5, 6, 7, 8, 9, 10};
	/*	ps.insert(4, "");
		ps.insert(5,"");
		ps.insert(6,"");
		ps.insert(7,"");
		ps.insert(8,"");
		ps.insert(9,"");
		ps.insert(3," ");
		ps.insert(10,"");*/
		ps.Extract_Min();
		ps.Extract_Min();
		//ps.delete();
		//System.out.println(ps.contains());
		//ps.Extract_Min();
		//	System.out.println(ps.contains(1));
		Iterator it = ps.iterator();
		while(it.hasNext()) {
			
			System.out.println(it.next());
		}
	}
	
	
	

}
