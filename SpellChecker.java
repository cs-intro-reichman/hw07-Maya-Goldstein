
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		if (str.length() == 1)
			return "";
		return str.substring(1);
	}

	public static int levenshtein(String word1, String word2) {
		//case-insensitive
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();

		int lenWord1 = word1.length();
		int lenWord2 = word2.length();

		if (lenWord2 == 0)
			return lenWord1;
		if (lenWord1 == 0)
			return lenWord2;
		if (word1.charAt(0) == word2.charAt(0))
			return levenshtein(tail(word1), tail(word2));
		else {
			return 1 + Math.min(Math.min(levenshtein(tail(word1), word2), levenshtein(word1, tail(word2))), levenshtein(tail(word1), tail(word2)));
		}

	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);
		int i = 0;

		while (!in.isEmpty()) {
			String word = in.readLine();
			dictionary[i] = word;
			i++;
		}

		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		int min = levenshtein(word, dictionary[0]);
		String minWord = dictionary[0];

		for (int i = 1; i < dictionary.length; i++) {
			int editDistance = levenshtein(word, dictionary[i]);
			if (editDistance < min) {
				min = editDistance;
				minWord = dictionary[i];
			}
		}

		if (min > threshold)
			return word;
		return minWord;
	}

}
