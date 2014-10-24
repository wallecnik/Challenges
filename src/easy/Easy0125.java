package easy;


import library.Library;

/**
 * Dailyprogrammer easy challenge number 125
 * Not all implemented
 *
 * @author Wallecnik
 * @version 24.10.2014
 */
public class Easy0125 {

    private String plainText = "";

    public Easy0125(String filePath) {

        plainText = Library.readFile(filePath);

    }

    @Override
    public String toString() {
        return plainText;
    }

    public int countWords() {
        return plainText.split("\\s+").length;
    }

    public int countLetters() {
        int count = 0;
        for (int i = 0; i < plainText.length(); i++) {
            String letter = "" + plainText.charAt(i);
            if ( letter.matches("[a-zA-Z]") ) {
                count++;
            }
        }
        return count;
    }

    public int countSymbols() {
        int count = 0;
        for (int i = 0; i < plainText.length(); i++) {
            String letter = "" + plainText.charAt(i);
            if ( letter.matches("[^a-z_A-Z_\\s]") ) {
                count++;
            }
        }
        return count;
    }

    /*
    public String countCommonWords() {
        String[] words = new String[countWords()];
        String[] commons = new String[3];
        int[] counts = new int[3];

        for (String word : words = plainText.split("\\s+")) {
            if (word == words[0]
        }

        return commons[0] + ", " + commons[1] + ", " + commons[2];
    }
    */

    public int countOnceUsedWords() {
        int numWords = countWords();
        String[] words = new String[numWords];
        words = plainText.split("\\s+");
        String used = "";
        int count = 0;

        for (int i = 0; i < numWords; i++) {
            if (used.contains(words[i])) count++;
            used += words[i] + " ";
        }

        return numWords-count;
    }

}
