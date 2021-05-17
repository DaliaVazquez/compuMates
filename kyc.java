import java.util.ArrayList;
import java.util.Arrays;

public class kyc {
	public ArrayList<ArrayList<String>> gramar;
	public ArrayList<Node>[][] matrix;
	public String cadena; 
	
	public kyc(ArrayList<ArrayList<String>> gramar, String cadena) {
		this.cadena = cadena;
		this.gramar = gramar;
		
		this.matrix = new ArrayList[cadena.length()][cadena.length()];
		
		for(int i=0; i<matrix.length;i++) {
			for(int j=0; j<matrix.length;j++) {
				matrix[i][j] = new ArrayList<Node>();
			}
		}
	}
	
	public boolean ans() {
		
		for(int i = 0; i<matrix.length;i++) {
			for(ArrayList<String> product : gramar) {
				if(product.contains(Character.toString(cadena.charAt(i)))){
					matrix[i][i].add(new Node(product.get(0)));
				}
			}
		}
	
		for(int i = 1; i<matrix.length;i++) {
			for(int j=0;j<matrix.length-i;j++) {
				matrix[j][j+i]=checkN(j,j+i);
			}
		}
		
		for(Node node : matrix[0][matrix.length-1]) {
			if(node.root.equals("S")){
				System.out.println(true);
				System.out.println();
				for(Node nodes : matrix[0][matrix.length-1]) {
					if(nodes.root.equals("S")){
						printNode(nodes);
						break;
					}
				}
				return true; 
			}
		}
		
		System.out.println(false);
		return false; 
	}
	
	
	public void printNode(Node node) {
		if(node.hoja()) {
			System.out.println(node.root);
			printNode(node.der);
			printNode(node.izq);
		}else {
			System.out.println(node.root);
		}
	}
	public ArrayList<Node> checkN(int i, int j){
		ArrayList<Node> res = new ArrayList<Node>();
		int columna = i;
		for(int fila= i+1; fila<matrix.length;fila++) {
			for(int x = 0; x<matrix[fila][j].size();x++) {
				for(int y = 0; y<matrix[i][columna].size();y++) {
					res.addAll(verifica(matrix[i][columna].get(y),matrix[fila][j].get(x)));
				}
			}
			columna++;
		}
		return res;
	}
	
	public ArrayList<Node> verifica(Node a, Node b) {
		String cadenaAChecar = a.root + b.root;
		ArrayList<Node> res = new ArrayList<Node>();
		for(int i=0; i<gramar.size();i++) {
			for(int j= 1; j < gramar.get(i).size(); j++) {
				if(gramar.get(i).get(j).equals(cadenaAChecar)) {
					res.add(new Node(gramar.get(i).get(0),a,b));
				}
			}
		}
		return res; 
	}
	
	
	
	public static void main(String[] args) {
		ArrayList<String> S = new ArrayList<String>(Arrays.asList("S","AB","SS","AC","BD","BA"));
		ArrayList<String> A = new ArrayList<String>(Arrays.asList("A","a"));
		ArrayList<String> B = new ArrayList<String>(Arrays.asList("B","b"));
		ArrayList<String> C = new ArrayList<String>(Arrays.asList("C","SB"));
		ArrayList<String> D = new ArrayList<String>(Arrays.asList("D","SA"));
		String cadena = "baabb";
		
		ArrayList<ArrayList<String>> gramar = new ArrayList<ArrayList<String>>(Arrays.asList(S,A,B,C,D));
		
		kyc a = new kyc(gramar, cadena);
		a.ans();
		
	}
	
}

class Node{
	public String root;
	public Node der;
	public Node izq;
	
	public Node(String raiz) {
		this.root = raiz; 
	}
	
	public Node(String raiz, Node der, Node izq) {
		this.root = raiz;
		this.der = der;
		this.izq = izq; 
	}
	
	public boolean hoja() {
		return der != null && izq != null; 
	}
}
