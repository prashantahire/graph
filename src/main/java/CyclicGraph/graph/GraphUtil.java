package CyclicGraph.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 * This class provides methods related to graph 
 * 
 * @author prashant
 *
 */
public class GraphUtil {
	
	/**
	 * Based on graph node input this method iterates through graph and
	 *  prepares map of all the nodes/persons in graph along with 
	 *  connections list based on depth provided. This method uses breadth-first order
	 *  to visit all the persons in graph
	 * @param node
	 * @param depth
	 * @return
	 */
	public Map<Node, List<Node>> findConnections(Node node, int depth){
		Map<Node, List<Node>> nodeMap = new HashMap<Node, List<Node>>();
		
		Queue<Node> queue = new LinkedList<Node>();
		Set<Node> visited = new HashSet<Node>();
		queue.add(node);
		visited.add(node);
		while(!queue.isEmpty()){
			Node tempNode = queue.poll();
			nodeMap.put(tempNode, getConnectionList(tempNode, depth));
			Iterator<Node> childIter = tempNode.childrens().iterator();
			while(childIter.hasNext()){
				Node childNode = childIter.next();
				if(!visited.contains(childNode)){
					queue.add(childNode);
					visited.add(childNode);
				}
			}
		}
		return nodeMap;
	}
	
	/**
	 * THis method return list of connection for provide node based on depth.
	 * @param node
	 * @param depth
	 * @return
	 */
	private List<Node> getConnectionList(Node node, int depth){
		List<Node> connectionList = new LinkedList<Node>();
		Stack<Node> stack = new Stack<Node>();
		Set<Node> visited = new HashSet<Node>();
		stack.push(node);
		visited.add(node);
		int count = 0;
		while(!stack.isEmpty()){
			count++;
			Node tempnode = stack.pop();
			Iterator<Node> childIter = tempnode.childrens().iterator();
			while(childIter.hasNext()){
				Node childNode = childIter.next();
				if(!visited.contains(childNode)){
					stack.push(childNode);
					visited.add(childNode);
					connectionList.add(childNode);
				}
			}
			if(count>= depth)
				break;
		}
		return connectionList;
	}
	
	/**
	 * This method takes node1 and node2 as input and check whether these two nodes are connected.
	 * If nodes are connected then it return connection level otherwise returns 0.
	 * This method uses Depth-first order to traverse the graph.
	 * 
	 * @param node1
	 * @param node2
	 * @return
	 */
	public int connected(Node node1, Node node2, Node graph){
		Stack<Node> stack = new Stack<Node>();
		Set<Node> visited = new HashSet<Node>();
		stack.push(graph);
		visited.add(graph);
		boolean firstNodeFound = false;
		boolean connectingNodeFound = false;
		int level = 0;
		while(!stack.isEmpty() && !connectingNodeFound){
			Node node= stack.pop();
			if(node1.equals(node)){
				firstNodeFound = true;
				visited.clear();
				stack.clear();
			}
			if(firstNodeFound){
				level++;
			}				
			Iterator<Node> childIter = node.childrens().iterator();
			while(childIter.hasNext()){
				Node childNode = childIter.next();
				if(firstNodeFound && childNode.equals(node2)){
					connectingNodeFound = true;
					break;
				}
				if(!visited.contains(childNode)){
					stack.push(childNode);
					visited.add(childNode);
				}
			}
		}
		if(connectingNodeFound){
			return level;
		}else
			return 0;
	}
}
