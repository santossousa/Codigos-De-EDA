package br.ufc.crateus.ST;

public class TableSymbol {

	public static void main(String[] args) {
		
		SeparateChaininghST ts = new SeparateChaininghST<>();
		//ts.inteiros(1000);//88 e 114
		//ts.inteiros(10000);
		//
		//ts.inteiros(100000);//67 e 143
		ts.inteiros(1000000);//63 e 145
		//79 e 130
		//
	} 
	/*para valores pequenos com 10³ a nao demora muito
	 * com valores 10⁴ a lista curta ja vai mundando e a lista grande tambem,
	 * assim sucessivamente com 10⁵ e 10⁶ as lista sao sa quase do mesmo tamanho
	 * 
	*/

}
