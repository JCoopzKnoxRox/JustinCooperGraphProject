package graph.impl;

import java.util.Collection;

import graph.INode;
import java.util.*;

/**
 * Class to represent a single node (or vertex) of a graph.
 * 
 * Node can be used for either directed or undirected graphs, as well as
 * for weighted or unweighted graphs. For unweighted graphs, use something like 1 for all 
 * of the weights. For undirected graphs, add a directed edge in both directions.
 * 
 * You want to make as many operations O(1) as possible, which means you will
 * probably use a lot of Maps.
 * 
 * Side note: You can tell that I come from a networking background and not a mathematical
 * background because I almost always use the term "node" instead of "vertex".
 * 
 * @author jspacco
 *
 */
public class Node implements INode
{
	   
    /**
     * Create a new node with the given name. The newly created node should
     * have no edges.
     * 
     * @param name
     */
	private Map<String, ArrayList<INode>> nodes = new HashMap<>();
	private Map<INode, Integer> neighbors = new HashMap<>();
	private String nam;
    public Node(String name) {
        //throw new UnsupportedOperationException("Implement this method");
    	this.nam = name;
//    	ArrayList<INode> neighbors = new ArrayList<>();
//    	nodes.put(name, neighbors);
    	
    }
    
    
    
    /**
     * Return the name of the node, which is a String.
     * 
     * @return
     */
    public String getName() {
        //throw new UnsupportedOperationException("Implement this method");
    	return this.nam;
    }

    /**
     * Return a collection of nodes that the current node is connected to by an edge.
     * 
     * @return
     */
    public Collection<INode> getNeighbors() {
        //throw new UnsupportedOperationException("Implement this method");
    	return neighbors.keySet();
    }
    
    /**
     * Add a directed edge to the given node using the given weight.
     * 
     * @param n
     * @param weight
     */
    public void addDirectedEdgeToNode(INode n, int weight) {
        //throw new UnsupportedOperationException("Implement this method");
    	neighbors.put(n, weight);
//    	if(!nodes.containsKey(this.nam)) {
//    	nodes.get(this.nam).add(n);
//    	}
    	
    }
    
    /**
     * Add an undirected edge to the given node using the given weight.
     * Remember than an undirected edge can be implemented using two directed edges.
     * 
     * @param n
     * @param weight
     */
    public void addUndirectedEdgeToNode(INode n, int weight) {
        //throw new UnsupportedOperationException("Implement this method");
    	
    	addDirectedEdgeToNode(n, weight);
    	//System.out.println(this.getName());
    	//System.out.println(n.getName());
    	n.addDirectedEdgeToNode(this, weight);
    	
    	//System.out.println(neighbors.get(this));
    	//System.out.println(neighbors.get(n));
    	
    	
    }

    /**
     * Remove the directed edge to the given node.
     * 
     * If there is no edge to the given node, throw
     * IllegalStateException (which is a type of runtime exception).
     * 
     * @param n
     * @throws IllegalStateException
     */
    public void removeDirectedEdgeToNode(INode n) {
        //throw new UnsupportedOperationException("Implement this method");
    	neighbors.remove(n);
    }
    
    /**
     * Remove an undirected edge to the given node. This means removing
     * the edge to the given node, but also any edge from the given
     * node back to this node.
     * 
     * Throw a IllegalStateException if there is no edge to the given node.
     * 
     * @param n
     * @throws IllegalStateException
     */
    public void removeUndirectedEdgeToNode(INode n) {
        //throw new UnsupportedOperationException("Implement this method");
    	removeDirectedEdgeToNode(n);
    	n.removeDirectedEdgeToNode(this);
    }
    
    /**
     * Return true if there is an edge to the given node from this node,
     * and false otherwise.
     * 
     * @param other
     * @return
     */
    public boolean hasEdge(INode other) {
        //throw new UnsupportedOperationException("Implement this method");
    	if (neighbors.containsKey(other)) {
    		return true;
    	}
    	return false;
    }
    
    /**
     * Get the weight of the edge to the given node.
     * 
     * If no such edge exists, throw {@link IllegalStateException}
     * 
     * @param n
     * @return
     * @throws IllegalStateException
     */
    public int getWeight(INode n) {
        //throw new UnsupportedOperationException("Implement this method");
//    	System.out.print(n.getName());
//    	System.out.print(neighbors.get(n));
    	//System.out.println(neighbors.get(n));
    	return neighbors.get(n);
    }
}
