package CyclicGraph.graph;

import java.util.Set;

/**
 * This class implements node behavior
 * 
 * @author prashant
 *
 */
public class Person implements Node{
	
	/**
	 * person name
	 */
	private String personName;
	
	/**
	 * set of connections
	 */
	private Set<Node> childNodes;
	
	/**
	 * Constructor
	 * @param name
	 */
	public Person(String name){
		this.personName = name;
	}
	
	/**
	 * This method return name
	 */
	public String name() {
		return personName;
	}
	
	/**
	 * This method return child nodes
	 */
	public Set<Node> childrens() {
		return childNodes;
	}
	
	/**
	 * set child nodes
	 */
	public void setChildNodes(Set<Node> chileNodes){
		this.childNodes = chileNodes;
	}
	
	/**
	 * Override equal implementation
	 */
	@Override
	public boolean equals(Object object){
		
		if(object instanceof Person)
		{
			if(this.personName.equals(((Person)object).personName)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Override hashcode implementation
	 */
	@Override
	public int hashCode(){
		return 1;
	}
	
	@Override
	public String toString(){
		return personName;
	}
}
