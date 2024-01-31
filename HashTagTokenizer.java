

public class HashTagTokenizer {

	public static void main(String[] args) {

		String hashTag = args[0];
		String []dictionary = readDictionary("dictionary.txt");
		breakHashTag(hashTag, dictionary);
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

	public static boolean existInDictionary(String word, String []dictionary) {
		for (int i = 0; i < dictionary.length; i++) {
			if (dictionary[i].equals(word))
				return true;
		}
		return false;
	}

	public static void breakHashTag(String hashtag, String[] dictionary) {
		hashtag = hashtag.toLowerCase(); 
		// Base case: do nothing (return) if hashtag is an empty string.
        if (hashtag.isEmpty()) {
            return;
        }
 
        int N = hashtag.length();

        for (int i = 1; i <= N; i++) {
			String prefix = hashtag.substring(0,i);
			if (existInDictionary(prefix, dictionary) == true) {
				System.out.println(prefix);
				breakHashTag(hashtag.substring(i), dictionary);
				break;
			} 
        }
		breakHashTag(hashtag.substring(N), dictionary);
    }

}
