/* WORD LADDER Main.java
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
import java.io.*;

public class Main {

	// static variables and constants only here.

	public static void main(String[] args) throws Exception 
	{

		Scanner kb;	// input Scanner for commands
		PrintStream ps;	// output file
		// If arguments are specified, read/write from/to files instead of Std IO.
		if (args.length != 0) {
			kb = new Scanner(new File(args[0]));
			ps = new PrintStream(new File(args[1]));
			System.setOut(ps);			// redirect output to ps
		} else {
			kb = new Scanner(System.in);// default from Stdin
			ps = System.out;			// default to Stdout
		}
		
		initialize();

		// TODO methods to read in words, output ladder
	}

	public static void initialize() 
	{
		// initialize your static variables or constants here.
		// We will call this method before running our JUNIT tests.  So call it
		// only once at the start of main.
	}

	/**
	 * @param keyboard Scanner connected to System.in
	 * @return ArrayList of 2 Strings containing start word and end word.
	 * If command is /quit, return empty ArrayList.
	 */
	public static ArrayList<String> parse(Scanner keyboard) 
	{
		ArrayList<String> pArray = new ArrayList<String>();

		while(pArray.add(keyboard.nextLine())){}

		for(String s: pArray){
			if(s.equals("quit")){
				return new ArrayList<String>();
			}
			else{
				break;
			}
		}
		ArrayList<String> splitWords = new ArrayList<String>();
		String[] temp = pArray.get(0).split(" ");
		splitWords.add(temp[0]);
		splitWords.add(temp[1]);
		return splitWords;
	}

	public static ArrayList<String> getWordLadderDFS(String start, String end) 
	{

		// Returned list should be ordered start to end.  Include start and end.
		// Return empty list if no ladder.
		// TODO some code
		Set<String> dict = makeDictionary();
		// TODO more code

		return null; // replace this line later with real return
	}

    public static ArrayList<String> getWordLadderBFS(String start, String end) 
    {
		Set<String> dict = makeDictionary();
		Set<String> encountered = new HashSet<String>();
		Queue<Node> q = new LinkedList();
		Node wordTreeRoot = new Node(start);
		
		// Add start to queue so while loop condition doesn't fail
		q.add(wordTreeRoot);
		Node current;
		while(!q.isEmpty()) {
			
			current = q.remove();
			
			if (current.getWord().equals(end)) {
				
				// Build ladder as ArrayList
				// Return ladder
				
			}
		
			// for loop for each 1-letter permutation
			
		}
		
		return null; // TODO Replace this line later with real return
	}

	public static Set<String>  makeDictionary() 
	{
		Set<String> words = new HashSet<String>();
		Scanner infile = null;
		try {
			infile = new Scanner (new File("five_letter_words.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Dictionary File not Found!");
			e.printStackTrace();
			System.exit(1);
		}
		while (infile.hasNext()) {
			words.add(infile.next().toUpperCase());
		}
		return words;
	}

	public static void printLadder(ArrayList<String> ladder) 
	{

	}
	
	// TODO
	// Other private static methods here

	private static void permutations(Set<String> dict, Set<String> encountered, Queue<Node> q, Node n){
		String[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
		String[] word = n.getWord().toUpperCase().split("");
		String[] tempStr;
		List<Node> temp = n.getNextList();
		for(int i = 0; i < word.length; i ++){
			for(int j = 0; j < alphabet.length; j ++){
				tempStr = word;
			}

		}

	}
	
	private static ArrayList<String> treeToLadder(Node end) 
	{
		ArrayList<String> wordLadder = new ArrayList<String>();
		Node current = end;
		while (current.getPreviousNode() != null) {
			
		}
		
		return wordLadder;
	}
	
}
