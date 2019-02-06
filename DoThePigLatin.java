import java.util.Scanner;

public class DoThePigLatin {

	/*
	 * Created by Luna
	 */

	private static String pigged(String input) {

		input = input.toLowerCase();
		String[] strArr = input.split(" "); // Split the input by the space and store them in an array
		String punctuation = ",.;:!?";
		String word = "", savePunctuation = "", pigLatin = "";

		for (int i = 0; i < strArr.length; i++) {

			word = strArr[i];

			if (startsWithVowel(word)) {

				if (punctuationCheck(word, punctuation)) {
					// Concatenate saved punctuation at the end of the word
					savePunctuation = punctuationFilter(word);
					word = removePunctuation(word) + "-yay" + savePunctuation;
				} else {
					word = word + "-yay";
				}
			}

			else {

				if (punctuationCheck(word, punctuation)) {
					savePunctuation = punctuationFilter(word);
					word = removePunctuation(word);

					if (startsWithConsonantCluster(word)) {
						word = consonantClusterFormat(word) + "ay" + savePunctuation;
					} else {
						word = startsWithConsonant(word) + "ay" + savePunctuation;
					}

				} else {

					if (startsWithConsonantCluster(word)) {
						word = consonantClusterFormat(word) + "ay";
					} else {
						word = startsWithConsonant(word) + "ay";
					}
				}
			}

			pigLatin += word + " ";
		}

		return pigLatin;
	}

	private static boolean startsWithVowel(String word) {

		if (word.charAt(0) == 'a' || word.charAt(0) == 'e' || word.charAt(0) == 'i' || word.charAt(0) == 'o'
				|| word.charAt(0) == 'u') {
			return true;
		}

		return false;
	}

	private static boolean startsWithConsonantCluster(String word) {

		if (!startsWithVowel(word) && !startsWithVowel(word.substring(1, word.length()))) {
			return true;
		}

		return false;
	}

	private static String consonantClusterFormat(String word) {

		for (int i = 0; i < word.length(); i++) {

			if (startsWithVowel("" + word.charAt(i))) {
				word = word.substring(i, word.length()) + "-" + word.substring(0, i);
				return word;
			}
		}

		return word;
	}

	private static String startsWithConsonant(String word) {

		char c = word.charAt(0);

		word = word.substring(1, word.length()) + "-" + c;

		return word;
	}

	private static boolean punctuationCheck(String word, String punctuation) {

		for (int i = 0; i < word.length(); i++) {

			if (punctuation.contains("" + word.charAt(i))) {
				return true;
			}
		}

		return false;
	}

	private static String punctuationFilter(String word) {

		String punctuation = "";

		for (int i = 0; i < word.length(); i++) {

			if (!Character.isLetter(word.charAt(i))) {
				punctuation = word.substring(i);
				return punctuation;
			}
		}

		return punctuation;
	}

	private static String removePunctuation(String word) {

		for (int i = 0; i < word.length(); i++) {

			if (!Character.isLetter(word.charAt(i))) {
				word = word.substring(0, i);
			}
		}

		return word;
	}

	
	public static void main(String[] args) {

		Scanner keybScan = new Scanner(System.in);
		String input = "";

		System.out.println("-- " + pigged("Welcome to the World of Pig Latin") + "--");
		System.out.println("Validated punctuation marks include: , . ; : ! ?\n");
		System.out.print("Try me: ");
		input = keybScan.nextLine();
		System.out.println("Piggy says: " + pigged(input) + "\n");
		System.out.print("Would you like to continue? Enter \"Yes\" to continue: ");
		input = keybScan.nextLine();

		input = input.toLowerCase();
		if ((input.toLowerCase()).equals("yes")) {

			while (true) {

				System.out.print("\nTry me: ");
				input = keybScan.nextLine();
				System.out.println("Piggy says: " + pigged(input) + "\n");
				System.out.print("Would you like to continue? Enter \"Yes\" to continue: ");
				input = keybScan.nextLine();

				if (!(input.toLowerCase()).equals("yes")) {
					System.out.println("Piggy says: " + pigged("Good bye") + "\n");
					break;
				}
			}
		}

		System.out.println("-- " + pigged("Program is ending") + " --");
	}

}
