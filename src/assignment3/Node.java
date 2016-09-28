/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Replace <...> with your actual data.
 * Ali Ziyaan Momin
 * AZM259
 * 16470
 * Aaron Babber
 * aab3456
 * 16480
 * Slip days used: 0
 * Git URL: https://github.com/AABabber/EE_422C_Project3.git
 * Fall 2016
 */

package assignment3;

import java.util.List;

public class Node {
	
	private String word;	// Data stored in this instance
	private Node previousNode;	// Parent of this instance
	private List<Node> nextList;	// List of Nodes with words that differ from this one by one letter
	
	public Node(String word) 
	{
		
		// TODO Write constructor
		
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
	public Node getPrevious() 
	{
		return previousNode;
	}
	
	/**
	 * Setter method for previousNode instance variable
	 * 
	 * @param previousNode is the reference to the previous (or parent) Node
	 */
	public void setPrevious(Node previousNode) 
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

/*
Create an empty queue.
Add the start word to the end of the queue.
while (the queue is not empty) {
	Dequeue the first ladder from the queue.
	if (the final word in this ladder is the destination word){
		Return this ladder as the solution.
	}
	for (each word in the lexicon of English words that differs by one letter){
		if (that word has not already been used in a ladder) {
			Create a copy of the current ladder.
			Add the new word to the end of the copy.
			Add the new ladder to the end of the queue.
		}
	}
}
Report that no word ladder exists.
 */
