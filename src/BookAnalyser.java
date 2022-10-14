import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringTokenizer;
/**
 * <b>BookAnalyser</b>
 * BookAnalyser is a software that is able to perform analysis on the content of books.
 * 
 * @author <qian2z>
 * @version 1.0
 * final submission 26 Nov 2021
 */
public class BookAnalyser {
	
	private static WordList wordList = new WordList();
	private static String[] stopWord = {"a", "an", "and", "are", "as", "at", "be", "by", "for", "from", "has", "he", "in", "is", "it", "its", "of",
										"on", "that", "the", "to", "was", "were", "will", "with"};
	private static String[] actionList = {"count() - The total word count from the book.", "find() - The availability of the query word in the book.", 
			                              "findUnique() - All unique words in the book.", "topKWords() - The top K most frequently appearing words in the book.",
			                              "ttr() - Type Token Ratio."};
	
	// standardize all the words in the text file
	public static String preProcess(String text) {
		String processed = text;
		processed = processed.toLowerCase().replaceAll("[^a-zA-Z0-9]", " ");
		processed = processed.replaceAll("[\\d.]", "");
		processed = " " + processed + " ";
		for(String element : stopWord) {
			processed = processed.replace((" " + element + " "), " ");
		}
		return processed;
	}
	
	// print options of analysis to user
	public static void printOption(int index) {
		System.out.println("\nAnalysis: " + actionList[index - 1]);
	}
	
	// count() action
	public static void actionCount() {
		System.out.println("The total word count from the book: " + wordList.count() + " words.");
	}
	
	// find() action, get the query word from user
	public static void actionFind(Scanner scan) {
		System.out.println("Please enter your query word: ");
		System.out.print(">> ");
		String word = scan.next();
		if(wordList.find(word)) {
			System.out.println("The word '" + word + "' is available in the book.");
		}
		else {
			System.out.println("The word '" + word + "' is not available in the book.");
		}
	}
	
	// findUnique() action
	public static void actionFindUnique() {
		wordList.printFindUnique();
	}
	
	// topKWords() action, get the value of K from user
	public static void actionTopKWords(Scanner scan) {
		System.out.println("Please enter the value of K: ");
		System.out.print(">> ");
		int k = scan.nextInt();
		wordList.topKWords(k);
	}
	
	// ttr() action
	public static void actionTTR() {
		System.out.println("Type Token Ratio: " + wordList.ttr() + "%");
	}
	
	public static void main(String[] args) {
		
		// get input file from user
		System.out.println("Welcome to BookAnalyser.");
		Scanner user = new Scanner(System.in);
		System.out.println("Please enter the name of text file for analysing: ");
		System.out.print(">> ");
		String fileName = user.next();
		File text;
		Scanner scan_file = null;
		boolean checkFile = false;
		while(!checkFile) {
			try {
				text = new File(fileName);
				scan_file = new Scanner(text);
				checkFile = true;
			}
			catch(FileNotFoundException e) {
				System.err.println("Can't file the file " + fileName + ". Please enter again.");
				System.out.print(">> ");
				fileName = user.next();
			}
		}
		
		// read the input file, do pre-processing on text, insert processed words into WordList
		System.out.println("File " + fileName + " is analysing...");
		while(scan_file.hasNextLine()) {
			String line = scan_file.nextLine();
			StringTokenizer strtoken = new StringTokenizer(preProcess(line));
			while(strtoken.hasMoreTokens()) {
				wordList.add(strtoken.nextToken());
			}
		}
		
		// call calculateOccurenceOfEachWord() method first to make the analysis easier later
		wordList.calculateOccurrenceOfEachWord();
		// add unique words into UniqueWordList
		wordList.findUnique();
		
		// get options from user for analysis
		int index;
		boolean checkInput = false;
		do {
			try {
				do {
					System.out.println("\nOptions for text file analysis: ");
					for(int i = 1; i <= actionList.length; i++) {
						System.out.println("#" + i + ": " + actionList[i - 1]);
					}
					System.out.println("#0: EXIT");
					System.out.println("Please enter the index number to perform the analysis: ");
					System.out.print(">> ");
					index = user.nextInt();
					switch(index) {
						case 1: 
							printOption(index);
							actionCount();
							break;
						case 2: 
							printOption(index);
							actionFind(user);
							break;
						case 3: 
							printOption(index);
							actionFindUnique();
							break;
						case 4: 
							printOption(index);
							actionTopKWords(user);
							break;
						case 5: 
							printOption(index);
							actionTTR();
							break;
						case 0:
							System.out.println("Thanks for using BookAnalyser!");
							break;
					}
				}
				while(index != 0);
				checkInput = true;
			}
			catch(InputMismatchException e) {
				System.err.println("Invalid input. Please enter the index number again.");
				user.reset();
				user.next();
			}
		}
		while(!checkInput);
		
	}

}
