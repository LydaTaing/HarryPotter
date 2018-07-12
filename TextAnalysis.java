package project;

import java.io.*;
import java.util.*;

/**
 * @author tainglyda CSC 143 May 3, 2018
 * 
 * This program finds the top 10 words and number of times they occur in
 * books by Charlotte Bronte but not used by Jane Austen.
 *
 */

public class TextAnalysis {

	public static void main(String[] args) throws FileNotFoundException {

		// joint of all words from JA's books
		Map<String, Integer> jJA = new TreeMap<>();
		jJA = jointJA();

		// joint of all words from CB's books
		Map<String, Integer> jCB = new TreeMap<>();
		jCB = jointCB();

		// Map that only contains CB but not in JA.
		Map<String, Integer> CBnotJA = new TreeMap<>();
		CBnotJA = different(jJA, jCB);

		// New map of top 10 words in CB's books.
		Map<String, Integer> mtemp = new HashMap<>();
		Map<String, Integer> tempCB = new TreeMap<>(CBnotJA);

		String max = findMax(tempCB);
		for (int i = 0; i < 10; i++) {
			tempCB.remove(max);
			mtemp.put(max, CBnotJA.get(max));
			max = findMax(tempCB);
		}

		for (String s : mtemp.keySet()) {

			System.out.println(s + " : " + mtemp.get(s));
		}

	}

	// post: return a key that relates to the value that occurs the most.
	private static String findMax(Map<String, Integer> tempCB) {
		int max = Integer.MIN_VALUE;
		String current = null;
		for (String s : tempCB.keySet()) {
			if (tempCB.get(s) > max) {
				max = tempCB.get(s);
				current = s;
			}
		}
		return current;
	}

	// post: return a map that contains only element that occur by CB, but not JA.
	private static Map<String, Integer> different(Map<String, Integer> jJA, Map<String, Integer> jCB) {
		Map<String, Integer> CBnotJA = new TreeMap<>();
		for (String s : jCB.keySet()) {
			if (!(jJA.containsKey(s))) {
				CBnotJA.put(s, jCB.get(s));
			}
		}

		return CBnotJA;
	}

	// post: built the map of all the word in the books by JA.
	private static Map<String, Integer> jointJA() throws FileNotFoundException {

		String file1 = "Pride and Prejudice.txt";
		String file2 = "Emma.txt";
		String file3 = "Sense and Sensibility.txt";
		String file4 = "Persuasion.txt";
		String file5 = "Mansfield Park.txt";
		Map<String, Integer> mCB1 = new TreeMap<>();
		mCB1 = ScanFile(file1);
		Map<String, Integer> mCB2 = new TreeMap<>();
		mCB2 = ScanFile(file2);
		Map<String, Integer> mCB3 = new TreeMap<>();
		mCB3 = ScanFile(file3);
		Map<String, Integer> mCB4 = new TreeMap<>();
		mCB4 = ScanFile(file4);
		Map<String, Integer> mCB5 = new TreeMap<>();
		mCB5 = ScanFile(file5);

		Map<String, Integer> jCB = new TreeMap<>();
		jCB.putAll(mCB1);
		jCB.putAll(mCB2);
		jCB.putAll(mCB3);
		jCB.putAll(mCB4);
		jCB.putAll(mCB5);
		jCB.remove("");

		return jCB;
	}

	// post: built the map of all the word in the books by CB.
	private static Map<String, Integer> jointCB() throws FileNotFoundException {
		String file6 = "Jane Eyre An Autobiography.txt";
		String file7 = "Villette.txt";
		String file8 = "Shirley.txt";
		String file9 = "The Professor.txt";

		Map<String, Integer> mCB6 = new TreeMap<>();
		mCB6 = ScanFile(file6);
		Map<String, Integer> mCB7 = new TreeMap<>();
		mCB7 = ScanFile(file7);
		Map<String, Integer> mCB8 = new TreeMap<>();
		mCB8 = ScanFile(file8);
		Map<String, Integer> mCB9 = new TreeMap<>();
		mCB9 = ScanFile(file9);

		Map<String, Integer> jCB = new TreeMap<>();
		jCB.putAll(mCB6);
		jCB.putAll(mCB7);
		jCB.putAll(mCB8);
		jCB.putAll(mCB9);
		jCB.remove("");

		return jCB;
	}

	// post: build a map of word in a book and return it.
	private static Map<String, Integer> ScanFile(String file1) throws FileNotFoundException {
		Scanner infile = new Scanner(new File(file1));
		Map<String, Integer> mCB = new TreeMap<>();

		while (infile.hasNext()) {
			String word = cleanword(infile);
			if (mCB.containsKey(word)) {
				mCB.put(word, mCB.get(word) + 1);
			} else {
				mCB.put(word, 1);
			}
		}

		return mCB;
	}

	// post: return a clean word.
	private static String cleanword(Scanner infile) {
		String word = infile.next().toLowerCase();//.replaceAll("[^a-z|^0-99]", "");
		word= word.replace("93", "");
		word= word.replace("94", "");
		word= word.replace("91", "");
		word= word.replace("!", "");
		word= word.replace(",", "");
		word= word.replace(".", "");
		word= word.replace("\'", "");
		word= word.replace("\\", "");
		word= word.replace("\"", "");
		word= word.replace("-", "");
		word= word.replace(" '", "");
		word= word.replace("?'", "");
		word= word.replace("(", "");
		word= word.replace(")", "");
		word= word.replace("-", "");
		word= word.replace("_", "");
		word= word.replace(":", "");
		word= word.replace("%", "");
		word= word.replace("&", "");
		word= word.replace("*", "");
		word= word.replace("#", "");
		word= word.replace("@", "");
		word= word.replace("$", "");
		word= word.replace("=", "");
		word= word.replace("?", "");
		word= word.replace(";", "");

		return word;
	}

}
