package graph.impl;

import java.util.Collection;
import java.util.Map;

import graph.IGraph;
import graph.INode;
import graph.NodeVisitor;
import java.util.*;

/**
 * A basic representation of a graph that can perform BFS, DFS, Dijkstra,
 * and Prim-Jarnik's algorithm for a minimum spanning tree.
 * 
 * @author jspacco
 *
 */
public class Graph implements IGraph
{
    private Map <String, INode> nodes = new HashMap<>();
    /**
     * Return the {@link Node} with the given name.
     * 
     * If no {@link Node} with the given name exists, create
     * a new node with the given name and return it. Subsequent
     * calls to this method with the same name should
     * then return the node just created.
     * 
     * @param name
     * @return
     */
    public INode getOrCreateNode(String name) {
        //throw new UnsupportedOperationException("Implement this method");
        if(nodes.containsKey(name)) {
        	return nodes.get(name);
        }
        INode newNode = new Node(name);
        nodes.put(name, newNode);
        return newNode;
        
    }

    /**
     * Return true if the graph contains a node with the given name,
     * and false otherwise.
     * 
     * @param name
     * @return
     */
    public boolean containsNode(String name) {
        //throw new UnsupportedOperationException("Implement this method");
    	if (nodes.containsKey(name)){
    		return true;
    	}
    	else {
    		return false;
    	}
    }

    /**
     * Return a collection of all of the nodes in the graph.
     * 
     * @return
     */
   
    public Collection<INode> getAllNodes() {
        //throw new UnsupportedOperationException("Implement this method");
    	return nodes.values();
    
    
    }
    
    
    /**
     * Perform a breadth-first search on the graph, starting at the node
     * with the given name. The visit method of the {@link NodeVisitor} should
     * be called on each node the first time we visit the node.
     * 
     * 
     * @param startNodeName
     * @param v
     */
    public void breadthFirstSearch(String startNodeName, NodeVisitor v)
    {
        // TODO: Implement this method
        //throw new UnsupportedOperationException("Implement this method");
    	Set<INode> visited = new HashSet<>();
    	Queue<INode> toVisit = new LinkedList<>();
    	toVisit.add(nodes.get(startNodeName));
    	while(!toVisit.isEmpty()) {
    		INode x = toVisit.poll();
    		if(visited.contains(x)) {
    			continue;
    		}
    		v.visit(x);
    		visited.add(x);
    		for(INode n : x.getNeighbors()) {
    			if(!visited.contains(n)) {
    				toVisit.add(n);
    			}
    		}
    	}
    }
    

    /**
     * Perform a depth-first search on the graph, starting at the node
     * with the given name. The visit method of the {@link NodeVisitor} should
     * be called on each node the first time we visit the node.
     * 
     * 
     * @param startNodeName
     * @param v
     */
    public void depthFirstSearch(String startNodeName, NodeVisitor v)
    {
        // TODO: implement this method
        //throw new UnsupportedOperationException("Implement this method");
    	Set<INode> visited = new HashSet<>();
    	Stack<INode> toVisit = new Stack<>();
    	toVisit.push(nodes.get(startNodeName));
    	while(!toVisit.isEmpty()) {
    		INode x = toVisit.pop();
    		if(visited.contains(x)) {
    			continue;
    		}
    		v.visit(x);
    		visited.add(x);
    		for(INode n : x.getNeighbors()) {
    			if(!visited.contains(n)) {
    				toVisit.push(n);
    			}
    		}
    	}
    }

    /**
     * Perform Dijkstra's algorithm for computing the cost of the shortest path
     * to every node in the graph starting at the node with the given name.
     * Return a mapping from every node in the graph to the total minimum cost of reaching
     * that node from the given start node.
     * 
     * <b>Hint:</b> Creating a helper class called Path, which stores a destination
     * (String) and a cost (Integer), and making it implement Comparable, can be
     * helpful. Well, either than or repeated linear scans.
     * 
     * @param startName
     * @return
     */
    public Map<INode,Integer> dijkstra(String startName) {
        // TODO: Implement this method
        //throw new UnsupportedOperationException("Implement this method");
    	Map <INode, Integer>result = new HashMap<>();
    	PriorityQueue<Path> todo = new PriorityQueue<>();
    	todo.add(new Path(startName, 0));
    	while(result.size() < nodes.size()) {
    		Path next = todo.poll();
    		INode node = nodes.get(next.node);
    		//System.out.println(next.toString());
    		if (result.containsKey(node)) {
    			continue;
    		}
    		int cost = next.cost;
    		result.put(node, cost);
    		for(INode n : node.getNeighbors()) {
    			//Path temp = new Path(n.getName(), cost + todo.poll().cost);
    			//System.out.println(temp.toString());
    			todo.add(new Path(n.getName(), cost + n.getWeight(node)));
    		}
    		
    	}
    	return result;
    }
    
    /**
     * Perform Prim-Jarnik's algorithm to compute a Minimum Spanning Tree (MST).
     * 
     * The MST is itself a graph containing the same nodes and a subset of the edges 
     * from the original graph.
     * 
     * @return
     */
    public IGraph primJarnik() {
        //TODO Implement this method
        //throw new UnsupportedOperationException();
    	Graph mst = new Graph();
    	INode start = nodes.values().iterator().next();
    	PriorityQueue<Edge> todo = new PriorityQueue<>();
    	mst.getOrCreateNode(start.getName());
    	//System.out.println(this.getAllNodes().size());
    	Collection<INode> nodeNeighbors = start.getNeighbors();
    	//System.out.println(nodeNeighbors);
    	for(INode n: nodeNeighbors) {
			if(!mst.containsNode(n.getName())) {
			todo.add(new Edge (start.getName(), n.getName(), n.getWeight(start)));
			//start.addUndirectedEdgeToNode(n, start.getWeight(n));
			System.out.println(todo);
			
			}
    	}
//    	for(Edge e: todo) {
//    		System.out.println(e.toString());
//    	}
    	System.out.println(mst.getAllNodes().size());
    	while (mst.getAllNodes().size() < this.getAllNodes().size()) {
    		//System.out.println(todo);
				nodeNeighbors = nodes.get(todo.peek().n2Name).getNeighbors();
				String s1Peek = todo.peek().n1Name;
				String s2Peek = todo.peek().n2Name;
				int peekerWeight = todo.peek().weight;
				//int pathPeeker = todo.peek().cost;
				if(!mst.containsNode(s2Peek)) {
				mst.getOrCreateNode(s2Peek);
				mst.nodes.get(s1Peek).addUndirectedEdgeToNode(mst.nodes.get(s2Peek), peekerWeight);
				todo.poll();
				}
				for(INode nNodes: nodeNeighbors) {
					//peeker.addUndirectedEdgeToNode(nNodes, pathPeeker);
					if(!mst.containsNode(nNodes.getName())) {
					todo.add(new Edge (s2Peek, nNodes.getName(), nodes.get(s2Peek).getWeight(nNodes)));
					}
					//System.out.println(todo);
				}
				
				System.out.println(todo);
				System.out.println(mst.getAllNodes().size());
				

    	
    	}
//    	for (INode end : mst.getAllNodes()) {
//    		System.out.print(end.getName() + " ");
//    	}
//    	System.out.println(mst.getAllNodes().size());'
    	//System.out.println(todo);
    	return mst;	
    }
}