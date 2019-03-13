package br.ufc.crateus.ST;
import java.util.*;

public class BST<K extends Comparable<K>, V> implements OrderetST<K, V> {
	
	protected Node root;
	protected class Node{
		 Node left,right;
		 int n;
		 K k;
		 int key;
		 V v;
		/*public Node(int key) {
			key = key;
		}*/
		public Node(K k,V v) {
			this.v = v;
			this.k = k;
			this.n = 0;
	
			
		}
		@SuppressWarnings("unused")
		public Node(K k){
			this.k = k;
			
		}
		public Node(int i) {
			key = i;
		}
		public K get() {
			return k;
		}
		public int getKey() {
			return key;
		}
	}
	private int height;
	
	public void impri() {
		System.out.println(percore(root));
	}
	private String percore(Node x) {
		String retorno;
		
		retorno = "";
		if(x != null) {
			retorno += ","+x.key;
			retorno +=percore(x.left);

			retorno +=percore(x.right);
			
			
		}
		retorno += " ";
		return retorno;
	
	}

	/*private void constroi(int n,int[] vet) {
		int k;
		int aux;
		for(k = 1;k < n;++k) {
			int f = k+1;
			while(f > 1 && vet[f/2] < vet[f]) {
				troca(vet[f/2],vet[f]);
				f/=2;
				
				
			}
				
			
		}
	}
	private void troca(int a,int b) {
		int t = a;
		a = b;
		b = t;
	}
	public void Complente(int[] vet) {
		root = quaseComp(root,vet);
	}
	private Node quaseComp(Node x,int[] vet) {
		int n = vet.length-1;
		int aux;
		int m;
		constroi(n,vet);
		for( m = n; m >= 2;--m) {
			troca(vet[1],vet[m]);
			
			peneira(m-1,vet);
			x = inser(x,vet[m]);
			

			
		}
		
		return x;
	}
	private void peneira(int n,int[] vet) {
		int f = 2;
		int aux;
		while(f <= n) {
			if(f < n && vet[f] < vet[f+1]) ++f;
			
			if(vet[f/2] >= vet[f])break;
			troca(vet[f/2],vet[f]);
			f*=2;
		}
	}*/
	public void Complente(int[] vet) {
		root = heapF(root,vet);
	}
	private void heap(int[] vet,int n,int i) {
		int max,clix;
		clix = 2*i+1;
		max = i;
		
		if(clix < n) 
			if(vet[clix] > vet[max])
				max = clix;
		if(clix+1 < n)
			if(vet[clix+1] > vet[max])
				max = clix + 1;
		if(max != i) {
			int tmp = vet[i];
			vet[i] = vet[max];
			vet[max] = tmp;
			heap(vet,n,max);
		}
	}
	private void prierre(int[] vet) {
		for(int i = vet.length/2-1;i >= 0;i--)
			heap(vet,vet.length,i);
	}
	private Node heapF(Node x,int[] vet) {
		prierre(vet);
		
		for(int i = vet.length-1;i >= 0;i--) {
			int tem = vet[0];
			vet[0] = vet[i];
			vet[i] = tem;
			heap(vet,i,0);
			x =inser(x,vet[i]);
			
		}
		return x;
	}
	@Override
	public void delete(K k) {
		root  = delete(root,k);
	}
	/*private Node delete(Node x, Key k) {
		
		if(x == null) return null;
		
		int cmp = k.compareTo(x.k);
		
		if(cmp < 0) x.left = delete(x.left,k);
		else if (cmp > 0) x.right = delete(x.right,k);
		else {
			
			if(x.right == null) return x.left;
			if(x.left == null) return x.right;
			Node t = x;
			x = maxNode(t.left);
			x.left= deleteMax(t.left);
			x.right = t.right;
		}
		x.n = size(x.left)+size(x.right)+1;
		return x;
	}*/
	private Node delete(Node r, K key) {
				if (r == null) return null;
				
				int cmp = key.compareTo(r.k); 
				if (cmp < 0) r.left = delete(r.left, key);
				else if (cmp > 0) r.right = delete(r.right, key);
				else {
					if (r.left == null) return r.right;
					if (r.right == null) return r.left;
					Node t = r;
					r = maxNode(r.left);
					r.right = t.right;
					r.left = deleteMax(t.left);
				}
				
				r.n = size(r.left) + size(r.right) + 1;
				return r;
			}
		 
		 		

	@Override
	public void put(K k, V v) {
		root = put(root,k,v);
		height++;
	}
	
	
	private Node put(Node x,K k,V v) {
		if(x == null) return new Node(k,v);
		int cmp = k.compareTo(x.k);
		if(cmp < 0)x.left = put(x.left,k,v);
		else if(cmp > 0) x.right = put(x.right,k,v);
		else x.v = v;
		x.n = size(x.left)+size(x.right)+1;
		return x;
	}
	protected int size(Node x) {
		if(x == null) return 0;
		else return x.n;
	}
	public int height() {
		return height;
	}
	@Override
	public int size() {
		return size(root);
	}

	@Override
	public V get(K k) {
		return get(root,k);
	}
	private V get(Node x,K k) {
		if(x == null) return null;
		int cmp = k.compareTo(x.k);
		if(cmp < 0 )return get(x.left,k);
		else if(cmp > 0) return get(x.right,k);
		else return x.v;
		
		
	}

	@Override
	public K min() {
		return min(root).k;
	}
	private Node min(Node x) {
		if(x.left == null) return x;
		return min(x.left);
	}

	@Override
	public K max() {
		
	//	return null;
		Node m = maxNode(root);
		return (m != null)? m.k:null;
		
		
	}
	
	private Node inser(Node x,int v) {
		if(x == null) {
			x = new Node(v);
			x.key = v;
			x.left = null;
			x.right = null;
		}
		else if(v < x.key) {
			x.left = inser(x.left,v);
			
		}
		else {
			x.right = inser(x.right,v);
		}
		return x;
		
		
		
	}
	public int nivel(K k) {
		return chaveX(root,k);
	}
	private int chaveX(Node x,K k) {
		if(x == null) return 0;
		int cmp = chaveX(x.left,k)+chaveX(x.right,k)+1;
		return cmp;
	}
	public void array(int[] vetor) {
		int i = 0;
		int fim = vetor.length-1;
			
		
		 root = arv(root,vetor,i,fim);
		
	}
	
	private Node arv(Node x,int[] key,int i,int fim) {
		if(i > fim )return null;
		int m =(i + fim)/2;
		
		x = inser(x,key[m]); 
		
		 x.left = arv(x.left,key,i,m-1);
		 x.right = arv(x.right,key,m+1,fim);
		return x;
		
		
		
		
			
						
		
		
	}

	@Override
	public K floor(K k) {
		Node x = floor(root,k);
		if(x == null) return null;
		
		return x.k;
	}
	private Node floor(Node x,K k) {
		if(x == null) return null;
		int cmp = k.compareTo(x.k);
		if(cmp == 0)return x;
		if(cmp < 0) return floor(x.left,k);
		
		Node t = floor(x.right,k);
		if(t != null) return t;
		else return x;

	}
	@Override
	public K select(int k) {
				return select(root,k).k;
	}
	private Node select(Node x,int k) {
		if(x == null) return null;
		int t = size(x.left);
		if(t > k)return select(x.left,k);
		else if(t < k)return select(x.right,k-t-1);
		else return x;
	}

	@Override
	public int rank(K k) {
		
		return rank(root,k);
	}
	private int rank(Node x,K k) {
		if(x == null) return 0;
		int cmp = k.compareTo(x.k);
		if(cmp < 0) return rank(x.left,k);
		else if (cmp > 0) return  1+size(x.left)+rank(x.right,k);
		else return size(x.left);
	}
	
	@Override
	public void deleteMin() {
		root = deleteMin(root);
	}
	private Node deleteMin(Node x) {
		if(x.left == null) return x.right;
		x.left = deleteMin(x.left);
		x.n = size(x.left)+size(x.right)+1;
		return x;
	}

	@Override
	public void deleteMax() {
		root = deleteMax(root);
		
		 	
		 

	}
	private Node deleteMax(Node r) {
		if (r.right == null) return r.left;
		r.right = deleteMax(r.right);
		
		r.n = size(r.left) + size(r.right) + 1;
		return r;
	}
	protected Node maxNode(Node r) {
				if (r == null) return null;
				while (r.right != null) r = r.right;
				return r;
		 	}
		 


	@Override
	public Iterable<K> Keys() {
		return keys(min(),max());
		
		
	}
	void print() {
		Iterable<K> k = this.Keys();
		for(K c : k) {
		}
	}
	
	@Override
	public Iterable<K> keys(K lo, K li) {
		Queue<K> queue = new LinkedList<K>();

		
		Keys(root,queue,lo,li);
		return queue;
	}
	
	private void Keys(Node x, Queue<K> queue,K lo, K li) {
		
		if(x == null) return;
		int cmplo = lo.compareTo(x.k);
		int cmpli = li.compareTo(x.k);
		if(cmplo < 0) Keys(x.left,queue,lo,li);
		if(cmplo <= 0 && cmpli >= 0) queue.add(x.k);
		if(cmpli > 0 )Keys(x.left,queue,lo,li);
		
	}
	public void imp() {
		IT(root);
		
	}
	public void IT(Node n) {

		
		if(n != null) {
			System.out.println(n.k+" ");
			IT(n.left);
			IT(n.right);
		}
			
		
	}
	public String toString() {
		StringBuffer dbs = new StringBuffer();
		for(int i = 0; i < size();i++) { dbs.append(root.k.toString());}
		return dbs.toString();
	}
	

	@Override
	public int size(K lo, K hi) {
		if(hi.compareTo(lo) < 0)return 0;
		else if(contains(hi))
			return rank(hi) - rank(lo)+1;
		else 
			return rank(hi) - rank(lo);
		
	}
	@Override
	public boolean contains(K k) {
		// TODO Auto-generated method stub
		return get(k) != null;
	}
	@Override
	public K ceiling(K e) {
		
		return ceiling(root,e);
	}
	private K ceiling(Node r, K e) {
		if (r == null) return null;
		int cmp = e.compareTo(r.k);
		if (cmp > 0) return ceiling(r.right, e);
		if (cmp < 0) {
			K tmp = ceiling(r.left, e);
			return (tmp != null)? tmp : r.k;
		}
		return r.k;
	}


	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return root != null;
	}
	@Override
	public Iterable<K> keys() {
		
		return null;
	}
	
	public Iterator<K> iterator() {
		
		return new Iterator<K>() {
			Node x =  root;
			@Override
			public boolean hasNext() {
				
				return x != null;
			}

			@Override
			public K next() {
				K key = x.k;
				
				
				return key;
			}
			
		};
	}

}
