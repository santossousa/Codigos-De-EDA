package br.ufc.crateus.ST;

import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class TreeDigital<V> implements ST<String, V>{
	
	private static final int R = 256;
	private Node root = new Node();
	private static class Node{
		Object value;
		Node[] next = new Node[R]; 
		protected int size;
		public Node() {
			
		}
		
		
	}
	@Override
	public void put(String k, V v) {
		root = put(root,k,v,0);
		
		
	}
	private Node put(Node r,String k,V v,int i) {
		if(r == null) r = new Node();
		if(i == k.length()){
			r.value = v;
			return r;
		}
		char c = k.charAt(i);
		r.next[c] = put(r.next[c],k,v,i+1);
		return r;
	}
	
	@Override
	public boolean contains(String k) {
				return get(k)!= null;
	}
	@Override
	public boolean isEmpty() {
		return root == null;
	}
	@Override
	public void delete(String k) {
		root = delete(root,k,0);
	}
	private Node delete(Node r, String k,int i) {
		if(r == null)return null;
		if(i == k.length()) r.value = null;
		else {
			char ch = k.charAt(i);
			r.next[ch] = delete(r.next[ch],k, i + 1);
		}
		if(r.value != null)return r;
		
		for(char cc = 0; cc < R;cc++) 
			if(r.next[cc] != null)return r;
			return null;
		
	}
	@Override
	public V get(String k) {
		Node n = get(root,k,0);
		if(n == null)return null;
		  return (V)n.value;
	}
	private Node get(Node r,String k,int i) {
		if(r == null)return null;
		if(i == k.length()) return r;
		char ch = k.charAt(i);
		return get(r.next[ch],k,i+1);
	}
	@Override
	public int size() {
		return size(root);
	}
	private int size(Node r) {
		if(r == null) return 0;
		int cnt = 0;
		if(r.value != null)cnt++;
		for(char c = 0;c  < R;c++)
			cnt+=size(r.next[c]);
		return cnt;
	}
	@Override
	public Iterable<String> keys() {
		
		return keysWinthPrefix("");
	}
	public Iterable<String> keysWinthPrefix(String pre){
		Queue<String> keys = new LinkedList<>();
		collect(get(root,pre,0),pre,keys);
		return keys;
	}
	private void collect(Node x,String pre,Queue<String> keys) {
		if(x == null)return;
		if(x.value != null) keys.add(pre);
		for(char c = 0;c < R;c++)
			collect(x.next[c], pre+c, keys);
	}
	public Iterable<String> keysThatMacth(String pat){
		Queue<String> keys = new LinkedList<>();
		collect(root,"",pat,keys);
		return keys;
	}
	protected void collect(Node r,String pre,String pat,Queue<String> keys) {
		//int d = pre.length();
		if(r == null)return;
		if(pre.length() == pat.length() && r.value != null)keys.add(pre);
		if(pre.length() == pat.length())return;
		
		char prox = pat.charAt(pre.length());
		for(char c = 0 ;c < R;c++)
			if(prox == '.' || prox == c)
				collect(r.next[c], pre + c, pat, keys);
		
	}
	
	public String longestPrefixOf(String s) {
		int length = longestPrefixOf(root, s, 0, 0);
		return s.substring(0,length);
		
	}
	private int search(Node r,String s,int d,int length) {
		if(r == null)return length;
		if(r.value != null) length = d;
		if(d == s.length())return length;
		char c = s.charAt(d);
		return search(r.next[c], s, d+1, length);
	}

    private int longestPrefixOf(Node x, String query, int d, int length) {
        if (x == null) return length;
        if (x.value != null) length = d;
        if (d == query.length()) return length;
        char c = query.charAt(d);
        return longestPrefixOf(x.next[c], query, d+1, length);
    }

	
	
	

    public String floor(String key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        return floor(root, key, 0, new StringBuilder(), null, true);
    }

    private String floor(Node node, String key, int digit, StringBuilder prefix, String lastKeyFound,
                         boolean mustBeEqualDigit) {
        if (node == null) {
            return null;
        }

        if (prefix.toString().compareTo(key) > 0) {
            return lastKeyFound;
        }

        if (node.value != null) {
            lastKeyFound = prefix.toString();
        }

        char currentChar;

        if (mustBeEqualDigit && digit < key.length()) {
            currentChar = key.charAt(digit);
        } else {
            currentChar = R - 1;
        }

        for (char nextChar = currentChar; nextChar > 0; nextChar--) {
            if (node.next[nextChar] != null) {
                if (nextChar < currentChar) {
                    break;
                }

                lastKeyFound = floor(node.next[nextChar], key, digit + 1, prefix.append(nextChar), lastKeyFound, mustBeEqualDigit);

                if (lastKeyFound != null) {
                    return lastKeyFound;
                }
                prefix.deleteCharAt(prefix.length() - 1);
            }

            // nextChar value never becomes less than zero in the for loop, so we need this extra validation
            if (nextChar == 0) {
                break;
            }
        }

        return lastKeyFound;
    }
    public String ceiling(String key) {
    	if(key == null) {
    		return null;
    	}
    	return ceiling(root,key,0,new StringBuilder());
    }
    private String ceiling(Node r,String key,int d,StringBuilder prefix) {
    	if(r == null) return null;
    	
    	if(r.value != null && prefix.toString().compareTo(key) >= 0)return prefix.toString();
    	char ch;
    	if(d == key.length()) {
    		ch = key.charAt(d);
    	}
    	else {
    		ch = 0;
    	}
    	for(char c = ch; c < R;c++ ) {
    		if(r.next[c] != null) {
    			if(c > ch) {
    				break;
    			}
    			String keyfist = ceiling(r.next[c],key,d+1,prefix.append(c));
    			if(keyfist != null) {
    				return keyfist;
    			}
    			
    			prefix.deleteCharAt(prefix.length()-1);
    		}
    	}
    	return null;
    	
    }
    
    public String select(int i) {
    	if(i < 0 && i > size()) {
    		return null;
    	}
    	
    	return select(root,i,new StringBuilder());
    	
    }
    private String select(Node r,int i,StringBuilder prefix) {
    	if(r == null) {
    		return null;
    	}
    	if(r.value != null) {
    		i--;
    	}
    	if(i ==-1) {
    		return prefix.toString();
    	}
    	for(char c = 0; c < R;c++) {
    		if(r.next[c] != null) {
    			if(i - size(r.next[c]) < 0) {
    				return select(r.next[c],i,prefix.append(c));
    			}
    			else {
    				i = i - size(r.next[c]);
    			}
    		}
    	}
    	return null;
    }
    public int rank(String key) {
    	if( key == null)throw new IllegalArgumentException("ueueueueueueueueue");
    	return rank(root,key,0,0);
    }
    private int rank(Node r,String key,int i,int size) {
    	if(r == null || i == key.length()) return 0;
    	if(r.value != null) {
    		if(i < key.length()) {
    			size++;
    		}
    		else {
    			return size;
    		}
    		
    	}
    	char c = key.charAt(i);
    	for(char ch = 0; ch < R;c++) {
    		size+=size(r.next[ch]);
    	}
    	return rank(r.next[c],key,i+1,size);
    	
    }
}