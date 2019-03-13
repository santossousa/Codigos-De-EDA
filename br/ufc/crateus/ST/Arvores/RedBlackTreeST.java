package br.ufc.crateus.ST;

public class RedBlackTreeST<K extends Comparable<K>, V> extends BST<K,V>{
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	
	private class RBNode extends Node {
		K k;
		V v;
	
		int n;
		int cot;
		
		boolean color;
		
		public RBNode(K k,V v) {
			super(k,v);
			this.n = 0;
			
			this.color = RED;
		}
	}

	private boolean isB(Node x) {
		if(x == null)return false;
		return true;
		//return (x.color == RED || x.color == BLACK);
	}
	private int heightBlack;
	@Override
	public void put(K k, V v) {
		root = put(root,k,v);
		if(isBLACK(root))heightBlack++;
		((RBNode)root).color = BLACK;
		
	}
	private Node put(Node r,K k, V v) {
		if(r == null) return new RBNode(k,v);
		
		int cmp = k.compareTo(r.k);
		if(cmp <  0) r.left = put(r.left,k,v);
		else if(cmp > 0) r.right = put(r.right,k,v);
		else r.v = v;
		
		if(getColor(r.left) == BLACK && getColor(r.right) == RED) r = rotateLeft(r);
		if(getColor(r.left) == RED && getColor(r.left.left) == RED) r = rotateRight(r);System.out.println(isB(r));
		if(getColor(r.left) == RED && getColor(r.right) == RED) flipColors(r);
		
		r.n = size(r.left)+size(r.right)+1;
		
		return r;
				
		
	}
	public int heightBlack() {
		return heightBlack;
	}
	private Node rotateLeft(Node h) {
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		setColor(x, getColor(h));
		setColor(h, RED);
		
		h.n = size(h.left) + size(h.right) + 1;
		x.n = h.n + size(x.right) + 1;
		return x;
	}
	public int NoPreto() {
		 return contaNegro(root);
	}
	private int contaNegro(Node x) {
		int esq = 0,dir = 0;
		int i = 0;
		//RBNode node =  (
		
		if(x == null)return 0;
		if(isBLACK(x))return 1+contaNegro(x.left)+contaNegro(x.right);
		else return contaNegro(x.left)+contaNegro(x.right);
		
	
			
				
						
	
	}
	private boolean isBLACK(Node x) {
		if(x == null) return false;
		return (((RBNode)x).color == RED);
	}
	private Node rotateRight(Node h) {
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		setColor(x, getColor(h));
		setColor(h, RED);
		
		h.n = size(h.left) + size(h.right) + 1;
		x.n = h.n + size(x.right) + 1;
		return x;
	}
	
	private void flipColors(Node h) {
		setColor(h, RED);
		setColor(h.left, BLACK);
		setColor(h.right, BLACK);
	}
	
	@SuppressWarnings("unchecked")
	private void setColor(Node n, boolean color) {
		RBNode node = (RBNode)n;
		node.color = color;
	}
	
	@SuppressWarnings("unchecked")
	private boolean getColor(Node n) {
		if (n == null) return BLACK;
		RBNode node = (RBNode)n;
		return node.color;
	}
	public void imprimir() {
		imprimir(root);
		
	}
	private  void imprimir(Node x) {
		if(x != null) {
			System.out.println(x.k+","+((RBNode)x).color);
			imprimir(x.left);
			imprimir(x.right);

		}
	}
	public int getA() {
		return getAltura(root);
	}
	private int getAltura(Node n) {
		if(n == null)return 0;
		//Node e = n.right;
		int altu = 0;
		if(((RBNode)n).color == BLACK) {
			altu = Math.max(getAltura(n.left),getAltura(n.right))+1;
		}
		else {
		altu = Math.max(getAltura(n.left),getAltura(n.right));
		}
		return altu;
	}
	public static void main(String[] args) {
		RedBlackTreeST r = new RedBlackTreeST();
		
		r.put(2, "");
		r.put(5, "");
		r.put(8, "");
		r.put(10, "");
		r.put(13, "");
		r.imprimir();
		
		System.out.println(r.heightBlack());
		System.out.println(r.getA());

	}
	

}
