package CyclicGraph.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
/**
 * Test class to test directed cyclic graph requirement.
 * 
 * @author prashant
 *
 */
public class TestCyclicGraph {
	
	private Node graph;
	
	
	/**
	 * This method creates directed cyclic graph with 10 persons and having atleast 3 first level connections.
	 * Returns start node
	 * @return
	 */
	@Before
	public void createGraph(){
		
		Node prashant = new Person("Prashant");
		Node lina = new Person("Lina");
		Node john = new Person("John");
		Node alex = new Person("Alex");
		Node steve = new Person("Steve");
		Node david = new Person("David");
		Node tina = new Person("Tina");
		Node shashank = new Person("Shashank");
		Node amar = new Person("Amar");
		Node harshal = new Person("Harshal");
		
		Set<Node> prashantChildrens = new HashSet<Node>();
		prashantChildrens.add(lina);
		prashantChildrens.add(john);
		prashantChildrens.add(amar);
		prashant.setChildNodes(prashantChildrens);
		
		Set<Node> linaChildrens = new HashSet<Node>();
		linaChildrens.add(john);
		linaChildrens.add(alex);
		linaChildrens.add(steve);
		lina.setChildNodes(linaChildrens);
		
		Set<Node> johnChildrens = new HashSet<Node>();
		johnChildrens.add(alex);
		johnChildrens.add(steve);
		johnChildrens.add(david);
		john.setChildNodes(johnChildrens);
		
		Set<Node> alexChildrens = new HashSet<Node>();
		alexChildrens.add(steve);
		alexChildrens.add(david);
		alexChildrens.add(tina);
		alex.setChildNodes(alexChildrens);

		Set<Node> steveChildrens = new HashSet<Node>();
		steveChildrens.add(david);
		steveChildrens.add(tina);
		steveChildrens.add(shashank);
		steve.setChildNodes(steveChildrens);

		Set<Node> davidChildrens = new HashSet<Node>();
		davidChildrens.add(tina);
		davidChildrens.add(shashank);
		davidChildrens.add(amar);
		david.setChildNodes(davidChildrens);

		Set<Node> tinaChildrens = new HashSet<Node>();
		tinaChildrens.add(shashank);
		tinaChildrens.add(amar);
		tinaChildrens.add(harshal);
		tina.setChildNodes(tinaChildrens);
		
		Set<Node> shashankChildrens = new HashSet<Node>();		
		shashankChildrens.add(amar);
		shashankChildrens.add(harshal);
		shashankChildrens.add(prashant);
		shashank.setChildNodes(shashankChildrens);

		Set<Node> amarChildrens = new HashSet<Node>();
		amarChildrens.add(harshal);
		amarChildrens.add(alex);
		amarChildrens.add(steve);
		amar.setChildNodes(amarChildrens);

		Set<Node> harshalChildrens = new HashSet<Node>();
		harshalChildrens.add(prashant);
		harshalChildrens.add(lina);
		harshalChildrens.add(amar);
		harshal.setChildNodes(harshalChildrens);
		
		this.graph = prashant;
	}

	
	/**
	 * This method tests Graph has eqaul or more than 10 peoples
	 */
	@Test
	public void testForNumberOfNodesEligibility(){
		Map<String, Boolean> map = testGraphForEligibility();
		Assert.assertTrue("Graph has eqaul or more than 10 peoples", map.get("equalOrMorethan10Nodes"));
	}
	
	/**
	 * Test method for Each person has at least 3, first level connections to other people in the graph
	 * eligibility.
	 */
	@Test
	public void testForFirstLvlChildren(){
		Map<String, Boolean> map = testGraphForEligibility();
		Assert.assertTrue("Each person has at least 3, first levelconnections to other people in the graph", 
				map.get("allHasMoreThan3FirstLvlChildren"));
	}
	
	/**
	 * Test method for cyclic graph
	 */
	@Test
	public void testCyclicGraph(){
		Map<String, Boolean> map = testGraphForEligibility();
		Assert.assertTrue("The graph is cyclic", map.get("cyclic"));
	}
	/**
	 * Method to return result for basic graph requirement
	 * 
	 * @return
	 */
	private Map<String, Boolean> testGraphForEligibility(){
		Map<String, Boolean> resultMap = new HashMap<String, Boolean>();
		Node startNode = this.graph;
		
		boolean allHasMoreThan3FirstLvlChildren = true;
		boolean isCyclic = false;
		
		Stack<Node> stack = new Stack<Node>();
		Set<Node> visited = new HashSet<Node>();
		stack.push(startNode);
		visited.add(startNode);
		while(!stack.isEmpty()){
			Node node= stack.pop();
			if(node.childrens().size()<3){
				allHasMoreThan3FirstLvlChildren = false;
			}
			Iterator<Node> childIter = node.childrens().iterator();
			while(childIter.hasNext()){
				Node childNode = childIter.next();
				if(!visited.contains(childNode)){
					stack.push(childNode);
					visited.add(childNode);
				}else{
					isCyclic = true;
				}
			}
		}
		resultMap.put("equalOrMorethan10Nodes", visited.size()>=10);
		resultMap.put("allHasMoreThan3FirstLvlChildren", allHasMoreThan3FirstLvlChildren);
		resultMap.put("cyclic", isCyclic);
		return resultMap;
	}
	
	/**
	 * This method test find connection method in GraphUtil.
	 * 
	 */
	@Test
	public void testFindConnections(){
		GraphUtil graphUtil = new GraphUtil();
		Map<Node, List<Node>> connectionMap = graphUtil.findConnections(this.graph, 2);
		Assert.assertEquals("Map size should be 10 as total 10 persons added", 10, connectionMap.size());
		Assert.assertTrue("Expected Harshal 2 level connections...", 
				connectionMap.get(new Person("Harshal")).toString().equals("[Prashant, Lina, Amar, Alex, Steve]"));
		//System.out.println(connectionMap);
	}
	
	/**
	 * This method tests connected method in graph util
	 */
	@Test
	public void testConnected1LvlApart(){
		GraphUtil graphUtil = new GraphUtil();
		int level = graphUtil.connected(new Person("Prashant"), new Person("Lina"),this.graph);
		Assert.assertEquals("Prashant and Lina 1 levels apart...", 1, level);

	}
	
	@Test
	public void testConnected2LvlsApart(){
		GraphUtil graphUtil = new GraphUtil();
		int level = graphUtil.connected(new Person("Prashant"), new Person("Harshal"),this.graph);
		Assert.assertEquals("Prashant and Harshal 2 levels apart...", 2, level);
	}
	
	@Test
	public void testConnected3LevlsApart(){
		
		GraphUtil graphUtil = new GraphUtil();
		//graphUtil.createGraph();
		int level = graphUtil.connected(new Person("Alex"), new Person("Lina"), this.graph);
		Assert.assertEquals("Alex and Lina 3 levels apart...", 3, level);
		
		level = graphUtil.connected(new Person("Prashant"), new Person("David"), this.graph);
		Assert.assertEquals("Prashant and David 3 levels apart...", 3, level);
		//System.out.println(level);
	}
	
	@Test 
	public void testRange(){
		AppleTest ff= new AppleTest();
        List<String> s = ff.rnageStream(10).orElse(null);
        Assert.assertTrue(s != null);
        Assert.assertEquals("Epected Flip", "flip", s.get(2));
        Assert.assertEquals("Epected Flip", "flop", s.get(4));
        }
}
