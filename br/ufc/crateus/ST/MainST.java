package br.ufc.crateus.ST;

public class MainST {
	@SuppressWarnings("rawtypes")
	public static void main(String[] agrs) {
		AVL v = new AVL();
		
		v.put(2, "");
		v.put(5, "");	
		v.put(8, "");
		v.put(10, "");
		v.put(13, "");
		v.put(15, "");
		v.put(17, "");
		v.put(20, "");
		v.put(23, "");
		v.put(25, "");
		v.put(30, "");
		v.put(18, "");
		
		
		//v.imprimir();
		System.out.println("deletados....");
		v.deleteMin();
		v.deleteMin();
		v.deleteMin();
		//v.deleteMax();
		//v.deleteMax();
		BST n = new BST();
		n.put(4, "");
		n.put(2, "");
		n.put(6, "");
		n.put(1, "");
		n.put(3, "");
		n.put(5, " ");
		n.put(7, "");
		
		

		
		
		System.out.println(n.height());
		System.out.println(n.nivel(4));
		
		
	//	v.imprimir();
		n.imp();
		
		
		
		

		
	}
}
