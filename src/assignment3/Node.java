/* WORD LADDER Node.java
 * EE422C Project 3 submission by
 * Replace <...> with your actual data.
 * Ali Ziyaan Momin
 * AZM259
 * 16470
 * Aaron Babber
 * AAB3456
 * 16480
 * Slip days used: 0
 * Git URL: https://github.com/AABabber/EE_422C_Project3.git
 * Fall 2016
 */

package assignment3;

import java.util.*;

public class Node {
	
	private String word;	// Data stored in this Node instance
	private Node previousNode;	// Parent of this instance
	private List<Node> nextList;	// List of Nodes with words that differ from this one by one letter
	
	public Node(String word, Node prev)
	{
		this.setWord(word);
		this.setPreviousNode(prev);
		nextList = new ArrayList<Node>();	// Using private variable rather than setter for clarity
	}
	
	// ----------------------------------- Getter & Setter Methods ----------------------------------- //
	
	/**
	 * Getter method for word instance variable
	 * 
	 * @return String stored in this node
	 */
	public String getWord() 
	{
		return word;
	}
	
	/**
	 * Setter method for word instance variable
	 * 
	 * @param word is the String with which to overwrite this.word
	 */
	public void setWord(String word) 
	{
		this.word = word;
	}
	
	/**
	 * Getter method for previousNode instance variable
	 * 
	 * @return reference to the previous (or parent) Node
	 */
	public Node getPreviousNode() 
	{
		return previousNode;
	}
	
	/**
	 * Setter method for previousNode instance variable
	 * 
	 * @param previousNode is the reference to the previous (or parent) Node
	 */
	public void setPreviousNode(Node previousNode) 
	{
		this.previousNode = previousNode;
	}

	/**
	 * Getter method for nextList instance variable
	 * 
	 * @return reference to the List of next (or children) Nodes
	 */
	public List<Node> getNextList() 
	{
		return nextList;
	}
	
	/**
	 * Setter method for nextList instance variable
	 * 
	 * @param nextList is a reference to the List of words that we're going to explore next
	 */
	public void setNextList(List<Node> nextList) 
	{
		this.nextList = nextList;
	}
	
}

