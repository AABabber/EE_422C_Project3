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
	public static String[] alphabet;
	// TODO Appropriate way to store input? (see @194 & @204)
	public static String[] words;
	public static HashMap<String, HashSet<String>> linkedDict;


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
		
		
		
		
//		long time1 = System.nanoTime();		// TODO Delete this
		initialize();
//		long time2 = System.nanoTime();		// TODO Delete this

		//  Methods to read in words, output ladder

//		ArrayList<String> words = parse(kb);
		
//		long time3 = System.nanoTime();		// TODO Delete this
//		ArrayList<String> wordLadder = getWordLadderBFS(words.get(0), words.get(1));
//		printLadder(wordLadder);
//		long time4 = System.nanoTime();		// TODO Delete this

//		System.out.println("initialize: " + (time2 - time1) + " ns");		// TODO Delete this
//		System.out.println("BFS & printLadder: " + (time4 - time3) + " ns");		// TODO Delete this
		getWordLadderDFS("START", "STATE");
		
		return;
	}
	
	/**
	 * Initialize the static variables and constants here.
	 * This method is called (once) before running JUNIT tests. 
	 */
	public static void initialize() 
	{
		// TODO Check if initializations hold for multiple instances
		alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
		words = new String[2];
		constructLinkedDict();
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
	    String input = keyboard.nextLine();

	    /* Divides the input into its respective commands and stores
	     * the results in an array. The regex "\\s+" splits the input String
	     * using any whitespace character (the \\s part) as a delimiter. The '+'
	     * character is a quantifier for "one or more times".
	     */
	    String[] commands = input.split("\\s+");
	    ArrayList<String> splitWords;

	    if (commands.length < 1) {
	        System.exit(1);
	    }

	    splitWords = new ArrayList<String>(Arrays.asList(commands));
	    for (String word : splitWords) {
	        if (word.equals("/quit")) {
	            System.exit(0);
	        }
	    }

	    words[0] = splitWords.get(0);
	    words[1] = splitWords.get(1);

	    return splitWords;
	}

	public static ArrayList<String> getWordLadderDFS(String start, String end) 
	{
		// Set static variables
		words[0] = start.toUpperCase();
		words[1] = end.toUpperCase();
		
		// Returned list should be ordered start to end.  Include start and end.
		// Return empty list if no ladder.
				
		Set<String> dict = makeDictionary();
		Set<String>	visited = new HashSet<String>();
		ArrayList<String> ladder = new ArrayList<String>();
		ladder.add(start);

		if(myDFS(start, end, dict, visited, ladder)){
			Collections.reverse(ladder);
			printLadder(ladder);
		}

		return ladder; 
	}

    public static ArrayList<String> getWordLadderBFS(String start, String end) 
    {
    	// Set static variables
		words[0] = start.toUpperCase();
		words[1] = end.toUpperCase();
		
		// Initialization 
		Set<String> encounteredWords = new HashSet<String>();
		Queue<String> q = new LinkedList<String>();
		ArrayList<String> emptyLadder = new ArrayList<String>();
		Map<String, String> parentMap = new HashMap<String, String>();
		
		// Return empty ladder if start and end are the same word
		if ((start.toUpperCase()).equals(end.toUpperCase())) {
			return emptyLadder;
		}
		
		// Set values to pass initial loop conditions		
		q.add(start.toUpperCase());
		encounteredWords.add(start.toUpperCase());
		
		while (!q.isEmpty()) {
			
			String current = q.remove();
			HashSet<String> linkedValues = linkedDict.get(current);
			
			if (linkedValues.contains(end.toUpperCase())) {
				parentMap.put(end.toUpperCase(), current.toUpperCase());
				return parentMapToLadder(parentMap, end.toUpperCase());
			}
			else {
				update(encounteredWords, q, current, parentMap);
			}
			
		}
		
		// If there is no ladder, we return an empty list.
		return emptyLadder;	
	}

	public static Set<String> makeDictionary() 
	{
		Set<String> words = new HashSet<String>();
		Scanner infile = null;
		
		// TODO Verify Scanner initialization and file placement
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
	
	// TODO Double check @194 & @204
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
	
	
	private static void update(Set<String> encountered, Queue<String> q, String current, Map<String, String> parentMap)
	{
		HashSet<String> linkedEntries = linkedDict.get(current);
		
		for (String word : linkedEntries) {
			if (!encountered.contains(word.toUpperCase())) {
				q.add(word.toUpperCase());
				encountered.add(word.toUpperCase());
				parentMap.put(word.toUpperCase(), current.toUpperCase());
			}
		}
		
		return;
	}
	
	
	private static HashSet<String> dictPermutations(Set<String> dict, String word)
	{
		String[] wordArray = word.toUpperCase().split("");
		String[] tempStrArray = new String[wordArray.length];
		HashSet<String> entry = new HashSet<String>();

		for (int i = 0; i < wordArray.length; i++){
			/* 'tempStr = word' would be a "shallow copy"
			 * using the copyOf() method creates a "deep copy"
			 */
			tempStrArray = Arrays.copyOf(wordArray, wordArray.length);
			
			for (int j = 0; j < alphabet.length; j++){
				tempStrArray[i] = alphabet[j];
				StringBuilder permut = new StringBuilder();
				
				for (String letter : tempStrArray) {
					permut.append(letter);
				}
				String finalPermut = permut.toString().toUpperCase();
				
				if(dict.contains(finalPermut)){
					entry.add(finalPermut);
				}

			}

		}
		
		return entry;
	}
	
	private static void constructLinkedDict()
	{
		linkedDict = new HashMap<String, HashSet<String>>();
		Set<String> dict = makeDictionary();
		
		for (String word : dict) {
			HashSet<String> entry = dictPermutations(dict, word);
			linkedDict.put(word.toUpperCase(), entry);
		}
		
		return;
	}
	
	private static ArrayList<String> parentMapToLadder(Map<String, String> parentMap, String lastWord)
	{
		ArrayList<String> wordLadder = new ArrayList<String>();
		wordLadder.add(lastWord.toUpperCase());
		
		while (!wordLadder.contains(words[0].toUpperCase())) {
			String child = wordLadder.get(wordLadder.size() - 1);
			String parent = parentMap.get(child);
			wordLadder.add(parent.toUpperCase());
		}
		
		Collections.reverse(wordLadder);	// Put word ladder in proper order
		return wordLadder;
	}

	private static boolean myDFS(String start, String end, Set<String> dict, Set<String> visited, ArrayList<String> ladder)
	{
		visited.add(start);

		for(int i = 0; i < start.length(); i++){
			StringBuilder sb = new StringBuilder(start);
			for(char ch = 'A'; ch <= 'Z'; ch ++){
				if(start.charAt(i) == ch){
					continue;
				}
				sb.setCharAt(i, ch);
				String word = sb.toString();
				if(word.equals(end)){
					System.out.println(word);
					//ladder.add(word);
					return true;
				}
				else if(dict.contains(word) && !visited.contains(word)){
					boolean found = myDFS(word, end, dict, visited, ladder);
					if(found){
						System.out.println(word);
						//ladder.add(word);
						return true;
					}

					//System.out.println(word);

				}
			}
		}
		
		visited.remove(start);
		return false;
	}

}
