package br.ufc.crateus.heaps;

import java.security.NoSuchAlgorithmException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/*public class HeapBinario<Key> implements Heaps<Key> {
	private int n;
	private Key[] arrayheap;
	private Comparator<Key> comparator;
	
	 public HeapBinario(int capacity) {
		 arrayheap = (Key[]) new Object[capacity  + 1];
		 n = 0;
	 }
	 public HeapBinario() {
		 this(1);
	 }
	 public HeapBinario(int capacity, Comparator<Key> comparator) {
		 this.comparator = comparator;
		 arrayheap = (Key[]) new Object[capacity  + 1];
		 n = 0;
		 
	 }
	 
	 public HeapBinario(Comparator<Key> comparator) {
		 this(1,comparator);
	 }
	 public HeapBinario(Key[] keys) {
		 n = keys.length;
		 arrayheap = (Key[]) new Object[keys.length + 1];
		 for(int i = 0; i < n;i++) 
			 arrayheap[i + 1] = keys[i];
		 
		 for(int i = n/2;i >= 1; i--)
			 sink(i);
		 assert isMin();
			 
			 
		 
		 
	 }
	 public void decreaseKey(int i,Key key) {
		 arrayheap[i] = key;
		 swim(i);
	 }

	@Override
	public void insert(Key key) {
		arrayheap[++n] = key;
		swim(n);
		assert isMin();
				
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
		return arrayheap[1];
	}
	public void delete(int i) {
		exch(i, n--);
		swim(i);
		sink(i);
		arrayheap[i] = null;
		
	}
	@Override
	public Key deleteMin() {
	//	if(isEmpty()) throw new NoSuchAlgorithmException();
		
			Key min = arrayheap[1];
			exch(1, n--);
			sink(1);
			arrayheap[n + 1] = null;
			assert isMin();
			
		return min;
		
	}

	@Override
	public void swim(int key) {
		while (key > 1 && swapscompare(key/2, key)) {
            exch(key, key/2);
            key = key/2;
        }
		
	}

	@Override
	public void sink(int key) {
		 while (2 * key <= n) {
	            int j = 2 * key;
	            if (j < n && swapscompare(j, j+1)) j++;
	            if (!swapscompare(key, j)) break;
	            exch(key, j);
	            key = j;
	        }
		
	}

	@Override
	public Iterator<Key> iterator() {
		return new Iterator<Key>() {
			private int i = n;
			@Override
			public boolean hasNext() {
					return i > 0;
			}

			@Override
			public Key next() {
				return arrayheap[--i];
			}
			
		};
	}
	private boolean isMin() {
		return isMin(1);
		
	}
	private boolean swapscompare(int i,int j) {
		if(comparator == null)
			return ((Comparable<Key>)arrayheap[i]).compareTo(arrayheap[j]) > 0;
			
			else
				return comparator.compare(arrayheap[i],arrayheap[j]) > 0;
	}
	private boolean isMin(int key) {
		if(key > n)return true;
		int left = 2 * key;
		int right = 2 * key + 1;
		if(left <= n && swapscompare(key,left))return false;
		if(right <= n && swapscompare(key, right))return false;
		return isMin(left) && isMin(right);
		
	}
    private void exch(int i, int j) {
        Key swap = arrayheap[i];
        arrayheap[i] = arrayheap[j];
        arrayheap[j] = swap;
    }
    public static void main(String[] args) {
    	HeapBinario<Integer> pr = new HeapBinario<>(3);
    	pr.insert(3);
    	pr.insert(1);
    	pr.insert(2);
    	pr.delete(2);
    	Iterator it = pr.iterator();
    	while(it.hasNext()) {
    		System.out.println(it.next());
    	}
    }


}*/
public class HeapBinario<Key extends Comparable<Key>> implements Heaps<Key>, Iterable<Integer>  {
    private int maxN;       
    private int n;           
    private int[] arrayaux;        
    private int[] arrayinverse;        
    private Key[] keys;      

    public HeapBinario() {
    	this(1);
    }
    public HeapBinario(int maxN) {
        if (maxN < 0) throw new IllegalArgumentException();
        this.maxN = maxN;
        n = 0;
        keys = (Key[]) new Comparable[maxN + 1];    
        arrayaux   = new int[maxN + 1];
        arrayinverse   = new int[maxN + 1];                 
        for (int i = 0; i <= maxN; i++)
            arrayinverse[i] = -1;
    }
    @Override
    public boolean isEmpty() {
        return n == 0;
    }
    @Override
    public boolean contains(int i) {
        
        return arrayinverse[i] != -1;
    }
    @Override
    public int size() {
        return n;
    }
    @Override
    public void insert(int i, Key key) {
        n++;
        arrayinverse[i] = n;
        arrayaux[n] = i;
        keys[i] = key;
        swim(n);
    }

    public int minIndex() {
        return arrayaux[1];
    }
    @Override
    public Key min() {
        return keys[arrayaux[1]];
    }
    @Override
    public int Extract_Min() {
        int min = arrayaux[1];
        exch(1, n--);
        sink(1);
        assert min == arrayaux[n+1];
        arrayinverse[min] = -1;        
        keys[min] = null;    
        arrayaux[n+1] = -1;        
        return min;
    }

    public Key keyOf(int i) {
        if (i < 0 || i >= maxN) throw new IllegalArgumentException();
        else return keys[i];
    }


    
    public void decreaseKey(int i, Key key) {
        
        if (keys[i].compareTo(key) <= 0)
            throw new IllegalArgumentException("Calling decreaseKey() with given argument would not strictly decrease the key");
        keys[i] = key;
        swim(arrayinverse[i]);
    }
    @Override
    public void delete(int i) {
        int index = arrayinverse[i];
        exch(index, n--);
        swim(index);
        sink(index);
        keys[i] = null;
        arrayinverse[i] = -1;
    }


    private boolean swapCompare(int i, int j) {
        return keys[arrayaux[i]].compareTo(keys[arrayaux[j]]) > 0;
    }

    private void exch(int i, int j) {
        int swap = arrayaux[i];
        arrayaux[i] = arrayaux[j];
        arrayaux[j] = swap;
        arrayinverse[arrayaux[i]] = i;
        arrayinverse[arrayaux[j]] = j;
    }


    private void swim(int k) {
        while (k > 1 && swapCompare(k/2, k)) {
            exch(k, k/2);
            k = k/2;
        }
    }

    private void sink(int k) {
        while (2*k <= n) {
            int j = 2*k;
            if (j < n && swapCompare(j, j+1)) j++;
            if (!swapCompare(k, j)) break;
            exch(k, j);
            k = j;
        }
    }


    public Iterator<Integer> iterator() { return new HeapIterator(); }

    private class HeapIterator implements Iterator<Integer> {
        private HeapBinario<Key> copy;

        public HeapIterator() {
            copy = new HeapBinario<Key>(arrayaux.length - 1);
            for (int i = 1; i <= n; i++)
                copy.insert(arrayaux[i], keys[arrayaux[i]]);
        }

        public boolean hasNext()  { return !copy.isEmpty();                     }

        public Integer next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy.Extract_Min();
        }
    }


    public static void main(String[] args) {
        String[] strings = { "it", "was", "the", "best", "of", "times", "it", "was", "the", "worst" };

        HeapBinario<String> arrayaux = new HeapBinario<String>(strings.length);
        for (int i = 0; i < strings.length; i++) {
            arrayaux.insert(i, strings[i]);
        }
        	
        
        System.out.println(arrayaux.keyOf(9));
        
        
        // delete and print each key
      /*  while (!arrayaux.isEmpty()) {
            int i = arrayaux.Extract_Min();
            System.out.println(i + " " + strings[i]);
        }
        System.out.println();

        // reinsert the same strings
        for (int i = 0; i < strings.length; i++) {
            arrayaux.insert(i, strings[i]);
        }

        // print each key using the iterator
        for (int i : arrayaux) {
            System.out.println(i + " " + strings[i]);
        }
        while (!arrayaux.isEmpty()) {
            arrayaux.Extract_Min();
        }*/
        	
        

    }
}

