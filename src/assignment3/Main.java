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

	// Constants and static variables
	public static String[] alphabet  = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
	// TODO Appropriate way to store input? (see @194 & @204)
	public static String[] words = {"",""};

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
			kb = new Scanner(System.in);	// default from Stdin
			ps = System.out;				// default to Stdout
		}
		
		initialize();

		//  Methods to read in words, output ladder
		
		ArrayList<String> words = parse(kb);
		ArrayList<String> wordLadder = getWordLadderBFS(words.get(0), words.get(1));
		printLadder(wordLadder);
		
	}
	
	/**
	 * Initialize the static variables and constants here.
	 * This method is called (once) before running JUNIT tests. 
	 */
	public static void initialize() 
	{
		// alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
		// words = new String[2];
	}

	/**
	 * Parses user input
	 * 
	 * @param keyboard Scanner connected to System.in
	 * @return ArrayList of 2 Strings containing start word and end word.
	 * If command is /quit, return empty ArrayList.
	 */
	public static ArrayList<String> parse(Scanner keyboard) 
	{	
		
		// TODO Rewrite 
		
		String pArray = keyboard.nextLine();
		
		// TODO Revise to account for "/quit" as second argument

		ArrayList<String> splitWords = new ArrayList<String>();
		String[] temp = pArray.split(" ");
		splitWords.add(temp[0]);
		words[0] = temp[0];
		splitWords.add(temp[1]);
		words[1] = temp[1];

		for(String s : splitWords){
			if(s.equals("/quit")){
				//return new ArrayList<String>();
				System.exit(0);
			}
		}

		return splitWords;
	}

	public static ArrayList<String> getWordLadderDFS(String start, String end) 
	{
		// Set static variables
		words[0] = start;
		words[1] = end;
		
		// Returned list should be ordered start to end.  Include start and end.
		// Return empty list if no ladder.
				
		// TODO Write method
		
		Set<String> dict = makeDictionary();

		return null; // TODO Replace this line later with real return
	}

    public static ArrayList<String> getWordLadderBFS(String start, String end) 
    {
    	// Set static variables
		words[0] = start;
		words[1] = end;
		
		// Initialization 
		Set<String> dict = makeDictionary();
		Set<String> encounteredWords = new HashSet<String>();
		Queue<Node> q = new LinkedList<Node>();
		Node wordTreeRoot = new Node(start.toUpperCase(), null);
		ArrayList<String> emptyLadder = new ArrayList<String>();
		
		// Return empty ladder if start and end are the same word
		if (start.equals(end)) {
			return emptyLadder;
		}
		
		q.add(wordTreeRoot);	// Add start to queue so loop condition doesn't fail
		encounteredWords.add(wordTreeRoot.getWord());
		Node current;
		
		while(!q.isEmpty()) {
			current = q.remove();
			if (current.getWord().equals(end)) {
				return treeToLadder(current);		// Build ladder as ArrayList and return
			}
			permutations(dict, encounteredWords, q, current);	// Updates queue and current node connections
		}
		
		// If there is no ladder, we return an empty list.
		return emptyLadder;
	}

	public static Set<String> makeDictionary() 
	{
		Set<String> words = new HashSet<String>();
		Scanner infile = null;
		
		// TODO Move dictionaries?
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
	
	// TODO Determine if this method will be called independently of parse() (see @194 & @204)
	// If so, we must update the words array in each search function.
	public static void printLadder(ArrayList<String> ladder) 
	{
		// All output must be in lower case
		if (ladder.isEmpty()) {
			System.out.println("no word ladder can be found between " + words[0].toLowerCase() + 
							   " and " + words[1].toLowerCase() + ".");
		}
		else {
			String start = ladder.get(0);
			String end = ladder.get(ladder.size() - 1);
			int rungLength = ladder.size() - 2;
			
			System.out.println("a " + rungLength + "-rung word ladder exists between " + 
								start.toLowerCase() + " and " + end.toLowerCase() + ".");
			for (String word : ladder) {
				System.out.println(word.toLowerCase());
			}
		}
		
	}
	
	
	// ----------------------------------- private static methods ----------------------------------- //
	
	
	private static void permutations(Set<String> dict, Set<String> encountered, Queue<Node> q, Node n)
	{
		String[] word = n.getWord().toUpperCase().split("");
		String[] tempStr = new String[word.length];

		List<Node> placeholder = n.getNextList();
		for(int i = 0; i < word.length; i++){
			/* 'tempStr = word' would be a "shallow copy"
			 * using the copyOf() method creates a "deep copy"
			 */
			tempStr = Arrays.copyOf(word, word.length);
			for(int j = 0; j < alphabet.length; j++){
				tempStr[i] = alphabet[j];
				StringBuilder permut = new StringBuilder();
				for(String letter: tempStr) {
					permut.append(letter);
				}
				String finalPermut = permut.toString();

				if(dict.contains(finalPermut) && !encountered.contains(finalPermut)){
					encountered.add(finalPermut);
					Node temp = new Node(finalPermut, n);
					placeholder.add(temp);
					q.add(temp);
				}

			}

		}

	}
	
	private static ArrayList<String> treeToLadder(Node end) 
	{
		ArrayList<String> wordLadder = new ArrayList<String>();
		Node current = end;
		while (current.getPreviousNode() != null) {
			wordLadder.add(current.getWord());
			current = end.getPreviousNode();
		}
		
		// Adds the first word as that doesn't get done in the while loop
		wordLadder.add(current.getWord());	
		
		// Puts word ladder in proper order 
		Collections.reverse(wordLadder);
		
		return wordLadder;
	}
	
}
