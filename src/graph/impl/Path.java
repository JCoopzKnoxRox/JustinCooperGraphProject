package graph.impl;

public class Path implements Comparable<Path> {
	public String node;
	public int cost;
	
	public Path(String node, int cost) {
		this.node = node;
		this.cost = cost;
	}
	public int compareTo(Path other){
	    return this.cost - other.cost;
	  }

	  public String toString(){
	    return this.node + " with cost "+this.cost;
	  }
	  

}
