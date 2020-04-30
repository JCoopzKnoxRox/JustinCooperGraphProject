package graph.impl;

public class Edge implements Comparable<Edge> {
	public String n1Name;
	public String n2Name;
	public int weight;
	
	public Edge(String n1, String n2, int w) {
		this.n1Name = n1;
		this.n2Name = n2;
		this.weight = w;
	}
	public int compareTo(Edge other){
	    return this.weight - other.weight;
	  }
	public String toString() {
		
		    return this.n1Name + " " + this.weight + " " + this.n2Name;
	}
	
	
}
