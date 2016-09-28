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

		while(pArray.add(keyboard.next())){}

		for(String s: pArray){
			if(s.equals("quit") || s.equals("exit")){
				return new ArrayList<String>();
			}
			else{
				break;
			}
		}
		return pArray;
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

		// Initialize a Queue using a LinkedList
    	
    	// Initialize a Set using HashSet for encountered words
    	
    	// Initialize a Node for use with the start word
    	
		Set<String> dict = makeDictionary();
		

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
	
}
