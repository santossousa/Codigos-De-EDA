package br.ufc.crateus.ST;

public class SubStringlenght {
	public int contSubString(String text,int tam) {
		Tst<Integer> subS = new Tst<>();
		int maxTxt = text.length() - tam + 1;
		for(int i = 0; i < maxTxt;i++) {
			String subString = text.substring(i, i + tam);
			subS.put(subString, 0);
		}
		return subS.size();
		
	}
	public static void main(String[] args) {
		SubStringlenght s = new SubStringlenght();
		String sub = "cgcgggcgc";
		int j = Integer.parseInt(args[0]);
				
		System.out.println("lenght : "+ j+""+s.contSubString(sub, j));
	}

}
