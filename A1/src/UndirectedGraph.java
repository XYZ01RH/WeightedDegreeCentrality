import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Riley Hanson
 * CMSC 412: Social Networks
 * Assignment 1 - Problem 5
 * Undirected Weighted Graph
 * 
 * Calculates and outputs the unnormalized weight of nodes
 *
 */
public class UndirectedGraph {

	private int m;
	private int n;
	private int[][] graph;
	private File f;

	public UndirectedGraph() {
		f = new File("wdegree.txt");
	}

	public void setN(int x) {
		this.n = x;
		graph = new int[n][n];
	}

	public void setM(int y) {
		this.m = y;
	}

	public void setUVW(int[] xyz) {
		int x = xyz[0];
		int y = xyz[1];
		int z = xyz[2];
		graph[x-1][y-1] = z;
		graph[y-1][x-1] = z;
	}
	
	public void setK() {
		try {
			PrintWriter pw = new PrintWriter(f);
			int k = 0;
			
			for(int i = 0; i < this.n; i++) {
				for(int j = 0; j < this.n; j++) {
					if(graph[i][j] != 0) {
						k += graph[i][j];					
					} 
				}
				pw.println(k);
				k = 0;
			}
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public int getN() {
		return this.n;
	}

	public int getM() {
		return this.m;
	}
	
	public File getK() {
		return this.f;
	}

//	public String toString() {
//		int x;
//		String s = "";
//		String str = "";
//		for(int i = 0; i < this.n; i++) {
//			for(int j = 0; j < this.n; j++) {
//				x = graph[i][j];
//				s = Integer.toString(x);
//				str += s + " ";
//			} str = "";
//		}
//		return str;
//	}
	
	/**
	 * @param args
	 * run program with sample graph.txt file as args
	 */
	public static void main(String[] args) {
		String f = null;
		String line = null;
		String regex = " ";
		int uvw[] = new int [3];
		File output;

		if(args[0] != null) {
			f = args[0];
			BufferedReader br;

			try {
				br = new BufferedReader( new FileReader (f));
				UndirectedGraph g = new UndirectedGraph();

				line = br.readLine();
				String str[] = line.split(regex);
				g.setN(Integer.parseInt(str[0]));
				g.setM(Integer.parseInt(str[1]));
				
				for(int i = 0; i < g.getM(); i++) {
					line = br.readLine();
					str = line.split(regex);
					uvw[0] = Integer.parseInt(str[0]);
					uvw[1] = Integer.parseInt(str[1]);
					uvw[2] = Integer.parseInt(str[2]);
					g.setUVW(uvw);
				}
				
				g.setK();
				//System.out.print(g.toString());
				output = g.getK();
				br.close();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

