package CyclicGraph.graph;

import java.util.Set;

/**
 * Interface for nodes in graph
 * 
 * @author prashant
 */
public interface Node {
	
	/**
	 * Method to return noden name
	 * @return
	 */
	public String name();
	
	/**
	 * method to return child nodes.
	 * @return
	 */
	public Set<Node> childrens();
	
	/**
	 * method to set child nodes
	 * @param chileNodes
	 */
	public void setChildNodes(Set<Node> chileNodes);
}
