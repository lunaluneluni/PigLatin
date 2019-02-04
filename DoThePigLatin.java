import java.util.Scanner;

public class DoThePigLatin {

	/*
	 * Created by Luna
	 * Last updated: 3/20/2018
	 */

	private static String pigged(String input) {

		String[] strArr = input.split(" "); // Split the input by the space and store them in an array
		String punctuation = ",.;:!?";
		String word = "", p = "", pig = "";

		for (int i = 0; i < strArr.length; i++) {

			word = strArr[i];

			// Starts with vowels
			if (isVowel(word)) {

				// Checking for punctuation
				if (isPunc(word, punctuation)) {
					p = punc(word); // Save the punctuation to be concatenated at the end of the word
					word = removePunc(word) + "-yay" + p;
				} else {
					word = word + "-yay";
				}
			}
			// Starts with consonants
			else {

				// Checking for punctuation
				if (isPunc(word, punctuation)) {
					p = punc(word); // Save the punctuation to be concatenated at the end of the word
					word = removePunc(word);

					// Checking for consonant cluster
					if (isCluster(word)) {
						word = cluster(word) + "ay" + p; 
					} else {
						word = notCluster(word) + "ay" + p;
					}

				} else {

					// Checking for consonant cluster
					if (isCluster(word)) {
						word = cluster(word) + "ay";
					} else {
						word = notCluster(word) + "ay";
					}
				}
			}

			pig += word + " ";
		}

		return pig;
	}

	// Returns true if the word starts with a vowel
	private static boolean isVowel(String word) {

		word = word.toLowerCase();

		if (word.charAt(0) == 'a' || word.charAt(0) == 'e' || word.charAt(0) == 'i' || word.charAt(0) == 'o'
				|| word.charAt(0) == 'u') {
			return true;
		}

		return false;
	}

	// Returns true if the word starts with a consonant cluster
	private static boolean isCluster(String word) {

		if (!isVowel(word) && !isVowel(word.substring(1, word.length()))) {
			return true;
		}

		return false;
	}

	// Helps to format the word beginning with a consonant cluster
	private static String cluster(String word) {

		for (int i = 0; i < word.length(); i++) {

			if (isVowel("" + word.charAt(i))) {
				word = word.substring(i, word.length()) + "-" + word.substring(0, i);
				return word;
			}
		}

		return word;
	}

	// Helps to format the word beginning with a consonant
	private static String notCluster(String word) {

		char c = word.charAt(0);

		word = word.substring(1, word.length()) + "-" + c;

		return word;
	}

	// Checks for punctuation
	private static boolean isPunc(String word, String punctuation) {

		for (int i = 0; i < word.length(); i++) {

			if (punctuation.contains("" + word.charAt(i))) {
				return true;
			}
		}

		return false;
	}

	// Returns punctuation from the given word
	private static String punc(String word) {

		String p = "";

		for (int i = 0; i < word.length(); i++) {

			if (!Character.isLetter(word.charAt(i))) {
				p = word.substring(i);
				return p;
			}
		}

		return p;
	}

	// Removes punctuation (formatting purpose)
	private static String removePunc(String word) {

		for (int i = 0; i < word.length(); i++) {

			if (!Character.isLetter(word.charAt(i))) {
				word = word.substring(0, i);
			}
		}

		return word;
	}


	public static void main(String[] args) {

		Scanner keyb = new Scanner(System.in);
		String input = "";
		
		System.out.println("-- " + pigged("Welcome to the World of Pig Latin") + "--");
		System.out.println("Validated punctuation marks include: , . ; : ! ?\n");
		System.out.print("Try me: ");
		input = keyb.nextLine();
		System.out.println("Piggy says: " + pigged(input) + "\n");
		System.out.print("Would you like to continue? Enter \"Yes\" to continue: ");
		input = keyb.nextLine();
		
		if((input.toLowerCase()).equals("yes")) {

			while(true) {
				
				System.out.print("\nTry me: ");
				input = keyb.nextLine();
				System.out.println("Piggy says: " + pigged(input) + "\n");
				System.out.print("Would you like to continue? Enter \"Yes\" to continue: ");
				input = keyb.nextLine();
				
				if(!(input.toLowerCase()).equals("yes")) {
					System.out.println("Piggy says: "+ pigged("Good bye") + "\n");
					break;
				}
			}
		}
		
		System.out.println("-- " + pigged("Program is ending") + " --");
	}

}
