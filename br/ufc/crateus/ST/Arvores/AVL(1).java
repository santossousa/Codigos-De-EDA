package br.ufc.crateus.ST;

//import br.ufc.crateus.ST.BST.Node;

@SuppressWarnings("unused")
public class AVL<K extends Comparable<K> ,V> extends BST<K,V> {
	
	@SuppressWarnings("unused")
	private class AVLNode extends Node{

		protected int height;
		public AVLNode(K k, V v) {
			super(k, v);
			height = 0;
			}
		
			
	}
	
	public void put(K k,V value) {
	
	super.root = put(super.root,k,value);
			
	}
	@SuppressWarnings({ "unused", "unchecked" })
	private Node put(Node x,K k ,V value) {
	
		if(x == null) return new AVLNode(k,value);
		int cmp = k.compareTo(x.k);
		if(cmp < 0)x.left = put(x.left,k,value);
		else if(cmp > 0) x.right = put(x.right,k,value);
		else x.v = value;
		

		((AVLNode)x).height = Math.max(height(x.left),height(x.right))+1;
		
	
		return rebalance(x);
	}
	
	@SuppressWarnings("unchecked")
	private Node rebalance(Node x) {
		
		if(getbalance(x) > 1) {
			
			if(getbalance(x.left) < 0) {
				x.left= rotateLeft(x.left);
			}
			x = rotateRight(x);	
		}
		
		if( getbalance(x) < -1) {
			
			if(getbalance(x.right) > 0) {
				x.right = rotateRight(x.right);
			}
			x = rotateLeft(x);
			
		}
	
		((AVLNode)x).height = Math.max(height(x.left), height(x.right))+1;
		
		return x;
	}

	private int getbalance(Node x) {
		
		return height(x.left)- height(x.right);
	}
	
	public int height() {
		return height(root);
	}
	@SuppressWarnings("unchecked")
	private int height(Node x) {
		 return (x == null)? -1 : ((AVLNode)x).height ;
				
	}
	@SuppressWarnings("unchecked")
	private Node rotateRight(Node h) {
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		
		((AVLNode)h).height = Math.max(height(h.left), height(h.right)) + 1;
		((AVLNode)x).height = Math.max(((AVLNode)h).height, height(x.right)) + 1;
					
		return x;		
	}
	@SuppressWarnings("unchecked")
	private Node rotateLeft(Node h) {
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		
		((AVLNode)h).height = Math.max(height(h.left), height(h.right)) + 1;		
		((AVLNode)x).height = Math.max(((AVLNode)h).height, height(x.right)) + 1;
		
		return x;
			
		}

	public void delete(K k) {
		root = delete(root,k);
	}
	@SuppressWarnings("unchecked")
	private Node delete(Node x,K k) {
		if(x == null) return null;
		
		int cmp = k.compareTo(x.k);
		if(cmp < 0 ) x.left = delete(x.left,k);
		else if(cmp > 0) x.right = delete(x.right,k);
		else {
			if(x.left == null) return x.right;
			if(x.right == null) return x.left;
			
			Node t = x;
			x = maxNode(x.left);
			x.right = t.right;
			x.left = deleteMax(x.left);
		}
		((AVLNode)x).height = Math.max(height(x.left), height(x.right))+1;
		
		return rebalance(x);
		
	}
	public void deleteMax() {
		root = deleteMax(root);
	}
	@SuppressWarnings("unchecked")
	private Node deleteMax(Node x) {
		if(x.right == null) return x.left;
		x.right = deleteMax(x.right);
		((AVLNode)x).height = Math.max(height(x.left), height(x.right))+1;
		
		return rebalance(x);
	}
	public void deleteMin() {
		root = deleteMin(root);
	}
	@SuppressWarnings("unchecked")
	private Node deleteMin(Node x) {
		if(x.left == null) return x.right;
		x.left = deleteMin(x.left);
		((AVLNode)x).height = Math.max(height(x.left), height(x.right))+1;
		
		
		
		return rebalance(x);
		
	}
	
		public void imprimir() {
		imprimir(root);
	}
	private void imprimir(Node x) {
		if(x != null) {
			System.out.println("chave : "+x.k);					
			imprimir(x.left);			
			imprimir(x.right);
			
		}
	}

	
	
}
